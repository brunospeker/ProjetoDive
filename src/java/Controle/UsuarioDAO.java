package Controle;

import Modelo.Usuario;
import java.sql.*;
import java.util.*;



public class UsuarioDAO {
    
    private final Connection bd;
    private final String SQLC = "INSERT INTO usuarios (usuario,senha,email,urlfoto,admin) VALUES (?,?,?,?,?)";
    private final String SQLR = "SELECT * FROM usuarios WHERE id=?";
    private final String SQLU = "UPDATE usuarios set usuario=?, senha=?, email=?, urlfoto=?, admin=? WHERE id=?";
    private final String SQLD = "DELETE FROM usuarios WHERE id=?";
    private final String SQLALL = "SELECT * FROM usuarios";
    
    public UsuarioDAO () { //Sempre que chamar algum metodo ele abre conexao com o banco de dados
    
         bd = ConexaoBD.abrir();
    }
    
    public void checkUsuario(Usuario user){ //Metodo pra identificar se existe ou não usuario, se existir ele chama o metodo update, caso não exista chama o metodo para criar.
    
        try{
            PreparedStatement ps = bd.prepareStatement("SELECT id FROM usuarios WHERE id=?");
            int id = user.getIdusuario();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                updateUsuario(user);
            }
            else {
                addUsuario(user);
            }
        }
        catch(Exception e){
            System.out.println("Erro ao checar usuario: "+e.getMessage());    
        }
        }
    
    public void addUsuario(Usuario user){ //Criar o usuario no CRUD
    
        try{
            PreparedStatement ps = bd.prepareStatement(SQLC);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getSenha());            
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUrlfoto());
            ps.setInt(5, user.getAdmin());  
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);  
        }
        
        catch(SQLException sqle){
            System.out.println("Erro no addUsuario: "+sqle.getMessage());
        }
        
    }
        
    public void updateUsuario(Usuario user){ //Atualizar o usuario no CRUD
          
        try{
            PreparedStatement ps = bd.prepareStatement(SQLU);
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getSenha());            
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUrlfoto());
            ps.setInt(5, user.getAdmin());  
            ps.setInt(6, user.getIdusuario());
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);  
        }
        
        catch(SQLException sqle){
            System.out.println("Erro no updateUsuario: "+sqle.getMessage());
        }
        
    }
    
    public void deleteUsuario (int id){ //Deletar usuario do CRUD
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLD);
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            ConexaoBD.fechar(bd);      
        }
        
        catch (SQLException sqle){
            System.out.println("Erro no deleteUsuario: "+sqle.getMessage());
        
        }
    
    }
    
    public Usuario getByIdUsuario(int id){ //Ler o usuario pela ID no CRUD
        Usuario user = new Usuario();
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLR);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                user.setIdusuario(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setUrlfoto(rs.getString("urlfoto"));
                user.setAdmin(rs.getInt("admin"));
            }
            
            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
        }
        catch(SQLException sqle){
            System.out.println("Erro no getByIdUsuario: "+sqle.getMessage());
        }
        return user;
    }
    
    public List<Usuario> getAllUsuarios(){ //Ler todos os usuarios no CRUD
        List<Usuario> users = new ArrayList<Usuario>();
        
        try{
            PreparedStatement ps = bd.prepareStatement(SQLALL);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setIdusuario(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setUrlfoto(rs.getString("urlfoto"));
                u.setAdmin(rs.getInt("admin"));
                users.add(u);
            }
            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
            
        }
        catch(SQLException sqle){
            System.out.println("Erro no getAllUsuarios: "+sqle.getMessage());
        }
        return users;
    }
        
    
    }
