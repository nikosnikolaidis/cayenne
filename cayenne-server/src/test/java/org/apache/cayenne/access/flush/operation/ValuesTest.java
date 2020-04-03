/*****************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 ****************************************************************/

package org.apache.cayenne.access.flush.operation;

import java.util.Collections;

import org.apache.cayenne.ObjectId;
import org.apache.cayenne.PersistenceState;
import org.apache.cayenne.Persistent;
import org.apache.cayenne.map.DbAttribute;
import org.apache.cayenne.map.DbEntity;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @since 4.2
 */
public class ValuesTest {

    @Test
    public void testEmptyValues() {
        ObjectId id = ObjectId.of("test", "id", 123);
        Persistent persistent = mockObject(id);
        DbRowOp row = mockRow(persistent);

        Values values = new Values(row, false);

        assertTrue(values.getUpdatedAttributes().isEmpty());
        assertTrue(values.getSnapshot().isEmpty());
        assertTrue(values.getFlattenedIds().isEmpty());
        assertTrue(values.isEmpty());
    }

    @Test
    public void testValuesWithId() {
        ObjectId id = ObjectId.of("test", "id", 123);
        Persistent persistent = mockObject(id);
        DbRowOp row = mockRow(persistent);

        Values values = new Values(row, true);

        assertTrue(values.getUpdatedAttributes().isEmpty());
        assertTrue(values.getFlattenedIds().isEmpty());
        assertEquals(Collections.singletonMap("id", 123), values.getSnapshot());
        assertFalse(values.isEmpty());
    }

    @Test
    public void testValuesWithUpdatedAttributes() {
        ObjectId id = ObjectId.of("test", "id", 123);
        Persistent persistent = mockObject(id);
        DbRowOp row = mockRow(persistent);

        Values values = new Values(row, false);
        DbAttribute attr1 = new DbAttribute("attr1");
        values.addValue(attr1, 32, false);

        assertEquals(Collections.singletonList(attr1), values.getUpdatedAttributes());
        assertEquals(Collections.singletonMap("attr1", 32), values.getSnapshot());
        assertTrue(values.getFlattenedIds().isEmpty());
        assertFalse(values.isEmpty());
    }

    private DbRowOp mockRow(Persistent persistent) {
        DbRowOp row = mock(DbRowOp.class);
        ObjectId objectId = persistent.getObjectId();
        when(row.getChangeId()).thenReturn(objectId);
        when(row.getObject()).thenReturn(persistent);
        when(row.getEntity()).thenReturn(mockEntity());
        return row;
    }

    private Persistent mockObject(ObjectId id) {
        Persistent persistent = mock(Persistent.class);
        when(persistent.getObjectId()).thenReturn(id);
        when(persistent.getPersistenceState()).thenReturn(PersistenceState.MODIFIED);
        return persistent;
    }

    private DbEntity mockEntity() {
        DbAttribute attribute1 = new DbAttribute("id");
        attribute1.setPrimaryKey(true);
        DbAttribute attribute2 = new DbAttribute("attr");
        DbEntity testEntity = new DbEntity("TEST");
        testEntity.addAttribute(attribute1);
        testEntity.addAttribute(attribute2);
        return testEntity;
    }
}