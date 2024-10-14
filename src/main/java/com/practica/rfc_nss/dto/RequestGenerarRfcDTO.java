package com.practica.rfc_nss.dto;

import java.time.LocalDate;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@lombok.Builder
public class RequestGenerarRfcDTO {

    private String nombres;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private LocalDate fechaNacimiento;
    
}
