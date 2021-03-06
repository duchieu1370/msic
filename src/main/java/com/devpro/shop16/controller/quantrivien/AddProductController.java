package com.devpro.shop16.controller.quantrivien;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devpro.shop16.controller.BaseController;
import com.devpro.shop16.dto.AddProduct;
import com.devpro.shop16.dto.Contact;
import com.devpro.shop16.dto.ProductSearchModel;
import com.devpro.shop16.entities.Categories;
import com.devpro.shop16.entities.Product;
import com.devpro.shop16.service.CategoriesService;
import com.devpro.shop16.service.ProductService;

@Controller
public class AddProductController extends BaseController{

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping(value = { "/admin/product/list","/admin/product" }, method = RequestMethod.GET)
	public String adminProductList(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		
		ProductSearchModel searchModel = new ProductSearchModel();
		searchModel.keyword = request.getParameter("keyword");
		searchModel.setPage(getCurrentPage(request));
		searchModel.categoryId = super.getInteger(request, "categoryId");
		
		model.addAttribute("productsWithPaging", productService.search(searchModel));
		model.addAttribute("searchModel", searchModel);
		
		return "quantrivien/product";
	}

	@RequestMapping(value = { "/admin/product/add-product" }, method = RequestMethod.GET)
	public String admin_add(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		// t???o 1 ?????i t?????ng
		Product add = new Product();

		// ?????y ?????i t?????ng xu???ng t???ng view t??? Controller
		//?????y danh s??ch categories xu???ng
//		model.addAttribute("categories", categoriesService.findAll());

		// th??m s???n ph???m m???i
		model.addAttribute("add", add);

		// tr??? ???????ng d???n v??? view
		return "quantrivien/add-product";
	}
	
	@RequestMapping(value = { "/admin/product/add-product/{productId}" }, method = RequestMethod.GET)
	public String adminProductEdit(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("productId") int productId) throws IOException {
		// ?????y danh s??ch categories xu???ng
//		model.addAttribute("categories", categoriesService.findAll());

		// l???y s???n ph???m trong db
		Product product = productService.getById(productId);

		// edit s???n ph???m
		model.addAttribute("add", product);

		//tr??? v??? t???ng view
		return "quantrivien/add-product";
	}
	
	@RequestMapping(value = { "/admin/product/delete/{productId}" }, method = RequestMethod.GET)
	public String adminProductDelete(final Model model, final HttpServletRequest request, RedirectAttributes notify,
			final HttpServletResponse response, @PathVariable("productId") int productId) throws IOException {


		// l???y s???n ph???m trong db
		Product product = productService.getById(productId);

		// delete s???n ph???m
		productService.remove(product);
		notify.addFlashAttribute("TB", product.getTitle() + " has been removed to database!");

		return "redirect:/admin/product";
	}


	@RequestMapping(value = { "/admin/product/add-product" }, method = RequestMethod.POST)
	public String admin_addpost_spring_form(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @ModelAttribute("add") Product product,
			@RequestParam("productAvatar") MultipartFile productAvatar, // h???ng file ?????y l??n
			@RequestParam("productPictures") MultipartFile[] productPictures) throws Exception { // h???ng file ?????y l??n)
																									// throws
																									// IllegalStateException,
																									// IOException{
//		model.addAttribute("TB", "C???m ??n , b???n ???? th??m " + product.getTenSP() + " th??nh c??ng!");
		
		        // c???n ki???m tra xem id c???a product
				// = null || = 0 th?? th??m m???i
				// ng?????c l???i l?? ch???nh s???a
				if (product.getId() == null || product.getId() <= 0) { // th??m m???i
					productService.add(product, productAvatar, productPictures);
				} else { // ch???nh s???a
					productService.update(product, productAvatar, productPictures);
				}

		// l??u ???nh l??n server
//		attachment.transferTo(new File("C:/upload/" + attachment.getOriginalFilename()));
		// l??u ???????ng d???n ???nh v??o add
//		add.setAttachment("C:/upload/" + attachment.getOriginalFilename());

		// sau khi x??? l?? xong th?? ????a add v??? tr???ng m???i
//		model.addAttribute("add", new Product());

		// tr??? v??? ???????ng d???n t???i view
		return "redirect:/admin/product";

	}

	/**
	 * S??? d???ng ajax
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param contactModel
	 * @return
	 */
	@RequestMapping(value = { "/ajax/add-product" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_contact(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody AddProduct add) {
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "C???m ??n, b???n ???? th??m " + add.getTenSP() + " th??nh c??ng!");
		return ResponseEntity.ok(jsonResult);
	}
	
	@RequestMapping(value = { "/admin/product/add-category" }, method = RequestMethod.GET)
	public String admin_add_category(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		// t???o 1 ?????i t?????ng
		Categories addCate = new Categories();

		// th??m s???n ph???m m???i
		model.addAttribute("addCate", addCate);

		// tr??? ???????ng d???n v??? view
		return "quantrivien/add-category";
	}
	


	@RequestMapping(value = { "/admin/product/add-category" }, method = RequestMethod.POST)
	public String admin_add_spring_form(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @ModelAttribute("addCate") Categories categories
			) throws Exception { 
		categoriesService.saveOrUpdate(categories);
		// tr??? v??? ???????ng d???n t???i view
		return "redirect:/admin/product";

	}

}
