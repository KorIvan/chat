package com.tassta.view;

import com.tassta.model.Message;
import com.tassta.model.User;
import javafx.scene.control.ListView;

/**
 * Created by float on 07.08.16.
 */
public interface ChatView {
    ListView<UserViewModel> getUserListView();

    ListView<Message> getMessageListView();


}
