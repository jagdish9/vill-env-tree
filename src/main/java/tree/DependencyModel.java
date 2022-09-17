package tree;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class DependencyModel {

    @JsonAnySetter
    private Map<String, Object> dependency = new LinkedHashMap<>();

    public Map<String, Object> getDependency() {
        return dependency;
    }

    public void setDependency(Map<String, Object> dependency) {
        this.dependency = dependency;
    }
}
