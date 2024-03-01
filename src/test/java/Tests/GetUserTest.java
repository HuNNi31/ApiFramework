package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class GetUserTest {

    @Test
    public void testMethod(){
        //client definition
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        CreateAccountTest createAccountTest  = new CreateAccountTest();
        String userID = createAccountTest.testMethod();
        System.out.println("given userID is:"+userID);
        //response creation
        Response response = requestSpecification.get("Account/v1/User/"+userID);
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.asPrettyString());
    }

}
