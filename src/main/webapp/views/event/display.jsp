<%--
 * display.jsp
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
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<br>

<form:form action="${requestURI}" modelAttribute="eventForm">

	<form:hidden path="id" />

	<!-- Event title section -->
	<div class="col-xs-12 well spm-tournament-well">
		<!-- same as tournament, no error -->
		<span class="glyphicon glyphicon-calendar">&nbsp</span> <span
			class="lead"><b><jstl:out value="${eventForm.title}" /></b></span>
	</div>

	<!-- Event content section -->
	<div class="col-xs-12 well well-sm">
		<div class="col-xs-12 col-sm-5">
			<div>
				<b><spring:message code="event.creationMoment" />: </b>
				<fmt:formatDate value="${creationMoment}" pattern="dd/MM/yyyy" />
			</div>

			<div>
				<b><spring:message code="event.startMoment" />: </b>
				<fmt:formatDate value="${eventForm.startMoment}"
					pattern="dd/MM/yyyy HH:mm" />
			</div>

			<div>
				<b><spring:message code="event.finishMoment" />: </b>
				<fmt:formatDate value="${eventForm.finishMoment}"
					pattern="dd/MM/yyyy HH:mm" />
			</div>

			<div>
				<b><spring:message code="event.sport" />: </b>
				<jstl:out value="${eventForm.sport}" />
			</div>

			<div>
				<b><spring:message code="event.place" />: </b>
				<jstl:out value="${eventForm.place}" />
			</div>
			
			<div>
				<b><spring:message code="event.address" />: </b>
				<jstl:out value="${eventForm.address}" />
			</div>

			<div>
				<b><spring:message code="event.numberMaxParticipant" />: </b>
				<jstl:out value="${eventForm.numberMaxParticipant}" />
			</div>
			
			<div>
				<b><spring:message code="event.price" />: </b>
				<jstl:out value="${eventForm.price}" />
			</div>

			<div class="col-xs-12 hidden-sm hidden-md hidden-lg">
				<br>
			</div>

		</div>
		<div class="col-xs-12 col-sm-7 alert alert-success">
			<div>
				<b><spring:message code="event.description" />: </b>
				<jstl:out value="${eventForm.description}" />
			</div>
		</div>
		
		<security:authorize access="hasRole('USER')">
		<jstl:if test="${today.after(finish) and estoyApuntado==true}">
				<div>
					<a href="event/user/voteCustomer.do?placeString=${eventForm.place}">
						<button type="button" class="btn btn-md btn-default col-xs-12 col-sm-2">
							<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
							<spring:message	code="event.vote" />
						</button>
					</a>
				</div>
		</jstl:if>
		</security:authorize>
		
		<jstl:if test="${otherSportCenter != null }">
			<jstl:out value="${eventForm.otherSportCenter}" />
		</jstl:if>

	</div>

	<br>

	<h3>
		<spring:message code="event.participants" />
	</h3>

	<div class='table-responsive'>
		<display:table name="users" id="row" pagesize="5"
			class="">

			<display:column>
				<div class="col-xs-12 spm-search-row">
					<div class="spm-search-glyphicon col-sm-1 hidden-xs">
						<span class="glyphicon glyphicon-user"></span>
					</div>
					
					<div class="col-xs-12 col-sm-4 col-md-3">
						<div>
							<b><spring:message code="team.user.name" />: </b>
							<spring:message code="team.user.name" var="nameHeader" />
							<jstl:out value="${row.name}"></jstl:out>
						</div>
						
						<div>
							<b><spring:message code="team.user.surname" />: </b>
							<spring:message code="event.user.surname" var="surnameHeader" />
							<jstl:out value="${row.surname}"></jstl:out>
						</div>
					</div>
					
					<div class="col-xs-12 col-sm-5 col-md-4">
						<div>
							<b><spring:message code="team.user.email" />: </b>
							<spring:message code="event.user.email" var="emailHeader" />
							<jstl:out value="${row.email}"></jstl:out>
						</div>
						
						<div>
							<b><spring:message code="team.user.phone" />: </b>
							<spring:message code="event.user.phone" var="phoneHeader" />
							<jstl:out value="${row.phone}"></jstl:out>
						</div>
					</div>
					
					<div class="col-xs-12 hidden-sm hidden-md hidden-lg spm-xs-separator"></div>
					
					<security:authorize access="hasRole('USER')">
						<div class="col-xs-12 col-sm-3 pull-right text-center">
							<jstl:if test="${today.after(finish) and estoyApuntado==true and miId!=row.id}">
								<a href="event/user/vote.do?eventId=${eventForm.id}&userId=${row.id}">
									<button type="button" class="btn btn-md btn-default col-xs-12">
										<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
										<spring:message	code="event.vote" />
									</button>
								</a>
							</jstl:if>
						</div>
					</security:authorize>		
				</div>
				
			</display:column>

		</display:table>
	</div>

	<security:authorize access="hasRole('CUSTOMER')">
		<acme:cancel code="event.back" url="welcome/index.do" />
		<jstl:if test="${eventForm.customer.id == customer.id && eventForm.startMoment > currentDate && eventForm.finishMoment > currentDate}">
			<input type="button" name="edit" class="btn btn-md btn-success"
				value="<spring:message code="event.edit"/>"
				onclick="javascript: window.location.replace('event/customer/edit.do?eventId= ${eventForm.id}')" />

		</jstl:if>
	</security:authorize>

	<security:authorize access="hasRole('USER')">
		<acme:cancel code="event.back" url="welcome/index.do" />
		<jstl:if test="${eventForm.owner.id == user.id && eventForm.startMoment > currentDate && eventForm.finishMoment > currentDate}">
			<input type="button" class="btn btn-md btn-success" name="edit"
				value="<spring:message code="event.edit"/>"
				onclick="javascript: window.location.replace('event/user/edit.do?eventId= ${eventForm.id}')" />


		</jstl:if>
	</security:authorize>

</form:form>
