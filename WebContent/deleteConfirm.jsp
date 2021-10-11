<title>登録内容確認</title>
</head>

<body>
	<h1>登録内容確認　　　<span class="loginInfo">ログイン者名：${ sessionScope.LOGIN_EMP.nmEmployee }</span></h1>
	<form action="delete" method="post" >
		<table>
			<tr>
				<th class="header">社員ID</th>
				<td>
					<input type="text" name="empId" readonly="readonly" class="readOnly" value="${ empId }" />
				</td>
			</tr>
			<tr>
				<th class="header">社員名</th>
				<td>
					<input type="text" name="empNm" readonly="readonly" class="readOnly" value="${ empNm }"/>
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
				<th class="header">所属部署</th>
				<td>
					<select name="depId">
						<c:forEach items="${ sessionScope.DEP_LIST }" var="depList">
							<option value="${depList.idDepartment}" ${depList.idDepartment == depId ? 'selected="selected"' : 'disabled="disabled"'}><c:out value="${ depList.nmDepartment }" /></option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="btnArea">
					<input type="submit" value="削除" />
				</td>
			</tr>
		</table>
	</form>
	<a href="delete.jsp" >削除対象の変更</a>　|　<a href="menu.jsp" >メニューに戻る</a>
</body>
</html>