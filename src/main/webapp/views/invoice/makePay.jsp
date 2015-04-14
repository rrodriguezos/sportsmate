<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="customer.invoice.fee" var="fee"></spring:message>
<spring:message code="customer.invoice.datePay" var="datePay"></spring:message>
<spring:message code="customer.invoice.deadLine" var="deadLine"></spring:message>
<spring:message code="customer.invoice.details" var="details"></spring:message>
<spring:message code="customer.invoice.makePay" var="pay"></spring:message>





<display:table name="invoice" id="row"

requestURI="customer/makePay.do"
pagesize="5" class="displaytag" >


<display:column title="${fee}"> 
	<jstl:out value="${row.fee }"/>
</display:column>


<display:column title="${deadLine}"> 
	<jstl:out value="${row.deadLine }"/>
</display:column>



<display:column title="${pay}"> 
	<a href="customer/makePayPaypal.do?id=${row.id }"><jstl:out value="${ pay}"></jstl:out></a>
</display:column>




</display:table>