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
package org.entando.entando.aps.system.services.dataobjectrenderer;

import java.util.List;

import org.entando.entando.ent.util.EntLogging.EntLogger;
import org.entando.entando.ent.util.EntLogging.EntLogFactory;

import com.agiletec.aps.system.RequestContext;
import com.agiletec.aps.system.SystemConstants;
import com.agiletec.aps.system.services.baseconfig.ConfigInterface;
import com.agiletec.aps.system.services.lang.Lang;
import com.agiletec.aps.system.services.page.IPage;
import com.agiletec.aps.system.services.page.IPageManager;
import com.agiletec.aps.system.services.page.Widget;
import com.agiletec.aps.system.services.url.IURLManager;
import com.agiletec.aps.system.services.url.PageURL;
import com.agiletec.aps.tags.InternalServletTag;
import com.agiletec.aps.util.ApsWebApplicationUtils;

/**
 * @author E.Santoboni
 */
public class SystemInfoWrapper {

	private static final EntLogger _logger = EntLogFactory.getSanitizedLogger(SystemInfoWrapper.class);

	public SystemInfoWrapper(RequestContext reqCtx) {
		this.setReqCtx(reqCtx);
	}

	/**
	 * Return the value of a System parameter.
	 *
	 * @param paramName The name of parameters
	 * @return The value to return
	 */
	public String getConfigParameter(String paramName) {
		try {
			ConfigInterface configManager
					= (ConfigInterface) ApsWebApplicationUtils.getBean(SystemConstants.BASE_CONFIG_MANAGER, this.getReqCtx().getRequest());
			return configManager.getParam(paramName);
		} catch (Throwable t) {
			_logger.error("Error extracting config parameter - parameter ", paramName, t);
			return null;
		}
	}

	public IPage getCurrentPage() {
		try {
			IPage page = (IPage) this.getReqCtx().getExtraParam(SystemConstants.EXTRAPAR_CURRENT_PAGE);
			return page;
		} catch (Throwable t) {
			_logger.error("Error getting current page", t);
			return null;
		}
	}

	public IPage getPageWithWidget(String widgetCode) {
		IPage page = null;
		try {
			IPageManager pageManager = (IPageManager) ApsWebApplicationUtils.getBean(SystemConstants.PAGE_MANAGER, this.getReqCtx().getRequest());
			List<IPage> pages = pageManager.getOnlineWidgetUtilizers(widgetCode);
			if (null != pages && !pages.isEmpty()) {
				page = pages.get(0);
			}
			return page;
		} catch (Throwable t) {
			_logger.error("Error getting page with widget: {}", widgetCode, t);
			return null;
		}
	}

	public String getPageURLWithWidget(String widgetCode) {
		String url = null;
		try {
			IPage page = this.getPageWithWidget(widgetCode);
			if (null == page) {
				return url;
			}
			IURLManager urlManager = (IURLManager) ApsWebApplicationUtils.getBean(SystemConstants.URL_MANAGER, this.getReqCtx().getRequest());
			PageURL pageUrl = urlManager.createURL(this.getReqCtx());
			pageUrl.setPage(page);
			url = pageUrl.getURL();
		} catch (Throwable t) {
			_logger.error("Error getting pageUrl with widget: {}", widgetCode, t);
			return null;
		}
		return url;
	}

	public String getActionPathUrl(String actionPath) {
		String url = null;
		RequestContext reqCtx = this.getReqCtx();
		IURLManager urlManager = (IURLManager) ApsWebApplicationUtils.getBean(SystemConstants.URL_MANAGER, reqCtx.getRequest());
		try {
			PageURL pageUrl = urlManager.createURL(reqCtx);
			IPage currPage = (IPage) reqCtx.getExtraParam(SystemConstants.EXTRAPAR_CURRENT_PAGE);
			Integer currentFrame = (Integer) reqCtx.getExtraParam(SystemConstants.EXTRAPAR_CURRENT_FRAME);
			pageUrl.setPage(currPage);
			pageUrl.addParam(InternalServletTag.REQUEST_PARAM_ACTIONPATH, actionPath);
			pageUrl.addParam(InternalServletTag.REQUEST_PARAM_FRAMEDEST, currentFrame.toString());
			url = pageUrl.getURL();
		} catch (Throwable t) {
			_logger.error("Error getting path for action path: {}", actionPath, t);
			return null;
		}
		return url;
	}

	public Lang getCurrentLang() {
		try {
			return (Lang) this.getReqCtx().getExtraParam(SystemConstants.EXTRAPAR_CURRENT_LANG);
		} catch (Throwable t) {
			_logger.error("Error getting current lang", t);
			return null;
		}
	}

	public Widget getCurrentWidget() {
		try {
			return (Widget) this.getReqCtx().getExtraParam(SystemConstants.EXTRAPAR_CURRENT_WIDGET);
		} catch (Throwable t) {
			_logger.error("Error getting current Widget", t);
			return null;
		}
	}

	protected RequestContext getReqCtx() {
		return _reqCtx;
	}

	private void setReqCtx(RequestContext reqCtx) {
		this._reqCtx = reqCtx;
	}

	private RequestContext _reqCtx;

}
