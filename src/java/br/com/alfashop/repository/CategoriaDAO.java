package br.com.alfashop.repository;

import br.com.alfashop.config.Conex;
import br.com.alfashop.model.Categoria;
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
public class CategoriaDAO {
    Connection conn;    
    
    /**
     * Método construtor da classe.
     * Sempre que criar um objeto, chama este método.
     */
    public CategoriaDAO(){
        this.conn = Conex.getConnection();    
    }
    
    /**
     * Método para listar as categorias.
     * @return List<Categoria>
     */
    public List<Categoria> listar(){
        List<Categoria> lista = new ArrayList<Categoria>();       
        try{
            //Criar um objeto para a manipulação dos SQL's.
            Statement stmt = this.conn.createStatement();

            //Criar o comando e executar.
            String sql = "select * from categorias";
            ResultSet rset = stmt.executeQuery(sql); 

            //Manipular o resultado.        
            while (rset.next()){ //Enquanto houver linhas no rset.
                Categoria obj = new Categoria(); 
                obj.setIdcategoria (rset.getLong("idcategoria"));
                obj.setNome (rset.getString ("nome"));
                obj.setDescricao (rset.getString ("descricao"));
                obj.setAtivo (rset.getString ("ativo"));
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
     * Método para ser usado como JavaBean getCategorias().
     * @return 
     */
    public List<Categoria> getCategorias(){
        return listar();
    }    
        
    /**
     * Método para buscar uma categoria.
     * @param filtro
     * @return Categoria
     */
    public List<Categoria> buscar(String filtro){
        List<Categoria> lista = new ArrayList<Categoria>();       
        try{
            //Criar um objeto para a manipulação dos SQL's.
            Statement stmt = this.conn.createStatement();

            //Criar o comando e executar.
            String sql = "select * from categorias where "+filtro;
            ResultSet rset = stmt.executeQuery(sql); 

            //Manipular o resultado.        
            while (rset.next()){ //Enquanto houver linhas no rset.
                Categoria obj = new Categoria(); 
                obj.setIdcategoria (rset.getLong("idcategoria"));
                obj.setNome (rset.getString("nome"));
                obj.setDescricao (rset.getString("descricao"));
                obj.setAtivo (rset.getString("ativo"));
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
     * Método para buscar uma categoria específica.
     * @param idc
     * @return Categoria
     */
     public Categoria buscarPorId(Long idc){
        Categoria obj = new Categoria();
        try{          
            //Criar um objeto para a manipulação dos SQL's.
            String sql = "select * from categorias where idcategoria = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);            
            
            //Substituição do "?" pelo "Long idc" que veio pela URL.
            stmt.setLong(1, idc);            
            
            //Executar o comando.
            ResultSet rset = stmt.executeQuery(); 
            
            //Manipular o resultado.        
            if (rset.next()){ //Se houver linhas no rset.
                obj.setIdcategoria (rset.getLong("idcategoria"));
                obj.setNome (rset.getString("nome"));
                obj.setDescricao (rset.getString("descricao"));
                obj.setAtivo (rset.getString("ativo"));   
            }

            rset.close();
            stmt.close();
            this.conn.close();
        }
        catch(Exception e){
            obj = null;
        }      
        return obj;    
    } 
    
    /**
     * Método para cadastrar uma nova categoria.
     * @param objcat 
     * @return  
     */
    public int inserir(Categoria objcat){       
        //Declaração de variável.
        int res = 0;
        //Pegar os parâmetros.      
        String nom = objcat.getNome();
        String des = objcat.getDescricao();
        String ati = objcat.getAtivo();
        //Monta o comando SQL para inserção.
        String sql = "insert into categorias (nome, descricao, ativo) values (?, ?, ?)";   
        //Executa.             
        try{         
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, ati);
            //Se conseguir inserir um comando na tabela, "res" será maior que 0.
            res = stmt.executeUpdate();
            //Fecha os objetos de manipulação do SGBD.
            stmt.close();
            this.conn.close();    
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            res = 0;
        }                
        
        return res;
    }
    
    /**
     * Método para atualizar uma categoria.
     * @param objcat 
     * @return  
     */
    public int atualizar(Categoria objcat){
        //Declaração de variável.
        int res = 0;
        //Pega os parâmetros. 
        Long idc = objcat.getIdcategoria();
        String nom = objcat.getNome();
        String des = objcat.getDescricao();
        String ati = objcat.getAtivo();            
        //Monta o comando SQL para atualização.
        String sql = "update categorias set nome=?, descricao=?, ativo=? where idcategoria=?";          
        //Executa.         
        try{         
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, ati);
            stmt.setLong(4, idc);
            //Se conseguir inserir um comando na tabela, "res" será maior que 0.
            res = stmt.executeUpdate();
            //Fecha os objetos de manipulação do SGBD.
            stmt.close();
            this.conn.close();    
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            res = 0;
        }                
        
        return res;    
    }
    
    /**
     * Método para excluir uma categoria.
     * @param objcat 
     * @return  
     */
    public int excluir(Categoria objcat){
        //Declaração de variável.
        int res = 0;
        //Pegar o parâmetro.   
        Long idc = objcat.getIdcategoria();
        //Monta o comando SQL para exclusão.
        String sql = "delete from categorias where idcategoria=?";          
        //Executa.         
        try{         
            PreparedStatement stmt = this.conn.prepareStatement(sql);         
            stmt.setLong(1, idc);
            //Se conseguir inserir um comando na tabela, "res" será maior que 0.
            res = stmt.executeUpdate();
            //Fecha os objetos de manipulação do SGBD.
            stmt.close();
            this.conn.close();    
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            res = 0;
        }                
        
        return res;        
    }
}
