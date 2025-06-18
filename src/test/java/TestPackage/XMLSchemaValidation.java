package TestPackage;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import static org.hamcrest.Matchers.equalTo;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {

	@Test
	public void validateXMLSchema() throws Exception {

		File file = new File(
				"C:\\Users\\Sandeep.Usakoyala\\eclipse-workspace\\TestRestAssured\\src\\test\\resources\\add.xml");

		// Ensure the file exists
		if (!file.exists()) {
			throw new RuntimeException("File not found: " + file.getAbsolutePath());
		}

		FileInputStream fis = new FileInputStream(file);

		String ReqBody = IOUtils.toString(fis, "UTF-8");

		baseURI = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService";

		given().header("Content-Type", "text/xml; charset=utf-8").body(ReqBody).when().post("Calc.asmx").then()
				.statusCode(200).log().all().and().body("//*AddResult.text()", equalTo("7")).and().assertThat()
				.body(RestAssuredMatchers.matchesXsdInClasspath("Response.xsd"));
	}
	

}

	
