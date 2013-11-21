<%-- Fragmento com trecho utilizado no menu de navegacao. --%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
	<spring:message code="label.mercadorias" var="label_mercadorias" htmlEscape="false" />
	<spring:message code="menu.lista" var="menu_lista" htmlEscape="false" />
	<spring:message code="menu.incluir" var="menu_incluir" htmlEscape="false" />
	<spring:message code="menu.sobre" var="menu_sobre" htmlEscape="false" />
	
	<ul class="nav nav-list" style="padding-top: 15px;">
		<li><em>${label_mercadorias}</em></li>
		
		<li class="${empty active || active == 'lista' ? 'active' : ''}">
			<a href="/">${menu_lista}</a>
		</li>
		<li class="${active == 'incluir' ? 'active' : ''}">
			<a href="/?form">${menu_incluir}</a>
        </li>
        
        <%-- Modificado - Rafael --%>
        
		<li class="divider"></li>
		
		<li><em>Projeto01</em></li>
		
		<li class="${active == 'form_uc' ? 'active' : ''}">
			<a href="/formUC">Criar UC</a>
		</li>
		
		<li class="${active == 'lista_presenca' ? 'active' : ''}">
			<a href="/listaPresenca">Lista de Presenca</a>
		</li>
		
		<li class="${active == 'lista_alunos' ? 'active' : ''}">
			<a href="/listaAlunos">Lista de Alunos</a>
		</li>
		
		<%-- Modificado - Rafael --%>
        
		<li class="divider"></li>
		<li class="${active == 'sobre' ? 'active' : ''}">
			<a href="/sobre">${menu_sobre}</a>
		</li>
	</ul>
</div>
