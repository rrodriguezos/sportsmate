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
<spring:message code="sportCenter.events" var="events"/>
<spring:message code="sportCenter.tournaments" var="tournaments"/>






<h1> <jstl:out value="${events }"></jstl:out> </h1>

<display:table name="events" id="row"

requestURI="event/user/calendar/seeSportCenterCalendar.do"
pagesize="5" class="displaytag" >



</display:table>

<h1> <jstl:out value="${tournaments }"></jstl:out> </h1>


<display:table name="tournaments" id="row"

requestURI="event/user/calendar/seeSportCenterCalendar.do"
pagesize="5" class="displaytag" >




</display:table>
