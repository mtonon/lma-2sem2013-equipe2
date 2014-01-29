<%-- Pagina principal da aplicacao, a listagem de mercadorias. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>
	<div style="border-bottom: 1px solid #E5E5E5;">
		<h3>Criar novo Aluno</h3>
	</div>
	<form:form id="criarAluno" method="post" action="incluirAluno">

		<table class="table table-hover">
			<tr>
				<th>Nome:</th>
				<th><input type="text" name="nome"></th>
			</tr>
			<tr>
				<th>RA:</th>
				<th><input type="text" name="ra"></th>
			</tr>
		</table>


		<button name="botao" value="incluirAluno" id="incluirAluno"
			class="btn btn-success" style="width: 100px">Incluir</button>
	</form:form>
</div>