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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:message code="customer.invoice.fee" var="fee"></spring:message>
<spring:message code="customer.invoice.datePay" var="datePay"></spring:message>
<spring:message code="customer.invoice.deadLine" var="deadLine"></spring:message>
<spring:message code="customer.invoice.details" var="details"></spring:message>



<div class='table-responsive'>
	<display:table name="invoices" id="row"
		requestURI="customer/seeInvoices.do" pagesize="5"
		class="">
		<display:column>
		
		<div class="col-xs-12 spm-search-row">
		
			<div class="spm-search-glyphicon col-sm-1 hidden-xs hidden-sm">
				<span class="glyphicon glyphicon-credit-card"></span>
			</div>
			
			<div class="col-xs-12 col-sm-7 col-md-6">
				<jstl:if test="${row.datePay == null}">
					<div class="spm-invoices-fix-top-margin">
						<b><spring:message code="customer.invoice.datePay" />: </b>
						<div class="spm-unpaid-warning bg-warning">
							<span class="glyphicon glyphicon-alert"></span>
							<spring:message code="customer.invoice.unPaid" />
						</div>
					</div>
				</jstl:if>
				
				<jstl:if test="${row.datePay !=null}">
					<div class="spm-invoices-fix-top-margin">
						<b><spring:message code="customer.invoice.datePay" />: </b>
						<div class="spm-paid-label bg-success">
							<span class="glyphicon glyphicon-ok-circle"> </span>
							<spring:message code="customer.invoice.payMaked" />
							<span class="glyphicon glyphicon-menu-right"></span>
							<jstl:out value="${row.datePay}"></jstl:out>
						</div>
					</div>
				</jstl:if>
			</div>
			
			<div class="col-xs-12 col-sm-3">
				<div class="spm-invoices-fix-top-margin">
					<b><spring:message code="customer.invoice.fee" />: </b>
					<spring:message code="customer.invoice.fee" var="fee"></spring:message>
					<jstl:out value="${row.fee}"></jstl:out>
				</div>
			</div>
			
			<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
			
			<div class="col-xs-12 col-sm-2 pull-right">
				<a href="customer/invoiceDetails.do?id=${row.id}">
					<button type="button" class="btn btn-md btn-default col-xs-12">
						${details }
					</button> 
				</a>
			</div>
		
		</div>
		</display:column>
	</display:table>

</div>


<%-- DISABLED TABLE BY @JUANJE 
<div class='table-responsive'>
	<display:table name="items" id="row"
		requestURI="customer/ownItemList.do" pagesize="5"
		class="table table-bordered table-hover">

	</display:table>
</div>
--%>

<br>
<!-- REDIRECT INFO -->
<div class="col-xs-12 bg-info spm-invoices-redirect-span">
	<span class="glyphicon glyphicon-info-sign"></span>
	<spring:message code="customer.invoice.redirect" />
</div>