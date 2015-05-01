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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<div class='table-responsive'>
	<display:table name="users" id="row"
		requestURI="friendships/user/list.do" pagesize="5"
		class="table table-bordered table-hover">
		<display:column>
			<b><spring:message code="friendship.name" />: </b>
			<jstl:out value="${row.name}"></jstl:out>
			<br>
			<b><spring:message code="friendship.surname" />: </b>
			<jstl:out value="${row.surname}"></jstl:out>
			<br>
			<b><spring:message code="friendship.email" />: </b>
			<jstl:out value="${row.email}"></jstl:out>
			<br>
		</display:column>
	</display:table>
</div>

<acme:cancel code="user.back" url="welcome/index.do" />
&nbsp;
