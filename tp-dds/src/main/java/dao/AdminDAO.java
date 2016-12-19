package dao;

import users.Admin;
import users.Terminal;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class AdminDAO extends GenericDao<Admin,Long> {

    public AdminDAO(EntityManager entityManager) {
        super(entityManager);
    }


    public boolean filterAdmin(String user,String pw){
        AdminDAO adminDAO=new AdminDAO(entityManager);
        List<Admin> admin= adminDAO.getAll();
        return (admin.stream().filter(x->x.getNombre().equalsIgnoreCase(user)
                &&x.contrasenia.equalsIgnoreCase(pw)).collect(Collectors.toList())).size()>0;
    }

}
