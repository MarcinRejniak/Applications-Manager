package pl.rejniak.claim.domain.application.dto;

public record AppDto (
        Long id,
        String name,
        String content,
        String state,
        String reason
){}

