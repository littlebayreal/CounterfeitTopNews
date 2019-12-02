package com.sziti.counterfeittopnews.widget.NewsDetailView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.sziti.counterfeittopnews.R;

public class NewsDetailHeaderView extends FrameLayout {
    private Context mContext;
    private View view;
    private WebView webView;
    public NewsDetailHeaderView(@NonNull Context context) {
        super(context);
    }

    public NewsDetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsDetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public NewsDetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void initView(){
       view = inflate(mContext, R.layout.news_detail_header,this);
       webView = view.findViewById(R.id.wv_content);
    }
    public void setDetail(String detail,LoadWebListener listener) {
        mWebListener = listener;

//        mTvTitle.setText(detail.title);
//
//        if (detail.media_user == null) {
//            //如果没有用户信息
//            mLlInfo.setVisibility(GONE);
//        } else {
//            if (!TextUtils.isEmpty(detail.media_user.avatar_url)){
//                GlideUtils.loadRound(mContext, detail.media_user.avatar_url, mIvAvatar);
//            }
//            mTvAuthor.setText(detail.media_user.screen_name);
//            mTvTime.setText(com.chaychan.news.utils.DateUtils.getShortTime(detail.publish_time * 1000L));
//        }

        if (TextUtils.isEmpty(detail))
            webView.setVisibility(GONE);

        webView.getSettings().setJavaScriptEnabled(true);//设置JS可用
//        webView.addJavascriptInterface(new ShowPicRelation(mContext),NICK);//绑定JS和Java的联系类，以及使用到的昵称

        String htmlPart1 = "<!DOCTYPE HTML html>\n" +
                "<head><meta charset=\"utf-8\"/>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<style> \n" +
                "img{width:100%!important;height:auto!important}\n" +
                " </style>";
        String htmlPart2 = "</body></html>";

        String html = htmlPart1 + detail + htmlPart2;


        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
//                addJs(view);//添加多JS代码，为图片绑定点击函数
                if (mWebListener != null){
                    mWebListener.onLoadFinished();
                }
            }
        });
    }
    /**添加JS代码，获取所有图片的链接以及为图片设置点击事件*/
    //添加js脚本  点击图片放大
//    private void addJs(WebView wv) {
//        wv.loadUrl("javascript:(function  pic(){"+
//                "var imgList = \"\";"+
//                "var imgs = document.getElementsByTagName(\"img\");"+
//                "for(var i=0;i<imgs.length;i++){"+
//                "var img = imgs[i];"+
//                "imgList = imgList + img.src +\";\";"+
//                "img.onclick = function(){"+
//                "window.chaychan.openImg(this.src);"+
//                "}"+
//                "}"+
//                "window.chaychan.getImgArray(imgList);"+
//                "})()");
//    }
    private LoadWebListener mWebListener;
    /**页面加载的回调*/
    public interface LoadWebListener{
        void onLoadFinished();
    }
}
