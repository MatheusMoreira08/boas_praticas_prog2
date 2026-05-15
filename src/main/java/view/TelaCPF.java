package view;

import service.CPFService;
import javax.swing.*;
import java.awt.*;

public class TelaCPF extends JInternalFrame {

    private JTextField campoCPF;
    private JLabel labelResultado;
    private CPFService cpfService;

    public TelaCPF() {
        this.cpfService = new CPFService();
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
        JLabel titulo = new JLabel("Validação de CPF");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(3, 1, 10, 10));
        panelCampos.setBackground(Color.WHITE);

        JLabel labelCPF = new JLabel("Digite o CPF:");
        labelCPF.setFont(new Font("Arial", Font.PLAIN, 14));

        campoCPF = new JTextField();
        campoCPF.setFont(new Font("Arial", Font.PLAIN, 16));
        campoCPF.setHorizontalAlignment(JTextField.CENTER);
        campoCPF.setPreferredSize(new Dimension(300, 40));

        JButton botaoValidar = new JButton("Validar CPF");
        botaoValidar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoValidar.setBackground(new Color(0, 102, 204));
        botaoValidar.setForeground(Color.WHITE);
        botaoValidar.setPreferredSize(new Dimension(150, 40));
        botaoValidar.setFocusPainted(false);
        botaoValidar.addActionListener(e -> validarCPF());

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoLimpar.setBackground(new Color(100, 100, 100));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setPreferredSize(new Dimension(150, 40));
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.addActionListener(e -> limpar());

        labelResultado = new JLabel("Aguardando validação...");
        labelResultado.setFont(new Font("Arial", Font.BOLD, 16));
        labelResultado.setHorizontalAlignment(SwingConstants.CENTER);

        panelCampos.add(labelCPF);
        panelCampos.add(campoCPF);

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
        setTitle("Validar CPF");
        setSize(450, 350);
        setResizable(false);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
    }

    private void validarCPF() {
        String cpf = campoCPF.getText().trim();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um CPF.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cpfService.exibirResultado(campoCPF, labelResultado);
    }

    private void limpar() {
        campoCPF.setText("");
        campoCPF.setBackground(Color.WHITE);
        labelResultado.setText("Aguardando validação...");
        labelResultado.setForeground(Color.BLACK);
        campoCPF.requestFocus();
    }
}