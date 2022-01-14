
// Cập nhật số lượng sản phẩm trong giỏ hàng
function UpdateQuanlityCart(baseUrl, productId) {
	// javascript object.  data la du lieu ma day len action cua controller
	let data = {
		productId: productId, // lay theo id
		
	};
	
	// $ === jQuery
	// json == javascript object
	jQuery.ajax({
		url: baseUrl + "/ajax/updateQuanlityCart", //->action
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(data),

		dataType: "json", // kieu du lieu tra ve tu controller la json
		success: function(jsonResult) {
			// tăng số lượng sản phẩm trong giỏ hàng trong icon
			$( "#quanlity_" +  productId).html(jsonResult.currentProductQuality);
			
			
		},
		error: function(jqXhr, textStatus, errorMessage) {
			
		}
	});
}




// Thêm sản phẩm vào trong giỏ hàng
function AddToCart(baseUrl, productId, quanlity) {
	// javascript object.  data la du lieu ma day len action cua controller
	let data = {
		productId: productId, // lay theo id
		quanlity: quanlity, // lay theo id
	};
	
	// $ === jQuery
	// json == javascript object
	jQuery.ajax({
		url: baseUrl + "/ajax/addToCart", //->action
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(data),

		dataType: "json", // kieu du lieu tra ve tu controller la json
		success: function(jsonResult) {
			// tăng số lượng sản phẩm trong giỏ hàng trong icon
			$( "#iconShowTotalItemsInCart" ).html(jsonResult.totalItems);
			$([document.documentElement, document.body]).animate({
			    scrollTop: $("#iconShowTotalItemsInCart").offset().top
			}, 2000);
		},
		error: function(jqXhr, textStatus, errorMessage) {
			
		}
	});
}

add = function(baseUrl) {
	// tạo javascript object.  
	// data là dữ liệu cùng kiểu với RequestMapping
	let data = {
		maSP: jQuery("#validationCustom01").val(), // lay theo id
		tenSP: jQuery("#validationCustom02").val(), // lay theo id
		loaiSP: jQuery("#validationCustom03").val(),
		total: jQuery("#validationCustom04").val(),
	};

	// $ === jQuery
	// json == javascript object
	jQuery.ajax({
		url: baseUrl + "/ajax/add-product", //->action
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(data),

		dataType: "json", // kieu du lieu tra ve tu controller la json vì controlelr action trả về kiểu Map
		success: function(jsonResult) {
			/*alert(JSON.stringify(jsonResult));
			alert(jsonResult.message);*/
			jQuery("#TB_AJAX").html(jsonResult.message);
		},
		error: function(jqXhr, textStatus, errorMessage) {

		}
	});
}

home = function(baseUrl) {
	// tạo javascript object.  
	// data là dữ liệu cùng kiểu với RequestMapping
	let data = {
		email: jQuery("#email").val(), // lay theo id

	};

	// $ === jQuery
	// json == javascript object

	jQuery.ajax({
		url: baseUrl + "/ajax/home", //->action
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(data),

		dataType: "json", // kieu du lieu tra ve tu controller la json vì controlelr action trả về kiểu Map
		success: function(jsonResult) {
			/*alert(JSON.stringify(jsonResult));
			alert(jsonResult.message);*/
			if (ValidateEmail(data.email)) {
				document.getElementById('email').reset();
				jQuery("#TB_AJAX").html(jsonResult.message);
			} else {
				jQuery("#TB_AJAX").html(jsonResult.err);
			}
			
		},
		error: function(jqXhr, textStatus, errorMessage) {
			
		}
	});

}

function ValidateEmail(email) {
	const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	return mailformat.test(String(email).toLowerCase());

}


