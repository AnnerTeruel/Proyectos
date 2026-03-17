package com.Empresa;

import com.Empresa.view.Login;

public class Main {
    public static void main(String[] args) {
        /*
         * Punto de arranque oficial. 
         * Maven apunta a esta clase para crear el jar ejecutable porque al no heredar de Application,
         * evita que Java 17 bloquee los módulos de gráficos.
         */
        Login.main(args);
    }
}