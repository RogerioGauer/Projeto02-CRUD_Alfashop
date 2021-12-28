<%@page import="br.com.alfashop.model.Produto"%>
<%@page import="br.com.alfashop.repository.ProdutoDAO"%>
<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean class="br.com.alfashop.repository.CategoriaDAO" id="lstcat" />

<%
String sidp = request.getParameter("idp");
Long idp = Long.parseLong(sidp);
ProdutoDAO objdao = new ProdutoDAO();
Produto objpro = objdao.buscarPorId(idp);

//Teste para o checked do radio (Ativo "s" ou "n").
String checsim = "";
if (objpro.getAtivo().equals("s")){
    checsim = "checked='checked'";
}
String checnao = "";
if (objpro.getAtivo().equals("n")){
    checnao = "checked='checked'";
}

//Atribuindo variáveis nesta página.
pageContext.setAttribute("pro", objpro);
pageContext.setAttribute("checsim", checsim);
pageContext.setAttribute("checnao", checnao);
%>                

      <div class="container">
         <div class="row">
            
            <%@include file="_inc/_menu.jsp" %>            

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">               
               <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                  <h3 class="h3">Atualização de Produto:</h3>                    
                  <a href="produtos.jsp" class="btn btn-outline-primary">Voltar</a>
               </div> 
                              
               <form action="produtos-exec" method="post">
                  <input type="hidden" name="action" value="update">
                  <input type="hidden" name="idp" value="${pro.idproduto}">
                  
                  Categoria:<br>
                  <select name="cid">
                     <c:forEach items="${lstcat.categorias}" var="objcat">
                        <option name="cid" value="${objcat.idcategoria}">${objcat.nome}</option> 
                     </c:forEach>
                  </select><br><br>
                  
                  Nome:<br>
                  <input type="text" name="nom" value="${pro.nome}">
                  <br><br>
                  
                  Descrição:<br>
                  <input type="text" name="des" value="${pro.descricao}">
                  <br><br>
                  
                  Maisinfo:<br>
                  <input type="text" name="inf" value="${pro.maisinfo}">
                  <br><br>
                  
                  Valor:<br>
                  <input type="text" name="val" value="${pro.valor}">
                  <br><br>
                  
                  Peso:<br>
                  <input type="text" name="pes" value="${pro.peso}">
                  <br><br>
                  
                  Destaque:<br>
                  <input type="radio" name="dtq" value="s"> Sim <br>
                  <input type="radio" name="dtq" value="n" checked="checked"> Não <br><br>
                  
                  Ativo:<br>
                  <input type="radio" name="ati" value="s" ${checsim}> Sim <br>
                  <input type="radio" name="ati" value="n" ${checnao}> Não
                  <br><br>                       
       
                  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                     <button type="submit" class="btn btn-outline-success">Atualizar</button>
                  </div>
               </form>
                             
            </main>
         </div>
      </div>

   </body>
</html>
