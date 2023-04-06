package Exel_DataProvider;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExcelTest {

    @Test(dataProvider = "usersFromSheet1", dataProviderClass = ExcelDataProviders.class)
    public void test(String... params) {
        System.out.println(params[0] + " " + params[1]);
    }

    @Test(dataProvider = "usersFromSheet2", dataProviderClass = ExcelDataProviders.class)
    public void test2(String... params) {
        System.out.println(params[0] + " " + params[1] + " " + params[2]);
    }

    @Test(dataProvider = "usersForApi", dataProviderClass = ExcelDataProviders.class)
    public void testApi(String... params) {
        int id = (int) Double.parseDouble(params[0]);
        Response response = given()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users/" + id)
                        .then().log().body().extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("data.id"), id);
        Assert.assertEquals(jsonPath.getString("data.email"), params[1]);
        Assert.assertEquals(jsonPath.getString("data.first_name"), params[2]);
        Assert.assertEquals(jsonPath.getString("data.last_name"), params[3]);
        Assert.assertEquals(jsonPath.getString("data.avatar"), params[4]);
    }

}

