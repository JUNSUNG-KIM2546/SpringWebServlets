<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test</title>
<body>
	<h1>${s}</h1>
	<h2>"TEST 할꼬얌 vo.x : {${vox}}, vo.y : {${voy}}"</h2> mod.addAttribute("vox",vo.getX()); mod.addAttribute("voy",vo.getY());
	<h2>"TEST 할꼬얌 vo.x : {${mv.x}}, vo.y : {${mv.y}}"</h2> @ModelAttribute(value = "mv") MyVo vo
	<h2>"TEST 할꼬얌 v.x : {${myVo.x}}, v.y : {${myVo.y}}"</h2> MyVo v
</body>
</html>