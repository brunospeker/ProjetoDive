<%@page import="Controle.UsuarioDAO"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Tweet"%>
<%@page import="Controle.TweetDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Dive</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <jsp:useBean id="UsuarioLogado" class="Modelo.Usuario" scope="session">
    </jsp:useBean>
    <body>
        <div class="perfil">
            <img src="" style="width: 200px; height: 250px;margin-top: 20px;">
            <h2><%UsuarioLogado.getUsuario();%></h2>
            <h2>@<%UsuarioLogado.getUsuario();%></h2>
            <h2>Email:<%UsuarioLogado.getEmail();%></h2>
            
            <button onclick="location.href='home.jsp'">Inicio</button>
            <button onclick="location.href='settings.jsp'">Configurações</button>
            <form>
            <button>Sair</button>
            </form>
        </div>
        
        <div class="mensagem">
            <form action="PostarTweet" method="POST">
            <textarea maxlength="240" placeholder="Digite sua mensagem aqui." size="240" name="tweet" required></textarea>
            <input type="hidden" name="logado" value="<%UsuarioLogado.getIdusuario();%>">
            <input type="submit" value="Enviar">
            </form>
        </div>
        
        
        <%
            TweetDAO tdao = new TweetDAO();
            UsuarioDAO udao = new UsuarioDAO();
            List<Tweet> tweet = tdao.getAllTweets();
            Iterator itweet = tweet.iterator();
            while(itweet.hasNext()){
                Tweet t = (Tweet) itweet.next();
        %>
        <div class="comentario">
            <img src="<%%>" style="height: 100px; width: 75px; float: left; margin: 15px;">
            <h3><%=t.getIdusuario() %></h3><h4>@<%=t.getIdusuario()%></h4>
            <textarea maxlength="240" placeholder="<%=t.getMensagem()%>" size="240" style="resize: none; border: none; background: none;" disabled></textarea>
            <img src="" style="height: 20px; width: 20px;">
            <img src="" style="height: 20px; width: 20px;">
            <img src="" style="height: 20px; width: 20px;">
        </div>
        
        <div class="subcomentario">
            <% for(int j=0; j<3; j++){ %>
            <div class="sub">
                <img src="" style="height: 60px; width: 45px; float: left; margin: 15px;">
                <h4>Nome do usuario</h4><h5>@subusuario</h5>
                <textarea maxlength="240" placeholder="Digite sua mensagem aqui." size="240" style="resize: none; border: none; background: none;" disabled></textarea>
                <img src="" style="height: 20px; width: 20px;">
            </div>
            <% } %>
            <div class="resposta">
                <form action="PostarReply" method="POST">
                    <textarea maxlength="240" placeholder="Digite sua mensagem aqui." name="reply" size="240" required></textarea>
                    <input type="hidden" name="idtweet" value="<%=t.getIdtweet()%>">
                    <input type="hidden" name="log" value="<%UsuarioLogado.getIdusuario();%>">
                    <input type="submit" value="Enviar">
                </form>
            </div>
        </div>
        
        <% } %>
        
    </body>
</html>
