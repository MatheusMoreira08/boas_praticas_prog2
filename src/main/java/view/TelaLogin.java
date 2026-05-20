package view;

import service.LoginService;
import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JInternalFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoLimpar;
    private LoginService loginService;

    public TelaLogin() {
        this.loginService = new LoginService();
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
        JLabel titulo = new JLabel("Login do Sistema");
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

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        labelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        panelCampos.add(labelUsuario, gbc);

        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        campoUsuario.setPreferredSize(new Dimension(220, 35));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        panelCampos.add(campoUsuario, gbc);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSenha.setHorizontalAlignment(SwingConstants.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        panelCampos.add(labelSenha, gbc);

        campoSenha = new JPasswordField();
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        campoSenha.setPreferredSize(new Dimension(220, 35));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.8;
        panelCampos.add(campoSenha, gbc);

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoLimpar.setBackground(new Color(100, 100, 100));
        botaoLimpar.setForeground(Color.BLACK);
        botaoLimpar.setPreferredSize(new Dimension(140, 40));
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.addActionListener(e -> limpar());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        panelCampos.add(botaoLimpar, gbc);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoEntrar.setBackground(new Color(0, 153, 0));
        botaoEntrar.setForeground(Color.BLACK);
        botaoEntrar.setPreferredSize(new Dimension(140, 40));
        botaoEntrar.setFocusPainted(false);
        botaoEntrar.addActionListener(e -> realizarLogin());
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        panelCampos.add(botaoEntrar, gbc);

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(240, 240, 240));
        JLabel labelInfo = new JLabel(
                "<html><center>Usuários disponíveis:<br>admin / 123<br>matheus / 123456<br>usuario / senha123</center></html>");
        labelInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        panelInfo.add(labelInfo);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        panelPrincipal.add(panelInfo, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void configurarJanela() {
        setTitle("Login");
        setSize(400, 380);
        setResizable(false);
        setClosable(true);
        setIconifiable(true);
    }

    private void realizarLogin() {
        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());

        String mensagemErro = loginService.getMensagemErro(usuario, senha);

        if (mensagemErro.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + usuario + "!",
                    "Login Efetuado",
                    JOptionPane.INFORMATION_MESSAGE);
            limpar();
        } else {
            JOptionPane.showMessageDialog(this,
                    mensagemErro,
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        campoUsuario.setText("");
        campoSenha.setText("");
        campoUsuario.requestFocus();
    }
}