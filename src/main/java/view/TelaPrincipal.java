package view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private JDesktopPane desktopPane;

    public TelaPrincipal() {
        inicializarComponentes();
        configurarJanela();
    }

    private void inicializarComponentes() {
        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(220, 220, 220));

        JMenuBar menuBar = new JMenuBar();

        JMenu menuValidacoes = new JMenu("Validações");
        menuValidacoes.setFont(new Font("Arial", Font.BOLD, 12));

        JMenuItem itemCPF = new JMenuItem("Validar CPF");
        itemCPF.addActionListener(e -> abrirTela(new TelaCPF()));
        menuValidacoes.add(itemCPF);

        JMenuItem itemCNPJ = new JMenuItem("Validar CNPJ");
        itemCNPJ.addActionListener(e -> abrirTela(new TelaCNPJ()));
        menuValidacoes.add(itemCNPJ);

        JMenuItem itemEmail = new JMenuItem("Validar E-mail");
        itemEmail.addActionListener(e -> abrirTela(new TelaEmail()));
        menuValidacoes.add(itemEmail);

        JMenuItem itemLogin = new JMenuItem("Login");
        itemLogin.addActionListener(e -> abrirTela(new TelaLogin()));
        menuValidacoes.add(itemLogin);

        JMenu menuCadastro = new JMenu("Clientes");
        menuCadastro.setFont(new Font("Arial", Font.BOLD, 12));

        JMenuItem itemCadastroCliente = new JMenuItem("Cadastrar Cliente");
        itemCadastroCliente.addActionListener(e -> abrirTela(new TelaCadastroCliente()));
        menuCadastro.add(itemCadastroCliente);

        JMenuItem itemRelatorio = new JMenuItem("Relatório de Clientes");
        itemRelatorio.addActionListener(e -> abrirTela(new TelaRelatorioClientes()));
        menuCadastro.add(itemRelatorio);

        JMenu menuArquivos = new JMenu("Arquivos");
        menuArquivos.setFont(new Font("Arial", Font.BOLD, 12));

        JMenuItem itemLerCSV = new JMenuItem("Ler Estados CSV");
        itemLerCSV.addActionListener(e -> abrirTela(new TelaLerCSV()));
        menuArquivos.add(itemLerCSV);

        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjuda.setFont(new Font("Arial", Font.BOLD, 12));

        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> mostrarSobre());
        menuAjuda.add(itemSobre);

        menuBar.add(menuValidacoes);
        menuBar.add(menuCadastro);
        menuBar.add(menuArquivos);
        menuBar.add(menuAjuda);

        setJMenuBar(menuBar);
        add(desktopPane, BorderLayout.CENTER);
    }

    private void configurarJanela() {
        setTitle("Sistema de Clientes - Fase 2");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void abrirTela(JInternalFrame tela) {
        desktopPane.add(tela);
        tela.setVisible(true);
        centralizarTela(tela);
        try {
            tela.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void centralizarTela(JInternalFrame tela) {
        int desktopWidth = desktopPane.getWidth();
        int desktopHeight = desktopPane.getHeight();
        int frameWidth = tela.getWidth();
        int frameHeight = tela.getHeight();

        int x = Math.max(0, (desktopWidth - frameWidth) / 2);
        int y = Math.max(0, (desktopHeight - frameHeight) / 2);

        tela.setLocation(x, y);
    }

    private void mostrarSobre() {
        JOptionPane.showMessageDialog(this,
            "Sistema de Clientes - Fase 2\n\n" +
            "Disciplina: Boas Práticas e Padrões de Projetos\n\n" +
            "Funcionalidades:\n" +
            "- Validação de CPF\n" +
            "- Validação de CNPJ\n" +
            "- Validação de E-mail\n" +
            "- Sistema de Login\n" +
            "- Cadastro de Clientes\n" +
            "- Leitura de CSV\n" +
            "- Relatório de Clientes\n\n" +
            "Versão: 1.0.0",
            "Sobre o Sistema",
            JOptionPane.INFORMATION_MESSAGE);
    }
}