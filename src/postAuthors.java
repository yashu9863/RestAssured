import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class postAuthors {
	
	
	@Test
	public void PostTest() {
		// TODO Auto-generated method stub

		// BaseURL or HOST

		RestAssured.baseURI = "http://localhost:8080";
		
		Response res = given().		
		
		body("{\r\n" + 
				"    \"fName\": \"James\",\r\n" + 
				"    \"lName\": \"Bean1\",\r\n" + 
				"    \"age\": 45,\r\n" + 
				"    \"book\": \"j2EE\",\r\n" + 
				"    \"city\": \"Lw\"\r\n" + 
				"}").contentType(ContentType.JSON).
		when().post("/api/authors");
		
		ResponseBody body = res.getBody();
		
		System.out.println(body.asString());
//		.and().body("status", equalTo("OK")).extract().response();
		
		
		
		
		/*int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, "201");
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
	}*/
		/*.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);*/

}

}
