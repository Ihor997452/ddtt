<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test='${not empty sessionScope.message}'>
  <script>
    insertMessage('${sessionScope.message}');
  </script>
  ${sessionScope.remove("message")}
</c:if>