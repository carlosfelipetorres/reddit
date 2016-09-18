package com.prueba.carlos.rappitext.managers;

import com.prueba.carlos.rappitext.model.RedditApp;
import com.prueba.carlos.rappitext.model.RedditCategory;
import com.prueba.carlos.rappitext.persistence.ICrudManager;

/**
 * Exposed queries for apps' manager
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public interface IAppsManager extends ICrudManager<RedditApp, Integer> {
}
