<%--
  Created by IntelliJ IDEA.
  User: davidzhang
  Date: 2020-06-06
  Time: 2:42 a.m.
  To change this template use File | Settings | File Templates.
--%>

<!doctype html>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="utf-8">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->--%>
<%--    <title>Bootstrap 101 Template</title>--%>

<%--    <!-- Bootstrap -->--%>
<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">--%>

<%--    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->--%>
<%--    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->--%>
<%--    <!--[if lt IE 9]>--%>
<%--    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>--%>
<%--    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>
<%--    <![endif]-->--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Hello, world!</h1>--%>

<%--<!-- Button trigger modal -->--%>
<%--<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">--%>
<%--    Launch demo modal--%>
<%--</button>--%>

<%--<!-- Modal -->--%>
<%--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
<%--    <div class="modal-dialog" role="document">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
<%--                <h4 class="modal-title" id="myModalLabel">Modal title</h4>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                ...--%>
<%--            </div>--%>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
<%--                <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

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

</head>
<body>

<div id="dialog">


    <!-- Button trigger modal -->
<%--    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">--%>
<%--        Launch demo modal--%>
<%--    </button>--%>

    <button id="button1" type="button" class="btn btn-primary btn-lg"></button>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button id="closeicon" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button id="closebutton" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    $(function () {

        function closeModal() {
            $('#myModal').modal('hide');
        }

        $("#button1").on("click", function () {
            $('#myModal').modal('show');
        });

        $("#closebutton").on("click", closeModal );

        $("#closeicon").on("click", closeModal );

    })
</script>

</body>
</html>
