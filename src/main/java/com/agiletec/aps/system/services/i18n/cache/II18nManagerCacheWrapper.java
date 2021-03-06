/*
 * Copyright 2018-Present Entando Inc. (http://www.entando.com) All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.agiletec.aps.system.services.i18n.cache;

import com.agiletec.aps.system.common.ICacheWrapper;
import java.util.Map;

import org.entando.entando.ent.exception.EntException;
import com.agiletec.aps.system.services.i18n.II18nDAO;
import com.agiletec.aps.util.ApsProperties;

public interface II18nManagerCacheWrapper extends ICacheWrapper {

    public static final String I18N_MANAGER_CACHE_NAME = "Entando_I18nManager";

    public static final String I18N_CACHE_NAME_PREFIX = "I18nManager_labelGroup_";

    public static final String I18N_CODES_CACHE_NAME = "I18nManager_labelGroups";

    public void initCache(II18nDAO i18nDAO) throws EntException;

    public Map<String, ApsProperties> getLabelGroups();

    public ApsProperties getLabelGroup(String key);

    public void addLabelGroup(String key, ApsProperties labels);

    public void updateLabelGroup(String key, ApsProperties labels);

    public void removeLabelGroup(String key);

}
