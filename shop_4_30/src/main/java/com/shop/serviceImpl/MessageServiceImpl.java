package com.shop.serviceImpl;

import java.util.List;


import com.shop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

import com.shop.Utils.PageBean;
import com.shop.mapper.MessageMapper;
import com.shop.po.Message;


public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageMapper messagesMapper;

	@Override
	public void insertMessage(Message messages) throws Exception {
		messagesMapper.insert(messages);
	}

	@Override
	public PageBean<Message> findAllMessageByPage(int page) throws Exception {
		PageBean<Message> pageBean = new PageBean<>();
//		设置这是第几页
		pageBean.setPage(page);
//		设置10个
		int limitPage =4;
		pageBean.setLimitPage(limitPage);
//		设置一共多少页
		int totlePage = 0;
//		查询一共有多少页
		totlePage = messagesMapper.countAllMessage();
		if(Math.ceil(totlePage % limitPage)==0){
			totlePage=totlePage / limitPage;
		}else{
			totlePage=totlePage / limitPage+1;
		}
		pageBean.setTotlePage(totlePage);
		int beginPage= (page-1)*limitPage;
		//商品集合

		List<Message> lists = messagesMapper.findAllMessageByPage(beginPage, limitPage) ;

		pageBean.setList(lists);
		return pageBean;
	}

	@Override
	public void deleteMessage(int messageid) throws Exception {
		messagesMapper.deleteByPrimaryKey(messageid);
	}
}
