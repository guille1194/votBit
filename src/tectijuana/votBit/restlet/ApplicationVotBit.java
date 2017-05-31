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
		ruteador.attach("/categoria", CategoriaResource.class);
		ruteador.attach("/opciones", OpcionesResource.class);
		ruteador.attach("/pregunta", PreguntaResource.class);
		ruteador.attach("/respuesta", RespuestaResource.class);
		ruteador.attach("/respuestaopciones", RespuestaOpcionesResource.class);
		ruteador.attach("/usuario", UsuarioResource.class);
		//return super.createInboundRoot();
		return ruteador;
	}
}
