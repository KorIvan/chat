package com.tassta.test.chat.noimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import com.tassta.model.*;
import javafx.application.Platform;

public class IoManagerImpl implements IoManager {

	private Timer messageTimer;
	public IoManagerImpl(){
		messageTimer=new Timer();
		messageTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				String formattedTime = dateFormat.format(now);
				User loggedUser = ApplicationStub.getLoggedUser();
				if(messageHandler != null && loggedUser!=null) {
					for (User user : ApplicationStub.userList) {
						messageHandler.accept(new MessageImpl(String.format("Hello at {%s} from {%s}",formattedTime,
                                user.getName()),
								user,loggedUser,now));
					}

			}}
		},5000,60000);
	}

	@Override
	public void sendMessage(User receiver, String text) throws Exception {

		System.out.println("Message sent to user "+receiver.getName()+". Message is: "+text);
		// TODO Auto-generated method stub

	}
	private Consumer<Message> messageHandler;
	@Override
	public void setRecieveMessageHandler(Consumer<Message> handler) {
			// TODO Auto-generated method stub
		messageHandler = handler;

	}

	@Override
	public void setUserStateChangeHandler(UserStateChangeHandler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserAddedHandler(Consumer<User> handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserRemovedHandler(Consumer<Integer> userId) {
		
		// TODO Auto-generated method stub
	}
}
