package edu.ubb.cartering.api.rest.authentication;

import edu.ubb.cartering.api.assembler.UserAssembler;
import edu.ubb.cartering.backend.services.AuthenticationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path(value = "/logout")
@Produces(MediaType.TEXT_PLAIN)
public class LogOutResource {

    @GET
    public Response logout(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(LogInResource.SESSION_EMAIL, null);
        session.setAttribute(LogInResource.SESSION_EMAIL, 0);
        return Response.ok("Successful logout!").build();
    }
}
