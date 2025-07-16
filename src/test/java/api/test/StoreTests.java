package api.test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndpoints;
import api.payload.Store;
import io.restassured.response.Response;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.ZoneOffset;


public class StoreTests {
	Faker faker;
	Store storePayload;
	
	@BeforeTest
	public void setup() 
	{
		faker=new Faker();
		storePayload=new Store();
		
		storePayload.setId(faker.number().numberBetween(2, 10));
		storePayload.setPetId(faker.number().numberBetween(2, 10));
		storePayload.setQuantity(faker.number().numberBetween(1, 3));
		storePayload.setShipDate(ZonedDateTime.now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_INSTANT));
		storePayload.setStatus("placed");
		storePayload.setComplete(faker.bool().bool());
		
	}
	
	@Test(priority=1)
	public void testPostStore() throws InterruptedException
	{
		Response res=StoreEndpoints.createOrder(storePayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		Thread.sleep(3000);
	}
	
	
	@Test(priority=2)
	public void testGetStore()
	{
		Response res=StoreEndpoints.getOrder(this.storePayload.getId());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
