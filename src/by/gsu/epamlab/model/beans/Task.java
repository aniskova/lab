package by.gsu.epamlab.model.beans;


import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;

public class Task {
    private int id;
    private int id_user;
    private String description;
    private Date date;
    private TaskState state;
    private String fileName;

    public Task() {
    }



    public Task(int id, User user, String description, Date date, TaskState state, String fileName) {
        this.id = id;
        this.id_user = user.getId();
        this.description = description;
        this.date = date;
        this.state = state;
        this.fileName = fileName;
    }
    public Task(int id,User user, String description, String date, TaskState state,String fileName) {
        this.id = id;
        this.id_user = user.getId();
        this.description = description;
        setDate(date);
        this.state = state;
        this.fileName = fileName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setDate(String date){
        this.date = Date.valueOf(date);
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String filename) {
        this.fileName = filename;
    }
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", isFixed=" + state +
                '}';
    }
}
