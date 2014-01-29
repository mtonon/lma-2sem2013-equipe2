<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>Criar nova UC</h3>
	</div>

	<form:form id="criarUC" method="post" action="incluirUC">
		<table class="table table-hover">
			<tr>
				<th>Nome:</th>
				<th><input type="text" name="nome"></th>
			</tr>
			<tr>
				<th>Semestre:</th>
				<th><input type="text" name="semestre"></th>
			</tr>
			<tr>
				<th>Ano:</th>
				<th><input type="text" name="ano"></th>
			</tr>
			</table>
		<table class="table table-hover">
		<thead><tr>
				<th></th>
				<th>Quantidade</th>
				<th>Peso</th>
				
			</tr>
		</thead>
			<tr>
				<th>Provas:</th>
				<th><input type="text" name="numProvas" style="width: 50px"></th>
				<th><input type="text" name="pesoProvas" style="width: 50px" ></th>
			</tr>
			<tr>
				<th>Trabalhos:</th>
				<th><input type="text" name="numTrabalhos" style="width: 50px"></th>
				<th><input type="text" name="pesoTrabalhos" style="width: 50px"></th>
			</tr>
		
	</table>
		
		<table class="table">
			<thead>
				<th>Selecao de Alunos:</th>
			</thead>

			<br>

			<c:forEach items="${alunos}" var="aluno">
				<tr>
					<td width="40%">${aluno.nome}</td>
					<td><input type="checkbox" name="selecionado"
						value="${aluno.id}"></td>
				</tr>
			</c:forEach>
		</table>


		<button name="botao" value="incluirUC" id="incluirUC"
			class="btn btn-success" style="width: 100px">Incluir </button>

	</form:form>
</div>
