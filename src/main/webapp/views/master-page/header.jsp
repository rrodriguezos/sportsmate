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


<!-- *************************** -->
<!-- HERE STARTS THE NEW MENUBAR -->
<!-- *************************** -->



<div class='navbar navbar-inverse navbar-fixed-top'>
	<div class='container'>
		
		<div class='navbar-header'>
			<a href="?language=es" class="navbar-brand" >
				<div>
					<!-- <img src="images/es_flag.jpg" alt="Espanol" />  -->
					<span>ES</span>
				</div>
			</a>
		
			<a href="?language=en" class="navbar-brand" >
				<div>
					<!--   <img src="images/en_flag.jpg" alt="English" /> -->
					<span>EN</span>
				</div>
			</a>
		
			<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>
				<span class='sr-only'>Toggle Navigation</span>
				<span class='icon-bar'></span>
				<span class='icon-bar'></span>
				<span class='icon-bar'></span>
			</button> 
		</div>
		
		
		<!-- *********************************************** -->
		<!-- HERE STARTS THE DIFFERENT MENU'S AND SUB-MENU'S -->
		<!-- *********************************************** -->
		
		<!-- Main headbar instructions -->
    	<ul class='nav navbar-nav navbar-right collapse navbar-collapse'>

           
            <!-- ADMIN -->
            <security:authorize access="hasRole('ADMIN')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.administrator" />
						<span class='caret'></span>
					</a>
					
					<ul class='dropdown-menu'>
						<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
					</ul>
				</li>		
			</security:authorize>
            
            
            
            <!-- CUSTOMER -->
            <security:authorize access="hasRole('CUSTOMER')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.customer" />
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>				
						<li><a href="event/customer/list.do"><spring:message code="master.page.customer.events" /></a></li>
						<li><a href="tournament/customer/list.do"><spring:message code="master.page.customer.tournaments" /></a></li>	
						<li><a href="profile/customer/list.do"><spring:message code="master.page.customer.listProfile" /></a></li>
						<li><a href="profile/customer/edit.do"><spring:message code="master.page.customer.editProfile" /></a></li>				
						<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
					</ul>
				</li>		
				<li><a href="customer/seeInvoices.do"><spring:message code="master.page.customer.seeInvoices" /></a></li>	
			</security:authorize>
            
         
         	<!-- USER -->
         	<security:authorize access="hasRole('USER')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.user" />
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
						<li><a href="event/user/list.do"><spring:message code="master.page.user.events" /></a></li>	
						<li><a href="tournament/user/list.do"><spring:message code="master.page.user.tournaments" /></a></li>
						<li><a href="profile/user/list.do"><spring:message code="master.page.user.listProfile" /></a></li>
						<li><a href="profile/user/edit.do"><spring:message code="master.page.user.editProfile" /></a></li>
						<li><a href="event/user/calendar/seeSportCenters.do"><spring:message code="master.page.user.seeSportCenters" /></a></li>			
						<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
						<li><a href="team/user/list.do"><spring:message code="master.page.user.teams" /></a></li>
					</ul>
				</li>
				<li><a href="event/user/listAllEvents.do"><spring:message code="master.page.user.listAllEvents" /></a></li>
				<li><a href="team/user/listAllTeams.do"><spring:message code="master.page.user.listAllTeams" /></a></li>
			</security:authorize>
         	
         	
         	<!-- ANONYMOUS -->
         	<security:authorize access="isAnonymous()">
				<li>
					<a href="security/login.do">
						<spring:message code="master.page.login" />
					</a>
				</li>
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message code="master.page.register" />
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
						<li><a href="register/registerCustomer.do"><spring:message code="master.page.register.customer" /></a></li>
						<li><a href="register/registerUser.do"><spring:message code="master.page.register.user" /></a></li>
					</ul>
				</li>
			</security:authorize>
			
         	<!-- AUTENTICATED -->
         	<security:authorize access="isAuthenticated()">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message code="master.page.profile" /> 
			        	<security:authentication property="principal.username" />
			        	<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
						<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
					</ul>
				</li>
			</security:authorize>
			
         	

            <!-- CONDITIONS & COOKIES -->
            <li>
				<a href='#' data-toggle='dropdown'>
					<spring:message code="master.page.terms" />
					<span class='caret'></span>
				</a>
				
				<ul class='dropdown-menu'>
					<li><a href="conditions/laws.do"><spring:message code="master.page.laws" /></a></li>
					<li><a href="conditions/cookies.do"><spring:message code="master.page.cookies" /></a></li>
				</ul>
			</li>
			
			
	    </ul>
	    
	</div>
</div>

<br><br><br><br>


<!-- ******** -->
<!-- LOGOTYPE -->
<!-- ******** -->

<div id='header_image' class='container'> 
	<img src="images/logotrans.png" alt="SportsMate Co., Inc." />
</div>
