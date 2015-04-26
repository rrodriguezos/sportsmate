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

<br>

<form:form action="${requestURI}" modelAttribute="teamForm">

	<form:hidden path="id" />
	<form:hidden path="captain"/>
	
	<acme:textbox code="team.name" path="name"/>
	<br/>
	
	<acme:textbox code="team.maxNumber" path="maxNumber"/>
	
	
			
	<security:authorize access="hasRole('USER')">
	<div class="col-xs-12">
		<br>
		<acme:submit code="team.save" name="saveEU" />
		<acme:cancel code="team.cancel" url="team/user/list.do" />
	</div>
	</security:authorize>
	
</form:form>


	

