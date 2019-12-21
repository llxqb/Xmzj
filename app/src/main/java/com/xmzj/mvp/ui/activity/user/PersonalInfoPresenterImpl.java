package com.xmzj.mvp.ui.activity.user;

import android.content.Context;

import com.xmzj.R;
import com.xmzj.entity.request.UploadImage;
import com.xmzj.entity.response.PersonalInfoResponse;
import com.xmzj.help.RetryWithDelay;
import com.xmzj.mvp.model.LoginModel;
import com.xmzj.mvp.model.ResponseData;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by li.liu on 2017/4/27.
 * PresenterBooksImpl
 */

public class PersonalInfoPresenterImpl implements PersonalInfoControl.PresenterPersonalInfo {

    private PersonalInfoControl.PersonalInfoView mPersonalInfoView;
    private final LoginModel mLoginModel;
    private final Context mContext;

    @Inject
    public PersonalInfoPresenterImpl(Context context, LoginModel model, PersonalInfoControl.PersonalInfoView view) {
        mContext = context;
        mLoginModel = model;
        mPersonalInfoView = view;
    }

    /**
     * 请求个人信息
     */
    @Override
    public void onRequestPersonalInfo(String token) {
        mPersonalInfoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mLoginModel.onRequestPersonalInfo(token).compose(mPersonalInfoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestPersonalInfoSuccess, throwable -> mPersonalInfoView.showErrMessage(throwable),
                        () -> mPersonalInfoView.dismissLoading());
        mPersonalInfoView.addSubscription(disposable);
    }


    /**
     * 请求个人信息 成功
     */
    private void requestPersonalInfoSuccess(ResponseData responseData) {
        if (responseData.resultCode == 0) {
            responseData.parseData(PersonalInfoResponse.class);
            if (responseData.parsedData != null) {
                PersonalInfoResponse response = (PersonalInfoResponse) responseData.parsedData;
                mPersonalInfoView.getPersonalInfoSuccess(response);
            }
        } else {
            mPersonalInfoView.showToast(responseData.errorMsg);
        }
    }
    /**
     * 上传图片
     */
    @Override
    public void uploadImageRequest(UploadImage uploadImage) {
        mPersonalInfoView.showLoading(mContext.getResources().getString(R.string.loading));
        Disposable disposable = mLoginModel.uploadImageRequest(uploadImage).compose(mPersonalInfoView.applySchedulers()).retryWhen(new RetryWithDelay(3, 3000))
                .subscribe(this::requestUploadImageSuccess, throwable -> mPersonalInfoView.showErrMessage(throwable),
                        () -> mPersonalInfoView.dismissLoading());
        mPersonalInfoView.addSubscription(disposable);
    }


    /**
     * 上传图片 成功
     */
    private void requestUploadImageSuccess(ResponseData responseData) {
//        if (responseData.resultCode == 0) {
//            responseData.parseData(PersonalInfoResponse.class);
//            if (responseData.parsedData != null) {
//                PersonalInfoResponse response = (PersonalInfoResponse) responseData.parsedData;
//                mPersonalInfoView.getPersonalInfoSuccess(response);
//            }
//        } else {
//            mPersonalInfoView.showToast(responseData.errorMsg);
//        }
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }


}
