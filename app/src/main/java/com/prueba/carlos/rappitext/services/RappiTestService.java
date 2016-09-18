package com.prueba.carlos.rappitext.services;

import android.content.Context;

import com.github.johnpersano.supertoasts.SuperToast;
import com.prueba.carlos.rappitext.clients.ClienteRappiTestSystem;
import com.prueba.carlos.rappitext.clients.IClienteRappiTestSystem;
import com.prueba.carlos.rappitext.managers.IAppsManager;
import com.prueba.carlos.rappitext.managers.ICategoriesManager;
import com.prueba.carlos.rappitext.model.ListingData;
import com.prueba.carlos.rappitext.model.RedditApp;
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
     * Saved Category
     **/
    private RedditCategory categorySaved;

    /**
     * Saved App
     **/
    private RedditApp appSaved;

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

    /**
     * Categories Manager
     **/
    private final ICategoriesManager mCategoriesManager;

    /**
     * Apps Manager
     **/
    private final IAppsManager mAppsManager;


    public RappiTestService(Context context, ICategoriesManager categoriesManager, IAppsManager appsManager) {
        this.mContext = context;
        this.mCategoriesManager = categoriesManager;
        this.mAppsManager = appsManager;
    }

    @Override
    public RedditApp getAppSaved() {
        return appSaved;
    }

    @Override
    public void saveApp(RedditApp appSaved) {
        this.appSaved = appSaved;
    }

    @Override
    public RedditCategory getCategorySaved() {
        return categorySaved;
    }

    @Override
    public void saveCategory(RedditCategory categorySaved) {
        this.categorySaved = categorySaved;
    }

    /**
     * This method creates or updates an redditCategory
     *
     * @param redditCategory category to be created or updated
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

    /**
     * This method creates or updates an reddit app
     *
     * @param redditApp app to be created or updated
     */
    @Override
    public void createOrUpdateApp(RedditApp redditApp) {
        mAppsManager.createOrUpdate(redditApp);
    }

    /**
     * This method gets all the stored apps
     *
     * @return All stored apps
     */
    @Override
    public List<RedditApp> getAllApps() {
        return mAppsManager.all();
    }

    /**
     * This method gets the stored apps by id
     *
     * @return  stored apps
     */
    @Override
    public List<RedditApp> getAppsByCatId(Object value) {
        return mAppsManager.findByAttr("idCategory", value);
    }


    @Override
    public List<RedditCategory> obtenerReddit() {
        IRappiTestApi rappiTestApi = getRappiTestApi();

        Call<RespuestaBasica<ListingData<RedditCategory>>> call = rappiTestApi.getReddits();
        Response<RespuestaBasica<ListingData<RedditCategory>>> response = mCliente.execute(call);

        if (!isSuccessful(response)) {
            List<RedditCategory> categoriesPersisted = getAllCategories();
            return categoriesPersisted;
        }
        if (response.body() == null) {
            AppUtils.crearToast(mContext, "Hubo un problema con la conexion a internet", SuperToast.Duration.MEDIUM,
                    TipoNotificacion.ALERTA).show();
        }

        List<RedditCategory> categories = new ArrayList<>();
        List<RespuestaBasica<RedditCategory>> listing = response.body().getData().getElementos();
        for (RespuestaBasica<RedditCategory> rc : listing) {
            categories.add(rc.getData());
        }

        if (getAllCategories().isEmpty()) {
            for (RedditCategory rc : categories) {
                createOrUpdateCategory(rc);
            }
        }

        return categories;
    }

    @Override
    public List<RedditApp> obtenerRedditApps() {
        IRappiTestApi rappiTestApi = getRappiTestApi();

        Call<RespuestaBasica<ListingData<RedditApp>>> call = rappiTestApi.getRedditsApps(categorySaved.getUrl() + ".json");
        Response<RespuestaBasica<ListingData<RedditApp>>> response = mCliente.execute(call);

        if (!isSuccessful(response)) {
            List<RedditApp> appsPersisted = getAppsByCatId(categorySaved.getId());
            return appsPersisted;
        }
        if (response.body() == null) {
            AppUtils.crearToast(mContext, "Hubo un problema con la conexion a internet", SuperToast.Duration.MEDIUM,
                    TipoNotificacion.ALERTA).show();
        }

        List<RedditApp> apps = new ArrayList<>();
        List<RespuestaBasica<RedditApp>> listing = response.body().getData().getElementos();
        for (RespuestaBasica<RedditApp> rc : listing) {
            apps.add(rc.getData());
        }

        if (getAppsByCatId(categorySaved.getId()).isEmpty()) {
            for (RedditApp rc : apps) {
                rc.setIdCategory(categorySaved.getId());
                createOrUpdateApp(rc);
            }
        }

        return apps;
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
