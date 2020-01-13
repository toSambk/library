package buildersTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import stepOneConsoleApp.builders.PublisherBuilder;
import stepOneConsoleApp.entities.Publisher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PublisherBuilderTest {

    private PublisherBuilder publisherBuilder;

    @Before
    public void setup() {
        publisherBuilder = new PublisherBuilder();
    }

    @Test
    public void createPublisherTest() {
        Publisher result = publisherBuilder.setName("rosman").getResult();
        assertNotNull(result);
        assertEquals(result.getName(), "rosman");
    }

}
