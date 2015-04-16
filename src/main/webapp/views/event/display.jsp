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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<br>

<form:form action="${requestURI}" modelAttribute="eventForm">

	<form:hidden path="id" />


	<b><spring:message code="event.creationMoment" />: </b>
	<fmt:formatDate value="${creationMoment}" pattern="dd/MM/yyyy" />
	<br />
	<br />
	<b><spring:message code="event.title" />: </b>
	<jstl:out value="${eventForm.title}" />
	<br />
	<br />
	<b><spring:message code="event.startMoment" />: </b>
	<fmt:formatDate value="${eventForm.startMoment}"
		pattern="dd/MM/yyyy HH:mm" />
	<br />
	<br />
	<b><spring:message code="event.finishMoment" />: </b>
	<fmt:formatDate value="${eventForm.finishMoment}"
		pattern="dd/MM/yyyy HH:mm" />
	<br />
	<br />
	<b><spring:message code="event.sport" />: </b>
	<jstl:out value="${eventForm.sport}" />
	<br />
	<br />
	<b><spring:message code="event.numberMaxParticipant" />: </b>
	<jstl:out value="${eventForm.numberMaxParticipant}" />
	<br />
	<br />
	<b><spring:message code="event.place" />: </b>
	<jstl:out value="${eventForm.place}" />
	<br />
	<br />
	<jstl:if test="${otherSportCenter != null }">
		<jstl:out value="${eventForm.otherSportCenter}" />
	</jstl:if>

	<b><spring:message code="event.description" />: </b>
	<jstl:out value="${eventForm.description}" />
	<br />

	<br>
	<br>

	<h1>
		<spring:message code="event.participants" />
	</h1>

	<div class='table-responsive'>
		<display:table name="users" id="row" pagesize="5"
			class="table table-bordered table-hover">

			<spring:message code="event.user.name" var="nameHeader" />
			<display:column property="name" title="${nameHeader}" />

			<spring:message code="event.user.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" />

			<spring:message code="event.user.email" var="emailHeader" />
			<display:column property="email" title="${nameHeader}" />

			<spring:message code="event.user.phone" var="phoneHeader" />
			<display:column property="phone" title="${phoneHeader}" />

			<security:authorize access="hasRole('USER')">
				<jstl:if test="${today.after(finish)}">
					<display:column>
						<a
							href="event/user/vote.do?eventId=${eventForm.id}&userId=${row.id}"><spring:message
								code="event.vote" /> </a>
					</display:column>
				</jstl:if>
			</security:authorize>

		</display:table>
	</div>

	<security:authorize access="hasRole('CUSTOMER')">
		<acme:cancel code="event.back" url="event/customer/list.do" />
		<jstl:if test="${eventForm.customer.id == customer.id}">
			<input type="button" name="edit" class="btn btn-md btn-success"
				value="<spring:message code="event.edit"/>"
				onclick="javascript: window.location.replace('event/customer/edit.do?eventId= ${eventForm.id}')" />

		</jstl:if>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<acme:cancel code="event.back" url="event/user/list.do" />
		<jstl:if test="${eventForm.owner.id == user.id}">
			<input type="button" class="btn btn-md btn-success" name="edit"
				value="<spring:message code="event.edit"/>"
				onclick="javascript: window.location.replace('event/user/edit.do?eventId= ${eventForm.id}')" />


		</jstl:if>
	</security:authorize>

</form:form>
