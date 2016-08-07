package com.tassta.view;


import com.tassta.model.User;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Callback;

public class UserViewModel {
    private User user;
    private BooleanProperty online;
    private BooleanProperty newMessage;

    public UserViewModel (User user){
    this.user=user;
        this.online=new SimpleBooleanProperty(user.isOnline());
        this.newMessage=new SimpleBooleanProperty(false);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isOnline() {
        return online.get();
    }

    public BooleanProperty onlineProperty() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online.set(online);
    }

    public boolean isNewMessage() {
        return newMessage.get();
    }

    public BooleanProperty newMessageProperty() {
        return newMessage;
    }

    public void setNewMessage(boolean newMessage) {
        this.newMessage.set(newMessage);
    }

    public static Callback<UserViewModel,Observable[]> extractor(){
        return (UserViewModel uvm)-> new Observable[]{
            uvm.newMessageProperty(),
                uvm.onlineProperty()
        };
    }
}
