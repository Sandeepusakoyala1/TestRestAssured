package TestPackage;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

public class JsonSchemaValidator {

	@Test
	public void getExample() {	
		baseURI = "https://reqres.in/api";
		given().when().get("/users").then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"))
				.statusCode(200);
	}
}
