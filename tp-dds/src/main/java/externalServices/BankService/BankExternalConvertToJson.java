package externalServices.BankService;

import java.util.ArrayList;
import java.util.List;

import Dto.bankDto.BankDTO;
import domain.Address;
import domain.Coordinate;
import org.springframework.stereotype.Component;
import poi.Bank;

@Component
public class BankExternalConvertToJson {
	
	public List<Bank> convertToDomain( List<BankDTO> response){
		
		List<Bank> banks = new ArrayList<Bank>();
		Bank bank;
		for(BankDTO currentBank : response){
			bank = new Bank(currentBank.getBanco(), new Address(currentBank.getSucursal()), new Coordinate(currentBank.getX(),currentBank.getY()));
			banks.add(bank);
		}
		
		return banks;
		
	}

}
