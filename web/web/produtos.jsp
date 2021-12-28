<!------- HEADER ------->
<%@include file="./_inc/_header.jsp" %>   

<!------- MENU ------->
<%@include file="./_inc/_menu.jsp" %>

<%@page import="java.util.List" %>
<%@page import="br.com.alfashop.model.Produto" %>
<%@page import="br.com.alfashop.repository.ProdutoDAO" %>

<%    
String filtro = "1 != 1";
String nom = "";
Long idc = 0L;

//Pegar o parâmetro "idc" (idcategoria) que veio na URL.    
if (request.getParameter("idc") !=null){    
 
      String sidc = request.getParameter("idc");

      try{
          idc = Long.parseLong(sidc);
          filtro = "categoriaid = "+idc;  
      }
      catch (Exception e){
          System.out.println (e.getMessage());
      }
}

//Pegar o parâmetro "nom" que veio pelo campo pesquisar produto, do form no _header.
if (request.getParameter("nom") !=null){
    
      nom = request.getParameter("nom");     
      filtro = " p.nome like '%"+nom+"%' "; 
}

//Criar um objeto DAO para consultar por Id de categoria.
ProdutoDAO daopro = new ProdutoDAO();

//Usar o retorno (lista) para exibir os dados (neste caso, sem fazer uso do <jsp:useBean>).
List<Produto> lstpro = daopro.buscar(filtro);

//Adicionar o objeto (lstpro) a um atributo da página, para ser usado no expression language (lstpro, abaixo no forEach).
pageContext.setAttribute("lstpro", lstpro);
%>
  
         <div class="container px-4 py-4" id="custom-cards">
            <h2 class="pb-2 border-bottom">Produtos encontrados:</h2>
            <div class="row row-cols-1 row-cols-lg-3 align-items-stretch g-4 py-5">               
               
              <c:forEach items="${lstpro}" var="objpro">                               
               <div class="col">
                  <div class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg" style="background-image: url('https://picsum.photos/100/500?randon=${objpro.idproduto}');">
                     <div class="d-flex flex-column h-100 p-5 pb-3 text-white text-shadow-1">
                        <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">
                           <a href="detalhes.jsp?idp=${objpro.idproduto}">${objpro.nome}</a>
                        </h2>
                        <ul class="d-flex list-unstyled mt-auto">
                           <li class="me-auto">          
                              R$ ${objpro.valor}
                           </li>
                           <li class="d-flex align-items-center me-3">
                              <small>Categoria: ${objpro.nomecat}</small>
                           </li>
                        </ul>
                     </div>
                  </div>
               </div>   
              </c:forEach>                       
             
            </div>
         </div>
         
         <!------- FOOTER ------->         
         <%@include file="./_inc/_footer.jsp" %>