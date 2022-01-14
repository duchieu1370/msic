<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- SPRING FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://kit.fontawesome.com/64d58efce2.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="${base}/css/login.css">
<title>Sign in & Sign up Form</title>
</head>
<body>
	<div class="container">
		<div class="forms-container">
			<div class="signin-signup">
				<form method="POST" action="${base }/perform_login" class="sign-in-form">
					
					<!-- Kiểm tra xem đã login thành công hay không qua biến login_error -->
					<c:if test="${not empty param.login_error }">
					    <div class="alert alert-danger" role="alert">
					         Login attempt was not successful, try again!!!
					    </div>					  
					</c:if>					
					
					<h2 class="title">Sign in</h2>										
					
					<div class="input-field">
						<i class="fas fa-user"></i>
						<!-- bắt buộc name phải đẻ là "username" -->
						 <input type="text" name="username"
							placeholder="Username" />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i>
						<!-- bắt buộc name phải đẻ là "password" -->
						 <input name="password" type="password"
							placeholder="Password" />
					</div>
					<input type="submit" value="Login" class="btn solid" />
					<p class="social-text">Or Sign in with social platforms</p>
					<div class="social-media">
						<a href="#" class="social-icon"> <i class="fab fa-facebook-f"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-twitter"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-google"></i>
						</a> <a href="#" class="social-icon"> <i
							class="fab fa-linkedin-in"></i>
						</a>
					</div>
				</form>
				
				
				<sf:form action="${base }/login"
					method="POST" enctype="multipart/form-data" modelAttribute="user" class="sign-up-form">
					<h2 class="title">Sign up</h2>
					<div class="input-field">
						<i class="fas fa-user"></i> <sf:input path="username" type="text"
						name="username"
							placeholder="Username" />
					</div>
					<div class="input-field">
						<i class="fas fa-envelope"></i> <sf:input path="email" type="email"
							placeholder="Email" name="email" />
					</div>
					<div class="input-field">
						<i class="fas fa-lock"></i> <sf:input path="password" type="password"
							placeholder="Password" name="password" />
					</div>
					<div class="input-field">
						 <i class="fas fa-map-marker-alt"></i><sf:input type="address" path="address"
							placeholder="Address" name="address" />
					</div>
					<input type="submit" class="btn" value="Sign up" />
					<p class="social-text">Or Sign up with social platforms</p>
					<div class="social-media">
						<a href="#" class="social-icon"> <i class="fab fa-facebook-f"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-twitter"></i>
						</a> <a href="#" class="social-icon"> <i class="fab fa-google"></i>
						</a> <a href="#" class="social-icon"> <i
							class="fab fa-linkedin-in"></i>
						</a>
					</div>
				</sf:form>
			</div>
		</div>

		<div class="panels-container">
			<div class="panel left-panel">
				<div class="content">
					<h3>New here ?</h3>
					<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
						Debitis, ex ratione. Aliquid!</p>
					<button class="btn transparent" id="sign-up-btn">Sign up</button>
				</div>
				
			</div>
			<div class="panel right-panel">
				<div class="content">
					<h3>One of us ?</h3>
					<p>Lorem ipsum dolor sit amet consectetur adipisicing elit.
						Nostrum laboriosam ad deleniti.</p>
					<button class="btn transparent" id="sign-in-btn">Sign in</button>
				</div>
				
			</div>
		</div>
	</div>

	<script type="text/javascript"
	src="${base}/js/login.js"></script>
</body>
</html>
