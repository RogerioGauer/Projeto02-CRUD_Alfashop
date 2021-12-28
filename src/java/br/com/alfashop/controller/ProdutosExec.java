package br.com.alfashop.controller;

import br.com.alfashop.model.Produto;
import br.com.alfashop.repository.ProdutoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rogerio
 */
public class ProdutosExec extends HttpServlet {
    
    public void insertServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Pegar os parâmetros "name" enviados do form.
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        String inf = request.getParameter("inf");
        String val = request.getParameter("val");
        String pes = request.getParameter("pes");
        String dtq = request.getParameter("dtq");
        String cid = request.getParameter("cid");
        
        //Criar o objeto Produto.
        Produto objpro = new Produto();
        objpro.setNome(nom);
        objpro.setDescricao(des);
        objpro.setMaisinfo(inf);
        objpro.setValor(Float.parseFloat(val));
        objpro.setPeso(Float.parseFloat(pes));
        objpro.setDestaque(dtq);
        objpro.setCategoriaid(Long.parseLong(cid));  
        objpro.setAtivo("s");
        
        //Criar o objeto DAO, e inserir na tabela do banco de dados.
        ProdutoDAO objdao = new ProdutoDAO();
        objdao.inserir(objpro);
        
        //Voltar para a lista de produtos.
        response.sendRedirect("produtos.jsp");
    }
    
        public void updateServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Pegar os parâmetros do campo "name" enviados pelo form.
        String idp = request.getParameter("idp");
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        String inf = request.getParameter("inf");
        String val = request.getParameter("val");
        String pes = request.getParameter("pes");
        String dtq = request.getParameter("dtq");        
        String ati = request.getParameter("ati");
        String cid = request.getParameter("cid");
        
        //Criar o objeto Produto.
        Produto objpro = new Produto();    
        objpro.setIdproduto(Long.parseLong(idp));
        objpro.setNome(nom);
        objpro.setDescricao(des);
        objpro.setMaisinfo(inf);
        objpro.setValor(Float.parseFloat(val));
        objpro.setPeso(Float.parseFloat(pes));
        objpro.setDestaque(dtq);
        objpro.setAtivo(ati);     
        objpro.setCategoriaid(Long.parseLong(cid));     
        
        //Criar o objeto DAO, e atualizar na tabela do banco de dados.
        ProdutoDAO objdao = new ProdutoDAO();
        objdao.atualizar(objpro);
        
        //Voltar para a lista de categorias.
        response.sendRedirect("produtos.jsp");
    }
        
        public void deleteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Pegar o parâmetro enviado pelo formulário.
        String sidp = request.getParameter("idp");
        Long idp = Long.parseLong(sidp);
        
        //Criar o objeto Produto.
        Produto objpro = new Produto();    
        objpro.setIdproduto(idp);
        
        //Criar o objeto DAO, e remover na tabela do banco de dados.
        ProdutoDAO objdao = new ProdutoDAO();
        objdao.excluir(objpro);
        
        //Voltar para a lista de produtos.
        response.sendRedirect("produtos.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        /**
        Trecho de código para excluir por GET.       
        String act = request.getParameter("action"); 
        if (act.equals("delete")){
            deleteServlet(request, response);       
        }
        else{
            response.sendRedirect("categorias.jsp");
        }
        **/
        response.sendRedirect("produtos.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        //Pegar o parâmetro que verifica a ação (insert, update ou delete).
        String act = request.getParameter("action");        
        if (act.equals("insert")){
            insertServlet(request, response);        
        }        
        if (act.equals("update")){
            updateServlet(request, response);
        }        
        if (act.equals("delete")){
            deleteServlet(request, response);        
        }             
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
