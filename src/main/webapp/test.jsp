<%@ page import="com.flipkart.site.Test" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
    <h2>Hello World!</h2>

    <p>
        The time right now is: testing <%=Test.method()%>
        <br/>
        <%
            String time = Test.method();
            out.println("Confirming the time again: " + time);
        %>
    </p>

    <c:forEach var="i" begin="1" end="5">
       Testing taglib & el <c:out value=": ${i}"/><p>
    </c:forEach>

    <span style="color:blue; font-weight:bold">Everything's fine if you see the current time in the first and second lines, and values from 1 to 5 in the lines above.</span>

</body>
</html>
