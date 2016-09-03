package tp.dds.services.externalServices.BankService;

import java.util.ArrayList;
import java.util.List;

import tp.dds.api.bankDto.BankDTO;
import tp.dds.domain.common.Address;
import tp.dds.domain.common.Coordinate;
import tp.dds.domain.poi.Bank;

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
