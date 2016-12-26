package users;

import dao.model.Action;

import javax.persistence.*;
import java.util.List;

/**
 * Created by gabrieldyck on 25/12/16.
 */
@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="NOMBRE",unique = true)
    private String nombre;

    @Column(name="CONTRASENIA")
    private String contrasenia;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_action_terminal")
    private List<Action> actions;

    public User() {
    }

    public User(String nombre, String contrasenia, List<Action> actions) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.actions = actions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
