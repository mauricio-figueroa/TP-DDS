package internalService;

import dao.EntityManagerProvider;
import dao.UserDao;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class UsuarioService {
    EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();


    UserDao userDAO= new UserDao(entityManager);

    private Map<String, String> users;

    public UsuarioService() {
        this.users = new HashMap<>();
    }



    public int existeUsuario2(String user, String password) {
      if(userDAO.filterAdmin(user,password)){
          return 2;
      }
      if(userDAO.filterTerminals(user,password)){
          return 1;
      }
      return 0;
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserDao getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}