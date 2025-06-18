package TestPackage;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class RegistrationXML{

	@Test
    public void RegistrationXml() throws Exception {
        // Read the XML request file
        File regfile = new File("C:\\Users\\Sandeep.Usakoyala\\eclipse-workspace\\TestRestAssured\\src\\test\\resources\\Registration.xml");
        if (!regfile.exists()) {
            throw new RuntimeException("File not found: " + regfile.getAbsolutePath());
        }
        FileInputStream fileIn = new FileInputStream(regfile);
        String regReqBody = IOUtils.toString(fileIn, StandardCharsets.UTF_8);

        // Set the base URI
        baseURI = "http://10.1.180.85/services/";

        // Send the SOAP request and capture response
        Response response = given()
            .header("Content-Type", "text/xml; charset=utf-8")
            .body(regReqBody)
            .when()
            .post("UserProfileBwinService?wsdl")
            .then()
            .log().all()
            .extract()
            .response();

        // Extract response as String
        String responseString = response.asString();

        // Parse XML response
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // Important for SOAP namespaces
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new java.io.ByteArrayInputStream(responseString.getBytes()));

        // Extract the <ax232:registered> element
        NodeList nodeList = doc.getElementsByTagNameNS("*", "registered");
        if (nodeList.getLength() > 0) {
            String registeredValue = nodeList.item(0).getTextContent();
            System.out.println("Registered: " + registeredValue);
            
            // Assert or check if it's true
            if ("true".equalsIgnoreCase(registeredValue)) {
                System.out.println("Registration Successful");
            } else {
                throw new RuntimeException("Registration Failed: registered=" + registeredValue);
            }
        } else {
            throw new RuntimeException("<registered> element not found in response");
        }
    }

   
}
