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

	<form:form id="atualizarPresenca" method="post" action="listaPresencaAction">
		<div class="control-group">
			<div class="controls">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Nome</th>
							<c:forEach items="${aulas}" var="aula">
								<th>${aula.data}</th>
							</c:forEach>
						</tr>
					</thead>
					<c:forEach items="${alunos}" var="aluno">
						<tr>
							<td>${aluno.nome}</td>

							<%-- Cada elemento da matrizAulaPresence eh uma lista de objetos AulaPresencaBean,
							 que contem o id de uma aula e uma lista de alunos presentes  --%>
							<c:forEach items="${matrizAulaPresenca}" var="aulaPresenca">
								<td><input type="checkbox" name="presente" value="${aulaPresenca.idAula}:${aluno.id}"
									${fn:contains(aulaPresenca.alunosPresentesNaAula, aluno) ? 'checked' : ''}></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				<button name="botao" value="salvarPresenca" id="salvarPresenca" class="btn btn-success" style="width:100px">Salvar</button>
			    <button name="botao" value="exibirNotas" id="notasUC" class="btn btn-warning" style="width:100px">Notas</button>
			</div>
	</form:form>
	
	<form:form id="criarNovaAula" method="post" action="criarNovaAula">
		<div class="control-group">
			</br>
			<button value="${uc.id}" name="btnCriarNovaAula" id="btnCriarNovaAula" class="btn btn-info" style="width:100px">Nova Aula</button>
		</div>
	</form:form>
</div>