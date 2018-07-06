package by.gsu.epamlab.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.model.factories.UserDAOFactory;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.util.Constants;


public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(Constants.REGISTRATION_JSP);
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String login = request.getParameter(Constants.LOGIN);
			String password = request.getParameter(Constants.PASSWORD);
			String rePassword = request.getParameter(Constants.RE_PASSWORD);
			checkInput(login,  password, rePassword);

			IUserDAO userDAO = UserDAOFactory.getClassFromFactory();
			User user = userDAO.addUser(login, rePassword);

			request.getSession().setAttribute(Constants.USER, user);

			response.sendRedirect(Constants.TODO);
		} catch(ValidationException | DAOException e){
			request.setAttribute(Constants.ERROR, e);
			request.getRequestDispatcher(Constants.REGISTRATION_JSP).forward(request, response);
		}

	}
	
	private static void checkInput(String login, String password, String rePassword) {
		if(login == null || Constants.EMPTY.equals(login.trim())) {
			throw new ValidationException(Constants.LOGIN_EMPTY_ERROR);
		}
		
		login = login.trim();
		
		if (password == null ||rePassword == null
				|| Constants.EMPTY.equals(password) || Constants.EMPTY.equals(rePassword)) {
			throw new ValidationException(Constants.REQUIED_PARAMETERS_ABSENT_ERROR);
		} else {
			if(!password.equals(rePassword)) {
				throw new ValidationException(Constants.BOTH_PASSWORDS_NOT_SAME);
			}
		}
	}

}
