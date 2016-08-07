package com.tassta.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.Date;


public class MessageHistoryImpl implements MessageHistory {
    public ObservableList<Message> getMessageList(){
        MessageImpl message1=new MessageImpl("Hi, my name is ",new UserImpl(0,"user",true,new Image("")),new UserImpl(4,"Mark",true,new Image("")),new Date());
        //message1.setText();
        //message1.setSender();
       // message1.setDate(new Date());
        MessageImpl message2=new MessageImpl("Hi, I'm  ",new UserImpl(0,"user",true,new Image("")),new UserImpl(5,"Serj",false,new Image("")),new Date());
        //message2.setText("Hi, I am ");
       // message2.setSender(new UserImpl(5,"Anton"));
       // message2.setDate(new Date());
        ObservableList<Message> list=FXCollections.observableArrayList(message1,message2);

        return list;
    }

}
