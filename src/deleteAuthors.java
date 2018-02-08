import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class deleteAuthors {

	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Yeshwanth\\eclipse-workspace\\DemoProject\\src\\com\\files\\envi.properties");
		prop.load(fis);	
	}
	
	
	@Test
	public void AddandDeleteTest() {
		// TODO Auto-generated method stub

		// BaseURL or HOST
		
		String b = "{\r\n" + 
				"	\r\n" + 
				"    \"fName\": \"Alex\",\r\n" + 
				"    \"lName\": \"Bean\",\r\n" + 
				"    \"age\": 99,\r\n" + 
				"    \"book\": \"j2EE\",\r\n" + 
				"    \"city\": \"Lw\"\r\n" + 
				"}";

		RestAssured.baseURI = prop.getProperty("HOST_1");
		
		Response res = given().
	
	body(b).contentType(ContentType.JSON).when().post("/api/authors");
		
		ResponseBody body = res.getBody();
		
		//System.out.println(body.asString());
		
	
			
//	.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
//	body("status", equalTo("OK")).extract().response();
		
		String responseString = body.asString();
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		String id = js.get("id").toString();
		System.out.println(id);
		
		String path = "/api/authors/{id}";
		System.out.println(path);
		
		//Delete request
		
		given().pathParam("slotID", id).
		
		when().delete("/api/authors/{slotID}").then().assertThat().statusCode(200);

}
}