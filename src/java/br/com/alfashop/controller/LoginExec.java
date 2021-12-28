package br.com.alfashop.controller;

import br.com.alfashop.extra.Util;
import br.com.alfashop.model.Usuario;
import br.com.alfashop.repository.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Rogerio
 */
public class LoginExec extends HttpServlet {
  
    protected void validarLogin(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Classe para permitir navegações pelas páginas restritas, se os usuários válidos passaram pela página de login.
        HttpSession session = request.getSession();    
        
        //Pegar dados do form.
        String ema = request.getParameter("ema");
        String sen = request.getParameter("sen");
        //Pega a senha e a encriptografa;
        sen = Util.sha1(sen);        
        
        //Montar filtro.
        //Exemplo de SQL Injection = Inserir este comando na senha para burlar a validação do login => senha_inexistente' or 'a' = 'a
        //String filtro = "email = '"+ema+"' and senha = '"+sen+"'";
        
        //Consultar dados usando classe DAO.
        UsuarioDAO daousu = new UsuarioDAO();
        List<Usuario> lstusu = daousu.validar(ema,sen);
        
        String destino = "";
        String msg = "";
        
        if (lstusu.size() > 0){
           //Se deu certo, vai para a página de administração.    
           Usuario usu = lstusu.get(0); //Pega o primeiro objeto usuario da lista.
           session.setAttribute("usu", usu); //Cria uma variável de sessão com este objeto usuario.         
           destino = "dashboard.jsp";        
        }          
        else{                  
           //Se deu errado, volta para o login com aviso. 
           msg = "bug";
           destino = "./?msg="+msg;       
        }        
        response.sendRedirect(destino);        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("usu", null);
        session.invalidate();
        response.sendRedirect("index.jsp");
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
        validarLogin(request, response);
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
