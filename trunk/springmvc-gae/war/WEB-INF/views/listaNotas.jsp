<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div>

	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>
			Presença dos Alunos <small> Listagem</small>
		</h3>
	</div>
	
	<form:form id="atualizarPresenca" method="post" action="salvarNotasAction">
		<div class="control-group">
			<div class="controls">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Aluno</th>
							<c:forEach items="${tableHeadList}" var="nomeDaNota">
								<th>${nomeDaNota}</th>
							</c:forEach>
						</tr>
					</thead>
					<c:forEach items="${alunoNotaBeans}" var="alunoNotaBean">
						<tr>
							<td>${alunoNotaBean.nomeAluno}</td>
							<c:forEach items="${alunoNotaBean.notasAluno}" var="nota">
								<td>${nota}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
	</form:form>
</div>