package Ecommerce.AdminController;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Ecommerce.Entity.PaymentType;
import Ecommerce.Service.PaymentTypeServiceImpl;
import Ecommerce.Validator.PaymentTypeValidator;

@Controller
public class PaymentTypeManagementController {
	@Autowired
	private PaymentTypeServiceImpl paymentTypeServiceImpl;
	
	@RequestMapping(value = { "quan-tri/danh-sach-phuong-thuc-thanh-toan", "quan-tri/danh-sach-phuong-thuc-thanh-toan/{message}" }, method = RequestMethod.GET)
	public ModelAndView PaymentTypeList(HttpSession httpSession, @PathVariable(required = false) String message) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/payment-type-list");
		modelAndView.addObject("paymentTypes", paymentTypeServiceImpl.GetPaymentTypes());
		if(message != null) {
			if(message.equals("delete-success"))
				modelAndView.addObject("state", "Xóa thành công");
			else if(message.equals("delete-failed"))
				modelAndView.addObject("state", "Xóa thất bại");
			else 
				modelAndView.addObject("state", "Không xác định được nội dung thông báo");
		}
		return modelAndView;
	}

	@RequestMapping(value = "quan-tri/chi-tiet-phuong-thuc-thanh-toan/{id}", method = RequestMethod.GET)
	public ModelAndView PaymentTypeDetail(HttpSession httpSession, @PathVariable BigDecimal id) {
		if (id.intValue() <= 0)
			return new ModelAndView("redirect:/quan-tri/danh-sach-phuong-thuc-thanh-toan");

		PaymentType paymentType = paymentTypeServiceImpl.GetPaymentType(id);
		if (paymentType == null)
			return new ModelAndView("redirect:/quan-tri/danh-sach-phuong-thuc-thanh-toan");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/payment-type-detail");
		modelAndView.addObject("paymentType", paymentType);
		return modelAndView;
	}

	@RequestMapping(value = {"quan-tri/tao-moi-phuong-thuc-thanh-toan", "quan-tri/tao-moi-phuong-thuc-thanh-toan/{message}"}, method = RequestMethod.GET)
	public ModelAndView CreatePaymentType(HttpSession httpSession, @PathVariable(required = false) String message) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/create-payment-type");
		modelAndView.addObject("paymentType", new PaymentType());
		if(message != null) {
			if(message.equals("add-success"))
				modelAndView.addObject("state", "Thêm thành công");
			else if(message.equals("add-failed"))
				modelAndView.addObject("state", "Thêm thất bại");
			else 
				modelAndView.addObject("state", "Không xác định được nội dung thông báo");
		}
		return modelAndView;
	}

	@RequestMapping(value = "quan-tri/tao-moi-phuong-thuc-thanh-toan", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView CreatePaymentType(HttpSession httpSession, @ModelAttribute("paymentType") PaymentType paymentType, 
			BindingResult bindingResult, PaymentTypeValidator paymentTypeValidator) {
		paymentTypeValidator.validate(paymentType, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("admin/create-payment-type");
			modelAndView.addObject("paymentType", paymentType);
			return modelAndView;
		}

		if(paymentTypeServiceImpl.CreatePaymentType(paymentType))
			return new ModelAndView("redirect:/quan-tri/tao-moi-phuong-thuc-thanh-toan/add-success");
		
		return new ModelAndView("redirect:/quan-tri/tao-moi-phuong-thuc-thanh-toan/add-failed");
	}

	@RequestMapping(value = {"quan-tri/chinh-sua-phuong-thuc-thanh-toan/{id}", "quan-tri/chinh-sua-phuong-thuc-thanh-toan/{id}/{message}"}, method = RequestMethod.GET)
	public ModelAndView UpdateCategory(HttpSession httpSession, @PathVariable BigDecimal id, @PathVariable(required = false) String message) {
		if (id.intValue() <= 0)
			return new ModelAndView("redirect:/quan-tri/danh-sach-phuong-thuc-thanh-toan");

		PaymentType paymentType = paymentTypeServiceImpl.GetPaymentType(id);
		if (paymentType == null)
			return new ModelAndView("redirect:/quan-tri/danh-sach-phuong-thuc-thanh-toan");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/update-payment-type");
		modelAndView.addObject("paymentType", paymentType);
		if(message != null) {
			if(message.equals("edit-success"))
				modelAndView.addObject("state", "Chỉnh sửa thành công");
			else if(message.equals("edit-failed"))
				modelAndView.addObject("state", "Chỉnh sửa thất bại");
			else 
				modelAndView.addObject("state", "Không xác định được nội dung thông báo");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "quan-tri/chinh-sua-phuong-thuc-thanh-toan/{id}", method = RequestMethod.POST, produces = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView UpdateCategory(HttpSession httpSession, @ModelAttribute("category") PaymentType paymentType, 
			BindingResult bindingResult, PaymentTypeValidator paymentTypeValidator) {	
		paymentTypeValidator.validate(paymentType, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("admin/update-payment-type");
			modelAndView.addObject("paymentType", paymentType);
			return modelAndView;
		}

		if(paymentTypeServiceImpl.UpdatePaymentType(paymentType))
			return new ModelAndView("redirect:/quan-tri/chinh-sua-phuong-thuc-thanh-toan/{id}/edit-success");
		
		return new ModelAndView("redirect:/quan-tri/chinh-sua-phuong-thuc-thanh-toan/{id}/edit-failed");
	}

	@RequestMapping(value = "quan-tri/xoa-phuong-thuc-thanh-toan", method = RequestMethod.POST)
	public ModelAndView DeleteCategory(HttpSession httpSession, @RequestParam(value = "id", required = true) BigDecimal id) {		
		if(id.intValue() <= 0)
			return new ModelAndView("redirect:/quan-tri/danh-sach-phuong-thuc-thanh-toan");
		
		if(paymentTypeServiceImpl.DeletePaymentType(id))
			return new ModelAndView("redirect:/quan-tri/danh-sach-phuong-thuc-thanh-toan/delete-success");
		
		return new ModelAndView("redirect:/quan-tri/danh-sach-phuong-thuc-thanh-toan/delete-failed");
	}
}