<title>ログイン</title>
</head>

<body>
    <h1>ログイン</h1>
    <form method="post" action="login">
        <c:if test="${errMsg != null}">
            <div class="msgBox error">
                <c:forEach items="${ errMsg }" var="errList">
                    <c:out value="${errList}" />
                </c:forEach>
            </div>
        </c:if>
        <table>
            <tr>
                <th class="header">メールアドレス</th>
                <td style="width: 250px;">
                    <input type="text" placeholder="メールアドレス" name="mail" required="required"
                        style="width: 100%;" value="${mail }"/>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">パスワード</th>
                <td>
                    <input type="password" placeholder="パスワード" name="pass" required="required"
                        style="width: 100%;" value="${pass }" />
                </td>
            </tr>
            <tr>
                <td colspan="2" class="btnArea">
                    <input type="reset" value="リセット" />
                    <input type="submit" value="ログイン"  />
                </td>
            </tr>
        </table>
    </form>
</body>

</html>
