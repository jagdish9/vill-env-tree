package dependency;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParsePomXmlFile {

    public static void main(String[] args) {
      //  String str = "C:\\softwares\\OSCS_9\\oscskb-crawler\\oscskbcrawler\\ARTIFACTS_TAR_FILE\\com.itextpdf_html2pdf_2.1.6";
      //  String str = "C:\\Users\\jkaiwari\\Downloads\\mina-sshd-sshd-2.2.0\\mina-sshd-sshd-2.2.0";
        String str = "C:\\Users\\jkaiwari\\Downloads\\james-project-james-project-3.5.0\\james-project-james-project-3.5.0";
        getPomDependencies(new File(str), "test1test");
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(ParsePomXmlFile.class);
    private static StringBuilder allPomPaths = new StringBuilder();
    private static Map<String, PomModel> pomDependencies = new HashMap<>();

    public static Map<String, PomModel> getPomDependencies(File homeDir, String artifact) {
        recursiveSearch(homeDir);
        String[] dependencyStr = allPomPaths.toString().split(",");
        allPomPaths.deleteCharAt(allPomPaths.length()-1);
        Arrays.sort(dependencyStr, (s1, s2) -> s1.length() - s2.length());
        for(String dependency : dependencyStr) {
            parsePomFile(dependency, artifact);
        }
        return pomDependencies;
    }

    public static void clearPomPathAndDependencies() {
        allPomPaths.setLength(0);
        pomDependencies.clear();
    }

    private static void recursiveSearch(File file){
        if (file.isDirectory()) {
            if (file.canRead()) {
                File[] files = file.listFiles();
                if (files != null && file.isDirectory()) for (File temp : files) {
                    if(temp.isDirectory()) {
                        recursiveSearch(temp);
                    }
                    else {
                        storePomPath(temp);
                    }
                }
            }
        }
    }

    private static void storePomPath(File file) {
        if(file.getName().endsWith("pom.xml") || file.getName().endsWith(".pom")) {
            allPomPaths.append(file.getAbsolutePath()).append(",");
        }
    }

    private static void parsePomFile(String path, String artifact) {
        MavenXpp3Reader mavenReader = new MavenXpp3Reader();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
            Model model = mavenReader.read(fileReader);
            List<Dependency> dependencies = model.getDependencies();
            if(dependencies != null && dependencies.isEmpty()) {
                DependencyManagement dependencyManagement = model.getDependencyManagement();
                if(dependencyManagement != null && dependencyManagement.getDependencies() != null && !dependencyManagement.getDependencies().isEmpty()) {
                    dependencies = model.getDependencyManagement().getDependencies();
                }
            }
            processForDependencies(dependencies, model, artifact);
            if(dependencies != null && !dependencies.isEmpty() && model.getDependencyManagement() != null &&
                    model.getDependencyManagement().getDependencies() != null) {
                dependencies = model.getDependencyManagement().getDependencies();
            }
            processForDependencies(dependencies, model, artifact);
        }
        catch(IOException | XmlPullParserException ioe){
            ioe.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    LOGGER.info("closing pom file");
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void processForDependencies(List<Dependency> dependencies, Model model, String artifact) {
        Properties pomProperties = model.getProperties();
        if(artifact.startsWith("io.vertx")) {
            pomProperties = parsePomProperties(pomProperties);
        }
        for(Dependency dependency : dependencies) {
            String dependencyGroupId = getDependencyGroupIdFinal(dependency, pomProperties, model);
            String dependencyArtifactId = getDependencyArtifactIdFinal(dependency, pomProperties, model);
            String dependencyVersion = getDependencyVersion(dependency, pomProperties, model);
            if(dependencyVersion != null && dependencyVersion.contains("$")) dependencyVersion = null;
            PomModel pomModel = new PomModel();
            String gavId = null;
            boolean isWithoutVersion = false;
            String groupId = dependencyGroupId;
            if(dependencyVersion == null) {
                gavId = groupId + ":" + dependencyArtifactId;
                isWithoutVersion = true;
            }
            else{
                gavId = groupId + ":" + dependencyArtifactId + ":" + dependencyVersion;
            }

            if(isWithoutVersion) {
                String dependentGavId = null;
                for(Map.Entry<String, PomModel> eachDependency : pomDependencies.entrySet()) {
                    if(eachDependency.getKey().contains(gavId)) {
                        dependentGavId = eachDependency.getKey();
                        break;
                    }
                }
                if(dependentGavId != null) {
                    pomDependencies.get(dependentGavId).setOptional(dependency.isOptional());
                }
                else {
                    pomModel.setGavId(gavId);
                    pomModel.setScope(dependency.getScope());
                    pomModel.setOptional(dependency.isOptional());
                    pomDependencies.put(gavId, pomModel);
                }
            }
            else {
                pomModel.setGavId(gavId);
                pomModel.setScope(dependency.getScope());
                pomModel.setOptional(dependency.isOptional());
                pomDependencies.put(gavId, pomModel);
            }
        }
    }

    private static int getElementsCount(Properties pomProperties) {
        int elementsCount = 0;
        Enumeration enu = pomProperties.elements();
        while(enu.hasMoreElements()) {
            enu.nextElement();
            elementsCount++;
        }
        return elementsCount;
    }

    private static String getDependencyArtifactId(Dependency dependency, Properties properties, Model model) {
        String dependencyArtifactId = dependency.getArtifactId();
        if(dependencyArtifactId != null && dependencyArtifactId.contains("$") && !dependencyArtifactId.contains("project.ArtifactId")) {
            dependencyArtifactId = dependencyArtifactId.replace("$", "")
                    .replace("{", "")
                    .replace("}", "");
            if(properties != null) {
                dependencyArtifactId = properties.getProperty(dependencyArtifactId);
                if(dependencyArtifactId != null && dependencyArtifactId.contains("$")) {
                    return getDependencyArtifactId(dependency, properties, model);
                }
                return dependencyArtifactId;
            }
        }
        else if(dependencyArtifactId != null && dependencyArtifactId.contains("project.ArtifactId")) {
            dependencyArtifactId = model.getArtifactId();
            if(dependencyArtifactId == null) {
                if(model.getParent() != null) {
                    dependencyArtifactId = model.getParent().getArtifactId();
                }
            }
        }
        return dependencyArtifactId;
    }

    private static String getDependencyGroupId(Dependency dependency, Properties properties, Model model) {
        String dependencyGroupId = dependency.getGroupId();
        if(dependencyGroupId != null && dependencyGroupId.contains("$") && !dependencyGroupId.contains("project.groupId")) {
            dependencyGroupId = dependencyGroupId.replace("$", "")
                    .replace("{", "")
                    .replace("}", "");
            if(properties != null) {
                dependencyGroupId = properties.getProperty(dependencyGroupId);
                if(dependencyGroupId != null && dependencyGroupId.contains("$")) {
                    return getDependencyGroupId(dependency, properties, model);
                }
                return dependencyGroupId;
            }
        }
        else if(dependencyGroupId != null && dependencyGroupId.contains("project.groupId")) {
            dependencyGroupId = model.getGroupId();
            if(dependencyGroupId == null) {
                if(model.getParent() != null) {
                    dependencyGroupId = model.getParent().getGroupId();
                }
            }
        }
        return dependencyGroupId;
    }

    private static String getDependencyArtifactIdFinal(Dependency dependency, Properties properties, Model model) {
        String dependencyArtifactId = dependency.getArtifactId();
        int dollarSymbolCount = 0;
        if(dependencyArtifactId!= null) dollarSymbolCount = getSpecialSymbolCount(dependencyArtifactId);
        if(dependencyArtifactId != null && dependencyArtifactId.contains("$") && !dependencyArtifactId.contains("project.ArtifactId") && dollarSymbolCount > 1) {
            dependencyArtifactId =  getCorrectDependencyData(dependencyArtifactId, properties);
        }
        else if(dependencyArtifactId != null && !dependencyArtifactId.contains("project.ArtifactId") && dollarSymbolCount == 1) {
            dependencyArtifactId = dependencyArtifactId.replace("$", "")
                    .replace("{", "")
                    .replace("}", "");
            dependencyArtifactId = properties.getProperty(dependencyArtifactId);
        }
        else if(dependencyArtifactId != null && dependencyArtifactId.contains("project.ArtifactId")) {
            dependencyArtifactId = model.getArtifactId();
            if(dependencyArtifactId == null) {
                if(model.getParent() != null) {
                    dependencyArtifactId = model.getParent().getArtifactId();
                }
            }
        }

        return dependencyArtifactId;
    }

    private static String getDependencyGroupIdFinal(Dependency dependency, Properties properties, Model model) {
        String dependencyGroupId = dependency.getGroupId();
        int dollarSymbolCount = 0;
        if(dependencyGroupId!= null) dollarSymbolCount = getSpecialSymbolCount(dependencyGroupId);
        if(dependencyGroupId != null && dependencyGroupId.contains("$") && !dependencyGroupId.contains("project.groupId") && dollarSymbolCount > 1) {
            dependencyGroupId =  getCorrectDependencyData(dependencyGroupId, properties);
        }
        else if(dependencyGroupId != null && !dependencyGroupId.contains("project.groupId") && dollarSymbolCount == 1) {
            dependencyGroupId = dependencyGroupId.replace("$", "")
                    .replace("{", "")
                    .replace("}", "");
            dependencyGroupId = properties.getProperty(dependencyGroupId);
        }
        else if(dependencyGroupId != null && dependencyGroupId.contains("project.groupId")) {
            dependencyGroupId = model.getGroupId();
            if(dependencyGroupId == null) {
                if(model.getParent() != null) {
                    dependencyGroupId = model.getParent().getGroupId();
                }
            }
        }
        return dependencyGroupId;
    }

    public static String getDependencyVersion(Dependency dependency, Properties properties, Model model) {
        String dependencyVersion = dependency.getVersion();
        dependencyVersion = getDependentArtifactVersion(dependencyVersion, properties);
        if(dependencyVersion != null && dependencyVersion.contains("$") && !dependencyVersion.contains("project.version") && getSpecialSymbolCount(dependencyVersion) > 1) {
            dependencyVersion = getCorrectDependencyData(dependencyVersion, properties);
        }
        else if(dependencyVersion != null && dependencyVersion.contains("project.version")) {
            dependencyVersion = model.getVersion();
            if(dependencyVersion == null) {
                if(model.getParent() != null) {
                    dependencyVersion = model.getParent().getVersion();
                }
            }
        }
        return dependencyVersion;
    }

    public static Properties parsePomProperties(Properties pomProperties) {
        Properties allProperties = new Properties();
        Hashtable<String, String> properties = (Hashtable)pomProperties;
        for(Map.Entry<String, String> propertyEntry : properties.entrySet()) {
            String propertyValue = propertyEntry.getValue();
            allProperties.setProperty(propertyEntry.getKey(), propertyValue);
        }
        return allProperties;
    }

    private static String getCorrectDependencyData(String dependencyVersion, Properties properties) {
        String[] versions = dependencyVersion.replace(".$", ",$").split("\\,");
        StringBuilder correctDependencyVersion = new StringBuilder();
        for(String eachDependency : versions) {
            correctDependencyVersion.append(getDependentArtifactVersion(eachDependency, properties))
                    .append(".");
        }
        correctDependencyVersion.deleteCharAt(correctDependencyVersion.length()-1);
        return correctDependencyVersion.toString();
    }

    private static String getDependentArtifactVersion(String dependencyVersion, Properties properties) {
        if (dependencyVersion != null && dependencyVersion.contains("$") && getSpecialSymbolCount(dependencyVersion) < 2) {
            dependencyVersion = dependencyVersion.replace("$", "")
                    .replace("{", "")
                    .replace("}", "");
            if (properties != null) {
                dependencyVersion = properties.getProperty(dependencyVersion);
            }
        }
        return dependencyVersion;
    }

    public static int getSpecialSymbolCount(String dependencyVersion) {
        int count = 0;
        for(int i = 0; i < dependencyVersion.length(); i++) {
            if(dependencyVersion.charAt(i) == '$') {
                count++;
            }
            if(count > 1) return count;
        }
        return count;
    }
}
