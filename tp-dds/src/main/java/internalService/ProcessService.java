package internalService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.sun.deploy.util.StringUtils;
import org.apache.http.client.ClientProtocolException;

import json.JsonFactory;
import Dto.bankDto.BankDTO;
import Dto.trashPoi.TrashPoi;

import com.fasterxml.jackson.core.type.TypeReference;

import poi.ComercialShop;
import poi.Poi;
import reader.URLReader;
import users.Admin;
import users.Terminal;
import domain.Address;
import domain.ProcessSearchAdmin;
import domain.ProcessSearchInterfaz;
import domain.ProcessSearchTerminal;
import domain.ProcessStory;
import externalServices.BankService.BankService;

public class ProcessService {


	private static List<ProcessStory> processStories;
	private static PoiService poiService;
	private static ProcessService instance;
	private static JsonFactory jsonFactory;
	private static URLReader urlReader;
	
	
	public static ProcessService getInstance() {
		if (instance == null) {
			instance = new ProcessService();
			processStories= new ArrayList<ProcessStory>();
			poiService = PoiService.getInstance();
			jsonFactory= JsonFactory.getInstance();
			urlReader = new URLReader();
		}
		return instance;
	}
	
	public ProcessService(){
		
	}
	

	public String updateComercialShops(String path,Admin admin){
		Calendar initDate= Calendar.getInstance();
		Calendar endDate=null;
		String processName="updateCommecial";
		String userRan= admin.getNombre();
		String result=null;
		String errorMessage=null;

		try {
			BufferedReader reader= new BufferedReader(new FileReader((path)));
			String line;

			while((line=reader.readLine()) != null)
			{
				System.out.println(line);

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
				endDate=Calendar.getInstance();
				result="SUCCESS";
			}
		} catch (IOException e) {

			e.printStackTrace();
			endDate=Calendar.getInstance();
			result="Error";
			errorMessage=e.getMessage();
			
		}
		ProcessStory story = new ProcessStory(initDate, endDate, processName, userRan, result, errorMessage);
		processStories.add(story);
		return result;


	}

	public String turnOffAPoi(Admin admin){
		System.out.println("aaaaa");
		Calendar initDate= Calendar.getInstance();
		Calendar endDate=null;
		String processName="turnOffAPoi";
		String userRan= admin.getNombre();
		String result=null;
		String errorMessage=null;

		
		Random random= new Random();
		int randomNumber= random.nextInt(10);
		String url;
		System.out.println(randomNumber);
		if (randomNumber<0){
			System.out.println("aavaba");
			url= "http://demo3537367.mockable.io/trash/pois";
		}else{
			url= "http://demo3537367.mockable.io/trash/pois_bad";
		}
		List<TrashPoi> poisToTurnOff;
		try {
			System.out.println(url);
			poisToTurnOff = jsonFactory.fromJson(urlReader.getStringFromURL(url),
						new TypeReference<ArrayList<TrashPoi>>() {});

			poisToTurnOff.forEach(poi_trashed -> poiService.getAllPois().forEach(poi ->{ 
			
			if(poi.getId()==poi_trashed.getId()){
				poi.setActived(false);
			}
		
			}));
			endDate=Calendar.getInstance();
			result="SUCCESS";
		} catch (IOException e ) {
			System.out.println("AAA");
			e.printStackTrace();
			endDate=Calendar.getInstance();
			errorMessage=e.getMessage();
			result="Error";
			
		}
		ProcessStory story = new ProcessStory(initDate, endDate, processName, userRan, result, errorMessage);
		processStories.add(story);
		return result;
		
		
	}

	public String addActionsToUser(String nombre,String type,List<String> actions,Admin admin){
		Calendar initDate= Calendar.getInstance();
		Calendar endDate=null;
		String processName="AddActions";
		String userRan= admin.getNombre();
		String result=null;
		String errorMessage=null;
		
		System.out.println("ADD");
		if (type =="Terminal"){
			ProcessSearchInterfaz searchTerminal= new ProcessSearchTerminal();
			List<Terminal> terminales = (List<Terminal>) searchTerminal.search(nombre);
			terminales.get(0).getActions().add( actions);
		}else{
			ProcessSearchInterfaz searchAdmin= new ProcessSearchAdmin();
			List<Admin> admins =(List<Admin>) searchAdmin.search(nombre);
			admins.get(0).getActions().add(StringUtils.join(actions,","));
		}
		
		endDate=Calendar.getInstance();
		result="SUCCESS";
		ProcessStory story = new ProcessStory(initDate, endDate, processName, userRan, result, errorMessage);
		processStories.add(story);
		return result;
		
	}
	
	public static List<ProcessStory> getProcessStories() {
		return processStories;
	}

	public static void setProcessStories(List<ProcessStory> processStories) {
		ProcessService.processStories = processStories;
	}

	public static PoiService getPoiService() {
		return poiService;
	}

	public static void setPoiService(PoiService poiService) {
		ProcessService.poiService = poiService;
	}

	public static void setInstance(ProcessService instance) {
		ProcessService.instance = instance;
	}

	public String undoAddActionToUser(String nombre,String type,Admin admin){
		Calendar initDate= Calendar.getInstance();
		Calendar endDate=null;
		String processName="AddActions";
		String userRan= admin.getNombre();
		String result=null;
		String errorMessage=null;
		
		System.out.println("UNDO AAAAAAAAADD");
		if (type =="Terminal"){
			ProcessSearchInterfaz searchTerminal= new ProcessSearchTerminal();
			List<Terminal> terminales = (List<Terminal>) searchTerminal.search(nombre);
			int indexToErase=terminales.get(0).getActions().size()-1;
			terminales.get(0).getActions().remove(indexToErase);
			
	}else{
		ProcessSearchInterfaz searchAdmin= new ProcessSearchAdmin();
		List<Admin> admins =(List<Admin>) searchAdmin.search(nombre);
		int indexToErase=admins.get(0).getActions().size()-1;
		admins.get(0).getActions().remove(indexToErase);
	}
		endDate=Calendar.getInstance();
		result="SUCCESS";
		ProcessStory story = new ProcessStory(initDate, endDate, processName, userRan, result, errorMessage);
		processStories.add(story);
		
		
		return result;
		
	}
		
}
