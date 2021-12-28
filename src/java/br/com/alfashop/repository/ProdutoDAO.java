package br.com.alfashop.repository;

import br.com.alfashop.config.Conex;
import br.com.alfashop.model.Produto;
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
public class ProdutoDAO {
    Connection conn;    
    
    /**
     * Método construtor da classe.
     * Sempre que criar um objeto, chama este método.
     */
    public ProdutoDAO(){
        this.conn = Conex.getConnection();    
    }
    
    /**
     * Método para listar os produtos.
     * @return lista.
     */
    public List<Produto> listar(){
        List<Produto> lista = new ArrayList<Produto>();
        
        try{
            //Criar um objeto para a manipulação dos SQL's.
            Statement stmt = this.conn.createStatement();

            //Criar o comando e executar.
            String sql = "select * from produtos";
            ResultSet rset = stmt.executeQuery(sql); 

            //Manipular o resultado.        
            while (rset.next()){ //Enquanto houver linhas no rset.
                Produto obj = new Produto();
                obj.setIdproduto (rset.getLong("idproduto"));
                obj.setNome (rset.getString ("nome"));
                obj.setDescricao (rset.getString ("descricao"));
                obj.setMaisinfo (rset.getString ("maisinfo"));
                obj.setValor (rset.getFloat ("valor"));
                obj.setPeso (rset.getFloat ("peso"));
                obj.setDestaque (rset.getString ("destaque"));
                obj.setAtivo (rset.getString ("ativo"));
                obj.setCategoriaid (rset.getLong ("categoriaid"));
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
     * Método para ser usado como JavaBean getProdutos().
     * @return 
     */
    public List<Produto> getProdutos(){
        return listar();
    }    
        
    /**
     * Método para buscar um produto específico.
     * @param filtro.
     * @return lista.
     */
    public List<Produto> buscar(String filtro){
        List<Produto> lista = new ArrayList<Produto>();
        
        try{
            //Criar um objeto para a manipulação dos SQL's.
            Statement stmt = this.conn.createStatement();

            //Criar o comando e executar.
            String sql = "select p.*, c.nome as nomecat from produtos p "
                           + "inner join categorias c on c.idcategoria = p.categoriaid "
                           + "where "+filtro; 
            ResultSet rset = stmt.executeQuery(sql); 

            //Manipular o resultado.        
            while (rset.next()){ //Enquanto houver linhas no rset.
                Produto obj = new Produto();
                obj.setIdproduto (rset.getLong("idproduto"));
                obj.setNome (rset.getString ("nome"));
                obj.setDescricao (rset.getString ("descricao"));
                obj.setMaisinfo (rset.getString ("maisinfo"));
                obj.setValor (rset.getFloat ("valor"));
                obj.setPeso (rset.getFloat ("peso"));
                obj.setDestaque (rset.getString ("destaque"));
                obj.setAtivo (rset.getString ("ativo"));
                obj.setCategoriaid (rset.getLong ("categoriaid"));         
                obj.setNomecat (rset.getString ("nomecat"));
                //Adicionar o obj em uma lista.
                lista.add(obj);
            }

            rset.close();
            stmt.close();
            this.conn.close();
        }
        catch(Exception e){
            lista = null;
            System.out.println(e.getMessage());
        }     
        
        return lista;    
    } 
    
    /**
     * Método para buscar um produto específico.
     * @param idp.
     * @return obj.
     */
    public Produto buscarPorId(Long idp){
        Produto obj = new Produto();
        try{          
            //Criar um objeto para a manipulação dos SQL's.
            String sql = "select * from produtos where idproduto = ?";
            PreparedStatement stmt = this.conn.prepareStatement(sql);            
            
            //Substituição do "?" pelo "Long idp" que veio pela URL.
            stmt.setLong(1, idp);            
            
            //Executar o comando.
            ResultSet rset = stmt.executeQuery(); 
            
            //Manipular o resultado.        
            if (rset.next()){ //Se houver linhas no rset.
                obj.setIdproduto (rset.getLong("idproduto"));
                obj.setNome (rset.getString("nome"));
                obj.setDescricao (rset.getString("descricao"));
                obj.setMaisinfo (rset.getString("maisinfo"));   
                obj.setValor (rset.getFloat("valor"));   
                obj.setPeso (rset.getFloat("peso"));   
                obj.setDestaque (rset.getString("destaque"));   
                obj.setAtivo (rset.getString("ativo"));   
                obj.setCategoriaid (rset.getLong("categoriaid"));   
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
     * Método para ser usado como JavaBean getDestaques().
     * @return 
     */
    public List<Produto> getDestaques(){
        return buscar(" destaque='s' ");
    } 
    
    /**
     * Método para cadastrar um novo produto.
     * @param objpro. 
     * @return res.  
     */
    public int inserir(Produto objpro){       
        //Declaração de variável.
        int res = 0;
        //Pegar os parâmetros.      
        String nom = objpro.getNome();
        String des = objpro.getDescricao();
        String inf = objpro.getMaisinfo();
        Float val = objpro.getValor();
        Float pes = objpro.getPeso();
        String dtq = objpro.getDestaque();        
        String ati = objpro.getAtivo();
        Long cid = objpro.getCategoriaid();
        //Monta o comando SQL para inserção.
        String sql = "insert into produtos (nome, descricao, maisinfo, valor, peso, destaque, ativo, categoriaid) values (?, ?, ?, ?, ?, ?, ?, ?)";   
        //Executa.             
        try{         
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, inf);
            stmt.setFloat(4, val);
            stmt.setFloat(5, pes);
            stmt.setString(6, dtq);
            stmt.setString(7, ati);
            stmt.setLong(8, cid);
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
     * Método para atualizar um produto.
     * @param obj.
     * @return res. 
     */
    public int atualizar(Produto obj){
        //Declaração de variável.
        int res = 0;
        //Obter os parâmetros do "ProdutosExec.java". 
        Long idp = obj.getIdproduto();        
        String nom = obj.getNome();
        String des = obj.getDescricao();
        String inf = obj.getMaisinfo();
        Float val = obj.getValor();
        Float pes = obj.getPeso();
        String dtq = obj.getDestaque();
        String ati = obj.getAtivo();
        Long cid = obj.getCategoriaid();
             
        //Monta o comando SQL para atualização.
        String sql = "update produtos set nome=?, descricao=?, maisinfo=?, valor=?, peso=?, destaque=?, ativo=?, categoriaid=? where idproduto=?";          
        //Executa.         
        try{         
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, des);
            stmt.setString(3, inf);
            stmt.setFloat(4, val);
            stmt.setFloat(5, pes);
            stmt.setString(6, dtq);
            stmt.setString(7, ati);
            stmt.setLong(8, cid);
            stmt.setLong(9, idp);
            //Se conseguir inserir um comando na tabela, "res" será maior que 0.
            res = stmt.executeUpdate();
            //Fechar os objetos de manipulação do SGBD.
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
     * Método para excluir um produto específico.
     * @param objpro 
     * @return  
     */
    public int excluir(Produto objpro){
        //Declaração de variável.
        int res = 0;
        //Pega o parâmetro.   
        Long idp = objpro.getIdproduto();
        //Monta o comando SQL para exclusão.
        String sql = "delete from produtos where idproduto=?";          
        //Executa.         
        try{         
            PreparedStatement stmt = this.conn.prepareStatement(sql);         
            stmt.setLong(1, idp);
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
