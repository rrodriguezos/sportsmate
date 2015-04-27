<%--
 * list.jsp
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1>
	<spring:message code="actor.users" />
</h1>
<display:table name="users" id="row" requestURI="user/search.do"
	pagesize="5" class="displaytag">

	<spring:message code="user.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	<spring:message code="user.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" />

	<spring:message code="user.email" var="emailHeader" />
	<display:column property="email" title="${emailHeader}" />
	
	<security:authorize access="hasRole('USER')">
	<spring:message code="user.sendRequest" var="sendRequestHeader" />
	<display:column title="${sendRequestHeader}">
		<jstl:if test="${principal != row}">
		<a href="friendship/user/sendRequest.do?userFriendId=${row.id}"> <spring:message
				code="user.sendRequest" />
		</a>
		</jstl:if>
	</display:column>
	</security:authorize>	


</display:table>

<h1>
	<spring:message code="actor.customers" />
</h1>
<display:table name="customers" id="row" requestURI="customer/search.do"
	pagesize="5" class="displaytag">

	<spring:message code="customer.nameCenter" var="nameCenterHeader" />
	<display:column property="nameCenter" title="${nameCenterHeader}" />

	<spring:message code="customer.provinceCenter"
		var="provinceCenterHeader" />
	<display:column property="provinceCenter"
		title="${provinceCenterHeader}" />

	<spring:message code="customer.emailCenter" var="emailCenterHeader" />
	<display:column property="emailCenter" title="${emailCenterHeader}" />


</display:table>








