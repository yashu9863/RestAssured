import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class getAuthors {

	Properties prop = new Properties();

	@BeforeTest
	public void getData() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Yeshwanth\\eclipse-workspace\\DemoProject\\src\\com\\files\\envi.properties");
		prop.load(fis);
		// prop.get("HOST");
	}

	@Test
	public void Test() {
		// TODO Auto-generated method stub
		// BaseURL or HOST
		RestAssured.baseURI = prop.getProperty("HOST_1");
		given().when().get("/api/authors/all").then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

}
