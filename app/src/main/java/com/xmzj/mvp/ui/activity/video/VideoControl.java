package com.xmzj.mvp.ui.activity.video;


import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class VideoControl {
    public interface VideoView extends LoadDataView {

        // void homeUserInfoSuccess(HomeUserInfoResponse homeUserInfoResponse);
//        void messageIdSuccess(MessageIdResponse messageIdResponse);
    }

    public interface PresenterVideo extends Presenter<VideoView> {

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
