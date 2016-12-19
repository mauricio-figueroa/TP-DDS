package controller.controllers;

import internalService.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private UsuarioService usuarioService=new UsuarioService();

    private Map<String, String> users = new HashMap<>();

    @RequestMapping(value = ("/crearUsuario"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity crearUsuario(
            @RequestParam(value = "user", required = true) String user,
            @RequestParam(value = "pw", required = true) String pw) {

        this.users.put(user.toLowerCase(), pw.toLowerCase());

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }




    @RequestMapping(value = ("/validarUsuario"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity validarUsuario(@RequestParam(value = "user", required = true) String user,
                                         @RequestParam(value = "pw", required = true) String pw) {


        return new ResponseEntity<Integer>(this.usuarioService.existeUsuario2(user,pw),HttpStatus.OK);

    }






}
