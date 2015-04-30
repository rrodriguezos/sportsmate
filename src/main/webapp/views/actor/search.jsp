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
	<display:column>
		<b><spring:message code="user.name" />: </b>
		<jstl:out value="${row.name}"></jstl:out>
		<br>
		<b><spring:message code="user.surname" />: </b>
		<jstl:out value="${row.surname}"></jstl:out>
		<br>
		<b><spring:message code="user.email" />: </b>
		<jstl:out value="${row.email}"></jstl:out>
		<br>
		<security:authorize access="hasRole('USER')">
			<spring:message code="user.sendRequest" var="sendRequestHeader" />
			<jstl:if test="${principal != row}">
				<jstl:if test="${!friendshipRequested.contains(row)}">
					<a href="friendship/user/sendRequest.do?userFriendId=${row.id}">
						<button type="button">
							<spring:message code="user.sendRequest" />
						</button>
					</a>
				</jstl:if>
			</jstl:if>

		</security:authorize>

	</display:column>
</display:table>

<h1>
	<spring:message code="actor.customers" />
</h1>
<display:table name="customers" id="row" requestURI="customer/search.do"
	pagesize="5" class="displaytag">

	<display:column>
		<b><spring:message code="customer.nameCenter" />: </b>
		<jstl:out value="${row.nameCenter}"></jstl:out>
		<br>
		<b><spring:message code="customer.provinceCenter" />: </b>
		<jstl:out value="${row.provinceCenter}"></jstl:out>
		<br>
		<b><spring:message code="customer.emailCenter" />: </b>
		<jstl:out value="${row.emailCenter}"></jstl:out>
		<br>
	</display:column>
</display:table>








