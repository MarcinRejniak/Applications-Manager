package pl.rejniak.claim.domain.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppDto {
    private final Long id;
    private final String name;
    private final String content;
    private final String state;
    private final String reason;
}

