package edu.ubb.cartering.api.assembler;

import edu.ubb.cartering.api.dto.MenuOrderDTO;
import edu.ubb.cartering.api.dto.StateDTO;
import edu.ubb.cartering.api.exceptions.ApiException;
import edu.ubb.cartering.backend.model.Menu;
import edu.ubb.cartering.backend.model.MenuOrder;
import edu.ubb.cartering.backend.model.State;
import javax.ejb.Stateless;
import java.util.*;


@Stateless
public class MenuOrderAssembler extends EntityAssembler<MenuOrder, MenuOrderDTO> {

    @Override
    public MenuOrderDTO createDto() {
        return new MenuOrderDTO();
    }

    @Override
    public MenuOrder createModel() {
        return new MenuOrder();
    }

    @Override
    public MenuOrderDTO modelToDto(MenuOrder model) throws ApiException {
        final MenuOrderDTO menuOrderDto = createDto();
        menuOrderDto.setMenuOrderId(model.getId());
        menuOrderDto.setMenuId(model.getMenu().getId());
        menuOrderDto.setUserId(model.getUser().getId());
        menuOrderDto.setUser(model.getUser().getFirst() + " " + model.getUser().getLast());
        menuOrderDto.setDate(model.getDate());
        menuOrderDto.setStates(convertStateToPair(model.getStates()));
        return menuOrderDto;
    }

    private List<StateDTO> convertStateToPair(List<State> states) {
        List<StateDTO> statePair = new ArrayList<>();
        for (State state : states) {
            statePair.add(new StateDTO(state.getState(), state.getDate().getTime()));
        }

        return statePair;
    }
    @Override
    public MenuOrder dtoToModel(MenuOrderDTO model) throws ApiException {
        final MenuOrder order = createModel();
        order.setDate(model.getDate() == null ? new Date(new Date().getTime() + model.getMin()): model.getDate());
        Menu menu = new Menu();
        menu.setId(model.getMenuId());
        order.setMenu(menu);
        return order;
    }
}