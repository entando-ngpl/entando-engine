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
package org.entando.entando.aps.system.services.actionlog;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.agiletec.aps.system.SystemConstants;
import com.agiletec.aps.system.common.FieldSearchFilter;
import com.agiletec.aps.system.common.model.dao.SearcherDaoPaginatedResult;
import org.entando.entando.ent.exception.EntException;
import com.agiletec.aps.system.services.user.UserDetails;
import org.entando.entando.aps.system.services.actionlog.model.ActionLogRecord;
import org.entando.entando.aps.system.services.actionlog.model.IActionLogRecordSearchBean;
import org.entando.entando.aps.system.services.actionlog.model.IActivityStreamSearchBean;

/**
 * Interface for the service that manages the {@link ActionLogRecord}
 *
 * @author E.Santoboni - S.Puddu
 */
public interface IActionLogManager {

    /**
     * Load a list of {@link ActionLogRecord} codes that match the search
     * criteria rapresented by the searchBean
     *
     * @param searchBean object containing the search criteria
     * @return a list of codes
     * @throws EntException if an error occurs
     */
    public List<Integer> getActionRecords(IActionLogRecordSearchBean searchBean) throws EntException;

    /**
     * Save a new {@link ActionLogRecord}
     *
     * @param actionRecord
     * @throws EntException
     */
    public void addActionRecord(ActionLogRecord actionRecord) throws EntException;

    /**
     * Load a {@link ActionLogRecord}
     *
     * @param id the code of the record to load
     * @return an {@link ActionLogRecord}
     * @throws EntException if an error occurs
     */
    public ActionLogRecord getActionRecord(int id) throws EntException;

    /**
     * Delete a {@link ActionLogRecord}
     *
     * @param id the code of the record to delete
     * @throws EntException if an error occurs
     */
    public void deleteActionRecord(int id) throws EntException;

    public List<Integer> getActivityStream(List<String> userGroupCodes) throws EntException;
    
    public List<Integer> getActivityStream(FieldSearchFilter[] filters, List<String> userGroupCodes) throws EntException;

    public List<Integer> getActivityStream(UserDetails loggedUser) throws EntException;

    public List<Integer> getActivityStream(FieldSearchFilter[] filters, UserDetails loggedUser) throws EntException;

    public List<Integer> getActivityStream(IActivityStreamSearchBean activityStreamSearchBean) throws EntException;

    public Set<Integer> extractOldRecords(Integer maxActivitySizeByGroup) throws EntException;

    public void updateRecordDate(int id) throws EntException;

    public Date lastUpdateDate(UserDetails loggedUser) throws EntException;

    public static final String LOG_APPENDER_THREAD_NAME_PREFIX = SystemConstants.ENTANDO_THREAD_NAME_PREFIX + "ActionRecordAppender_";
    public static final String LOG_CLEANER_THREAD_NAME_PREFIX = SystemConstants.ENTANDO_THREAD_NAME_PREFIX + "ActivityStreamCleanerThread_";

    public SearcherDaoPaginatedResult<ActionLogRecord> getPaginatedActionRecords(IActionLogRecordSearchBean searchBean) throws EntException;

}
