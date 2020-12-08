<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>GasMet | Result</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <jsp:directive.include file="/WEB-INF/jspf/headDip.jspf" />
</head>
<body class="hold-transition sidebar-mini layout-fixed  bg-dark">
<div class="wrapper">

    <%--<jsp:directive.include file="/WEB-INF/jspf/navBar.jspf" />

    <jsp:directive.include file="/WEB-INF/jspf/sideBar.jspf" />--%>

    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
   
          </div><!-- /.col -->
    
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content text-dark">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
       <!-- Main row -->
        <div class="row">
          <!-- Left col -->
          <section class="col-lg-6 connectedSortable">
            <!-- Custom tabs (Charts with tabs)-->
            <div class="card">
              <div class="card-header bg-dark">
                <h3 class="card-title ">
                  <i class="fas fa-chart-pie mr-1"></i>
                  Metric result
                </h3>
                
              </div><!-- /.card-header -->
              <div class="card-body table-responsive p-0" style="height: 600px;">
                <table class="table table-bordered table-head-fixed  text-center">
             
                  <thead>                  
                    <tr>                      
                      <th>Metric</th>
                      <th>Metric description</th>
                      <th>Cost Smell description</th>
                      <th>Value</th>
                      <th>Line</th>                      
                    </tr>
                  </thead>
                  <tbody>
                        <c:forEach items="${metriche}" var="met">
                    <tr>
                      
                        <td><span class="badge bg-danger">${met.metric}</span></td>
                      <td>
                        ${met.description}
                      </td>
                      <td>${met.descCostSmell}</td>
                      <td>${met.valore}</td>
                      <td>${met.line}</td>
                    </tr>
                    </c:forEach>
                   </tbody>
                </table>
              </div><!-- /.card-body -->
            </div>
            <!-- /.card -->

             </section>
                <section class="col-lg-6 connectedSortable">
            <!-- Custom tabs (Charts with tabs)-->
            <!-- /.card -->
 <div class="row">
          <div class="col-12">
            <div class="card">
               <div class="card-header bg-dark">
                <h3 class="card-title">
                  <i class="fas fa-chart-pie mr-1"></i>
                  File 
                </h3>
                
              </div>
              <!-- /.card-header -->
              <div class="card-body table-responsive p-0" style="height: 600px;">
                <table class="table table-head-fixed text-nowrap">
                  <thead>
                    <tr>
                       <th>Line</th>
                      <th>Code</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${file}" var="f">
                    <tr>
                      
                        <td><span class="badge bg-gradient-green">${f.i}</span></td>
                      <td ${f.b}>
                        ${f.a}
                      </td>
                     
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
             </section>
            </div>
            <!-- /.card -->
          </section>
        
          <!-- /.Left col -->
          <!-- right col (We are only adding the ID to make the widgets sortable)-->
          
          <!-- right col -->
        </div>
        <!-- /.row (main row) -->
  
    <!-- /.content -->

 

  
<!-- ./wrapper -->
<jsp:directive.include file="/WEB-INF/jspf/scriptDip.jspf" />

</body>
</html>
