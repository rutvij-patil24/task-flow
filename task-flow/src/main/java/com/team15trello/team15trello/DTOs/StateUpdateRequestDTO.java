package com.team15trello.team15trello.DTOs;

import com.team15trello.team15trello.state.State;

public class StateUpdateRequestDTO {
    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
