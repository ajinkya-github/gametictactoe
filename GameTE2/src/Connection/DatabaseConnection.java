/**
 * 
 */
package Connection;

/**
 * @author ajinkyapatil
 *
 */

import java.sql.*;
import java.util.Arrays;

public class DatabaseConnection {
	
	Connection con = null;
	static ResultSet rs;
    Statement stmt = null;
	
	DatabaseConnection(){		
		try {			
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameec","root","");
				stmt = con.createStatement();
				if(!con.isClosed())
					System.out.println("Db Connected!!!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("The error is here");
			}
	}

	public String signUp(String userName, String pwd){
		String result = "";
		String user1 = "";
		int gameid = 0;
		int gamerid=0;
		int rowcount;
		try {

			String sql1 = "SELECT * FROM tblgame WHERE User1!='' and User2 IS NULL  LIMIT 0,1 ";
			ResultSet rs = stmt.executeQuery(sql1);
			int rowcnt = rs.getRow();
			System.out.println("Counts:"+rowcnt);
			while(rs.next())
			{	
				user1 = rs.getString("User1");
				gameid = Integer.parseInt(rs.getString("GameID"));
				//System.out.println(lastName + "\n");
				System.out.println("GameID :"+gameid);
			}
			rs.close();
			int turn=0;
			if(gameid!=0)
			{
				turn=2;
			}
			else
			{
				turn=1;
			}
			
			String query2 = "INSERT INTO tblgamers (GamerName,Turn) VALUES ('"+userName+"','"+turn+"')";
			stmt.executeUpdate(query2, stmt.RETURN_GENERATED_KEYS); 
			ResultSet rse = stmt.getGeneratedKeys();
			rse.next();
			gamerid = rse.getInt(1);
			rse.close();
			if(gameid!=0)
				{
					String query = "UPDATE tblgame SET  User2='" + gamerid + "' WHERE GameID='"+gameid+"'";
					rowcount=stmt.executeUpdate(query);
					if(rowcount > 0){
						result="true:"+gamerid+":"+gameid;
						System.out.println("Updated");
					}
					else{
						result="false: The data could not be inserted in the database.";
					}
				}
				else
				{
					String query = "Insert into tblgame (User1) values ('" + gamerid + "')";
					rowcount=stmt.executeUpdate(query);
					stmt.executeUpdate(query2, stmt.RETURN_GENERATED_KEYS); 
					ResultSet rs2 = stmt.getGeneratedKeys();
					rs2.next();
					rs2.close();
					String sql4 = "SELECT * FROM tblgame WHERE User1='"+gamerid+"' LIMIT 0,1";
					System.out.println(sql4);
					ResultSet rs4 = stmt.executeQuery(sql4);

					while(rs4.next())
					{	
						
						gameid = Integer.parseInt(rs4.getString("GameID"));
						
					}
					rs4.close();
					
					for(int j=1;j<10;j++)
					{
						String query3 = "Insert into tblgamematrix (GameID,Matrice) values ('" + gameid + "','" + j + "')";
						System.out.println(query3);
						rowcount=stmt.executeUpdate(query3);						
					}
					
					if(rowcount > 0){
						result="true:"+gamerid+":"+gameid;
						System.out.println("Insert Successful"+result);
					}
					else{
						result="false: The data could not be inserted in the database.";
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String gaming(String s[],String l) throws SQLException
	{
		
		String result="";
		System.out.println(Arrays.asList(s));
		String matrix = "";
		String user1 = "";
		String user2 = "";
		String gameid = "";
		String isover = "";
		String winner = "";
		int own1 = 0;
		int own2 = 0;
		int own3 = 0;
		int own4 = 0;
		
		if(!s[0].equals("-")){matrix="1";}
		if(!s[1].equals("-")){matrix="2";}
		if(!s[2].equals("-")){matrix="3";}
		if(!s[3].equals("-")){matrix="4";}
		if(!s[4].equals("-")){matrix="5";}
		if(!s[5].equals("-")){matrix="6";}
		if(!s[6].equals("-")){matrix="7";}
		if(!s[7].equals("-")){matrix="8";}
		if(!s[8].equals("-")){matrix="9";}
		//check whose turn
		
		String sql = "SELECT * FROM tblgame WHERE User1='"+l+"' OR User2='"+l+"'  LIMIT 0,1 ";
		ResultSet rs8 = stmt.executeQuery(sql);
		while(rs8.next())
		{	
			user1 = rs8.getString("User1");
			user2 = rs8.getString("User2");
			gameid = rs8.getString("GameID");
			isover = rs8.getString("IsOver");
			winner = rs8.getString("Winner");
		}
		rs8.close();
		
		if(isover.equals("0"))
		{
		String sql2 = "SELECT count(*) AS OW FROM tblgamematrix WHERE GameID='"+gameid+"' AND Owner='"+user1+"'";
		ResultSet rs2 = stmt.executeQuery(sql2);
		while(rs2.next())
		{	
			own1 = Integer.parseInt(rs2.getString("OW"));
		}
		rs2.close();
		String sql3 = "SELECT count(*) AS OW FROM tblgamematrix WHERE GameID='"+gameid+"' AND Owner='"+user2+"'";
		ResultSet rs3 = stmt.executeQuery(sql3);
		while(rs3.next())
		{	
			own2 = Integer.parseInt(rs3.getString("OW"));
		}		
		rs3.close();
		System.out.println("sql3");
		if(own1>own2)
		{
			   if(user2.equals(l))
			   {
				   String sql4 = "SELECT count(*) AS OW FROM tblgamematrix WHERE GameID='"+gameid+"' AND Matrice='"+matrix+"' AND OWNER!=''";
					ResultSet rs4 = stmt.executeQuery(sql4);
					System.out.println("sql4:"+sql4);
					while(rs4.next())
					{	
						own1 = Integer.parseInt(rs4.getString("OW"));
						
					}	
					rs4.close();
					if(own1>0)
					{
						result = "This box is already played. Choose some another box.";
					}
					else
					{
						String sql5 = "UPDATE tblgamematrix SET Owner='"+l+"' WHERE GameID='"+gameid+"' AND Matrice='"+matrix+"'";
						stmt.executeUpdate(sql5);
						System.out.println("sql5");
						
						String sql10 = "SELECT * FROM tblgamematrix WHERE GameID='"+gameid+"' AND Owner='"+l+"'";
						ResultSet rs10 = stmt.executeQuery(sql10);
						String combo = "";
						while(rs10.next())
						{	
							combo += rs10.getString("Matrice");
						}
						rs10.close();
						System.out.println("Combo:"+combo);
						if(combo.equals("123") || combo.equals("456") || combo.equals("789") || combo.equals("147") || combo.equals("258") || combo.equals("369") || combo.equals("159") || combo.equals("357")       )
						{
							String sql12="UPDATE tblgame SET IsOver='1',Winner='"+l+"' WHERE GameID='"+gameid+"'";
							stmt.executeUpdate(sql12);
							result = "Congratulations! You won!";
							System.out.println("Winner");
						}
						//check to see if game is draw and no options are left
						String sql14 = "SELECT COUNT(*) AS OPT FROM tblgamematrix WHERE  GameID='"+gameid+"' AND (Owner='' OR Owner IS NULL)";
						ResultSet rs14 = stmt.executeQuery(sql14);
						while(rs14.next())
						{
							String options =rs14.getString("OPT");
							System.out.println("options: "+options);
							if(options.equals("0"))
							{
								String sql15="UPDATE tblgame SET IsOver='1',Winner='0' WHERE GameID='"+gameid+"'";
								stmt.executeUpdate(sql15);
								result = "Game is draw";
							}
						}
						rs14.close();
					}
			   
			   }
			   else
			   {
				  // it is not user1's turn
				   result = "It is not your turn.";
			   }
			
		}
		else
		{ //turn of user1
		   if(user1.equals(l))
		   {
			   String sql7 = "SELECT count(*) AS OW FROM tblgamematrix WHERE GameID='"+gameid+"' AND Matrice='"+matrix+"' AND OWNER!=''";
				ResultSet rs7 = stmt.executeQuery(sql7);
				System.out.println("sql7");
				while(rs7.next())
				{	
					own1 = Integer.parseInt(rs7.getString("OW"));
					
				}			   
		        rs7.close();
		        if(own1>0)
				{
					result = "This box is already played. Choose some another box.";
				}
				else
				{
					System.out.println("sql9");
					String sql9 = "UPDATE tblgamematrix SET Owner='"+l+"' WHERE GameID='"+gameid+"' AND Matrice='"+matrix+"'";
					stmt.executeUpdate(sql9);
					System.out.println(sql9);
					
					//check to see if user won
					System.out.println("sql11");
					String sql11 = "SELECT * FROM tblgamematrix WHERE GameID='"+gameid+"' AND Owner='"+l+"'";
					ResultSet rs11 = stmt.executeQuery(sql11);
					String combo = "";
					while(rs11.next())
					{	
						combo += rs11.getString("Matrice");
					}
					rs11.close();
					System.out.println("Combo:"+combo);
					System.out.println("sql13");
					if( (combo.indexOf("1")!=-1 && combo.indexOf("2")!=-1 && combo.indexOf("3")!=-1) || 
						(combo.indexOf("4")!=-1 && combo.indexOf("5")!=-1 && combo.indexOf("6")!=-1) || 
						(combo.indexOf("7")!=-1 && combo.indexOf("8")!=-1 && combo.indexOf("9")!=-1) || 
						(combo.indexOf("1")!=-1 && combo.indexOf("4")!=-1 && combo.indexOf("7")!=-1) || 
						(combo.indexOf("2")!=-1 && combo.indexOf("5")!=-1 && combo.indexOf("8")!=-1) || 
						(combo.indexOf("3")!=-1 && combo.indexOf("6")!=-1 && combo.indexOf("9")!=-1) || 
						(combo.indexOf("1")!=-1 && combo.indexOf("5")!=-1 && combo.indexOf("9")!=-1) || 
						(combo.indexOf("3")!=-1 && combo.indexOf("5")!=-1 && combo.indexOf("7")!=-1)  
					  )
					{
						String sql13="UPDATE tblgame SET IsOver='1',Winner='"+l+"' WHERE GameID='"+gameid+"'";
						stmt.executeUpdate(sql13);
						result = "Congratulations! You won!";
						System.out.println("Winner");
					}
					
					//check to see if game is draw and no options are left
					System.out.println("sql17");
					String sql17 = "SELECT COUNT(*) AS OPT FROM tblgamematrix WHERE  GameID='"+gameid+"' AND (Owner='' OR Owner IS NULL) LIMIT 0,1";
					ResultSet rs17 = stmt.executeQuery(sql17);
					rs17.next();
					
						String option = rs17.getString("OPT");
						System.out.println("option: "+option);
						if(option.equals("0"))
						{
							System.out.println("sql18");
							String sql18="UPDATE tblgame SET IsOver='1',Winner='0' WHERE GameID='"+gameid+"'";
							stmt.executeUpdate(sql18);
							System.out.println(sql18);
							result = "Game is draw";
						}
					
					
					
				}
		   }
		   else
		   {
			  // it is not user2's turn
			   result = "Please wait for the other player to move";
		   }
		}
		
	   }
		else
		{	
			if(winner.equals(l))
			{
				result = "Congratulations! You win!";
			}
			else if(winner.equals("0"))
			{
				result = "Game is draw";
			}
			else
			{
				result = "You Lose!";
			}
		}
		System.out.println("Returning of servlet of gaming");
		//if turn true insert
		return result;
	}
	
	public String FindOpponent(String curr)
	{
		String result = "";
		String user1 = "";
		String user2 = "";
		String gameid = "";
		int gamerid=0;
		int rowcount;
		try {
            
			String sql1 = "SELECT * FROM tblgame WHERE User1='"+curr+"' OR User2='"+curr+"'  LIMIT 0,1 ";
			ResultSet rs = stmt.executeQuery(sql1);
			int rowcnt = rs.getRow();
			
			System.out.println("sql1"+sql1);
			
			
			while(rs.next())
			{	
				user1 = rs.getString("User1");
				user2 = rs.getString("User2");
				gameid = rs.getString("GameID");
			}
			rs.close();
			
			System.out.println(user1+" vs. "+user2);
			
			if(user1.equals(curr))
			{
				if(user2==null || user2=="null" || user2.equals("null"))
				{
					System.out.println("null conition");
					result="No user found (Wait for some time)";
				}
				else
				{	
					System.out.println("user1");
					String query2 = "SELECT * FROM tblgamers WHERE GamerID='"+user2+"'";
					ResultSet rs2 = stmt.executeQuery(query2);
					int rowcnt2 = rs2.getRow();
					//System.out.println("Counts:"+rowcnt2);
					while(rs2.next())
					{	
							result = rs2.getString("GamerName");
					}
					rs2.close();
				}
				
			}
			
			if(user2!=null && user2!="null" && !user2.equals("null") && user2.equals(curr) )
			{
				//System.out.println("user2");
				String query2 = "SELECT * FROM tblgamers WHERE GamerID='"+user1+"'";
				ResultSet rs2 = stmt.executeQuery(query2);
				System.out.println("query2:"+query2);
				//System.out.println(query2);
				int rowcnt2 = rs2.getRow();
				//System.out.println("Counts:"+rowcnt2);
				while(rs2.next())
				{	
						result = rs2.getString("GamerName");
				}
				rs2.close();
				if(result.equals(""))
				{
					result="No user found (Wait for some time)";
				}
			}
			
			//System.out.println("Getting matrix");
			
			String sql20 = "SELECT * FROM tblgamematrix WHERE GameID='"+gameid+"'";
			ResultSet rs20 = stmt.executeQuery(sql20);
			String combo = "";
			String use = "";
			System.out.println("sql20:"+sql20);
			while(rs20.next())
			{	
				System.out.println("owns:"+rs20.getString("Owner"));
				if(rs20.getString("Owner")!=null)
				{	
					if(rs20.getString("Owner").equals(user1))
					{
						result += ":X";	
					}
					else if(rs20.getString("Owner").equals(user2))
					{
						result += ":0";	
					}
				}	
				else
				{
					result +=":A";
				}
				
				
			}

				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;		
	}

}
