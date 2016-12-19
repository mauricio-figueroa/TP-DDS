package dao;

import users.Terminal;

import javax.persistence.EntityManager;

public class TerminalDao  extends GenericDao<Terminal,Long> {

    public TerminalDao(EntityManager entityManager) {
        super(entityManager);
    }
}
