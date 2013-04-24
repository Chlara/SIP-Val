/*== SIP-Val ==================================================================================
The SIP-Val application is used for validate Submission Information Package (SIP).
Copyright (C) 2011-2013 Claire R�thlisberger (KOST-CECO), Daniel Ludin (BEDAG AG)
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

package ch.kostceco.bento.sipval.service.impl;

import java.io.File;
import ch.kostceco.bento.sipval.exception.SystemException;
import ch.kostceco.bento.sipval.logging.Logger;
import ch.kostceco.bento.sipval.service.SiardValService;
import ch.kostceco.bento.sipval.service.TextResourceService;
import ch.kostceco.bento.sipval.util.StreamGobbler;
import ch.kostceco.bento.sipval.util.Util;

/**
 * Dieser Service stellt die Schnittstelle zur SIARD-Val Software dar.
 * 
 * @author Rc Claire R�thlisberger-Jourdan, KOST-CECO
 */
public class SiardValServiceImpl implements SiardValService
{

	private static final Logger	LOGGER	= new Logger( SiardValServiceImpl.class );

	private TextResourceService	textResourceService;

	public TextResourceService getTextResourceService()
	{
		return textResourceService;
	}

	public void setTextResourceService( TextResourceService textResourceService )
	{
		this.textResourceService = textResourceService;
	}

	@Override
	public String executeSiardVal( String pathToSiardValExe,
			String pathToInputFile, String pathToOutput, String nameOfSip )
			throws SystemException
	{
		File report;
		// Pfad zum Programm SIARD-Val
		File siardvalExe = new File( pathToSiardValExe );
		// Pfad zur SIARD-Datei
		File input = new File( pathToInputFile );
		File output = new File( pathToOutput );
		StringBuffer command = new StringBuffer( "java -jar " + siardvalExe
				+ " " );

		command.append( pathToInputFile );
		command.append( " " );
		command.append( output.getAbsolutePath() );

		try {

			Runtime rt = Runtime.getRuntime();

			Process proc = rt.exec( command.toString() );

			// Fehleroutput holen
			StreamGobbler errorGobbler = new StreamGobbler(
					proc.getErrorStream(), "ERROR" );

			// Output holen
			StreamGobbler outputGobbler = new StreamGobbler(
					proc.getInputStream(), "OUTPUT" );

			Util.switchOffConsole();

			// Threads starten
			errorGobbler.start();
			outputGobbler.start();

			// Warte, bis wget fertig ist
			proc.waitFor();

			Util.switchOnConsole();

			// Der Name des generierten Reports lautet per default
			// Dateinamen.Extension.validationlog.log
			// und es gibt keine M�glichkeit, dies zu �bersteuern.
			String log = new String( input.getName() + ".validationlog.log" );

			report = new File( pathToOutput, log );
			File newReport = new File( pathToOutput, nameOfSip
					+ ".siardval.log" );

			// falls das File bereits existiert, z.B. von einem vorhergehenden
			// Durchlauf, l�schen wir es
			if ( newReport.exists() ) {
				newReport.delete();
			}

			boolean renameOk = report.renameTo( newReport );
			if ( !renameOk ) {
				throw new SystemException(
						"Der Report konnte nicht umbenannt werden." );
			}
			report = newReport;

		} catch ( Exception e ) {
			LOGGER.logDebug( "SIARD-Val Service failed: " + e.getMessage() );
			throw new SystemException( e.toString() );
		}
		return report.getAbsolutePath();
	}

	@Override
	public String getPathToInputFile()
	{
		return null;
	}

	@Override
	public String getPathToSiardValExe()
	{
		return null;
	}

	@Override
	public void setPathToInputFile( String pathToInputFile )
	{

	}

	@Override
	public void setPathToSiardValExe( String pathToSiardValExe )
	{

	}

}
