package helper.user;

import helper.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserClient extends RestClient {
    private static final String USER_PATH = "api/auth/user/";
    private static final String CREATE_USER_PATH = "api/auth/register/";
    private static final String LOGIN_USER_PATH = "api/auth/login/";
    private static final String DELETE_USER_PATH = "api/auth/user/";


    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(CREATE_USER_PATH)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .spec(getBaseSpec())
                .body(accessToken)
                .when()
                .post(DELETE_USER_PATH)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse loginUser(String email, String password) {
        UserCredential userCredentials = new UserCredential(email, password);
        return given()
                .spec(getBaseSpec())
                .body(userCredentials)
                .when()
                .post(LOGIN_USER_PATH)
                .then();
    }

    @Step("Получение информации о пользователе")
    public ValidatableResponse getUser(User user) {
        return given()
                .spec(getBaseSpec())
                .when()
                .post(USER_PATH)
                .then();
    }

}
