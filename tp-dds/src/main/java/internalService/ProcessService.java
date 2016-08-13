package internalService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import poi.ComercialShop;
import poi.Poi;
import domain.ProcessStory;

public class ProcessService {
	
	
	private List<ProcessStory> processStories= new ArrayList<ProcessStory>();
	private PoiService poiService= PoiService.getInstance();
	
	
	public void updateComercialShops(Path path){
	List<ComercialShop> comercials = (List<ComercialShop>) poiService.getAllPois().stream().filter(poi -> poi.getType()== "ComercialShop");
	try {
		List<String> file= Files.readAllLines(path);
		
		file.forEach(line -> {
			
			boolean alreadyModify=false;
			String [] splited= line.split(";");
			String [] data= splited[1].split(" ");
			List <ComercialShop> filtererComercials= (List<ComercialShop>) comercials.stream().filter(comercial -> comercial.getName().equalsIgnoreCase(splited[0]));
				
			List<String> newData= new ArrayList<String>();
			
				for(String currentString: data){
					newData.add(currentString);
				}
			if (filtererComercials.size()>0){
				
				filtererComercials.get(0).setData(newData);
			}else{
				ComercialShop newShop= new ComercialShop(splited[0],null,null,null);
				newShop.setData(newData);
				poiService.getAllPois().add(newShop);
			}
		});
	} catch (IOException e) {
		e.printStackTrace();
	}

	
	}
	
	
	/*
	 * {
				
				if (comercial.getName().equalsIgnoreCase(splited[0])){
					List<String> newData= new ArrayList<String>();
					
					for(int index=1;index<splited.length-1;index++){
						newData.add(splited[index]);
					}
					comercial.setData(newData);
					
					
				}
				
			}
	 */
	

}
