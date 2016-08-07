package com.tassta.view;

import com.tassta.model.*;
import com.tassta.test.chat.noimpl.IoManager;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.stream.Collectors;

public class UserViewModelStub {

    private IoManager manager;
    private final IntegerProperty id=new SimpleIntegerProperty();
    private final StringProperty name=new SimpleStringProperty();
    private final BooleanProperty isOnline=new SimpleBooleanProperty();
    private final ImageView icon=new ImageView();

    private final UserListModel userListModel = new UserListModelImpl();

    private final MessageHistoryModel messageHistoryModel = new MessageHistoryModelImpl();


    private final User user;

 /*   public UserViewModelStub(String name,String password) {
        this.user=new UserImpl(0,name);
        this.id.set(user.getId());
        this.name.set(name);
        this.isOnline.set(true);
        this.icon.setImage(new Image("file:images/photo.jpg"));
    }
*/
    public UserViewModelStub(User user){
        this.user=user;
        this.name.set(user.getName());
        this.icon.setImage(user.getIcon());
        this.isOnline.set(user.isOnline());
        this.id.set(user.getId());
    }
/*
    public void sendMessage(UserViewModel user,MessageViewModel message) throws Exception {
    }

    public ObservableList<MessageViewModel> getMessageHistory(){
        return FXCollections.observableArrayList(this.messageHistoryModel.getMessageHistory(this.user).getMessageList().stream().map(e->new MessageViewModel(e)).collect(Collectors.toList()));
    }

    public ObservableList<UserViewModel> getListOfUsers(){
        return FXCollections.observableArrayList(userListModel.getUserList().stream().map(e->new UserViewModel(e)).collect(Collectors.toList()));
    }
*/

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public boolean isOnline() {
        return isOnline.get();
    }

    public BooleanProperty isOnlineProperty() {
        return isOnline;
    }

    public ImageView getIcon() {
        return icon;
    }



}
