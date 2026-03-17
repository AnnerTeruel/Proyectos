package com.Empresa.view;

import com.Empresa.controller.LoginController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login extends Application {

    private LoginController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new LoginController();

        // 1. Contenedor Principal (Fondo Completo)
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        // Le aplicamos la clase ".root" del estilo.css para ese gradiente épico
        root.getStyleClass().add("root");

        // 2. Panel Central (Efecto Glassmorphism / Cristal translúcido)
        VBox panel = new VBox(25); // 25 px de separación entre elementos
        panel.setAlignment(Pos.CENTER);
        panel.setPadding(new Insets(50, 40, 50, 40));
        panel.setMaxWidth(400); // Para que no se estire demasiado en pantallas grandes
        panel.getStyleClass().add("panel-glass");

        // 3. Componentes Internos del Panel
        Label lblTitulo = new Label("G-INVENTARY");
        lblTitulo.getStyleClass().add("titulo-principal");

        Label lblSubtitulo = new Label("Ingrese sus credenciales de acceso");
        lblSubtitulo.getStyleClass().add("subtitulo");

        TextField txtUsuario = new TextField();
        txtUsuario.setPromptText("Usuario (ej: admin_carlos)");
        txtUsuario.getStyleClass().add("text-field");

        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Contraseña (ej: 1234)");
        txtPassword.getStyleClass().add("password-field");

        Button btnIngresar = new Button("INGRESAR AL SISTEMA");
        btnIngresar.setMaxWidth(Double.MAX_VALUE); // Para que el botón sea 100% ancho
        btnIngresar.getStyleClass().add("btn-primary");

        // ===================================
        // EVENTOS O LÓGICA DE LOS BOTONES
        // ===================================
        btnIngresar.setOnAction(e -> {
            String user = txtUsuario.getText().trim();
            String pass = txtPassword.getText();

            if (user.isEmpty() || pass.isEmpty()) {
                controller.mostrarAlerta("Campos Vacíos", "Por favor, digite su usuario y contraseña.", Alert.AlertType.WARNING);
            } else {
                if (controller.validarLogin(user, pass)) {
                    controller.mostrarAlerta("¡Bienvenido!", "Acceso Concedido: " + user, Alert.AlertType.INFORMATION);
                    
                    // Aquí llamaremos próximamente a Dashboard.java
                    // Dashboard dashboard = new Dashboard();
                    // dashboard.start(new Stage());
                    // primaryStage.close(); // Cerramos el login actual
                    
                } else {
                    controller.mostrarAlerta("Acceso Denegado", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
                }
            }
        });

        // Ensamblar todo
        panel.getChildren().addAll(lblTitulo, lblSubtitulo, txtUsuario, txtPassword, btnIngresar);
        root.getChildren().add(panel);

        // Crear la escena de tamaño 900x600 aprox.
        Scene scene = new Scene(root, 900, 600);

        // Intentar enlazar el CSS que hicimos
        try {
            // Nota: En Java o Maven los resources se acceden leyendo desde la raíz del classpath "/"
            scene.getStylesheets().add(getClass().getResource("/css/estilo.css").toExternalForm());
        } catch (NullPointerException ex) {
            System.err.println("Advertencia: No se encontró el archivo /css/estilo.css o no ha compilado bien.");
        }

        primaryStage.setTitle("Punto de Venta / Inventario - Modern UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Para llamarlo independientemente o probar la ventana
    public static void main(String[] args) {
        launch(args);
    }
}
