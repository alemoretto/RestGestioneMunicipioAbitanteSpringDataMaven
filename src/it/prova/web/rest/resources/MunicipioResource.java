package it.prova.web.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.service.municipio.MunicipioService;
import it.prova.web.dto.municipio.MunicipioDTO;

@Component
@Path("/municipio")
public class MunicipioResource {

	@Autowired
	MunicipioService municipioService;

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMunicipio(@PathParam("id") Long id) {
		MunicipioDTO result = MunicipioDTO
				.buildMunicipioDTOFromModel(municipioService.caricaSingoloMunicipioConAbitanti(id), true);
		return Response.status(200).entity(result).build();
	}

}
