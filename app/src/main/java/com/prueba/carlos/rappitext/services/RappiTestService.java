package com.prueba.carlos.rappitext.services;

import android.content.Context;

import com.github.johnpersano.supertoasts.SuperToast;
import com.prueba.carlos.rappitext.clients.ClienteRappiTestSystem;
import com.prueba.carlos.rappitext.clients.IClienteRappiTestSystem;
import com.prueba.carlos.rappitext.managers.ICategoriesManager;
import com.prueba.carlos.rappitext.model.ListingData;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.model.RespuestaBasica;
import com.prueba.carlos.rappitext.model.TipoNotificacion;
import com.prueba.carlos.rappitext.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class RappiTestService implements IRappiTestService {

    /**
     * Cliente servidor
     **/
    @Inject
    IClienteRappiTestSystem mCliente = new ClienteRappiTestSystem();

    /**
     * Singleton API visitas
     **/
    private IRappiTestApi mRappiTestApi;

    /**
     * Application context
     **/
    private Context mContext;

    /** Categories Manager **/
    private final ICategoriesManager mCategoriesManager;


    public RappiTestService(Context context, ICategoriesManager categoriesManager) {
        this.mContext = context;
        this.mCategoriesManager = categoriesManager;
    }

    /**
     * This method creates or updates an redditCategory
     *
     * @param redditCategory
     *         category to be created or updated
     */
    @Override
    public void createOrUpdateCategory(RedditCategory redditCategory) {
        mCategoriesManager.createOrUpdate(redditCategory);
    }

    /**
     * This method gets all the stored categories
     *
     * @return All stored categories
     */
    @Override
    public List<RedditCategory> getAllCategories() {
        return mCategoriesManager.all();
    }


    @Override
    public List<RedditCategory> obtenerReddit() {
        IRappiTestApi rappiTestApi = getRappiTestApi();

        Call<RespuestaBasica<ListingData>> call = rappiTestApi.getReddits();
        Response<RespuestaBasica<ListingData>> response = mCliente.execute(call);

        if (!isSuccessful(response)) {
            List<RedditCategory> categoriesPersisted = getAllCategories();
            return categoriesPersisted;
        }
        if(response.body() == null){
            AppUtils.crearToast(mContext, "Hubo un problema con la conexion a internet", SuperToast.Duration.MEDIUM,
                    TipoNotificacion.ALERTA).show();
        }

        List<RedditCategory> categories =  new ArrayList<>();
        List<RespuestaBasica<RedditCategory>> listing = response.body().getData().getElementos();
        for(RespuestaBasica<RedditCategory>  rc : listing){
            categories.add(rc.getData());
        }

        if(getAllCategories().isEmpty()){
            for(RedditCategory rc : categories){
                createOrUpdateCategory(rc);
            }
        }

        return categories;
    }

    /**
     * Obtiene el API de las visitas del cliente
     *
     * @return La interfaz del API de vencimisntos de producto
     */
    private IRappiTestApi getRappiTestApi() {
        if (mRappiTestApi == null) {
            mRappiTestApi = mCliente.getApi(IRappiTestApi.class);
        }
        return mRappiTestApi;
    }

    /**
     * Este metodo verifica si la respuesta fue exitosa o no
     *
     * @param response La respuesta para ser verificada
     * @return True si fue exitoso. False de lo contrario.
     */
    public static boolean isSuccessful(Response response) {
        return response != null && response.isSuccessful();
    }
}
