package com.prueba.carlos.rappitext.controllers;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prueba.carlos.rappitext.R;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.utils.ImageManageUtils;

import java.io.File;
import java.util.List;

/**
 * Esta clase es utilizada para adaptar las visita de pdv tat en un Recycler view
 *
 * @author <a href="mailto:carlos-torres@accionplus.com">Carlos Torres</a>
 */
public class CategoriesAdapter extends RecyclerView.Adapter {
    /**
     * Contexto actual
     **/
    private Context mContext;

    /**
     * Inflater
     **/
    private LayoutInflater mInflater;

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
    public CategoriesAdapter(Context context, List<RedditCategory> redditCategories) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.redditCategoryList = redditCategories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vistaCompetencia = mInflater.inflate(R.layout.categories_row, parent, false);
        return new ViewHolder(vistaCompetencia);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RedditCategory category = redditCategoryList.get(position);
        ViewHolder vh = (ViewHolder) holder;

        vh.titulo.setText(category.getTitle());
        vh.descripcion.setText(category.getPublicDescription());
        if ((category.getIconImg().isEmpty() || category.getIconImg() == null) && category.getHeaderImg() != null) {
            Uri uri = Uri.parse(category.getHeaderImg());
            ImageManageUtils.displayImage(vh.imagen, uri.toString(), null);
        } else {
            Uri uri = Uri.parse(category.getIconImg());
            ImageManageUtils.displayImage(vh.imagen, uri.toString(), null);
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

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.title);
            descripcion = (TextView) itemView.findViewById(R.id.description);
            imagen = (ImageView) itemView.findViewById(R.id.photo);
        }
    }
}

