import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import fr.gtm.bovoyages.entities.Client;
import fr.gtm.bovoyages.entities.DatesVoyage;
import fr.gtm.bovoyages.entities.Destination;
import fr.gtm.bovoyages.entities.Voyage;
import fr.gtm.bovoyages.entities.Voyageur;
import fr.gtm.bovoyages.services.ClientService;


public class ClientServiceTest {


		@Test
		public void  getAllRegions() {
		String [] regions = fr.gtm.bovoyages.services.ClientService.getAllRegions();
		assertNotNull(regions);
		assertTrue(regions.length>0);
		}
		
		@Test
		public void getValidDestinations(){
			List<Destination> destinations = ClientService.getValidDestinations();
			assertNotNull(destinations);
			assertTrue(destinations.size()>0);
		}
		
		@Test
		public void getValidDatesVoyagesByDestinationId() {
			long id =4;
			List<DatesVoyage> datesvoyages = ClientService.getValidDatesVoyagesByDestinationId(id);
			assertNotNull(datesvoyages);
			assertTrue(datesvoyages.size()>0);
		}
		
		@Test
		public void submitVoyage() {
			//Test avec un requete acceptable par le serveur
		Client client = new Client("test");
		List<Voyageur> voyageurs = new ArrayList<Voyageur>();
		for (int i=0; i<=2; i++ ) {
		 Voyageur voyageur = new Voyageur(String.valueOf(i), String.valueOf(i), String.valueOf(i), new Date(90, 0, 1, 20, 20, 20));
		 voyageurs.add(voyageur);
			}
		DatesVoyage dv = new DatesVoyage(new Date(100, 5, 14, 0, 0, 0), new Date(100, 6, 14, 0, 0, 0),10);
		ClientService.submitVoyage("test", "ceci est un test", voyageurs, client, dv);
		List<Voyage> voyages = ClientService.getAllVoyages();
		Voyage voyageTest = voyages.get(voyages.size() - 1);
		String descriptifTest = voyageTest.getDescriptif();
		String regionTest = voyageTest.getRegion();
		List<Voyageur> voyageursTest = voyageTest.getVoyageurs();
		Client clientTest = voyageTest.getClient();
		assertEquals("test",regionTest);
		assertEquals("ceci est un test",descriptifTest);
		assertEquals(voyageurs,voyageTest);
		assertEquals(client,clientTest);
		//Test avec un requete NON acceptable par le serveur
		Client client2 = new Client("test");
		List<Voyageur> voyageurs2 = new ArrayList<Voyageur>();
		for (int i=0; i<=10; i++ ) {
		 Voyageur voyageur = new Voyageur(String.valueOf(i), String.valueOf(i), String.valueOf(i), new Date(90, 0, 1, 20, 20, 20));
		 voyageurs2.add(voyageur);
			}	
		ClientService.submitVoyage("test", "ceci est un test", voyageurs2, client2, dv);
		List<Voyage> voyages2 = ClientService.getAllVoyages();
		Voyage v2 = voyages.get(voyages.size() - 1);
		assertNull(v2);
		}
		
		@Test
		public void getVoyageById() {
			long id=1;
			Voyageur voyageur = new Voyageur("M", "Doe", "John", new Date(119, 11, 27, 0, 0, 0));
			List<Voyageur> voyageurs = new ArrayList<Voyageur>();
			voyageurs.add(voyageur);
			voyageurs.add(voyageur);
			Client client = new Client("moi");
			Voyage voyage = ClientService.getVoyageById(id);
			String descriptifTest = voyage.getDescriptif();
			String regionTest = voyage.getRegion();
			List<Voyageur> voyageursTest = voyage.getVoyageurs();
			Client clientTest = voyage.getClient();
			assertNotNull(voyage);
			assertEquals("test",regionTest);
			assertEquals("ceci est un test",descriptifTest);
			assertEquals(voyageurs.size(), voyageursTest.size());
			assertEquals(voyageurs.get(0).getNom(), voyageursTest.get(0).getNom());
			assertEquals(client.getNom(),clientTest.getNom());
		}
		
		@Test
		public void updateVoyage() {
			long id =1;
			Voyageur voyageur = new Voyageur("M", "Test", "Test", new Date(119, 11, 27, 0, 0, 0));
			List<Voyageur> voyageurs = new ArrayList<Voyageur>();
			voyageurs.add(voyageur);
			voyageurs.add(voyageur);
			Voyage v1 = ClientService.getVoyageById(id);
			ClientService.updateVoyage(id, voyageurs);
			Voyage v2 = ClientService.getVoyageById(id);
			List<Voyageur> voyageursTest = v2.getVoyageurs();
			assertNotEquals(v1.getVoyageurs(),voyageursTest);
			Voyageur voyageurFinal = new Voyageur("M", "Doe", "John", new Date(119, 11, 27, 0, 0, 0));
			List<Voyageur> voyageursFinal = new ArrayList<Voyageur>();
			voyageursFinal.add(voyageurFinal);
			voyageursFinal.add(voyageurFinal);
			ClientService.updateVoyage(id, voyageursFinal);		
		}
		
		
	}


