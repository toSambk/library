package buildersTests;

import org.junit.Before;
import org.junit.Test;
import consoleApp.builders.AuthorBuilder;
import consoleApp.builders.BookBuilder;
import consoleApp.builders.PublisherBuilder;
import consoleApp.entities.Author;
import consoleApp.entities.Book;
import consoleApp.entities.Publisher;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookBuilderTest {

    private BookBuilder bookBuilder;

    private Author author, author2;

    private Publisher publisher;

    @Before
    public void setup() {
        bookBuilder = new BookBuilder();
        author = new AuthorBuilder().setName("petr").getResult();
        author2 = new AuthorBuilder().setName("grisha").getResult();
        publisher = new PublisherBuilder().setName("rosman").getResult();
    }

    @Test
    public void createBookTest() {
        Book result = bookBuilder.setName("bookName").setAuthors(Arrays.asList(author, author2)).setPublisher(publisher).getResult();
        assertNotNull(result);
        assertEquals(result.getName(), "bookName");
        assertEquals(result.getAuthors().size(), 2);
        assertEquals(result.getPublisher().getName(), publisher.getName());
    }


}
