<%-- 
    Document   : settings
    Created on : 23/06/2019, 06:16:16
    Author     : BRUNO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configurações de Perfil - Dive</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <div class="perfil">
            <img src="" style="width: 200px; height: 250px;margin-top: 20px;">
            <h2>Nome</h2>
            <h2>@usuario</h2>
            <h2>Email:</h2>
            
            <button>Inicio</button>
            <button>Configurações</button>
            <button>Sair</button>
            
        </div>
        
        <div class="options">
            <form>
                <fieldset>
                    <label>Nome:</label>
                    <input type="text">
                </fieldset>
                <fieldset>
                    <label>Usuario:</label>
                    <input type="text">
                </fieldset>
                <fieldset>
                    <label>Email:</label>
                    <input type="email">
                </fieldset>
                <fieldset>
                    <label>Senha:</label>
                    <input type="password">
                </fieldset>
                <fieldset>
                    <label>Foto:</label>
                    <input type="text">
                </fieldset>
                <input type="submit" value="Confirmar modificações">
            </form>
        </div>
        
        <button id="godmode">Botão exclusivo para Admins</button>
        
    </body>
</html>
