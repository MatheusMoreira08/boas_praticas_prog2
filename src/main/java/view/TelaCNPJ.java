package view;

import service.CNPJService;
import javax.swing.*;
import java.awt.*;

public class TelaCNPJ extends JInternalFrame {

    private JTextField campoCNPJ;
    private JLabel labelResultado;
    private CNPJService cnpjService;

    public TelaCNPJ() {
        this.cnpjService = new CNPJService();
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 102, 204));
        JLabel titulo = new JLabel("Validação de CNPJ");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(3, 1, 10, 10));
        panelCampos.setBackground(Color.WHITE);

        JLabel labelCNPJ = new JLabel("Digite o CNPJ:");
        labelCNPJ.setFont(new Font("Arial", Font.PLAIN, 14));

        campoCNPJ = new JTextField();
        campoCNPJ.setFont(new Font("Arial", Font.PLAIN, 16));
        campoCNPJ.setHorizontalAlignment(JTextField.CENTER);
        campoCNPJ.setPreferredSize(new Dimension(300, 40));

        JButton botaoValidar = new JButton("Validar CNPJ");
        botaoValidar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoValidar.setBackground(new Color(0, 102, 204));
        botaoValidar.setForeground(Color.BLACK);
        botaoValidar.setPreferredSize(new Dimension(150, 40));
        botaoValidar.setFocusPainted(false);
        botaoValidar.addActionListener(e -> validarCNPJ());

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoLimpar.setBackground(new Color(100, 100, 100));
        botaoLimpar.setForeground(Color.BLACK);
        botaoLimpar.setPreferredSize(new Dimension(150, 40));
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.addActionListener(e -> limpar());

        labelResultado = new JLabel("Aguardando validação...");
        labelResultado.setFont(new Font("Arial", Font.BOLD, 16));
        labelResultado.setHorizontalAlignment(SwingConstants.CENTER);

        panelCampos.add(labelCNPJ);
        panelCampos.add(campoCNPJ);

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotoes.setBackground(Color.WHITE);
        panelBotoes.add(botaoValidar);
        panelBotoes.add(botaoLimpar);

        panelCampos.add(panelBotoes);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        panelPrincipal.add(labelResultado, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void configurarJanela() {
        setTitle("Validar CNPJ");
        setSize(450, 350);
        setResizable(false);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
    }

    private void validarCNPJ() {
        String cnpj = campoCNPJ.getText().trim();

        if (cnpj.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um CNPJ.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cnpjService.exibirResultado(campoCNPJ, labelResultado);
    }

    private void limpar() {
        campoCNPJ.setText("");
        campoCNPJ.setBackground(Color.WHITE);
        labelResultado.setText("Aguardando validação...");
        labelResultado.setForeground(Color.BLACK);
        campoCNPJ.requestFocus();
    }
}