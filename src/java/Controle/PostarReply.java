package Controle;

import Modelo.Reply;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostarReply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReplyDAO dao = new ReplyDAO();
        String action = "";
        try{
            action = request.getParameter("action");
        } catch(Exception e){
            System.out.println("Nenhuma action foi encontrada: "+e.getMessage());
        }
        
        if(action.equalsIgnoreCase("delete")){/*se a action for delete*/
            int id = Integer.parseInt(request.getParameter("id"));/*ele pega o id*/
            dao.deleteReply(id);/*joga o id no dao de deletar*/
            response.sendRedirect("home.jsp");/*e manda pra pagina gerenciar novamente*/
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String reply = request.getParameter("reply");
        int idtweet = Integer.parseInt(request.getParameter("idtweet"));
        int idusuario = Integer.parseInt(request.getParameter("log"));
        
        ReplyDAO replydao = new ReplyDAO();
        Reply r = new Reply();
        
        r.setIdtweet(idtweet);
        r.setIdususario(idusuario);
        r.setMensagem(reply);
        
        replydao.addReply(r);
        response.sendRedirect("home.jsp");
    }
}