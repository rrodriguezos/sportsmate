<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div id="header_image"> 
	<img src="images/logotrans.png" alt="SportsMate Co., Inc." />
</div>

<div id="header_menubar">
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
			
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/action-1.do"><spring:message code="master.page.administrator.action.1" /></a></li>
					<li><a href="administrator/action-2.do"><spring:message code="master.page.administrator.action.2" /></a></li>					
					<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
				</ul>
			</li>
				
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>					
					<li><a href="event/customer/list.do"><spring:message code="master.page.customer.events" /></a></li>	
					<li><a href="customer/seeInvoices.do"><spring:message code="master.page.customer.seeInvoices" /></a></li>
					<li><a href="profile/customer/list.do"><spring:message code="master.page.customer.listProfile" /></a></li>
					<li><a href="profile/customer/edit.do"><spring:message code="master.page.customer.editProfile" /></a></li>				
					<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
				</ul>
			</li>
				
		</security:authorize>
		
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message	code="master.page.user" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="event/user/list.do"><spring:message code="master.page.user.events" /></a></li>	
					<li><a href="profile/user/list.do"><spring:message code="master.page.user.listProfile" /></a></li>
					<li><a href="profile/user/edit.do"><spring:message code="master.page.user.editProfile" /></a></li>
					<li><a href="event/user/calendar/seeSportCenters.do"><spring:message code="master.page.user.seeSportCenters" /></a></li>			
					<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
				</ul>
			</li>
				
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			
			<li><a class="fNiv"><spring:message code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="register/registerCustomer.do"><spring:message code="master.page.register.customer" /></a></li>
					<li><a href="register/registerUser.do"><spring:message code="master.page.register.user" /></a></li>
				</ul>
			</li>
			
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/action-1.do"><spring:message code="master.page.profile.action.1" /></a></li>
					<li><a href="profile/action-2.do"><spring:message code="master.page.profile.action.2" /></a></li>
					<li><a href="profile/action-3.do"><spring:message code="master.page.profile.action.3" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			
		</security:authorize>
	
	
		<li>
				<a class="fNiv"> 
					<spring:message code="master.page.terms" /> 
			        
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="conditions/laws.do"><spring:message code="master.page.laws" /></a></li>
					<li><a href="conditions/cookies.do"><spring:message code="master.page.cookies" /></a></li>
					
				</ul>
			</li>
	</ul>
	
	
</div>

<div id="header_languagebar">
	<a href="?language=en">
		<div class="flagdiv">
			<img src="images/en_flag.jpg" alt="English" />
			<span>EN</span>
		</div>
	</a>
	<a href="?language=es">
		<div class="flagdiv">
			<img src="images/es_flag.jpg" alt="Espanol" />
			<span>ES</span>
		</div>
	</a>	

</div>

