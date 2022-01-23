package br.com.alfashop.repository;

import br.com.alfashop.config.Conex;
import br.com.alfashop.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rogerio
 */
public class UsuarioDAO {    
     Connection conn;    
    
    /**
     * Método construtor da classe.
     * Sempre que criar um objeto, chama este método.
     */
    public UsuarioDAO(){
        this.conn = Conex.getConnection();    
    }
    
    /**
     * Método para listar os usuários.
     * @return List<Usuario>
     */
    public List<Usuario> listar(){
        List<Usuario> lista = new ArrayList<Usuario>();
        
        try{
            //Criar um objeto para a manipulação dos SQL's.
            Statement stmt = this.conn.createStatement();

            //Criar o comando e executar.
            String sql = "select * from usuarios";
            ResultSet rset = stmt.executeQuery(sql); 

            //Manipular o resultado.        
            while (rset.next()){ //Enquanto houver linhas no rset.
                Usuario obj = new Usuario();
                obj.setIdusuario (rset.getLong("idusuario"));
                obj.setNome (rset.getString ("nome"));
                obj.setEmail (rset.getString ("email"));
                obj.setSenha (rset.getString ("senha"));
                //Adicionar o obj em uma lista.
                lista.add(obj);                          
            }

            rset.close();
            stmt.close();
            this.conn.close();
        }
        catch(Exception e){
            lista = null;
        }
      
        return lista;    
    }   
    
    /**
     * Método para ser usado como JavaBean getUsuarios().
     * @return 
     */
    public List<Usuario> getUsuarios(){
        return listar();
    }    
        
    /**
     * Método para buscar um usuário específico.
     * @param filtro
     * @return Usuario
     */
    public List<Usuario> buscar(String filtro){
        List<Usuario> lista = new ArrayList<Usuario>();
        try{
            //Criar um objeto para a manipulação dos SQL's.
            Statement stmt = this.conn.createStatement();

            //Criar o comando e executar.
            String sql = "select * from usuarios where "+filtro;
            ResultSet rset = stmt.executeQuery(sql); 

            //Manipular o resultado.        
            while (rset.next()){ //Enquanto houver linhas no rset.
                Usuario obj = new Usuario();
                obj.setIdusuario (rset.getLong("idusuario"));
                obj.setNome (rset.getString("nome"));
                obj.setEmail (rset.getString("email"));
                obj.setSenha (rset.getString("senha"));
                //Adicionar o obj em uma lista.
                lista.add(obj);
            }

            rset.close();
            stmt.close();
            this.conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());   
            lista = null;
        }      
        return lista;    
    }   
    
    //Método para validar o login, perante o banco de dados.
    public List<Usuario> validar(String ema, String sen){
        List<Usuario> lista = new ArrayList<Usuario>();
        try{
            //Criar o comando SQL.
            String sql = "select * from usuarios where email=? and senha=?";
            
            //Criar um objeto para a manipulação dos SQL's, e executar.
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, ema);
            stmt.setString(2, sen);
            ResultSet rset = stmt.executeQuery(); 

            //Manipular o resultado.        
            while (rset.next()){ //Enquanto houver linhas no rset.
                Usuario obj = new Usuario();
                obj.setIdusuario (rset.getLong("idusuario"));
                obj.setNome (rset.getString("nome"));
                obj.setEmail (rset.getString("email"));
                obj.setSenha (rset.getString("senha"));
                //Adicionar o obj em uma lista.
                lista.add(obj);
            }

            rset.close();
            
            stmt.close();
            this.conn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());   
            lista = null;
        }      
        return lista;    
    }     
}
