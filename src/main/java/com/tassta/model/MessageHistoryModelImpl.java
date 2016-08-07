package com.tassta.model;


public class MessageHistoryModelImpl implements MessageHistoryModel{
    @Override
    public MessageHistory getMessageHistory(User user) {
        MessageHistory mh=new MessageHistoryImpl();
        for (int i=0;i<mh.getMessageList().size();i++){
          //  String text=((MessageImpl)mh.getMessageList().get(i)).getText();
            //((MessageImpl)mh.getMessageList().get(i)).setText(text+user.getName());
                    }



        return mh;
    }
}
