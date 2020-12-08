<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>GasMet | Home</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <jsp:directive.include file="/WEB-INF/jspf/headDip.jspf" />
</head>
<body class="hold-transition login-page bg-gradient-dark">
   <!-- /.login-logo -->
   <img src="<c:url value="/resources/logo.jpg"/>" alt="GasMet" class="brand-image img-circle elevation-3 "
           style="opacity: .8">
      
      <p class="login-box-msg"></p>

      <form method="POST"  action="${pageContext.request.contextPath}/fileupload" enctype="multipart/form-data">
        <div class="input-group mb-3">
           <input name="file"  class="custom-file" type="file">
        </div>
        
        <div class="row">
         
          <!-- /.col -->
          <div class="col-12">
             <button type="submit" class="btn btn-primary btn-block">Carica il tuo smart contract</button>
          </div>
          <!-- /.col -->
        </div>
      </form>
 
 
<!-- ./wrapper -->
<jsp:directive.include file="/WEB-INF/jspf/scriptDip.jspf" />

</body>
</html>
