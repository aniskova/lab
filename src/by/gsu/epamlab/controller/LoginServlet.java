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
;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher(Constants.LOGIN_JSP);
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
				String login = request.getParameter(Constants.LOGIN);
				String password = request.getParameter(Constants.PASSWORD);
				checkInput(login, password);
				
				IUserDAO userDAO = UserDAOFactory.getClassFromFactory();
				User user = userDAO.getUser(login, password);
				
				request.getSession().setAttribute(Constants.USER, user);
				
				response.sendRedirect(Constants.TODO);
		} catch(ValidationException | DAOException exc){
			request.setAttribute(Constants.ERROR, exc);
			request.getRequestDispatcher(Constants.LOGIN_JSP).forward(request, response);
		}
	}
	
	private static void checkInput(String login, String password) {
		if(login == null || password == null) {
			throw new ValidationException(Constants.REQUIED_PARAMETERS_ABSENT_ERROR);
		}
		
		if(Constants.EMPTY.equals(login)) {
			throw new ValidationException(Constants.LOGIN_EMPTY_ERROR);
		}
	}
}
