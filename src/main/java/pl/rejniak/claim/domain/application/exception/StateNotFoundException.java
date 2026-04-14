package pl.rejniak.claim.domain.application.exception;

import pl.rejniak.claim.domain.application.State;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StateNotFoundException extends RuntimeException{

    public StateNotFoundException(Long id, State... states){
        super(formatMessage(id, states));
    }

    private static String formatMessage(Long id, State... states) {
        String statesFormatted = Arrays.stream(states)
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        return String.format("AppEntity %s not found, appId: %d", statesFormatted, id);
    }
}
