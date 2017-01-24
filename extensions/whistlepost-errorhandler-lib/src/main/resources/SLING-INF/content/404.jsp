<!DOCTYPE html>
<%@page session="false"%>
<%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0"%>
<% response.setStatus(404); %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Page Not Found (404) - Whistlepost</title>

    <!-- Bootstrap core CSS -->
    <link href="/libs/wp-bootstrap/bower_components/bootstrap-css/bootstrap.min.css" rel="stylesheet">

    <link href="/apps/wp/css/style.css" rel="stylesheet">
    <link href="/apps/wp/css/grid.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="starter-template.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Sling -->
    <script src="/system/sling.js"></script>
</head>

<body>

<%-- sling.include("header.html/" + currentNode.name); --%>

<div class="container">

    <div class="starter-template wp-logo-bg">
        <h1>Requested Page Not Found</h1>
        <a href="javascript:history.go(-1);">Return to previous page</a>
    </div>

</div><!-- /.container -->

<%-- sling.include("footer.html"); --%>
</body>
</html>
