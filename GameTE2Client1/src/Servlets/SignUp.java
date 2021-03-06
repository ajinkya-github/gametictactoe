package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connection.ServiceProxy;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ServiceProxy proxy=new ServiceProxy();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
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
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String qdone;		 
		 try{			
			    System.out.println();
			 	String user = request.getParameter("user");
			 	String pass = request.getParameter("pass");
			 	proxy.setEndpoint("http://localhost:8080/GameTE2/services/Service");
				qdone = proxy.signUp(user,pass);
				String[] qd = qdone.split(":");
				HttpSession session = request.getSession();
				if(qdone.substring(0,4).equals("true")){
					session.setAttribute("userSession", session);
					session.setAttribute("userName", user);
					session.setAttribute("userGAME", qd[2]);					
					session.setAttribute("userID", qd[1]);
				out.println("WELCOME to Simple Market Place :"+ user);//Displaying the message on the servlet itself.

					String GAME_PAGE = "Game.jsp";
					// other code here ...
					response.sendRedirect(GAME_PAGE);
				}
				else{
					out.println(qdone.substring(6));
					out.println("\n <a href=\"SignUp.jsp\"><br>Go back to SignUp again</a>");
				}
		 }
		 catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}

}
