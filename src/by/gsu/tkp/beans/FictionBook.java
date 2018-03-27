package by.gsu.tkp.beans;

public class FictionBook extends Book {
private String genre;

    public FictionBook(String publisher, String author, int numPages, String genre) {
        super(publisher, author, numPages);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    protected String getFullName() {
        return super.getFullName()+";"+genre;
    }
}
