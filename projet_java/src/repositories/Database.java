
package repositories;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Database {
     protected Connection conn=null;
     protected  PreparedStatement statement=null;

     public void ouvrirConnexion() {
       try {
        Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/projet_java", "root", "");
       } catch (Exception e) {
          System.out.println("Erreur de connexion a la BD");
       }
     }
     public void initPrepareStatement(String sql){
        try {
            statement= conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Erreur Initialisation du PrepareStatement");
        }
     }
     public int executeUpdate(){
        int nbreLigne=0;
          try {
            nbreLigne= statement.executeUpdate();
         } catch (SQLException e) {
            System.out.println("Erreur Execution de requete");
          }
        return nbreLigne;
     }


     public ResultSet executeSelect(){
        ResultSet rs=null;
        try {
            rs= statement.executeQuery();
         } catch (SQLException e) {
            System.out.println("Erreur Execution de requete");
          }
          return rs;
     }

     public void fermerConnexion() throws SQLException {
        if (conn!=null) {
            conn.close();
        }
     }

}

