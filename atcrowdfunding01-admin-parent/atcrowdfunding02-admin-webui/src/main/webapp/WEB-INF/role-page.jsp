<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="jquery/jquery.pagination.js"></script>
<link rel="stylesheet" href="ztree/zTreeStyle.css"/>
<script type="text/javascript" src="ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="crowd/my-role.js"></script>
<script type="text/javascript">
	
	$(function() {
		
		// 1.为分页操作准备初始化数据
		window.pageNum = 1;
		window.pageSize = 5;
		window.keyword = "";
		
		// 2.调用执行分页的函数，显示分页效果
		generatePage();
		
		// 3.给查询按钮绑定单击响应函数
		$("#searchBtn").click(function () {
			
			// ①获取关键词数据赋值给对应的全局变量
			window.keyword = $("#keywordInput").val();
			
			// ②调用分页函数刷新页面
			generatePage();
			
		});
		
		// 4.点击新增按钮打开模态框
		$("#showAddModalBtn").click(function () {
			
			$("#addModal").modal("show");
		});
		
		$("#saveRole").click(function () {
			
			var roleName = $.trim($("#inputAdd").val());
			$.ajax({
				"url": "role/to/save.json",
				"type": "post",
				"data": {
					"name": roleName
				},
				"dataType": "json",
				"success": function (response) {
					if (response.result == "SUCCESS") {
						window.pageNum = 9999;
						generatePage();
						layer.msg("添加成功!");
					}
					if (response.result == "FAILED") {
						layer.msg("添加失败!" + response.message)
					}
					
				},
				"error": function (response) {
					layer.msg(response.status + " " + response.statusText());
				}
			});
			
			$("#inputAdd").val("");
			$("#addModal").modal("hide");
		});
		
		$("#rolePageBody").on("click",".pencilBtn",function () {
			
			$("#editModal").modal('show');
			
			var roleName = $(this).parent().prev().text();
			
			window.roleId = this.id;
			
			$("#editModal [name = roleName]").val(roleName);
		});
		
		$("#updateRoleBtn").click(function () {
			
			var roleName = $("#editModal [name = roleName]").val();
			
			$.ajax({
				"url": "role/update.json",
				"type": "post",
				"data":{
					"id":window.roleId,
					"name":roleName
				},
				"dataType": "json",
				"success": function (response) {
					if (response.result == "SUCCESS"){
						layer.msg("更新成功");
					}
					if (response.result == "FAILED"){
						layer.msg("失败!"+ response.message);
					}
				},
				"error": function (response) {
					layer.msg(response.status + " " + response.statusText());
				}
			});
			$("#editModal").modal('hide');
			
			generatePage();
		});
		
		
		$("#rolePageBody").on("click",".removeBtn",function () {
			
			var roleName = $(this).parent().prev().text();
			
			
			var roleArray = [{
				roleRemoveId:this.id,
				roleRemoveName:roleName
			}];
			
			showConfirm(roleArray);
	
		});
		
		$("#removeRoleBtn").click(function () {
			
			var requestBody = JSON.stringify(window.roleArray);
			
			$.ajax({
				"url":"role/remove.json",
				"type": "post",
				"data": requestBody,
				"contentType":"application/json;charset=UTF-8",
				"dataType": "json",
				"success": function (response) {
					
					var result = response.result;
					
					if (result == "SUCCESS"){
						layer.msg("删除成功!");
						
						generatePage();
						
					}
					
					if (result == "FAILED"){
						layer.msg("失败!"+response.message);
					}
				},
				"error": function (response) {
					layer.msg(response.status +" "+ response.statusText);
				}
			});
			
			$("#removeModal").modal('hide');
			
			$("#summaryBox").prop("checked",false);
			
		});
		$("#summaryBox").click(function () {
			
			var currentStatus = this.checked;
			
			$(".itemBox").prop("checked",currentStatus);
			
		});
		
		$("#rolePageBody").on("click",".itemBox",function () {
			
			var checkedBoxCount = $(".itemBox:checked").length;
			
			var totalBoxCount = $(".itemBox").length;
			
			$("#summaryBox").prop("checked",totalBoxCount == checkedBoxCount);
		});
		
		$("#batchRemoveBtn").click(function () {
			
			var roleArray = [];
			
			$(".itemBox:checked").each(function () {
				
				var roleId = this.id;
				
				var roleName = $(this).parent().next().text();
			
				roleArray.push({
					roleRemoveId:roleId,
					roleRemoveName:roleName
				});
			});
			
			if (roleArray.length == 0){
				layer.msg("请至少选择一个删除");
			}
			showConfirm(roleArray);
		});
		
		$("#rolePageBody").on("click",".checkBtn",function () {
			
			window.roleId = this.id;
			
			$("#assignModal").modal('show');
			
			fillAuthTree();
		});
		
		$("#assignBtn").click(function () {
			
			var authIdArray = [];
			
			var zTreObj = $.fn.zTree.getZTreeObj("authTreeDemo");
			
			var checkedNodes = zTreObj.getCheckedNodes(true);
			
			for (var i = 0; i < checkedNodes.length; i++){
			
				var checkedNode = checkedNodes[i];
				
				var authId = checkedNode.id;
				
				authIdArray.push(authId);
			}
			var requestBody = {
				"authIdArray":authIdArray,
				"roleId":[window.roleId]
			};
			requestBody = JSON.stringify(requestBody);
			
			$.ajax({
				"url":"assign/do/role/assign/auth.json",
				"type":"post",
				"data":requestBody,
				"contentType":"application/json;charset=UTF-8",
				"dataType":"json",
				"success":function (response) {
					var result = response.result;
					
					if (result == "SUCCESS"){
						layer.msg("操作成功");
					}
					if (result == "FAILED"){
						layer.msg("操作失败");
					}
				},
				"error":function (response) {
					layer.msg(response.status+" "+response.statusText);
				}
			})
			$("#assignModal").modal('hide');
			
		});
		
	});
</script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="keywordInput" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button id="searchBtn" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchRemoveBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>
						<button 
							type="button" 
							id="showAddModalBtn" class="btn btn-primary"
							style="float: right;">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="summaryBox" type="checkbox"></th>
										<th>名称</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody id="rolePageBody"></tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="pagination"><!-- 这里显示分页 --></div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/WEB-INF/modal-role-add.jsp" %>
	<%@include file="/WEB-INF/modal-role-edit.jsp" %>
	<%@include file="/WEB-INF/modal-role-remove.jsp"%>
	<%@include file="/WEB-INF/modal-role-assign-auth.jsp"%>
</body>
</html>