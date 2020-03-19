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

    <title>Registration</title>

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
            <h5 class="card-title text-center">Create an Account</h5>
            <form id="form-validation" class="form-signin" name="form-validation"  action="CreateAccount" method="post">
            
	            <c:if test="${successMsg ne null and successMsg ne ''}">
		            <div class="form-label-group">
		                <b style="color: green;">${successMsg}</b>
		            </div>
	            </c:if>
           		
              <div class="form-label-group">
            	 <b>UserName</b>
                <input type="text" id="username" name="username" class="form-control" placeholder="User Name" required autofocus>
                
              </div>

              <div class="form-label-group">
	              <b>Password</b>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
              </div>
               
               <div class="form-label-group">
            	 <b>First Name</b>
                <input type="text" id="firstName" name="firstName" class="form-control" placeholder="First Name" required>
                
              </div>
              
               <div class="form-label-group">
            	 <b>Last Name</b>
                <input type="text" id="lastName" name="lastName" class="form-control" placeholder="Last Name" required>
                
              </div>
              
               <div class="form-label-group">
            	 <b>Position</b>
                <input type="text" id="position" name="position" class="form-control" placeholder="Position" required>
              </div>
              
              <div class="form-label-group">
            	 <b>Role</b>
            	 <select id="role" name="role" class="form-control" required>
	            	 <c:forEach items="${roleList}" var="roleObj">
	            	 	<option value="${roleObj.id}">${roleObj.name}</option>
	            	 </c:forEach>
            	 </select>
              </div>
              
               <div class="form-label-group">
            	 <b>Address</b>
                <input type="text" id="address" name="address" class="form-control" placeholder="Address" required>
                
              </div>
              
               <div class="form-label-group">
            	 <b>Telephone</b>
                <input type="text" id="telephone" name="telephone" class="form-control" placeholder="Telephone" required>
                
              </div>
              
               <div class="form-label-group">
            	 <b>Emergency Contact</b>
                <input type="text" id="emergencyContact" name="emergencyContact" class="form-control" placeholder="Emergency Contact" required>
                
              </div>
              
               <div class="form-label-group">
            	 <b>Contact</b>
                <input type="text" id="contact" name="contact" class="form-control" placeholder="Contact" required>
              </div>
              
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Register</button>
                 <div class="form-label-group" style="text-align: center;">
	              	<b>Already have an account ? <a href="Login">Click Here</a></b>
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

