package com.xmzj.mvp.ui.activity.user;


import com.xmzj.entity.request.UploadImage;
import com.xmzj.entity.response.PersonalInfoResponse;
import com.xmzj.mvp.presenter.LoadDataView;
import com.xmzj.mvp.presenter.Presenter;

/**
 * Created by li.liu on 2017/12/13.
 */

public class PersonalInfoControl {
    public interface PersonalInfoView extends LoadDataView {
        void getPersonalInfoSuccess(PersonalInfoResponse personalInfoResponse);
    }

    public interface PresenterPersonalInfo extends Presenter<PersonalInfoView> {

        /**
         * 请求个人信息
         */
        void onRequestPersonalInfo(String token);
        /**
         * 上传图片
         */
        void uploadImageRequest(UploadImage uploadImage);

    }

}
