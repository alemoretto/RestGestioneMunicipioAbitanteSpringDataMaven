package it.prova.web.rest.resources;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.model.Municipio;
import it.prova.service.municipio.MunicipioService;
import it.prova.web.dto.municipio.MunicipioDTO;

@Component
@Path("/municipio")
public class MunicipioResource {

	private static final Logger LOGGER = Logger.getLogger(MunicipioResource.class.getName());

	@Context
	HttpServletRequest request;

	@Autowired
	MunicipioService municipioService;

	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMunicipio(@PathParam("id") Long id) {
		MunicipioDTO result = MunicipioDTO
				.buildMunicipioDTOFromModel(municipioService.caricaSingoloMunicipio(id), false);
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/eager/{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMunicipioEager(@PathParam("id") Long id) {
		MunicipioDTO result = MunicipioDTO
				.buildMunicipioDTOFromModel(municipioService.caricaSingoloMunicipioConAbitanti(id), true);
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		List<MunicipioDTO> result = municipioService.listAllMunicipi().stream().map(m -> MunicipioDTO.buildMunicipioDTOFromModel(m,false)).collect(Collectors.toList());
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/eager")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAllEager() {
		List<MunicipioDTO> result = municipioService.listAllMunicipiEager().stream().map(m -> MunicipioDTO.buildMunicipioDTOFromModel(m,true)).collect(Collectors.toList());
		return Response.status(200).entity(result).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovoMunicipio(MunicipioDTO municipioInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		municipioService.inserisciNuovo(MunicipioDTO.buildMunicipioModelFromDTO(municipioInput,false));
		return Response.status(200).entity(municipioInput).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornaMunicipio(MunicipioDTO municipioInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		municipioService.aggiorna(MunicipioDTO.buildMunicipioModelFromDTO(municipioInput, false));
		return Response.status(200).entity(municipioInput).build();
	}
	
	@DELETE
	@Path("{id : \\d+}")
	public Response deleteMunicipio(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Municipio municipio = municipioService.caricaSingoloMunicipio(id);
		if(municipio != null) {
			municipioService.rimuovi(municipio);
			return Response.status(200).entity("Rimosso Municipio con id: "+id).build();
		}
		
		return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
	}
	
	@GET
	@Path("/cercaPerDescrizione")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByDescrizioneILike(@QueryParam("descrizione") String descrizione) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<MunicipioDTO> result = municipioService.cercaByDescrizioneILike(descrizione).stream().map(m -> MunicipioDTO.buildMunicipioDTOFromModel(m, false)).collect(Collectors.toList());
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/cercaPerEsempio")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByExample(MunicipioDTO municipioInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Municipio> resultDTO = municipioService.findByExample(MunicipioDTO.buildMunicipioModelFromDTO(municipioInput,false));
		List<MunicipioDTO> result = resultDTO.stream().map(m -> MunicipioDTO.buildMunicipioDTOFromModel(m, false)).collect(Collectors.toList());
		return Response.status(200).entity(result).build();
	}
}
