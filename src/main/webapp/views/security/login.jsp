 <%--
 * login.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
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

<form:form action="j_spring_security_check" modelAttribute="credentials">

	<br>
	
	<div class='col-md-6 well'>
	
	<div class="input-group input-group-lg">
		<form:label path="username" class="input-group-addon" id="sizing-addon1">
			<span class="glyphicon glyphicon-user"></span>
		</form:label>
		
		<form:input path="username" class="form-control" aria-describedby="sizing-addon1" placeholder="Username"/>	
		<form:errors class="alert alert-danger" path="username" />
	</div>
	
	<br>
	
	<div class="input-group input-group-lg">
		<form:label path="password" class="spm-login-label input-group-addon" id="sizing-addon1">
			<span class="glyphicon glyphicon-lock"></span>
		</form:label>
		<form:password path="password" class="form-control" aria-describedby="sizing-addon1" placeholder="Password"/>
		<form:errors class="alert alert-danger" path="password" />
	</div>
	
	<br>
	
	<jstl:if test="${showError == true}">
		<div class="alert alert-danger">
			<spring:message code="security.login.failed" />
		</div>
	</jstl:if>
	
	<input type="submit" class="btn btn-lg btn-success" value="<spring:message code="security.login" />" />
	
	</div>

	<div class='col-md-6 hidden-sm hidden-xs'>
		<img id='spm-login-img' class='img-responsive' src='images/login_entering.jpg' alt='Players entering'></img>
	</div>
	
	
</form:form>