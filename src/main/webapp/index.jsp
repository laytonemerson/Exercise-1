<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="Search Home Page" />

<%@include file="head.jsp"%>
<html>
    <body>
            <h2>User Display Exercise - Week 1</h2>
            <form action="/searchUser" method="GET">
                <u><h3>Select Search Type</h3></u>

                <input type="radio" name="searchType" value="last_name" checked>
                Last Name
                <input type="radio" name="searchType" value="first_name">
                First Name
                <input type="radio" name="searchType" value="userid">
                User ID
                <input type="radio" name="searchType" value="all_users">
                All Users
                <br>
                <br>
                <u><h3>Enter Search Term</h3></u>
                <input type="text" name="searchTerm" />
                <input type="submit" name="" value="Enter" />
            </form>
    </body>
</html>