package edu.ubb.cartering.api.rest.menu;

import edu.ubb.cartering.api.assembler.MenuAssembler;
import edu.ubb.cartering.api.dto.MenuDTO;
import edu.ubb.cartering.api.exceptions.ApiException;
import edu.ubb.cartering.backend.enums.USER_ROLES;
import edu.ubb.cartering.backend.model.Menu;
import edu.ubb.cartering.backend.services.AuthenticationService;
import edu.ubb.cartering.backend.services.MenuService;
import edu.ubb.cartering.backend.services.ServiceException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

import static edu.ubb.cartering.api.rest.authentication.LogInResource.SESSION_EMAIL;
import static edu.ubb.cartering.api.rest.authentication.LogInResource.SESSION_ROLE;


@Stateless
@Path(value = "/menu")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource {

    @EJB
    private MenuService menuService;

    @EJB
    private AuthenticationService authenticationService;

    @EJB
    private MenuAssembler menuAssembler;

    @POST
    public Response createMenu(MenuDTO menuDto, @Context HttpServletRequest request) throws ApiException {
        try {
            HttpSession session = request.getSession();
            Integer userType = session.getAttribute(SESSION_ROLE) != null ? Integer.parseInt(session.getAttribute(SESSION_ROLE).toString()) : null;
            if (userType != null && userType.equals(USER_ROLES.ADMIN.getRole())) {
                Menu menu = menuAssembler.dtoToModel(menuDto);
                if (menuService.createMenu(menu)) {
                    return Response.ok("Saved!").build();
                } else {
                    throw new ApiException("Invalid data!");
                }
            } else {
                return Response.status(401).build();
            }
        } catch (ServiceException ex) {
            throw new ApiException(ex.getMessage(), ex);
        }
    }

    @GET
    public Response getAllMenu(@Context HttpServletRequest request) throws ApiException {
        try {
            HttpSession session = request.getSession();
            String email = session.getAttribute(SESSION_EMAIL) != null ? session.getAttribute(SESSION_EMAIL).toString() : null;
            if (email != null && authenticationService.findByEmail(email) != null) {
                List<Menu> menus = menuService.getAllMenu();
                List<MenuDTO> menuDtos = menus.stream().map(p -> menuAssembler.modelToDto(p)).collect(Collectors.toList());
                return Response.ok(menuDtos).build();
            } else {
                throw new ApiException("Unauthorized!");
            }
        } catch (ServiceException ex) {
            throw new ApiException(ex.getMessage(), ex);
        }
    }

    @GET
    @Path("/{id}")
    public Response getMenu(@PathParam("id") long id, @Context HttpServletRequest request) throws ApiException {
        try {
            HttpSession session = request.getSession();
            String email = session.getAttribute(SESSION_EMAIL) != null ? session.getAttribute(SESSION_EMAIL).toString() : null;
            if (email != null && authenticationService.findByEmail(email) != null) {
                Menu menu = menuService.getMenu(id);
                return Response.ok(menuAssembler.modelToDto(menu)).build();
            } else {
                throw new ApiException("Unauthorized!");
            }
        } catch (ServiceException ex) {
            throw new ApiException(ex.getMessage(), ex);
        }
    }

}
