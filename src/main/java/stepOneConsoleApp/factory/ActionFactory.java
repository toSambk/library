package stepOneConsoleApp.factory;

import stepOneConsoleApp.entities.Author;
import stepOneConsoleApp.entities.Book;
import stepOneConsoleApp.entities.Publisher;

public class ActionFactory {

    public Action getAction(Class clazz) {

        if (clazz == Book.class) return new BookAction();

        if (clazz == Author.class) return new AuthorAction();

        if (clazz == Publisher.class) return new PublisherAction();

        return null;

    }
}
