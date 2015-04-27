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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<br>

<form:form action="${requestURI}" modelAttribute="tournamentForm">

	<form:hidden path="id" />

	<acme:textbox code="tournament.title" path="title" />
	<br />

	<acme:textbox code="tournament.startMoment" path="startMoment" />

	<div class="col-xs-12">
		<span><spring:message code="tournament.start" /></span>
	</div>
	<br />
	<br />

	<acme:textbox code="tournament.finishMoment" path="finishMoment" />
	<div class="col-xs-12">
		<span><spring:message code="tournament.finish" /></span>
	</div>

	<div class="col-xs-12">
		<br>
	</div>

	<acme:textarea code="tournament.description" path="description" />
	<br />

	<acme:textbox code="tournament.numberOfTeams" path="numberOfTeams" />


	<!-- --------------------------------------------------------------------------------------------- -->

	<div class="col-xs-12">
		<div class="row">
			<br>

			<div class="form-group col-xs-12 col-md-3">
				<form:label path="advertised">
					<spring:message code="tournament.advertised" />
				</form:label>

				<form:select path="advertised" class="form-control">
					<option value="true">Yes</option>
					<option value="false">No</option>
				</form:select>
			</div>


			<div class="form-group col-xs-12 col-md-3">
				<form:label path="sport">
					<spring:message code="tournament.sport" />
				</form:label>

				<form:select path="sport" class="form-control">
					<form:options items="${sports}" />
				</form:select>
				<form:errors class="alert alert-danger spm-form-error" path="sport" />
			</div>


			<security:authorize access="hasRole('USER')">

				<div class="form-group col-xs-12 col-md-3">
					<form:label path="place">
						<spring:message code="tournament.place" />
					</form:label>
					<form:select path="place" class="form-control">
						<form:options items="${places}" />
					</form:select>
					<form:errors class="alert alert-danger spm-form-error" path="place" />
				</div>
			</security:authorize>



			<br>
			<br>
		</div>
	</div>

	<!-- --------------------------------------------------------------------------------------------- -->

	<div class="col-xs-12">
		<br>
	</div>

	<security:authorize access="hasRole('USER')">
		<acme:textbox code="tournament.otherSportCenter"
			path="otherSportCenter" />
	</security:authorize>

	<security:authorize access="hasRole('CUSTOMER')">
		<br>
		<acme:textbox code="tournament.place" path="place" readonly="true" />
	</security:authorize>


	<div class="col-xs-12">
		<security:authorize access="hasRole('CUSTOMER')">
			<br>
			<acme:submit code="tournament.save" name="saveTC" />

			<jstl:if test="${tournamentForm.id != 0}">
				<input type="submit" class="btn btn-md btn-danger" name="deleteTC"
					value="<spring:message code="tournament.delete" />"
					onclick="return confirm('<spring:message code="tournament.confirm.delete" />')" />
			</jstl:if>

			<acme:cancel code="tournament.cancel"
				url="tournament/customer/list.do" />
		</security:authorize>

		<security:authorize access="hasRole('USER')">
			<br>
			<acme:submit code="tournament.save" name="saveTU" />

			<jstl:if test="${tournamentForm.id != 0}">
				<input type="submit" class="btn btn-md btn-danger" name="deleteTU"
					value="<spring:message code="tournament.delete" />"
					onclick="return confirm('<spring:message code="tournament.confirm.delete" />')" />
			</jstl:if>

			<acme:cancel code="tournament.cancel" url="tournament/user/list.do" />
		</security:authorize>
	</div>
</form:form>




