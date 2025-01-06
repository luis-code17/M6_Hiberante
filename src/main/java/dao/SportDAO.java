package dao;

import model.Sport;
import org.hibernate.SessionFactory;

import java.util.ArrayList;


public class SportDAO {

    SessionFactory sessionFactory;

    public SportDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ArrayList<Sport> getAll(){
        ArrayList<Sport> sports = new ArrayList<>();
        sessionFactory.inTransaction(session -> {
            session.createSelectionQuery("from Sport", Sport.class)
                    .getResultList()
                    .forEach(sports::add);
        });
        return sports;
    }

    public void add(Sport sport){
        sessionFactory.inTransaction(session -> {
            session.save(sport);
            System.out.println("Deporte insertado correctamente.");
        });
    }

    public boolean exists(Sport sport){
        boolean result = false;
        ArrayList<Sport> sports = getAll();
        for (Sport s : sports) {
            if (s.getName().equals(sport.getName())) {
                result = true;
            }
        }
        return result;
    }


}
