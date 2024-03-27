
package Tests;

import Actions.AccountActions;
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
    public AccountActions accountActions;

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
        System.out.println();

        System.out.println("Step 4: deleted new account");
        deleteSpecificAccount();
        System.out.println();

        System.out.println("Step 5: re-check deleted account");
        getSpecificAccount();
        System.out.println();
    }

    public void createAccount(){

        accountActions = new AccountActions();
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/createAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());
        ResponseAccountSuccess responseAccountBody = accountActions.createnewAccount(requestAccountBody);
        userId = responseAccountBody.getUserID();

    }

    public void generateToken(){

        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestAccountBody);
        token = responseTokenSuccess.getToken();

    }

    public void getSpecificAccount(){

        accountActions.getSpecificAccount(token, userId, requestAccountBody);

    }

    public void deleteSpecificAccount(){
        accountActions.deleteSpecificAccount(token, userId);
    }
}
