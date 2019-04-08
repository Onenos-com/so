/*-
 * ============LICENSE_START=======================================================
 * ONAP - SO
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
 * Copyright (C) 2017 Huawei Technologies Co., Ltd. All rights reserved.
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
 * ============LICENSE_END=========================================================
 */

package org.onap.so.apihandlerinfra.validation;

import org.onap.so.exceptions.ValidationException;
import org.onap.so.serviceinstancebeans.ModelInfo;
import org.onap.so.serviceinstancebeans.RequestInfo;

public class RequestScopeValidation implements ValidationRule {
    @Override
    public ValidationInformation validate(ValidationInformation info) throws ValidationException {
        ModelInfo modelInfo = info.getSir().getRequestDetails().getModelInfo();
        RequestInfo requestInfo = info.getSir().getRequestDetails().getRequestInfo();
        String requestScope;

        if (modelInfo == null) {
            throw new ValidationException("model-info");
        }
        if (requestInfo == null) {
            throw new ValidationException("requestInfo");
        }
        info.setRequestInfo(requestInfo);
        if (modelInfo.getModelType() == null) {
            throw new ValidationException("modelType");
        }
        requestScope = info.getSir().getRequestDetails().getModelInfo().getModelType().name();
        info.setRequestScope(requestScope);
        return info;
    }
}
