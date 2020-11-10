<%--
  Created by IntelliJ IDEA.
  User: 14397
  Date: 2020/7/1
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${requestScope.empty}">
        哈哈哈 jstl 有用
    </c:if>
</body>
</html>
