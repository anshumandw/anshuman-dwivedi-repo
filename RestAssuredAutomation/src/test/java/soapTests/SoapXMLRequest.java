package soapTests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SoapXMLRequest {
	
	@Test
	public void soapXMLtest() throws IOException {
		
		File file = new File("./SoapRequest/add.xml");
		
		if(file.exists()) {
			System.out.println("  >>  File Exists  <<  ");
		}
		
		FileInputStream fis = new FileInputStream(file);
		
		String requestBody = IOUtils.toString(fis, "UTF-8");
		
		baseURI = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService";
		
		given()
			.contentType("text/xml")
			.accept(ContentType.XML)
			.body(requestBody)
		.when()
			.post("/Calc.asmx")
		.then()
			.statusCode(200)
			.log()
			.all();
		
		Response response = post(baseURI + "/Calc.asmx");
		
		System.out.println(response.getTime());
		
		
	}
}
