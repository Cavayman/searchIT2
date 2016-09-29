<%--
  Created by IntelliJ IDEA.
  User: cavayman
  Date: 29.09.2016
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>

<title>Title</title>
<link rel="stylesheet" href="/resources/css/main.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
      crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>

<body>
<section class="top-main">
    <div class="wrapper col-xs-5 col-xs-offset-5">
        <div class="col-xs-12 ">
            <div class="logo">
                <h1><i class="fa fa-search" aria-hidden="true"></i> SearchIT</h1>
            </div>
        </div>
        <div class="button col-xs-12">
            <div>
                    <input id="file" type="file" name="file" size="50" accept=".txt"/>
                    <br />
                    <input class="btn btn-warning" id="upload" value="Upload File" readonly />

            </div>
        </div>
    </div>
</section>


<script src="https://use.fontawesome.com/d651e9a7a1.js"></script>
<script src="/resources/main.js"></script>
</body>
</html>
