package dao;

import users.Terminal;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalDao  extends GenericDao<Terminal,Long> {

    public TerminalDao(EntityManager entityManager) {
        super(entityManager);
    }

    public boolean filterTerminals(String user,String pw){
        TerminalDao temrminalDao=new TerminalDao(entityManager);
        List<Terminal> terminales= temrminalDao.getAll();
        return (terminales.stream().filter(x->x.getNombre().equalsIgnoreCase(user)
                &&x.getContrasenia().equalsIgnoreCase(pw)).collect(Collectors.toList())).size()>0;
    }



}
