package com.practica.rfc_nss.dto;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@lombok.Builder
public class ResponseRfcDTO {
    private String nombreCompleto;

    private String rfc;

    private String homoClave;

    private String rfcHomoClave;
}
