package com.prueba.carlos.rappitext.services;

import com.prueba.carlos.rappitext.model.RedditCategory;

import java.util.List;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public interface IRappiTestService {

    /**
     * Crea o modifica una categoria
     *
     * @param redditCategory
     */
    void createOrUpdateCategory(RedditCategory redditCategory);

    /**
     * Obtiene todas las categorias
     *
     * @return All categories
     */
    List<RedditCategory> getAllCategories();

    /**
     * Obtiene la informacion de categorias de reddit
     *
     * @return Respuesta con Reddit
     */
    List<RedditCategory> obtenerReddit();
}
