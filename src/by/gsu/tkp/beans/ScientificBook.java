package by.gsu.tkp.beans;

public class ScientificBook extends Book{
    private String subject;
    private Complexity complexity;

    public ScientificBook(String publisher, String author, int numPages, String subject, Complexity complexity) {
        super(publisher, author, numPages);
        this.subject = subject;
        this.complexity = complexity;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    @Override
    protected String getFullName() {
        return super.getFullName()+";"+subject+";"+complexity;
    }
}
