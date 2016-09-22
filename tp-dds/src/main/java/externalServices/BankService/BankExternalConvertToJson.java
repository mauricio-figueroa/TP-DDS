package externalServices.BankService;

import java.util.ArrayList;
import java.util.List;

import Dto.bankDto.BankDTO;
import domain.Address;
import domain.Coordinate;
import org.eclipse.jetty.util.StringUtil;
import poi.Bank;

public class BankExternalConvertToJson {
	
	public List<Bank> convertToDomain( List<BankDTO> response){
		
		List<Bank> banks = new ArrayList<Bank>();
		Bank bank;
		for(BankDTO currentBank : response){
			String service=new String();
			for (String currentString:currentBank.getServicios()){
				service=currentString+",";
			}
			bank = new Bank(currentBank.getBanco(), new Address(currentBank.getSucursal()), new Coordinate(currentBank.getX(),currentBank.getY()),service);
			banks.add(bank);
		}
		
		return banks;
		
	}

}