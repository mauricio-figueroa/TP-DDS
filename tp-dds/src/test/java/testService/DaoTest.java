package testService;

import dao.BankDao;
import dao.EntityManagerProvider;
import domain.Address;
import domain.Coordinate;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import poi.Bank;
import poi.Poi;

import javax.persistence.EntityManager;

public class DaoTest {


    //EntityManager entityManager = PerThreadEntityManagers.getEntityManager();

    EntityManager entityManager2= EntityManagerProvider.getInstance().getEntityManager();
    Bank bank = new Bank("Banco1", new Address("corrientes"), new Coordinate(127.4, 125.6), "extraccion moneda");
    BankDao bankDao = new BankDao(entityManager2);


    @Test
    public void test(){
        bankDao.saveOrUpdate(bank);
    }


}
