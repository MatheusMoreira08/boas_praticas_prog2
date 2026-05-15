package service;

import util.ArquivoUtil;

public class CSVService {

    public StringBuilder lerEstadosCSV() {
        return ArquivoUtil.lerEstadosCSV();
    }

    public String formatarCSVParaExibicao(StringBuilder conteudo) {
        if (conteudo == null || conteudo.length() == 0) {
            return "Arquivo vazio ou não encontrado.";
        }
        return conteudo.toString();
    }
}