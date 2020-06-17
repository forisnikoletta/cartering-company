package edu.ubb.cartering.api.rest.authentication;

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

import edu.ubb.cartering.api.assembler.UserAssembler;
import edu.ubb.cartering.api.dto.UserDTO;
import edu.ubb.cartering.api.exceptions.ApiException;
import edu.ubb.cartering.backend.model.User;
import edu.ubb.cartering.backend.services.AuthenticationService;
import edu.ubb.cartering.backend.services.ServiceException;


@Stateless
@Path(value = "/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LogInResource {
	public static String SESSION_ROLE = "role";
	public static String SESSION_EMAIL = "email";

	@EJB
	private AuthenticationService authService;
	
	@EJB
    private UserAssembler userAssembler;
	
	@POST
	public Response login(UserDTO userDto, @Context HttpServletRequest request) throws ApiException {
		
		try {
			final User user = userAssembler.dtoToModel(userDto);
			final User user2 = authService.validatePassword(user.getEmail(), user.getPassword());
			if (user2 != null) {
				HttpSession session = request.getSession();
				session.setAttribute(SESSION_EMAIL, user.getEmail());
				session.setAttribute(SESSION_ROLE, user.getRole());
				return Response.ok("Successful login!").build();
			} else {
				throw new ApiException("Incorrect email or password!");
			}
		} catch( ServiceException ex) {
			throw new ApiException(ex.getMessage(), ex);
		}
	}
	
}
