package domain;

import users.Admin;

public class RepeatErrorProcessResolution implements ErrorProcessResolution {
	
	public void errorResolution(String result,Admin admin, Runnable process){
		String resultValidate=result;
		if (resultValidate=="Error"){
			int index=0;
				while( index < 3 && resultValidate=="Error"){
				
					
					
				}
		}
	}
}
