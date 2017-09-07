<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>

<c:set var="count" value="0" scope="page" />

<html xmlns="http://www.w3.org/1999/xhtml">
    <body>
        <div id="content">
            <c:choose>
                <c:when test="${noTermSent == true}">
                    No search term was sent.
                </c:when>
                <c:otherwise>
                    <table border= "1" padding="10">
                        <tr>
                            <th>User ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Age</th>
                        </tr>
                        <c:forEach var="current" items="${users}">
                            <tr>
                                <td>${current.userid}</td>
                                <td>${current.firstName}</td>
                                <td>${current.lastName}</td>
                                <td>${current.calculateAge()}</td>
                            </tr>
                            <c:set var="count" value="${count + 1}" scope="page"/>
                        </c:forEach>
                    </table>
                    <c:choose>
                        <c:when test="${count eq 1}">
                            <p>Displaying ${count} result.</p>
                        </c:when>
                        <c:otherwise>
                            <p>Displaying ${count} results.</p>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

