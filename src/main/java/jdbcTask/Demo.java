package jdbcTask;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Demo {

    private static final String url = "jdbc:mysql://localhost:3306/library";

    private static final String user = "root";

    private static final String password = "password";

    private static Connection connection;

    private static Statement statement;

    private static ResultSet resultSet;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

//       Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

       try {
           connection = DriverManager.getConnection(url, user, password);

           statement = connection.createStatement();

           ResultSet resultSet = statement.executeQuery("select *from test");

           int columns = resultSet.getMetaData().getColumnCount();

           while (resultSet.next()) {
               for(int i = 1; i <= columns; i++) {
                   System.out.print(resultSet.getString(i) + "\t");
               }
               System.out.println();
           }

           if(resultSet!=null) {
               resultSet.close();
           }
           if(statement!=null){
               statement.close();
           }
           if(connection!=null){
               connection.close();
           }

       } catch (SQLException e) {
           e.printStackTrace();
       }

    }

}
