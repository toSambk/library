package jdbcTask.factory.deleteAction;

import jdbcTask.dao.BookDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class DeleteBookAction implements DeleteAction {

    private BookDao bookDao;

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public DeleteBookAction() {
        bookDao = BookDao.getInstance();
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Введите имя книги для удаления: ");
        String nameOfBook = bufferedReader.readLine();
        try {
            System.out.println("Удаление...");
            bookDao.deleteByName(nameOfBook);
            System.out.println("Удалено.");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления!");
            e.printStackTrace();
        }
    }

}
