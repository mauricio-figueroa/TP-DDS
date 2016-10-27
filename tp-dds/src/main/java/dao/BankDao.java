package dao;


import poi.Bank;

import javax.persistence.EntityManager;

public class BankDao extends GenericDao<Bank,Long> {

    public BankDao(EntityManager entityManager) {
        super(entityManager);
    }

}
