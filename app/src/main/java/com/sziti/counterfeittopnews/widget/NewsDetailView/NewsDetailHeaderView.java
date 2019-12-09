package com.sziti.counterfeittopnews.widget.NewsDetailView;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sziti.counterfeittopnews.R;
import com.sziti.counterfeittopnews.http.Response.NewsDetail;
import com.sziti.counterfeittopnews.util.GlideUtils;
//import com.sziti.counterfeittopnews.util.GlideUtils;

public class NewsDetailHeaderView extends FrameLayout {
    private Context mContext;
    private View view;

	private TextView mTvTitle;

	public LinearLayout mLlInfo;

	private ImageView mIvAvatar;

	private TextView mTvAuthor;

	private TextView mTvTime;

	WebView mWvContent;
    public NewsDetailHeaderView(@NonNull Context context) {
//        super(context);
        this(context,null);
    }

    public NewsDetailHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
		this(context,attrs,0);
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
       view = inflate(mContext, R.layout.header_news_detail,this);
       mTvTitle = view.findViewById(R.id.tvTitle);
       mIvAvatar = view.findViewById(R.id.iv_avatar);
       mTvAuthor = view.findViewById(R.id.tv_author);
       mLlInfo = view.findViewById(R.id.ll_info);
       mTvTime = view.findViewById(R.id.tv_time);

       mWvContent = view.findViewById(R.id.wv_content);
    }
    public void setDetail(NewsDetail detail, LoadWebListener listener) {
        mWebListener = listener;

        mTvTitle.setText(detail.title);

        if (detail.media_user == null) {
            //如果没有用户信息
            mLlInfo.setVisibility(GONE);
        } else {
            if (!TextUtils.isEmpty(detail.media_user.avatar_url)){
                GlideUtils.loadRound(mContext, detail.media_user.avatar_url, mIvAvatar);
            }
            mTvAuthor.setText(detail.media_user.screen_name);
            mTvTime.setText("");
        }

        if (TextUtils.isEmpty(detail.content))
            mWvContent.setVisibility(GONE);

		mWvContent.getSettings().setJavaScriptEnabled(true);//设置JS可用
//		mWvContent.addJavascriptInterface(new ShowPicRelation(mContext),NICK);//绑定JS和Java的联系类，以及使用到的昵称
//		mWvContent.addJavascriptInterface(this, "MyApp");
        String htmlPart1 = "<!DOCTYPE HTML html>\n" +
                "<head><meta charset=\"utf-8\"/>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<style> \n" +
                "img{width:100%!important;height:auto!important}\n" +
                " </style>";
        String htmlPart2 = "</body></html>";

        String html = htmlPart1 + detail.content + htmlPart2;

		mWvContent.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
		mWvContent.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
            	Log.i("zxb","webView的实际高度:"+ view.getContentHeight());
				Log.i("zxb","webView的实际高度:"+ view.getMeasuredHeight());
//                addJs(view);//添加多JS代码，为图片绑定点击函数
                if (mWebListener != null){
                    mWebListener.onLoadFinished();
                }
            }
        });

    }
    /**添加JS代码，获取所有图片的链接以及为图片设置点击事件*/
    //添加js脚本  点击图片放大
    private void addJs(WebView wv) {
//		wv.loadUrl("javascript:MyApp.resize(document.body.getBoundingClientRect().height)");
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
    }

	@JavascriptInterface
	public void resize(final float height) {
    	final float scale = mContext.getResources().getDisplayMetrics().density;
		((Activity) getContext()).runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mWvContent.setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels - 50, (int) (height*scale/2)));
			}
		});
	}
    private LoadWebListener mWebListener;
    /**页面加载的回调*/
    public interface LoadWebListener{
        void onLoadFinished();
    }
}
