/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildFileTest;

public class UpToDateTest extends BuildFileTest {

    public UpToDateTest(String name) {
        super(name);
    }

    public void setUp() {
        configureProject("src/etc/testcases/taskdefs/uptodate.xml");
    }

    public void tearDown() {
        executeTarget("tearDown");
    }

    public void testFilesetUpToDate() {
        expectPropertySet("testFilesetUpToDate", "foo");
    }

    public void testFilesetOutOfDate() {
        expectPropertyUnset("testFilesetOutOfDate", "foo");
    }

    public void testRCUpToDate() {
        expectPropertySet("testRCUpToDate", "foo");
    }

    public void testRCOutOfDate() {
        expectPropertyUnset("testRCOutOfDate", "foo");
    }
}
