<!------- HEADER ------->
<%@include file="./_inc/_header.jsp" %>   

<!------- MENU ------->
<%@include file="./_inc/_menu.jsp" %>

<%@page import="java.util.List" %>
<%@page import="br.com.alfashop.model.Produto" %>
<%@page import="br.com.alfashop.repository.ProdutoDAO" %>

<%    
String filtro = "1 != 1";
Long idp = 0L;

//Pegar o parâmetro "idp" (idproduto) que veio na URL.    
if (request.getParameter("idp") !=null){    
 
      String sidp = request.getParameter("idp");

      try{
          idp = Long.parseLong(sidp);
          filtro = "idproduto = "+idp;  
      }
      catch (Exception e){
          System.out.println (e.getMessage());
      }
}

//Criar um objeto DAO para consultar por Id de produto.
ProdutoDAO daopro = new ProdutoDAO();

//Usar o retorno (lista) para exibir os dados (neste caso, sem fazer uso do <jsp:useBean>).
List<Produto> lstpro = daopro.buscar(filtro);

//Adicionar o objeto (lstpro) a um atributo da página, para ser usado no expression language (lstpro, abaixo no forEach).
pageContext.setAttribute("lstpro", lstpro);
%>

     <c:forEach items="${lstpro}" var="objpro">      
         <div class="container px-4 py-4" id="custom-cards">
            <h2 class="pb-2 border-bottom">
               ${objpro.nome}
            </h2>
            <div class="row row-cols-1 row-cols-lg-3 align-items-stretch g-4 py-5">                             
               
               Descrição: ${objpro.descricao}
               <br><br><br>       
               Detalhes: ${objpro.maisinfo}
               <br><br><br>
               Valor: R$${objpro.valor}
               <br><br><br>
               Peso: ${objpro.peso} kilos
               
            </div>
         </div>                           
     </c:forEach>  
         
         <!------- FOOTER ------->         
         <%@include file="./_inc/_footer.jsp" %>