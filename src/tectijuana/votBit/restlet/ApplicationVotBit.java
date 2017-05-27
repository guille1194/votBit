package tectijuana.votBit.restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ApplicationVotBit extends Application {
	@Override
	public Restlet createInboundRoot() {
		// TODO Auto-generated method stub
		Router ruteador = null;
		
		ruteador = new Router(getContext());
		
		ruteador.attach("/roles", RolesResource.class);
		
		//return super.createInboundRoot();
		return ruteador;
	}
}
