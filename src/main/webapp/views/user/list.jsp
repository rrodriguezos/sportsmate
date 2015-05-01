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
		class="">
		<display:column>
			<div class="col-xs-12">
				<div class="spm-message-list-item">
					<div class="spm-message-glyphicon col-xs-1 hidden-xs"><span class="glyphicon glyphicon-user">&nbsp</span></div>
					
					<div class="col-xs-12 col-sm-3">
						<b><spring:message code="friendship.name" />: </b>
						<jstl:out value="${row.name}"></jstl:out>
					</div>
					
					<div class="col-xs-12 col-sm-4">
						<b><spring:message code="friendship.surname" />: </b>
						<jstl:out value="${row.surname}"></jstl:out>
					</div>
					
					<div class="col-xs-12 col-sm-4">
						<b><spring:message code="friendship.email" />: </b>
						<jstl:out value="${row.email}"></jstl:out>
					</div>
	
				</div>
			</div>
		</display:column>
	</display:table>
</div>

<br>

<acme:cancel code="user.back" url="welcome/index.do" />