/*== SIP-Val ==================================================================================
The SIP-Val application is used for validate Submission Information Package (SIP).
Copyright (C) 2011-2012 Claire Röthlisberger (KOST-CECO), Daniel Ludin (BEDAG AG)
-----------------------------------------------------------------------------------------------
SIP-Val is a development of the KOST-CECO. All rights rest with the KOST-CECO. 
This application is free software: you can redistribute it and/or modify it under the 
terms of the GNU General Public License as published by the Free Software Foundation, 
either version 3 of the License, or (at your option) any later version. 
BEDAG AG and Daniel Ludin hereby disclaims all copyright interest in the program 
SIP-Val v0.2.0 written by Daniel Ludin (BEDAG AG). Switzerland, 1 March 2011.
This application is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
See the follow GNU General Public License for more details.
You should have received a copy of the GNU General Public License along with this program; 
if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, 
Boston, MA 02110-1301 USA or see <http://www.gnu.org/licenses/>.
==============================================================================================*/

package ch.kostceco.bento.sipval.validation.module1;

import java.io.File;

import ch.kostceco.bento.sipval.exception.module1.Validation1eSipTypeException;
import ch.kostceco.bento.sipval.validation.ValidationModule;

/**
 * Validierungsschritt 1e Der SIP Typ wird ermittelt und angezeigt
 * (metadata.xml)//ablieferungstyp: GEVER oder FILE
 * 
 * @author razm Daniel Ludin, Bedag AG @version 0.2.0
 */

public interface Validation1eSipTypeModule extends ValidationModule
{

	public boolean validate( File sipDatei )
			throws Validation1eSipTypeException;

}
