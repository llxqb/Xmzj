package com.xmzj.mvp.ui.activity.main;


import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class MainControl {
    public interface MainView extends LoadDataView {

        // void homeUserInfoSuccess(HomeUserInfoResponse homeUserInfoResponse);
//        void messageIdSuccess(MessageIdResponse messageIdResponse);
    }

    public interface PresenterMain extends Presenter<MainView> {

//        /**
//         * 根据融云第三方id获取用户头像和昵称
//         */
//        UserInfo onRequestUserInfoByRid(UserInfoByRidRequest userInfoByRidRequest);
//
//        /**
//         * 查看用户嗨豆查看私密照片message_id
//         */
//        void onRequestMessageId(TokenRequest tokenRequest);
    }

}
