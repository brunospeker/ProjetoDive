<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aviso</title>
        <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
    </head>
    <body>
        <!-- Logo -->
        <a href="index.jsp"><img class="logo" src="imagens/LogoDive.png"></a>
        <div id="container3">
            <h2>${notificacao}</h2><!-- Lugar onde o atributto é colocado -->
            <!-- Ondas do container -->
            <div class="wave-01"></div>
            <div class="wave-02"></div>
            <div class="wave-03"></div>
        </div>

        <!-- Ondas do plano de fundo -->
        <div class="waveHorizontals">
            <div id="waveHorizontal1" class="waveHorizontal"></div>
            <div id="waveHorizontal2" class="waveHorizontal"></div>
            <div id="waveHorizontal3" class="waveHorizontal"></div>
        </div>

        <footer><!-- Rodapé -->
            <a href="#" style='color: white; text-decoration: none;'>Copyright © 2019 Dive Grupo J. Todos os direitos reservados.</a>
        </footer>
    </body>
</html>