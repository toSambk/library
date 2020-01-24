package jdbcTask.factory.updateAction;

import jdbcTask.dao.BookDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UpdateBookAction implements UpdateAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private BookDao bookDao;

    public UpdateBookAction() {
        bookDao = BookDao.getInstance();
    }

    @Override
    public void execute() throws IOException {

        System.out.println("Введите название книги: ");
        String bookName = bufferedReader.readLine();

        System.out.println("Какие поля вы хотите поменять? Возможные варианты - bookName, authors, publisher ");
        String field = bufferedReader.readLine();
        switch (field) {
            case "bookName":
                changeBookName(bookName);
                break;

            case "authors":
                changeBookAuthors(bookName);
                break;

            case "publisher":
                changeBookPublisher(bookName);
                break;

            default:
                System.out.println("Неопознанная операция");
                break;
        }
    }


    void changeBookName(String bookName) throws IOException {

        System.out.println("Введите новое название книги: ");
        String newBookName = bufferedReader.readLine();
        try {
            System.out.println("Добавление...");
            bookDao.updateBookName(bookName, newBookName);
        } catch (SQLException e) {
            System.out.println("Операция не удалась!");
            e.printStackTrace();
        }

    }

    void changeBookAuthors(String bookName) throws IOException {
        System.out.println("Введите имена авторов через пробел для добавления: ");
        String authorsNotParsed = bufferedReader.readLine();
        List<String> authorNames = Arrays.asList(authorsNotParsed.split(" "));
        try {
            System.out.println("Добавление...");
            bookDao.updateBookAuthor(bookName, authorNames);
        } catch (SQLException e) {
            System.out.println("Неудачная операция!");
            e.printStackTrace();
        }
    }

    void changeBookPublisher(String bookName) throws IOException {
        System.out.println("Введите название издателя: ");
        String publisherName = bufferedReader.readLine();
        try {
            System.out.println("Добавление...");
            bookDao.updateBookPublisher(bookName, publisherName);
        } catch (SQLException e) {
            System.out.println("Неудачная операция!");
            e.printStackTrace();
        }
    }
}
