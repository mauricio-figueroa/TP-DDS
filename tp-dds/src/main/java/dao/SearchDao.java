package dao;

import domain.Search;

import javax.persistence.EntityManager;

public class SearchDao extends GenericDao<Search, Long> {

    public SearchDao(EntityManager entityManager) {
        super(entityManager);
    }

}
