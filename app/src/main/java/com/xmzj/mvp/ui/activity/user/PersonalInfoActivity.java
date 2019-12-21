package com.xmzj.mvp.ui.activity.user;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.di.components.DaggerPersonalInfoComponent;
import com.xmzj.di.modules.ActivityModule;
import com.xmzj.di.modules.PersonalInfoModule;
import com.xmzj.entity.base.BaseActivity;
import com.xmzj.entity.request.UploadImage;
import com.xmzj.entity.response.PersonalInfoResponse;
import com.xmzj.mvp.utils.PicUtils;
import com.xmzj.mvp.views.CircleImageView;
import com.xmzj.mvp.views.dialog.AvatarPopupWindow;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人中心
 */
public class PersonalInfoActivity extends BaseActivity implements PersonalInfoControl.PersonalInfoView, AvatarPopupWindow.PopupWindowListener, TakePhoto.TakeResultListener,
        InvokeListener {
    @Inject
    PersonalInfoControl.PresenterPersonalInfo mPresenter;
    @BindView(R.id.personal_info_layout)
    LinearLayout mPersonalInfoLayout;
    @BindView(R.id.common_title_tv)
    TextView mCommonTitleTv;
    @BindView(R.id.avatar_iv)
    CircleImageView mAvatarIv;
    @BindView(R.id.nick_tv)
    EditText mNickTv;
    @BindView(R.id.phone_tv)
    EditText mPhoneTv;
    @BindView(R.id.email_tv)
    EditText mEmailTv;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private Uri uri;
    //裁剪使用
    private CropOptions cropOptions;
    //成功取得照片
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        File file = new File(getExternalCacheDir(), System.currentTimeMillis() + ".png");
        uri = Uri.fromFile(file);
        int size = Math.min(getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);
        cropOptions = new CropOptions.Builder().setOutputX(size).setOutputX(size).setWithOwnCrop(false).create();
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_personal_info);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initInjectData();
        setStatusBar();
    }

    @Override
    protected void initView() {
        mCommonTitleTv.setText("个人信息");
        onRequestPersonalInfo();
    }

    @Override
    protected void initData() {

    }

    /**
     * 请求个人信息
     */
    private void onRequestPersonalInfo() {
        mPresenter.onRequestPersonalInfo(mBuProcessor.getToken());
    }

    @Override
    public void getPersonalInfoSuccess(PersonalInfoResponse personalInfoResponse) {
        mImageLoaderHelper.displayImage(this, personalInfoResponse.getAvatar(), mAvatarIv, R.mipmap.default_head);
        mNickTv.setText(personalInfoResponse.getNickname());
        mPhoneTv.setText(personalInfoResponse.getPhoneNum());
        mEmailTv.setText(personalInfoResponse.getEmail());
    }

    @OnClick({R.id.common_back, R.id.avatar_layout_rl, R.id.nick_layout_rl, R.id.phone_layout_rl, R.id.email_layout_rl, R.id.save_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_back:
                finish();
                break;
            case R.id.avatar_layout_rl:
                //弹出popupWindow框
                new AvatarPopupWindow(this, this).initPopWindow(mPersonalInfoLayout);
                break;
            case R.id.nick_layout_rl:
                break;
            case R.id.phone_layout_rl:
                break;
            case R.id.email_layout_rl:
                break;
            case R.id.save_tv:
                break;
        }
    }


    @Override
    public void takeSuccess(TResult result) {
        bitmap = BitmapFactory.decodeFile(result.getImage().getCompressPath());
        String path = PicUtils.convertIconToString(PicUtils.ImageCompressL(bitmap));
//        LogUtils.d("path:" + result.getImage().getCompressPath());
        //上传图片
        mAvatarIv.setImageBitmap(bitmap);
        uploadImage(path);
    }

    /**
     * 上传图片
     */
    private void uploadImage(String filename) {
        UploadImage uploadImage = new UploadImage();
        uploadImage.avatarFile = filename;
        mPresenter.uploadImageRequest(uploadImage);
    }


//    /**
//     * 上传图片成功
//     */
//    @Override
//    public void getUploadImageSuccess(String avatarUrl) {
//        onRequestPersonalInfo(avatarUrl, null, null, null, null);
//    }


    @Override
    public void takePhotoBtnListener() {
        //拍照进行裁剪
        takePhoto.onPickFromCaptureWithCrop(uri, cropOptions);
    }

    @Override
    public void albumBtnListener() {
        //从相册中选取进行裁剪
        takePhoto.onPickFromGalleryWithCrop(uri, cropOptions);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void takeCancel() {
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 获取TakePhoto实例
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        //设置压缩规则，最大500kb
        takePhoto.onEnableCompress(new CompressConfig.Builder().setMaxSize(500 * 1024).setMaxPixel(800).create(), false);
        return takePhoto;
    }


    private void initInjectData() {
        DaggerPersonalInfoComponent.builder().appComponent(getAppComponent())
                .personalInfoModule(new PersonalInfoModule(this, this))
                .activityModule(new ActivityModule(this)).build().inject(this);
    }


}
