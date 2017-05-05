package com.gruponutresa.prisma.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.inject.Inject;
import com.gruponutresa.prisma.business.ResponsableBusiness;
import com.gruponutresa.prisma.exception.TechnicalException;
import com.gruponutresa.prisma.models.Responsable;

@Path("/responsable")
public class ResponsableService {
	
	@Inject
	ResponsableBusiness responsableBusiness;
	
	
	@GET
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Responsable> consultarResponsable() throws TechnicalException{
		
		return responsableBusiness.obtenerResponsable();
	}
}
