package testService;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import internalService.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import poi.Bank;

public class PoiExternalServiceTest {

	@Autowired
	PoiService poiService;
	
	@SuppressWarnings("deprecation")
	@Test
	public void getBanksFromExternalServiceTrue(){
		List<Bank> banks= poiService.getBanksFromExternalService("banco","servicio");
		Assert.assertTrue(banks!=null);
	}

}
