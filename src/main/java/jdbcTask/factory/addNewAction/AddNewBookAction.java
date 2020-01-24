package jdbcTask.factory.addNewAction;

import jdbcTask.dao.AuthorBookDao;
import jdbcTask.dao.AuthorDao;
import jdbcTask.dao.BookDao;
import jdbcTask.dao.PublisherDao;
import jdbcTask.entities.Author;
import jdbcTask.entities.Book;
import jdbcTask.entities.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddNewBookAction implements AddNewAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private BookDao bookDao;

    private AuthorDao authorDao;

    private PublisherDao publisherDao;

    private AuthorBookDao authorBookDao;

    public AddNewBookAction() {
        bookDao = BookDao.getInstance();
        authorDao = AuthorDao.getInstance();
        publisherDao = PublisherDao.getInstance();
        authorBookDao = AuthorBookDao.getInstance();
    }

    @Override
    public void execute() throws IOException {

        System.out.println("---Введите название книги---");
        String nameOfBook = bufferedReader.readLine();
        Book curBook;

        try {
            if (bookDao.getAll().stream().noneMatch(x -> x.getName().equals(nameOfBook))) {
                System.out.println("Добавление...");
                curBook = bookDao.addNew(nameOfBook);
                System.out.println("Добавлено.");
            } else {
                System.out.println("Данная книга уже добавлена");
                return;
            }
        } catch (SQLException exc) {
            System.out.println("Ошибка добавления!");
            exc.printStackTrace();
            return;
        }

        try {
            System.out.println("---Введите имена авторов через пробел---");
            String authorsNotParsed = bufferedReader.readLine();
            List<Author> authors = new ArrayList<>();
            for (String author : authorsNotParsed.split(" ")) {
                if (authorDao.getAll().stream().noneMatch(x -> x.getName().equals(author))) {
                    System.out.println("Автора " + author + " не существует. Добавляем...");
                    Author curAuthor = authorDao.addNew(author);
                    authors.add(curAuthor);
                    authorBookDao.addDependency(curBook, curAuthor);
                    System.out.println("Автор " + author + " добавлен.");
                } else {
                    authors.add(authorDao.getAuthorByName(author));
                }
            }
            curBook.setAuthors(authors);
        } catch (SQLException exc) {
            System.out.println("Ошибка добавления авторов");
            exc.printStackTrace();
            return;
        }

        System.out.println("---Введите название издательства---");
        String publisherName = bufferedReader.readLine();
        Publisher publisher;
        try {
            if (publisherDao.getAll().stream().noneMatch(x -> x.getName().equals(publisherName))) {
                System.out.println("Добавление...");
                publisher = publisherDao.addNewWithBook(publisherName, curBook);
                System.out.println("Издатель добавлен");
            } else {
                publisher = publisherDao.getPublisherByName(publisherName);
                System.out.println("Данное издательство уже добавлено");
            }
            curBook.setPublisher(publisher);
        } catch (SQLException exc) {
            System.out.println("Ошибка добавления издателя!");
            exc.printStackTrace();
        }

    }
}
