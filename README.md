# Sistema de Clientes - Fase 2

Aplicação Desktop Java com interface Swing seguindo o padrão MDI (Multiple Document Interface).

## Estrutura do Projeto

```
src/main/java/
├── app/
│   └── Main.java                    # Classe principal
├── view/
│   ├── TelaPrincipal.java           # Janela principal com JDesktopPane
│   ├── TelaCPF.java                # Validação de CPF
│   ├── TelaCNPJ.java                # Validação de CNPJ
│   ├── TelaEmail.java              # Validação de E-mail
│   ├── TelaLogin.java              # Sistema de Login
│   ├── TelaCadastroCliente.java    # Cadastro de Clientes
│   ├── TelaLerCSV.java             # Leitura de CSV
│   └── TelaRelatorioClientes.java  # Relatório de Clientes
├── service/
│   ├── CPFService.java             # Lógica de validação CPF
│   ├── CNPJService.java            # Lógica de validação CNPJ
│   ├── EmailService.java           # Lógica de validação E-mail
│   ├── LoginService.java           # Lógica de Login
│   ├── ClienteService.java        # Lógica de Clientes
│   └── CSVService.java             # Lógica de Leitura CSV
├── model/
│   └── Cliente.java               # Modelo Cliente
└── util/
    └── ArquivoUtil.java           # Utilitários de arquivo
```

## Requisitos

- Java 20+
- Maven 3.6+

## Como Executar

```bash
cd boas_praticas_prog2
mvn compile exec:java
```

## Funcionalidades

| Funcionalidade | Descrição |
|----------------|-----------|
| Validar CPF | Validação real usando algoritmo de dígitos verificadores |
| Validar CNPJ | Validação real usando algoritmo de dígitos verificadores |
| Validar E-mail | Validação via regex |
| Login | Sistema com usuários fixos (admin/123, matheus/123456) |
| Cadastro Cliente | Salva em arquivo TXT (clientes.txt) |
| Ler CSV | Lê e exibe arquivo estados.csv |
| Relatório | Lista todos os clientes cadastrados |

## Usuários para Login

| Usuário | Senha |
|---------|-------|
| admin | 123 |
| matheus | 123456 |
| usuario | senha123 |# boas_praticas_prog2
