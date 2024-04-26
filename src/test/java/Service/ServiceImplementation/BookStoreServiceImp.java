package Service.ServiceImplementation;

import ObjectData.RequestObject.RequestAccountBooks;
import Service.ApiService.BookstoreApiService;
import Service.InterfaceService.BookStoreServiceInterface;
import io.restassured.response.Response;

public class BookStoreServiceImpl implements BookStoreServiceInterface {

    private BookstoreApiService bookstoreApiService;

    @Override
    public Response addBooksToAccount(RequestAccountBooks body, String token) {
        bookstoreApiService = new BookstoreApiService();

        return bookstoreApiService.post(body, "/BookStore/v1/Books", token);
    }
}
