package Modelo;
/*Criação da classe tweet padronizado, com os elementos para serem usados*/
public class Tweet {
    private int idtweet;
    private int idusuario;
    private String mensagem;

    public Tweet() {
    }

    public Tweet(int idtweet, int idusuario, String mensagem) {
        this.idtweet = idtweet;
        this.idusuario = idusuario;
        this.mensagem = mensagem;
    }

    public int getIdtweet() {
        return idtweet;
    }

    public void setIdtweet(int idtweet) {
        this.idtweet = idtweet;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
