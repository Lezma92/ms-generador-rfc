package com.practica.rfc_nss.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CalcularHomoclave {

    private CalcularHomoclave() {

    }

    private static final String HOMOCLAVE_DIGITS = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
    private static final Map<String, String> FULL_NAME_MAPPING = new HashMap<>();

    static {
        FULL_NAME_MAPPING.put(" ", "00");
        FULL_NAME_MAPPING.put("0", "00");
        FULL_NAME_MAPPING.put("1", "01");
        FULL_NAME_MAPPING.put("2", "02");
        FULL_NAME_MAPPING.put("3", "03");
        FULL_NAME_MAPPING.put("4", "04");
        FULL_NAME_MAPPING.put("5", "05");
        FULL_NAME_MAPPING.put("6", "06");
        FULL_NAME_MAPPING.put("7", "07");
        FULL_NAME_MAPPING.put("8", "08");
        FULL_NAME_MAPPING.put("9", "09");
        FULL_NAME_MAPPING.put("&", "10");
        FULL_NAME_MAPPING.put("A", "11");
        FULL_NAME_MAPPING.put("B", "12");
        FULL_NAME_MAPPING.put("C", "13");
        FULL_NAME_MAPPING.put("D", "14");
        FULL_NAME_MAPPING.put("E", "15");
        FULL_NAME_MAPPING.put("F", "16");
        FULL_NAME_MAPPING.put("G", "17");
        FULL_NAME_MAPPING.put("H", "18");
        FULL_NAME_MAPPING.put("I", "19");
        FULL_NAME_MAPPING.put("J", "21");
        FULL_NAME_MAPPING.put("K", "22");
        FULL_NAME_MAPPING.put("L", "23");
        FULL_NAME_MAPPING.put("M", "24");
        FULL_NAME_MAPPING.put("N", "25");
        FULL_NAME_MAPPING.put("O", "26");
        FULL_NAME_MAPPING.put("P", "27");
        FULL_NAME_MAPPING.put("Q", "28");
        FULL_NAME_MAPPING.put("R", "29");
        FULL_NAME_MAPPING.put("S", "32");
        FULL_NAME_MAPPING.put("T", "33");
        FULL_NAME_MAPPING.put("U", "34");
        FULL_NAME_MAPPING.put("V", "35");
        FULL_NAME_MAPPING.put("W", "36");
        FULL_NAME_MAPPING.put("X", "37");
        FULL_NAME_MAPPING.put("Y", "38");
        FULL_NAME_MAPPING.put("Z", "39");
        FULL_NAME_MAPPING.put("Ñ", "40");
    }

    public static String calculateHomoclave(String fullName) {
        // Normalize full name
        fullName = StringUtils.stripAccents(fullName.toUpperCase());
        fullName = fullName.replaceAll("[\\-\\.',]", ""); // remove .'-,
        fullName = addMissingCharToFullName(fullName, 'Ñ');

        // Map full name to digits code
        StringBuilder nombreCompleto = new StringBuilder("0");
        for (int i = 0; i < fullName.length(); i++) {
            nombreCompleto.append(mapCharacterToTwoDigitCode(String.valueOf(fullName.charAt(i))));
        }

        // Sum pairs of digits
        int pairsOfDigitsSum = 0;
        for (int i = 0; i < nombreCompleto.toString().length() - 1; i++) {
            int intNum1 = Integer.parseInt(nombreCompleto.toString().substring(i, i + 2));
            int intNum2 = Integer.parseInt(nombreCompleto.toString().substring(i + 1, i + 2));
            pairsOfDigitsSum += intNum1 * intNum2;
        }

        // Build homoclave
        int lastThreeDigits = pairsOfDigitsSum % 1000;
        int quo = lastThreeDigits / 34;
        int reminder = lastThreeDigits % 34;

        return String.valueOf(HOMOCLAVE_DIGITS.charAt(quo))
                + String.valueOf(HOMOCLAVE_DIGITS.charAt(reminder));
    }

    private static String addMissingCharToFullName(String fullName, char missingChar) {
        int index = fullName.indexOf(missingChar);
        if (index == -1) {
            return fullName;
        }

        StringBuilder newFullName = new StringBuilder(fullName);
        while (index >= 0) {
            newFullName.setCharAt(index, missingChar);
            index = fullName.indexOf(missingChar, index + 1);
        }
        return newFullName.toString();
    }

    private static String mapCharacterToTwoDigitCode(String c) {
        if (!FULL_NAME_MAPPING.containsKey(c)) {
            throw new IllegalArgumentException("No two-digit-code mapping for char: " + c);
        } else {
            return FULL_NAME_MAPPING.get(c);
        }
    }

}
