package jdbcTask.factory.deleteAction;

import jdbcTask.dao.PublisherDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class DeletePublisherAction implements DeleteAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private PublisherDao publisherDao;

    public DeletePublisherAction() {
        publisherDao = PublisherDao.getInstance();
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Введите имя издателя для удаления: ");
        String nameOfAuthor = bufferedReader.readLine();
        try {
            System.out.println("Удаление...");
            publisherDao.deleteByName(nameOfAuthor);
            System.out.println("Удалено.");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления!");
            e.printStackTrace();
        }

    }
}
