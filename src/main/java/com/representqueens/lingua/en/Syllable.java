/* $Id: Syllable.java,v 1.2 2007-10-22 01:28:51 larry Exp $ */
package com.representqueens.lingua.en;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Routine for estimating syllable count in words.
 * </p>
 * 
 * <p>
 * This is a straight port of perl's Lingua::EN::Syllable written by Greg Fast
 * (comments and all).
 * </p>
 * 
 * <p>
 * Note that it isn't entirely accurate... it fails (by one syllable) for about
 * 10-15% of my /usr/dict/words. The only way to get a 100% accurate count is to
 * do a dictionary lookup, so this is a small and fast alternative where
 * more-or-less accurate results will suffice, such as estimating the reading
 * level of a document.
 * </p>
 * 
 * <p>
 * I welcome pointers to more accurate algorithms, since this one is pretty
 * quick-and-dirty. This was designed for English (well, American at least)
 * words, but sometimes guesses well for other languages.
 * </p>
 * 
 * <p>
 * <b>Known Limitations</b>
 * </p>
 * 
 * <p>
 * Accuracy for words with non-alpha characters is somewhat undefined. In
 * general, punctuation characters, et al, should be trimmed off before handing
 * the word to syllable(), and hyphenated compounds should be broken into their
 * separate parts.
 * </p>
 * 
 * <p>
 * Syllables for all-digit words (eg, "1998"; some call them "numbers") are
 * often counted as the number of digits. A cooler solution would be converting
 * "1998" to "nineteen eighty eight" (or "one thousand nine hundred eighty
 * eight", or...), but that is left as an exercise for the reader.
 * </p>
 * 
 * <p>
 * Contractions are not well supported.
 * </p>
 * 
 * <p>
 * Compound words (like "lifeboat"), where the first word ends in a silent 'e'
 * are counted with an extra syllable.
 * </p>
 * 
 * <p>
 * <b>COPYRIGHT</b>
 * </p>
 * <p>
 * Distributed under the same terms as Perl.
 * </p>
 * 
 * @author Greg Fast &lt;gdf@imsa.edu&gt;
 * @author Larry Ogrodnek &lt;ogrodnek@gmail.com&gt;
 * @version $Revision: 1.2 $ $Date: 2007-10-22 01:28:51 $
 */
public class Syllable {
  /*
   * basic algortithm: each vowel-group indicates a syllable, except for: final
   * (silent) e 'ia' ind two syl @AddSyl and @SubSyl list regexps to massage the
   * basic count. Each match from @AddSyl adds 1 to the basic count, each
   * @SubSyl match -1 Keep in mind that when the regexps are checked, any final
   * 'e' will have been removed, and all '\'' will have been removed.
   */
  private static final Pattern[] SubSyl = new Pattern[] { Pattern.compile("cial"), Pattern.compile("tia"),
      Pattern.compile("cius"), Pattern.compile("cious"), Pattern.compile("giu"), // belgium!
      Pattern.compile("ion"), Pattern.compile("iou"), Pattern.compile("sia$"), Pattern.compile(".ely$"), // absolutely!
                                                                                                         // (but
                                                                                                         // not
                                                                                                         // ely!)
  };

  private static final Pattern[] AddSyl = new Pattern[] { Pattern.compile("ia"), Pattern.compile("riet"),
      Pattern.compile("dien"), Pattern.compile("iu"), Pattern.compile("io"), Pattern.compile("ii"),
      Pattern.compile("[aeiouym]bl$"), // -Vble, plus -mble
      Pattern.compile("[aeiou]{3}"), // agreeable
      Pattern.compile("^mc"), Pattern.compile("ism$"), // -isms
      Pattern.compile("([^aeiouy])\1l$"), // middle twiddle battle bottle, etc.
      Pattern.compile("[^l]lien"), // alien, salient [1]
      Pattern.compile("^coa[dglx]."), // [2]
      Pattern.compile("[^gq]ua[^auieo]"), // i think this fixes more than it
                                          // breaks
      Pattern.compile("dnt$"), // couldn't
  };

  // (comments refer to titan's /usr/dict/words)
  // [1] alien, salient, but not lien or ebbullient...
  // (those are the only 2 exceptions i found, there may be others)
  // [2] exception for 7 words:
  // coadjutor coagulable coagulate coalesce coalescent coalition coaxial

  /**
   * Get the syllable count for a word.
   * 
   * @param _word
   *          word
   * 
   * @return syllable count for word.
   */
  public static final int syllable(final String _word) {
    if (_word == null || "".equals(_word)) {
      return 0;
    }
    
    final String word = _word.toLowerCase().replaceAll("'", "").replaceAll("e$", "");    
    
    if (word.length() == 1) {
      return 1;
    }

    final String[] scrugg = word.split("[^aeiouy]+"); // '-' should be perhaps
                                                      // added?

    int syl = 0;

    // special cases
    for (final Pattern p : SubSyl) {
      final Matcher m = p.matcher(word);

      if (m.find()) {
        syl--;
      }
    }

    for (final Pattern p : AddSyl) {
      final Matcher m = p.matcher(word);

      if (m.find()) {
        syl++;
      }
    }

    // count vowel groupings
    if (scrugg.length > 0 && "".equals(scrugg[0])) {
      syl += scrugg.length - 1;
    } else {
      syl += scrugg.length;
    }

    if (syl == 0) {
      // got no vowels? ("the", "crwth")
      syl = 1;
    }

    return syl;
  }
}
