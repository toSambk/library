package jdbcTask;

import jdbcTask.entities.Author;
import jdbcTask.entities.Book;
import jdbcTask.factory.*;

public class AppConfig {

    private BookActionsFactory bookActionsFactory;

    private AuthorActionsFactory authorActionsFactory;

    private PublisherActionsFactory publisherActionsFactory;

    private AbstractActionsFactory getFactory(Class clazz) {
        if (clazz == Book.class) {

            if (bookActionsFactory == null) {
                bookActionsFactory = new BookActionsFactory();
            }
            return bookActionsFactory;

        } else if (clazz == Author.class) {

            if (authorActionsFactory == null) {
                authorActionsFactory = new AuthorActionsFactory();
            }
            return authorActionsFactory;

        } else {

            if (publisherActionsFactory == null) {
                publisherActionsFactory = new PublisherActionsFactory();
            }
            return publisherActionsFactory;

        }
    }

    public Action getAction(Class clazz, ActionType choice) {

        AbstractActionsFactory factory = getFactory(clazz);

        switch (choice) {
            case GET:
                return factory.getAllAction();

            case ADD:
                return factory.getAddNewAction();

            case DELETE:
                return factory.getDeleteAction();

            case UPDATE:
                return factory.getUpdateAction();
        }
        return null;
    }

}
