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
				<th>Nome</th>
				<th>RA</th>
			</tr>
		</thead>
		<c:forEach items="${alunos}" var="a">
		<tr>
			<td>${a.nome}</td>
			<td>${a.ra}</td>
		</tr>
		</c:forEach>
	</table>
	<form:form id="atualizarAluno" action="synch" method="GET">
		<div class="control-group">
	   		<div class="controls">
	   		</div>
	   	</div>
	</form:form>
</div>