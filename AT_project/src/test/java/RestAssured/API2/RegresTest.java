package RestAssured.API2;

import RestAssured.API1.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class RegresTest {

    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarsNoPojoTest() {

        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());

        Response response = given()
                .when()
                .get(URL + "api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2))
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");

        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
        }

        Assert.assertTrue(emails.stream().allMatch(x -> x.endsWith("@reqres.in")));

        int w = 2;

    }


    @Test
    public void successUserRegNoPojoTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());

        Map<String, String> user = new HashMap<>();
        user.put("email", "eve.holt@reqres.in");
        user.put("password", "pistol");

        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        int id = jsonPath.get("id");
        String token = jsonPath.get("token");

        Assert.assertEquals(id, 4);
        Assert.assertEquals(token, "QpwL5tke4Pnpja7X4");

    }

    @Test
    public void unSuccessRegNoPojoTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecError400());
        Map<String, String> user = new HashMap<>();
        user.put("email", "sydney@fife");

        given()
                .body(user).
                when()
                .post("api/register")
                .then().log().all()
                .body("error", equalTo("Missing password"));
    }

}
