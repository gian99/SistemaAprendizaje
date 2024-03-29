<%@page import="practicas.login.servlet.LoginServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="application/json; charset=ISO-8859-1">
<script type="text/javascript" src="resources/js/hash.js"></script>
<link href="css/boostrap/bootstrap.min.css" rel="stylesheet" />
<link href="css/login.css" rel="stylesheet" />
<title>CEE LUIS BRAILLE</title>
</head>
<script type="text/javascript">
	var login = {};
	login.submit = function() {
		document.getElementById("p").value = document.getElementById("pwd").value;
		return true;
	}
</script>

<body>
	<div class="container">
		<form action="login" method="post" class="form-signin">
			<input type="hidden" name="f" value="signup" /> <input type="hidden"
				name="p" id="p" value="" />
			<div align="center">
				<img src="<%=request.getContextPath()%>/resources/assets/images/braille.png" alt="">
			
				<h2 class="form-signin-heading" align="center">INICIO SESION</h2>
			</div>
			<div class="form-group">
				<div>
					<div>
						<!-- 					<label>user:</label> -->
						<%
						String u = (String) request.getAttribute(LoginServlet.LOGINUSER);
						u = (u == null) ? "" : u;
						//System.out.println("u:" + u);
					%>
						<input type="input" name="u" id="u" value="<%=u%>"
							class="form-control" placeholder="&#128272; Usuario" required="required" />
					</div>
					<div>
						<!--<label>password:</label>  -->
						<input type="password" id="pwd" class="form-control"
							placeholder="&#128272; Contraseņa" />
					</div>
				</div>
			</div>
			<br />
			<div>
			<%
				String oWarning = (String) request
						.getAttribute(LoginServlet.WARNING);
				oWarning = (oWarning == null) ? "" : oWarning;
			%>

				<label style="color: red"><%=oWarning%></label>
			</div>
			<div>
				<input type="submit" value="Login"
					onclick="return login.submit();"
					class="btn btn-lg btn-primary btn-block" />
					
			</div>
		</form>
	</div>
</body>
</html>