package service;

import model.Cliente;
import util.ArquivoUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private final CPFService cpfService;
    private final EmailService emailService;

    public ClienteService() {
        this.cpfService = new CPFService();
        this.emailService = new EmailService();
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (!cpfService.validarCPF(cliente.getCpf())) {
            return false;
        }
        if (!emailService.validarEmail(cliente.getEmail())) {
            return false;
        }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            return false;
        }

        try {
            ArquivoUtil.salvarCliente(cliente.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> lerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        StringBuilder conteudo = ArquivoUtil.lerClientes();
        String[] linhas = conteudo.toString().split("\n");

        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) {
                String[] partes = linha.split(";");
                if (partes.length >= 3) {
                    Cliente cliente = new Cliente(partes[0].trim(), partes[1].trim(), partes[2].trim());
                    clientes.add(cliente);
                }
            }
        }
        return clientes;
    }

    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("=".repeat(60)).append("\n");
        sb.append("           RELATÓRIO DE CLIENTES CADASTRADOS\n");
        sb.append("=".repeat(60)).append("\n\n");

        List<Cliente> clientes = lerClientes();

        if (clientes.isEmpty()) {
            sb.append("Nenhum cliente cadastrado.\n");
        } else {
            sb.append(String.format("%-5s %-25s %-15s %-30s\n", "Nº", "NOME", "CPF", "E-MAIL"));
            sb.append("-".repeat(65)).append("\n");

            int contador = 1;
            for (Cliente cliente : clientes) {
                sb.append(String.format("%-5d %-25s %-15s %-30s\n",
                    contador++,
                    limitarTexto(cliente.getNome(), 25),
                    cliente.getCpf(),
                    limitarTexto(cliente.getEmail(), 30)));
            }

            sb.append("\n").append("-".repeat(65)).append("\n");
            sb.append("Total de clientes: ").append(clientes.size()).append("\n");
        }

        sb.append("=".repeat(60)).append("\n");
        return sb.toString();
    }

    private String limitarTexto(String texto, int max) {
        if (texto == null) return "";
        if (texto.length() <= max) return texto;
        return texto.substring(0, max - 3) + "...";
    }
}