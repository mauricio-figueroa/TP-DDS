package tp.dds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tp.dds.services.externalServices.UsuarioService;


@Controller
public class LoginController {

    private UsuarioService usuarioService=new UsuarioService();




    @RequestMapping(value = ("/crearUsuario"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity addTerminal(@RequestParam(value = "user", required = true) String user,
                                      @RequestParam(value = "pw", required = true) String pw){

        this.usuarioService.addUser(user,pw);
        //return new ResponseEntity<Boolean>(true, HttpStatus.OK);

      return new ResponseEntity<>( HttpStatus.OK);

    }


    @RequestMapping(value = ("/validarUsuario"), method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity validarUsuario(@RequestParam(value = "user", required = true) String user,
                                      @RequestParam(value = "pw", required = true) String pw){

        if(this.usuarioService.existeUsuario(user,pw)){
            return new ResponseEntity<>( HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }


}
