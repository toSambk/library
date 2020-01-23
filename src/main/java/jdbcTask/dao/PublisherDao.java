package jdbcTask.dao;

import jdbcTask.App;
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

    public void addNew(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("insert into publisher(name) values(?)");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    public List<Publisher> getAll() {
        List<Publisher> publishers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from publisher");
            ResultSet resultSet = preparedStatement.executeQuery();
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
}
