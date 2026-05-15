package view;

import service.CSVService;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class TelaLerCSV extends JInternalFrame {

    private JTextArea textArea;
    private CSVService csvService;

    public TelaLerCSV() {
        this.csvService = new CSVService();
        inicializarComponentes();
        configurarJanela();
        carregarCSV();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.setBackground(Color.WHITE);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 102, 204));
        JLabel titulo = new JLabel("Leitura de Arquivo CSV - Estados");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        textArea = new JTextArea();
        textArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setBackground(new Color(248, 248, 248));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotoes.setBackground(Color.WHITE);

        JButton botaoAtualizar = new JButton("Atualizar");
        botaoAtualizar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoAtualizar.setBackground(new Color(0, 102, 204));
        botaoAtualizar.setForeground(Color.WHITE);
        botaoAtualizar.setFocusPainted(false);
        botaoAtualizar.addActionListener(e -> carregarCSV());

        JButton botaoCopiar = new JButton("Copiar");
        botaoCopiar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoCopiar.setBackground(new Color(100, 100, 100));
        botaoCopiar.setForeground(Color.WHITE);
        botaoCopiar.setFocusPainted(false);
        botaoCopiar.addActionListener(e -> copiarConteudo());

        JButton botaoFechar = new JButton("Fechar");
        botaoFechar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoFechar.setBackground(new Color(200, 0, 0));
        botaoFechar.setForeground(Color.WHITE);
        botaoFechar.setFocusPainted(false);
        botaoFechar.addActionListener(e -> dispose());

        panelBotoes.add(botaoAtualizar);
        panelBotoes.add(botaoCopiar);
        panelBotoes.add(botaoFechar);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelBotoes, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void configurarJanela() {
        setTitle("Ler Estados CSV");
        setSize(600, 500);
        setResizable(true);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
    }

    private void carregarCSV() {
        StringBuilder conteudo = csvService.lerEstadosCSV();
        textArea.setText(conteudo.toString());
    }

    private void copiarConteudo() {
        StringSelection selection = new StringSelection(textArea.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        JOptionPane.showMessageDialog(this, "Conteúdo copiado para a área de transferência!");
    }
}