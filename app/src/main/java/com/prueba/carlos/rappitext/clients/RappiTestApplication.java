package com.prueba.carlos.rappitext.clients;

import android.app.Application;

import com.prueba.carlos.rappitext.services.ManagersModule;
import com.prueba.carlos.rappitext.services.ServicesModule;


/**
 * This is the Rappitest Application where Dependency Injection is set up
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class RappiTestApplication extends Application {

    DiComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDiComponent.builder().servicesModule(new ServicesModule(this))
                .managersModule(new ManagersModule(this)).build();
    }

    public DiComponent getComponent() {
        return component;
    }

}


