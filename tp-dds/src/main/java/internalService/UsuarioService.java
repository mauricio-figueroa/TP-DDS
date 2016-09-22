package internalService;

import org.springframework.stereotype.Component;

import java.util.Map;


import java.util.HashMap;
import java.util.Map;

@Component
public class UsuarioService {

    private Map<String, String> users;

    public UsuarioService() {
        this.users = new HashMap<>();
    }


    public void addUser(String name, String password) {
        this.users.put(name, password);
    }

    public boolean existeUsuario(String user, String password) {
        try {
            return this.users.get(user).equalsIgnoreCase(password) ? true : false;
        } catch (NullPointerException e) {
            return false;
        }
    }


}