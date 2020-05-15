package com.shop.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.Utils.PageBean;
import com.shop.po.Message;
import com.shop.po.User;
import com.shop.service.MessageService;


@Controller
public class messageController {
	@Autowired
	private MessageService messageService;

	@RequestMapping("/messages")
    public String messages(@RequestParam int page, Model model,
                              HttpServletRequest request) throws Exception {
        PageBean<Message> pageBean = messageService.findAllMessageByPage(page);
        model.addAttribute("pageBean", pageBean);
        return "messageList";
    }

    @RequestMapping("/saveMessage")
	public String saveMessage(@RequestParam String messageinfo,HttpServletRequest request,Model model) throws Exception {
		Message Message = new Message();
		 
		User loginUser = (User) request.getSession().getAttribute("loginUser");
		if(loginUser==null){
			model.addAttribute("message", "对不起您还没有登录");
			return "msg";
		}
 
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Message.setMessage(messageinfo);
		Message.setUid(loginUser.getUid());
		Message.setMessagedate(sdf.format(new Date()));

		messageService.insertMessage(Message);
		 
		request.getSession().setAttribute("Message", Message);
		return "redirect:/messageList.action?page=1";
	}

	 
	// 显示留言板全部留言
	@RequestMapping("/messageList")
	public String messageList(@RequestParam int page, Model model,
			HttpServletRequest request) throws Exception {
		PageBean<Message> pageBean = messageService.findAllMessageByPage(page);
		model.addAttribute("pageBean", pageBean);
		List<Message> lists=pageBean.getList();

		for (Message list:lists)
		{
			System.out.println("222222"+list.toString());
		}
		return "messageList";
	} 
}
