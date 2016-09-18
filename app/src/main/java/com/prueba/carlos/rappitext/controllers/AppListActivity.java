package com.prueba.carlos.rappitext.controllers;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.prueba.carlos.rappitext.R;
import com.prueba.carlos.rappitext.clients.DiComponent;
import com.prueba.carlos.rappitext.model.RedditApp;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.model.TipoNotificacion;
import com.prueba.carlos.rappitext.services.IRappiTestService;
import com.prueba.carlos.rappitext.utils.AppUtils;
import com.prueba.carlos.rappitext.utils.ImageManageUtils;
import com.prueba.carlos.rappitext.utils.ItemClickSupport;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AppListActivity extends BaseActivity implements
        SwipeRefreshLayout.OnRefreshListener, ItemClickSupport.OnItemClickListener {

    /**
     * Reddits Apps
     **/
    private List<RedditApp> redditApp;

    /**
     * Sincronizar
     **/
    @BindView(R.id.app_rv)
    RecyclerView appRv;

    /**
     * Reddit service
     **/
    @Inject
    IRappiTestService rappiTestService;

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
     * card view
     **/
    @BindView(R.id.cv)
    public CardView cv;

    /**
     * Adapter
     **/
    private AppsAdapter mAdapter;

    /**
     * Swipe and Refresh layout
     **/
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        setTitle(rappiTestService.getCategorySaved().getDisplayName()+" Apps");
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            appRv.setLayoutManager(new LinearLayoutManager(this));
        } else {
            appRv.setLayoutManager(new GridLayoutManager(this, 2));
        }

        AppUtils.initSwipeRefreshLayout(mRefreshLayout);
        mRefreshLayout.setOnRefreshListener(this);

        RedditCategory categorySaved = rappiTestService.getCategorySaved();
        titulo.setText(categorySaved.getTitle());
        descripcion.setText(categorySaved.getPublicDescription());
        if ((categorySaved.getIconImg().isEmpty() ||
                categorySaved.getIconImg() == null) &&
                categorySaved.getHeaderImg() != null) {
            Uri uri = Uri.parse(categorySaved.getHeaderImg());
            ImageManageUtils.displayImage(imagen, uri.toString(), null);
        } else {
            Uri uri = Uri.parse(categorySaved.getIconImg());
            ImageManageUtils.displayImage(imagen, uri.toString(), null);
        }
        if (!categorySaved.getKeyColor().isEmpty()) {
            cv.setBackgroundColor(Color.parseColor(categorySaved.getKeyColor()));
        } else {
            cv.setCardBackgroundColor(R.color.material_deep_orange_400);
        }

        new CargaInicialAsyncTask().execute();
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
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

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
            redditApp = rappiTestService.obtenerRedditApps();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (redditApp == null) {
                AppUtils.crearToast(AppListActivity.this, "Hubo un problema con la conexion a internet", SuperToast.Duration.MEDIUM,
                        TipoNotificacion.ALERTA).show();
                super.onPostExecute(aVoid);
                return;
            }
            mAdapter = new AppsAdapter(AppListActivity.this, redditApp, rappiTestService );
            appRv.setAdapter(mAdapter);

            if (mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setRefreshing(false);
            }
            super.onPostExecute(aVoid);
        }
    }
}
