package tp.dds.domain.common;

import java.util.concurrent.Callable;

import tp.dds.domain.users.Admin;

public class NoneErrorProcessResolution implements ErrorProcessResolution {
	
	public void errorResolution(String result,Admin admin,Callable<String> run){
		
	}

}
