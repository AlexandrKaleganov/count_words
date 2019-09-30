$(document).ready(function () {
        listuser();
});

// /**
//  * скрипты для отрисовывания таблицы userlist
//  */
// function listuser() {
//     $.ajax({
//         type: "POST",
//         url: "./user/api/user",
//         data: {action: "getListUser", us: "{\"login\":\"login\"" + "}"},
//         dataType: "json",
//         success: function (data) {
//             console.log(data);
//             for (var i = 0; i < data.length; i++) {
//                 $("#todolist_table tbody:last").append(loadtable(data[i]));
//             }
//         }
//     });
// }

/**
 * скрипты для отрисовывания таблицы userlist
 */
function listuser() {
    console.log("всё хорошо");
        var userList = req.getParameter("users");
        console.log("log");
        console.log(userList.toString());
            for (var i = 0; i < userList.length; i++) {
                $("#todolist_table tbody:last").append(loadtable(userList[i]));
            }
}

function loadtable(u) {
    var rsl = "";
    rsl = rsl + "<tr><td>" + u.id + "</td><td>" + u.name + "</td><td>" + u.login + "</td><td>" + u.roles.name + "</td><td>";
    if ($("#rol").val() === "ADMIN") {
        rsl = rsl + stringButton(u.id);

    } else if ($("#log").val() === u.login){
        rsl = rsl + stringButton(u.id);
    } else {
        rsl = rsl + "</td><td></td></tr>"
    }
    return rsl;
}

function stringButton(id) {
    var rsl = " <form action=\"${pageContext.servletContext.contextPath}/listUser\" method=\"post\">\n" +
        "                            <input type=\"hidden\" name=\"us\" value=\"" + id + "\">\n" +
        "                            <input type=\"hidden\" name=\"action\" value=\"findByIdUser\">\n" +
        "                            <input type=\"submit\" value=\"EDIT\">\n" +
        "                        </form>" + "</td><td>" +
        "                        <form action=\"${pageContext.servletContext.contextPath}/listUser\" method=\"post\">\n" +
        "                            <input type=\"hidden\" name=\"us\" value=\"" + id + "\">\n" +
        "                            <input type=\"hidden\" name=\"action\" value=\"deleteUser\">\n" +
        "                            <input type=\"submit\" value=\"DELETE\">\n" +
        "                       </form>" + "" +
        "</td></tr>";
    return rsl;
}
function jsonId(id) {
    var rsl = "{\"id\":\"" + id + "\"}";
    return rsl;
}
