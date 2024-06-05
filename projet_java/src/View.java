import java.util.List;
import java.util.Scanner;
import Services.ModuleService;
import Services.CoursService;
import Services.ProfesseurService;
import Entities.Module;
import Entities.Cours;
import Entities.Professeur;
import java.time.LocalDate;
import java.time.LocalTime;

public class View {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ModuleService moduleService = new ModuleService();
        CoursService coursService = new CoursService();
        ProfesseurService professeurService = new ProfesseurService();
        int choix;

        do {
            System.out.println("******* GESTION SCOLAIRE ******");
            System.out.println("1. Ajouter un Module");
            System.out.println("2. Lister les Modules");
            System.out.println("3. Créer un Cours");
            System.out.println("4. Lister tous les Cours");
            System.out.println("5. Lister les Cours d'un Module et d'un Professeur");
            System.out.println("6. Quitter");
            choix = sc.nextInt();
            sc.nextLine();

            switch (choix) {
                case 1:
                    System.out.println("Entrer le nom du module:");
                    String nomModule = sc.nextLine();
                    Module module = new Module();
                    module.setNom(nomModule);
                    moduleService.ajouterModule(module);
                    System.out.println("Module ajouté avec succès.");
                    break;

                case 2:
                    System.out.println("Les modules sont les suivants:");
                    List<Module> modules = moduleService.listerModules();
                    for (Module mod : modules) {
                        System.out.println("ID: " + mod.getId() + " - Nom: " + mod.getNom());
                    }
                    break;
                    case 3:
                    System.out.println("Entrer la date du cours (aaaa-mm-jj):");
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    System.out.println("Entrer l'heure de début du cours (HH:MM):");
                    LocalTime heureDebut = LocalTime.parse(sc.nextLine());
                    System.out.println("Entrer l'heure de fin du cours (HH:MM):");
                    LocalTime heureFin = LocalTime.parse(sc.nextLine());

                  
                    System.out.println("Entrer le nom du professeur:");
                    String nomProfesseur = sc.nextLine();
                    System.out.println("Entrer le prénom du professeur:");
                    String prenomProfesseur = sc.nextLine();
                    System.out.println("Entrer le téléphone du professeur:");
                    String telProfesseur = sc.nextLine();
                    Professeur professeur = new Professeur();
                    professeur.setNom(nomProfesseur);
                    professeur.setPrenom(prenomProfesseur);
                    professeur.setTel(telProfesseur);
                    professeurService.ajouterProfesseur(professeur);
                    System.out.println("Professeur ajouté avec succès.");

                    
                    System.out.println("Les professeurs sont les suivants:");
                    List<Professeur> professeurs = professeurService.listerProfesseurs();
                    for (Professeur prof : professeurs) {
                        System.out.println("ID: " + prof.getId() + " - Nom: " + prof.getNom() +
                                           " - Prénom: " + prof.getPrenom() + " - Téléphone: " + prof.getTel());
                    }
                    System.out.println("Entrer l'ID du professeur:");
                    int professeurId = sc.nextInt();
                    sc.nextLine(); 

                    
                    System.out.println("Les modules sont les suivants:");
                    modules = moduleService.listerModules();
                    for (Module mod : modules) {
                        System.out.println("ID: " + mod.getId() + " - Nom: " + mod.getNom());
                    }
                    System.out.println("Entrer l'ID du module:");
                    int moduleId = sc.nextInt();
                    sc.nextLine(); 

                    Cours cours = new Cours();
                    cours.setDate(date);
                    cours.setHeureDebut(heureDebut);
                    cours.setHeureFin(heureFin);

                    Professeur professeurChoisi = professeurService.getProfesseurById(professeurId);
                    cours.setProfesseur(professeurChoisi);

                    Module coursModule = moduleService.getModuleById(moduleId);
                    cours.setModule(coursModule);

                    coursService.ajouterCours(cours);
                    System.out.println("Cours créé avec succès.");
                    break;
   
                   

                case 4:
                    System.out.println("Tous les cours:");
                    List<Cours> coursList = coursService.listerTousLesCours();
                    for (Cours c : coursList) {
                        System.out.println("ID: " + c.getId() + " - Date: " + c.getDate() +
                                " - Heure Debut: " + c.getHeureDebut() + " - Heure Fin: " + c.getHeureFin());
                    }
                    break;

                case 5:
                    System.out.println("Entrer l'ID du module:");
                    int modId = sc.nextInt();
                    System.out.println("Entrer l'ID du professeur:");
                    int profId = sc.nextInt();
                    List<Cours> coursByModAndProf = coursService.listerCoursParModuleEtProfesseur(modId, profId);
                    for (Cours c : coursByModAndProf) {
                        System.out.println("ID: " + c.getId() + " - Date: " + c.getDate() +
                                " - Heure Debut: " + c.getHeureDebut() + " - Heure Fin: " + c.getHeureFin());
                    }
                    break;

                case 6:
                    System.out.println("***************Merci***************");
                    break;
            }
        } while (choix != 6);

        sc.close();
    }
}
