package de.hsb.simon.client.net;

import java.net.UnknownHostException;
import java.util.List;

import valueobjects.Person;
import valueobjects.Ware;
import de.hsb.simon.client.ui.gui.SwingLagClientGUI;
import de.hsb.simon.client.ui.tableModels.WarenTableModel;
import de.hsb.simon.commons.ClientInterface;
import de.hsb.simon.commons.ServerInterface;
import de.hsb.simon.commons.SessionInterface;
import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.annotation.SimonRemote;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

@SimonRemote( value = {ClientInterface.class})
public class ClientInterfaceImpl implements ClientInterface{

	private Lookup lookup;
	private ServerInterface server;
	private SessionInterface session;
	private boolean safeLogout = false;
	
	private SwingLagClientGUI clientGui;
	
	/**
	 * Konstruktor
	 */
	public ClientInterfaceImpl() {
	}
	
	/**
	 * die Gui wird in einem extra attribut gespeichert damit die Werte verändert werden können
	 * @param clientGui die gui
	 */
	public void initialisiereGui(SwingLagClientGUI clientGui){
		this.clientGui = clientGui;
	}

	
	@Override
	public void erhalteAktualisiertePersonenDaten(List<Person> aktualisierteDaten) {
		this.clientGui.updatePersonenListe(aktualisierteDaten);
	}
	
	@Override
	public void erhalteAktualisierteWarenDaten(List<Ware> aktualisierteDaten) {
		this.clientGui.updateWarenListe(aktualisierteDaten);
		
	}
	
	
	/**
	 * Verbindet den Client mit dem Server
	 * @throws UnknownHostException
	 * @throws LookupFailedException
	 * @throws EstablishConnectionFailed
	 */
	public void verbindeClientMitServer()throws UnknownHostException, LookupFailedException, EstablishConnectionFailed{
		lookup = Simon.createNameLookup("127.0.0.1", 5000);
		
		server = (ServerInterface) lookup.lookup("server");
		
		session = server.login("User",  this);
	}
	/**
	 * Returned die Session des zugehörigen Clients
	 * @return SessionInterface
	 */
	public SessionInterface getSession() {
        return this.session;
    }

	/**
	 * Überprüft ob die Verbindung unterbrochen oder vom user beendet wurde
	 */
	public void unreferenced(){
        if(!this.safeLogout){
            System.err.println("Server ist nicht verfügbar!");
        }
    }

	
}


