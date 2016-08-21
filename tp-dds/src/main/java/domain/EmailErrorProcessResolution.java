package domain;

import java.util.concurrent.Callable;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import json.JsonFactory;
import observers.busqueda.NotificarAdmin;
import users.Admin;

public class EmailErrorProcessResolution implements ErrorProcessResolution {
	
	public void errorResolution(String result,Admin admin, Callable<String> process){
		try {
			if (result=="Error"){
			NotificarAdmin.processExecutionError(admin.getMail(), process.toString());
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
