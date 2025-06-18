package TestPackage;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class JsonUser_localTest {
	
	@Test
	public void testJsonUser() {
		
		baseURI = "http://localhost:3000";
		
		JSONObject request = new JSONObject();
		request.put("firstName", "firstNameLedu");
		request.put("lastName", "lastNameLedu");
		request.put("subjectId", "subjectIdLedu");
		
		given().
				contentType("application/json")
				.body(request.toJSONString())
			.when()
				.post("/users")
			.then()
				.statusCode(201)
				.log().all();
		System.out.println("Running JsonUser_localTest");
	}

}
