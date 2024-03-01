package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.testng.annotations.Test;

@Getter
@Setter
public class CreateAccountTest {

    @Test
    public String testMethod(){
        //client definition
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        //request definition
        JSONObject createUserBody = new JSONObject();
        createUserBody.put("userName", "TestName4");
        createUserBody.put("password","1234.Password!");
        requestSpecification.body(createUserBody.toString());

        //response creation
        Response response = requestSpecification.post("Account/v1/User");
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        ResponseBody responseBody = response.getBody();
        System.out.println(responseBody.asPrettyString());

        // userID from the response JSON extraction

        String userID = response.jsonPath().getString("userID");


        System.out.println("User ID is saved: " + userID);
        return userID;
    }


}
