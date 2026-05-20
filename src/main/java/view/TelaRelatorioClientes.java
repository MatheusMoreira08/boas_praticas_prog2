package view;

import service.ClienteService;
import javax.swing.*;
import java.awt.*;

public class TelaRelatorioClientes extends JInternalFrame {

    private JTextArea textArea;
    private ClienteService clienteService;

    public TelaRelatorioClientes() {
        this.clienteService = new ClienteService();
        inicializarComponentes();
        configurarJanela();
        carregarRelatorio();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 102, 204));
        JLabel titulo = new JLabel("Relatório de Clientes Cadastrados");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setBackground(new Color(248, 248, 248));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(550, 450));

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotoes.setBackground(Color.WHITE);

        JButton botaoAtualizar = new JButton("Atualizar Relatório");
        botaoAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoAtualizar.setBackground(new Color(0, 102, 204));
        botaoAtualizar.setForeground(Color.BLACK);
        botaoAtualizar.setFocusPainted(false);
        botaoAtualizar.addActionListener(e -> carregarRelatorio());

        JButton botaoExportar = new JButton("Exportar");
        botaoExportar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoExportar.setBackground(new Color(0, 153, 0));
        botaoExportar.setForeground(Color.BLACK);
        botaoExportar.setFocusPainted(false);
        botaoExportar.addActionListener(e -> exportarRelatorio());

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoFechar.setBackground(new Color(200, 0, 0));
        botaoFechar.setForeground(Color.BLACK);
        botaoFechar.setFocusPainted(false);
        botaoFechar.addActionListener(e -> dispose());

        panelBotoes.add(botaoAtualizar);
        panelBotoes.add(botaoExportar);
        panelBotoes.add(botaoFechar);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void configurarJanela() {
        setTitle("Relatório de Clientes");
        setSize(650, 550);
        setResizable(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
    }

    private void carregarRelatorio() {
        String relatorio = clienteService.gerarRelatorio();
        textArea.setText(relatorio);
    }

    private void exportarRelatorio() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Relatório");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            try (java.io.FileWriter writer = new java.io.FileWriter(fileToSave)) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this,
                        "Relatório exportado com sucesso!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao exportar relatório: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}