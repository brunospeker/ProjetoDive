package Controle;

import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControleUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioDAO u = new UsuarioDAO();
        Usuario user = new Usuario();
        
        user.setIdusuario(Integer.parseInt(request.getParameter("idusuario")));
        user.setNome(request.getParameter("nome"));
        user.setUsuario(request.getParameter("usuario"));
        user.setEmail(request.getParameter("email"));
        user.setSenha(request.getParameter("senha"));
        user.setUrlfoto(request.getParameter("urlfoto"));
        user.setAdmin(Integer.parseInt(request.getParameter("admin")));
        
        u.updateUsuario(user);
        response.sendRedirect("settings.jsp");
    }
}
