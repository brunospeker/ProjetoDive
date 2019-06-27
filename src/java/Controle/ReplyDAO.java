package Controle;

import Modelo.Reply;
import java.sql.*;
import java.util.*;

public class ReplyDAO {

    private final Connection bd;
    private final String SQLC = "INSERT INTO replys (idtweet,idusuario,mensagem) VALUES (?,?,?)";
    private final String SQLR = "SELECT * FROM replys WHERE idreply=?";
    private final String SQLU = "UPDATE replys set idtweet=?, idusuario=?, mensagem=? WHERE idreply=?";
    private final String SQLD = "DELETE FROM replys WHERE idreply=?";
    private final String SQLALL = "SELECT * FROM replys WHERE idtweet=? ORDER BY idreply DESC";
    private final String SQLDELETEALL = "DELETE FROM replys WHERE idtweet=?";

    public ReplyDAO() { //Sempre que chamar algum metodo ele abre conexao com o banco de dados
        bd = ConexaoBD.abrir();
    }

    public void addReply(Reply reply) { //Criar a resposta no CRUD

        try {
            PreparedStatement ps = bd.prepareStatement(SQLC);
            ps.setInt(1, reply.getIdtweet());
            ps.setInt(2, reply.getIdususario());
            ps.setString(3, reply.getMensagem());
            ps.executeUpdate();

            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no addReply: " + sqle.getMessage());
        }
    }

    public void updateReply(Reply reply) { //Atualizar a resposta no CRUD

        try {
            PreparedStatement ps = bd.prepareStatement(SQLU);
            ps.setInt(1, reply.getIdtweet());
            ps.setInt(2, reply.getIdususario());
            ps.setString(3, reply.getMensagem());
            ps.setInt(4, reply.getIdreply());
            ps.executeUpdate();

            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no updateTweet: " + sqle.getMessage());
        }
    }

    public void deleteReply(int id) { //Deletar a resposta no CRUD

        try {
            PreparedStatement ps = bd.prepareStatement(SQLD);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no deleteReply: " + sqle.getMessage());
        }
    }

    public Reply getByIdReply(int id) { //Ler a resposta pela ID no CRUD
        Reply reply = new Reply();

        try {
            PreparedStatement ps = bd.prepareStatement(SQLR);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                reply.setIdreply(rs.getInt("idreply"));
                reply.setMensagem(rs.getString("mensagem"));
                reply.setIdususario(rs.getInt("idusuario"));
            }

            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no getByIdReply: " + sqle.getMessage());
        }
        return reply;
    }

    public List<Reply> getAllReplys(int tweet) { //Ler todas as respostas no CRUD
        List<Reply> replys = new ArrayList<Reply>();

        try {
            PreparedStatement ps = bd.prepareStatement(SQLALL);
            ps.setInt(1, tweet);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reply r = new Reply();
                r.setIdususario(rs.getInt("idusuario"));
                r.setMensagem(rs.getString("mensagem"));
                r.setIdreply(rs.getInt("idreply"));
                replys.add(r);
            }
            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no getAllReplys: " + sqle.getMessage());
        }
        return replys;
    }

    public void deleteTodosReplysDoTweet(int id) { //Deletar a resposta no CRUD

        try {
            PreparedStatement ps = bd.prepareStatement(SQLDELETEALL);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no deleteTodosReplys: " + sqle.getMessage());
        }
    }
}