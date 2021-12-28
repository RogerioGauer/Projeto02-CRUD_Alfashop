<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.alfashop.model.Usuario" %>

<% 
if (session.getAttribute("usu") == null){
    //Se "usu" não existir nesta página, vá para a página "index.jsp", finalizando a programação dentro deste If, sem processar o restante do código.    
    RequestDispatcher despachador = request.getRequestDispatcher("index.jsp");
    despachador.forward(request, response);
}     
Usuario usu = (Usuario) session.getAttribute("usu"); //(Usuario) = Typecasting para obter a variável "usu".
%>
