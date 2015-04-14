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
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${nameFolder == Inbox}">
	<h1><spring:message code="message.received"/></h1>
</jstl:if>
<jstl:if test="${nameFolder == Outbox}">
	<h1><spring:message code="message.posted"/></h1>
</jstl:if>

<div class='table-responsive'>
<display:table name="messages" id="row" requestURI="message/actor/list.do" 
			   pagesize="5" class="table table-bordered table-hover">
			   
	<spring:message code="message.sendMoment" var="sendMomentHeader"/>
	<display:column property="sendMoment" title="${sendMomentHeader}" format="{0,date,dd/MM/yyyy HH:mm}" />	
		
	<spring:message code="message.subject" var="subjectHeader"/>
	<display:column property="subject" title="${subjectHeader}"  />			

	<spring:message code="message.details" var="detailsHeader"/>
	<display:column title="${detailsHeader}">
		<a href="message/actor/display.do?messageId=${row.id}">
			<spring:message code="message.details" />
		</a>
	</display:column>	
	
</display:table>
</div>

<acme:cancel code="message.back" url="folder/actor/list.do" />&nbsp;

<jstl:if test="${nameFolder == Inbox}">
	<input type="button" class="btn btn-md btn-success" name="create" value="<spring:message code="message.create"/>" 
	   	   onclick="javascript: window.location.replace('message/actor/create.do');" />
</jstl:if>