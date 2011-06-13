Introduction
Measures the readability of English text.

This package is pretty much a straight port of two Perl packages into Java:

Lingua::EN::Fathom by Kim Ryan.
Lingua::EN::Syllable by Greg Fast.
This package allows you to generate common readability indices from English text.

Fog Index
( words_per_sentence + percent_complex_words ) * 0.4

The Fog index, developed by Robert Gunning, indicates the number of years of formal education a reader would need to understand the text on the first reading.

Flesch reading ease level
206.835 - (1.015 * words_per_sentence) - (84.6 * syllables_per_word)

The Flesch reading ease test rates text on a 100 point scale. The higher the score, the easier it is to understand the text. A score of 60 to 70 is considered to be optimal.

Flesch-Kincaid grade level score
(11.8 * syllables_per_word) + (0.39 * words_per_sentence) - 15.59

The Flesch-Kincaid grade level translates the 100 point scale to a U.S. grade school level. So a score of 8.0 means that the document can be understood by an eighth grader. A score of 7.0 to 8.0 is considered to be optimal.

Released under the same license as Perl.