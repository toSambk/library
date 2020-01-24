package jdbcTask.factory.addNewAction;

import jdbcTask.dao.PublisherDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class AddNewPublisherAction implements AddNewAction {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private PublisherDao publisherDao;

    public AddNewPublisherAction() {
        publisherDao = PublisherDao.getInstance();
    }

    @Override
    public void execute() throws IOException {
        System.out.println("Введите имя издателя для добавления: ");
        String nameAuthor = bufferedReader.readLine();
        try {
            System.out.println("Добавление издателя...");
            publisherDao.addNew(nameAuthor);
            System.out.println("Добавлено.");
        } catch (SQLException e) {
            System.out.println("Ошибка добавления!!!");
            e.printStackTrace();
        }
    }
}
