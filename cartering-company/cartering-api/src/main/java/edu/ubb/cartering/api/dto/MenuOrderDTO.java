package edu.ubb.cartering.api.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuOrderDTO extends AbstractDTO {

    private long menuOrderId;
    private long userId;

    private long menuId;

    private MenuDTO menuDTO;

    private String user;

    private Date date;

    private int min;

    private List<StateDTO> states;

    public MenuOrderDTO() {
    }

    public long getMenuOrderId() {
        return menuOrderId;
    }

    public void setMenuOrderId(long menuOrderId) {
        this.menuOrderId = menuOrderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public MenuDTO getMenuDTO() {
        return menuDTO;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.menuDTO = menuDTO;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public List<StateDTO> getStates() {
        return states;
    }

    public void setStates(List<StateDTO> states) {
        this.states = states;
    }
}
