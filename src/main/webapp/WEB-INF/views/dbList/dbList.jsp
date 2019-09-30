<%--
  Created by IntelliJ IDEA.
  User: Lis
  Date: 17 сен 19
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script charset="UTF-8" type="text/javascript">
        <%@include file="/WEB-INF/views/js/navibar.js" %>
        <%@include file="/WEB-INF/views/js/sortedTable.js" %>
        <%@include file="/WEB-INF/views/js/filterTable.js"%>
        <%@include file="/WEB-INF/views/dbList/db.js"%>
    </script>
    <title>Список баз данных</title>
</head>
<body id="body">
<div id="navipanel">
</div>
<div class="username" id="username" style="float: right;">
</div>
<div class="container">
    <p>Список баз данных: </p>
    <div>
        <form action="${pageContext.servletContext.contextPath}/dbList" method="post">
            <input type="hidden" name="an" value="0">
            <input type="hidden" name="action" value="findByIdAn">
            <input type="submit" value="Подать объявление">
        </form>
    </div>
    <br/>
    <div>
        <input class="form-control" id="myInput" type="text" placeholder="Search..">
        <table class="table table-striped" id="todolist_table">
            <thead class="thead-dark">
            <tr>
                <th onclick="sortTable(0, 'todolist_table')">Наименование БД &darr;</th>
                <th onclick="sortTable(1, 'todolist_table')">IP БД &darr; </th>
                <th onclick="sortTable(2, 'todolist_table')">Пароль &darr;</th>
                <th onclick="sortTable(3, 'todolist_table')">Марка &darr;</th>
                <th onclick="sortTable(4, 'todolist_table')">Модель &darr;</th>
                <th onclick="sortTable(5, 'todolist_table')">Год выпуска &darr;</th>
                <th onclick="sortTable(6, 'todolist_table')">Пользователь &darr;</th>
                <th onclick="sortTable(7, 'todolist_table')">Продано &darr;</th>
                <th>Просмотр</th>
            </tr>
            </thead>
            <tbody id="myTable"></tbody>
        </table>
    </div>
</div>
</body>
</html>