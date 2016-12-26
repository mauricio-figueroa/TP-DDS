package testService;

import controller.controllers.AdminController;
import controller.controllers.TerminalController;
import dao.*;
import domain.*;
import internalService.PoiService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import poi.*;
import users.Admin;
import users.Terminal;
import users.User;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoTest {
    private BankDao bankDao;
    private CGPDao cgpDao;
    private BusDao busDao;
    private EntityManager entityManager;
    private List<CGPService> cgpServices;
    private Bank bank;
    private Schedule schedule;
    private Schedule schedule2;
    private List<Schedule> schedules;

    @Before
    public void setup() {
        this.entityManager = EntityManagerProvider.getInstance().getEntityManager();
        this.bank = new Bank("Banco", new Address("corrientes"), new Coordinate(127.4, 125.6), "extraccion moneda");
        this.cgpServices = new ArrayList<>();
        this.schedule = new Schedule("04:30", "04:55");
        this.schedule2 = new Schedule("04:40", "04:55");
        this.schedules = new ArrayList<>();

        this.bankDao = new BankDao(entityManager);
        this.cgpDao = new CGPDao(entityManager);
        this.busDao = new BusDao(entityManager);
    }

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
        String nameTerminal = "TerminalTest";
        Terminal terminal = new Terminal(nameTerminal, "asda", corCoordinate, null,"TERMINAL");
        poiService.addTerminal(terminal);


        TerminalController terminalController = new TerminalController();
        terminalController.searchPoiFrom(Arrays.asList(name), nameTerminal);
        terminal.setPoiService(poiService);

        Search search = searchDao.getById(1l);


        Assert.assertEquals(search.getPoiDTOs().get(0).getName(), bank.getName());
        Assert.assertEquals(search.getPoiDTOs().get(1).getName(), bank2.getName());

    }


    @Test
    public void persistirUsuario() {
        UserDao userDao = new UserDao(entityManager);
        AdminController adminCon = new AdminController();
        ResponseEntity response = adminCon.registerAdmin("gabo1", "123", "gabriel.dyck@gmail.com");
        long id = (long) response.getBody();
        User admin = userDao.getById(id);
        admin.setNombre("f3r");
        userDao.saveOrUpdate(admin);
        admin = userDao.getById(id);
        Assert.assertTrue(admin.getNombre().equalsIgnoreCase("f3r"));
    }


    @Test
    public void persistirUsuario2() {
        UserDao userDao = new UserDao(entityManager);
        AdminController adminCon = new AdminController();
        ResponseEntity response = adminCon.registerAdmin("gabo", "123", "gabriel.dyck@gmail.com");
        long id = (long) response.getBody();
        User admin=new User(null,"mauri","mauripw","mauri@gmial.com","Mauri");

        User admin2= userDao.saveOrUpdate(admin);
    }


    @Test
    public void peristirTerminal() {
        UserDao userDao = new UserDao(entityManager);
        User terminal=new User("terminal1","terminal1",new Coordinate(),null);

        User terminal2= userDao.saveOrUpdate(terminal);
    }


    @Test
    public void test(){
        EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();
        UserDao userDao = new UserDao(entityManager);
        AdminController adminCon = new AdminController();
        User admin=new User(null,"mauri1","123","mauri@gmial.com","Mauri");
        User admin2=new User(null,"mel","123","mel@gmial.com","Mel");
        User admin3=new User(null,"gabi","123","gaby@gmial.com","Gabi");
        User admin4=new User(null,"juani","123","juani@gmial.com","Juani");
        User terminal=new User("terminalMauri1","terminal1",new Coordinate(),null);
        User terminal2=new User("terminalMel1","terminal2",new Coordinate(),null);
        User terminal3=new User("terminalGabi1","terminal3",new Coordinate(),null);
        User termina4=new User("terminalMel2","terminal4",new Coordinate(),null);
        userDao.saveOrUpdate(admin);
        userDao.saveOrUpdate(admin2);
        userDao.saveOrUpdate(admin3);
        userDao.saveOrUpdate(admin4);
        userDao.saveOrUpdate(terminal);
        userDao.saveOrUpdate(terminal2);
        userDao.saveOrUpdate(terminal3);
        userDao.saveOrUpdate(termina4);

    }



}





