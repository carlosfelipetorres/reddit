package com.prueba.carlos.rappitext.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.prueba.carlos.rappitext.R;
import com.prueba.carlos.rappitext.model.RedditApp;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.services.IRappiTestService;
import com.prueba.carlos.rappitext.utils.AnimationUtils;
import com.prueba.carlos.rappitext.utils.ImageManageUtils;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Esta clase es utilizada para adaptar las visita de app de reddit en un Recycler view
 *
 * @author <a href="mailto:carlos-torres@accionplus.com">Carlos Torres</a>
 */
public class AppsAdapter extends RecyclerView.Adapter {
    /**
     * Contexto actual
     **/
    private Context mContext;

    /** ultima posicion del item **/
    private int lastPosition = -1;

    /**
     * Inflater
     **/
    private LayoutInflater mInflater;

    /**
     * Las categorias a ser mostradas
     **/
    private List<RedditApp> redditAppsList;

    /**
     * Reddit service
     **/
    @Inject
    IRappiTestService rappiTestService;

    /**
     * Constructor para el adaptador de informes devisitas
     *
     * @param context    Contexto actual
     * @param redditApps Visitas
     */
    public AppsAdapter(Context context, List<RedditApp> redditApps, IRappiTestService rappiTestService) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.redditAppsList = redditApps;
        this.rappiTestService = rappiTestService;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vistaCompetencia = mInflater.inflate(R.layout.categories_row, parent, false);
        return new ViewHolder(vistaCompetencia);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        RedditApp app = redditAppsList.get(position);
        final ViewHolder vh = (ViewHolder) holder;

        vh.titulo.setText(app.getTitle());
        vh.descripcion.setText(app.getSelftext());
        Uri uri = Uri.parse(app.getThumbnail());
        ImageManageUtils.displayRoundImage(vh.imagen, uri.toString(), null);
        vh.author.setText("Author: " + app.getAuthor());
        vh.author.setVisibility(View.VISIBLE);
        vh.score.setText("Score: " + app.getScore());
        vh.score.setVisibility(View.VISIBLE);
        vh.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedditApp rc = redditAppsList.get(position);
                rappiTestService.saveApp(rc);
                Intent intent = new Intent(mContext, ResumeAppActivity.class);
                AnimationUtils.configurarAnimacion(mContext, vh.cv, true, intent);
            }
        });
        setAnimation(vh.cv, position);
    }

    /**
     * Set the animation of the holder o the list
     *
     * @param viewToAnimate
     * @param position
     */
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = android.view.animation.AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return redditAppsList.size();
    }

    /**
     * Set reddit list
     *
     * @param redditApps
     */
    public void setRedditAppsList(List<RedditApp> redditApps) {
        this.redditAppsList = redditApps;
    }

    /**
     * Clase que contiene los elementos de la vista
     */
    private static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Nombre de la app
         **/
        public TextView titulo;
        /**
         * Descripcion de la app
         **/
        public TextView descripcion;
        /**
         * Imagen
         **/
        public ImageView imagen;
        /**
         * autor de la app
         **/
        public TextView author;
        /**
         * score de la app
         **/
        public TextView score;

        /**
         * card view
         **/
        public CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.title);
            descripcion = (TextView) itemView.findViewById(R.id.description);
            imagen = (ImageView) itemView.findViewById(R.id.photo);
            author = (TextView) itemView.findViewById(R.id.author);
            score = (TextView) itemView.findViewById(R.id.score);
            cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}


