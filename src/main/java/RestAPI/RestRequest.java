package RestAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;

public class RestRequest {

    public static RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    Map<String, String> headers = new HashMap<String, String>();
    Map<String, String> params = new HashMap<String, String>();
    Object jsonBody;
    String entity;

    public RestRequest(RestHeaders headers) {
        this.headers = headers.getHeaders();
    }

    public RestRequest(RestParameters params) {
        this.params = params.getParams();
    }

    public RestRequest(RestHeaders headers, RestParameters params) {
        this.headers = headers.getHeaders();
        this.params = params.getParams();
    }

    public RestRequest(RestHeaders headers, RestParameters params, Object jsonBody) {
        this.headers = headers.getHeaders();
        this.params = params.getParams();
        this.jsonBody = jsonBody;
    }

    public RestRequest(RestHeaders headers, Object jsonBody) {
        this.headers = headers.getHeaders();
        this.jsonBody = jsonBody;
    }

    public RestRequest(RestParameters params, Object jsonBody) {
        this.params = params.getParams();
        this.jsonBody = jsonBody;
    }

    public RestRequest(Object jsonBody) {
        this.jsonBody = jsonBody;
    }

    public RestRequest(RestHeaders headers, String body) {
        this.headers = headers.getHeaders();
        this.entity = body;
    }

    public RestRequest(RestParameters params, String body) {
        this.params = params.getParams();
        this.entity = body;
    }

    public RestRequest(String body) {
        this.entity = body;
    }

    public RequestSpecification getRequestSpec() {
        if (!params.isEmpty()) {
            requestSpecBuilder.addParams(params);
        }
        if (!headers.isEmpty()) {
            requestSpecBuilder.addHeaders(headers);
        }
        if (null != jsonBody) {
            requestSpecBuilder.setBody(jsonBody);
            requestSpecBuilder.setContentType(ContentType.JSON);
        }
        if (null != entity) {
            requestSpecBuilder.setBody(entity);
        }
        return requestSpecBuilder.build();
    }
}
