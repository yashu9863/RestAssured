import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

public class basics {
	
	@Test
	public void Test() {
		// TODO Auto-generated method stub

		// BaseURL or HOST

		RestAssured.baseURI = "https://maps.googleapis.com";

		given().param("location", "-33.8670522,151.1957362").param("radius", "500")
				.param("key", "AIzaSyBy0WRs_Jor-8lMdIbVer_vQDrE_v2Oq78").

				when().get("/maps/api/place/nearbysearch/json").

				then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.and().body("results[0].name",equalTo("Sydney")).and().
				body("results[0].place_id",equalTo("ChIJyWEHuEmuEmsRm9hTkapTCrk")).and()
				.header("Server", "pablo");
	}

}
