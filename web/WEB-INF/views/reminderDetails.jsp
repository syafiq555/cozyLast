<!DOCTYPE html>
<html lang="en">
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.util.List" %>
    <%@page import="beans.Medication" %>
    <%@ page import="java.text.SimpleDateFormat" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>View</title>
  <!-- Bootstrap core CSS-->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <jsp:include page="_header.jsp"></jsp:include>
  <div class="content-wrapper">
    <div class="container">
	
	
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a>Reminder Details</a>
        </li>
      </ol>
      <%
          Medication medication = new Medication();
          medication = (Medication) request.getAttribute("medication");
          int medicationType = medication.getMedicationType();
          String type;
          switch(medicationType){
              case 0:
                  type = "Tablet";
                  break;
              case 1:
                  type = "Syringe";
                  break;
              case 2:
                  type = "Liquid";
                  break;
              default:
                  type = "NULL";
                  break;
          }
          
          
      %>
      <!-- Icon Cards-->
      <p style="color: red;">${errorString}</p>
      <div class="row">
            <div class="col-xl-12 col-sm-6 mb-3">
                <div class="card text-white bg-danger o-hidden h-100">
                  <div class="card-body">
                    <div class="mr-5">NAME : ${medication.getMedicationName().toUpperCase()}</div>
                    <div class="mr-5">TYPE : <%= type %></div>
                    <div class="mr-5">TIME : ${medication.getTime()}</div>
                    <BR>
                    <div class="mr-5"><fmt:formatDate type = "date" value = "${medication.getDate_start()}" /> - <fmt:formatDate type = "date" value = "${medication.getDate_end()}" /></div> 
                  </div>
                </div>
            </div>
          </div>
        </div>
        
      </div>
    
    <!-- /.container-fluid-->
    <!-- /.content-wrapper-->
    <footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Cozy 2017</small>
        </div>
      </div>
    </footer>
	
	
	
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
	
    <jsp:include page="_logoutModal.jsp"></jsp:include>
    
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="js/sb-admin-datatables.min.js"></script>
    <script src="js/sb-admin-charts.min.js"></script>
  </div>
</body>

</html>
