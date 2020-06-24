/*-
 * ============LICENSE_START=======================================================
 *  Copyright (C) 2020 Nordix Foundation.
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * ============LICENSE_END=========================================================
 */

package org.onap.so.bpmn.infrastructure.service.level;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.onap.so.client.exception.ExceptionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for Service level upgrade Execution, it should be extended for service level upgrade tasks.
 */
public abstract class AbstractServiceLevelPreparable {

    protected static final String WORKFLOW_TO_INVOKE = "healthCheckWorkflow";
    protected static final String GENERIC_PNF_HEALTH_CHECK_WORKFLOW = "GenericPnfHealthCheck";
    protected static final String GENERIC_PNF_SOFTWARE_UPGRADE_WORKFLOW = "GenericPnfSoftwareUpgrade";
    protected static final String RESOURCE_TYPE = "RESOURCE_TYPE";
    protected static final int ERROR_CODE = 601;

    // TODO This value needs to be updated once vnf health check workflow is available
    protected static final String GENERIC_VNF_HEALTH_CHECK_WORKFLOW = "GenericVNFHealthCheck";

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractServiceLevelPreparable.class);

    @Autowired
    protected ExceptionBuilder exceptionBuilder;

    /**
     * This method fetches workflow names to be invoked based on the controller scope .
     *
     * @param scope Controller scope
     * @return String value of Workflow name
     */
    protected abstract String fetchWorkflowUsingScope(DelegateExecution execution, final String scope);

    /**
     * This method validates the execution parameters to be passed for health check workflow.
     *
     * @param execution Delegate execution obj
     * @param scope Controller scope * Throws workflow exception if validation fails
     */
    protected void validateParamsWithScope(DelegateExecution execution, final String scope, List<String> params)
            throws Exception {
        List<String> invalidVariables = new ArrayList<>();
        for (String param : params) {
            if (!execution.hasVariable(param) || execution.getVariable(param) == null
                    || String.valueOf(execution.getVariable(param)).isEmpty()) {
                invalidVariables.add(param);
            }
        }
        if (invalidVariables.size() > 0) {
            LOG.error("Validation error for the {} health check attributes: {}", scope, invalidVariables);
            exceptionBuilder.buildAndThrowWorkflowException(execution, ERROR_CODE,
                    "Validation of health check workflow parameters failed for the scope: " + scope);
        }

    }

}
