<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %> 

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean class="br.com.alfashop.repository.CategoriaDAO" id="lstcat" />

      <div class="container">
         <div class="row">
            
            <%@include file="_inc/_menu.jsp" %>            

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">               
               <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                  <h3 class="h3">Cadastro de Produto:</h3>                    
                  <a href="produtos.jsp" class="btn btn-outline-primary">Voltar</a>
               </div> 
                              
               <form action="produtos-exec" method="post">
                  <input type="hidden" name="action" value="insert">
                  
                  Categoria:<br>
                  <select name="cid">
                     <c:forEach items="${lstcat.categorias}" var="objcat">
                        <option value="${objcat.idcategoria}">${objcat.nome}</option> 
                     </c:forEach>
                  </select><br><br>
                  
                  Nome:<br>
                  <input type="text" name="nom"><br><br>
                  
                  Descrição:<br>
                  <textarea id="d" name="des" rows="2" cols="80"></textarea><br><br>
                  
                  Mais info:<br>
                  <textarea id="i" name="inf" rows="2" cols="80"></textarea><br><br>
                  
                  Valor:<br>
                  <input type="text" name="val"><br><br>
                  
                  Peso:<br>
                  <input type="text" name="pes"><br><br>
                  
                  Destaque:<br>
                  <input type="radio" name="dtq" value="s">Sim<br>
                  <input type="radio" name="dtq" value="n" checked="checked">Não<br><br>                  
                           
                  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                     <button type="submit" class="btn btn-outline-primary">Cadastrar</button>
                  </div>
               </form>
                             
            </main>
         </div>
      </div>

   </body>
</html>
