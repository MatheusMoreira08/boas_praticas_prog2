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
        panelCampos.setLayout(new GridLayout(3, 2, 10, 15));
        panelCampos.setBackground(Color.WHITE);
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        labelUsuario.setHorizontalAlignment(SwingConstants.RIGHT);

        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        labelSenha.setHorizontalAlignment(SwingConstants.RIGHT);

        campoSenha = new JPasswordField();
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel labelEspaco = new JLabel();

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoEntrar.setBackground(new Color(0, 153, 0));
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setFocusPainted(false);
        botaoEntrar.addActionListener(e -> realizarLogin());

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoLimpar.setBackground(new Color(100, 100, 100));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.addActionListener(e -> limpar());

        panelCampos.add(labelUsuario);
        panelCampos.add(campoUsuario);
        panelCampos.add(labelSenha);
        panelCampos.add(campoSenha);
        panelCampos.add(labelEspaco);
        panelCampos.add(botaoLimpar);
        panelCampos.add(new JLabel());
        panelCampos.add(botaoEntrar);

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(new Color(240, 240, 240));
        JLabel labelInfo = new JLabel("<html><center>Usuários disponíveis:<br>admin / 123<br>matheus / 123456<br>usuario / senha123</center></html>");
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