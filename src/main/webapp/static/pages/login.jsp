<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 2020/2/24
  Time: 10:26 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt" %>
<!--数据格式化标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="sql" %>
<!--数据库相关标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fn" %>
<!--常用函数标签库-->
<%@ page isELIgnored="false" %>
<!--支持EL表达式，不设的话，EL表达式不会解析-->
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>综合信息管理系统</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/login.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/font/font/iconfont.css">
    <link rel="icon" href="<%=path%>/static/image/favicon.ico" type="images/x-ico"/>
</head>

<body>
<noscript>
    <strong>We're sorry but information management system doesn't work properly without JavaScript enabled. Please
        enable it to continue.
    </strong>
</noscript>
<div class="outside">
    <div class="headFont">综合信息管理系统</div>
    <div class="login-container">
        <form action="<%=path%>/login/login" method="post" id="login-form" onsubmit="return login_submit()">
            <div class="left-container">
                <div class="title">
                    <span>用户登录</span>
                </div>
                <div class="input-container">
                    <input type="text" id="usrId" name="usrId" placeholder="ID" onblur="check_login_id()">
                    <span id="usrIdReg"></span>
                    <input type="text" id="usrPassword" name="usrPassword" placeholder="密码"
                           onblur="check_login_password()">
                    <span id="usrPasswordReg"></span><br>
                    <c:if test="${msg!=null}"><font color="red">${msg}</font></c:if>
                    <%--                <input type="text" id="id" placeholder="身份证" onblur="idBlur()">--%>
                    <%--                <span id="idReg"></span>--%>
                    <%--                <input type="text" id="phone" placeholder="手机号码" onblur="phoneBlur()">--%>
                    <%--                <span id="phoneReg"></span>--%>
                    <%--                <input type="text" id="address" placeholder="地址" onblur="addressBlur()">--%>
                    <%--                <span id="addressReg"></span>--%>
                </div>
            </div>
            <div class="right-container">
                <div class="regist-container">
                    <span class="regist">信息卡</span>
                </div>
                <div class="action-container">
                    <input type="submit" id="sub" value="提交">
                </div>
            </div>
        </form>
    </div>
</div>

<%--    <div id="content">--%>
<%--        <div id="form">--%>
<%--            <h1>用户登录</h1><br>--%>
<%--            <form action="/login/login" method="post" id="myform" onsubmit="return validate()">--%>
<%--                用户ID<input type="text" id="usrId" name="usrId" style="width: 190px; height: 26px; margin-left: 39px;"><br>--%>
<%--                密码<input type="password" id="usrPassword" name="usrPassword" style="width: 190px; height: 26px; margin-top: 8px; margin-left: 54px;"><br>--%>
<%--                <input type="submit" value="登录" style="width: 50px; height: 30px; margin-top: 8px;">--%>
<%--                <a href="">注册</a>--%>
<%--            </form>--%>
<%--            <c:if test="${msg!=null}"><font color="red">${msg}</font></c:if>--%>
<%--            &lt;%&ndash;<c:if test="${noticeMsg!=null}"><font color="green">${noticeMsg}</font></c:if>&ndash;%&gt;--%>
<%--        </div>--%>
<%--    </div>--%>
<script>
    let loginFlag = false;

    function getText(inputId, reg) {
        const text = document.getElementById(inputId).value;
        const result = reg.test(text.trim());
        const show = document.getElementById(inputId + "Reg");

        if (result && text !== "") {
            show.innerText = "√";
            show.style.color = "green";
            loginFlag = false;
        } else {
            show.innerText = "X";
            show.style.color = "red";
            loginFlag = true;
        }
    }

    function login_submit() {
        if (loginFlag) {
            alert('信息输入错误，请重新输入后提交');
            return false;
        } else {
            return true;
        }
    }

    function check_login_id() {
        getText("usrId", /\w{1,12}$/);
    }

    function check_login_password() {
        getText("usrPassword", /\w{1,12}$/);
    }

    // function idBlur() {
    //     getText("id", /^[1-9]{1}\d{17}$|^[1-9]{1}\d{16}(X|x)$/);
    // }
    //
    // function phoneBlur() {
    //     getText("phone", /^1[3-9]{1}\d{9}$/);
    // }
    //
    // function addressBlur() {
    //     getText("address", /^[\u4e00-\u9fa5][0-9A-z\u4e00-\u9fa5]+$/);
    // }
</script>
</body>
</html>
