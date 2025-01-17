package pl.rejniak.claim.domain.application.exception;

import pl.rejniak.claim.domain.application.State;

public class StateNotFoundException extends RuntimeException{
    public StateNotFoundException(State state, Long id){
        super("AppEntity '" + state + "' not found, appId: " + id);
    }

}
