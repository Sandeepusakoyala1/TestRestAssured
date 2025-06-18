package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class Test_One {

	@Test
	public void testMethod() {

		Response response = get("https://reqres.in/api/users?page=2");

		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200, "Status code is not 200");
		
	}
	
	@Test
	public void testMethod2() {
		
		baseURI = "https://reqres.in/api";
		
		given()
        .when()
            .get("/users?page=2")
        .then()
            .statusCode(200)
            .body("data[1].id", equalTo(8))
            .log().all();
	}

}
