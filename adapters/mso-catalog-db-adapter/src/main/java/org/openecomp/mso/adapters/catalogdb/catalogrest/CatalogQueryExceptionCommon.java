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
package org.openecomp.mso.adapters.catalogdb.catalogrest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

public abstract class CatalogQueryExceptionCommon {
	private String messageId;

	public CatalogQueryExceptionCommon() { messageId = null; }
	public CatalogQueryExceptionCommon(String messageId) { this.messageId = messageId; }

	public String getMessageId() { return messageId; }
	public void setMessageId(String messageId) { this.messageId = messageId; }

	public String toJsonString() {
		try {
			String jsonString = null;
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationConfig.Feature.WRAP_ROOT_VALUE);
			jsonString = mapper.writeValueAsString(this);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String toXmlString() {
		try {
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			JAXBContext context = JAXBContext.newInstance(this.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //pretty print XML
			marshaller.marshal(this, bs);
			return bs.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
