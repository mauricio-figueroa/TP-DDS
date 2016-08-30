package testService;

import Dto.bankDto.BankDTO;
import dao.EntityManagerProvider;
import domain.Address;
import domain.Coordinate;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import poi.Bank;
import poi.Poi;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class pruebaBD {


    @Test
    public void test1() {
        BankDTO bankDTO=new BankDTO("hola",23.2,23.3,"lalala");

        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
        EntityTransaction tx=entityManager.getTransaction();
        tx.begin();

        entityManager.persist(bankDTO);
        tx.commit();
    }


}
