package TestPackage;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class soapXMLReq {
	
	@Test
	public void validateSoapXML() throws Exception {
		
		File file = new File("C:\\Users\\Sandeep.Usakoyala\\eclipse-workspace\\TestRestAssured\\src\\test\\resources\\add.xml");
		
		// Ensure the file exists
		if (!file.exists()) {
			throw new RuntimeException("File not found: " + file.getAbsolutePath());
		}
		
		FileInputStream fis = new FileInputStream(file);
		
		String ReqBody = IOUtils.toString(fis, "UTF-8");
		
		baseURI = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService";
		
		given()
		.header("Content-Type", "text/xml; charset=utf-8")
		.body(ReqBody)
		.when()
		.post("Calc.asmx")
		.then()
		.statusCode(200).log().all();
	}
	@Test
	public void RegistrationXml () throws Exception {
		
		File regfile = new File("C:\\Users\\Sandeep.Usakoyala\\eclipse-workspace\\TestRestAssured\\src\\test\\resources\\Registration.xml");
		
		if (!regfile.exists()) {
			throw new RuntimeException("File not found: " + regfile.getAbsolutePath());
		}
		FileInputStream fileIn = new FileInputStream(regfile);
		
		String regReqBody = IOUtils.toString(fileIn, "UTF-8");
		
		baseURI = "http://10.1.180.85/services/";
		
		given()
		.header("Content-Type", "text/xml; charset=utf-8")
		.body(regReqBody)
		.when()
		.post("UserProfileBwinService?wsdl")
		.then()
		.log().all();
		}
	
}
