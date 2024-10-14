package com.practica.rfc_nss.services;

import org.springframework.stereotype.Service;

import com.practica.rfc_nss.dto.RequestGenerarRfcDTO;
import com.practica.rfc_nss.dto.ResponseRfcDTO;

@Service
public interface GeneradorRfcService {

    public ResponseRfcDTO calcularRFC(RequestGenerarRfcDTO request);

}
