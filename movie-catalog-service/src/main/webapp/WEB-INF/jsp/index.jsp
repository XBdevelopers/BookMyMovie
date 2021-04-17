<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang=en>

<head>
<title>MR - Spring Application</title>
</head>

<body>
	<div class="wrapper">
		<div class="content-header">
			<h1>MovieReview</h1>
		</div>
		<div class="content-body">
			<div class="default-content">
				<form:form action="/catalogs">
					<input type="submit" value="Show the catalogs" />
				</form:form>
				<form:form action="/catalogs/update">
					<input type="submit" value="Add Movie" />
				</form:form>
			</div>
			<c:if test="${null != newCatalog }">
				<div>
					<h3>Add New Movie</h3>
					<hr>
					<form:form action="/catalogs/update/add" modelAttribute="newCatalog" method="POST">
						<table>
							<tr>
								<td><form:label path="name">Movie Name: </form:label></td>
								<td><form:input path="name" /></td>
							</tr>
							<tr>
								<td><form:label path="desc">Movie Description: </form:label></td>
								<td><form:input path="desc" /></td>
							</tr>
							<tr>
								<td><form:label path="stars">Movie Rating: </form:label></td>
								<td><form:input path="stars" /></td>
							</tr>
						</table>
						<input type="submit" value="Insert New Data" />
					</form:form>
					<strong>${message}</strong>
				</div>
			</c:if>
			<c:if test="${null != catalogItems}">
				<div class="catalog-wrapper">
					<h3>Movies List</h3>
					<hr>
					<div>
						<table border="1">
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Average Rating</th>
								<th>Description</th>
							</tr>
							<c:forEach var="catalogItem" items="${catalogItems }">
								<tr>
									<td>${catalogItem.movie.movieId }</td>
									<td>${catalogItem.movie.name }</td>
									<td>${catalogItem.rating.stars }</td>
									<td>${catalogItem.movie.desc }</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</c:if>
		</div>
	</div>
    
</body>

</html>