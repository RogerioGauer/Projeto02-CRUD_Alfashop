<%@include file="_inc/_verificasession.jsp" %>
<%@include file="_inc/_header.jsp" %>       

      <div class="container">
         <div class="row">
            
            <%@include file="_inc/_menu.jsp" %>            

            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">               
               <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                  <h3 class="h3">Cadastro de Categoria:</h3>                    
                  <a href="categorias.jsp" class="btn btn-outline-primary">Voltar</a>
               </div> 
                              
               <form action="categorias-exec" method="post">
                  <input type="hidden" name="action" value="insert">
                  
                  Nome:<br>
                  <input type="text" name="nom"><br><br>
                  
                  Descrição:<br>
                  <textarea id="id" name="des" rows="2" cols="80"></textarea><br><br>
                  
                  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                     <button type="submit" class="btn btn-outline-primary">Cadastrar</button>
                  </div>    
               </form>
                             
            </main>
         </div>
      </div>

   </body>
</html>
