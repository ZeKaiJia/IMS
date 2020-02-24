<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 2020/2/24
  Time: 10:26 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="icon" href="image/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/login_css.css">
    <title>综合信息管理系统</title>
</head>
<body>
<noscript>
    <strong>We're sorry but information management system doesn't work properly without JavaScript enabled. Please
        enable it to continue.
    </strong>
</noscript>
<script src="./iconfont.js"></script>
<div id="app">
    <div class="login">
        <form class="login-form">
            <div class="login-form-title">
                    <span style="vertical-align: inherit">
                        综合信息管理系统
                    </span>
            </div>
            <div class="login-form-info">
                <div class="login-form-info-content" style="margin-left: 0">
                    <div class="form-input">
                        <label>
                            <input type="text" autocomplete="off" placeholder="工号" class="form-input-inner">
                        </label>
                    </div>
                    <div class="input-error">
                            <span style="vertical-align: inherit">
                                请输入工号
                            </span>
                    </div>
                </div>
            </div>
            <div class="login-form-info">
                <div class="login-form-info-content" style="margin-left: 0">
                    <div class="form-input">
                        <label>
                            <input type="password" autocomplete="off" placeholder="密码" class="form-input-inner">
                        </label>
                    </div>
                    <div class="input-error">
                            <span style="vertical-align: inherit">
                                请输入密码
                            </span>
                    </div>
                </div>
            </div>
            <div class="login-form-code">
                <div class="login-form-info-content" style="margin-left: 0">
                    <div class="form-input">
                        <label>
                            <input type="text" autocomplete="off" placeholder="验证码" class="form-input-inner">
                        </label>
                    </div>
                    <img src="">
                </div>
            </div>
            <label>
                <select>
                    <option value="volvo">学生</option>
                    <option value="saab">教师</option>
                    <option value="opel">管理员</option>
                </select>
            </label>
            <div class="login-form-submit"></div>
        </form>
    </div>
</div>
</body>
</html>
