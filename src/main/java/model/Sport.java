package model;
import jakarta.persistence.*;

@Entity
@Table(name = "deportes")
public class Sport {

    @Column(name = "nombre", length = 50)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod")
    private int code;

    // Constructor sin argumentos (obligatorio para Hibernate)
    public Sport() {

    }

    public Sport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}