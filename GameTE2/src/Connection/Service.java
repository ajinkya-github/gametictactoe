/**
 * 
 */
package Connection;

/**
 * @author ajinkyapatil
 *
 */


import java.sql.SQLException;

import javax.jws.WebService;

@WebService
public class Service {
	
DatabaseConnection db=new DatabaseConnection();
	
	public String signUp(String username, String pwd)
	{
		System.out.println("Inside Signup");
		String result;
		
		/**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*/
		
		result = db.signUp(username,pwd);
		System.out.println("Return Result");
		
		return result;//this value is returned to the calling servlet
	}
	
	public String gamer(String s[],String l) throws SQLException
	{
	   	System.out.println("Inside gamer");
	   	String result;
	   	result = db.gaming(s,l);
	   	System.out.println("Return gamer result");
	   	return result;
	}

    public String findOpponent(String curr)
    {
    	System.out.println("Inside find opponent");
    	String result;
    	result = db.FindOpponent(curr);
	   	System.out.println("Return find opponent result");
    	return result;
    }

}
