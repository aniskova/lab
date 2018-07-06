package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.gsu.epamlab.util.Constants;

public class LogoutServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		request.getSession().invalidate();
		response.sendRedirect(Constants.MAIN_JSP);
	}
}
