<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
     
<html lang="en">
  <head>
    <title>Gasmet - Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <jsp:directive.include file="/WEB-INF/jspf/headDip.jspf" />

  </head>
  <body>
       <div class="hero-wrap js-fullheight img" style="background-image: url(images/bg_1.jpg);">
      <div class="overlay"></div>
      <div class="container-fluid px-0">
      	<div class="row d-md-flex no-gutters slider-text align-items-center js-fullheight justify-content-center">
	        <div class="col-md-8 text-center d-flex align-items-center ftco-animate js-fullheight">
	        	<div class="text mt-5">
	        		<span class="subheading"></span>
	            <h1 class="mb-3">Gasmet</h1>
	            <p>Carica il tuo smart contract</p>
	        <div class="container">
    		<div class="row d-flex align-items-center justify-content-center">
    			<div class="col-lg-10 p-5 ftco-wrap ftco-animate">
    		<form method="POST"  action="${pageContext.request.contextPath}/fileupload" class="domain-form d-flex mb-3" enctype="multipart/form-data">
              <div class="form-group domain-name">
   
                  <input name="file"  class="custom-file" type="file">
              </div>
              <div class="form-group domain-select d-flex">
	         
                <input type="submit" class="search-domain btn btn-primary text-center" value="Carica">
	            </div>
            </form>
          
    			</div>
    		</div>
    	</div>
	          </div>
	        </div>
	    	</div>
      </div>
    </div>
    
   
         
    
  

<jsp:directive.include file="/WEB-INF/jspf/scriptDip.jspf" />
    
  </body>
</html>