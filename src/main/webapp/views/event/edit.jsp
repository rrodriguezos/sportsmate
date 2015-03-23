<%--
 * edit.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="eventForm">

	<form:hidden path="id" />
	
	<acme:textbox code="event.title" path="title"/>
	<br/>
	
	<acme:textbox code="event.startMoment" path="startMoment"/>
	<br/>
	
	<acme:textbox code="event.finishMoment" path="finishMoment"/>
	<br/>
	
	<acme:textarea code="event.description" path="description"/>
	<br/>
	
	<acme:textbox code="event.numberMaxParticipant" path="numberMaxParticipant"/>
	<br/>
	
	<form:label path="sport"><spring:message code="event.sport"/></form:label>
		<form:select path="sport" >
			<form:option label="------" value="0"/>
			<form:options items="${sports}" itemLabel="name" itemValue="id"/>
		</form:select>
		<form:errors cssClass="error" path="sport" />
	<br />
	
	<acme:textbox code="event.otherSport" path="otherSport"/>
	<br/>
	
	<acme:textbox code="event.place" path="place"/>
	<br/>
	
</form:form>


	

