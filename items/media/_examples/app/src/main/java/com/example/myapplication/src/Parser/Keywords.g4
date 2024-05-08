grammar Keywords;

@header {
  package com.example.myapplication.src.Parser;
}

tokens { NOUN, VERB }

start : text EOF;

text : (WORD | PUNCTUATION)+;

WORD : NOUN | VERB | ARTICLE | ADJECTIVE | ADVERB | CONJUNCTION | PREPOSITION | PRONOUN;

NOUN : (LOWERCASE | UPPERCASE)+ (NOUN_SUFFIX)?;
fragment NOUN_SUFFIX : 's' | 'es' | 'ies';

VERB : (LOWERCASE | UPPERCASE)+ (VERB_SUFFIX)?;
fragment VERB_SUFFIX : 's' | 'es' | 'ed' | 'ing';

ARTICLE : 'a' | 'an' | 'the';

ADJECTIVE : (LOWERCASE | UPPERCASE)+;

ADVERB : (LOWERCASE | UPPERCASE)+ ADVERB_SUFFIX;
fragment ADVERB_SUFFIX : 'ly';

CONJUNCTION : 'and' | 'but' | 'or' | 'yet' | 'so' | 'for' | 'nor';

PREPOSITION : 'in' | 'on' | 'at' | 'of' | 'with' | 'to' | 'for' | 'from' | 'by';

PRONOUN : 'I' | 'you' | 'he' | 'she' | 'it' | 'we' | 'they' | 'me' | 'him' | 'her' | 'us' | 'them';

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;
PUNCTUATION : [,.!?;:'"[\]{}()<>%#$\-+=_] | '\\s' | '/' | '\\\\';

WS  : [ \t\r\n]+ -> skip;