

<title>社員登録</title>
</head>

<body>
	<h1>社員登録　　　<span class="loginInfo">ログイン者名：${ sessionScope.LOGIN_EMP.nmEmployee }</span></h1>
	<form action="inputCheck" method="post" >

	    <c:if test="${errMsg != null}">
            <div class="msgBox error">
                <c:forEach items="${ errMsg }" var="errList">
                    <c:out value="${errList}" />
                </c:forEach>
            </div>
        </c:if>
		<table>
			<tr>
				<th class="header">社員ID</th>
				<td>
					<input type="text" name="empId" readonly="readonly" class="readOnly" value="自動で設定されます" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名</th>
				<td>
					<input type="text" name="empNm" placeholder="社員名" required="required" value="${ empNm }" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名フリガナ</th>
				<td>
					<input type="text" name="empKn" placeholder="フリガナ" required="required" value="${ empKn }" />
				</td>
			</tr>
			<tr>
				<th class="header">メールアドレス</th>
				<td>
					<input type="text" name="mail" placeholder="メールアドレス" required="required" value="${ mail }" />
				</td>
			</tr>
			<tr>
				<th class="header">パスワード</th>
				<td>
					<input type="password" name="pass" placeholder="パスワード ※3文字以上" required="required" value="${ pass }" />
				</td>
			</tr>
			<tr>
				<th class="header">所属部署</th>
				<td>
					<select name="depId">
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option value="${depList.idDepartment}" ${depList.idDepartment == depId ? 'selected="selected"' : ''}><c:out value="${ depList.nmDepartment }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="btnArea">
					<input type="submit" value="登録確認" />
				</td>
			</tr>
		</table>
	</form>
	<a href="menu.jsp" >メニューに戻る</a>
</body>
</html>