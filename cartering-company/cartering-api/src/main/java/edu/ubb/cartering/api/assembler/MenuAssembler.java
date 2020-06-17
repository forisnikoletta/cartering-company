package edu.ubb.cartering.api.assembler;

import edu.ubb.cartering.api.dto.FoodDTO;
import edu.ubb.cartering.api.dto.MenuDTO;
import edu.ubb.cartering.api.exceptions.ApiException;
import edu.ubb.cartering.backend.model.Food;
import edu.ubb.cartering.backend.model.Menu;

import javax.ejb.Stateless;
import java.util.*;


@Stateless
public class MenuAssembler extends EntityAssembler<Menu, MenuDTO> {

    @Override
    public MenuDTO createDto() {
        return new MenuDTO();
    }

    @Override
    public Menu createModel() {
        return new Menu();
    }

    @Override
    public MenuDTO modelToDto(Menu model) throws ApiException {
        final MenuDTO menuDTO = createDto();
        menuDTO.setId(model.getId());
        menuDTO.setName(model.getName());
        menuDTO.setFoods(convertFoodToFoodDTO(model.getFoods()));
        return menuDTO;
    }

    private List<FoodDTO> convertFoodToFoodDTO(Set<Food> foods) {
        List<FoodDTO> foodDTOS = new ArrayList<>();
        Iterator<Food> iterator = foods.iterator();
        while (iterator.hasNext()) {
            Food fd = iterator.next();
            FoodDTO fdDTO = new FoodDTO();
            fdDTO.setId(fd.getId());
            fdDTO.setName(fd.getName());
            fdDTO.setTime(fd.getTime());
            foodDTOS.add(fdDTO);
        }
        return foodDTOS;
    }

    private Set<Food> convertFoodDTOToFood(List<FoodDTO> foodsDTOS) {
        Set<Food> foods = new HashSet<>();
        for (FoodDTO fdDTO : foodsDTOS) {
            Food fd = new Food();
            fd.setId(fdDTO.getId());
            fd.setName(fdDTO.getName());
            fd.setTime(fdDTO.getTime());
            foods.add(fd);
        }
        return foods;
    }

    @Override
    public Menu dtoToModel(MenuDTO model) throws ApiException {
        final Menu menu = createModel();
        menu.setId(model.getId());
        menu.setName(model.getName());
        menu.setFoods(convertFoodDTOToFood(model.getFoods()));
        return menu;
    }
}