<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>P�gina de Inicio</title> 
</head>
<body>
   <% 
       String userName = (String) session.getAttribute("userName");
       String userEmail = (String) session.getAttribute("userEmail");
       if (userName != null && userEmail != null) { // Usuario autenticado
   %>
       <div>
           <p>Bienvenido, <%= userName %>!</p>
           <p>Correo electr�nico: <%= userEmail %></p>          
           <a class="btn btn-logout" href="CerrarSesion.jsp">Cerrar Sesi�n</a>
       </div>
   <% } else { // Usuario no autenticado %>
       <a class="btn btn-login" href="Login.jsp">Iniciar Sesi�n</a>
       <a class="btn btn-Signup" href="Registro.jsp">Reg�strate</a>
   <% } %>
</body>
</html>
