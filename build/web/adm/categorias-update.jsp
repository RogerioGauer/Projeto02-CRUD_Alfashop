<%@page import="br.com.alfashop.model.Categoria"%>
<%@page import="br.com.alfashop.repository.CategoriaDAO"%>
<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>

<%
String sidc = request.getParameter("idc");
Long idc = Long.parseLong(sidc);
CategoriaDAO objdao = new CategoriaDAO();
Categoria objcat = objdao.buscarPorId(idc);

//Teste para o checked do radio (Ativo "s" ou "n").
String checsim = "";
if (objcat.getAtivo().equals("s")){
    checsim = "checked='checked'";
}
String checnao = "";
if (objcat.getAtivo().equals("n")){
    checnao = "checked='checked'";
}

//Atribuindo variáveis nesta página.
pageContext.setAttribute("cat", objcat);
pageContext.setAttribute("checsim", checsim);
pageContext.setAttribute("checnao", checnao);
%>                

      <div class="container">
         <div class="row">
            
            <%@include file="_inc/_menu.jsp" %>            

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">               
               <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                  <h3 class="h3">Atualização de Categoria:</h3>                    
                  <a href="categorias.jsp" class="btn btn-outline-primary">Voltar</a>
               </div> 
                              
               <form action="categorias-exec" method="post">
                  <input type="hidden" name="action" value="update">
                  <input type="hidden" name="idc" value="${cat.idcategoria}">
                  
                  Nome:
                  <br>
                  <input type="text" name="nom" value="${cat.nome}">
                  <br><br>
                  
                  Descrição:
                  <br>
                  <input type="text" name="des" value="${cat.descricao}">
                  <br><br>
                  
                  Ativo:
                  <br>
                  <input type="radio" name="ati" value="s" ${checsim}> Sim <br><br>
                  <input type="radio" name="ati" value="n" ${checnao}> Não <br><br>
                  
                  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                     <button type="submit" class="btn btn-outline-success">Atualizar</button>
                  </div>
               </form>
                             
            </main>
         </div>
      </div>

   </body>
</html>
