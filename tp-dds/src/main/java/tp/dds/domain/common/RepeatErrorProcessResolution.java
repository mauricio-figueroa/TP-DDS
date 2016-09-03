package tp.dds.domain.common;

import java.util.concurrent.Callable;

import tp.dds.domain.users.Admin;

public class RepeatErrorProcessResolution implements ErrorProcessResolution {
	
	public void errorResolution(String result,Admin admin, Callable<String> process){
		String resultValidate=result;
		if (resultValidate=="Error"){
			int index=0;
				while( index < 3 && resultValidate=="Error"){
					
					try {
						System.out.println("INTENTO NUMERO " +index );
						resultValidate= process.call();
						index++;
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
		}
	}
}
