/*
 * Copyright 2015-Present Entando Inc. (http://www.entando.com) All rights reserved.
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
package org.entando.entando.aps.system.services.dataobjectmodel.cache;

import com.agiletec.aps.system.common.ICacheWrapper;
import java.util.List;

import org.entando.entando.ent.exception.EntException;
import org.entando.entando.aps.system.services.dataobjectmodel.DataObjectModel;
import org.entando.entando.aps.system.services.dataobjectmodel.IDataObjectModelDAO;

public interface IDataObjectModelCacheWrapper extends ICacheWrapper {

    public static final String CACHE_NAME = "Entando_DataObjectModelManager";

    public static final String CACHE_NAME_PREFIX = "DataObjectModelManager_model_";

    public static final String CODES_CACHE_NAME = "DataObjectModelManager_codes";

    public void initCache(IDataObjectModelDAO dataObjectModelDAO) throws EntException;

	public DataObjectModel getModel(String code);

    public void addModel(DataObjectModel model);

    public void updateModel(DataObjectModel model);

    public void removeModel(DataObjectModel model);

    public List<DataObjectModel> getModels();

}
