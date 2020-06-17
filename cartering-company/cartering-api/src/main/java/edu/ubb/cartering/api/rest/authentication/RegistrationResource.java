package edu.ubb.cartering.api.rest.authentication;

import edu.ubb.cartering.api.assembler.UserAssembler;
import edu.ubb.cartering.api.dto.UserDTO;
import edu.ubb.cartering.api.exceptions.ApiException;
import edu.ubb.cartering.backend.model.User;
import edu.ubb.cartering.backend.services.AuthenticationService;
import edu.ubb.cartering.backend.services.ServiceException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path(value = "/registration")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationResource {

	@EJB
	private AuthenticationService authService;
	
	@EJB
    private UserAssembler userAssembler;
	
	@POST
	public Response registration(UserDTO userDto, @Context HttpServletRequest request)  throws ApiException {
		try {
			final User user = userAssembler.dtoToModel(userDto);
			authService.register(user);
			return Response.ok("Registration completed").build();
		} catch( ServiceException ex) {
			throw new ApiException(ex.getMessage());
		}
	}
}
