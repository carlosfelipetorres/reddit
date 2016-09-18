package com.prueba.carlos.rappitext.controllers;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.prueba.carlos.rappitext.R;
import com.prueba.carlos.rappitext.clients.DiComponent;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.model.TipoNotificacion;
import com.prueba.carlos.rappitext.services.IRappiTestService;
import com.prueba.carlos.rappitext.utils.AppUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Actividad que maneja la pantalla inicial del clima
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class CategoryListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * Reddits categories
     **/
    private List<RedditCategory> redditCategories;

    /** Sincronizar **/
    @BindView(R.id.reddits_rv)
    RecyclerView redditsRv;

    /**
     * Reddit service
     **/
    @Inject
    IRappiTestService rappiTestService;

    /** Adapter **/
    private CategoriesAdapter mAdapter;

    /**
     * Swipe and Refresh layout
     **/
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        setTitle("Categories");
        int orientation = getResources().getConfiguration().orientation;
        if(orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            redditsRv.setLayoutManager(new LinearLayoutManager(this));
        }else {
            redditsRv.setLayoutManager(new GridLayoutManager(this, 2));
        }

        AppUtils.initSwipeRefreshLayout(mRefreshLayout);
        mRefreshLayout.setOnRefreshListener(this);

        new CargaInicialAsyncTask().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    /**
     * Injection component. This should be done if there are fields to be injected
     *
     * @param diComponent Dependency injection
     */
    @Override
    protected void injectComponent(DiComponent diComponent) {
        diComponent.inject(this);
    }

    @Override
    public void onRefresh() {
        new CargaInicialAsyncTask().execute();
    }

    /**
     * Esta clase realiza la carga inicial de manera asíncrona
     */
    private class CargaInicialAsyncTask extends ProgressAsyncTask<Void, Void, Void> {

        /**
         * Constructor para el progress async task
         */
        public CargaInicialAsyncTask() {
            super(getActivity());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            redditCategories = rappiTestService.obtenerReddit();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (redditCategories == null) {
                AppUtils.crearToast(CategoryListActivity.this, "Hubo un problema con la conexion a internet", SuperToast.Duration.MEDIUM,
                        TipoNotificacion.ALERTA).show();
                super.onPostExecute(aVoid);
                return;
            }
            mAdapter = new CategoriesAdapter(CategoryListActivity.this, redditCategories);
            redditsRv.setAdapter(mAdapter);

            if (mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(false);
            }
            super.onPostExecute(aVoid);
        }
    }
}
