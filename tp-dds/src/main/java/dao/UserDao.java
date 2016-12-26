package dao;

import dao.model.Action;
import users.Admin;
import users.Terminal;
import users.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao  extends GenericDao<User,Long> {

    public UserDao(EntityManager entityManager) {
        super(entityManager);
    }

    public boolean filterTerminals(String user,String pw){
        UserDao userDao=new UserDao(entityManager);
        List<User> terminales= userDao.getAll();
        return (terminales.stream().filter(x->x.getNombre().equalsIgnoreCase(user)&&x.getType().equalsIgnoreCase("TERMINAL")
                &&x.getContrasenia().equalsIgnoreCase(pw)).collect(Collectors.toList())).size()>0;
    }

    public List<User> searchTerminals(String user){
        UserDao userDao=new UserDao(entityManager);
        List<User> terminales= userDao.getAll();
        return (terminales.stream().filter(x->x.getNombre().equalsIgnoreCase(user)&&x.getType().equalsIgnoreCase("TERMINAL")
               ).collect(Collectors.toList()));
    }


    public boolean filterAdmin(String user,String pw){
        UserDao userDao=new UserDao(entityManager);
        List<User> admin= userDao.getAll();
        return (admin.stream().filter(x->x.getNombre().equalsIgnoreCase(user)&&x.getType().equalsIgnoreCase("ADMIN")
                &&x.getContrasenia().equalsIgnoreCase(pw)).collect(Collectors.toList())).size()>0;
    }

    public User getAdminByName(String user){
        UserDao userDao=new UserDao(entityManager);
        List<User> admin= userDao.getAll();
        return (admin.stream().filter(x->x.getNombre().equalsIgnoreCase(user)&&x.getType().
                equalsIgnoreCase("ADMIN")).collect(Collectors.toList())).get(0);
    }

    public User saveAdmin(List<Action> actions, String user, String pw, String mail, String resolution){
        User admin= new User(actions,user,pw,mail,resolution);
        return saveOrUpdate(admin);
    }



}
