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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2>
	<spring:message code="actor.users" />
</h2>

<display:table name="users" id="row" requestURI="user/search.do"
	pagesize="5" class="">
	<display:column>
		<div class="row">
		<div class="col-xs-12">
		<div class="spm-search-row">
				
				<div class="spm-search-glyphicon col-sm-1"><span class="glyphicon glyphicon-search hidden-xs"></span></div>
				
				<div class="col-sm-3">
					<div>
						<b><spring:message code="user.name" />: </b>
						<jstl:out value="${row.name}"></jstl:out>
					</div>
					
					<div>
						<b><spring:message code="user.surname" />: </b>
						<jstl:out value="${row.surname}"></jstl:out>
					</div>
				</div>
				
				<div class="col-sm-3">
					<b><spring:message code="user.email" />: </b>
					<jstl:out value="${row.email}"></jstl:out>
				</div>
					
				<div class="hidden-sm hidden-md hidden-lg">
				<br></div>
				
				<security:authorize access="hasRole('USER')">
				
				<div class="col-sm-3 col-md-3 col-xs-12 pull-right spm-button-nopaddingleft">
					<spring:message code="user.sendRequest" var="sendRequestHeader" />
					<jstl:if test="${principal != row}">
						<jstl:if test="${!friendshipRequested.contains(row)}">
							<a href="friendship/user/sendRequest.do?userFriendId=${row.id}">
								<button type="button" class="btn btn-sm btn-success col-xs-12">
									<spring:message code="user.sendRequest" />
								</button>
							</a>
						</jstl:if>
					</jstl:if>
				</div>
				
				<div class="col-xs-12 hidden-sm hidden-md hidden-lg"><br></div>
				
				<div class="col-sm-2 col-md-2 col-xs-12 pull-right spm-button-nopaddingleft">	
					<spring:message code="user.profile" var="profileHeader" />
						<jstl:if test="${principal != row}">
							<a href="user/displayOtherUser.do?userId=${row.id}">
								<button type="button" class="btn btn-sm btn-default col-xs-12">
									<spring:message code="user.profile" />
								</button>
							</a>
						</jstl:if>
				</div>
				</security:authorize>		
	</div>	
	</div>
	</div>
	</display:column>
</display:table>

<h2>
	<spring:message code="actor.customers" />
</h2>

<display:table name="customers" id="row" requestURI="customer/search.do"
	pagesize="5" class="">

	<display:column>
		<div class="row">
		<div class="col-xs-12">
		<div class="spm-search-row">
			
			<div class="spm-search-glyphicon col-sm-1"><span class="glyphicon glyphicon-search hidden-xs"></span></div>
			
			<div class="col-sm-3">
				<div>
					<b><spring:message code="customer.nameCenter" />: </b>
					<jstl:out value="${row.nameCenter}"></jstl:out>
				</div>
				<div>
					<b><spring:message code="customer.provinceCenter" />: </b>
					<jstl:out value="${row.provinceCenter}"></jstl:out>
				</div>
			</div>
			
			<div class="col-sm-5">
				<div>
					<b><spring:message code="customer.emailCenter" />: </b>
					<jstl:out value="${row.emailCenter}"></jstl:out>
				</div>		
			</div>	
		</div>
		</div>
		</div>
	</display:column>
</display:table>








