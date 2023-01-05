package RestAssures.API1;

import io.restassured.http.ContentType;
import lombok.Data;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndIdTest() {

        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));// проверяем что URL аватара содержит id пользователя
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));//проверяем что email закагчивается на reqres.in

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    @Test
    public void checkAvatarAndIdTestAdvanced() {

        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());

        List<UserData> users2 = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users2.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));// проверяем что URL аватара содержит id пользователя
        Assert.assertTrue(users2.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));//проверяем что email закагчивается на reqres.in

        List<String> avatars2 = users2.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids2 = users2.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars2.size(); i++) {
            Assert.assertTrue(avatars2.get(i).contains(ids2.get(i)));
        }
    }

    @Test
    public void successRegTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());

        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);

        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());

        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());

    }

    @Test
    public void unSuccessRegTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecError400());

        Register user = new Register("sydney@fife", "");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);

        Assert.assertEquals(unSuccessReg.getError(), "Missing password");
    }

    @Test
    public void sortedYearsTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());

        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);

        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);

    }

    @Test
    public void deleteUserTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecUnique(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();


    }

    @Test
    public void timeTest() {
        Specification.installSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);

        String regex = "(.{11})$";
        String regexResp = "(.{5})$";

        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");
        System.out.println(currentTime);
        System.out.println(response.getUpdatedAt().replaceAll(regexResp, ""));
        Assert.assertEquals(currentTime, response.getUpdatedAt().replaceAll(regexResp, ""));
    }

}
