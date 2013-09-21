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