package dao;

import users.Admin;

import javax.persistence.EntityManager;

/**
 * Created by gabrieldyck on 28/10/16.
 */
public class AdminDAO extends GenericDao<Admin,Long> {

    public AdminDAO(EntityManager entityManager) {
        super(entityManager);
    }

}
