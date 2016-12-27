/*
 *   Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.handler.synapse;

import org.apache.synapse.AbstractSynapseHandler;
import org.apache.synapse.MessageContext;
import org.slf4j.MDC;

import java.util.UUID;

public class MessageIDHandler extends AbstractSynapseHandler {

    private static final String __MESSAGE_ID__ = "__MESSAGE_ID__";
    private static final String LOG_KEY = "messageId";

    public boolean handleRequestInFlow(MessageContext messageContext) {
        String messageId = UUID.randomUUID().toString();
        messageContext.setProperty(__MESSAGE_ID__, messageId);
        MDC.put(LOG_KEY, messageId + " - Request Flow");
        return true;
    }

    public boolean handleRequestOutFlow(MessageContext messageContext) {
        return true;
    }

    public boolean handleResponseInFlow(MessageContext messageContext) {
        MDC.put(LOG_KEY, String.valueOf(messageContext.getProperty(__MESSAGE_ID__)) + " - Response Flow");
        return true;
    }

    public boolean handleResponseOutFlow(MessageContext messageContext) {
        return true;
    }
}
