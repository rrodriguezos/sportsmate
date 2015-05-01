<%--
 * display.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="admin.clients" var="clients"/>.
<spring:message code="admin.invoices" var="invoices"/>
<spring:message code="admin.invoices.admin" var="adminInvoices"/>


<div class="table-responsive">

	<display:table name="customers" id="row" class="table table-bordered table-hover">
	
		<display:column title="${clients }">
		
			<jstl:out value="${row.name }"></jstl:out>
		
		</display:column>
		
		<display:column title="${invoices }">
		
			<a href="admin/invoice/manageInvoices.do?idCustomer=${row.id }"> <jstl:out value="${adminInvoices }"></jstl:out> </a>
		
		</display:column>
	
	</display:table>
	
</div>
	
	

