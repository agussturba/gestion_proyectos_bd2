package com.grupo.bd2.dto;

public record TaskProgressRequestDto(
    Long id,
    Double percentageCompleted // se calcula y se guarda despues de calcular
){}

