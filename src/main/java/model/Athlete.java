package model;

import jakarta.persistence.*;

/*
Consideraciones:
- Los nombres de la tabla ("deportistas") y columnas ("cod", "nombre", "cod_deporte") deben coincidir con los de la base de datos.
- La longitud del campo en @Column(name = "nombre") debe ser la misma que en la base de datos (VARCHAR(50)).
*/


@Entity
@Table(name = "deportistas") // Nombre de la tabla en la base de datos
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrementable
    @Column(name = "cod") // Configura la columna "cod"
    private int code;

    @Column(name = "nombre", nullable = false, length = 50) // Configura la columna "nombre"
    private String name;

    @ManyToOne
    @JoinColumn(name = "cod_deporte")
    private Sport sport;

    // Constructor sin argumentos (obligatorio para Hibernate)
    public Athlete() {
    }

    public Athlete(String name, Sport sport) {
        this.name = name;
        this.sport = sport;
    }

    //GETTERS Y SETTERS
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }


    @Override
    public String toString() {
        return "Athlete{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", sport name =" + sport.getName() +
                '}';
    }
}
