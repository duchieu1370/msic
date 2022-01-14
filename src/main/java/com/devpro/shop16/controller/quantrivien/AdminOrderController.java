package com.devpro.shop16.controller.quantrivien;


import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.devpro.shop16.controller.BaseController;
import com.devpro.shop16.dto.OrderSearchModel;
import com.devpro.shop16.service.SaleorderService;

@Controller
public class AdminOrderController extends BaseController{
	
	@Autowired
	private SaleorderService saleorderService;
	
	@RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
	public String adminOrder(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		
		OrderSearchModel searchModel = new OrderSearchModel();
		searchModel.keyword = request.getParameter("keyword");
		searchModel.setPage(getCurrentPage(request));
		
		model.addAttribute("orderWithPaging", saleorderService.search(searchModel));
		model.addAttribute("searchModel", searchModel);
		
		
		return "quantrivien/order";
	}
	
	
	@RequestMapping(value = { "/admin/order-product" }, method = RequestMethod.GET)
	public String adminOrderProduct(final Model model, final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		
		

		
		
		return "quantrivien/order-product";
	}

}
