<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<script type="text/javascript">
	function search() {
		var key = document.getElementById("idsearch").value;
		window.location.replace('actor/search.do?keyword=' + key);
	}
	function hidSearch(event) {
		if (e.which == 13)
			return false;
	}
</script>

<!-- *************************** -->
<!-- HERE STARTS THE NEW MENUBAR -->
<!-- *************************** -->



<div class='navbar navbar-inverse navbar-fixed-top'>
	<div class='container'>

		<!-- BUTTON WHEN MOBILE OR EXTRA SMALL SIZE -->
		<div class='navbar-header'>
			
			
        	<a href="welcome/index.do" class="navbar-brand hidden-sm hidden-md hidden-lg">
        		<img class="spm-mobile-logo" src="images/logotrans_inv.png"	alt="SportsMate" />
			</a>
      		
			
			<button type='button' class='navbar-toggle' data-toggle='collapse'
				data-target='.navbar-collapse'>
				<span class='sr-only'>Toggle Navigation</span> <span
					class='icon-bar'></span> <span class='icon-bar'></span> <span
					class='icon-bar'></span>
			</button>
		</div>


		<!-- *********************************************** -->
		<!-- HERE STARTS THE DIFFERENT MENU'S AND SUB-MENU'S -->
		<!-- *********************************************** -->



		<!-- Main headbar instructions -->
		<ul class='nav navbar-nav collapse navbar-collapse'>



			
				<!-- Antiguo enlace de nombre de administrador -> <spring:message code="master.page.administrator" /> -->

			<!-- AUTENTICATED -->
			<security:authorize access="isAuthenticated()">
				<li>
					<a href='#' data-toggle='dropdown' class="text-capitalize">
						<!-- Mayus la primera por css --> <security:authentication
							property="principal.username" /> <span class='caret'></span>
					</a>
					<ul class='dropdown-menu'>

						<!-- PROFILE CUSTOMER -->
						<security:authorize access="hasRole('CUSTOMER')">
							<li>
								<a href="customer/display.do">
									<spring:message code="master.page.customer.listProfile" />
								</a>
							</li>
							<li class='divider'></li>
						</security:authorize>

						<!-- PROFILE USER -->
						<security:authorize access="hasRole('USER')">
							<li>
								<a href="user/display.do">
									<spring:message	code="master.page.user.listProfile" />
								</a>
							</li>
							<li class='divider'></li>
						</security:authorize>

						
						
						<!-- FOLDERS USER -->
						<security:authorize access="hasRole('USER')">
							<li>
								<a href="folder/actor/list.do">
									<spring:message code="master.page.folders" />
								</a>
							</li>
							<li class='divider'></li>
						</security:authorize>

						<!-- FOLDERS CUSTOMER -->
						<security:authorize access="hasRole('CUSTOMER')">
							<li>
								<a href="folder/actor/list.do"><spring:message
										code="master.page.folders" />
								</a>
							</li>
							<li class='divider'></li>
						</security:authorize>
						

						<!-- FOLDERS ADMIN  ( DISABLED ) -->
						<!--  
						<security:authorize access="hasRole('ADMIN')">
							<li>
								<a href="folder/actor/list.do">
									<spring:message	code="master.page.folders" />
								</a>
							</li>
							<li class='divider'></li>
						</security:authorize>
						-->
						
						<!-- INVOICES ADMIN -->
						<security:authorize access="hasRole('ADMIN')">
							<li>
								<a href="admin/invoice/listCustomers.do">
									<spring:message code="master.page.admin.manageInvoices" />
								</a>
							</li>
							<li class='divider'></li>
						</security:authorize>
						
						
						
						<!-- INVOICES CUSTOMER -->
						<security:authorize access="hasRole('CUSTOMER')">
							<li>
								<a href="customer/seeInvoices.do"><spring:message
										code="master.page.customer.seeInvoices" />
								</a>
							</li>
							<li class='divider'></li>
						</security:authorize>

						<!-- FRIENDSHIPS USER -->
						<security:authorize access="hasRole('USER')">
							<li>
								<a href="friendships/user/list.do">
									<spring:message	code="master.page.user.friendships" />
								</a>
							</li>
							<li>
								<a href="friendship/user/list.do">
									<spring:message code="master.page.user.friendshipRequest" />
								</a>
							</li>
										
							<li class='divider'></li>
						</security:authorize>

						

						<!-- LOGOUT -->
						<li>
							<a href="j_spring_security_logout">
								<spring:message code="master.page.logout" />
							</a>
						</li>
					</ul>
				</li>
			</security:authorize>


			<!-- EVENTS -->
			<security:authorize access="hasRole('USER')">
				<li><a href='#' data-toggle='dropdown'> <spring:message
							code="master.page.user.eventsgroup" /> <span class='caret'></span>
				</a>
					<ul class='dropdown-menu'>
						<li><a href="event/user/listFootballEvents.do"><spring:message
									code="master.page.user.listFootballEvents" /></a></li>
						<li><a href="event/user/listTennisEvents.do"><spring:message
									code="master.page.user.listTennisEvents" /></a></li>
						<li><a href="event/user/listPaddleEvents.do"><spring:message
									code="master.page.user.listPaddleEvents" /></a></li>
						<li><a href="event/user/listOtherEvents.do"><spring:message
									code="master.page.user.listOtherEvents" /></a></li>
						<li><a href="event/user/list.do"><spring:message
									code="master.page.user.events" /></a></li>
						<li><a href="event/user/create.do"><spring:message
									code="master.page.createEvents" /></a></li>
					</ul></li>
			</security:authorize>

			<security:authorize access="hasRole('CUSTOMER')">
				<li><a href='#' data-toggle='dropdown'> <spring:message
							code="master.page.user.eventsgroup" /> <!-- le dejo el de user pq es lo mismo -->
						<span class='caret'></span>
				</a>
					<ul class='dropdown-menu'>
						<li><a href="event/customer/listFootballEvents.do"><spring:message
									code="master.page.user.listFootballEvents" /></a></li>
						<li><a href="event/customer/listTennisEvents.do"><spring:message
									code="master.page.user.listTennisEvents" /></a></li>
						<li><a href="event/customer/listPaddleEvents.do"><spring:message
									code="master.page.user.listPaddleEvents" /></a></li>
						<li><a href="event/customer/listOtherEvents.do"><spring:message
									code="master.page.user.listOtherEvents" /></a></li>
						<li><a href="event/customer/list.do"><spring:message
									code="master.page.customer.events" /></a></li>
						<li><a href="event/customer/create.do"><spring:message
									code="master.page.createEvents" /></a></li>
					</ul></li>
			</security:authorize>




			<!-- TOURNAMENT -->
			<security:authorize access="hasRole('USER')">
				<li><a href='#' data-toggle='dropdown'> <spring:message
							code="master.page.tournament" /> <span class='caret'></span>
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
				<li><a href='#' data-toggle='dropdown'> <spring:message
							code="master.page.tournament" /> <span class='caret'></span>
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
				<li><a href='#' data-toggle='dropdown'> <spring:message
							code="master.page.team" /> <span class='caret'></span>
				</a>
					<ul class='dropdown-menu'>
						<li><a href="team/user/listAllTeams.do"><spring:message
									code="master.page.user.listAllTeams" /></a></li>
						<li><a href="team/user/list.do"><spring:message
									code="master.page.user.teams" /></a></li>
						<li><a href="team/user/create.do"><spring:message
									code="master.page.actor.create.team" /></a></li>
						<li><a href="requestTeam/user/list.do"><spring:message
									code="master.page.user.requestTeam" /></a></li>
					</ul></li>
			</security:authorize>


			<!-- SPORTS CENTER -->
			<security:authorize access="hasRole('USER')">
				<li><a href="event/user/calendar/seeSportCenters.do"><spring:message
							code="master.page.user.seeSportCenters" /></a></li>
			</security:authorize>
			
			
			<!-- SEARCH BAR (CUSTOMER) -->
			<security:authorize access="hasRole('CUSTOMER')">			
			<li class="col-sm-4 col-md-4 spm-searchbar-wrapper navbar-right">
					<div class="input-group">
						<input type="text" class="form-control spm-search-input" id="idsearch" onkeypress="hidSearch(event);">
						<span class="input-group-btn">
        					<button class="btn btn-default spm-search-button" type="button" onclick="search()"><spring:message code="master.page.search" /></button>
      					</span>
      				</div>		
			</li>		

			</security:authorize>
			
			
			<!-- SEARCH BAR (USER) -->
			<security:authorize access="hasRole('USER')">
			<li class="col-sm-3 col-md-4 spm-searchbar-wrapper navbar-right">
				<div class="input-group">
						<input type="text" class="form-control spm-search-input" id="idsearch" onkeypress="hidSearch(event);">
						<span class="input-group-btn">
        					<button class="btn btn-default spm-search-button" type="button" onclick="search()"><spring:message code="master.page.search" /></button>
      					</span>
      			</div>		
			</li>
			</security:authorize>
			
			
			<!-- SEARCH BAR (ADMIN)  (DISABLED)-->
			<!--  
			<security:authorize access="hasRole('ADMIN')">
			<li class="col-sm-4 col-md-4 spm-searchbar-wrapper navbar-right">
				<div class="input-group">
						<input type="text" class="form-control spm-search-input" id="idsearch" onkeypress="hidSearch(event);">
						<span class="input-group-btn">
        					<button class="btn btn-default spm-search-button" type="button" onclick="search()"><spring:message code="master.page.search" /></button>
      					</span>
      			</div>		
			</li>
			</security:authorize>
			-->
			
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
						<li><a href="customer/create.do"><spring:message
									code="master.page.register.customer" /></a></li>
						<li><a href="user/create.do"><spring:message
									code="master.page.register.user" /></a></li>
					</ul></li>
			</security:authorize>
				
	    </ul>

	</div>
</div>

<br>
<br>


<!-- ******** -->
<!-- LOGOTYPE -->
<!-- ******** -->

<div id='header_image' class='container hidden-xs'>
	<a href="welcome/index.do"> <img src="images/logotrans.png"
		alt="SportsMate Co., Inc." />
	</a>
</div>
