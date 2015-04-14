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

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="6000">
  
  <!-- Indicators -->
  
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <!--  <li data-target="#carousel-example-generic" data-slide-to="2"></li> -->
  </ol>

  <!-- Wrapper for slides -->
  
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="images/carousel_01.jpg" alt="Carousel_image_01">
      <div class="carousel-caption">
        <h3>SportsMate Ejemplo 1</h3>
      </div>
    </div>
    <div class="item">
      <img src="images/carousel_02.jpg" alt="Carousel_image_02">
      <div class="carousel-caption">
        <h3>SportsMate Ejemplo 2</h3>
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

<!-- Carousel's end -->

<br>
<span>Bla bla bla...</span>