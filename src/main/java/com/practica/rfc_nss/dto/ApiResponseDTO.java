package com.practica.rfc_nss.dto;

import com.practica.rfc_nss.util.Meta;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data 
public class ApiResponseDTO {

    private Meta meta;

    private Object data;
    
}
