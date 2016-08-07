package com.tassta.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * Created by DimaKorn on 8/8/2016.
 */
public class ApplicationStub {
    public static ObservableList<User> userList;
    static {
        UserImpl user1 = new UserImpl(1, "Ivan", true, new Image(("file:images/icon1.png")));

        UserImpl user2 = new UserImpl(2, "Alex", true, new Image("file:images/icon2.png"));

        UserImpl user3 = new UserImpl(3, "Mark", true, new Image("file:images/icon3.png"));

        UserImpl user4 = new UserImpl(4, "Serj", false, null);

        UserImpl user5 = new UserImpl(5, "Andre", true, null);


        userList = FXCollections.observableArrayList(user1, user2, user3, user4, user5);
    }
    private static  User loggedUser;
    public static void setLoggedUser(User user){
        loggedUser = user;
    }

    public static User getLoggedUser(){
        return loggedUser;
    }

}
