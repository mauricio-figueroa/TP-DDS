package dao.model;

import users.Admin;
import users.Terminal;

import javax.persistence.*;

/**
 * Created by gabrieldyck on 25/12/16.
 */
@Entity
@Table
public class Action {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String action;

    public Action(String action) {
        this.action = action;
    }

    public Action() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
