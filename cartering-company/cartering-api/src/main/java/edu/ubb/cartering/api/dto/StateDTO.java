package edu.ubb.cartering.api.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StateDTO {
    private int state;
    private long date;

    public StateDTO() {}
    public StateDTO(int state, long date) {
        this.state = state;
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
