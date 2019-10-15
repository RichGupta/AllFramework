package RestAPI;

import java.util.HashMap;
import java.util.Map;

public class RestParameters {

    private Map<String,String> params = new HashMap<String,String>();

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void addParam(String key,String value){
        params.put(key,value);
    }

    public String getParam(String key){
        return params.get(key);
    }
}
