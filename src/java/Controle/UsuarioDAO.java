package Controle;

import Modelo.Usuario;
import java.sql.*;
import java.util.*;

public class UsuarioDAO {

    private final Connection bd;
    private final String SQLC = "INSERT INTO usuarios (nome,usuario,senha,email,urlfoto,admin) VALUES (?,?,?,?,?,?)";
    private final String SQLR = "SELECT * FROM usuarios WHERE idusuario=?";
    private final String SQLU = "UPDATE usuarios set nome=?, usuario=?, senha=?, email=?, urlfoto=?, admin=? WHERE idusuario=?";
    private final String SQLD = "DELETE FROM usuarios WHERE idusuario=?";
    private final String SQLALL = "SELECT * FROM usuarios";

    public UsuarioDAO() { //Sempre que chamar algum metodo ele abre conexao com o banco de dados
        bd = ConexaoBD.abrir();
    }

    public void addUsuario(Usuario user) { //Criar o usuario no CRUD

        try {
            PreparedStatement ps = bd.prepareStatement(SQLC);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getUsuario());
            ps.setString(3, user.getSenha());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUrlfoto());
            ps.setInt(6, user.getAdmin());
            ps.executeUpdate();

            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no addUsuario: " + sqle.getMessage());
        }

    }

    public void updateUsuario(Usuario user) { //Atualizar o usuario no CRUD

        try {
            PreparedStatement ps = bd.prepareStatement(SQLU);
            ps.setString(1, user.getNome());
            ps.setString(2, user.getUsuario());
            ps.setString(3, user.getSenha());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUrlfoto());
            ps.setInt(6, user.getAdmin());
            ps.setInt(7, user.getIdusuario());
            ps.executeUpdate();

            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no updateUsuario: " + sqle.getMessage());
        }

    }

    public void deleteUsuario(int id) { //Deletar usuario do CRUD

        try {
            PreparedStatement ps = bd.prepareStatement(SQLD);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no deleteUsuario: " + sqle.getMessage());

        }

    }

    public Usuario getByIdUsuario(int id) { //Ler o usuario pela ID no CRUD
        Usuario user = new Usuario();

        try {
            PreparedStatement ps = bd.prepareStatement(SQLR);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setIdusuario(rs.getInt("idusuario"));
                user.setNome(rs.getString("nome"));
                user.setUsuario(rs.getString("usuario"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                user.setUrlfoto(rs.getString("urlfoto"));
                user.setAdmin(rs.getInt("admin"));
            }

            rs.close();
            ps.close();
            ConexaoBD.fechar(bd);
        } catch (SQLException sqle) {
            System.out.println("Erro no getByIdUsuario: " + sqle.getMessage());
        }
        return user;
    }

    public List<Usuario> getAllUsuarios() { //Ler todos os usuarios no CRUD
        List<Usuario> users = new ArrayList<Usuario>();

        try {
            PreparedStatement ps = bd.prepareStatement(SQLALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdusuario(rs.getInt("idusuario"));
                u.setNome(rs.getString("nome"));
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
        } catch (SQLException sqle) {
            System.out.println("Erro no getAllUsuarios: " + sqle.getMessage());
        }
        return users;
    }
}