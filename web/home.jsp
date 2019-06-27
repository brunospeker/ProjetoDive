<%-- 
    Document   : home
    Created on : 22/06/2019, 18:58:59
    Author     : BRUNO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Dive</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div class="perfil">
            <img src="" style="width: 200px; height: 250px;margin-top: 20px;">
            <h2>Nome</h2>
            <h2>@usuario</h2>
            <h2>Email:</h2>
            
            <button onclick="location.href='home.jsp'">Inicio</button>
            <button onclick="location.href='settings.jsp'">Configurações</button>
            <form>
            <button>Sair</button>
            </form>
        </div>
        
        <div class="mensagem">
            <form>
            <textarea maxlength="240" placeholder="Digite sua mensagem aqui." size="240" required></textarea>
            <input type="submit" value="Enviar">
            </form>
        </div>
        
        <% for(int i=0; i<4; i++){ %>
        <div class="comentario">
            <img style="height: 100px; width: 75px; float: left; margin: 15px;">
            <h3>Nome do Usuario</h3><h4>@usuario</h4>
            <textarea maxlength="240" placeholder="Digite sua mensagem aqui." size="240" style="resize: none; border: none; background: none;" disabled></textarea>
            <img style="height: 20px; width: 20px;">
        </div>
        
        <div class="subcomentario">
            <% for(int j=0; j<3; j++){ %>
            <div class="sub">
                <img style="height: 60px; width: 45px; float: left; margin: 15px;">
                <h4>Nome do usuario</h4><h5>@subusuario</h5>
                <textarea maxlength="240" placeholder="Digite sua mensagem aqui." size="240" style="resize: none; border: none; background: none;" disabled></textarea>
                <img style="height: 20px; width: 20px;">
            </div>
            <% } %>
            <div class="resposta">
                <form>
                    <textarea maxlength="240" placeholder="Digite sua mensagem aqui." size="240" required></textarea>
                    <input type="submit" value="Enviar">
                </form>
            </div>
        </div>
        
        <% } %>
        
    </body>
</html>
