<%@include file="/WEB-INF/jsp/taglibs.jsp"%>
<style>
	thead{
	background: #007bff;
    background: linear-gradient(to right, #0062E6, #33AEFF);
    color: white;
	}

</style>
<div class="container">
    <h2 align="center" >Employee List</h2>
    <p align="center" >*This information is sensitive and for emergency reference only*</p>            
    <table class="table" border="1">
      <thead>
      
      
        <tr>
          <th>UserName</th>
          <c:if test="${sessionScope.isAdmin}"> 
          	 <th>Password</th>
          </c:if>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Position</th>
          <c:if test="${sessionScope.isAdmin || sessionScope.isManager }">
          	<th>Address</th>
          </c:if>
          <th>Telephone</th>
          <th>Emergency Contact No</th>
          <th>Contact No</th> 
          
        </tr>
      </thead>
      <tbody >
      <c:choose>
      	<c:when test="${userList ne null and userList ne '[]'}">
      		<c:forEach items="${userList}" var="userObj">
      		 	<tr>
		           <td>${userObj.userName}</td>
		           <c:if test="${sessionScope.isAdmin}"> 
		           		<td>${userObj.password}</td>
		           </c:if>
		           <td>${userObj.firstName}</td>
		           <td>${userObj.lastName}</td>
		           <td>${userObj.position}</td>
		           <c:if test="${sessionScope.isAdmin || sessionScope.isManager }">
		           	<td>${userObj.address}</td>
		           </c:if>
		           
		           <td>${userObj.telephone}</td>
		           <td>${userObj.emergencyContact}</td>
		           <td>${userObj.contactNumber}</td>
		           <%-- <td>${userObj.role.name}</td>  --%>
		        </tr>
      		</c:forEach>
      	</c:when> 
      	<c:otherwise>
      		<tr>
      			<td>No record found.</td>
      		</tr>
      	</c:otherwise>
      
      </c:choose>
       
      </tbody>
    </table>
  </div>