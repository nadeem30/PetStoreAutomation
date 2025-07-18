package api.endpoints;

/*
 Swagger URI: https://petstore.swagger.io
 Base URL: https://petstore.swagger.io/v2 
  Create User(POST): https://petstore.swagger.io/v2/user
  Get User(GET): https://petstore.swagger.io/v2/user/{username}
  Update User(UPDATE): https://petstore.swagger.io/v2/user/{username}
  Delete User(DELETE): https://petstore.swagger.io/v2/user/{username}
  
 */

public class Routes {
	public static String base_url="https://petstore.swagger.io/v2";
	
	//User module
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String update_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	
	//Store module
        //Here we need to create Store module URL's
	public static String store_post_url=base_url+"/store/order";
	public static String store_get_url=base_url+"/store/order/{orderId}";
	public static String store_delete_url=base_url+"/store/order/{orderId}";
	
	//Pet module
    //Here we need to create Pet module URL's
	
	
	

}
