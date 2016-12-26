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

    public UsuarioService() {
        this.users = new HashMap<>();
    }



    public int existeUsuario2(String user, String password) {
      if(adminDAO.filterAdmin(user,password)){
          return 2;
      }
      if(terminalDao.filterTerminals(user,password)){
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

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public TerminalDao getTerminalDao() {
        return terminalDao;
    }

    public void setTerminalDao(TerminalDao terminalDao) {
        this.terminalDao = terminalDao;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}