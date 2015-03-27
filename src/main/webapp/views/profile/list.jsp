<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>




<div>
	<security:authorize access="hasRole('CUSTOMER')">
	<display:table name="profile"	requestURI="profile/customer/list.do" id="profile"
		pagesize="5" class="displayTag">
		
			<h1>
			<spring:message code="personal.actor" />
			</h1>
		<display:column property="name" titleKey="actor.name"/>
		<display:column property="surname" titleKey="actor.surname"/>
		<display:column property="email" titleKey="actor.email"/>
		<display:column property="phone" titleKey="actor.phone"/>
		
		<h1>
		<spring:message code="customer.center" />
		</h1>
		<display:column property="cif" titleKey="center.cif"/>
		<display:column property="street" titleKey="center.street"/>
		<display:column property="zip" titleKey="center.zip"/>
		<display:column property="provinceCenter" titleKey="center.provinceCenter"/>
		<display:column property="city" titleKey="center.city"/>
		<display:column property="nameCenter" titleKey="center.nameCenter"/>
		<display:column property="phoneCenter" titleKey="center.phoneCenter"/>
		<display:column property="emailCenter" titleKey="center.emailCenter"/>
		<display:column property="web" titleKey="center.web"/>
		<display:column>
				<a href="profile/customer/edit.do?customerId=${profile.id}"><spring:message code="customer.edit" /></a>
		</display:column>
		
		
</display:table>
</security:authorize>
	<security:authorize access="hasRole('USER')">
	<display:table name="profile"	requestURI="profile/user/list.do" id="profile"
		pagesize="5" class="displayTag">
		
		<h1>
			<spring:message code="personal.actor" />
			</h1>
		<display:column property="name" titleKey="actor.name"/>
		<display:column property="surname" titleKey="actor.surname"/>
		<display:column property="email" titleKey="actor.email"/>
		<display:column property="phone" titleKey="actor.phone"/>
		
		<display:column>
		<a href="profile/user/edit.do?userId=${profile.id}"><spring:message code="user.edit" /></a>
		</display:column>
		
		
</display:table>
</security:authorize>


</div>