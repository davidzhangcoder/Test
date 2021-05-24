<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">




    <div class="modal-dialog" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <button id="closeIcon" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Resource Detail</h4>
            </div>

            <div class="modal-body">

                <div class="container-fluid table-bordered" style="padding: 15px 15px">

                    <%@include file="editresourcemodal_detail.jsp" %>

                </div>

            </div>

            <div class="modal-footer">
                <button id="closeButton" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button id="updateButton" type="button" class="btn btn-primary">Save changes</button>
            </div>

        </div>
    </div>

    <script>
        $( function () {

            $("#updateButton").on('click', function(){
                // console.log( $("#resouceformid").serialize() );
                // console.log( $("#resouceformid").serializeArray() );
                // $("#resouceformid").submit();

                var params = {
                    id : $("#dialog #id").val(),
                    title : $("#dialog #title").val(),
                    content : $("#dialog #content").val(),
                    link : $("#dialog #link").val(),
                    password : $("#dialog #password").val()
                };

                $.ajax({
                    url : '/Module_SpringMVC_from_maven_war_exploded/resource/updateResource',
                    type : 'post',
                    data : params,
                    dataType : 'json',
                    success : function(data) {
                        // if (data.status == 200)
                        {
                            // $("#allResource").bootstrapTable('refresh');
                            // initResourceCount();
                            // $("#title").val("");
                            // $("#content").val("");
                            // $("#link").val("");
                            // $("#password").val("");
                            closeDialog();
                            alert("修改成功", "", "success");
                            refresh();
                        }
                    },
                    error : function() {
                        alert("添加资源错误", "请重新操作", "error");
                    }
                });

            });

            function closeDialog() {
                $("#myModal").modal("hide");
            }

            $("#closeButton").on("click" , closeDialog);

            $("#closeIcon").on("click" , closeDialog);

        })
    </script>
</div>
