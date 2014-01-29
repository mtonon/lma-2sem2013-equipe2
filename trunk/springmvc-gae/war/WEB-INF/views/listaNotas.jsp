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
							<th>Nome</th>
							<c:forEach items="${notas}" var="nota">
								<th>${nota.nome}</th>
							</c:forEach>
						</tr>
					</thead>
					<c:forEach items="${alunoNotaBeans}" var="alunoNotaBean">
						<tr>
							<td>${alunoNotaBean.nomeAluno}</td>
							
						</tr>
					</c:forEach>
				</table>
				<button id="salvarNotas" class="btn btn-success" style="width:100px">Salvar</button>
			    <button id="Voltar" class="btn btn-warning" style="width:100px">Notas</button>
			</div>
	</form:form>
</div>