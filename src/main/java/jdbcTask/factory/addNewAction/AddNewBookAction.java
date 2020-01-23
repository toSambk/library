package jdbcTask.factory.addNewAction;

import jdbcTask.dao.AuthorBookDao;
import jdbcTask.dao.AuthorDao;
import jdbcTask.dao.BookDao;
import jdbcTask.dao.PublisherDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

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
    public void execute() throws IOException, SQLException {

        System.out.println("---Введите название книги---");
        String nameOfBook = bufferedReader.readLine();
        if (bookDao.getAll().stream().noneMatch(x -> x.getName().equals(nameOfBook))) {
            bookDao.addNew(nameOfBook);
        } else {
            System.out.println("Данная книга уже добавлена");
            return;
        }

        System.out.println("---Введите имена авторов через пробел---");
        String authorsNotParsed = bufferedReader.readLine();
        for (String author : authorsNotParsed.split(" ")) {
            if (authorDao.getAll().stream().noneMatch(x -> x.getName().equals(author))) {
                System.out.println("Автора " + author + " не существует. Добавляем...");
                authorDao.addNew(author);
                authorBookDao.addDependency(bookDao.getIdByName(nameOfBook), authorDao.getIdByName(author));
            }
        }

        System.out.println("---Введите название издательства---");
        String publisherName = bufferedReader.readLine();
        if (publisherDao.getAll().stream().noneMatch(x -> x.getName().equals(publisherName))) {
            System.out.println("Добавление...");
            publisherDao.addNew(publisherName);
            System.out.println("Издатель добавлен");
        } else {
            System.out.println("Данное издательство уже добавлено");
        }

    }
}
