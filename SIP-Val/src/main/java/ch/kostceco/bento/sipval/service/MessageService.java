/*== SIP-Val ==================================================================================
The SIP-Val application is used for validate Submission Information Package (SIP).
Copyright (C) 2011 Claire R�thlisberger (KOST-CECO), Daniel Ludin (BEDAG AG)
$Id: MessageService.java 14 2011-07-21 07:07:28Z u2044 $
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

package ch.kostceco.bento.sipval.service;


/**
 * Interface f�r den Message Stack Service.
 * @author razm Daniel Ludin, Bedag AG @version 0.2.0
 */
public interface MessageService extends Service {
    
    final static String ERROR = "0";
    final static String FATAL = "1";
    final static String INFO = "2";
    final static String WARN = "3";
    final static String DEBUG = "4";

    void logInfo(String message);
    void logDebug(String message);
    void logWarning(String message);
    void logError(String message);
    void logFatal(String message);
    void clear();
    void print();
}
