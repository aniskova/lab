package by.gsu.epamlab.controller;

import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskID = (String) req.getSession().getAttribute("taskID");
        User user = (User) req.getSession().getAttribute(Constants.USER);
        ITaskDAO taskDAO = TaskDAOFactory.getClassFromFactory();
        Task task = taskDAO.getTaskById(taskID, user);
        req.setAttribute("task",task);
        req.getRequestDispatcher(Constants.UPDATE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String description = req.getParameter("description");
       String date = req.getParameter("date");
        String taskID = (String) req.getSession().getAttribute("taskID");
       ITaskDAO taskDAO = TaskDAOFactory.getClassFromFactory();
       taskDAO.updateTask(taskID, description,date);
       resp.sendRedirect(Constants.TODO);


    }
}
