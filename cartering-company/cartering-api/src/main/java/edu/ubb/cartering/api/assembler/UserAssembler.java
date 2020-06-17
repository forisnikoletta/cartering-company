package edu.ubb.cartering.api.assembler;

import edu.ubb.cartering.api.dto.UserDTO;
import edu.ubb.cartering.api.exceptions.ApiException;
import edu.ubb.cartering.backend.model.User;

import javax.ejb.Stateless;

@Stateless
public class UserAssembler extends EntityAssembler<User, UserDTO>{

    @Override
    public UserDTO createDto() {
        return new UserDTO();
    }

    @Override
    public User createModel() {
        return new User();
    }

    @Override
    public UserDTO modelToDto(User model) throws ApiException {
        final UserDTO userDTO = createDto();
        userDTO.setId(model.getId());
        userDTO.setFirst(model.getFirst());
        userDTO.setLast(model.getLast());
        userDTO.setPassword(model.getPassword());
        userDTO.setPhone(model.getPhone());
        userDTO.setUsername(model.getEmail());
        userDTO.setAddress(model.getAddress());
        return userDTO;
    }

    @Override
    public User dtoToModel(UserDTO model) throws ApiException {
        final User user = createModel();
        user.setId(model.getId());
        user.setFirst(model.getFirst());
        user.setLast(model.getLast());
        user.setPassword(model.getPassword());
        user.setPhone(model.getPhone());
        user.setEmail(model.getUsername());
        user.setAddress(model.getAddress());
        return user;
    }
}
