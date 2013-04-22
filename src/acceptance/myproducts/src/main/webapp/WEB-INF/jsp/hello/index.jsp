<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<body>
		Main page 2 ${list} <br>
		<c:forEach var="s" items="${list}">
			${s} <br>
		</c:forEach>
	</body>
</html>
