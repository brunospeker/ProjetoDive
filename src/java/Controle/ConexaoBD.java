package Controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    /*Conexão com o banco de dados*/
    public static Connection abrir(){/*metodo que abre a conexão*/
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");/*pega a biblioteca*/
            String url="jdbc:derby://localhost:1527/dive;create=true;user=app;password=app";
            Connection con = DriverManager.getConnection(url);/*busca a conexão*/
            System.out.println("===Conexao com banco de dados estabelecida===");
            
            return con;/*retorna com a conexão*/
            
        }catch(ClassNotFoundException cnfe){
            System.out.println("Ocorreu um erro no ConexaoBD.abrir():" + cnfe.getMessage());
            return null;
        }catch(SQLException sqle){
            System.out.println("Ocorreu um erro no ConexaoBD.abrir():" + sqle.getMessage());
            return null;
        }
    }
    
    public static void fechar(Connection con){/*metodo que fecha a conexão*/
        try{
        if(con!=null){
            con.close();/*fecha a ocnexão*/
            System.out.println("===Conexao com banco de dados encerrada===");
        }
        }catch(SQLException sqle){
            System.out.println("Ocorreu um erro no ConexaoBD.fechar():" + sqle.getMessage());
        }
    }

    public Connection getCon() { /*verifica se há conexão aberta, se não abre uma*/
        return abrir();
    }
}