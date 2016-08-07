package com.tassta.model;

import com.tassta.view.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class UserListModelImpl implements UserListModel {

	@Override
	public ObservableList<User> getUserList() {


		return ApplicationStub.userList;
	}

}
