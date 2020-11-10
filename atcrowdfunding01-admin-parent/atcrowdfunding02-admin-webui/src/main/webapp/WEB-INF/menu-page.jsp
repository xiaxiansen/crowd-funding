<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp" %>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-menu.js"></script>
<script type="text/javascript">
    $(function () {
        generateTree();
        
        $("#treeDemo").on("click",".addBtn",function () {
            
            window.pid = this.id;
            
            $("#menuAddModal").modal("show");
            
            return false;
        });
        
        $("#menuSaveBtn").click(function () {
            
            var name = $.trim($("#menuAddModal [name=name]").val());
            var url = $.trim($("#menuAddModal [name=url]").val());
            var icon = $.trim($("#menuAddModal [name=icon]:checked").val());

            $.ajax({
                "url":"menu/save.json",
                "type":"post",
                "data":{
                    "pid":window.pid,
                    "name":name,
                    "url":url,
                    "icon":icon
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;
                    
                    if (response.result == "SUCCESS"){
                        layer.msg("操作成功");
                    }
                    if (response.result == "FAILED"){
                        layer.msg("操作失败!"+response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            $("#menuAddModal").modal("hide");

            setTimeout(function(){
                generateTree();
            },1000);

            $("#menuResetBtn").click();
        });

        $("#treeDemo").on("click",".editBtn",function () {

            window.id = this.id;

            $("#menuEditModal").modal("show");

            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
            
            var key = "id";
            
            var value = window.id;
            
            var currentNode = zTreeObj.getNodeByParam(key, value);
            
            $("#menuEditModal [name=name]").val(currentNode.name);

            $("#menuEditModal [name=url]").val(currentNode.url);

            $("#menuEditModal [name=icon]").val([currentNode.icon]);

            return false;
        });
        
        $("#menuEditBtn").click(function () {
            var name = $("#menuEditModal [name=name]").val();

            var url = $("#menuEditModal [name=url]").val();

            var icon = $("#menuEditModal [name=icon]:checked").val();

            $.ajax({
                "url":"menu/update.json",
                "type":"post",
                "data":{
                    "id":window.id,
                    "name":name,
                    "url":url,
                    "icon":icon
                },
                "dataType":"json",
                "success":function (response) {
                    var result = response.result;

                    if (response.result == "SUCCESS"){
                        layer.msg("操作成功");
                    }
                    if (response.result == "FAILED"){
                        layer.msg("操作失败!"+response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });

            $("#menuEditModal").modal("hide");

            setTimeout(function(){
                generateTree();
            },1000);
        });
        
        $("#treeDemo").on("click",".removeBtn",function () {

            window.id = this.id;

            $("#menuConfirmModal").modal("show");

            var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");

            var key = "id";

            var value = window.id;

            var currentNode = zTreeObj.getNodeByParam(key, value);

            $("#removeNodeSpan").html("<i class='"+currentNode.icon+"'></i>"+currentNode.name);
            
            return false;
        });

        $("#confirmBtn").click(function () {
            
            $.ajax({
                "url":"menu/remove.json",
                "type":"post",
                "data":{
                    "id":window.id
                },
                "success":function (response) {
                    var result = response.result;

                    if (response.result == "SUCCESS"){
                        layer.msg("操作成功");
                    }
                    if (response.result == "FAILED"){
                        layer.msg("操作失败!"+response.message);
                    }
                },
                "error":function (response) {
                    layer.msg(response.status+" "+response.statusText);
                }
            });
            $("#menuConfirmModal").modal("hide");

            setTimeout(function(){
                generateTree();
            },1000);
        });

    });
</script>
<body>

<%@ include file="/WEB-INF/include-nav.jsp"%>
<div class="container-fluid">
    <div class="row">
        <%@ include file="/WEB-INF/include-sidebar.jsp"%>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <<div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <!-- 这个ul标签是zTree动态生成的节点所依附的静态节点 -->
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/modal-menu-add.jsp"%>
<%@include file="/WEB-INF/modal-menu-edit.jsp"%>
<%@include file="/WEB-INF/modal-menu-confirm.jsp"%>
</body>
</html>