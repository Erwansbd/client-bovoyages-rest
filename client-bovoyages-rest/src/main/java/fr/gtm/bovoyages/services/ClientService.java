package fr.gtm.bovoyages.services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.entities.Voyageur;

public class ClientService {
	
	
		private static final String WEB_APPLI ="http://localhost:9080/bovoyages-ws";
		
		
//		public String[] getAllRegions() {
//	String uri= WEB_APPLI + "/rest/destinations/all/regions";
//			Client client = ClientBuilder.newClient();
//			WebTarget target = client.target(uri);
//			
//			String[] regions  = target.request("application/json;charset=utf-8").get(String[].class);
//            return regions;
//		}
		
		public static List<Destination> getValidDestinations(){
	String uri= WEB_APPLI + "/rest/destinations/all/valid";
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(uri);
			Destination[] destinations = target.request("application/json;charset=utf-8").get(Destination[].class);
			return Arrays.asList(destinations);
		}
		
		public static final List<DatesVoyage> getValidDatesVoyagesByDestinationId(long id) {
			
	String uri= WEB_APPLI + "/rest/destinations/"+id+"/dates/valid";
			
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target(uri);
			DatesVoyage[] datesvoyages = target.request("application/json;charset=utf-8").get(DatesVoyage[].class);
			return Arrays.asList(datesvoyages);
		}
		
	  public static final Voyage submitVoyage(String region, String descriptif, List<Voyageur> voyageurs, fr.gtm.bovoyages.entities.Client client, DatesVoyage datesVoyage) {
		 Voyage voyage = new Voyage(region, descriptif, client, voyageurs, datesVoyage);
		  String uri= WEB_APPLI +"/rest/destinations/voyage/add";
		  javax.ws.rs.client.Client client2 = ClientBuilder.newClient();
			WebTarget target = client2.target(uri);
			Voyage voyageConf = target.request("application/json;charset=utf-8").post(Entity.json(voyage), Voyage.class);
            return voyageConf;
	  }
	  
	
	  
	  public static final  Voyage getVoyageById(long id) {
		  String uri= WEB_APPLI + "/rest/destinations/voyage/"+id;
		  		
		  javax.ws.rs.client.Client client = ClientBuilder.newClient();
		  		WebTarget target = client.target(uri);
		  		
		  		Voyage voyage = target.request("application/json;charset=utf-8").get(Voyage.class);
		  		return voyage;
		  	}
	  
	  /**
	 *a modifier
	 */
	public static final void updateVoyage( Long id, List<Voyageur> voyageurs) {
		Voyage voyage = getVoyageById(id);
		voyage.setVoyageurs(voyageurs);
		  String uri= WEB_APPLI +"/rest/destinations/voyage/edit";
		  Client client = ClientBuilder.newClient();
			WebTarget target = client.target(uri);
			Voyage v = target.request("application/json;charset=utf-8").post(Entity.json(voyage), Voyage.class);
	  }
	  
public static final List<Voyage> getAllVoyages() {
	String uri= WEB_APPLI +"/rest/destinations/voyage/all";
	  Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
	Voyage [] voyages = target.request("application/json;charset=utf-8").get(Voyage[].class);
	return Arrays.asList(voyages);
	
}

	public static  String[] getAllRegions() {
		
		String uri= WEB_APPLI + "/rest/destinations/all/regions";
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		
		String[] regions  = target.request("application/json;charset=utf-8").get(String[].class);
        return regions;
	}
}



