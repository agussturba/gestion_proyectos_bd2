package com.grupo.bd2.dto;

import lombok.Builder;
@Builder
public record RedisDto(
         Long key,
          String value
) {
}
