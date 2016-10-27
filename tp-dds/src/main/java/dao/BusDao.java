package dao;

import poi.BusStation;

import javax.persistence.EntityManager;

public class BusDao extends GenericDao<BusStation, Long> {

    public BusDao(EntityManager entityManager) {
        super(entityManager);
    }

}
