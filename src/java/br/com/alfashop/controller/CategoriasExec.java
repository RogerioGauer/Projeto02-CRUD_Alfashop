package br.com.alfashop.controller;

import br.com.alfashop.model.Categoria;
import br.com.alfashop.repository.CategoriaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rogerio
 */
public class CategoriasExec extends HttpServlet {
    
    public void insertServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Pegar os parâmetros enviados do form.
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        
        //Criar o objeto Categoria.
        Categoria objcat = new Categoria();
        objcat.setNome(nom);
        objcat.setDescricao(des);
        objcat.setAtivo("s");
        
        //Criar o objeto DAO, e inserir na tabela do banco de dados.
        CategoriaDAO objdao = new CategoriaDAO();
        objdao.inserir(objcat);
        
        //Voltar para a lista de categorias.
        response.sendRedirect("categorias.jsp");
    }
    
        public void updateServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Pegar os parâmetros enviados do form.
        String sidc = request.getParameter("idc");
        Long idc = Long.parseLong(sidc);
        String nom = request.getParameter("nom");
        String des = request.getParameter("des");
        String ati = request.getParameter("ati");
        
        //Criar o objeto Categoria.
        Categoria objcat = new Categoria();    
        objcat.setIdcategoria(idc);
        objcat.setNome(nom);
        objcat.setDescricao(des);
        objcat.setAtivo(ati);
        
        //Criar o objeto DAO, e atualizar na tabela do banco de dados.
        CategoriaDAO objdao = new CategoriaDAO();
        objdao.atualizar(objcat);
        
        //Voltar para a lista de categorias.
        response.sendRedirect("categorias.jsp");
    }
        
        public void deleteServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //Pegar o parâmetro enviado pelo formulário.
        String sidc = request.getParameter("idc");
        Long idc = Long.parseLong(sidc);
        
        //Criar o objeto Categoria.
        Categoria objcat = new Categoria();    
        objcat.setIdcategoria(idc);
        
        //Criar o objeto DAO, e remover na tabela do banco de dados.
        CategoriaDAO objdao = new CategoriaDAO();
        objdao.excluir(objcat);
        
        //Voltar para a lista de categorias.
        response.sendRedirect("categorias.jsp");
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
        response.sendRedirect("categorias.jsp");
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
