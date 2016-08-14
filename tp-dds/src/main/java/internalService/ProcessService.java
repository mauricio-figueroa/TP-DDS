package internalService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;

import json.JsonFactory;
import Dto.bankDto.BankDTO;
import Dto.trashPoi.TrashPoi;

import com.fasterxml.jackson.core.type.TypeReference;

import poi.ComercialShop;
import poi.Poi;
import reader.URLReader;
import domain.Address;
import domain.ProcessStory;

public class ProcessService {


	private List<ProcessStory> processStories= new ArrayList<ProcessStory>();
	private PoiService poiService= PoiService.getInstance();


	public void updateComercialShops(String path){

		try {
			BufferedReader reader= new BufferedReader(new FileReader((path)));
			String line;

			while((line=reader.readLine()) != null)
			{

				System.out.println(line);
				boolean alreadyModify=false;
				String [] splited= line.split(";");
				String [] data= splited[1].split(" ");
				List<String> newData= new ArrayList<String>();

				for(String currentString: data){
					newData.add(currentString);
				}

				List <ComercialShop> commercials= poiService.getAllPois().stream()
						.filter(comercial -> comercial.getType()== "ComercialShop")
						.map(poi -> (ComercialShop)poi)
						.collect(Collectors.toList());
				List <ComercialShop> filtererComercialsByName= (List<ComercialShop>) commercials.stream()
						.filter(comercial -> comercial.getName().equalsIgnoreCase(splited[0]))
						.collect(Collectors.toList());


				if (filtererComercialsByName.size()>0){

					filtererComercialsByName.get(0).setData(newData);
				}else{
					ComercialShop newShop= new ComercialShop(splited[0],new Address("aca"),null,null);
					newShop.setData(newData);
					poiService.getAllPois().add(newShop);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void turnOffAPoi() throws ClientProtocolException, IOException{
		JsonFactory jsonFactory = new JsonFactory();
		URLReader urlReader = new URLReader();
		String url= "http://demo3537367.mockable.io/trash/pois";
		List<TrashPoi> pois_to_turn_off= jsonFactory.fromJson(urlReader.getStringFromURL(url),
					new TypeReference<ArrayList<TrashPoi>>() {});

		
		pois_to_turn_off.forEach(poi_trashed -> poiService.getAllPois().stream().filter(poi -> poi.getId()==poi_trashed.getId()));
		
		
		
	}

}
