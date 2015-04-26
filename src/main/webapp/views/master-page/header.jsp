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

<script type="text/javascript">
	function search()
	{
		var key = document.getElementById("idsearch").value;
		window.location.replace('actor/search.do?keyword='+ key);
	}
	function hidSearch(event)
	{
		  if (e.which == 13)  return false;
	}
</script>

<!-- *************************** -->
<!-- HERE STARTS THE NEW MENUBAR -->
<!-- *************************** -->



<div class='navbar navbar-inverse navbar-fixed-top'>
	<div class='container'>

		<!-- BUTTON WHEN MOBILE OR EXTRA SMALL SIZE -->
		<div class='navbar-header'>
	
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
    	<ul class='nav navbar-nav collapse navbar-collapse'>

           
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
            
            
            <!-- AUTENTICATED -->
         	<security:authorize access="isAuthenticated()">
				<li>
					<a href='#' data-toggle='dropdown' class="text-capitalize"> <!-- Mayus la primera por css -->
			        	<security:authentication property="principal.username" />
			        	<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
						
						<!-- PROFILE CUSTOMER -->
						<security:authorize access="hasRole('CUSTOMER')">
							<li><a href="profile/customer/display.do"><spring:message code="master.page.customer.listProfile" /></a></li>
							<li><a href="profile/customer/edit.do"><spring:message code="master.page.customer.editProfile" /></a></li>
						</security:authorize>
						
						<!-- PROFILE USER -->
						<security:authorize access="hasRole('USER')">
							<li><a href="profile/user/display.do"><spring:message code="master.page.user.listProfile" /></a></li>
							<li><a href="profile/user/edit.do"><spring:message code="master.page.user.editProfile" /></a></li>						
						</security:authorize>
						
						<li class='divider'></li>
						
						<!-- FOLDERS USER -->
						<security:authorize access="hasRole('USER')">
							<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
						</security:authorize>
						
						<!-- FOLDERS CUSTOMER -->
						<security:authorize access="hasRole('CUSTOMER')">
							<li><a href="folder/actor/list.do"><spring:message code="master.page.folders" /></a></li>
						</security:authorize>
						
						<li class='divider'></li>
						
						<!-- INVOICES CUSTOMER -->
						<security:authorize access="hasRole('CUSTOMER')">
							<li><a href="customer/seeInvoices.do"><spring:message code="master.page.customer.seeInvoices" /></a></li>
						</security:authorize>
						
						<!-- FRIENDSHIPS USER -->
						<security:authorize access="hasRole('USER')">
							<li><a href="friendships/user/list.do"><spring:message code="master.page.user.friendships" /></a></li>
							<li><a href="friendship/user/list.do"><spring:message code="master.page.user.friendshipRequest" /></a></li>
						</security:authorize>
						
						<li class='divider'></li>
						
						<!-- LOGOUT -->
						<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
					</ul>
				</li>
			</security:authorize>

			
			<!-- EVENTS -->
			<security:authorize access="hasRole('USER')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.user.eventsgroup" />
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
						<li><a href="event/user/listAllEvents.do"><spring:message code="master.page.user.listAllEvents" /></a></li>
						<li><a href="event/user/list.do"><spring:message code="master.page.user.events" /></a></li>
						<li><a href="event/user/create.do"><spring:message code="master.page.createEvents" /></a></li>
					</ul>
				</li>
			</security:authorize>
			
			<security:authorize access="hasRole('CUSTOMER')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.user.eventsgroup" /> <!-- le dejo el de user pq es lo mismo -->
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>				
						<li><a href="event/customer/list.do"><spring:message code="master.page.customer.events" /></a></li>
						<li><a href="event/customer/create.do"><spring:message code="master.page.createEvents" /></a></li>
					</ul>
				</li>
			</security:authorize>
			
			
			
			
			<!-- TOURNAMENT -->
			<security:authorize access="hasRole('USER')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.tournament" />
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
					
						<li><a href="tournament/listAll.do"><spring:message code="master.page.actor.all.tournaments" /></a></li>
						<li><a href="tournament/user/list.do"><spring:message code="master.page.actor.list.tournaments" /></a></li>
						<li><a href="tournament/user/create.do"><spring:message code="master.page.actor.create.tournaments" /></a></li>
						<li><a href="tournament/user/rounds/list.do"><spring:message code="master.page.user.manageTournaments" /></a></li>
					</ul>
				</li>		
			</security:authorize>
			
			<security:authorize access="hasRole('CUSTOMER')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.tournament" />
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
						<li><a href="tournament/customer/list.do"><spring:message code="master.page.actor.list.tournaments" /></a></li>
						<li><a href="tournament/customer/create.do"><spring:message code="master.page.actor.create.tournaments" /></a></li>
						<li><a href="tournament/listAll.do"><spring:message code="master.page.actor.all.tournaments" /></a></li>
						<li><a href="tournament/user/rounds/list.do"><spring:message code="master.page.user.manageTournaments" /></a></li>
					</ul>
				</li>		
			</security:authorize>
         	
         	<!-- TEAM -->
			<security:authorize access="hasRole('USER')">
				<li>
					<a href='#' data-toggle='dropdown'>
						<spring:message	code="master.page.team" />
						<span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>
						<li><a href="team/user/listAllTeams.do"><spring:message code="master.page.user.listAllTeams" /></a></li>
						<li><a href="team/user/list.do"><spring:message code="master.page.user.teams" /></a></li>
						<li><a href="team/user/create.do"><spring:message code="master.page.actor.create.team" /></a></li>
						<li><a href="requestTeam/user/list.do"><spring:message code="master.page.user.requestTeam" /></a></li>
					</ul>
				</li>		
			</security:authorize>
			
			
			<!-- SPORTS CENTER -->
         	<security:authorize access="hasRole('USER')">
					<li><a href="event/user/calendar/seeSportCenters.do"><spring:message code="master.page.user.seeSportCenters" /></a></li>			
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
			
	
			<!-- Here starts the search bar -->
						
			<li>
				<form class="navbar-form navbar-right">
					<div class="input-group">
						<input type="text" class="form-control spm-search-input" id="idsearch" onkeypress="hidSearch(event);">
						<span class="input-group-btn">
        					<button class="btn btn-default spm-search-button" type="button" onclick="search()"><spring:message code="master.page.search" /></button>
      					</span>
      				</div>
      			</form>			
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
