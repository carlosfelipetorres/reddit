package com.prueba.carlos.rappitext.services;


import com.prueba.carlos.rappitext.model.ListingData;
import com.prueba.carlos.rappitext.model.RedditApp;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.model.RespuestaBasica;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Api del clima
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos F. Torres J.</a>
 */
public interface IRappiTestApi {

    /**
     * Gets the reddits
     *
     * @return Basic response of listings of categories
     */
    @GET("reddits.json")
    Call<RespuestaBasica<ListingData<RedditCategory>>> getReddits();

    /**
     * Gets the apps from reddit
     *
     * @param url url of app
     * @return Basic response of listings of apps
     */
    @GET
    Call<RespuestaBasica<ListingData<RedditApp>>> getRedditsApps(@Url String url);
}
