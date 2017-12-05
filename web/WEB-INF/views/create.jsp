<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Create Reminder</title>
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
          <a>CREATE REMINDER</a>
        </li>
      </ol>
	  
	
	 
	  
      <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header">REMINDER</div>
      <div class="card-body">
          <form action="create" method="post">
            <div class="form-group">
                <label for="medicationName">Medicine Name</label>
                <input class="form-control" name="medicationName" type="text" aria-describedby="medicationHelp" placeholder="e.g: Paracetamol etc" required>

                <br>

                <label for="medicationType">Medicine Type</label>
                <select name="medicationType" class="form-control" required>
                    <option value="0">Tablet</option>
                    <option value="1">Syringe</option>
                    <option value="2">Liquid</option>
                </select>

                <br>

                <label for="time">Time intake (24 hr)</label>
                <select name="time" class="form-control" required>                    
                    <option name='0800' value='0800'>0800</option>
                    <option name='0900' value='0900'>0900</option>
                    <option name='1000' value='1000'>1000</option>
                    <option name='1100' value='1100'>1100</option>
                    <option name='1200' value='1200'>1200</option>
                    <option name='1300' value='1300'>1300</option>
                    <option name='1400' value='1400'>1400</option>
                    <option name='1500' value='1500'>1500</option>
                    <option name='1600' value='1600'>1600</option>
                    <option name='1700' value='1700'>1700</option>
                    <option name='1800' value='1800'>1800</option>
                    <option name='1900' value='1900'>1900</option>
                    <option name='2000' value='2000'>2000</option>
                    <option name='2100' value='2100'>2100</option>
                    <option name='2200' value='2200'>2200</option>
                    <option name='2300' value='2300'>2300</option>
                    <option name='2400' value='2400'>2400</option>
                </select>
                
                <br>

                <label for="date">Period medicine intakes</label>
                
                <br>
                
                <label for="date_start">FROM</label>
                <input type="date" name="date_start" class="form-control" required>

                <label for="date_end">TO</label>
                <input type="date" name="date_end" class="form-control" required>
                <br>

                <input type="submit" value="Create Reminder" style="width: 100%;background-color: #007bff;color:white;padding:5px;font-size:18px;border:none;padding:8px;">
                </div>
            </form>
        <p style="color: red;">${errorString}</p>
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
