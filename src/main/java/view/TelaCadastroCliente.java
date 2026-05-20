package view;

import model.Cliente;
import service.ClienteService;
import service.CPFService;
import service.EmailService;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroCliente extends JInternalFrame {

    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoEmail;
    private JButton botaoSalvar;
    private JButton botaoLimpar;
    private ClienteService clienteService;
    private CPFService cpfService;
    private EmailService emailService;

    public TelaCadastroCliente() {
        this.clienteService = new ClienteService();
        this.cpfService = new CPFService();
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
        JLabel titulo = new JLabel("Cadastro de Cliente");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridBagLayout());
        panelCampos.setBackground(Color.WHITE);
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelNome = new JLabel("Nome Completo:");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 14));

        campoNome = new JTextField();
        campoNome.setFont(new Font("Arial", Font.PLAIN, 14));
        campoNome.setPreferredSize(new Dimension(300, 35));

        JLabel labelCPF = new JLabel("CPF:");
        labelCPF.setFont(new Font("Arial", Font.PLAIN, 14));

        campoCPF = new JTextField();
        campoCPF.setFont(new Font("Arial", Font.PLAIN, 14));
        campoCPF.setPreferredSize(new Dimension(300, 35));

        JLabel labelEmail = new JLabel("E-mail:");
        labelEmail.setFont(new Font("Arial", Font.PLAIN, 14));

        campoEmail = new JTextField();
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        campoEmail.setPreferredSize(new Dimension(300, 35));

        botaoSalvar = new JButton("Salvar Cliente");
        botaoSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoSalvar.setBackground(new Color(0, 153, 0));
        botaoSalvar.setForeground(Color.BLACK);
        botaoSalvar.setPreferredSize(new Dimension(150, 40));
        botaoSalvar.setFocusPainted(false);
        botaoSalvar.addActionListener(e -> salvarCliente());

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoLimpar.setBackground(new Color(100, 100, 100));
        botaoLimpar.setForeground(Color.BLACK);
        botaoLimpar.setPreferredSize(new Dimension(150, 40));
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.addActionListener(e -> limpar());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add(labelNome, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelCampos.add(campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panelCampos.add(labelCPF, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelCampos.add(campoCPF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelCampos.add(labelEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelCampos.add(campoEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panelCampos.add(botaoLimpar, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        panelCampos.add(botaoSalvar, gbc);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void configurarJanela() {
        setTitle("Cadastrar Cliente");
        setSize(550, 350);
        setResizable(false);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
    }

    private void salvarCliente() {
        String nome = campoNome.getText().trim();
        String cpf = campoCPF.getText().trim();
        String email = campoEmail.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo nome é obrigatório.", "Aviso", JOptionPane.WARNING_MESSAGE);
            campoNome.requestFocus();
            return;
        }

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo CPF é obrigatório.", "Aviso", JOptionPane.WARNING_MESSAGE);
            campoCPF.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campo e-mail é obrigatório.", "Aviso", JOptionPane.WARNING_MESSAGE);
            campoEmail.requestFocus();
            return;
        }

        if (!cpfService.validarCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            campoCPF.requestFocus();
            return;
        }

        if (!emailService.validarEmail(email)) {
            JOptionPane.showMessageDialog(this, "E-mail inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            campoEmail.requestFocus();
            return;
        }

        Cliente cliente = new Cliente(nome, cpf, email);

        if (clienteService.cadastrarCliente(cliente)) {
            JOptionPane.showMessageDialog(this,
                    "Cliente cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar cliente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        campoNome.setText("");
        campoCPF.setText("");
        campoEmail.setText("");
        campoNome.requestFocus();
    }
}