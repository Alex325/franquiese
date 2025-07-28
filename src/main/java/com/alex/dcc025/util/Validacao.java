package com.alex.dcc025.util;

import org.apache.commons.validator.routines.EmailValidator;

public class Validacao {
    public static boolean emailValido(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
