package users;

import dao.model.Action;
import domain.Coordinate;

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

    @Column(name="TYPE")
    private String type;

    @Column(name="MAIL")
    private String mail;

    @Column(name="RESOLUTION_TYPE")
    private String resolutionType;



    public User() {
    }

    public User(String nombre, String contrasenia, List<Action> actions, String type) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.actions = actions;
        this.type = type;
    }

    public User(List<Action> actions, String user, String pw, String mail, String resolution){
        this.actions=actions;
        this.nombre=user;
        this.contrasenia=pw;
        this.mail=mail;
        this.resolutionType=resolution;
        this.type="ADMIN";
    }

    public User(String nombre, String contrasenia, Coordinate coordinate, List<Action> actions){
        this.nombre=nombre;
        this.contrasenia=contrasenia;
        this.actions=actions;
        this.type="TERMINAL";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(String resolutionType) {
        this.resolutionType = resolutionType;
    }
}
