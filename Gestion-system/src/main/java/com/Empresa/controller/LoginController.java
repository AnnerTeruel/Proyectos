package com.Empresa.controller;

import com.Empresa.DAO.UsuarioDAO;
import com.Empresa.model.Usuario;
import com.Empresa.util.PaswordHash;
import javafx.scene.control.Alert;

public class LoginController {

    private UsuarioDAO usuarioDAO;

    public LoginController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Valida si las credenciales existen en la base de datos usando BCrypt.
     */
    public boolean validarLogin(String username, String passwordIngresada) {
        Usuario usuarioDeBD = usuarioDAO.buscarPorUsername(username);

        // Si el usuario existe, validamos el hash
        if (usuarioDeBD != null) {
            String hashGuardado = usuarioDeBD.getPassword();
            
            // ATENCIÓN: Como en los datos de prueba insertamos '$2a$10$hash_ficticio_1' (que es falso)
            // Agregamos una verificación de desarrollo para poder acceder probar el diseño.
            if (hashGuardado.equals("$2a$10$hash_ficticio_1") || hashGuardado.equals("$2a$10$hash_ficticio_2")) {
                // Modo Bypass (Solo para datos de prueba iniciales)
                System.out.println("ADVERTENCIA: Usando bypass de datos de prueba.");
                // Asumimos que la clave de prueba para el admin_carlos y vend_ana es "1234"
                return passwordIngresada.equals("1234");
            }

            // MODO PRODUCCIÓN: Validar Hash Criptográfico
            try {
                return PaswordHash.checkPassword(passwordIngresada, hashGuardado);
            } catch (Exception e) {
                System.err.println("Error al chequear contraseña: " + e.getMessage());
                return false;
            }
        }
        
        return false;
    }

    /**
     * Funcionalidad genérica para desplegar mensajes Pop-Up en la Interfaz.
     */
    public void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
