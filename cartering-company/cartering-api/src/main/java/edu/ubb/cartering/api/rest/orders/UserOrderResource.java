package edu.ubb.cartering.api.rest.orders;

import edu.ubb.cartering.api.assembler.MenuOrderAssembler;
import edu.ubb.cartering.api.dto.MenuOrderDTO;
import edu.ubb.cartering.api.dto.StateDTO;
import edu.ubb.cartering.api.exceptions.ApiException;
import edu.ubb.cartering.backend.enums.ORDER_STATES;
import edu.ubb.cartering.backend.enums.USER_ROLES;
import edu.ubb.cartering.backend.model.Menu;
import edu.ubb.cartering.backend.model.MenuOrder;
import edu.ubb.cartering.backend.model.User;
import edu.ubb.cartering.backend.services.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static edu.ubb.cartering.api.rest.authentication.LogInResource.SESSION_EMAIL;
import static edu.ubb.cartering.api.rest.authentication.LogInResource.SESSION_ROLE;


@Stateless
@Path(value = "/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserOrderResource {

    @EJB
    private MenuOrderService orderService;

    @EJB
    private MenuService menuService;

    @EJB
    private OrderStateService stateService;

    @EJB
    private AuthenticationService authenticationService;

    @EJB
    private MenuOrderAssembler menuOrderAssembler;

    @POST
    public Response createOrder(MenuOrderDTO menuDto, @Context HttpServletRequest request) throws ApiException {
        try {
            HttpSession session = request.getSession();
            String email = session.getAttribute(SESSION_EMAIL) != null ? session.getAttribute(SESSION_EMAIL).toString() : null;
            if (email != null && authenticationService.findByEmail(email) != null) {
                Menu menu = menuService.getMenu(menuDto.getMenuId());
                if(menu == null) {
                    throw new ApiException("Invalid menu id!");
                } else {
                    User user = authenticationService.findByEmail(email);
                    orderService.orderMenu(user, menu);
                    return Response.ok("Saved").build();
                }
            } else {
                throw new ApiException("Unauthorized!");
            }
        } catch (ServiceException ex) {
            throw new ApiException(ex.getMessage(), ex);
        }
    }

    @GET
    public Response getAllUserOrders(@Context HttpServletRequest request) throws ApiException {
        try {
            HttpSession session = request.getSession();
            String email = session.getAttribute(SESSION_EMAIL) != null ? session.getAttribute(SESSION_EMAIL).toString() : null;
            if (email != null && authenticationService.findByEmail(email) != null) {
                    User user = authenticationService.findByEmail(email);
                    List<MenuOrder> menuOrders = orderService.getAllMenuOrders(user);
                    List<MenuOrderDTO> menuDtos = menuOrders.stream().map(p -> menuOrderAssembler.modelToDto(p)).collect(Collectors.toList());
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
    public Response getAllMenuOrders(@PathParam("id") long id, @Context HttpServletRequest request) throws ApiException {
        try {
            HttpSession session = request.getSession();
            String email = session.getAttribute(SESSION_EMAIL) != null ? session.getAttribute(SESSION_EMAIL).toString() : null;
            if (email != null && authenticationService.findByEmail(email) != null) {
                Menu menu = menuService.getMenu(id);
                if (menu == null) {
                    throw new ApiException("Invalid parameter!");
                } else {
                    List<MenuOrder> menuOrders = orderService.getOrders(menu);
                    List<MenuOrderDTO> menuDtos = menuOrders.stream().map(p -> menuOrderAssembler.modelToDto(p)).collect(Collectors.toList());
                    return Response.ok(menuDtos).build();
                }
            } else {
                throw new ApiException("Unauthorized!");
            }
        } catch (ServiceException ex) {
            throw new ApiException(ex.getMessage(), ex);
        }
    }

    @POST
    @Path("/state/{id}")
    public Response setOrderState(StateDTO stateDTO, @PathParam("id") long id, @Context HttpServletRequest request) throws ApiException {
        try {
            HttpSession session = request.getSession();
            String email = session.getAttribute(SESSION_EMAIL) != null ? session.getAttribute(SESSION_EMAIL).toString() : null;
            Integer role = session.getAttribute(SESSION_ROLE) != null ? Integer.parseInt(session.getAttribute(SESSION_ROLE).toString()) : null;
            if (email != null && authenticationService.findByEmail(email) != null && role != null && role.equals(USER_ROLES.ADMIN.getRole())) {
                MenuOrder order = orderService.getOrder(id);
                if (order == null) {
                    throw new ApiException("Invalid parameter!");
                } else {
                    if (order.getLastState() < stateDTO.getState()) {
                        stateService.createState(order, ORDER_STATES.getState(stateDTO.getState()));
                        return Response.ok("Oke").build();
                    } else {
                        throw new ApiException("Invalid state!");
                    }
                }
            } else {
                throw new ApiException("Unauthorized!");
            }
        } catch (ServiceException ex) {
            throw new ApiException(ex.getMessage(), ex);
        }
    }

}
