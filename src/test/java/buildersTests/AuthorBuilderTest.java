package buildersTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stepOneConsoleApp.builders.AuthorBuilder;
import stepOneConsoleApp.entities.Author;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class AuthorBuilderTest {


    private AuthorBuilder authorBuilder;

    @Before
    public void setup() {
        authorBuilder = new AuthorBuilder();
    }

    @Test
    public void createAuthorTest() {

        Author result = authorBuilder.setName("petr").getResult();
        assertNotNull(result);
        assertEquals(result.getName(), "petr");

    }


}
