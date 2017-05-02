/*-
 * ============LICENSE_START=======================================================
 * OPENECOMP - MSO
 * ================================================================================
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved.
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
package org.openecomp.mso.adapters.workflowmessage;

import org.openecomp.mso.logger.MessageEnum;
import org.openecomp.mso.logger.MsoLogger;
import org.openecomp.mso.properties.MsoJavaProperties;
import org.openecomp.mso.properties.MsoPropertiesException;
import org.openecomp.mso.properties.MsoPropertiesFactory;

/**
 * Static methods to access Workflow Message Adapter properties.
 */
public final class WMAdapterProperties {
	private static final MsoPropertiesFactory MSO_PROPERTIES_FACTORY = new MsoPropertiesFactory();
	private static final MsoLogger LOGGER = MsoLogger.getMsoLogger(MsoLogger.Catalog.RA);

	/**
	 * Gets the value of a Workflow Message Adapter property.
	 * @param key the property key
	 * @param defaultValue the default value to use if the property does not
	 *        exist or if an error occurs
	 */
	public static String getProperty(String key, String defaultValue) {
		MsoJavaProperties properties;

		try {
			properties = MSO_PROPERTIES_FACTORY.getMsoJavaProperties(WMAdapterConstants.MSO_PROPERTIES_ID);
		} catch (MsoPropertiesException e) {
			LOGGER.error (MessageEnum.NO_PROPERTIES,
				"Unknown. MSO Properties ID not found in cache: " + WMAdapterConstants.MSO_PROPERTIES_ID,
				"WorkflowMessageAdatper", "", MsoLogger.ErrorCode.DataError,
				"Exception - MSO Properties ID not found in cache", e);
			return defaultValue;
		}

		String value = properties.getProperty(key, defaultValue);
		LOGGER.debug("Config read for " + WMAdapterConstants.MSO_PROPERTIES_ID
			+ " - key:" + key + " value:" + value);
		return value;
	}

	/**
	 * Gets the value of an Workflow Message Adapter property.
	 * @param key the property key
	 * @param defaultValue the default value to use if the property does not
	 *        exist or if an error occurs
	 */
	public static String getEncryptedProperty(String key, String defaultValue, String encryptionKey) {
		MsoJavaProperties properties;

		try {
			properties = MSO_PROPERTIES_FACTORY.getMsoJavaProperties(WMAdapterConstants.MSO_PROPERTIES_ID);
		} catch (MsoPropertiesException e) {
			LOGGER.error (MessageEnum.NO_PROPERTIES,
				"Unknown. MSO Properties ID not found in cache: " + WMAdapterConstants.MSO_PROPERTIES_ID,
				"WorkflowMessageAdatper", "", MsoLogger.ErrorCode.DataError,
				"Exception - MSO Properties ID not found in cache", e);
			return defaultValue;
		}

		String value = properties.getEncryptedProperty(key, defaultValue, encryptionKey);
		LOGGER.debug("Config read for " + WMAdapterConstants.MSO_PROPERTIES_ID + " - key:" + key);
		return value;
	}

	/**
	 * Instantiation is not allowed.
	 */
	private WMAdapterProperties() {
	}
}