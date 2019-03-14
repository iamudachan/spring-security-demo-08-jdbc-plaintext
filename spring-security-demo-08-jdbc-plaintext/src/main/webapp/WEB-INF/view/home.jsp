<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	<p>
	Welcome to the luv2code company home page!
	</p>
	
	<!--  display user name and role -->
	<p>
	Userr : <security:authentication property="principal.username"/>
	</br></br>
	Role(s) : <security:authentication property="principal.authorities"/>
	</p>
	<hr>
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
			(Only for mangaer peeps)
		</p>
	</security:authorize>
	<security:authorize access="hasRole('ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath}/systems">It System Meeting</a>
		(Only for ADMIN peeps)
	</p>
	</security:authorize>
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" method="post">
		<input type="submit" value="Logout"/>
	</form:form>
	</hr>
</body>

</html>