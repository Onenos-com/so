/*
 * ============LICENSE_START======================================================= ONAP : SO
 * ================================================================================ Copyright (C) 2018 AT&T Intellectual
 * Property. All rights reserved. ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * ============LICENSE_END=========================================================
 */

package org.onap.so.bpmn.core;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MissingInjectedFiledExceptionTest {

    private MissingInjectedFieldException missingInjectedFieldException;

    @Test
    public void test() {
        String fieldName = "anyFieldName";
        String taskName = "anyTask";
        String info = "missing required field";
        String expectedMessage1 = taskName + " injected field '" + fieldName + "' is bad: " + info;
        missingInjectedFieldException = new MissingInjectedFieldException(fieldName, taskName);
        assertEquals(expectedMessage1, missingInjectedFieldException.getMessage());
    }
}
