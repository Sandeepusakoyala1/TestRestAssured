package com.testAutomation.pojos;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostAPIReqUsingPojos {

    @Test
    public void postAPIReqUsingPojos() {
        // Create BookingDates object
        BookingDates bookingDates = new BookingDates("2023-10-01", "2023-10-10");

        // Create dataPojo object
        dataPojo data = new dataPojo("John", "Doe", true, bookingDates); 
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String req = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
            System.out.println(req);

            // Convert JSON string back to DataPojo object
            dataPojo bookingdetails = objectMapper.readValue(req, dataPojo.class); 

            System.out.println(bookingdetails.getFirstname());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        baseURI = "https://restful-booker.herokuapp.com";

        Response response = given()
            .log().all() 
            .contentType(ContentType.JSON)
            .body(data)
        .when()
            .post("/booking");

        response.then().log().all(); 
    }
}
