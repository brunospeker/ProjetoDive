package Modelo;
/*Criação da classe reply padronizado, com os elementos para serem usados*/
public class Reply {
    private int idreply;
    private int idtweet;
    private int idususario;
    private String mensagem;

    public Reply() {
    }

    public Reply(int idreply, int idtweet, int idususario, String mensagem) {
        this.idreply = idreply;
        this.idtweet = idtweet;
        this.idususario = idususario;
        this.mensagem = mensagem;
    }

    public int getIdreply() {
        return idreply;
    }

    public void setIdreply(int idreply) {
        this.idreply = idreply;
    }

    public int getIdtweet() {
        return idtweet;
    }

    public void setIdtweet(int idtweet) {
        this.idtweet = idtweet;
    }

    public int getIdususario() {
        return idususario;
    }

    public void setIdususario(int idususario) {
        this.idususario = idususario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
}
