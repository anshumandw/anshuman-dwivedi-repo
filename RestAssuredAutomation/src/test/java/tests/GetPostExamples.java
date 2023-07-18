package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetPostExamples {
	
	@Test
	public void testGet() {
		baseURI = "https://reqres.in/api";
		
		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			.body("data[4].first_name", equalTo("George"))
			.body("data.first_name", hasItems("George", "Lindsay"));
	}
	
	@Test
	public void testPost() {
		
		
//		Map<String, Object> map = new HashMap();
//		
//		map.put("name", "rupesh");
//		map.put("job", "engineer");
//		
//		JSONObject request = new JSONObject(map);
//		
//		System.out.println(request);
		
		JSONObject request = new JSONObject();
		
		request.put("name", "rupesh");
		request.put("job", "engineer");
		
		System.out.println(request.toJSONString());
		
		baseURI = "https://reqres.in/api";
		
		with()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(request.toJSONString())
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.log()
			.everything(); // all();
			
			Response response = get("https://reqres.in/api/users");
			System.out.println(response.getTime());
		
	}
	
}
