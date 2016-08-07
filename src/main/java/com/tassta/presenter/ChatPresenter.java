package com.tassta.presenter;

import com.tassta.model.Message;
import com.tassta.model.User;
import com.tassta.view.UserViewModel;
import javafx.collections.ObservableList;

import java.util.function.Consumer;


public interface ChatPresenter {




    void sendMessage(User user,String text) throws Exception;
    void setSelectedUser(User selectedUser);
     void initUsers();


}
