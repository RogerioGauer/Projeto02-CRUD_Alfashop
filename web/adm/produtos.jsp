<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean class="br.com.alfashop.repository.ProdutoDAO" id="lstpro" /> 

      <div class="container">
         <div class="row">
            
            <%@include file="_inc/_menu.jsp" %>            

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">               
               <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                  <h3 class="h3">Produtos:</h3>    
                  <a href="produtos-insert.jsp" class="btn btn-outline-primary">Cadastrar Produto</a>
               </div>               
               <div class="table-responsive">
                  <table class="table table-striped table-sm">
                     <thead>
                        <tr>
                           <th scope="col">Id:</th>
                           <th scope="col">Nome:</th>
                           <th scope="col">Categoria:</th>
                           <th scope="col">Valor:</th>
                           <th scope="col">Peso:</th>
                           <th scope="col">Destaque:</th>
                           <th scope="col">Ativo:</th>
                           <th scope="col">Opções:</th>                           
                        </tr>
                     </thead>
                     <tbody>
                        <c:forEach items="${lstpro.listar()}" var="objpro">                           
                           <tr>
                              <td>${objpro.idproduto}</td>
                              <td>${objpro.nome}</td>
                              <td>${objpro.categoriaid}</td>
                              <td>${objpro.valor}</td>
                              <td>${objpro.peso}</td>
                              <td>${objpro.destaque}</td>
                              <td>${objpro.ativo}</td>
                              <td>
                                 <a href="produtos-update.jsp?idp=${objpro.idproduto}" class="btn btn-outline-warning">[ A ]</a>
                                 <form action="produtos-exec" method="post" id="formdelete${objpro.idproduto}">
                                    <input type="hidden" name="action" value="delete"> 
                                    <input type="hidden" name="idp" value="${objpro.idproduto}">
                                    <button type="button" onclick="warningDelete('${objpro.nome}', '${objpro.idproduto}')" class="btn btn-outline-danger">[ X ]</button>
                                 </form>       
                              </td>
                           </tr>   
                        </c:forEach>
                     </tbody>
                  </table>
               </div>
            </main>
         </div>
      </div>
            
      <script>
         <%@include file="assets/js/warningDelete.js" %>
      </script>

   </body>
</html>

