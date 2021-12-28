//Função para perguntar antes de excluir, utilizada no formumário do arquivo produtos.jsp
function warningDelete(nom, idp){
   var ok = confirm("Deseja realmente excluir o produto "+nom+"?");
   if (ok) {
      var del = document.getElementById("formdelete"+idp).submit();
   } 
}   


