package jdbcTask.dao;


import jdbcTask.App;
import jdbcTask.entities.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    private Connection connection;

    private static AuthorDao instance;

    private AuthorDao() {
        connection = App.getConnection();
    }

    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from author");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int authorId = resultSet.getInt("id");
                String authorName = resultSet.getString("name");
                authors.add(new Author(authorId, authorName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    public void addNew(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("insert into author(name) values(?)");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    public int getIdByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("select id from author where name = (?)");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    public static AuthorDao getInstance() {
        if(instance == null) {
            instance = new AuthorDao();
        }
        return instance;
    }

}
