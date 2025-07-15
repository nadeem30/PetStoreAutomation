	package api.test;
	
	import java.util.List;
	
	import org.testng.Assert;
	import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
	
	public class DDTests {
		
		User usrPayload;
		
		@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
		public void testPostUser(String uID,String uname,String fname,String lname,String mail,String pwd, String phn) 
		{
			usrPayload=new User();
			usrPayload.setId(Integer.parseInt(uID));
			usrPayload.setUsername(uname);
			usrPayload.setFirstName(fname);
			usrPayload.setLastName(lname);
			usrPayload.setEmail(mail);
			usrPayload.setPassword(pwd);
			usrPayload.setPhone(phn);
			
			
			Response res=UserEndpoints.createUser(usrPayload);
			Assert.assertEquals(res.getStatusCode(),200); 
			
		
		} 
		
		
		
		@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
		public void testDeleteUser(String uname) throws InterruptedException
		{
			Thread.sleep(2000);
			Response res=UserEndpoints.deleteUser(uname);
			res.then().log().all();
			Assert.assertEquals(res.getStatusCode(),200); 
			
		} 
	}
