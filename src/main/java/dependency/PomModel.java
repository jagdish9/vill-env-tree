package dependency;

import java.util.List;

public class PomModel {
    private String gavId;
    private String scope;
    private boolean optional;
    private List<String> dependencyList;

    public String getGavId() {
        return gavId;
    }

    public void setGavId(String gavId) {
        this.gavId = gavId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public List<String> getDependencyList() {
        return dependencyList;
    }

    public void setDependencyList(List<String> dependencyList) {
        this.dependencyList = dependencyList;
    }
}
