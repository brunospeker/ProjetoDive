<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dive</title>
        <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
    </head>
    <body>
        <h1>Bem vindo(a)!</h1>
        <a href="index.jsp"><img class="logo" src="imagens/LogoDive.png"></a>
        <div id="container">
            <form class="login" action="LoginUsuario" method="POST">
                <h2>Fazer login</h2>
                <input type="text" placeholder="Usuario" name="user">
                <input type="password" placeholder="Senha"  name="pass">
                <input type="submit" value="Logar">
            </form>
            <div class="wave-01"></div>
            <div class="wave-02"></div>
            <div class="wave-03"></div>
        </div>
        
        <div id="container2">
            <form class="registro" action="RegistroUsuario" method="POST">
                <h2>Se cadastre</h2>
                <input type="text" placeholder="Usuario" name="usuario" required>
                <input type="email" placeholder="Email" name="email" required>
                <input type="password" placeholder="Senha" name="password" required>
                <input type="submit" value="Registrar">
            </form>
            <div class="wave-01"></div>
            <div class="wave-02"></div>
            <div class="wave-03"></div>
        </div>
        
        <div class="waveHorizontals">
	<div id="waveHorizontal1" class="waveHorizontal"></div>
	<div id="waveHorizontal2" class="waveHorizontal"></div>
	<div id="waveHorizontal3" class="waveHorizontal"></div>
        </div>
        
        <footer>
            <a href="#" style='color: white; text-decoration: none;'>Copyright © 2019 Dive Grupo J. Todos os direitos reservados.</a>
        </footer>
    </body>
</html>
