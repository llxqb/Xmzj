package com.xmzj.mvp.views.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmzj.R;
import com.xmzj.entity.constants.Constant;
import com.xmzj.help.DialogFactory;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 公用提示的dialog
 *
 * @author helei
 */
public class CommonDialog extends BaseDialogFragment {
    public static final String TAG = CommonDialog.class.getSimpleName();
    @BindView(R.id.common_dialog_title)
    TextView commonDialogTitle;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.pop_contain)
    LinearLayout mPopContain;
    @BindView(R.id.style5_left_btn)
    Button mStyle5LeftBtn;
    @BindView(R.id.style5_right_btn)
    Button mStyle5RightBtn;
    @BindView(R.id.dialog_style5_ll)
    LinearLayout mDialogStyle5Ll;
    private CommonDialogListener dialogBtnListener;
    private String title, mContent;
    private int mType;// 0 方式一  1 方式二
    private Unbinder bind;

    public static CommonDialog newInstance() {
        return new CommonDialog();
    }

    public void setStyle(int type) {
        this.mType = type;
    }


    public void setContent(String content) {
        this.mContent = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setListener(CommonDialogListener dialogBtnListener) {
        this.dialogBtnListener = dialogBtnListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_common, container, true);
        bind = ButterKnife.bind(this, view);
          if (mType == Constant.DIALOG_FIVE) {
            mDialogStyle5Ll.setVisibility(View.VISIBLE);
            ivClose.setVisibility(View.GONE);
        }
        commonDialogTitle.setText(mContent);
        return view;
    }


    @OnClick({R.id.iv_close, R.id.pop_contain,  R.id.common_dialog_layout, R.id.style5_left_btn, R.id.style5_right_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                closeCommonDialog();
                break;
            case R.id.pop_contain:
                break;
            case R.id.style5_left_btn:
                closeCommonDialog();
                break;
            case R.id.style5_right_btn:
                if (dialogBtnListener != null) {
                    dialogBtnListener.commonDialogBtnOkListener();
                }
                closeCommonDialog();
                break;
            case R.id.common_dialog_layout:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }


    public interface CommonDialogListener {
        void commonDialogBtnOkListener();
    }


    public void closeCommonDialog() {
        try {
            this.dismiss();
        } catch (Exception e) {
            DialogFactory.dismissDialogFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), TAG);
        }
    }
}
