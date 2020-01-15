package stepOneConsoleApp.factory;

import stepOneConsoleApp.entities.Author;
import stepOneConsoleApp.entities.Book;
import stepOneConsoleApp.entities.Publisher;

public class ActionFactory {

    public Action getAction(Class clazz) {

        if (clazz == Book.class) return new BookActionImpl();

        if (clazz == Author.class) return new AuthorActionImpl();

        if (clazz == Publisher.class) return new PublisherActionImpl();

        return null;

    }
}
