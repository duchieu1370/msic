package com.devpro.shop16.controller.khachhang;
import com.devpro.shop16.entities.Contact;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.devpro.shop16.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	// định nghĩa action phải sử dụng "@RequestMapping"
	/*
	 * model : sử dụng để đẩy dữ liệu từ controller -> view request :thông tin người
	 * dùng đẩy lên controller response
	 */
	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public String contact(final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws IOException {
		// tạo 1 đối tượng contact
		Contact contact = new Contact();

		// đẩy contact xuống tầng view từ Controller
		model.addAttribute("contact", contact);
		
		

		// trả đường dẫn về view
		return "khachhang/contact";
	}

//	@RequestMapping(value = { "/contact" }, method = RequestMethod.POST)
//	public String post_simple(final Model model, final HttpServletRequest request, final HttpServletResponse response)
//			throws IOException {
//		//Lấy theo name attribute trong thẻ html
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
//		String message = request.getParameter("message");
//		
//		model.addAttribute("TB", "Cảm ơn " + name + ", chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất!");
//		
//		// trả đường dẫn về view
//		return "khachhang/contact";
//	}

	@RequestMapping(value = { "/contact" }, method = RequestMethod.POST)
	public String post_spring_form(final Model model, 
								   final HttpServletRequest request, 
								   final HttpServletResponse response,
								   final @ModelAttribute("contact") Contact contact) throws IllegalStateException, IOException{
		model.addAttribute("TB", "Cảm ơn " + contact.getName() + ", chúng tôi sẽ liên hệ với bạn trong thời gian sớm nhất!");

		// lưu ảnh lên server
//		attachment.transferTo(new File("C:/upload/" + attachment.getOriginalFilename()));
		// lưu đường dẫn ảnh vào contact
//		contact.setAttachment("C:/upload/" + attachment.getOriginalFilename());
		contactService.saveOrUpdate(contact);
		// trả về đường dẫn tới view
		return "khachhang/contact";
	
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
	@RequestMapping(value = { "/ajax/contact" }, method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> ajax_contact(final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final @RequestBody Contact contact) {
		Map<String, Object> jsonResult = new HashMap<String, Object>();
		jsonResult.put("code", 200);
		jsonResult.put("message", "Cảm ơn bạn " + contact.getEmail() + ", Chúng tôi sẽ liên hệ sớm nhất!");
		return ResponseEntity.ok(jsonResult);
	}
}
