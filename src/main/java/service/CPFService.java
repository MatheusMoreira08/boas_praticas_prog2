package service;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CPFService {

    public boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return false;
        }

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(.)\\1{10}")) {
            return false;
        }

        try {
            int d1 = calcularDigitoVerificador(cpf.substring(0, 9), new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2});
            int d2 = calcularDigitoVerificador(cpf.substring(0, 9) + d1, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

            String cpfCalculado = cpf.substring(0, 9) + d1 + d2;

            return cpf.equals(cpfCalculado);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int calcularDigitoVerificador(String str, int[] multiplicadores) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * multiplicadores[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    public void exibirResultado(JTextField campoCPF, JLabel labelResultado) {
        String cpf = campoCPF.getText().trim();

        if (validarCPF(cpf)) {
            labelResultado.setText("CPF VÁLIDO");
            labelResultado.setForeground(new Color(0, 128, 0));
            campoCPF.setBackground(new Color(200, 255, 200));
        } else {
            labelResultado.setText("CPF INVÁLIDO");
            labelResultado.setForeground(new Color(200, 0, 0));
            campoCPF.setBackground(new Color(255, 200, 200));
        }
    }

    public String formatarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() == 11) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        }
        return cpf;
    }
}