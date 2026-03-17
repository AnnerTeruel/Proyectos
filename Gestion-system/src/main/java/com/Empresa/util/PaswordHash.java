package com.Empresa.util;

import org.mindrot.jbcrypt.BCrypt;

public class PaswordHash {

    /**
     * Encripta una contraseña en texto plano utilizando el algoritmo BCrypt.
     */
    public static String hashPassword(String password_plaintext) {
        // Genera un salt aleatorio y hashea la contraseña
        return BCrypt.hashpw(password_plaintext, BCrypt.gensalt(10));
    }

    /**
     * Verifica que la contraseña ingresada en el Login coincida con el hash de la Base de Datos.
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        if (stored_hash == null || !stored_hash.startsWith("$2a$")) {
            return false; // El hash guardado no es de BCrypt o está corrupto
        }
        return BCrypt.checkpw(password_plaintext, stored_hash);
    }
}