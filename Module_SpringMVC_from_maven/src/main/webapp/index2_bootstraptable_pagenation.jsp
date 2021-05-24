<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>Test BootstrapTable and pagenation</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <style>
        .table-demo {
            width: 80%;
            margin: 30px auto 0px auto;
        }
    </style>


    <!-- 全局js -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- Bootstrap table -->
    <script src="js/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="js/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="js/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- 自定义js -->
</head>
<body>

    <p> ${pageContext.request.contextPath}  </p>

    tab,
    dropdown,
    column sorting,
    validation,
    bootstraptable example crud 中拼接input的值
    alert

    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <div class="input-group">
                    <span class="input-group-addon">名称：</span>
                    <input id="titleSearch" type="text" name="titleSearch" class="form-control">
                </div>
            </div>
            <div class="col-md-2">
                <div class="input-group">
                    <span class="input-group-addon">ID：</span>
                    <input id="idSearch" type="text" name="idSearch" class="form-control">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <button type="button" class="btn btn-success" onclick="refresh()">搜索</button>
                <button type="button" class="btn btn-primary" onclick="">重置</button>
            </div>
        </div>

    </div>

    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <%@include file="/WEB-INF/pages/editresourcemodal_detail.jsp" %>
                </div>
                <div class="row">
                    <button id="saveButton" type="button" class="btn btn-primary">添加</button>
                </div>
            </div>
            <div class="col-md-6">
                <table id="allResource" data-toggle="table" >
                </table>
            </div>
        </div>
    </div>

<%--    <!-- Modal -->--%>
    <div id="dialog">
    </div>


<%--    <form id="resouceformid" method="post"--%>
<%--          action="/Module_SpringMVC_from_maven_war_exploded/resource/saveResource">--%>
<%--        <input name="title" type="text" />--%>
<%--        <input type="submit">--%>
<%--    </form>--%>

    <!-- Button trigger modal -->
<%--        <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModalnew">--%>
<%--            Launch demo modal--%>
<%--        </button>--%>

    <!-- Modal -->
<%--    <div class="modal fade" id="myModalnew" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
<%--        <div class="modal-dialog" role="document">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <button id="closeicon" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
<%--                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                    ...--%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button id="closebutton" type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
<%--                    <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

    <!-- Single button -->
    <div class="btn-group">
        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Action <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a href="#">Action</a></li>
            <li><a href="#">Another action</a></li>
            <li><a href="#">Something else here</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="#">Separated link</a></li>
        </ul>
    </div>




    <script type="text/javascript">

    function refresh() {
        $('#allResource').bootstrapTable('refresh');
    }

    function openResourceDetailDialog(resourceId) {

        //2.jquery的ajax的load,如果data是object,那么用post发送
        //如果data是string,那么用get发送
        //如果是json对象用post发送，在controller中的接收方法，好像不能forward到html文件，

        //如果data是object,那么用post发送
        // $("#myModal").load("/Module_SpringMVC_from_maven_war_exploded/resource/getResource", { "resourceId" : resourceId } , function () {
        //     $("#myModal").modal('show');
        // })

        //如果data是string,那么用get发送
        // $("#myModal").load("/Module_SpringMVC_from_maven_war_exploded/resource/getResource", "resourceId="+resourceId , function () {
        //     $("#myModal").modal('show');
        // })


            $("#dialog").load("/Module_SpringMVC_from_maven_war_exploded/resource/getResource", { "resourceId" : resourceId } , function () {
                $("#myModal").modal('show');
            });

    }


    (function() {

        function doSave() {
                var params = {
                    title : $("#title").val(),
                    content : $("#content").val(),
                    link : $("#link").val(),
                    password : $("#password").val()
                };

                $.ajax({
                    url : '/Module_SpringMVC_from_maven_war_exploded/resource/saveResource',
                    type : 'post',
                    data : params,
                    dataType : 'json',
                    success : function(data) {
                        refresh();
                        //alert("添加成功", "", "success");
                    },
                    error : function() {
                        alert("添加资源错误", "请重新操作", "error");
                    }
                });
        }

        $('#saveButton').on("click", doSave );

        // $('#modalbutton').on('click', function(){
        //     //1.直接调用html文件
        //     // $("#myModal").load("/Module_SpringMVC_from_maven_war_exploded/html/modal.html", function () {
        //     //     $("#myModal").modal.html('show');
        //     // })
        //
        //     //2.通过调用Controller返回html文件
        //     // $("#myModal").load("/Module_SpringMVC_from_maven_war_exploded/test/getModal", function () {
        //     //     $("#myModal").modal();
        //     // })
        //
        //     //3.has issue on close dialog
        //     // $("#dialog").load("/Module_SpringMVC_from_maven_war_exploded/test/getModal", function () {
        //     //     $("#myModal").modal('show');
        //     // });
        // });

        //传参数到后台
        function queryParams(params) {
            return {
                //每页多少条数据
                pageSize : params.limit,
                page : (params.offset) / params.limit + 1,
                titleSearch : $("#titleSearch").val(),
                idSearch : $("#idSearch").val()
            };
        }

        $('#allResource').bootstrapTable({
            method : 'post',
            url : "http://localhost:8080/Module_SpringMVC_from_maven_war_exploded/test/getBlogResource",
            dataType : "json",
            striped : false, //使表格带有条纹
            cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination : true, //在表格底部显示分页工具栏
            pageSize : 10,
            pageNumber : 1,
            sortOrder : "asc",
            sortable : true,
            pageList : [ 10, 20, 30 ],
            idField : "id", //标识哪个字段为id主键
            showToggle : false, //名片格式
            cardView : false, //设置为True时显示名片（card）布局
            showColumns : false, //显示隐藏列
            showRefresh : false, //显示刷新按钮
            search : false, //是否显示搜索框
            searchOnEnterKey : true, //设置为 true时，按回车触发搜索方法，否则自动触发搜索方法
            queryParams : queryParams, //参数
            sidePagination : "server", //服务端处理分页
            searchTimeOut : 500, //设置搜索超时时间
            toolbarAlign : 'left', //工具栏对齐方式
            buttonsAlign : 'right', //按钮对齐方式
            toolbar : '#toolbar', //指定工作栏
            searchAlign : 'right',
            contentType: 'application/json',
            //contentType : "application/x-www-form-urlencoded",
            formatLoadingMessage : function() {
                return "请稍等，正在加载中...";
            },
            formatNoMatches : function() { //没有匹配的结果
                return "无符合条件的记录";
            },
            responseHandler : function(res) {
                return {
                    "total" : res.total, //总页数
                    "rows" : res.list //数据
                };
            }
            ,
            columns : [
                {
                    title : '序号',
                    align : 'center',
                    valign : 'middle',
                    width : '5%',
                    formatter : function(value, row, index) {
                        //var index1 = index + 1;
                        var id = '<span title="ID:' + row.id + '">' + row.id + '</span>';
                        return id;
                    }
                },
                {
                    title : '名称',
                    field : 'title',
                    align : 'center',
                    width : '10%'
                    // ,
                    // cellStyle : formatTableUnit,
                    // formatter : operateOpinionFormatter
                },{
                    title : 'Action',
                    align : 'center',
                    valign : 'middle',
                    width : '5%',
                    formatter : function(value, row, index) {

                        var str = '<button type="button" class="btn btn-sm btn-primary" onclick="openResourceDetailDialog(' + row.id + ')">Edit</button>';
                        return str;
                    }
                }
            ]
        });

    })();

</script>



</body>
</html>