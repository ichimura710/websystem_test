<title>登録内容確認</title>
</head>

<body>
	<h1>登録内容確認　　　<span class="loginInfo">ログイン者名：${ sessionScope.LOGIN_EMP.nmEmployee }</span></h1>
	<form action="insert" method="post" id="form">
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
					<input type="text" name="empNm" readonly="readonly" class="readOnly" value="${ empNm }" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名フリガナ</th>
				<td>
					<input type="text" name="empKn" readonly="readonly" class="readOnly" value="${ empKn }" />
				</td>
			</tr>
			<tr>
				<th class="header">メールアドレス</th>
				<td>
					<input type="text" name="mail" readonly="readonly" class="readOnly" value="${ mail }" />
				</td>
			</tr>
			<tr>
				<th class="header">パスワード確認</th>
				<td>
					<input type="hidden" name="pass" value="${ pass }" />
					<input type="password" name="passConfirm" placeholder="再入力してください" />
				</td>
			</tr>
			<tr>
				<th class="header">所属部署</th>
				<td>
					<select name="depId" class="readOnly">
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option  class="readOnly" value="${depList.idDepartment}" ${depList.idDepartment == depId ? 'selected="selected"' : 'disabled="disabled"'}><c:out value="${ depList.nmDepartment }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="btnArea">
					<input type="submit" value="登録" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="flg" id="flg" value="0">
		</form>
	<a href="javascript:test()">登録内容の編集</a>　|　<a href="menu.jsp" >メニューに戻る</a>
	<script>
		function test(){
			document.getElementById("flg").value = "1";
			document.getElementById("form").submit();
		}
	</script>
</body>
</html>