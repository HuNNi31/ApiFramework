
package Tests;

import ObjectData.RequestObject.RequestAccount;
import ObjectData.ResponseObject.ResponseAccountGetSuccess;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.ResponseTokenSuccess;
import PropertyUtility.PropertyUtility;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTest {

    public String userId;
    public String token;
    public RequestAccount requestAccountBody;

    @Test
    public void testMethod(){
        System.out.println("Step 1: create new account");
        createAccount();
        System.out.println();

        System.out.println("Step 2: generate new token");
        generateToken();
        System.out.println();

        System.out.println("Step 3: get new account");
        getSpecificAccount();
    }

    public void createAccount(){
        //define the client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        //define the request
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/createAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());
        requestSpecification.body(requestAccountBody);

        //interaction with the response
        Response response = requestSpecification.post("Account/v1/User");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),201);
        System.out.println(response.getStatusLine());

        //validate response
        ResponseAccountSuccess responseAccountBody = response.body().as(ResponseAccountSuccess.class);
        System.out.println(responseAccountBody.getUserID());
        userId = responseAccountBody.getUserID();
        Assert.assertEquals(responseAccountBody.getUsername(),requestAccountBody.getUserName());
    }

    public void generateToken(){
        //define the client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");

        requestSpecification.body(requestAccountBody);

        //interaction with the response
        Response response = requestSpecification.post("Account/v1/GenerateToken");
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getStatusLine());

        //validate response
        ResponseTokenSuccess responseTokenSuccess = response.body().as(ResponseTokenSuccess.class);
        System.out.println(responseTokenSuccess.getToken());
        token = responseTokenSuccess.getToken();

        Assert.assertEquals(responseTokenSuccess.getStatus(),"Success");
        Assert.assertEquals(responseTokenSuccess.getResult(),"User authorized successfully.");
    }

    public void getSpecificAccount(){

        //define the client
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://demoqa.com/");
        requestSpecification.contentType("application/json");
        requestSpecification.header("Authorization", "Bearer " + token);

        //interaction with the response
        Response response = requestSpecification.get("Account/v1/User/" + userId);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getStatusLine());

        //validate response
        ResponseAccountGetSuccess responseAccountGetSuccess = response.body().as(ResponseAccountGetSuccess.class);
        Assert.assertEquals(responseAccountGetSuccess.getUsername(),requestAccountBody.getUserName());
    }
}
