<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<body>
<script type="text/javascript">
    $(function () {
        $("#testAjax").click(function () {
            console.log("ajax函数之前");
            $.ajax({
                "url":"test/ajax.html",
                "type":"post",
                "async":false,
                "dataType":"text",
                "success":function (response) {
                    console.log("ajax success "+response);
                }
            });
            console.log("ajax函数之后");
           /* setTimeout(function(){
                console.log("ajax函数之后");
            },5000);*/
        });
    });
</script>
<%@ include file="/WEB-INF/include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <button id="testAjax">测试Ajax异步请求</button>
        </div>
    </div>
</div>
</body>
</html>