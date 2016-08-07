package com.tassta.presenter;

import com.tassta.model.*;
import com.tassta.test.chat.noimpl.IoManager;
import com.tassta.test.chat.noimpl.IoManagerImpl;
import com.tassta.view.ChatView;
import com.tassta.view.UserViewModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ChatPresenterImpl implements ChatPresenter {

    private ChatView chatView;
    private User loggedUser;
    private User selectedUser;

    private IoManager ioManager;
    private final UserListModel userListModel = new UserListModelImpl();
    private final MessageHistoryModel messageHistoryModel = new MessageHistoryModelImpl();
    private ObservableList<Message> messages;
    private ObservableList<Message> currentDialogue;
    private ObservableList<UserViewModel> userViewModels;

    public ChatPresenterImpl(ChatView view) {
        this.chatView = view;
        this.loggedUser = ApplicationStub.getLoggedUser();
        ioManager = new IoManagerImpl();
        this.messages = FXCollections.observableArrayList();
        initServiceCallbacks();
    }

    public void initUsers() {
        List<UserViewModel> users = userListModel.getUserList().stream().map((User u) -> new UserViewModel(u)).collect(Collectors.toList());
        this.userViewModels = FXCollections.observableList(users, UserViewModel.extractor());

        this.chatView.getUserListView().setItems(this.userViewModels);
    }

    private void initServiceCallbacks(){
        this.ioManager.setUserAddedHandler(u -> this.addUserCallback(u));

        this.ioManager.setUserStateChangeHandler(new UserStateChangeHandler() {
            @Override
            public void handle(int id, User newValue) {
                userStateCallback(newValue);
            }
        });
        this.ioManager.setRecieveMessageHandler(m -> this.receiveMessage(m));
        this.ioManager.setUserRemovedHandler(m -> this.removeUser(m));
    }

    private void removeUser(Integer m) {
        Platform.runLater(()->{
            UserViewModel target = this.findById(m);
            if(target!=null)
                this.userViewModels.remove(target);
        });
    }


    private void receiveMessage(Message m) {
        Platform.runLater(()-> {
            this.messages.add(m);
            if (this.selectedUser != null && m.getSender().getId() == this.selectedUser.getId())
                this.currentDialogue.add(m);
            else
            {
                UserViewModel target = findById(m.getSender().getId());
                if(target!=null)
                    target.setNewMessage(true);
            }

        });

    }

    private void addUserCallback(User user) {
        Platform.runLater(()-> {
        this.userViewModels.add(new UserViewModel(user));
        });
    }

    private void userStateCallback(User user) {
        Platform.runLater(()->{
            UserViewModel target = findById(user.getId());
            if(target!=null)
                target.setOnline(user.isOnline());

        });

    }



    private UserViewModel findById(int userId){
        return this.userViewModels.filtered(u->u.getUser().getId() == userId).get(0);
    }



    @Override
    public void sendMessage(User selectedUser, String text) throws Exception {
        if(this.selectedUser!=null) {
            Integer userId = selectedUser.getId();
            User receiver = this.userListModel.getUserList().stream().filter(e -> e.getId() == userId).findFirst().get();
            Message message = new MessageImpl(text, loggedUser, receiver, new Date());
            this.messages.add(message);
            this.currentDialogue.add(message);
            ioManager.sendMessage(receiver, text);
        }
    }

    @Override
    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        this.currentDialogue =  FXCollections.observableArrayList(this.messages.filtered(e -> e.getReceiver().equals(selectedUser) || e.getSender().equals(selectedUser)));

        this.chatView.getMessageListView().setItems(this.currentDialogue);

    }







}
