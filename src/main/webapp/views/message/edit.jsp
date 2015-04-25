<%--
 * edit.jsp
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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<br>


<form:form action="message/actor/edit.do" modelAttribute="messageForm">

	<form:hidden path="id" />	
	<form:hidden path="recipient" />
	
	<acme:textbox code="message.sendMoment" path="sendMoment" readonly="true"/>
	<div class="col-xs-12 spm-message-fixing">
		
		<b><spring:message code="message.sender" />: </b>
			<jstl:out value="${sender}" />
		
		<br><br>
	</div>
	
	<div class="col-xs-12 col-md-9">
	<div class="input-group input-group-md">	
		<jstl:if test="${messageForm.recipient == null }">
		<form:label class="input-group-addon" path="recipient"><spring:message code="message.recipient"/></form:label>
			<form:select class="form-control" path="recipient" >
				<form:options items="${actors}"  itemLabel="name" itemValue="id"/>
			</form:select>
			<form:errors class="alert alert-danger spm-form-error" path="recipient" />	
		<br />
		</jstl:if>
		</div>
		<br>
	</div>
	
	
	<!--  <b><spring:message code="message.sender" />: </b> -->
		<jstl:out value="${recipient}" />
	
	<acme:textbox code="message.subject" path="subject"/>
	<br />
	
	<acme:textarea code="message.body" path="body"/>
	<br />
	
	<div class="col-xs-12"> 
	<input type="submit" class="btn btn-md btn-success" name="send" value="<spring:message code="message.send" />" />&nbsp; 
	</div>
</form:form>


	

