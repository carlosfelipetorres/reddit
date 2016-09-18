package com.prueba.carlos.rappitext.managers;

import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.persistence.CrudManager;

import java.sql.SQLException;

/**
 * Implementation of the categories' queries
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class CategoriesManager extends CrudManager<RedditCategory, Integer> implements ICategoriesManager {

    /**
     * This is the main constructor of the CrudManager
     *
     * @param helper
     *         The DBHelper
     *
     * @throws SQLException
     *         If there's an error creating the Entity's DAO
     */
    public CategoriesManager(DatabaseHelper helper) throws SQLException {
        super(helper, RedditCategory.class);
    }
}
