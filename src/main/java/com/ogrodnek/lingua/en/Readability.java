package com.ogrodnek.lingua.en;

import com.ogrodnek.lingua.en.Fathom.Stats;

/**
 * <p>
 * Common indices of readability.
 * </p>
 * 
 * <p>
 * Ported from perl's Lingua::EN::Fathom by Kim Ryan.
 * </p>
 * 
 * <p>
 * <b>COPYRIGHT</b> Distributed under the same terms as Perl.
 * </p>
 * 
 * @author Kim Ryan
 * @author Larry Ogrodnek &lt;ogrodnek@gmail.com&gt;
 * 
 * @version $Revision: 1.2 $ $Date: 2007-10-22 01:28:51 $
 */
public final class Readability {
  private Readability() { }

  /**
   * Returns the Fog index.
   * 
   * <p>
   * ( words_per_sentence + percent_complex_words ) * 0.4
   * </p>
   * 
   * <p>
   * The Fog index, developed by Robert Gunning, is a well known and simple
   * formula for measuring readability. The index indicates the number of years
   * of formal education a reader of average intelligence would need to
   * understand the text on the first reading.
   * </p>
   * 
   * <ul>
   * <li>18 unreadable</li>
   * <li>14 difficult</li>
   * <li>12 ideal</li>
   * <li>10 acceptable</li>
   * <li>8 childish</li>
   * </ul>
   * 
   * @param stats
   * @return Fog index.
   */
  public static float calcFog(final Stats stats) {
    return (wordsPerSentence(stats) + percentComplexWords(stats)) * 0.4f;
  }

  /**
   * Returns the Flesch reading ease score.
   * 
   * <p>
   * 206.835 - (1.015 * words_per_sentence) - (84.6 * syllables_per_word)
   * </p>
   * 
   * <p>
   * This score rates text on a 100 point scale. The higher the score, the
   * easier it is to understand the text. A score of 60 to 70 is considered to
   * be optimal.
   * </p>
   * 
   * @param stats
   * @return Flesch reading ease score.
   */
  public static float calcFlesch(final Stats stats) {
    return 206.835f - (1.015f * wordsPerSentence(stats)) - (84.6f * syllablesPerWords(stats));
  }

  /**
   * Returns the Flesch-Kincaid grade level score for the analysed text file or
   * block.
   * 
   * <p>
   * (11.8 * syllables_per_word) + (0.39 * words_per_sentence) - 15.59;
   * </p>
   * 
   * <p>
   * This score rates text on U.S. grade school level. So a score of 8.0 means
   * that the document can be understood by an eighth grader. A score of 7.0 to
   * 8.0 is considered to be optimal.
   * </p>
   * 
   * @param stats
   * @return Flesch-Kincaid score.
   */
  public static float calcKincaid(final Stats stats) {
    return (11.8f * syllablesPerWords(stats)) + (0.39f * wordsPerSentence(stats)) - 15.59f;
  }

  public static float wordsPerSentence(final Stats stats) {
    return ((float) stats.getNumWords()) / stats.getNumSentences();
  }

  public static float percentComplexWords(final Stats stats) {
    return (((float) stats.getNumComplexWords()) / stats.getNumWords()) * 100;
  }

  public static float syllablesPerWords(final Stats stats) {
    return ((float) stats.getNumSyllables()) / stats.getNumWords();
  }
}
