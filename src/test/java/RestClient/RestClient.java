package RestClient;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    //layer 1 = clasa unde sunt definite configurari la nivel de client
    // am defacut doua actiuni:
    // 1. metoda care configureaza clientul
    // 2. metoda care returnaza un raspuns pe baza configurari de la client

    private RequestSpecification prepareClient(RequestSpecification requestSpecification){
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");
        return requestSpecification;
    }

    public Response performRequest(String requestType, RequestSpecification requestSpecification, String endPoint){
        switch (requestType){
            case RequestType.REQUEST_POST:
                return prepareClient(requestSpecification).post(endPoint);
            case RequestType.REQUEST_PUT:
                return prepareClient(requestSpecification).put(endPoint);
            case RequestType.REQUEST_DELETE:
                return prepareClient(requestSpecification).delete(endPoint);
            case RequestType.REQUEST_GET:
                return prepareClient(requestSpecification).get(endPoint);
        }


        return null;
    }



}
