package Controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutUsuario extends HttpServlet {
    /*Servlet que faz Logout de usuarios.
    Servlet bem simples, pega a sessão recorrente e invalida/encerra ela, como também o
    usuario carregado no atributo. Logo após manda para a pagina de aviso com uma mensagem.*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        session.removeAttribute("UsuarioLogado");
        session.invalidate();
        System.out.println("Sessao Encerrada!");
        
        request.setAttribute("notificacao", "Obrigado por Twettar<br>Volte sempre!");
        request.getRequestDispatcher("aviso.jsp").forward(request, response);
    }
}