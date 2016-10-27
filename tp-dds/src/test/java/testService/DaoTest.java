package testService;

import domain.Address;
import domain.Coordinate;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import poi.Bank;
import poi.Poi;

import javax.persistence.EntityManager;

public class DaoTest {


    EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    Poi roboAPersistir = new Bank("Banco1", new Address("corrientes"), new Coordinate(127.4, 125.6), "extraccion moneda");


}
