package com.tassta.model;

import java.util.Date;

public class MessageImpl implements Message {

	private Date date;
	private String text;
	private User sender;
	private User receiver;

	public void setDate(Date date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setReciever(User reciever) {
		this.receiver = reciever;
	}
	public  MessageImpl(String text,User sender, User receiver,Date date){
		this.date=date;
		this.receiver=receiver;
		this.sender=sender;
		this.text=text;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public User getSender() {
		return sender;
	}

	@Override
	public User getReceiver() {
		return receiver;
	}
	@Override 
	public String toString(){
		return this.sender.getName()+": "+this.text;
	}

}
