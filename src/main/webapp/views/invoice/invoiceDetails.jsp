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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:message code="customer.invoice.fee" var="fee"></spring:message>
<spring:message code="customer.invoice.datePay" var="datePay"></spring:message>
<spring:message code="customer.invoice.deadLine" var="deadLine"></spring:message>
<spring:message code="customer.invoice.details" var="details"></spring:message>
<spring:message code="customer.invoice.makePay" var="makePay"></spring:message>
<spring:message code="customer.invoice.payMaked" var="payMaked"></spring:message>

<div class='table-responsive'>
<display:table name="invoice" id="row"

requestURI="customer/invoiceDetails.do"
pagesize="5" class="" >

	<display:column>
		<div class="col-xs-12 spm-search-row">
			<div class="spm-search-glyphicon col-sm-1 hidden-xs hidden-sm">
				<span class="glyphicon glyphicon-credit-card"></span>
			</div>
		
			<div class="col-xs-12 col-sm-5 col-md-4">
				<div>
					<b><spring:message code="customer.invoice.deadLine" />: </b>
					<spring:message code="customer.invoice.deadLine" var="deadLine"></spring:message>
					<fmt:formatDate value="${row.deadLine}" pattern="dd/MM/yyyy HH:mm" />
				</div>
				
				<div>
					<b><spring:message code="customer.invoice.datePay" />: </b>
					<spring:message code="customer.invoice.datePay" var="datePay"></spring:message>
					<fmt:formatDate value="${row.datePay}" pattern="dd/MM/yyyy HH:mm" />
				</div>
			</div>
			
			<div class="col-xs-12 col-sm-2">
				<div>
					<b><spring:message code="customer.invoice.fee" />: </b>
					<spring:message code="customer.invoice.fee" var="fee"></spring:message>
					<jstl:out value="${row.fee}"></jstl:out>
				</div>
			</div>
			
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
			
			<div class="col-xs-12 col-sm-4 pull-right">
				<jstl:if test="${row.datePay == null}">
					<a href="customer/makePayPaypal.do?id=${row.id}">
						<img src="images/paypal.png" alt="Pay with paypal button" class="pull-right">
					</a>
				</jstl:if>

				<jstl:if test="${row.datePay != null}">
					<div class="spm-invoice-payed pull-right text-center bg-success">
						<span class="glyphicon glyphicon-ok-circle"></span>
						<jstl:out value="${payMaked}"></jstl:out>
					</div>
					
				
				</jstl:if>
			</div>
		
		
		
		</div>

</display:column>


</display:table>
</div>
