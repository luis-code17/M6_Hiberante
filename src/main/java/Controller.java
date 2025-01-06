import dao.AthleteDAO;
import dao.SportDAO;
import model.Athlete;
import model.Sport;
import org.hibernate.SessionFactory;
import view.View;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        View view = new View(sc);
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        SportDAO sportDAO = new SportDAO(sessionFactory);
        AthleteDAO athleteDAO = new AthleteDAO(sessionFactory);

        //AthleteDAO athleteDAO = new AthleteDAO();
        int choice = 0;

        do{
            boolean result = false;
            choice = view.printMenu();
            switch (choice) {
                case 1:
                    while (result == false) {
                       String athleteName = view.AthleteForm();
                        ArrayList<Sport> ssports= (ArrayList<Sport>) sportDAO.getAll();
                        int sport = view.AskSport(ssports);
                        Athlete athlete = new Athlete(athleteName, ssports.get(sport));

                        result = athleteDAO.exists(athlete);
                        if (result == false) {
                            athleteDAO.add(athlete);
                            result = true;
                        } else {
                            System.out.println("The sport does not exist. Please enter a different sport.");
                        }
                    }
                    result = false;
                    break;
                case 2:
                    while (result == false) {
                        String name = view.SportForm(sc);
                        Sport sport = new Sport(name);
                        result = sportDAO.exists(sport);
                        if (result) {
                            System.out.println("The sport already exists. Please enter a different sport.");
                        } else {
                          sportDAO.add(sport);
                          result = true;
                        }
                    }
                    result = false;
                    break;
                case 3:
                    ArrayList<Athlete> athletes = (ArrayList<Athlete>) athleteDAO.getAll();
                    view.AthleteList(athletes);
                    break;
                case 4:
                    ArrayList<Sport> sports = sportDAO.getAll();
                    view.SportList(sports);
                    break;
                case 5:
                    ArrayList<Sport> sportss= sportDAO.getAll();
                    int code = view.AskSport(sportss);
                    ArrayList<Athlete> athletesBySport = (ArrayList<Athlete>) athleteDAO.getAthleteBySport(sportss.get(code));
                    view.AthleteList(athletesBySport);
                    break;

                case 6:
                    String name = view.AskName();
                    ArrayList<Athlete> athletess = (ArrayList<Athlete>) athleteDAO.getAthletesByPartialName(name);
                    if (athletess != null) {
                        view.AthleteList(athletess);
                    } else {
                        System.out.println("No athletes found with that name.");
                    }
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    break;
            }
        }while (choice != 7);


    }


}