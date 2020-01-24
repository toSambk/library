package jdbcTask.dao;

import jdbcTask.App;
import jdbcTask.entities.Book;
import jdbcTask.entities.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao {

    private static PublisherDao instance;
    private Connection connection;

    private PublisherDao() {
        connection = App.getConnection();
    }

    public static PublisherDao getInstance() {
        if (instance == null) {
            instance = new PublisherDao();
        }
        return instance;
    }

    public Publisher addNew(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("insert into publisher(name) values(?)");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();

        PreparedStatement result = connection
                .prepareStatement("select id from publisher where name = (?)");
        result.setString(1, name);
        ResultSet resultSet = result.executeQuery();
        result.close();
        resultSet.next();
        int id = resultSet.getInt("id");
        return new Publisher(id, name);

    }

    public Publisher addNewWithBook(String name, Book book) throws SQLException {

        PreparedStatement statement = connection
                .prepareStatement("insert into publisher(name, book_id) values (?, ?)");
        statement.setString(1, name);
        statement.setInt(2, book.getId());
        statement.executeUpdate();
        statement.close();

        PreparedStatement result = connection
                .prepareStatement("select id from publisher where name = (?)");
        result.setString(1, name);
        ResultSet resultSet = result.executeQuery();
        result.close();
        resultSet.next();
        int id = resultSet.getInt("id");
        return new Publisher(id, name, book);

    }

    public List<Publisher> getAll() {
        List<Publisher> publishers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from publisher");
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
            while (resultSet.next()) {
                int publisherId = resultSet.getInt("id");
                String publisherName = resultSet.getString("name");
                publishers.add(new Publisher(publisherId, publisherName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return publishers;
    }

    public Publisher getPublisherByName(String name) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("select *from publisher where name = (?)");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        statement.close();
        resultSet.next();
        int id = resultSet.getInt("id");
        String curName = resultSet.getString("name");
        return new Publisher(id, curName);

    }

    public void deleteByName(String name) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("delete from publisher where name = (?)");
        statement.setString(1, name);
        statement.executeUpdate();
        statement.close();
    }

    public void updatePublisherName(String oldName, String newName) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("update publisher set name = (?) where name = (?)");
        statement.setString(1, newName);
        statement.setString(2, oldName);
        statement.executeUpdate();
        statement.close();
        System.out.println("Добавлено.");
    }


}
