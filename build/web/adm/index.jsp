<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="">
      <title>Alfashop - Acesso Restrito</title> 

      <!-- Bootstrap core CSS -->
      <link href="./assets/css/bootstrap.min.css" rel="stylesheet">

      <!-- Custom styles for this template -->
      <link href="./assets/css/login.css" rel="stylesheet">
   </head>
   
   <body class="text-center">
      <main class="form-signin">
         <form action="login-exec" method="post">
            <img class="mb-4" src="./assets/img/login.jpg" alt="" width="53" height="53">
            <h1 class="h3 mb-3 fw-normal">Acesso Administrativo</h1>

            <div class="form-floating">
               <input type="email" class="form-control" id="floatingInput" placeholder="nome@exemplo.com" name="ema">
               <label for="floatingInput">Email:</label>
            </div>
            
            <div class="form-floating">
               <input type="password" class="form-control" id="floatingPassword" placeholder="senha" name="sen">
               <label for="floatingPassword">Senha:</label>
            </div>

            <div class="checkbox mb-3">
               <label>
                  <input type="checkbox" value="remember-me">Lembrar login.
               </label>
            </div>
            
            <button class="w-100 btn btn-lg btn-primary" type="submit">Acessar</button>
                         
            <div class="checkbox mb-3 text-danger">
               <br>
               <%
                   if (request.getParameter("msg") !=null && request.getParameter("msg").equals("bug")){
                       out.print("Não foi possível validar o login.");
                   }                   
               %>              
            </div>
            
            <p class="mt-5 mb-3 text-muted">&copy;Alfashop</p>
         </form>
      </main>
   </body>
</html>
