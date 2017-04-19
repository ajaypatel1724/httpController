package com.elitecore.elitehttp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EliteHTTPControllerServlet
 */
public class EliteHTTPControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String MODULE = "EliteHTTPController";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliteHTTPControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(MODULE + "Executing Do Post....");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String redirect_url = request.getParameter("redirect_url");
		String pause = request.getParameter("pause");
		long pauseVal = 1;
		
		try{
			pauseVal = Long.parseLong(pause);
		}catch(Exception e){
			System.out.println(MODULE + "Issue in parsing pause value.");
		}
		
		System.out.println(MODULE + "Received Parameter");
		System.out.println(MODULE + "Username = " + username);
		System.out.println(MODULE + "Password = " + password);
		System.out.println(MODULE + "Redirect Url = " + redirect_url);
		
		if(username == null || password == null){
			System.out.println(MODULE + "Invalid Input Parameter. Reason: Username or Password is missing.");
			request.getRequestDispatcher("redirect.jsp").forward(request, response);
		}
		
		System.out.println(MODULE + "Redirecting to url " + redirect_url);
		try {
			Thread.sleep(pauseVal*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect(redirect_url);
	}

}
