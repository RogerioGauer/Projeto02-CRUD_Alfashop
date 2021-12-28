//Função para perguntar antes de excluir, utilizada no formumário do arquivo categorias.jsp
function avisoDelete(nom, idc){
   var ok = confirm("Deseja realmente excluir a categoria "+nom+"?");
   if (ok) {
      var del = document.getElementById("formdel"+idc).submit();
   } 
}        


