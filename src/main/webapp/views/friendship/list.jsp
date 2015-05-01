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

<br>
<div class='table-responsive'>
	<display:table name="friendships" id="row" requestURI="${requestURI}"
		pagesize="5" class="table table-bordered table-hover">

		<display:column>
			<b><spring:message code="friendship.user" />: </b>
			<jstl:out value="${row.user.name}"></jstl:out>
			<br>
			<b><spring:message code="friendship.userfriend" />: </b>
			<jstl:out value="${row.userFriend.name}"></jstl:out>
			<br>
			<b><spring:message code="friendship.date" />: </b>
			<fmt:formatDate value="${row.date}"
				pattern="{0,date,dd/MM/yyyy HH:mm}"></fmt:formatDate>
			<br>
			<b><spring:message code="friendship.acceptRequest" />: </b>
			<jstl:out value="${acceptRequestHeader}"></jstl:out>
			<br>
			<security:authorize access="hasRole('USER')">
				<a href="friendship/user/acceptRequest.do?friendshipId=${row.id}">
					<button type="button">
						<spring:message code="friendship.acceptRequest" />
					</button>
				</a>
			</security:authorize>
		</display:column>
	</display:table>
</div>

<acme:cancel code="friendship.back" url="welcome/index.do" />