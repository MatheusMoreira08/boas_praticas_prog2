package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArquivoUtil {

    private static final String CAMINHO_CLIENTES = "src/main/resources/clientes.txt";
    private static final String CAMINHO_ESTADOS = "src/main/resources/estados.csv";

    public static void inicializarArquivos() {
        try {
            Path pathClientes = Paths.get(CAMINHO_CLIENTES);
            if (!Files.exists(pathClientes)) {
                Files.createDirectories(pathClientes.getParent());
                Files.createFile(pathClientes);
            }

            Path pathEstados = Paths.get(CAMINHO_ESTADOS);
            if (!Files.exists(pathEstados)) {
                Files.createDirectories(pathEstados.getParent());
                String conteudoCSV = "Código;UF;Estado\n1;AC;Acre\n2;AL;Alagoas\n3;AP;Amapá\n4;AM;Amazonas\n5;BA;Bahia\n6;CE;Ceará\n7;DF;Distrito Federal\n8;ES;Espírito Santo\n9;GO;Goiás\n10;MA;Maranhão\n11;MT;Mato Grosso\n12;MS;Mato Grosso do Sul\n13;MG;Minas Gerais\n14;PA;Pará\n15;PB;Paraíba\n16;PR;Paraná\n17;PE;Pernambuco\n18;PI;Piauí\n19;RJ;Rio de Janeiro\n20;RN;Rio Grande do Norte\n21;RS;Rio Grande do Sul\n22;RO;Rondônia\n23;RR;Roraima\n24;SC;Santa Catarina\n25;SP;São Paulo\n26;SE;Sergipe\n27;TO;Tocantins\n";
                Files.writeString(pathEstados, conteudoCSV);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void salvarCliente(String linha) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_CLIENTES, true))) {
            bw.write(linha);
            bw.newLine();
        }
    }

    public static StringBuilder lerArquivo(String caminho) throws IOException {
        StringBuilder conteudo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        }
        return conteudo;
    }

    public static StringBuilder lerClientes() {
        try {
            return lerArquivo(CAMINHO_CLIENTES);
        } catch (IOException e) {
            return new StringBuilder("Erro ao ler arquivo de clientes: " + e.getMessage());
        }
    }

    public static StringBuilder lerEstadosCSV() {
        try {
            return lerArquivo(CAMINHO_ESTADOS);
        } catch (IOException e) {
            return new StringBuilder("Erro ao ler arquivo de estados: " + e.getMessage());
        }
    }

    public static String getCaminhoClientes() {
        return CAMINHO_CLIENTES;
    }

    public static String getCaminhoEstados() {
        return CAMINHO_ESTADOS;
    }
}