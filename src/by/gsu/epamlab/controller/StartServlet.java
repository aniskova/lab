package by.gsu.epamlab.controller;

import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.factories.UserDAOFactory;
import by.gsu.epamlab.util.Constants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String UserdataSource = config.getInitParameter(Constants.USER_DAO_IMPL);
        String TaskdataSource = config.getInitParameter(Constants.TASK_DAO_IMPL);
        UserDAOFactory.setDataSource(UserdataSource);
        TaskDAOFactory.setDataSource(TaskdataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    request.getRequestDispatcher(Constants.MAIN_JSP).forward(request, response);

    }
}
