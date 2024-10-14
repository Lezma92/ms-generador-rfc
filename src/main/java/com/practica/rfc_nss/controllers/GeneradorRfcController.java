package com.practica.rfc_nss.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.rfc_nss.dto.ApiResponseDTO;
import com.practica.rfc_nss.dto.RequestGenerarRfcDTO;
import com.practica.rfc_nss.services.GeneradorRfcService;
import com.practica.rfc_nss.util.Meta;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/generador")
public class GeneradorRfcController {
    private final GeneradorRfcService generadorRfcService;
    public GeneradorRfcController(GeneradorRfcService generadorRfcService){
        this.generadorRfcService = generadorRfcService;
    }
    @PostMapping("/rfc")
    public ApiResponseDTO generarRfc(@RequestBody RequestGenerarRfcDTO request) {
        return new ApiResponseDTO(Meta.getMetaOk(), generadorRfcService.calcularRFC(request));
    }

}
