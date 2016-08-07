package com.tassta.view;

import com.tassta.model.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.converter.DateTimeStringConverter;

import java.util.Date;


public class MessageViewModel {
    private final StringProperty text=new SimpleStringProperty();
 //   private final UserViewModel sender;//=new UserViewModel();
 //   private final UserViewModel receiver;//=new UserViewModel();
    private final Date date;

    public MessageViewModel(Message message) {
        this.text.set(message.getText());
 //       this.sender=new UserViewModel("1","f");
 //       this.receiver=new UserViewModel("1","1");
        this.date=message.getDate();
    }
/*
    public UserViewModel getSender() {
        return sender;
    }

    public UserViewModel getReceiver() {
        return receiver;
    }
*/
    public Date getDate() {
        return date;
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }
}
