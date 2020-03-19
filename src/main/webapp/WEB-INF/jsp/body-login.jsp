<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/WEB-INF/jsp/taglibs.jsp"%>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../resources/assets/favicon.ico">

    <title>Login</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/starter-template/">

    <!-- Bootstrap core CSS -->
    <link href="../resources/assets/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../resources/assets/starter-template.css" rel="stylesheet">
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.min.js"></script>
    
     <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
      <link href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" rel="stylesheet">
    
     <link href="../resources/assets/style.css" rel="stylesheet">
     
     
     
    
    <style type="text/css">
     
     body {
		  background: #007bff;
		  background: linear-gradient(to right, #0062E6, #33AEFF);
		 
		}
	</style>
    
  </head>  
  
  <body>
  
   <main role="main" class="container">

   <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Sign In</h5>
            <form id="form-validation" class="form-signin" name="form-validation"  action="Login" method="post">
           
              <div class="form-label-group">
            	 <b>UserName</b>
                <input type="text" id="username" name="username" class="form-control" placeholder="Email address" required autofocus>
                
              </div>

              <div class="form-label-group">
	              <b>Password</b>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
              </div>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                <div class="form-label-group" style="text-align: center;">
	              	<b>Need an <a href="Registration">account?</a></b>
	            </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  </main><!-- /.container -->
    
<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../resources/assets/popper.min.js"></script>
    <script src="../resources/assets/bootstrap.min.js"></script>
  </body>
</html>

