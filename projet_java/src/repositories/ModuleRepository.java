package repositories;

import Entities.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleRepository extends Database {
    private final String SQL_INSERT = "INSERT INTO `modules` (`nom`) VALUES (?);";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM `modules` WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM `modules`";

    public void insert(Module module) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, module.getNom());
            executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Module selectModuleById(int id) {
        Module module = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                module = new Module();
                module.setId(rs.getInt("id"));
                module.setNom(rs.getString("nom"));
            }
            statement.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la BD");
        }
        return module;
    }

    public List<Module> listerModules() {
        List<Module> modules = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Module module = new Module();
                module.setId(rs.getInt("id"));
                module.setNom(rs.getString("nom"));
                modules.add(module);
            }
            statement.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la BD");
        }
        return modules;
    }
}
