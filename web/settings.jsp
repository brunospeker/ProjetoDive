<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Configurações de Perfil - Dive</title>
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
            
        <!-- Atualiza o usuario -->
        <div class="options">
            <form action="ControleUsuario" method="POST">
                <fieldset>
                    <label>Nome:</label>
                    <input type="hidden" value="<%=UsuarioLogado.getIdusuario()%>" name="idusuario">
                    <input type="text" value="<%=UsuarioLogado.getNome()%>" name="nome">
                </fieldset>
                <fieldset>
                    <label>Usuario:</label>
                    <input type="text" value="<%=UsuarioLogado.getUsuario()%>" name="usuario">
                </fieldset>
                <fieldset>
                    <label>Email:</label>
                    <input type="email" value="<%=UsuarioLogado.getEmail()%>" disabled>
                    <input type="hidden" value="<%=UsuarioLogado.getEmail()%>" name="email">
                </fieldset>
                <fieldset>
                    <label>Senha:</label>
                    <input type="password" value="<%=UsuarioLogado.getSenha()%>" name="senha">
                </fieldset>
                <fieldset>
                    <label>Foto:</label>
                    <input type="text" value="<%=UsuarioLogado.getUrlfoto()%>" name="urlfoto">
                    <input type="hidden" value="<%=UsuarioLogado.getAdmin()%>" name="admin">
                </fieldset>
                <input type="submit" value="Confirmar modificações">
            </form>
        </div>
    </body>
</html>
