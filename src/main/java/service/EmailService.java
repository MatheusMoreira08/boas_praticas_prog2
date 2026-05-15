package service;

import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EmailService {

    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return Pattern.matches(REGEX_EMAIL, email);
    }

    public void exibirResultado(JTextField campoEmail, JLabel labelResultado) {
        String email = campoEmail.getText().trim();

        if (validarEmail(email)) {
            labelResultado.setText("E-MAIL VÁLIDO");
            labelResultado.setForeground(new Color(0, 128, 0));
            campoEmail.setBackground(new Color(200, 255, 200));
        } else {
            labelResultado.setText("E-MAIL INVÁLIDO");
            labelResultado.setForeground(new Color(200, 0, 0));
            campoEmail.setBackground(new Color(255, 200, 200));
        }
    }
}