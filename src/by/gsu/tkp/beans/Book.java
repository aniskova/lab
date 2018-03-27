package by.gsu.tkp.beans;

public class Book {
    private String publisher;
    private String author;
    private int numPages;

    public Book() {
    }

    public Book(String publisher, String author, int numPages) {
        this.publisher = publisher;
        this.author = author;
        this.numPages = numPages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }
    protected String getFullName(){
        return publisher+";"+author;
    }

    @Override
    public String toString() {
        return getFullName()+";"+numPages;
    }
}
