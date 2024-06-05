package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Entities.Cours;

public class CoursRepository extends Database {
    private final String SQL_INSERT_COURS = "INSERT INTO cours (date, heure_Debut, heure_Fin, professeur_id, module_id) VALUES (?, ?, ?, ?, ?)";


    private final String SQL_SELECT_ALL_COURS = "SELECT * FROM cours";
    private final String SQL_SELECT_COURS_BY_MODULE_AND_PROFESSEUR = "SELECT * FROM cours WHERE module_id = ? AND professeur_id = ?";

    public void insert(Cours cours) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT_COURS);
            statement.setDate(1, Date.valueOf(cours.getDate()));
            statement.setTime(2, Time.valueOf(cours.getHeureDebut()));
            statement.setTime(3, Time.valueOf(cours.getHeureFin()));
            statement.setInt(4, cours.getProfesseur().getId());
            statement.setInt(5, cours.getModule().getId());
            executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion du cours : " + e.getMessage());
            e.printStackTrace();
        }
    }

    

    public List<Cours> findAll() {
        List<Cours> cours = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_ALL_COURS);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Cours cour = new Cours();
                cour.setId(rs.getInt("id"));
                cour.setDate(rs.getDate("date").toLocalDate());
                cour.setHeureDebut(rs.getTime("heure_Debut").toLocalTime());
                cour.setHeureFin(rs.getTime("heure_Fin").toLocalTime());
                cours.add(cour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return cours;
    }

    public List<Cours> findByModuleAndProfesseur(int moduleId, int professeurId) {
        List<Cours> cours = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_COURS_BY_MODULE_AND_PROFESSEUR);
            statement.setInt(1, moduleId);
            statement.setInt(2, professeurId);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Cours cour = new Cours();
                cour.setId(rs.getInt("id"));
                cour.setDate(rs.getDate("date").toLocalDate());
                cour.setHeureDebut(rs.getTime("heure_Debut").toLocalTime());
                cour.setHeureFin(rs.getTime("heure_Fin").toLocalTime());
                cours.add(cour);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return cours;
    }
}
