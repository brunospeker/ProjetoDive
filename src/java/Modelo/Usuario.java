package Modelo;
/*Criação da classe padrão, com get e sets e booleano para validação de login*/
public class Usuario {
    private int idusuario;
    private String usuario;
    private String email;
    private String senha;
    private String urlfoto;
    private int admin;
    private boolean valid;

    public Usuario() {
    }

    public Usuario(int idusuario, String usuario, String email, String senha, String urlfoto, int admin) {
        this.idusuario = idusuario;
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.urlfoto = urlfoto;
        this.admin = admin;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}