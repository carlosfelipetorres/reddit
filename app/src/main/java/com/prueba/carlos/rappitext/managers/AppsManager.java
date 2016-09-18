package com.prueba.carlos.rappitext.managers;

import com.prueba.carlos.rappitext.model.RedditApp;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.persistence.CrudManager;

import java.sql.SQLException;

/**
 * Implementation of the apps' queries
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class AppsManager extends CrudManager<RedditApp, Integer> implements IAppsManager {

    /**
     * This is the main constructor of the CrudManager
     *
     * @param helper
     *         The DBHelper
     *
     * @throws SQLException
     *         If there's an error creating the Entity's DAO
     */
    public AppsManager(DatabaseHelper helper) throws SQLException {
        super(helper, RedditApp.class);
    }
}
