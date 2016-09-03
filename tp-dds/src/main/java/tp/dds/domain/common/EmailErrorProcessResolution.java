package tp.dds.domain.common;

import java.util.concurrent.Callable;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import tp.dds.domain.observers.busqueda.NotificarAdmin;
import tp.dds.domain.users.Admin;
import tp.dds.utils.json.JsonFactory;

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
