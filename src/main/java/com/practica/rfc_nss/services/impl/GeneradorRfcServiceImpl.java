package com.practica.rfc_nss.services.impl;

import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.practica.rfc_nss.dto.RequestGenerarRfcDTO;
import com.practica.rfc_nss.dto.ResponseRfcDTO;
import com.practica.rfc_nss.services.GeneradorRfcService;
import com.practica.rfc_nss.util.CalcularHomoclave;
import com.practica.rfc_nss.util.DigitoVerificador;
import com.practica.rfc_nss.util.FuncionesRfcUtils;

@Service
public class GeneradorRfcServiceImpl implements GeneradorRfcService {

    
    @Override
    public ResponseRfcDTO calcularRFC(RequestGenerarRfcDTO request) {
        ResponseRfcDTO datosRfc = new ResponseRfcDTO();
        String rfc = "";
        try {
            String primerNombre = FuncionesRfcUtils.getPrimerNombre(request.getNombres().trim().toUpperCase());
            String primerApellido = FuncionesRfcUtils
                    .getPrimerApellido(request.getApellidoPaterno().trim().toUpperCase());
            String segundoApellido = FuncionesRfcUtils
                    .getPrimerApellido(request.getApellidoMaterno().trim().toUpperCase());

            rfc += FuncionesRfcUtils.getInicialApellido(primerApellido);
            rfc += FuncionesRfcUtils.getPrimerVocalInterna(primerApellido);
            rfc += FuncionesRfcUtils.getInicialApellido(segundoApellido);
            rfc += FuncionesRfcUtils.getInicialNombre(primerNombre);

            String fecha = request.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyMMdd"));
            rfc += fecha;
            datosRfc.setRfc(rfc);

            String nombreCompleto = request.getNombres().trim() + " " + request.getApellidoPaterno().trim() + " "
                    + request.getApellidoMaterno().trim();
            datosRfc.setNombreCompleto(nombreCompleto);
            String homoclave = CalcularHomoclave.calculateHomoclave(nombreCompleto);
            rfc += homoclave;
            homoclave += DigitoVerificador.calculateVerificationDigit(rfc);
            datosRfc.setHomoClave(homoclave);
            rfc += homoclave;
            datosRfc.setRfcHomoClave(rfc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Error: ", e);
        }
        return datosRfc;
    }

}
