package Model;

import java.sql.*;

public class DataBaseHelper {
    private String url="jdbc:h2:~/test";
    private String user="sa";
    private String password="";

    private Connection conn;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


  public void openConnection() throws SQLException {
      conn= DriverManager.getConnection(url,user,password);
  }
  public void closeConnection() throws SQLException{
      conn.close();
  }
    public int deletePerson(int id) throws SQLException {
        int rowsAffected = 0;
        try {
            openConnection();
            preparedStatement = conn.prepareStatement("DELETE FROM Person WHERE id = ?");
            preparedStatement.setInt(1, id);
            rowsAffected = preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException ex) {
            System.out.println("delete Person Data Base Helper:");
            ex.printStackTrace();
        }
        return rowsAffected;
    }

  public  int insertPerson(Person p)throws SQLException{
int i=0;
     try {
         openConnection();
         preparedStatement = conn.prepareStatement("Insert into Person values (?,?,?,?,?,?)");

         preparedStatement.setInt(1,p.getId());
         preparedStatement.setString(2,p.getName());
         preparedStatement.setString(3,p.getAddress());
         preparedStatement.setString(4,p.getGender());
         preparedStatement.setDate(5,new java.sql.Date(p.getBirthdayDate().getTime()));
         preparedStatement.setString(6,p.getPhone());


         i=preparedStatement.executeUpdate();
         closeConnection();
     }catch (SQLException ex){
         System.out.println("insert Person Data Base Helper:");
         ex.printStackTrace();
     }finally {
         return i;
     }
  }
}
