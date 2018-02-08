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

public class basics3 {
	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Yeshwanth\\eclipse-workspace\\DemoProject\\src\\com\\files\\envi.properties");
		prop.load(fis);
		// prop.get("HOST");
	}

	@Test
	public void AddandDeleteTest() {
		// TODO Auto-generated method stub

		// BaseURL or HOST

		String b = "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -33.8669710,\r\n" + "    \"lng\": 151.1958750\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Google Shoes!\",\r\n"
				+ "  \"phone_number\": \"(02) 9374 4000\",\r\n"
				+ "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\r\n"
				+ "  \"types\": [\"shoe_store\"],\r\n" + "  \"website\": \"http://www.google.com.au/\",\r\n"
				+ "  \"language\": \"en-AU\"\r\n" + "}";

		RestAssured.baseURI = prop.getProperty("HOST");

		Response res = given().queryParam("key", "AIzaSyBy0WRs_Jor-8lMdIbVer_vQDrE_v2Oq78").

				body(b).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK")).extract().response();

		String responseString = res.asString();
		System.out.println(responseString);
		JsonPath js = new JsonPath(responseString);
		String placeid = js.get("place_id");
		System.out.println(placeid);

		// Delete request

		given().queryParam("key", "AIzaSyBy0WRs_Jor-8lMdIbVer_vQDrE_v2Oq78").

				body("{\r\n" + "  \"place_id\": \"" + placeid + " \"\r\n" + "}\r\n" + "    ").when()
				.post("/maps/api/place/delete/json").then().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("status", equalTo("OK"));
	}
}
