package testService;

import controller.controllers.AdminController;
import controller.controllers.TerminalController;
import dao.*;
import domain.*;
import internalService.PoiService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import poi.*;
import users.Admin;
import users.Terminal;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class DaoTest {

    EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();
    Bank bank = new Bank("Banco", new Address("corrientes"), new Coordinate(127.4, 125.6), "extraccion moneda");
    List<CGPService> cgpServices = new ArrayList<>();
    Schedule schedule = new Schedule("04:30", "04:55");
    Schedule schedule2 = new Schedule("04:40", "04:55");
    List<Schedule> schedules = new ArrayList<>();

    BankDao bankDao = new BankDao(entityManager);
    CGPDao cgpDao = new CGPDao(entityManager);
    BusDao busDao = new BusDao(entityManager);

    @Test
    public void testBank() {
        bankDao.saveOrUpdate(bank);
    }

    @Test
    public void testCgp() {
        schedules.add(schedule);
        schedules.add(schedule2);
        CGPService cgpService1 = new CGPService("venta de falopa", new RangeOfAtention(schedules, null));
        cgpServices.add(cgpService1);
        CGP cgp = new CGP("CGP1", new Address("calle falsa123"), new Coordinate(120.3, 125.96), 129.2, cgpServices);
        cgpDao.saveOrUpdate(cgp);
    }

    @Test
    public void testBus() {
        BusStation busStation = new BusStation("unaParada", new Address("calle falsa123"), new Coordinate(120.3, 125.96), 130);
        busDao.saveOrUpdate(busStation);
    }

    @Test
    public void modificarPoi() {
        long id = bankDao.saveOrUpdate(bank).getId();


        Bank bankModi = bankDao.getById(id);
        Coordinate corCoordinate = new Coordinate(1234.5, 1234.5);
        bankModi.setCoordinate(corCoordinate);

        id = bankDao.saveOrUpdate(bankModi).getId();
        bankModi = bankDao.getById(id);

        Assert.assertEquals(bankModi.getCoordinate().getLatitude(), corCoordinate.getLatitude(), 0.1);
        Assert.assertEquals(bankModi.getCoordinate().getLongitude(), corCoordinate.getLongitude(), 0.1);
    }


    @Test
    public void eliminarPoi() {
        long id = bankDao.saveOrUpdate(bank).getId();
        Bank bankModi = bankDao.getById(id);
        bankDao.remove(bankModi);

        Assert.assertNull(bankDao.getById(id));
    }


    //3. Realizar una búsqueda, persistirla, recuperarla y verificar que
    // //corresponda al objeto de esa búsqueda e incluya referencias a los PoI.

    @Test
    public void persistirBusqueda() throws MessagingException, InterruptedException {
        Bank bank2 = new Bank("Banco", new Address("corrientes"), new Coordinate(127.4, 125.6), "extraccion moneda");
        PoiService poiService = PoiService.getInstance();
        poiService.addPoi(bank);
        poiService.addPoi(bank2);
        String name1 = bankDao.saveOrUpdate(bank).getName();
        String name = bankDao.saveOrUpdate(bank2).getName();
        SearchDao searchDao = new SearchDao(entityManager);
        Coordinate corCoordinate = new Coordinate(1234.5, 1234.5);
        PoiService.getInstance();
        String nameTerminal = "TerminalTest";
        Terminal terminal = new Terminal(nameTerminal, corCoordinate, null);

        TerminalController terminalController = new TerminalController();
        terminalController.searchPoiFrom(name, nameTerminal);
        terminal.setPoiService(poiService);

        Search search = searchDao.getById(1l);


        Assert.assertEquals(search.getPoiDTOs().get(0).getName(),bank.getName());
        Assert.assertEquals(search.getPoiDTOs().get(1).getName(),bank2.getName());

    }


    @Test
    public void persistirUsuario(){
        AdminDAO adminDAO = new AdminDAO(entityManager);
        AdminController adminCon= new AdminController();
        ResponseEntity response= adminCon.registerAdmin("gabo","123","gabriel.dyck@gmail.com");
        long id= (long)response.getBody();
        Admin admin= adminDAO.getById(id);
        admin.setNombre("f3r");
        adminDAO.saveOrUpdate(admin);
        admin=adminDAO.getById(id);
        Assert.assertTrue(admin.getNombre().equalsIgnoreCase("f3r"));
    }


}
