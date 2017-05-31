package tectijuana.votBit.restlet;

import org.restlet.Component;
import org.restlet.data.Protocol;

import tectijuana.votBit.hibernate.util.HibernateUtil;

public class ServidorVotBit {

	public static void main(String[] args) {
		Component componente = new Component();
		
		//componente.getServers().add(Protocol.HTTP);
		//componente.getDefaultHost().attach("/votbit", new Application());
		
		try {
			//verificar la sesion para crear estructura
			HibernateUtil.verificarFabricaSesion();
			
			componente.getServers().add(Protocol.HTTP, 8182);
			componente.getDefaultHost().attach("/votbit", new ApplicationVotBit());
			componente.start();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
