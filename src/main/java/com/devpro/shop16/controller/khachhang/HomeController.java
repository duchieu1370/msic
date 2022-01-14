package com.devpro.shop16.controller.khachhang;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shop16.dto.AddProduct;
import com.devpro.shop16.dto.Home;
import com.devpro.shop16.dto.ProductSearchModel;
import com.devpro.shop16.entities.Product;
import com.devpro.shop16.entities.Subcribe;
import com.devpro.shop16.service.PagerData;
import com.devpro.shop16.service.ProductService;
import com.devpro.shop16.service.SubcribeService;

@Controller // tạo một BEAN
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private SubcribeService subcribeService;
	
	// định nghĩa action phải sử dụng "@RequestMapping"
	/*
	 * model : sử dụng để đẩy dữ liệu từ controller -> view request :thông tin người
	 * dùng đẩy lên controller response
	 */
	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public String home(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
//		String ho = request.getParameter("Ho");
//		response.getWriter().print("Chao mung " + ho + " den voi chung toi");
		// tạo 1 đối tượng
		Subcribe subcribe = new Subcribe();

		// đẩy đối tượng xuống tầng view từ Controller
		model.addAttribute("subcribe", subcribe);
		
		ProductSearchModel searchModel = new ProductSearchModel();
		searchModel.keyword = request.getParameter("keyword");
		
		model.addAttribute("productsWithPaging", productService.search(searchModel));
		model.addAttribute("searchModel", searchModel);
		return "khachhang/index";
	}
	
	/**
	 * Sử dụng ajax
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param contactModel
	 * @return
	 */
	@RequestMapping(value = { "/ajax/home" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_contact(final Model model,
			final HttpServletRequest request,
			final HttpServletResponse response, 
			final @RequestBody Subcribe subcribe) {
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Cảm ơn, " + subcribe.getEmail() + " đã đăng kí thành công!");
		jsonResult.put("err", "Bạn hãy nhập lại đúng định dạng email");
		
		subcribeService.saveOrUpdate(subcribe);
		
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/product/details/{productSeo}"}, method = RequestMethod.GET)
	public String productDetails(final Model model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("productSeo") String productSeo)
			throws IOException {
//		String ho = request.getParameter("Ho");
//		response.getWriter().print("Chao mung " + ho + " den voi chung toi");
		ProductSearchModel searchModel = new ProductSearchModel();
		searchModel.seo = productSeo;
		
		PagerData<Product> products = productService.search(searchModel);
		Product product = products.getData().get(0);
		
		// tạo 1 đối tượng
//		Subcribe subcribe = new Subcribe();

		// đẩy đối tượng xuống tầng view từ Controller
		model.addAttribute("product", product);
		return "khachhang/details";
	}
}
