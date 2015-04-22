<%--
 * index.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<!-- <p><spring:message code="welcome.greeting.current.time" /> ${moment}</p> -->

<br>

<!-------------->
<!-- Carousel -->
<!-------------->

<div class="col-md-7 col-sm-12 hidden-xs">

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="6000">
  
  <!-- Indicators -->
  
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="images/carousel_01.jpg" alt="Carousel_image_01">
      <div class="carousel-caption">
      <div class="spm-carousel-text-bck">
        <span class="spm-carousel-text"><spring:message code="welcome.users" /></span>
        <p><spring:message code="welcome.users.caption" /></p>
      </div>
      </div>
    </div>
    
    <div class="item">
      <img src="images/carousel_02.jpg" alt="Carousel_image_02">
      <div class="carousel-caption">
      <div class="spm-carousel-text-bck">
        <span class="spm-carousel-text"><spring:message code="welcome.customers" /></span>
        <p><spring:message code="welcome.customers.caption" /></p>
      </div>
      </div>
    </div>
    
    <div class="item">
      <img src="images/carousel_03.jpg" alt="Carousel_image_03">
      <div class="carousel-caption">
      <div class="spm-carousel-text-bck">
        <span class="spm-carousel-text"><spring:message code="welcome.events" /></span>
        <p><spring:message code="welcome.events.caption" /></p>
      </div>
      </div>
    </div>
    <!-- ...  -->
  </div>

  <!-- Controls -->
  
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>

    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

</div>
<!-- Carousel's end -->

<!-- News start -->

<div class="col-md-5 col-xs-12 spm-news">
	<h2>Prueba de noticia</h2>
	<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in nisi a velit eleifend rutrum eleifend vitae mauris. Praesent maximus tortor quis eros semper, at fringilla lorem laoreet. Vestibulum at quam egestas, eleifend urna sed, vulputate orci. Quisque et iaculis lectus. Morbi blandit molestie arcu, sed mattis nisl pretium quis. Praesent dignissim tortor tincidunt lacus fringilla, ac sodales sem pharetra. Phasellus imperdiet rutrum turpis eu faucibus. Quisque fermentum non enim sed vehicula. Suspendisse potenti. Nulla consequat sapien ac porttitor tincidunt. In at sapien non purus cursus ultrices. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Maecenas suscipit, nisi in aliquam porta, dolor lectus laoreet arcu, ut elementum justo tellus vitae nulla. Sed in efficitur quam. Ut lectus tellus, euismod sit amet vestibulum sit amet, pharetra et ante.</p>
</div>

