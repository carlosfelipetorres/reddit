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
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.services.IRappiTestService;
import com.prueba.carlos.rappitext.utils.AnimationUtils;
import com.prueba.carlos.rappitext.utils.ImageManageUtils;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

/**
 * Esta clase es utilizada para adaptar las visita de categorias en un Recycler view
 *
 * @author <a href="mailto:carlos-torres@accionplus.com">Carlos Torres</a>
 */
public class CategoriesAdapter extends RecyclerView.Adapter {
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
     * Reddit service
     **/
    @Inject
    IRappiTestService rappiTestService;

    /**
     * Las categorias a ser mostradas
     **/
    private List<RedditCategory> redditCategoryList;

    /**
     * Constructor para el adaptador de informes devisitas
     *
     * @param context          Contexto actual
     * @param redditCategories Visitas
     */
    public CategoriesAdapter(Context context, List<RedditCategory> redditCategories,
                             IRappiTestService rappiTestService) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.redditCategoryList = redditCategories;
        this.rappiTestService = rappiTestService;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vistaCompetencia = mInflater.inflate(R.layout.categories_row, parent, false);
        return new ViewHolder(vistaCompetencia);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        RedditCategory category = redditCategoryList.get(position);
        final ViewHolder vh = (ViewHolder) holder;

        vh.titulo.setText(category.getTitle());
        vh.descripcion.setText(category.getPublicDescription());
        if ((category.getIconImg().isEmpty() || category.getIconImg() == null) && category.getHeaderImg() != null) {
            Uri uri = Uri.parse(category.getHeaderImg());
            ImageManageUtils.displayRoundImage(vh.imagen, uri.toString(), null);
        } else {
            Uri uri = Uri.parse(category.getIconImg());
            ImageManageUtils.displayImage(vh.imagen, uri.toString(), null);
        }
        vh.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RedditCategory rc = redditCategoryList.get(position);
                rappiTestService.saveCategory(rc);
                Intent intent = new Intent(mContext, AppListActivity.class);
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
            Animation animation = android.view.animation.AnimationUtils.loadAnimation(mContext, R.anim.anim_scale_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return redditCategoryList.size();
    }

    /**
     * Set reddit list
     *
     * @param redditCategory
     */
    public void setRedditCategoryList(List<RedditCategory> redditCategory) {
        this.redditCategoryList = redditCategory;
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
         * card view
         **/
        public CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.title);
            descripcion = (TextView) itemView.findViewById(R.id.description);
            imagen = (ImageView) itemView.findViewById(R.id.photo);
            cv = (CardView) itemView.findViewById(R.id.cv);
        }
    }
}

