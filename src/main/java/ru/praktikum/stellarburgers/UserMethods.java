package ru.praktikum.stellarburgers;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserMethods extends ConstantsData {

    @Step("Создание пользователя")
    public Response create(User user) {
        return given()
                .baseUri(URL_MAIN_PAGE)
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register");
    }

    @Step("Получение токена пользователя")
    public Response login(UserLogin userLogin) {
        return given()
                .baseUri(URL_MAIN_PAGE)
                .header("Content-type", "application/json")
                .body(userLogin)
                .post("/api/auth/login");
    }

    @Step("Удаление пользователя по токену")
    public Response delete(String accessToken) {
        return given()
                .baseUri(URL_MAIN_PAGE)
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }
}
