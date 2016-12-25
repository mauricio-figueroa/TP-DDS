package internalService;

import dao.AdminDAO;
import dao.EntityManagerProvider;
import dao.TerminalDao;
import org.springframework.stereotype.Component;
import users.Admin;
import users.Terminal;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;

@Component
public class UsuarioService {
    EntityManager entityManager = EntityManagerProvider.getInstance().getEntityManager();


    private AdminDAO adminDAO=new AdminDAO(entityManager);
    private TerminalDao terminalDao=new TerminalDao(entityManager);

    private Map<String, String> users;


    public List<Admin> getAdmins(){

        return null;
    }

    public UsuarioService() {
        this.users = new HashMap<>();
    }


    public void addUser(String name, String password) {
        this.users.put(name, password);
    }

/*    public boolean existeUsuario(String user, String password) {
        try {
            return this.users.get(user).equalsIgnoreCase(password) ? true : false;
        } catch (NullPointerException e) {
            return false;
        }
    }*/


    public int existeUsuario2(String user, String password) {
      if(adminDAO.filterAdmin(user,password)){
          return 2;
      }
      if(terminalDao.filterTerminals(user,password)){
          return 1;
      }
      return 0;
    }








}