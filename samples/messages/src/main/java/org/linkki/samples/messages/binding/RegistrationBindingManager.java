/*
 * Copyright Faktor Zehn AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.linkki.samples.messages.binding;

import java.util.function.Consumer;

import org.linkki.core.binding.DefaultBindingManager;
import org.linkki.core.message.MessageList;
import org.linkki.samples.messages.pmo.RegistrationValidationService;

// tag::binding-manager-update-messages[]
public class RegistrationBindingManager extends DefaultBindingManager {

	private final Consumer<MessageList> displayMessagesConsumer;

	public RegistrationBindingManager(RegistrationValidationService registrationValidationService,
			Consumer<MessageList> displayMessagesConsumer) {
		super(registrationValidationService);
		this.displayMessagesConsumer = displayMessagesConsumer;
	}

	@Override
	protected void updateMessages(MessageList messages) {
		super.updateMessages(messages);
		this.displayMessagesConsumer.accept(messages);
	}
}
// end::binding-manager-update-messages[]