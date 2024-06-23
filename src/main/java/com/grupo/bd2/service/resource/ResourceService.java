package com.grupo.bd2.service.resource;

import com.grupo.bd2.dto.ResourceRequestDto;
import com.grupo.bd2.dto.ResourceResponseDto;


public interface ResourceService {
    ResourceResponseDto getResourceById(Long id);
    ResourceResponseDto createOrUpdateResource(ResourceRequestDto resourceRequestDto);
}
