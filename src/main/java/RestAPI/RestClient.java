package RestAPI;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    private static RequestSpecification requestSpecification;
    private static Response response;
    private static JsonPath jsonPath;

    public Response sendRequest(String url, Method method, RestRequest restRequestSpec){
        RestAssured.baseURI = url;
        requestSpecification = RestAssured.given();
        switch (method){
            case GET:
                if(restRequestSpec!=null){
                    requestSpecification = requestSpecification.spec(restRequestSpec.getRequestSpec());
                }
                response = requestSpecification.when().get();
                break;
            case POST:
                if(restRequestSpec!=null){
                    requestSpecification = requestSpecification.spec(restRequestSpec.getRequestSpec());
                }
                response = requestSpecification.when().post();
                break;
            case PUT:
                if(restRequestSpec!=null){
                    requestSpecification = requestSpecification.spec(restRequestSpec.getRequestSpec());
                }
                response = requestSpecification.when().put();
                break;
            case DELETE:
                if(restRequestSpec!=null){
                    requestSpecification = requestSpecification.spec(restRequestSpec.getRequestSpec());
                }
                response = requestSpecification.when().delete();
                break;
            default:
                if(restRequestSpec!=null){
                    requestSpecification = requestSpecification.spec(restRequestSpec.getRequestSpec());
                }
                response = requestSpecification.when().get();
                break;
        }
        return response;
    }

    public static void main(String[] args){
        RestClient restTest = new RestClient();
        Response response = restTest.sendRequest("http://restapi.demoqa.com/utilities/weather/city/hyderabad",Method.GET,null);
        jsonPath = response.jsonPath();
        System.out.println(jsonPath.get("City").toString());
    }
}
