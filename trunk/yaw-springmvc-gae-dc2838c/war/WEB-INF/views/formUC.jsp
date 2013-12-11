<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>
			Criar nova UC
		</h3>
	</div>

	<form:form id="criarUC" method="post" action="incluirUC">
		<div class="control-group">
			<div class="controls">

			Nome: <input type="text" name="nome" > </br>
			Semestre: <input type="text" name="semestre" > </br>
			Ano: <input type="text" name="ano" > </br>
			
			
			<table class="table ">
			<thead><th>Selecao de Aluno:</th></thread> 
			
			<br>
			
			<c:forEach items="${alunos}" var="aluno">
			<tr>
				<td width="40%">${aluno.nome}</td>
				<td><input type="checkbox" name="selecionado" value="${aluno.id}" ></td>	
				
				
			</tr>
			</c:forEach>
			</table>
			</div>
		
		<button name="botao" value="incluirUC" id="incluirUC" class="btn btn-success">Incluir</button>

		</div>
	</form:form>
</div>