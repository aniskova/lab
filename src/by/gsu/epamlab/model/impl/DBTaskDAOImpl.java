package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Section;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.TaskState;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.util.ConnectionPool;
import by.gsu.epamlab.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTaskDAOImpl implements ITaskDAO {
    private static final String GET_ALL_TASKS_SQL="SELECT * FROM tasks WHERE id_user = ?";
    private static final String ADD_TASK_SQL="INSERT INTO tasks (id_user,description, date,state, file_name, file_data)  VALUES (?,?,?,?,?,?)";

    private final static String GET_TASKS_SQL_HEAD = "SELECT * FROM tasks WHERE ";
    private final static String GET_TASKS_SQL_TAIL = " AND id_user = ?";
    private final static String GET_TASK_BY_ID = "SELECT * FROM tasks WHERE id = ?";
    private final static String UPDATE_TASK = "UPDATE tasks SET description = ?, date = ? WHERE id = ?";
    private final static String UPDATE_FILE = "UPDATE tasks SET file_name = ?, file_data = ? WHERE id = ?";
    private final static String CHANGE_TASK_STATE = "UPDATE tasks SET state = ? WHERE id = ?";
    private final static String DELETE_TASK = "DELETE FROM tasks WHERE id = ?";
    private final static String GET_BINARY_FILE = "SELECT file_data FROM tasks WHERE id = ?";


    @Override
    public List<Task> getTasksList(User user, Section section) {
        final int ID_IND = 1;
        final int ID_DESCRIPTION = 3;
        final int ID_USER = 2;
        final int ID_DATE = 4;
        final int ID_STATE = 5;
        final int ID_FILE_NAME = 6;
        List<Task> list= new ArrayList<>();
        String idU =String.valueOf(user.getId());

        try(Connection cn = ConnectionPool.getConnection();
            PreparedStatement ps = cn.prepareStatement(GET_TASKS_SQL_HEAD+section.toString()+GET_TASKS_SQL_TAIL)) {
            ps.setString(ID_USER -1,idU);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(ID_IND);
                String description = rs.getString(ID_DESCRIPTION);
                Date date = rs.getDate(ID_DATE);
                TaskState state = TaskState.valueOf(rs.getString(ID_STATE).toUpperCase());
                String filename = rs.getString(ID_FILE_NAME);
                Task task = new Task(id, user, description, date,state, filename);
                list.add(task);
            }
            return list;
        }
        catch (SQLException e){throw  new DAOException(Constants.DATA_SOURCE_ERROR + e);}

    }

    @Override
    public Task addTask(User user, String description, String date,String fileName,InputStream inputStream) throws IOException {
        final int ID_USER = 1;
        final int ID_DESCR = 2;
        final int ID_DATE = 3;
        final int ID_STATE = 4;
        final int ID_FILE_NAME = 5;
        final int ID_FILE_DATA = 6;
        String id_user = String.valueOf(user.getId());
        try(Connection cn = ConnectionPool.getConnection(); PreparedStatement ps = cn.prepareStatement(ADD_TASK_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(ID_USER, id_user);
            ps.setString(ID_DESCR, description);
            ps.setString(ID_DATE, date);
            ps.setString(ID_STATE, TaskState.ACTIVE.toString());
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                ps.setString(ID_FILE_NAME, fileName);
                ps.setBinaryStream(ID_FILE_DATA,inputStream,inputStream.available());
            }
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                return new Task(id, user, description,date,TaskState.ACTIVE, fileName);
            }else {
                throw new DAOException(Constants.DATA_SOURCE_ERROR );
            }


        }catch (SQLException e){throw  new DAOException( Constants.DATA_SOURCE_ERROR + e);}

    }

    @Override
    public void changeState( String[] ids, String newState) {
        final int ID_STATE = 1;
        final int ID_IND = 2;
        TaskState newTaskState =
                TaskState.valueOf(newState.toUpperCase());
        try(Connection cn = ConnectionPool.getConnection();
            PreparedStatement ps = cn.prepareStatement(CHANGE_TASK_STATE)) {
            for (String id : ids) {
                ps.setString(ID_STATE,newTaskState.toString());
                ps.setString(ID_IND, id);
                ps.executeUpdate();
            }
        }
        catch (SQLException e){throw  new DAOException(Constants.DATA_SOURCE_ERROR + e);}


    }
    @Override
    public void deleteTasksFromDB( String[] ids) {
        final int ID_IND = 1;
        try(Connection cn = ConnectionPool.getConnection();
            PreparedStatement ps = cn.prepareStatement(DELETE_TASK)) {
            for (String id : ids) {
                ps.setString(ID_IND, id);
                ps.executeUpdate();
            }
        }
        catch (SQLException e){throw  new DAOException(Constants.DATA_SOURCE_ERROR + e);}

    }

    @Override
    public void updateTask(String id, String description, String date) {
        final int ID_DESC = 1;
        final int ID_DATE = 2;
        final int ID_ID = 3;
        try(Connection cn = ConnectionPool.getConnection();
            PreparedStatement ps = cn.prepareStatement(UPDATE_TASK)) {
            ps.setString(ID_DESC, description);
            ps.setString(ID_DATE, date);
            ps.setString(ID_ID, id);
            ps.executeUpdate();
        }
        catch (SQLException e){throw  new DAOException(Constants.DATA_SOURCE_ERROR + e);}
    }

    @Override
    public Task getTaskById(String id, User user) {
        final int ID_IND = 1;
        final int ID_DESCRIPTION = 3;
        final int ID_DATE = 4;
        final int ID_STATE = 5;
        final int ID_FILE_NAME = 6;
        Task result = null;
        try(Connection cn = ConnectionPool.getConnection();
            PreparedStatement ps = cn.prepareStatement(GET_TASK_BY_ID)) {
            ps.setString(ID_IND, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String description = rs.getString(ID_DESCRIPTION);
                Date date = rs.getDate(ID_DATE);
                TaskState state = TaskState.valueOf(rs.getString(ID_STATE).toUpperCase());
                String filename = rs.getString(ID_FILE_NAME);
                result = new Task(Integer.valueOf(id), user, description, date,state, filename);
                return result;
            }else throw new DAOException(Constants.DATA_SOURCE_ERROR);

        }
        catch (SQLException e){throw  new DAOException(Constants.DATA_SOURCE_ERROR + e);}
    }
    public InputStream getDbBinaryFile(String id){
        try(Connection cn = ConnectionPool.getConnection();PreparedStatement ps=cn.prepareStatement(GET_BINARY_FILE)){
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Blob blob = rs.getBlob("file_data");
                return blob.getBinaryStream();
            }else throw new DAOException(Constants.DATA_SOURCE_ERROR);
        } catch (SQLException e) {
            throw new DAOException(Constants.DATA_SOURCE_ERROR+e);
        }

    }
    @Override
    public void DeleteFile(String id){
        UploadFile(id,"",null);
    }
    public void UploadFile(String id,String fileName,InputStream inputStream){
        final int ID_FILE_NAME = 1;
        final int ID_FILE_DATA = 2;
        final int ID_ID = 3;
        try(Connection cn = ConnectionPool.getConnection(); PreparedStatement ps = cn.prepareStatement(UPDATE_FILE)) {
            ps.setString(ID_FILE_NAME, fileName);
            if(inputStream==null){
                ps.setString(ID_FILE_DATA,"");
            }else {
            ps.setBinaryStream(ID_FILE_DATA,inputStream,inputStream.available());
            }
            ps.setString(ID_ID, id);
            ps.executeUpdate();
        }catch (SQLException |IOException e){throw  new DAOException( Constants.DATA_SOURCE_ERROR + e);}
    }

}
