package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connection.ServiceProxy;

/**
 * Servlet implementation class SignUp
 */
public class Game extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ServiceProxy proxy=new ServiceProxy();  
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public Game() throws IOException {
        super();
        // TODO Auto-generated constructor stub
     }

    public String Opponent(String curr) throws RemoteException
    {
    	proxy.setEndpoint("http://localhost:8080/GameTE2/services/Service");
    	String qdone="";
    	qdone = proxy.findOpponent(curr);
    	System.out.println(qdone);
    	return qdone;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String qdone;		 
		 try{	
			    String game[] = new String [9];
			    game[0]="-";
			    game[1]="-";
			    game[2]="-";
			    game[3]="-";
			    game[4]="-";
			    game[5]="-";
			    game[6]="-";
			    game[7]="-";
			    game[8]="-";

			    if(request.getParameter("game_1") != null)
			    {	
			       game[0]= request.getParameter("game_1");
			    }
			    if(request.getParameter("game_2") != null)
			    {	
			       game[1]= request.getParameter("game_2");
			    }
			    if(request.getParameter("game_3") != null)
			    {	
			       game[2]= request.getParameter("game_3");
			    }
			    if(request.getParameter("game_4") != null)
			    {	
			       game[3]= request.getParameter("game_4");
			    }
			    if(request.getParameter("game_5") != null)
			    {	
			       game[4]= request.getParameter("game_5");
			    }
			    if(request.getParameter("game_6") != null)
			    {	
			       game[5]= request.getParameter("game_6");
			    }
			    if(request.getParameter("game_7") != null)
			    {	
			       game[6]= request.getParameter("game_7");
			    }
			    if(request.getParameter("game_8") != null)
			    {	
			       game[7]= request.getParameter("game_8");
			    }
			    if(request.getParameter("game_9") != null)
			    {	
			       game[8]= request.getParameter("game_9");
			    }
			    //System.out.println(Arrays.asList(game));
			 	proxy.setEndpoint("http://localhost:8080/GameTE2/services/Service");
				HttpSession session = request.getSession();
				String userID = (String) session.getAttribute("userID");
				qdone = proxy.gamer(game,userID);
				System.out.println(qdone);
				session.setAttribute("gameMessage",qdone);

				    String GAME_PAGE = "Game.jsp";
					// other code here ...
					response.sendRedirect(GAME_PAGE);
					//session.setAttribute("userSession", session);
				
				
		 }
		 catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}

}
