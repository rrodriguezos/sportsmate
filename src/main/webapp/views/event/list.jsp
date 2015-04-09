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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="events" id="row" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="event.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" />

	<spring:message code="event.startMoment" var="startMomentHeader" />
	<display:column property="startMoment" title="${startMomentHeader}"
		format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="event.finishMoment" var="finishMomentHeader" />
	<display:column property="finishMoment" title="${finishMomentHeader}"
		format="{0,date,dd/MM/yyyy HH:mm}" />

	<spring:message code="event.numberMaxParticipant"
		var="numberMaxParticipantHeader" />
	<display:column property="numberMaxParticipant"
		title="${numberMaxParticipantHeader}" />

	<spring:message code="event.sport" var="sportHeader" />
	<display:column property="sport" title="${sportHeader}" />

	<spring:message code="event.display" var="displayHeader" />
	<display:column title="${displayHeader}">
		<security:authorize access="hasRole('USER')">
			<a href="event/user/display.do?eventId=${row.id}"> <spring:message
					code="event.display" />
			</a>
		</security:authorize>
		<security:authorize access="hasRole('CUSTOMER')">
			<a href="event/customer/display.do?eventId=${row.id}"> <spring:message
					code="event.display" />
			</a>
		</security:authorize>
	</display:column>

	<jstl:if test="${requestURI == 'event/user/listAllEvents.do' }">
		<spring:message code="event.joinEvent" var="joinEventHeader" />
		<display:column title="${joinEventHeader}">
			<jstl:if test="${!principal.events.contains(row)}">
				<security:authorize access="hasRole('USER')">
					<a href="event/user/joinEvent.do?eventId=${row.id}"> <spring:message
							code="event.joinEvent" />
					</a>
				</security:authorize>
			</jstl:if>
		</display:column>
	</jstl:if>

	<jstl:if test="${requestURI == 'event/user/list.do' }">
		<spring:message code="event.disjoinEvent" var="disjoinEventHeader" />
		<display:column title="${disjoinEventHeader}">

			<security:authorize access="hasRole('USER')">
				<a href="event/user/disjoinEvent.do?eventId=${row.id}"> <spring:message
						code="event.disjoinEvent" />
				</a>
			</security:authorize>

		</display:column>
	</jstl:if>

</display:table>

<security:authorize access="hasRole('USER')">
	<jstl:if test="${requestURI == 'event/user/list.do' }">
		<input type="button" name="create"
			value="<spring:message code="event.create"/>"
			onclick="javascript: window.location.replace('event/user/create.do');" />
	</jstl:if>
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
	<input type="button" name="create"
		value="<spring:message code="event.create"/>"
		onclick="javascript: window.location.replace('event/customer/create.do');" />
</security:authorize>

<acme:cancel code="event.back" url="welcome/index.do" />