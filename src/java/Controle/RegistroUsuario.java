package Controle;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistroUsuario extends HttpServlet {
    /*Servlet de cadastro de novos usuarios.
    Ela é usada na pagina registro.jsp e é disponivel para usuarios novos.
    Caso o usuario deixe de marcar algo ou tenha marcado algo que não possa ser repetido
    ele é mandado para uma pagina de aviso mostrando o erro dele.*/
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String usuario = request.getParameter("usuario");
        String email = request.getParameter("email");
        String senha = request.getParameter("password");
        String urlfoto = request.getParameter("urlfoto");
        
        try{
            Connection con = ConexaoBD.abrir();
            String sql = "INSERT INTO usuarios(usuario, email, senha, urlfoto) values(?, ?, ?, ? )";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, usuario);
            ps.setString(2, email);
            ps.setString(3, senha);
            ps.setString(4, urlfoto);
            
            ps.executeUpdate();
            ps.close();
            ConexaoBD.fechar(con);
            if(request.getParameter("usuario")==null || request.getParameter("email")==null || request.getParameter("senha")==null){
                request.setAttribute("notificacao", "Você marcou algum campo errado ou deixou de marcar.");
                request.getRequestDispatcher("aviso.jsp").forward(request, response);
            }else{
                request.setAttribute("notificacao", "Cadastro realizado com sucesso.");
                request.getRequestDispatcher("/aviso.jsp").forward(request, response);
            }
        }catch(SQLException sqle){
            System.out.println("Erro ao usuario se registrar: " + sqle.getMessage());
            request.setAttribute("notificacao", "Usuario ou email já resgistrado.");
            request.getRequestDispatcher("aviso.jsp").forward(request, response);
        }
    }
}