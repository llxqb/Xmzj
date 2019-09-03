package com.xmzj.help;

/**
 * 下载文件help类
 */
public class DownLoadHelper {
    /**
     * 下载音频文件
     */
//    private void downloadVideo(Activity context , String urlPath) {
//        DownloadUtil mDownloadUtil = new DownloadUtil();
//        mDownloadUtil.downloadFile(urlPath, new DownloadListener() {
//            @Override
//            public void onStart() {
//                LogUtils.e("onStart: ");
//                context.runOnUiThread(() -> {
//                    ToastUtil.showToast(context,"下载中...");
//                    mCircleProgressLayout.setVisibility(View.VISIBLE);
//                });
//            }
//
//            @Override
//            public void onProgress(final int currentLength) {
//                context.runOnUiThread(() -> {
//                    mCircleProgress.setProgress(currentLength);
//                });
//            }
//
//            @Override
//            public void onFinish(String localPath) {
//                mVideoPath = localPath;
//                LogUtils.e("onFinish: " + localPath);
//                context.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast("下载完成");
//                        mCircleProgressLayout.setVisibility(View.GONE);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(final String erroInfo) {
//                LogUtils.e("onFailure: " + erroInfo);
//                context.runOnUiThread(() -> {
//                    showToast(erroInfo);
//                    mCircleProgressLayout.setVisibility(View.GONE);
//                });
//            }
//        });
//    }
}
