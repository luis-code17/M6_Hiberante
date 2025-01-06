package dao;
import model.Athlete;
import model.Sport;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteDAO implements DAO<Athlete> {
    SessionFactory sessionFactory;

    public AthleteDAO( SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Athlete item) {
        sessionFactory.inTransaction(session -> {
            session.save(item);
            System.out.println("Deportista añadido correctamente");
        });
    }

    public boolean exists(Athlete item) {
        boolean result = false;
        ArrayList<Athlete> athletes = (ArrayList<Athlete>) getAll();
        for (Athlete a : athletes) {
            if (a.getName().equals(item.getName())) {
                result = true;
            }
        }
        return result;
    }

    public List<Athlete> getAll() {
        List<Athlete> athletes = new ArrayList<>();
        sessionFactory.inTransaction(session -> {
            session.createSelectionQuery("from Athlete", Athlete.class)
                    .getResultList()
                    .forEach(athletes::add);
        });
        return athletes;
    }

    public List<Athlete> getAthleteBySport(Sport sport) {
        List<Athlete> athletes = new ArrayList<>();
        sessionFactory.inTransaction(session -> {
            List<Athlete> result = session.createQuery("FROM Athlete WHERE sport = :sport", Athlete.class)
                    .setParameter("sport", sport)
                    .getResultList();
            athletes.addAll(result);
        });
        return athletes;
    }


    public List<Athlete> getAthletesByPartialName(String partialName) {
        List<Athlete> athletes = new ArrayList<>();
        sessionFactory.inTransaction(session -> {
            List<Athlete> result = session.createQuery("FROM Athlete WHERE name LIKE :partialName", Athlete.class)
                    .setParameter("partialName", "%" + partialName + "%")
                    .getResultList();
            athletes.addAll(result);
        });
        return athletes;
    }




/*

public List<Athlete> getAthleteBySport(int codeSport) {
    List<Athlete> athletes = new ArrayList<>();
    String query = "SELECT * FROM listar_deportistas_por_deporte(?);";

    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, codeSport);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("nombre_deportista");
            int code = resultSet.getInt("cod");
            String sportName = resultSet.getString("nombre_deporte");

            Athlete athlete = new Athlete(name, code, codeSport, sportName);
            athletes.add(athlete);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return athletes;
}



public String getSportName(int codeSport) {
    String query = "SELECT nombre FROM deportes WHERE cod = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, codeSport);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {  // Verifica si hay un resultado
            return rs.getString(1);  // Retorna el nombre del deporte
        } else {
            // Si no hay resultados, retorna null o un mensaje adecuado
            return null;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}

public ArrayList<Athlete> getAthletesByPartialName(String partialName) {
    String query = "SELECT d.cod, d.nombre, d.cod_deporte, ds.nombre AS sport_name " +
            "FROM deportistas d " +
            "JOIN deportes ds ON d.cod_deporte = ds.cod " +
            "WHERE d.nombre ILIKE ?;";

    ArrayList<Athlete> athletes = new ArrayList<>();
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        // Usamos % para permitir búsqueda parcial
        stmt.setString(1, "%" + partialName + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int code = rs.getInt("cod");
            String athleteName = rs.getString("nombre");
            int codeSport = rs.getInt("cod_deporte");
            String sportName = rs.getString("sport_name");

            athletes.add(new Athlete(athleteName, code, codeSport, sportName));
        }

        if (athletes.isEmpty()) {
            System.out.println("No se encontraron deportistas.");
        }
        return athletes;
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
*/
}