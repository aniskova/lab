package by.gsu.epamlab.controller;

import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@MultipartConfig
public class ChangeStateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("deleteFile".equals(action)||"uploadFile".equals(action)||"downloadFile".equals(action)){req.getRequestDispatcher("fileOperation").forward(req,resp);return;}
        String[] ids = req.getParameterValues("checkbox");
        ITaskDAO taskDAO = TaskDAOFactory.getClassFromFactory();
        String NEXT_PAGE = Constants.TODO;
        if("update".equals(action)){
            req.getSession().setAttribute("taskID",ids[0]);
           NEXT_PAGE = Constants.UPDATE;
        }else if("deleteFromDB".equals(action)){
            taskDAO.deleteTasksFromDB(ids);
        }else {
            taskDAO.changeState(ids,action);
        }
        resp.sendRedirect(NEXT_PAGE);

    }
}
