package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.model.beans.Section;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.TaskState;
import by.gsu.epamlab.model.beans.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public interface ITaskDAO {
    List<Task> getTasksList(User user, Section section);
    Task addTask(User user, String description, String date,String fileName,InputStream inputStream) throws IOException;
    void changeState( String[] ids, String newState);
    void deleteTasksFromDB( String[] ids);
    void updateTask(String id,String description, String date);
    Task getTaskById(String id, User user);
    InputStream getDbBinaryFile(String id);
    void DeleteFile(String id);
    void UploadFile(String id,String fileName,InputStream inputStream);

}
