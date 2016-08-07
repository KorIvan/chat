package com.tassta.test.chat.noimpl;

import java.util.function.Consumer;

import com.tassta.model.Message;
import com.tassta.model.User;
import com.tassta.model.UserStateChangeHandler;

/**
 * It's the interface you supposed to use with your virtual fellow programmer who implements network module.
 * You don't need to implement it but your application must to work with any implementation of this interface.
 */
public interface IoManager
{
    void sendMessage(User receiver, String text) throws Exception;

    void setRecieveMessageHandler(Consumer<Message> handler);

    void setUserStateChangeHandler(UserStateChangeHandler handler);

    void setUserAddedHandler(Consumer<User> handler);

    void setUserRemovedHandler(Consumer<Integer> userId);
}
