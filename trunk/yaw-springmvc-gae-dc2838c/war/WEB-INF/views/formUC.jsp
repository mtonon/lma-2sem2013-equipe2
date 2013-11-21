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

			<button id="incluirUC" class="btn btn-success">Incluir</button>
			
		 </div>
	</form:form>
</div>