package dao;

import poi.CGP;

import javax.persistence.EntityManager;

public class CGPDao extends GenericDao<CGP, Long> {

    public CGPDao(EntityManager entityManager) {
        super(entityManager);
    }

}
