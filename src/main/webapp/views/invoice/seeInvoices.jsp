<%--
 * action-2.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<spring:message code="customer.invoice.fee" var="fee"></spring:message>
<spring:message code="customer.invoice.datePay" var="datePay"></spring:message>
<spring:message code="customer.invoice.deadLine" var="deadLine"></spring:message>
<spring:message code="customer.invoice.details" var="details"></spring:message>



<div class='table-responsive'>
	<display:table name="invoices" id="row"
		requestURI="customer/seeInvoices.do" pagesize="5"
		class="table table-bordered table-hover">

		<display:column title="${fee}">${row.fee}</display:column>
		<display:column title="${datePay}">

			<jstl:if test="${row.datePay == null}">
				<spring:message code="customer.invoice.unPaid" />

			</jstl:if>

			<jstl:if test="${row.datePay !=null}">
				<spring:message code="customer.invoice.payMaked" />
				<jstl:out value="---> ${row.datePay}"></jstl:out>
			</jstl:if>

		</display:column>
		<%-- <display:column title="${deadLine}">${row.deadLine }</display:column>> --%>

		<display:column title="${details}">
			<a href="customer/invoiceDetails.do?id=${row.id}">${details }</a>
		</display:column>


	</display:table>

</div>


<div class='table-responsive'>
	<display:table name="items" id="row"
		requestURI="customer/ownItemList.do" pagesize="5"
		class="table table-bordered table-hover">

	</display:table>
</div>