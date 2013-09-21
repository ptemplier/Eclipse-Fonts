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

/**
 * @author Guy Korland
 */
public class FontsControler {
	
	private static final BigDecimal MIN_FONT_SIZE = BigDecimal.ONE;

	private static final BigDecimal INCREMENT = new BigDecimal("0.1");

	final private static FontsControler controler = new FontsControler();
	
	private FontsControler(){}

	public static FontsControler getFontsControler(){
		return controler;
	}
	
	public synchronized void increase(){
		Adjuster adj = new Adjuster();

		BigDecimal fontSize = adj.get().add(INCREMENT);
		adj.set(fontSize);

		adj.persist();
	}

	public synchronized void decrease(){
		Adjuster adj = new Adjuster();

		BigDecimal fontSize = adj.get().subtract(INCREMENT);
		if(fontSize.compareTo(MIN_FONT_SIZE) < 0)
			fontSize = MIN_FONT_SIZE;
		adj.set(fontSize);

		adj.persist();
	}
}
