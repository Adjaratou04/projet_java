package repositories;

import Entities.Professeur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurRepository extends Database {
    private final String SQL_INSERT = "INSERT INTO `professeurs` (`nom`, `prenom`, `tel`) VALUES (?, ?, ?);";
    private final String SQL_SELECT_BY_ID = "SELECT * FROM `professeurs` WHERE id = ?";
    private final String SQL_SELECT_ALL = "SELECT * FROM `professeurs`";

    public void insert(Professeur professeur) {
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, professeur.getNom());
            statement.setString(2, professeur.getPrenom());
            statement.setString(3, professeur.getTel());
            executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Professeur selectProfesseurById(int id) {
        Professeur professeur = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                professeur = new Professeur();
                professeur.setId(rs.getInt("id"));
                professeur.setNom(rs.getString("nom"));
                professeur.setPrenom(rs.getString("prenom"));
                professeur.setTel(rs.getString("tel"));
            }
            statement.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la BD");
        }
        return professeur;
    }

    public List<Professeur> listerProfesseurs() {
        List<Professeur> professeurs = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_SELECT_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Professeur professeur = new Professeur();
                professeur.setId(rs.getInt("id"));
                professeur.setNom(rs.getString("nom"));
                professeur.setPrenom(rs.getString("prenom"));
                professeur.setTel(rs.getString("tel"));
                professeurs.add(professeur);
            }
            statement.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la BD");
        }
        return professeurs;
    }
}
