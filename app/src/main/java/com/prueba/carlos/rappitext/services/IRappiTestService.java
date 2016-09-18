package com.prueba.carlos.rappitext.services;

import com.prueba.carlos.rappitext.model.RedditApp;
import com.prueba.carlos.rappitext.model.RedditCategory;

import java.util.List;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public interface IRappiTestService {

    RedditApp getAppSaved();

    void saveApp(RedditApp appSaved);

    /**
     * Gets the saved category
     *
     * @return redit category
     */
    RedditCategory getCategorySaved();

    /**
     * Save the selected category
     *
     * @param categorySaved category
     */
    void saveCategory(RedditCategory categorySaved);

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

    void createOrUpdateApp(RedditApp redditApp);

    List<RedditApp> getAllApps();

    List<RedditApp> getAppsByCatId(Object value);

    /**
     * Obtiene la informacion de categorias de reddit
     *
     * @return Respuesta con Reddit
     */
    List<RedditCategory> obtenerReddit();

    /**
     * Obtiene los reddits apps
     *
     * @return reddit apps
     */
    List<RedditApp> obtenerRedditApps();
}
