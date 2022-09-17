package tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class ParseDependencyTree {

    public static void main(String[] args) {
        Map<String, Set<String>> finalList = new HashMap<>();
      //  String sResult = readJsonFile(new File("C:\\softwares\\csv generated files\\23-November-2021\\bittytax.json"));
        String sResult = readJsonFile(new File("C:\\softwares\\csv generated files\\23-November-2021\\bit-1.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            DependencyModel dependencyModel = objectMapper.readValue(sResult, DependencyModel.class);
          //  System.out.println(dependencyModel);
            addToListFromModel(dependencyModel.getDependency(), finalList, null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

     //   System.out.println(finalList);
        for(Map.Entry<String, Set<String>> fL : finalList.entrySet()) {
            if(!fL.getValue().isEmpty()) {
                for(String eachValue : fL.getValue()) {
                    System.out.println(fL.getKey() + "-->" + eachValue);
                }
            }
        }

        if(!finalList.isEmpty()) for(Map.Entry<String, Set<String>> eachDependency : finalList.entrySet()) {
            if(finalList.size() == 1 && eachDependency.getValue().isEmpty()) {
              //  persistDependenciesToKB(new ArrayList<>(eachDependency.getValue()), eachDependency.getKey());
                System.out.println("came inside");
            }
        }
    }

    private static void addToListFromModel(Object mapObject, Map<String, Set<String>> finalList, String parent) {
        LinkedHashMap<String, Object> hashMap = (LinkedHashMap<String, Object>) mapObject;
        if(hashMap.size() > 0) {
            for(Map.Entry<String, Object> mEntry : hashMap.entrySet()) {
                LinkedHashMap<String, Object> childMap = (LinkedHashMap<String, Object>) mEntry.getValue();
                Set<String> parentSet = new HashSet<>();
                if(parent != null && finalList.containsKey(parent)){
                    parentSet = finalList.get(parent);
                    if(!parent.equals(mEntry.getKey())) {
                        parentSet.add(mEntry.getKey());
                        finalList.put(parent, parentSet);
                    }
                }
                else {
                    if(parent != null && !parent.equals(mEntry.getKey())) {
                        parentSet.add(mEntry.getKey());
                        finalList.put(parent, parentSet);
                    }
                }
                Set<String> setValue = new HashSet<>();
                for(Map.Entry<String, Object> childEntry : childMap.entrySet()) {
                    setValue.add(childEntry.getKey());
                    addToListFromModel(childEntry.getValue(), finalList, childEntry.getKey());
                }
                finalList.put(mEntry.getKey(), setValue);
            }
        }
    }

    /*private static void addToList(JsonObject jsonObject) {
        for(Map.Entry<String, JsonElement> jEntry : jsonObject.entrySet()) {
            JsonElement jsonElement = jEntry.getValue();
            Set<String> allKeys = ((JsonObject) jsonElement).keySet();
            finalList.put(jEntry.getKey(), allKeys);
        }
        System.out.println(finalList);
    }*/

    private static String readJsonFile(File inputFile){
        if (!inputFile.exists()) {
            return "json file not found";
        }
        StringBuilder sb = new StringBuilder();
        try (Reader reader = new FileReader(inputFile)) {
            BufferedReader reader1 = new BufferedReader(reader);
            String line = null;
            while ((line = reader1.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  System.out.println(sb.toString());
        return sb.toString();
    }
}
