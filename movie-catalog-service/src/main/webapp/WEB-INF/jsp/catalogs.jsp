<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang=en>

<head>
<title>First Web Application</title>
</head>

<body>


			<div>
				<form:form action="/catalogs/add" modelAttribute="catalog" method="POST">
					<%-- <c:if test="${message }">
						<strong>${message}</strong>
					</c:if> --%>
					<table>
						<tr>
							<td><form:label path="movie.name">Movie Name: </form:label></td>
							<td><form:input path="movie.name" /></td>
						</tr>
						<tr>
							<td><form:label path="movie.desc">Movie Description: </form:label></td>
							<td><form:input path="movie.desc" /></td>
						</tr>
						<tr>
							<td><form:label path="rating.stars">Movie Rating: </form:label></td>
							<td><form:input path="rating.stars" /></td>
						</tr>
					</table>
					<input type="submit" value="Insert New Data" />
				</form:form>
			</div>
</body>

</html>