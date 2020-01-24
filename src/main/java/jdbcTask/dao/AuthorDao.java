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

    private static AuthorDao instance;
    private Connection connection;

    private AuthorDao() {
        connection = App.getConnection();
    }

    public static AuthorDao getInstance() {
        if (instance == null) {
            instance = new AuthorDao();
        }
        return instance;
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

    public Author addNew(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("insert into author(name) values(?)");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        PreparedStatement result = connection.prepareStatement("select id from author where name = (?)");
        result.setString(1, name);
        ResultSet resultSet = result.executeQuery();
        resultSet.next();
        int id = resultSet.getInt("id");
        return new Author(id, name);

    }

    public Author getAuthorByName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection
                .prepareStatement("select id from author where name = (?)");
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return new Author(resultSet.getInt("id"), name);
    }

    public void deleteByName(String name) throws SQLException {

        Author author = getAuthorByName(name);

        PreparedStatement preDel = connection
                .prepareStatement("delete from book_author where author_id = (?)");
        preDel.setInt(1, author.getId());
        preDel.executeUpdate();

        PreparedStatement statement = connection
                .prepareStatement("delete from author where name = (?)");
        statement.setString(1, name);
        statement.executeUpdate();
    }

    public void updateAuthorName(String oldName, String newName) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("update author set name = (?) where name = (?)");
        statement.setString(1, newName);
        statement.setString(2, oldName);
        statement.executeUpdate();
        System.out.println("Добавлено.");
    }

}
