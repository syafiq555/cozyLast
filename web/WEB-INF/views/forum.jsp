<!DOCTYPE html>
<html lang="en">
    

<head>
    <%@page import="java.util.ArrayList" %>
    <%@page import="java.util.List" %>
    <%@page import="beans.Thread" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Forum</title>
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
          <a>FORUM</a>
        </li>
      </ol>
      ${errorString}
      <div class="col-lg-12">
        <form action="forumSearch">
          <div class="input-group">
            <input class="form-control" type="text" placeholder="Search forum" name="string">
            <span class="input-group-btn">
              <button class="btn btn-danger" type="input">
                <i class="fa fa-search"></i>
              </button>
            </span>
          </div>
        </form><br>
        <div class="col-lg-12">
            <a class="btn btn-primary btn-block" href="myForum">Click here to view your Threads</a>
        </div>
      </div>
      
      <br>
      <div class="row">
        <div class="col-lg-12">
            
            <!-- Example Notifications Card-->
            <div class="card mb-3">
              <div class="card-header">
                <i class="fa fa-fw fa-list"></i> &nbsp;&nbsp;&nbsp;&nbsp; THREAD</div>
                    <div class="list-group list-group-flush medium">
                        <c:forEach var="thread" items="${requestScope.list}" begin="0" end="4">
                            <a class="list-group-item list-group-item-action" href="forumDetails?threadId=<c:out value="${thread.threadId}"/>&threadName=<c:out value="${thread.threadName}"/>&threadDetails=<c:out value="${thread.threadDetails}"/>&username=<c:out value="${thread.username}"/>">
                              <div class="media">
                                  <div class="media-body">
                                  <div> <strong><c:out value="${thread.threadName.toUpperCase()}"/></strong> </div>
                                  <strong><c:out value="${thread.username}"/></strong>  : <c:out value="${thread.threadDetails}"/>
                                  <div class="text-muted smaller">Today at 5:43 PM - 5m ago</div>
                                </div>
                              </div>
                            </a>
                        </c:forEach>
                        <a class="list-group-item list-group-item-action" href="forum?all=true">View all thread...</a>
                    </div>
                </div>
            
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a>POST NEW THREAD</a>
                    </li>
                </ol>
                <form action="addThread" method="post">
                    <div class="card mb-3">
                        <input type="text" placeholder="Thread's title" required name='threadName' class="form-control">
                    </div>
                    <div class="card mb-3">
                        <textarea class="form-control" required name="threadDetails" id="newThread" style="width:100%;height:150px;background-color: #004085; color:olive;border:none;padding:2%;font:18px/25px sans-serif;background:url('/pix/samples/bubble2.gif');"placeholder="Add new thread..."></textarea>  
                    </div>
                    <input type="submit" value="Submit" style="width: 100%;background-color: #007bff;color:white;padding:5px;font-size:18px;border:none;padding:8px;">
                </form>
            
        </div>
      </div>
            <br>
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
