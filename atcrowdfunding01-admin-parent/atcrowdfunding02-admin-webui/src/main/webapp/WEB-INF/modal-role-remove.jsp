<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- 模态框（Modal） -->
<div class="modal fade" id="removeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">尚筹网模态框</h4>
            </div>
            <div class="modal-body"  >
                <h4>请确认是否要删除下列角色?</h4>
                <div id="removeRoleDiv" style="text-align: center"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="removeRoleBtn"  type="button" class="btn btn-primary">确认删除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>