<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!--import JSTL-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- import SPRING-FORM -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Fashion Msic</title>

<jsp:include page="/WEB-INF/views/khachhang/layouts/css.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${base}/css/contact.css">
</head>
<body>
	<main class="container">
		<div class="free">Miễn phí vận chuyển với đơn hàng trên 1000k</div>

		<jsp:include page="/WEB-INF/views/khachhang/layouts/header.jsp"></jsp:include>

		<%--open content --%>
		<section>
			<c:if test="${not empty TB }">
				<div class="alert alert-primary" id="status" role="alert">${TB }</div>
			</c:if>
			<div class="container-content">
				<sf:form modelAttribute="contact" action="${base }/contact"
					method="POST" id="my-form" enctype="multipart/form-data">

					<div class="form-group">
						<label for="name"> Name</label>
						<sf:input path="name" type="text" id="name" name="name" />
					</div>

					<div class="form-group">
						<label for="email">Email</label>
						<sf:input path="email" type="email" id="email" name="email"
							 />
						<span id="text"
							style="display: block; color: #000; font-size: italic; font-weight: 300; padding: 5px;"></span>
					</div>

					<div class="form-group">
						<label for="massage">Massage</label>
						<sf:textarea path="massage" name="massage" id="massage" cols="30"
							rows="10" />
					</div>

					<button type="submit">Submit</button>
				</sf:form>
			</div>

		</section>


		<%--close content --%>
		<jsp:include page="/WEB-INF/views/khachhang/layouts/footer.jsp"></jsp:include>
		<div class="copyright">
			Copyright <i class="far fa-copyright"></i> <a href="#">msic.</a> <a
				href="#">Powered by Haravan</a>
		</div>
	</main>

</body>
<jsp:include page="/WEB-INF/views/khachhang/layouts/js.jsp"></jsp:include>
<script type="text/javascript">
    function validation(){
    	var my-form = document.getElementById("my-form"); 
    	var email = document.getElementById("email").value;
    	var text = document.getElementById("text");
    	var pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    	if (email.match(pattern))
    		{
    		my-form.classList.add("valid");
    		my-form.classList.remove("invalid");
    		text.innerHTML = "Your email address in valid.";
    		text.style.color = "#00ff00";
    		}
    	else{
    		my-form.classList.remove("valid");
    		my-form.classList.add("invalid");
    		text.innerHTML = "Please enter valid email address .";
    		text.style.color = "#ff0000";
    	}
    	
    	if (email == "")
		{
		my-form.classList.remove("valid");
		my-form.classList.remove("invalid");
		text.innerHTML = "";
		text.style.color = "#00ff00";
		}
    }
</script>
</html>