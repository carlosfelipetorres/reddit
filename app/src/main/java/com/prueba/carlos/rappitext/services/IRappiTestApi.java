package com.prueba.carlos.rappitext.services;


import com.prueba.carlos.rappitext.model.ListingData;
import com.prueba.carlos.rappitext.model.RespuestaBasica;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api del clima
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos F. Torres J.</a>
 */
public interface IRappiTestApi {

    @GET("reddits.json")
    Call<RespuestaBasica<ListingData>> getReddits();
}
