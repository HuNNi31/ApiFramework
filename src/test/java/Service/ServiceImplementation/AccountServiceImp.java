package Service.ServiceImplementation;

import ObjectData.RequestObject.RequestAccount;
import Service.ApiService.AccountApiService;
import Service.InterfaceService.AccountServiceInterface;
import io.restassured.response.Response;

public class AccountServiceImp implements AccountServiceInterface {

    //facem o instanta de ApiService ca sa putem accesa metodele generale POST, GET, DELETE, PUT etc.
    private AccountApiService accountApiService;

    @Override
    public Response createAccount(RequestAccount body) {
        accountApiService = new AccountApiService();
        return accountApiService.post(body, "Account/v1/User");
    }

    @Override
    public Response generateAccountToken(RequestAccount body) {
        accountApiService = new AccountApiService();
        return  accountApiService.post(body, "Account/v1/GenerateToken");
    }

    @Override
    public Response getSpecificAccount(String token, String userId) {
        accountApiService = new AccountApiService();
        String url = "Account/v1/User/" + userId;
        return accountApiService.get(token, url);
    }

    @Override
    public Response deleteSpecificAccount(String token, String userId) {
        accountApiService = new AccountApiService();
        String url = "Account/v1/User/" + userId;
        return accountApiService.delete(token, url);
    }
}
