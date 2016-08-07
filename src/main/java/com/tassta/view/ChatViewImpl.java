package com.tassta.view;

import com.tassta.model.*;
import com.tassta.presenter.ChatPresenterImpl;
import com.tassta.presenter.ChatPresenter;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatViewImpl implements Initializable, ChatView {

    private ChatPresenter presenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        presenter = new ChatPresenterImpl(this);
        init();
    }

    private User loggedUser;



    private ImageView noIcon = new ImageView("file:images/no_icon.png");

    @FXML
    private ListView<UserViewModel> userList;
    @FXML
    private ListView<Message> messageHistory;
    @FXML
    private TextArea messageText;
    @FXML
    private ImageView userStatusImage;
    @FXML
    private ImageView userIconImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label test1Label;
    @FXML
    private void buttonClick() {
        ObservableBooleanValue userOnline=userList.getItems().get(3).onlineProperty();
        StringBinding style=Bindings.when(userOnline).then("-fx-background-color: blue").otherwise("-fx-background-color: white");
        test1Label.setStyle(style.get());
    }



    private void loadUsers() {
        this.presenter.initUsers();
        this.userList.setCellFactory(param -> {
            ImageView icon = new ImageView();
            return new ListCell<UserViewModel>() {
                @Override
                public void updateItem(UserViewModel user, boolean empty) {
                    super.updateItem(user, empty);

                    if (user == null || empty) {
                        setText(null);
                        setGraphic(null);

                    } else {
                        setItem(user);
                        boolean newMessage=user.isNewMessage();
                        setText(newMessage?user.getUser().getName()+"!!!!":user.getUser().getName());
                        boolean isOnline = user.isOnline();
                        String style="-fx-background-insets: 2 ;";
                        setStyle(isOnline ?style+ "-fx-background-color:#ADFF2F" : style+"-fx-background-color:#D3D3D3");
                        if (user.getUser().getIcon() == null) {
                            icon.setImage(noIcon.getImage());
                        } else icon.setImage(user.getUser().getIcon());
                        icon.setPreserveRatio(true);
                        icon.setFitHeight(32);
                        icon.setFitWidth(40);
                        setGraphic(icon);
                    }
                }
            };
        });


    }

    public void sendMessage() throws Exception {
        UserViewModel receiver = this.userList.getSelectionModel().getSelectedItem();

        if (receiver == null) return;

        this.presenter.sendMessage(receiver.getUser(), messageText.getText());
        this.messageText.clear();

    }


    @FXML
    private void userListClickHandler() {
        UserViewModel selectedUserViewModel = userList.getSelectionModel().getSelectedItem();

        if (selectedUserViewModel == null) return;
        this.presenter.setSelectedUser(selectedUserViewModel.getUser());
        selectedUserViewModel.setNewMessage(false);

    }

    @FXML
    private SplitPane sPane;

    public void init() {
        loadUsers();
        this.loggedUser =  ApplicationStub.getLoggedUser();
        usernameLabel.setText(this.loggedUser.getName());

        if (loggedUser.getIcon() == null) {
            System.out.println("no image");
            userIconImage.setImage(noIcon.getImage());
        } else {
            userIconImage.setImage(loggedUser.getIcon());
            userIconImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                ImageView image = new ImageView(loggedUser.getIcon());

                @Override
                public void handle(MouseEvent event) {
                    final Popup popup = new Popup();
                    BorderPane pane = new BorderPane();
                    image.setPreserveRatio(true);
                    image.setFitWidth(300);
                    popup.setHideOnEscape(true);
                    pane.setCenter(image);

                    popup.getContent().add(pane);
                    popup.show(userIconImage.getScene().getWindow());
                    popup.setAutoHide(true);
                }
            });
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) sPane.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        Scene scene=new Scene(root, 300, 200);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/style.css");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public ListView<UserViewModel> getUserListView() {
        return userList;
    }

    @Override
    public ListView<Message> getMessageListView() {
        return messageHistory;
    }





    private class IconClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            final Popup popup = new Popup();
            Node node = (Node) event.getSource();
            ImageView image = ((ImageView) event.getSource());

            BorderPane pane = new BorderPane();
            image.setPreserveRatio(true);
            image.setFitWidth(300);
            popup.setHideOnEscape(true);
            pane.setCenter(image);

            popup.getContent().add(pane);
            popup.show(node.getScene().getWindow());
            popup.setAutoHide(true);
        }
    }
}
