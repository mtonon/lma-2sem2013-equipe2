<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div>

	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>
			Unidades Curriculares <small> Listagem</small>
		</h3>
	</div>

	<form:form id="listarPresencas" method="get"
		action="/listaPresenca">
		<div class="control-group">
			<div class="controls">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>UC</th>
							<th>Ano</th>
							<th>Semestre</th>
						</tr>
					</thead>
					<c:forEach items="${ucs}" var="uc">
						<tr>
							<td><button name="linkUC" class="btn btn-warning" value="${uc.id}"  style="width:150px">${uc.nome}</button></td>
							<td>${uc.ano}</td>
							<td>${uc.semestre}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</form:form>
</div>