/**
 * Copyright 2011, 2013 Guy Korland, Jonas Berlin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fonts;

import java.math.BigDecimal;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

class Adjuster {
	@SuppressWarnings("deprecation")
	private static final ScopedPreferenceStore STORE = new ScopedPreferenceStore( new InstanceScope(), "org.eclipse.ui.workbench" );

	private final String[] split;

	public Adjuster() {
		String font = STORE.getString(JFaceResources.TEXT_FONT);
		split = font.split("\\|");
	}

	public BigDecimal get() {
		return new BigDecimal(split[2]);
	}

	public void set(BigDecimal size) {
		split[2] = size.toString();
	}

	public void persist() {
		StringBuilder builder = new StringBuilder(split[0]);
		for (int i = 1; i < split.length; ++i) {
			builder.append('|').append(split[i]);
		}
		STORE.setValue(JFaceResources.TEXT_FONT,
				builder.toString());
	}
}