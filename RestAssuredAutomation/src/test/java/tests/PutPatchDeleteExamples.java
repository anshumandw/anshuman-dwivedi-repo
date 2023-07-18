package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.with;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutPatchDeleteExamples {
	
	Response response;
	
	@Test
	public void testPut() {
		
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
			.put("/users/2")
		.then()
			.statusCode(200)
			.log()
			.everything();
			
			response = put("https://reqres.in/api/users/2");
			System.out.println(response.getTime());
		
	}
	
	@Test
	public void testPatch() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "rupesh");
		request.put("job", "engineer");
		
		baseURI = "https://reqres.in/api";
		
		when()
			.patch("/users/2")
		.then()
			.statusCode(200)
			.log()
			.everything();
			
			response = patch("https://reqres.in/api/users/2");
			System.out.println(response.getTime());
		
	}
	
	@Test
	public void testDelete() {
		
		baseURI = "https://reqres.in/api";
		
		when()
			.delete("/users/2")
		.then()
			.statusCode(204)
			.log()
			.all();
			
			response = delete("https://reqres.in/api/users/2");
			System.out.println(response.getTime());
		
	}
	
}
