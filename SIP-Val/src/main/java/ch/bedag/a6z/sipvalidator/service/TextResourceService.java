package ch.bedag.a6z.sipvalidator.service;

import java.util.Locale;

/**
 * 
 * Service Interface f�r Text Ressourcen.
 * 
 * @author razm Daniel Ludin, Bedag AG @version 2.0
 *
 */
public interface TextResourceService extends Service {
    /**
     * Ermitteln eines ResourceValues anhand eines Keys. Es wird die Locale aus
     * dem UserContext verwendet.
     * 
     * Gleichzeitig wird ein MessageFormat an das Ergebnis angewendet und die
     * Platzhalter aus der Resource mit den Werten aus aValues ersetzt.
     * 
     * @param aKey
     *            der Resourcenschl�ssel
     * @param values
     *            die Werte f�r die Platzhalter
     * @return das formatierte Ergebnis
     */
    String getText(String aKey, Object... values);

    /**
     * Ermitteln eines ResourceValues anhand eines Keys.
     * 
     * Gleichzeitig wird ein MessageFormat an das Ergebnis angewendet und die
     * Platzhalter aus der Resource mit den Werten aus aValues ersetzt.
     * 
     * @param locale
     *            zu verwendende Locale
     * @param aKey
     *            der Resourcenschl�ssel
     * @param values
     *            die Werte f�r die Platzhalter
     * @return das formatierte Ergebnis
     */
    String getText(Locale locale, String aKey, Object... values);
}
