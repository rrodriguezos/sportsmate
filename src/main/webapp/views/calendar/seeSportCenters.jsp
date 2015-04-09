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



<spring:message code="sportCenter.name" var="name"/>
<spring:message code="sportCenter.seeCalendar" var="seeCalendar"/>
<spring:message code="sportCenter.city" var="city"/>
<spring:message code="sportCenter.street" var="street"/>
<spring:message code="sportCenter.CalendarOfEvents" var="calendarOfEvents"/>


<display:table name="centers" id="row"

requestURI="event/user/calendar/seeSportCenters.do"
pagesize="5" class="displaytag" >




	<display:column title="${name }">  <jstl:out value="${row.nameCenter }"></jstl:out> </display:column>
	<display:column title="${city }"> <jstl:out value="${row.city }"></jstl:out> </display:column>
	<display:column title="${street }"> <jstl:out value="${row.street }"></jstl:out> </display:column>
	<display:column title="${seeCalendar }"> <a href="event/user/calendar/seeSportCenterCalendar.do?id=${row.id }">
		<jstl:out value="${calendarOfEvents}"></jstl:out>
	</a></display:column>





</display:table>
