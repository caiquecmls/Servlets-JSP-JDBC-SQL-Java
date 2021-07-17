<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Curso JSP</title>

<style>
form {
	position: absolute;
	top: 40%;
	left: 40%;
	right: 40%;
}

h5 {
	margin-top: 10%;
	font-size: 45px;
	text-align: center;
}

.msg {
	position: absolute;
	top: 100%;
	font-size: 15px;
	color: red;
	position: absolute;
	text-align: justify;
}

.geral {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

.botao {
	margin-top: 10%
}

input {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	resize: vertical;
}

.text-input {
	width: 271px;
	display: inline;
}
</style>



</head>
<body>

	<h5>BEM VINDO AO CURSO DE JSP</h5>
	<form action="ServletLogin" method="post"
		class="row mb-3 needs-validation" novalidate>

		<div class="geral">
			<input type="hidden" value="<%=request.getParameter("url")%>"
				name="url">

			<div class="col-md-6">
				<label class="form-label">Login</label> <input
					class="form-control text-input" name="login" type="text" required>
				<div class="invalid-feedback">Informe o login!</div>
			</div>


			<div class="col-md-6">
				<label class="form-label">Senha</label> <input
					class="form-control text-input" name="senha" type="password"
					required>
				<div class="invalid-feedback">Informe a senha!</div>
			</div>

			<div class="botao">
				<input type="submit" value="Enviar" class="btn btn-primary">
				<h5 class="msg">${msg}</h5>
			</div>


		</div>
	</form>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script>
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>