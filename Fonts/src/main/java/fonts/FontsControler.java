/*
* Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*	 http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package fonts;

import java.math.BigDecimal;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * @author Guy Korland
 */
public class FontsControler {
	
	private static final BigDecimal MIN_FONT_SIZE = BigDecimal.ONE;

	private static final BigDecimal INCREMENT = new BigDecimal("0.1");

	final private static FontsControler controler = new FontsControler();
	
	final private ScopedPreferenceStore store = new ScopedPreferenceStore( new InstanceScope(), "org.eclipse.ui.workbench" );
	
	private FontsControler(){}

	public static FontsControler getFontsControler(){
		return controler;
	}
	
	public synchronized void increase(){

		String font = store.getString(JFaceResources.TEXT_FONT);
		String[] split = font.split("\\|");
		BigDecimal fontSize = new BigDecimal(split[2]).add(INCREMENT);

		split[2] = fontSize.toString();
		StringBuilder builder = new StringBuilder(split[0]);
		for(int i=1; i<split.length ; ++i){
			builder.append('|').append(split[i]);
		}
		store.setValue(JFaceResources.TEXT_FONT, builder.toString());

	}

	public synchronized void decrease(){
		String font = store.getString(JFaceResources.TEXT_FONT);
		String[] split = font.split("\\|");
		BigDecimal fontSize = new BigDecimal(split[2]).subtract(INCREMENT);
		if(fontSize.compareTo(MIN_FONT_SIZE) < 0)
			fontSize = MIN_FONT_SIZE;

		split[2] = fontSize.toString();
		StringBuilder builder = new StringBuilder(split[0]);
		for(int i=1; i<split.length ; ++i){
			builder.append('|').append(split[i]);
		}
		store.setValue(JFaceResources.TEXT_FONT, builder.toString());
	}
}
