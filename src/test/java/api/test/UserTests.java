package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayoad;
	public Logger logger;
	
	@BeforeTest
	public void setup() 
	{
		faker=new Faker();
		userPayoad=new User();
		
		userPayoad.setId(faker.idNumber().hashCode());
		userPayoad.setUsername(faker.name().username());
		userPayoad.setFirstName(faker.name().firstName());
		userPayoad.setLastName(faker.name().lastName());
		userPayoad.setEmail(faker.internet().safeEmailAddress());
		userPayoad.setPassword(faker.internet().password(5, 10));
		userPayoad.setPhone(faker.phoneNumber().cellPhone());	
		logger= LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority=1)
	public void testPostUser() 
	{
		logger.info("**************CREATING USER****************");
		Response res=UserEndpoints.createUser(userPayoad);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("**************USER IS CREATED****************");
	}
	
	@Test(priority=2)
	public void testGetUser() throws InterruptedException 
	{
		logger.info("**************READING USER INFO****************");
		Response res=UserEndpoints.getUser(this.userPayoad.getUsername());
	    res.then().log().all();
	    Assert.assertEquals(res.getStatusCode(), 200);
	    Thread.sleep(5000);
	    
	    logger.info("**************USER INFO IS DISPLAYED****************");
	}
	
	
	@Test(priority=3)
	public void testUpdateUser() throws InterruptedException 
	{
		//update data using payload
		logger.info("**************UPDATING USER****************");
		userPayoad.setFirstName(faker.name().firstName());
		userPayoad.setLastName(faker.name().lastName());
		userPayoad.setEmail(faker.internet().safeEmailAddress());
		
		Response res=UserEndpoints.updateUser(userPayoad, this.userPayoad.getUsername());
		res.then().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("**************USER UPDATED****************");
		
		Response resAfterUpdate=UserEndpoints.getUser(this.userPayoad.getUsername());
		resAfterUpdate.then().log().all();
	    Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);	
	    Thread.sleep(5000);
		
	}

	
	@Test(priority=4)
	 public void testDeleteUser() throws InterruptedException
		{
		logger.info("**************DELETING USER****************");
			Response res=UserEndpoints.deleteUser(this.userPayoad.getUsername());
		    res.then().log().all();
		    Assert.assertEquals(res.getStatusCode(), 200);
		    //Thread.sleep(5000);
		    logger.info("**************USER DELETED****************");
		}
}
