<%--
  Created by IntelliJ IDEA.
  User: davidzhang
  Date: 2020-05-31
  Time: 2:19 a.m.
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="http://www.itxst.com/package/bootstrap-table-1.14.1/jquery-3.3.1/jquery.js"></script>
    <link href="http://www.itxst.com/package/bootstrap-table-1.14.1/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet" />
    <link href="http://www.itxst.com/package/bootstrap-table-1.14.1/bootstrap-table-1.14.1/bootstrap-table.css" rel="stylesheet" />
    <script src="http://www.itxst.com/package/bootstrap-table-1.14.1/bootstrap-table-1.14.1/bootstrap-table.js"></script>
    <title>Bootstrap Table入门例子</title>
    <style>
        .table-demo {
            width: 80%;
            margin: 30px auto 0px auto;
        }
    </style>
</head>
<body>
<div class="table-demo">
    <table id="table" data-show-footer="true"></table>
</div>
<script>
    //设置需要显示的列
    var columns = [{
        field: 'Id',
        title: '编号'
    }, {
        field: 'ProductName',
        title: '产品名称'
    }, {
        field: 'StockNum',
        title: 'Item 库存',
        footerFormatter:stockNumFormatter

    }];

    //需要显示的数据
    var data = [{
        Id: 1,
        ProductName: '香蕉',
        StockNum: '100'
    }, {
        Id: 2,
        ProductName: '苹果',
        StockNum: '200'
    }];
    //bootstrap table初始化数据
    $('#table').bootstrapTable({
        columns: columns,
        data: data,
        showFooter:true , //显示隐藏头部

    });
    function stockNumFormatter(data) {
        return "总计："+data.length
    }
</script>
</body>
</html>