package Controle;

import Modelo.Tweet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostarTweet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TweetDAO dao = new TweetDAO();
        String action = "";
        try{
            action = request.getParameter("action");
        } catch(Exception e){
            System.out.println("Nenhuma action foi encontrada: "+e.getMessage());
        }
        
        if(action.equalsIgnoreCase("delete")){/*se a action for delete*/
            int id = Integer.parseInt(request.getParameter("id"));/*ele pega o id*/
            ReplyDAO rdao = new ReplyDAO();
            rdao.deleteTodosReplysDoTweet(id);
            dao.deleteTweet(id);/*joga o id no dao de deletar*/
            response.sendRedirect("home.jsp");/*e manda pra pagina gerenciar novamente*/
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tweet = request.getParameter("tweet");
        int idusuario = Integer.parseInt(request.getParameter("logado"));
        
        TweetDAO tweetdao = new TweetDAO();
        Tweet t = new Tweet();
        
        t.setIdusuario(idusuario);
        t.setMensagem(tweet);
        
        tweetdao.addTweet(t);
        response.sendRedirect("home.jsp");
    }
}