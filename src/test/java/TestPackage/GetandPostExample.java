package TestPackage;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

public class GetandPostExample {

	@Test
	public void getExample() {
		baseURI = "https://reqres.in/api";

		given().when().get("/users?page=2").then().statusCode(200).body("data[0].first_name", equalTo("Michael"))
				.body("data.first_name", hasItems("Michael", "Lindsay", "Tobias")).log().all();
	}

	@Test
	public void postExample() {
		baseURI = "https://reqres.in/api";

		JSONObject request = new JSONObject();
		request.put("name", "sandeep");
		request.put("job", "SDET");

		given().contentType("application/json").body(request.toJSONString()).when().post("/users").then()
				.statusCode(201).log().all();

	}

}
