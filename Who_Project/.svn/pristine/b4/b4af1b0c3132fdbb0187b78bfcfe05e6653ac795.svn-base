package com.who.onecupafterwork.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.who.onecupafterwork.signin.R;

public class NoticeContentActivity extends ActionBarActivity {

	String url;
	WebView webViewNotice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notice_content_activity);

		Intent intent = getIntent();
		url = intent.getStringExtra("keyNotice");
//		url = "http://naver.com";
		
		webViewNotice = (WebView) findViewById(R.id.webViewNotice);
		webViewNotice.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
		webViewNotice.loadUrl(url);
	}

}
