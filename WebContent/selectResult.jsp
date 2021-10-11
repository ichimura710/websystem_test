<title>検索結果表示</title>
</head>

<body>
	<h1>検索結果表示　　　<span class="loginInfo">ログイン者名：${ sessionScope.LOGIN_EMP.nmEmployee }</span></h1>
	<table>
		<thead>
			<tr>
				<th style="width: 60px;">社員ID</th>
				<th style="width: 100px;">社員名</th>
				<th style="width: 150px;">フリガナ</th>
				<th style="width: 150px;">メールアドレス</th>
				<th style="width: 60px;">パスワード</th>
				<th style="width: 150px;">所属部署</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ empInfoList }" var="empInfo">
				<tr>
					<td><c:out value="${ empInfo.employee.idEmployee }" /></td>
					<td><c:out value="${ empInfo.employee.nmEmployee }" /></td>
					<td><c:out value="${ empInfo.employee.knEmployee }" /></td>
					<td><c:out value="${ empInfo.employee.mailAddress }" /></td>
					<td><c:out value="${ empInfo.employee.password }" /></td>
					<td><c:out value="${ empInfo.nmDepartment }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<a href="select.jsp">検索条件入力に戻る</a>　|　<a href="menu.jsp">メニューに戻る</a>
</body>
</html>