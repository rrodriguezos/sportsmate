<%--
 * display.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<br>

<form:form action="message/actor/display.do" modelAttribute="messageForm">

	<form:hidden path="id" />	
		
	<div class="row">
	<div class="col-xs-12 col-sm-9">
	
	<div class="well well-sm spm-message-field-spacing">
		<b><spring:message code="message.sendMoment" />: </b> 
			<form:input class="spm-message-moment" path="sendMoment" readonly="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
	</div>
	
	<div class="well well-sm spm-message-field-spacing">
		<b><spring:message code="message.sender" />: </b> 
			<jstl:out value="${from}" />
	</div>	
	
	<div class="well well-sm">
		<b><spring:message code="message.recipient" />: </b> 
		<jstl:out value="${to}" />
	</div>

	<div class="">
		<b><spring:message code="message.subject" />: </b> 
			<form:input path="subject" readonly="true" class="form-control" />
		<br>
		
		<b><spring:message code="message.body" />: </b> 
			<form:textarea path="body" rows="6" readonly="true" class="form-control" />
	
	</div>
		
	</div>
	
		<div class="col-xs-12">
			<br>
			<jstl:if test="${destinatario }">
				<input type="button" class="btn btn-md btn-success" name="reply" value="<spring:message code="message.reply"/>" 
			   	   onclick="javascript: window.location.replace('message/actor/reply.do?messageId=${messageForm.id}');" />
			</jstl:if>  	

			<input type="button" class="btn btn-md btn-default" value="<spring:message code="message.back"/>" 
			   	   onclick="javascript: window.location.replace('message/actor/list.do?folderId= ${folderId}')" /> 
		</div>
	</div>  
</form:form>


