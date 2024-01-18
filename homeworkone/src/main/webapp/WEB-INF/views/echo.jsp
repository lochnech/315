<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Keegan Spell--%>
<html>
<head>
    <title>Echo Server</title>
</head>
<body>
<h1>Echo Server</h1>
<script>
    fetch("/echomessage")
        .then(response => response.text())
        .then(txt => alert(txt))
        .catch(err => console.error(err));
</script>
</body>
</html>
