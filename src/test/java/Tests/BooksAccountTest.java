package Tests;

import Actions.AccountActions;
import Actions.BookStoreActions;
import ObjectData.RequestObject.RequestAccount;
import ObjectData.RequestObject.RequestAccountBooks;
import ObjectData.ResponseObject.ResponseAccountSuccess;
import ObjectData.ResponseObject.ResponseTokenSuccess;
import PropertyUtility.PropertyUtility;
import extentUtility.ExtentUtility;
import extentUtility.ReportStep;
import hooks.Hooks;
import org.testng.annotations.Test;

import java.util.HashMap;

public class BooksAccountTest extends Hooks {
    public String userId;
    public String token;
    public RequestAccount requestAccountBody;
    public AccountActions accountActions;
    public BookStoreActions bookStoreActions;
    public RequestAccountBooks requestAccountBooks;

    @Test
    public void method() {
        System.out.println("Step 1: Create new account");
        createAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user creates a new account with success");

        System.out.println("\nStep 2: Generate new token");
        generateToken();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user generates a token for new account with success");

        System.out.println("\nStep 3: Get new account");
        getSpecificAccount();
        ExtentUtility.attachReportLog(ReportStep.PASS_STEP, "The user validates the presence of new account with success");

        System.out.println("\nStep 4: Add books to account");
        addBooksToAccount();
    }

    public void createAccount() {
        accountActions = new AccountActions();
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/createAccountData");
        requestAccountBody = new RequestAccount(propertyUtility.getAllData());
        ResponseAccountSuccess responseAccountBody = accountActions.createNewAccount(requestAccountBody);
        userId = responseAccountBody.getUserID();
    }

    public void generateToken() {
        ResponseTokenSuccess responseTokenSuccess = accountActions.generateToken(requestAccountBody);
        token = responseTokenSuccess.getToken();
    }

    public void getSpecificAccount() {
        accountActions.getSpecificAccount(token, userId, requestAccountBody);
    }

    public void addBooksToAccount() {
        PropertyUtility propertyUtility = new PropertyUtility("RequestData/booksAccountData");
        HashMap<String, String> testData = propertyUtility.getAllData();
        testData.put("userId", userId);
        requestAccountBooks = new RequestAccountBooks(testData);

        bookStoreActions = new BookStoreActions();
        bookStoreActions.addBooksToAccount(token, requestAccountBooks);
    }
}
