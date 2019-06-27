<%@page import="Modelo.Reply"%>
<%@page import="Controle.ReplyDAO"%>
<%@page import="Modelo.Usuario"%>
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
        <link rel="stylesheet" type="text/css" href="css/stylenew.css">
    </head>
    <jsp:useBean id="UsuarioLogado" class="Modelo.Usuario" scope="session">
    </jsp:useBean>
    <body>
        <!-- Dados do usuario logado -->
        <div class="perfil">
            <a href="home.jsp"><img src="imagens/LogoDive.png" style="position: relative; top: 9px;"></a>
            <img src="<%=UsuarioLogado.getUrlfoto()%>" style="width: 200px; height: 250px;margin-top: 20px;">
            <h2><%=UsuarioLogado.getNome()%></h2>
            <h2>@<%=UsuarioLogado.getUsuario()%></h2>
            <h2>Email:<%=UsuarioLogado.getEmail()%></h2>
            
            <button onclick="location.href='home.jsp'">Inicio</button>
            <button onclick="location.href='settings.jsp'">Configurações</button>
            <form action="LogoutUsuario" method="POST">
            <button>Sair</button>
            </form>
        </div>
        
        <div class="mensagem"><!-- Mensagem que o usuario vai postar -->
            <form action="PostarTweet" method="POST">
            <textarea maxlength="240" placeholder="Digite sua mensagem aqui." size="240" name="tweet" required></textarea>
            <input type="hidden" name="logado" value="<%=UsuarioLogado.getIdusuario()%>">
            <input type="submit" value="Enviar">
            </form>
        </div>
        <!-- Pega um metodo do dao que retorna uma lista, e faz com que o iterator
        percorra toda a lista, e dentro dessa lista instancia um usuario para
        setar imagens e nomes-->
        <%
            TweetDAO tdao = new TweetDAO();
            List<Tweet> tweet = tdao.getAllTweets();
            Iterator itweet = tweet.iterator();
            while(itweet.hasNext()){
                Tweet t = (Tweet) itweet.next();
                UsuarioDAO udao = new UsuarioDAO();
                Usuario u = udao.getByIdUsuario(t.getIdusuario());
        %>
        <div class="comentario" id="comentario" onclick="mostrar()"><!-- Começa a carregar os dados do while -->
            <img src="<%=u.getUrlfoto()%>" style="height: 100px; width: 75px; float: left; margin: 15px;">
            <h3><%=u.getNome()%></h3><h4>@<%=u.getUsuario()%></h4>
            <textarea maxlength="240" placeholder="<%=t.getMensagem()%>" size="240" style="resize: none; border: none; background: none;" disabled></textarea>
            <% if(UsuarioLogado.getIdusuario()== t.getIdusuario()||UsuarioLogado.getAdmin() == 1){ %>
            <a href="PostarTweet?action=delete&id=<%=t.getIdtweet()%>" style="float: right; margin: -16px 21px;"><img src="imagens/lixeira.png" style="height: 30px;width: 30px;"></a>
            <% } %>
        </div>
            
            <!-- Pega um metodo do dao que retorna uma lista, e faz com que o iterator
            percorra toda a lista, e dentro dessa lista instancia um usuario para
            setar imagens e nomes-->
            <div class="subcomentario" id="subcomentario">
            <%
                ReplyDAO rdao = new ReplyDAO();
                List<Reply> reply = rdao.getAllReplys(t.getIdtweet());
                Iterator ireply = reply.iterator();
                while(ireply.hasNext()){
                    Reply r = (Reply) ireply.next();
                    UsuarioDAO usdao = new UsuarioDAO();
                    Usuario us = usdao.getByIdUsuario(r.getIdususario());
            %>
            <div class="sub"><!-- Começa a carregar os dados do while -->
                <img src="<%=us.getUrlfoto()%>" style="height: 60px; width: 45px; float: left; margin: 15px;">
                <h4><%=us.getUsuario()%></h4><h5>@<%=us.getUsuario()%></h5>
                <textarea maxlength="240" placeholder="<%=r.getMensagem()%>" size="240" style="resize: none; border: none; background: none;" disabled></textarea>
                <% if(UsuarioLogado.getIdusuario()== r.getIdususario()||UsuarioLogado.getAdmin() == 1){ %>
                <a href="PostarReply?action=delete&id=<%=r.getIdreply()%>" style="float: right; margin: -35px 28px;"><img src="imagens/lixeira.png" style="height: 20px;width: 20px;"></a>
                <% } %>
            </div>
            <% } %>
            <!-- Div com a resposta em reply -->
            <div class="resposta">
                <form action="PostarReply" method="POST">
                    <textarea maxlength="240" placeholder="Digite sua mensagem aqui." name="reply" size="240" required></textarea>
                    <input type="hidden" name="idtweet" value="<%=t.getIdtweet()%>">
                    <input type="hidden" name="log" value="<%=UsuarioLogado.getIdusuario()%>">
                    <input type="submit" value="Enviar">
                </form>
            </div>
        </div>
        
        <% } %>
        <!-- Função JS dentro da pagina -->
        <script>
        function mostrar(){
          var x = document.getElementById("subcomentario");
          if (x.style.display === "none") {
            x.style.display = "block";
          } else {
            x.style.display = "none";
          }
        }
        </script>
    </body>
</html>
