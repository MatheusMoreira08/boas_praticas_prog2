package service;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private static final Map<String, String> USUARIOS = new HashMap<>();

    static {
        USUARIOS.put("admin", "123");
        USUARIOS.put("matheus", "123456");
        USUARIOS.put("usuario", "senha123");
    }

    public boolean validarLogin(String usuario, String senha) {
        if (usuario == null || usuario.isEmpty() || senha == null || senha.isEmpty()) {
            return false;
        }
        return USUARIOS.containsKey(usuario) && USUARIOS.get(usuario).equals(senha);
    }

    public String getMensagemErro(String usuario, String senha) {
        if (usuario == null || usuario.isEmpty()) {
            return "Campo usuário obrigatório";
        }
        if (senha == null || senha.isEmpty()) {
            return "Campo senha obrigatório";
        }
        if (!USUARIOS.containsKey(usuario)) {
            return "Usuário não encontrado";
        }
        if (!USUARIOS.get(usuario).equals(senha)) {
            return "Senha incorreta";
        }
        return "";
    }

    public boolean isUsuarioExistente(String usuario) {
        return USUARIOS.containsKey(usuario);
    }
}