package com.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.po.Category;
import com.shop.po.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;


@Controller
public class indexController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	/**
	 *
	 * @param model
	 * @param request 通过request对象获取客户端请求信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public String index(Model model,HttpServletRequest request) throws Exception {
			//查询一级分类
			List<Category> cList = categoryService.findCategory();
			request.getSession().getServletContext().setAttribute("cList",cList);
//			model.addAttribute("cList", cList);
			//查询热门商品
		    List<Product> hList= productService.findHotProduct();

			model.addAttribute("hList", hList);
			//查询最新商品
			List<Product> nList = productService.findNewProduct();
			model.addAttribute("nList", nList);
			//滚动图片专用，最多显示3个商品
			List<Product> sList;
			if(hList.size()>3)
			{
				sList = new ArrayList<Product>();
				sList.add(hList.get(0));
				sList.add(hList.get(1));
				sList.add(hList.get(2));
				model.addAttribute("sList", sList);
			}
			else
			{
				model.addAttribute("sList", hList);
			}
			
		return "index";
	}
	
	@RequestMapping("/about")
	public String about(Model model,HttpServletRequest request) throws Exception {
		return "about";
	}
	
	@RequestMapping("/contact")
	public String contact(Model model,HttpServletRequest request) throws Exception {
		return "contact";
	}
	
	@RequestMapping("/delivery")
	public String delivery(Model model,HttpServletRequest request) throws Exception {
		return "delivery";
	}
	
	//
	@RequestMapping("/searchProduct")
	public String searchProduct(@RequestParam String condition,Model model,HttpServletRequest request) throws Exception {
			//查询一级分类
			List<Category> cList = categoryService.findCategory();
			request.getSession().getServletContext().setAttribute("cList",cList); 
			//查询商品
		    List<Product> hList= productService.findHotProduct();
			model.addAttribute("hList", hList);
			
			//搜索商品
			List<Product> srList = productService.searchProduct(condition);
			model.addAttribute("srList", srList);
			
			//滚动图片专用，最多显示3个商品
			List<Product> sList;
			if(hList.size()>3)
			{
				sList = new ArrayList<Product>();
				sList.add(hList.get(0));
				sList.add(hList.get(1));
				sList.add(hList.get(2));
				model.addAttribute("sList", sList);
			}
			else
			{
				model.addAttribute("sList", hList);
			}
			
		return "searchProduct";
	}
}
