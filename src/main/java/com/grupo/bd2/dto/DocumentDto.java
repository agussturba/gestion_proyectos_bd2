package com.grupo.bd2.dto;

import lombok.Builder;

@Builder
public record DocumentDto(
        String name,
        Long id
) {

}
