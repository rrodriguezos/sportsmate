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
	
	<security:authorize access="hasRole('INNKEEPER')">
		<form:hidden path="status" />
	</security:authorize>
	
	<jstl:choose>
		<jstl:when test="${requestForm.id == 0}">	
			<acme:textbox code="request.title" path="title"/>		
			<br />
			
			<acme:textarea code="request.description" path="description"/>
			<br />					
		</jstl:when>
		<jstl:when test="${requestForm.id != 0}">	
			<acme:textbox code="request.title" path="title" readonly="true"/>		
			<br />
			
			<acme:textarea code="request.description" path="description" readonly="true"/>
			<br />					
		</jstl:when>
	</jstl:choose>
	
	<security:authorize access="hasRole('ADMIN')">
		<form:label path="status"><spring:message code="request.status"/></form:label>
		<form:select path="status" >			
			<form:options items="${allStatus}" />
		</form:select>
		<form:errors cssClass="error" path="status" />
		<br />
		<br />
		
		<acme:textarea code="request.comments" path="comments"/>	
		<br />		
		<br />			
	</security:authorize>
	
	<acme:submit code="request.save" name="save" />&nbsp;	
		
	<security:authorize access="hasRole('INNKEEPER')">				
		<acme:cancel code="request.cancel" url="request/innkeeper/list.do"/>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">		
		<acme:cancel code="request.cancel" url="request/administrator/list.do"/>
	</security:authorize>
	
</form:form>


	

