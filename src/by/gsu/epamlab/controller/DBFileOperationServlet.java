package by.gsu.epamlab.controller;

import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.util.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
@MultipartConfig
public class DBFileOperationServlet
        extends HttpServlet {
    // size of byte buffer to send file
    private static final int BUFFER_SIZE = 4096;

    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {
        String taskID = req.getParameter("taskID");
        User user = (User) req.getSession().getAttribute(Constants.USER);
        ITaskDAO taskDAO = TaskDAOFactory.getClassFromFactory();
        Task task = taskDAO.getTaskById(taskID, user);
        InputStream inputStream = taskDAO.getDbBinaryFile(taskID);
        int fileLength = inputStream.available();
        ServletContext context = getServletContext();
        String fileName = task.getFileName();
                // sets MIME type for the file download
                String mimeType = context.getMimeType(fileName);
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                // set content properties and header attributes for the response
                resp.setContentType(mimeType);
                resp.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                resp.setHeader(headerKey, headerValue);
                // writes the file to the client
                OutputStream outStream = resp.getOutputStream();

                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inputStream.close();
                outStream.close();
        resp.sendRedirect(Constants.TODO);
              }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String taskID = req.getParameter("taskID");
        ITaskDAO taskDAO = TaskDAOFactory.getClassFromFactory();
        if("deleteFile".equals(action)){
            taskDAO.DeleteFile(taskID);
        }
        if("uploadFile".equals(action)){
            Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
            if (filePart != null) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                InputStream inputStream = filePart.getInputStream();
                taskDAO.UploadFile(taskID,fileName, inputStream);
                inputStream.close();
            }}
            resp.sendRedirect(Constants.TODO);



    }
}
