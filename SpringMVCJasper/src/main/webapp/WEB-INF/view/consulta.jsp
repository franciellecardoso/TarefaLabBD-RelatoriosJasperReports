<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta</title>
</head>
<body>
	<div>
		<jsp:include page="menu.jsp" />
	</div>

	<div align="center">
		<form action="relatorio" method="post" target="_blank">
			<table>
				<tr>
					<td><input type="text" id="medico" name="medico"
						placeholder="Informe o Nome do Medico">
					<td><input type="text" id="paciente" name="paciente"
						placeholder="Informe o Nome do Paciente">
				</tr>
				<tr>
					<td><input type="number" min="0" step="1" id="cid" name="cid"
						placeholder="Informe o CID"></td>
					<td><input type="text" id="tratamento" name="tratamento"
						placeholder="Informe o Tratamento"></td>
					<td><input type="submit" id="botao" name="botao" value="Gerar"></td>
					<td><input type="submit" id="botao" name="botao"
						value="Exibir"></td>
				</tr>

			</table>
		</form>
	</div>

	<div align="center">
		<c:if test="${not empty erro }">
			<H2>
				<c:out value="${erro }" />
			</H2>
		</c:if>
		<c:if test="${not empty saida }">
			<H2>
				<c:out value="${saida }" />
			</H2>
		</c:if>
	</div>
</body>
</html>