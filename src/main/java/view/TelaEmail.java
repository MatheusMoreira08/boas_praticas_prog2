package view;

import service.EmailService;
import javax.swing.*;
import java.awt.*;

public class TelaEmail extends JInternalFrame {

    private JTextField campoEmail;
    private JLabel labelResultado;
    private EmailService emailService;

    public TelaEmail() {
        this.emailService = new EmailService();
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
        JLabel titulo = new JLabel("Validação de E-mail");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(3, 1, 10, 10));
        panelCampos.setBackground(Color.WHITE);

        JLabel labelEmail = new JLabel("Digite o E-mail:");
        labelEmail.setFont(new Font("Arial", Font.PLAIN, 14));

        campoEmail = new JTextField();
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        campoEmail.setHorizontalAlignment(JTextField.CENTER);
        campoEmail.setPreferredSize(new Dimension(300, 40));

        JButton botaoValidar = new JButton("Validar E-mail");
        botaoValidar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoValidar.setBackground(new Color(0, 102, 204));
        botaoValidar.setForeground(Color.BLACK);
        botaoValidar.setPreferredSize(new Dimension(150, 40));
        botaoValidar.setFocusPainted(false);
        botaoValidar.addActionListener(e -> validarEmail());

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

        panelCampos.add(labelEmail);
        panelCampos.add(campoEmail);

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
        setTitle("Validar E-mail");
        setSize(450, 350);
        setResizable(false);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
    }

    private void validarEmail() {
        String email = campoEmail.getText().trim();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um e-mail.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        emailService.exibirResultado(campoEmail, labelResultado);
    }

    private void limpar() {
        campoEmail.setText("");
        campoEmail.setBackground(Color.WHITE);
        labelResultado.setText("Aguardando validação...");
        labelResultado.setForeground(Color.BLACK);
        campoEmail.requestFocus();
    }
}