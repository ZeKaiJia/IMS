<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 2020/2/24
  Time: 10:26 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%> <!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql"%> <!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn"%> <!--常用函数标签库-->
<%@ page isELIgnored="false"%> <!--支持EL表达式，不设的话，EL表达式不会解析-->
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../css/login_css.css">
    <title>综合信息管理系统</title>
    <script>
        function validate() {
            if (document.getElementById("usrId").value === "") {
                alert("用户名不能为空");
                document.getElementById("usrId").focus();
                return false;
            } else if (document.getElementById("usrPassword").value === "") {
                alert("密码不能为空");
                document.getElementById("usrPassword").focus();
                return false;
            }
            return true;
        }
    </script>
</head>

<body>
    <noscript>
        <strong>We're sorry but information management system doesn't work properly without JavaScript enabled. Please
            enable it to continue.
        </strong>
    </noscript>
    <div id="content">
        <div id="form">
            <h1>用户登录</h1><br>
            <form action="/login/login" method="post" id="myform" onsubmit="return validate()">
                用户ID<input type="text" id="usrId" name="usrId" style="width: 190px; height: 26px; margin-left: 39px;"><br>
                密码<input type="password" id="usrPassword" name="usrPassword" style="width: 190px; height: 26px; margin-top: 8px; margin-left: 54px;"><br>
                <input type="submit" value="登录" style="width: 50px; height: 30px; margin-top: 8px;">
                <a href="">注册</a>
            </form>
            <c:if test="${msg!=null}"><font color="red">${msg}</font></c:if>
<%--            <c:if test="${noticeMsg!=null}"><font color="green">${noticeMsg}</font></c:if>--%>
        </div>
    </div>
</body>
</html>
