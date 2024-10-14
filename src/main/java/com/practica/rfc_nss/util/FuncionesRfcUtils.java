package com.practica.rfc_nss.util;

public class FuncionesRfcUtils {
    private FuncionesRfcUtils() {
    }

    public static String getPrimerNombre(String nombres) {
        String[] partes = nombres.split(" ");

        for (String nombre : partes) {
            if (!nombre.equals("JOSE") && !nombre.equals("MARIA") && !nombre.equals("MA.") && !nombre.equals("MA")) {
                return nombre;
            }
        }

        return partes[0];
    }

    public static String getPrimerApellido(String apellidos) {
        String[] partes = apellidos.split(" ");

        for (String parte : partes) {
            if (!parte.equals("DE") && !parte.equals("LA") && !parte.equals("LAS") && !parte.equals("MC") &&
                    !parte.equals("VON") && !parte.equals("DEL") && !parte.equals("LOS") && !parte.equals("Y") &&
                    !parte.equals("MAC") && !parte.equals("VAN")) {
                return parte;
            }
        }

        return partes[0];
    }

    public static String getInicialApellido(String apellido) {
        return apellido.isEmpty() ? "X" : apellido.substring(0, 1);
    }

    public static String getPrimerVocalInterna(String apellido) {

        for (int i = 1; i < apellido.length(); i++) {
            char c = apellido.charAt(i);
            if ("AEIOU".indexOf(c) >= 0) {
                return String.valueOf(c);
            }
        }

        return "X";
    }

    public static String getInicialNombre(String nombre) {
        return nombre.substring(0, 1);
    }

}
