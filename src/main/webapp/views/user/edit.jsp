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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="user/edit.do" modelAttribute="userForm" class="form-horizontal" method="post" enctype="multipart/form-data">
	
	<form:hidden path="id"/>
	
	<jstl:if test="${userForm.id != 0 }">
		<h1><spring:message code="user.registerEdit" /></h1>
	</jstl:if>
	<jstl:if test="${userForm.id == 0 }">
		<h1><spring:message code="user.register" /></h1>
	</jstl:if>
	
	<br>
	
	<fieldset>
		
			<legend>
				<spring:message code="user.useraccount" />
			</legend>
		
				<acme:textbox code="user.username" path="username" />
				<acme:password code="user.password" path="password" />
				<acme:password code="user.password2" path="password2" />
	
	</fieldset>

<br>
		
		<legend>
			<spring:message code="user.user" />
		</legend>
		<acme:textbox code="user.name" path="name" />
		<acme:textbox code="user.surname" path="surname" />
		<acme:textbox code="user.email" path="email" />
		<acme:textbox code="user.phone" path="phone" />
	
	<br>
	
	<!-- IGNORE THE WARNING, IT'S BEACUSE THE <FORM> -->
	<jstl:if test="${id == 0}">
		<form:hidden path="terms"/>
	</jstl:if>
	
	<div class="col-xs-12"> 
		
		<div class="row">
			<div class='col-md-3 spm-profile-picture-div text-center'> 
				<img src="images/default_profile.jpg" alt="Default profile" class="img-thumbnail .spm-profile-picture" />
					<br><br>
				<form:input type="file" id="imagen" path="imagen"/> 
			</div>
		</div>
	
		<jstl:if test="${userForm.id == 0}">
			<br>
			<b><spring:message code="user.terms" /></b>
			<br>
				
			<form:checkbox path="terms" />
				<a href="conditions/laws.do" target="_blank">
					<spring:message code="user.conditions"/>
				</a> 
		</jstl:if>
		<br><br>
	
		<acme:submit name="save" code="user.save" />
	
		<acme:cancel url="welcome/index.do" code="user.cancel" />
	</div>
	</form:form>
	