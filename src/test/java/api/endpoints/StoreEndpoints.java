package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Response;

public class StoreEndpoints {
	
	public static Response createOrder(Store payload)
	{
		Response res=given()
				 .contentType(ContentType.JSON)
				 .accept(ContentType.JSON)
				 .body(payload)
				.when()
				 .post(Routes.store_post_url);
				
				return res;
				
	}
	
	public static Response getOrder(Integer orderId)
	{
		Response res=given()
				 .accept(ContentType.JSON)
				 .pathParam("orderId", orderId)
				.when()
				 .get(Routes.store_get_url);
				
				return res;
				
	}
	
	
	public static Response deleteOrder(Integer orderId)
	{
		Response res=given()
				 .accept(ContentType.JSON)
				 .pathParam("orderId", orderId)
				.when()
				 .delete(Routes.store_delete_url);
				
				return res;
				
	}


}
