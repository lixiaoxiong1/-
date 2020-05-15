package com.shop.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.shop.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.Utils.PageBean;
import com.shop.service.OrderService;


@Controller
public class orderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping("/toOrder")
	public String toOrder(HttpServletRequest request,Model model) throws Exception {
		Orders orders = new Orders();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(loginUser==null){
			model.addAttribute("message", "对不起您还没有登录");
			return "msg";
		}


        //生成订单号
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        Long now = System.currentTimeMillis();//一个13位的时间戳
        String paymentID =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);// 订单ID
		orders.setOid(paymentID);
        //		0表示没有付款、1表示已付款即将发货 2表示确认收货 3表示交易成功
		orders.setState(0);
		orders.setOrderTime(new Date());
		orders.setUid(loginUser.getUid());
		orders.setMoney(cart.getTotale());
        System.out.println("121212"+orders.toString());
		orderService.toOrder(orders);

		Map<Integer, CartItem> cartItems = cart.getCartItem();
		for (Entry<Integer, CartItem> entry : cartItems.entrySet()) {
			CartItem cartItem = entry.getValue();
			Orderitem orderitem = new Orderitem();
			orderitem.setProduct(cartItem.getProduct());
			orderitem.setCount(cartItem.getCount());
			orderitem.setPid(cartItem.getProduct().getPid());
			orderitem.setSubtotal(cartItem.getSubtotle());
			orderitem.setOid(paymentID);
			orders.getOiList().add(orderitem);
			orderService.toOrderItem(orderitem);
		}
		cart.clearCart();
		request.getSession().setAttribute("orders", orders);
        System.out.println("222222"+orders.toString());
		return "order";
	}

	// 为定单付款
	@RequestMapping("/payOrder")
	public String payOrder(Orders orders, @RequestParam String receiveInfo, @RequestParam String phoNum, @RequestParam String accepter) throws Exception {
		orders.setReceiveinfo(receiveInfo);
		System.out.println("333333"+orders);
		orders.setPhonum(phoNum);
		orders.setAccepter(accepter);
		orderService.payOrder(orders);
		return "redirect:myOrder.action?page=1";
	}
   //payOrderAganin
	@RequestMapping("/payOrderAganin")
	public String payOrderAganin(@RequestParam String oid,HttpServletRequest request){
		Orders noPayOrder = orderService.findOrderByOid(oid);
		request.getSession().setAttribute("orders", noPayOrder);
		return "order";
	}
	// 查询myOrder
	@RequestMapping("/myOrder")
	public String myOrder(@RequestParam int page, Model model,
			HttpServletRequest request) throws Exception {
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		PageBean<Orders> pageBean = orderService.findOrderByUidAndPage(page,loginUser.getUid());
		model.addAttribute("pageBean", pageBean);
		List<Orders> list=pageBean.getList();
		for (Orders o:list){
			System.out.println("1212121"+o);
		}
		return "orderList";
	}
	
	// 确认收货
		@RequestMapping("/updateState")
		public String updateState(@RequestParam String oid ) throws Exception {
			orderService.updateOrderStatus(oid, 3);
			return "redirect:myOrder.action?page=1";
		}
}
