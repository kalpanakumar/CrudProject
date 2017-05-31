<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Enter the details</h1>
<Form action="/AddDetails">
Name<input type="text" name="name"/>
Email address<input type ="text" name="emailaddress"/>
<input type="submit" value="Add"/>
</Form>
${msg}
<h1>Fetch the saved details </h1>
<p>Enter the name who's detail's you need to fetch</p>
<form action="/FetchDetails">
<input type="text" name="name"/>
<input type="submit" value="Fetch"/>
</form>
${msg1}
<h1>Delete the saved details </h1>
<p>Enter the name who's detail's you need to delete</p>
<form action ="/DeleteDetails">
<input type="text" name="name"/>
<input type="submit" value="Delete"/>
</form> 
${msg2}
<h1>Update the List</h1>
<p>Enter the name you want to update</p>
<form action="Update">
<input type="text" name="name"/>
Enter the new name <input type="text" name="Newname"/>
Enter the new email address <input type ="text" name="Newemailaddress"/>
<input id="up" type="submit" value="Update"/>
</form>
${msg3}
<h1>List</h1>
<form action ="/ListDetails">
<input type="submit" value="List"/>
</form>
${cus}
<ul>
  <c:forEach items="${customerList}" var="item">
    <li>
      <c:out value="${item}" />
    </li>
  </c:forEach>
</ul> 

 </body>
</html>