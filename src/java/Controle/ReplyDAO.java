
package Controle;

import Modelo.Reply;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReplyDAO{
    
    private final Connection bd;
    private final String SQLC = "INSERT INTO replys (idtweet,idusuario,mensagem) VALUES (?,?,?)";
    private final String SQLR = "SELECT * FROM replys WHERE idreply=?";
    private final String SQLU = "UPDATE replys set idtweet=?, idusuario=?, mensagem=? WHERE idreply=?";
    private final String SQLD = "DELETE FROM replys WHERE idreply=?";
    private final String SQLALL = "SELECT * FROM replys";
    

public ReplyDAO () { //Sempre que chamar algum metodo ele abre conexao com o banco de dados
    
         bd = ConexaoBD.abrir();
    }

public void checkReply(Reply reply){ //Metodo pra identificar se existe ou não a resposta, se existir ele chama o metodo update, caso não exista chama o metodo para criar.
    
        try{
            PreparedStatement ps = bd.prepareStatement("SELECT id FROM replys WHERE id=?");
            int id = reply.getIdreply();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                updateReply(reply);
            }
            else {
                addReply(reply);
            }
        }
        catch(Exception e){
            System.out.println("Erro ao checar reply: "+e.getMessage());    
        }
        }
    
    public void addReply(Reply reply){ //Criar a resposta no CRUD
    
        try{
            PreparedStatement ps = bd.prepareStatement(SQLC);
            ps.setInt(1, reply.getIdtweet());
            ps.setInt(2, reply.getIdususario());
            ps.setString(3, reply.getMensagem());          
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);  
        }
        
        catch(SQLException sqle){
            System.out.println("Erro no addReply: "+sqle.getMessage());
        }
        
    }
        
    public void updateReply(Reply reply){ //Atualizar a resposta no CRUD
          
        try{
            PreparedStatement ps = bd.prepareStatement(SQLU);
            ps.setInt(1, reply.getIdtweet());
            ps.setInt(2, reply.getIdususario());
            ps.setString(3, reply.getMensagem());  
            ps.setInt(4, reply.getIdreply());
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);  
        }
        
        catch(SQLException sqle){
            System.out.println("Erro no updateTweet: "+sqle.getMessage());
        }
        
    }
    
    public void deleteReply (int id){ //Deletar a resposta no CRUD
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLD);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);      
        }
        
        catch (SQLException sqle){
            System.out.println("Erro no deleteReply: "+sqle.getMessage());
        
        }
    
    }
    
    public Reply getByIdReply(int id){ //Ler a resposta pela ID no CRUD
        Reply reply = new Reply();
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLR);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                reply.setIdreply(rs.getInt("idreply"));
                reply.setMensagem(rs.getString("mensagem"));
                reply.setIdususario(rs.getInt("idusuario"));
            }
            
            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
        }
        catch(SQLException sqle){
            System.out.println("Erro no getByIdReply: "+sqle.getMessage());
        }
        return reply;
    }
    
    public List<Reply> getAllReplys(){ //Ler todas as respostas no CRUD
        List<Reply> replys = new ArrayList<Reply>();
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLALL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Reply r = new Reply();
                r.setIdususario(rs.getInt("idusuario"));
                r.setMensagem(rs.getString("mensagem"));
                r.setIdreply(rs.getInt("idreply"));
                replys.add(r);
            }
            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
            
        }
        catch(SQLException sqle){
            System.out.println("Erro no getAllReplys: "+sqle.getMessage());
        }
        return replys;
    }
}
        