package domain;

import dao.EntityManagerProvider;
import dao.UserDao;
import internalService.PoiService;

import java.util.List;
import java.util.stream.Collectors;

import users.Terminal;
import users.User;

import javax.persistence.EntityManager;

public class ProcessSearchTerminal implements ProcessSearchInterfaz{
	private EntityManager entityManager= EntityManagerProvider.getInstance().getEntityManager();
	private	UserDao userDao= new UserDao(entityManager);

	public List<User> search(String nombre){
		return userDao.searchTerminals(nombre);
	}


}
