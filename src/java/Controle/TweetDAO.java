
package Controle;

import Modelo.Tweet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TweetDAO{
    
    private final Connection bd;
    private final String SQLC = "INSERT INTO tweets (idusuario,mensagem) VALUES (?,?)";
    private final String SQLR = "SELECT * FROM tweets WHERE idtweet=?";
    private final String SQLU = "UPDATE tweets set idusuario=?, mensagem=? WHERE idtweet=?";
    private final String SQLD = "DELETE FROM tweets WHERE idtweet=?";
    private final String SQLALL = "SELECT * FROM tweets ORDER BY idtweet DESC";
    

public TweetDAO () { //Sempre que chamar algum metodo ele abre conexao com o banco de dados
    
         bd = ConexaoBD.abrir();
    }

public void checkTweet(Tweet tweet){ //Metodo pra identificar se existe ou não a mensagem, se existir ele chama o metodo update, caso não exista chama o metodo para criar.
    
        try{
            PreparedStatement ps = bd.prepareStatement("SELECT idtweet FROM tweets WHERE idtweet=?");
            int id = tweet.getIdtweet();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                updateTweet(tweet);
            }
            else {
                addTweet(tweet);
            }
        }
        catch(Exception e){
            System.out.println("Erro ao checar tweet: "+e.getMessage());    
        }
        }
    
    public void addTweet(Tweet tweet){ //Criar a mensagem no CRUD
    
        try{
            PreparedStatement ps = bd.prepareStatement(SQLC);
            ps.setInt(1, tweet.getIdusuario());
            ps.setString(2, tweet.getMensagem());          
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);  
        }
        
        catch(SQLException sqle){
            System.out.println("Erro no addTweet: "+sqle.getMessage());
        }
        
    }
        
    public void updateTweet(Tweet tweet){ //Atualizar a mensagem no CRUD
          
        try{
            PreparedStatement ps = bd.prepareStatement(SQLU);
            ps.setInt(1, tweet.getIdusuario());
            ps.setString(2, tweet.getMensagem());
            ps.setInt(3, tweet.getIdtweet());
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);  
        }
        
        catch(SQLException sqle){
            System.out.println("Erro no updateTweet: "+sqle.getMessage());
        }
        
    }
    
    public void deleteTweet (int id){ //Deletar a mensagem do CRUD
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLD);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);      
        }
        
        catch (SQLException sqle){
            System.out.println("Erro no deleteTweet: "+sqle.getMessage());
        
        }
    
    }
    
    public Tweet getByIdTweet(int id){ //Ler o usuario pela ID no CRUD
        Tweet tweet = new Tweet();
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLR);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                tweet.setIdtweet(rs.getInt("idtweet"));
                tweet.setMensagem(rs.getString("mensagem"));
                tweet.setIdusuario(rs.getInt("idusuario"));
            }
            
            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
        }
        catch(SQLException sqle){
            System.out.println("Erro no getByIdTweet: "+sqle.getMessage());
        }
        return tweet;
    }
    
    public List<Tweet> getAllTweets(){ //Ler todos os usuarios no CRUD
        List<Tweet> tweets = new ArrayList<Tweet>();
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLALL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Tweet t = new Tweet();
                t.setIdusuario(rs.getInt("idusuario"));
                t.setMensagem(rs.getString("mensagem"));
                t.setIdtweet(rs.getInt("idtweet"));
                tweets.add(t);
            }
            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
            
        }
        catch(SQLException sqle){
            System.out.println("Erro no getAllUsuarios: "+sqle.getMessage());
        }
        return tweets;
    }
}
        




