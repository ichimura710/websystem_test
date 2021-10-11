<title>メインメニュー</title>
</head>

<body>
	<h1>メインメニュー　　　<span class="loginInfo">ログイン者名：${ sessionScope.LOGIN_EMP.nmEmployee }</span></h1>
	<table>
		<!-- 検索列 -->
		<tr>
			<td><a href="select.jsp">検索</a></td>
		</tr>

		<!-- 登録列 -->
		<tr>
			<td><a href="insert.jsp">登録</a></td>
		</tr>

		<!-- 更新列 -->
		<tr>
			<td><a href="update.jsp">更新</a></td>
		</tr>

		<!-- 削除列 -->
		<tr>
			<td><a href="delete.jsp">削除</a></td>
		</tr>
	</table>
</body>

</html>