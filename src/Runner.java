import by.gsu.tkp.beans.Book;
import by.gsu.tkp.beans.Complexity;
import by.gsu.tkp.beans.FictionBook;
import by.gsu.tkp.beans.ScientificBook;

public class Runner {

    public static void main(String[] args) {
        Book books[]=new Book[]{
                new Book("Publisher1","Author1",200),
                new FictionBook("Publisher1","Author5",370, "genre1"),
                new Book("Publisher2","Author1",700),
                new ScientificBook("Publisher3","Author3",280,"subj1", Complexity.EASY),
                new ScientificBook("Publisher5","Author5",720,"subj2", Complexity.HARD),
                new ScientificBook("Publisher3","Author3",415,"subj3", Complexity.MEDIUM),
                new Book("Publisher9","Author9",999),
                new Book("Publisher6","Author5",222),
                new FictionBook("Publisher8","Author7",777, "genre10"),
                new Book("Publisher7","Author1",200),
        };
        for (Book book : books) {
            System.out.println(book);
        }

    }
}
