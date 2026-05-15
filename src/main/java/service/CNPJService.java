package service;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CNPJService {

    private static final int[] PESOS_PRIMEIRO_DIGITO = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_SEGUNDO_DIGITO = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public boolean validarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }

        cnpj = cnpj.replaceAll("[^0-9]", "");

        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(.)\\1{13}")) {
            return false;
        }

        try {
            int d1 = calcularDigitoVerificador(cnpj.substring(0, 12), PESOS_PRIMEIRO_DIGITO);
            int d2 = calcularDigitoVerificador(cnpj.substring(0, 12) + d1, PESOS_SEGUNDO_DIGITO);

            String cnpjCalculado = cnpj.substring(0, 12) + d1 + d2;

            return cnpj.equals(cnpjCalculado);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int calcularDigitoVerificador(String str, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < str.length(); i++) {
            soma += Character.getNumericValue(str.charAt(i)) * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    public void exibirResultado(JTextField campoCNPJ, JLabel labelResultado) {
        String cnpj = campoCNPJ.getText().trim();

        if (validarCNPJ(cnpj)) {
            labelResultado.setText("CNPJ VÁLIDO");
            labelResultado.setForeground(new Color(0, 128, 0));
            campoCNPJ.setBackground(new Color(200, 255, 200));
        } else {
            labelResultado.setText("CNPJ INVÁLIDO");
            labelResultado.setForeground(new Color(200, 0, 0));
            campoCNPJ.setBackground(new Color(255, 200, 200));
        }
    }

    public String formatarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        if (cnpj.length() == 14) {
            return cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." + cnpj.substring(5, 8) + "/" + cnpj.substring(8, 12) + "-" + cnpj.substring(12);
        }
        return cnpj;
    }
}