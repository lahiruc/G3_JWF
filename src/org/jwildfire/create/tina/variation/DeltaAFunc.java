/*
  JWildfire - an image and animation processor written in Java 
  Copyright (C) 1995-2011 Andreas Maschke

  This is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser 
  General Public License as published by the Free Software Foundation; either version 2.1 of the 
  License, or (at your option) any later version.
 
  This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License along with this software; 
  if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
  02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jwildfire.create.tina.variation;

import org.jwildfire.base.mathlib.DoubleWrapperWF;
import org.jwildfire.create.tina.base.XForm;
import org.jwildfire.create.tina.base.XYZPoint;

import static org.jwildfire.base.mathlib.MathLib.*;

public class DeltaAFunc extends SimpleVariationFunc {
  private static final long serialVersionUID = 1L;

  private DoubleWrapperWF sina = new DoubleWrapperWF();
  private DoubleWrapperWF cosa = new DoubleWrapperWF();

  @Override
  public void transform(FlameTransformationContext pContext, XForm pXForm, XYZPoint pAffineTP, XYZPoint pVarTP, double pAmount) {
    // deltaA my Michael Faber, http://michaelfaber.deviantart.com/art/The-Lost-Variations-258913970 */
    double avgr = pAmount * (sqrt(sqr(pAffineTP.y) + sqr(pAffineTP.x + 1.0)) / sqrt(sqr(pAffineTP.y) + sqr(pAffineTP.x - 1.0)));
    double avga = (atan2(pAffineTP.y, pAffineTP.x - 1.0) - atan2(pAffineTP.y, pAffineTP.x + 1.0)) / 2.0;
    sinAndCos(avga, sina, cosa);

    pVarTP.x += avgr * cosa.value;
    pVarTP.y += avgr * sina.value;
    if (pContext.isPreserveZCoordinate()) {
      pVarTP.z += pAmount * pAffineTP.z;
    }
  }

  @Override
  public String getName() {
    return "deltaA";
  }

}
