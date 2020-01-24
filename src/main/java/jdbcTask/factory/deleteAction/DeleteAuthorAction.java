package jdbcTask.factory.deleteAction;

import jdbcTask.dao.AuthorDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class DeleteAuthorAction implements DeleteAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private AuthorDao authorDao;

    public DeleteAuthorAction() {
        authorDao = AuthorDao.getInstance();
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Введите имя автора для удаления: ");
        String nameOfAuthor = bufferedReader.readLine();
        try {
            System.out.println("Удаление...");
            authorDao.deleteByName(nameOfAuthor);
            System.out.println("Удалено.");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления!");
            e.printStackTrace();
        }
    }

}
