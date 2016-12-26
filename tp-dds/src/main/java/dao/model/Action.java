package dao.model;

import users.Admin;
import users.Terminal;

import javax.persistence.*;
import java.util.Date;

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

    @Column
    private Date updateDate;
    public Action(String action) {
        this.action = action;
        this.updateDate= new Date();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
