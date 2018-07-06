package by.gsu.epamlab.controller;

import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Section;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.util.Constants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
@MultipartConfig
public class TodoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ITaskDAO iTaskDAO = TaskDAOFactory.getClassFromFactory();
        String sectionStr = req.getParameter(Constants.SECTION);
        if(sectionStr==null){
            sectionStr = "today";
        }
        Section section = Section.valueOf(sectionStr.toUpperCase());
        List<Task> todoList =  iTaskDAO.getTasksList((User)req.getSession().getAttribute(Constants.USER), section);
        req.setAttribute("todoList", todoList);
       req.getRequestDispatcher(Constants.TODO_JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String description = req.getParameter("description");
        String sectionStr = req.getParameter(Constants.SECTION);
        if(sectionStr==null){
            sectionStr = "today";
        }
        Section section = Section.valueOf(sectionStr.toUpperCase());
        String date;
       if(section == Section.SOMEDAY){
           date = req.getParameter("date");
       }else {
           date = section.getSectionDate().toString();
       }
       User user = (User)req.getSession().getAttribute(Constants.USER);
        ITaskDAO iTaskDAO = TaskDAOFactory.getClassFromFactory();
        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
        if (filePart != null) {
           String fileName =Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
           InputStream inputStream = filePart.getInputStream();
            iTaskDAO.addTask(user, description,date,fileName, inputStream);
            inputStream.close();
        }else {
       iTaskDAO.addTask(user, description,date,"", null);}

       resp.sendRedirect(Constants.TODO_SECTION+sectionStr);
    }
}
