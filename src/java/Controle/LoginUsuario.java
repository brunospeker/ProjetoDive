
package Controle;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginUsuario extends HttpServlet {



@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String usuario = request.getParameter("user");
            String senha = request.getParameter("pass");
            Usuario log = new Usuario();
            
            Connection con = ConexaoBD.abrir();
            String slq = "SELECT * from usuarios where usuario='"+usuario+"' AND senha='"+senha+"'";
            PreparedStatement ps = con.prepareStatement(slq);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                log.setIdusuario(rs.getInt("idusuario"));
                log.setUsuario(rs.getString("usuario"));
                log.setEmail(rs.getString("email"));
                log.setSenha(rs.getString("senha"));
                log.setUrlfoto(rs.getString("urlfoto"));
                log.setAdmin(rs.getInt("admin"));
                log.setValid(true);
            }
            else{
                System.out.println("Usuario ou senha incorreta!");
                log.setValid(false);
            }
            rs.close();
            ps.close();
            ConexaoBD.fechar(con);
            
            if(log.isValid()){
                HttpSession session = request.getSession(true);
                System.out.println("Sessão Estabelecida com sucesso!");
                session.setAttribute("UsuarioLogado", log);
                response.sendRedirect("home.jsp");
            }
            else{
                request.setAttribute("notificacao", "Usuario ou senha incorreta!");
                request.getRequestDispatcher("aviso.jsp").forward(request, response);
            }
        } catch(IOException | SQLException | ServletException e){
            System.out.println("Erro ao Logar Usuario: "+e.getMessage());
        }
    }
}
