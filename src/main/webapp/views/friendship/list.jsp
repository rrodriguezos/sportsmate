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

<div>
	<display:table name="friendships" id="row"
		requestURI="friendships/user/list.do" pagesize="5"
		class="">

		<display:column>
				<div class="col-xs-12">
				<div class="spm-message-list-item">
					<div class="spm-message-glyphicon col-xs-1 hidden-xs"><span class="glyphicon glyphicon-user">&nbsp</span></div>
					
					<div class="col-xs-12 col-sm-3">
						<div>
							<b><spring:message code="friendship.user" />: </b>
							<jstl:out value="${row.user.name}"></jstl:out>
						</div>
						<!--  
						<div>
							<b><spring:message code="friendship.userfriend" />: </b>
							<jstl:out value="${row.userFriend.name}"></jstl:out>
						</div>
						-->
					</div>
					
					<div class="col-xs-12 col-sm-6">
						<b><spring:message code="friendship.date" />: </b>
						<fmt:formatDate value="${row.date}" pattern="dd/MM/yyyy HH:mm"></fmt:formatDate>
					</div>
					
					<div class="col-xs-12 hidden-sm hidden-md hidden-lg"><br></div>
					
					<div class="col-sm-2 col-xs-12 pull-right spm-message-list-btn">
						<!--  <b><spring:message code="friendship.acceptRequest" />: </b>
						<jstl:out value="${acceptRequestHeader}"></jstl:out> -->
						
						<security:authorize access="hasRole('USER')">
							<a href="friendship/user/acceptRequest.do?friendshipId=${row.id}">
								<button type="button" class="btn btn-sm btn-success col-xs-12">
									<spring:message code="friendship.acceptRequest" />
								</button>
							</a>
						</security:authorize>
					</div>
				</div>
				</div>

		</display:column>
	</display:table>
</div>
<br>
<acme:cancel code="friendship.back" url="welcome/index.do" />