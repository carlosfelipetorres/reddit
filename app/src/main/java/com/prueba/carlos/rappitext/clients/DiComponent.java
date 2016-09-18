package com.prueba.carlos.rappitext.clients;

import android.content.Context;

import com.prueba.carlos.rappitext.controllers.AppListActivity;
import com.prueba.carlos.rappitext.controllers.CategoryListActivity;
import com.prueba.carlos.rappitext.controllers.MainActivity;
import com.prueba.carlos.rappitext.controllers.ResumeAppActivity;
import com.prueba.carlos.rappitext.services.ManagersModule;
import com.prueba.carlos.rappitext.services.ServicesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dependency Injection component where all Activities, fragments and adapters are injected
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
@Singleton
@Component(modules = {ManagersModule.class, ServicesModule.class})
public interface DiComponent {

    /**
     * Context injection
     *
     * @return Injected context
     */
    Context context();

    // categories Activitie
    void inject(CategoryListActivity activity);

    // Main Activity
    void inject(MainActivity activity);

    // App Activity
    void inject(AppListActivity activity);

    // Resume Activity
    void inject(ResumeAppActivity activity);

}
