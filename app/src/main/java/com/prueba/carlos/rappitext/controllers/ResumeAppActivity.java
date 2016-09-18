package com.prueba.carlos.rappitext.controllers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prueba.carlos.rappitext.R;
import com.prueba.carlos.rappitext.clients.DiComponent;
import com.prueba.carlos.rappitext.model.RedditApp;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.services.IRappiTestService;
import com.prueba.carlos.rappitext.utils.AnimationUtils;
import com.prueba.carlos.rappitext.utils.AppUtils;
import com.prueba.carlos.rappitext.utils.ImageManageUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class ResumeAppActivity extends BaseActivity  implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * Nombre de la app
     **/
    @BindView(R.id.title)
    public TextView titulo;
    /**
     * Descripcion de la app
     **/
    @BindView(R.id.description)
    public TextView descripcion;

    /**
     * Imagen
     **/
    @BindView(R.id.photo)
    public ImageView imagen;

    /**
     * score de la app
     **/
    @BindView(R.id.score)
    public TextView score;

    /**
     * author de la app
     **/
    @BindView(R.id.author)
    public TextView author;

    /**
     * num_comments de la app
     **/
    @BindView(R.id.num_comments)
    public TextView numComments;

    /**
     * ups de la app
     **/
    @BindView(R.id.ups)
    public TextView ups;

    /**
     * url_btn de la app
     **/
    @BindView(R.id.url_btn)
    public TextView urlBtn;

    /**
     * url_btn de la app
     **/
    @BindView(R.id.show_content)
    public TextView showContent;

    /**
     * content de la app
     **/
    @BindView(R.id.content)
    public ImageView content;

    /**
     * Swipe and Refresh layout
     **/
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    /**
     * ups de la app
     **/
    @BindView(R.id.web_view)
    public WebView webView;

    /**
     * progress bar
     **/
    @BindView(R.id.progress_bar)
    public ProgressBar progressBar;

    /**
     * Reddit service
     **/
    @Inject
    IRappiTestService rappiTestService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_app);

        setTitle(rappiTestService.getAppSaved().getAuthor()+" App");

        final RedditApp appSaved = rappiTestService.getAppSaved();
        titulo.setText(appSaved.getTitle());
        descripcion.setText(appSaved.getSelftext());
        Uri uri = Uri.parse(appSaved.getThumbnail());
        ImageManageUtils.displayImage(imagen, uri.toString(), null);
        author.setText("Author: " + appSaved.getAuthor());
        score.setText("Score: " + appSaved.getScore());
        numComments.setText("N. Comments: " + appSaved.getNumComments());
        ups.setText("UPs: " + appSaved.getUps());

        urlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appSaved.getUrl()));
                AnimationUtils.configurarAnimacion(ResumeAppActivity.this, urlBtn, true, intent);
            }
        });

        if(appSaved.getImagenes() != null) {
            Uri uri2 = Uri.parse(appSaved.getImagenes().getUrlImageSource());
            ImageManageUtils.displayImage(content, uri2.toString(), null);
        }else{
            content.setVisibility(View.GONE);
        }

        webView.setWebViewClient(new MyBrowser());
        showContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                showContent.setVisibility(View.GONE);
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getProgress();
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.loadUrl(appSaved.getUrl());
            }
        });

        AppUtils.initSwipeRefreshLayout(mRefreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }


    }

    @Override
    protected void injectComponent(DiComponent diComponent) {
        diComponent.inject(this);
    }

    @Override
    public void onRefresh() {
        checkOnline();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        AnimationUtils.configurarAnimacion(ResumeAppActivity.this, urlBtn, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }
}
