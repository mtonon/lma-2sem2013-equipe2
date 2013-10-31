<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3> Alunos <small> Listagem</small></h3>
	</div>
	
	<table class="table table-hover">
		<thead>
			<tr>
				<th>#</th>
				<th>Nome</th>
				<th>RA</th>
			</tr>
		</thead>
		<c:forEach items="${alunos}" var="a">
		<tr>
			<td>${a.id}</td>
			<td>
				<spring:url value="/${a.id}" var="edit_url" htmlEscape="true">
					<spring:param name="listaAlunos"></spring:param>
				</spring:url>
				<a href="${edit_url}" title="Editar ${a.nome}">${a.nome}</a>
			</td>
			<td>${a.ra}</td>
		</tr>
		</c:forEach>
	</table>
	<form:form id="atualizarAluno" action="synch" method="GET">
		<div class="control-group">
	   		<div class="controls">
	   			<button id="salvarAluno" class="btn btn-success">Atualizar</button>
	   		</div>
	   	</div>
	</form:form>
</div>