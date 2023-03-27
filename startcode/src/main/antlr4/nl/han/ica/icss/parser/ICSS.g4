grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';


//Literals
TRUE: 'TRUE' | 'true' | 'True';
FALSE: 'FALSE' | 'false' | 'False';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;


//Color value takes precedence over id idents
COLOR:  '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
CLASS_IDENT: '.' [a-zA-Z0-9\-_]+; // was [a-z0-9\-]. Nu kan een class .ClassSelector | .Class_Selector | .class-selector | .class_selector heten.
ID_IDENT: '#' [a-zA-Z0-9\-_]+; // was [a-z0-9\-]. Nu kan een identity #IdSelector | #Id_Selector | #id-selector | #id_selector | #IDSelector heten.

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9\-_]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

MULTIPLE_CLASS_IDENT: CLASS_IDENT*;
ID_CLASS_IDENT: ID_IDENT CLASS_IDENT*;
ELEMENT_CLASS_IDENT: LOWER_IDENT CLASS_IDENT | LOWER_IDENT CLASS_IDENT*;


//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//Comments and line comments are skipped
COMMENT_BLOCK : '/*' (COMMENT_BLOCK|.)*? '*/' -> skip;
LINE_COMMENT  : '//' .*? '\n' -> skip;

OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COMMA : ',';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';


//--- PARSER: ---
stylesheet: varAssign* styleRule* EOF;
styleRule: sel OPEN_BRACE styleBody CLOSE_BRACE;
decl: propertyName COLON expr SEMICOLON;
propertyName: LOWER_IDENT;

varAssign: varRef ASSIGNMENT_OPERATOR expr+ SEMICOLON;

ifStmt: IF BOX_BRACKET_OPEN (varRef | boolLit) BOX_BRACKET_CLOSE OPEN_BRACE styleBody CLOSE_BRACE elseStmt?;
elseStmt: ELSE OPEN_BRACE styleBody CLOSE_BRACE;

expr: literal | expr (MUL) expr | expr (PLUS | MIN) expr;

boolLit: TRUE | FALSE;
colorLit: COLOR;
percentLit: PERCENTAGE;
pixelLit: PIXELSIZE;
scalarLit: SCALAR;
varRef: CAPITAL_IDENT;
literal: boolLit | colorLit | percentLit | pixelLit | scalarLit | varRef;

classSel: CLASS_IDENT | MULTIPLE_CLASS_IDENT;
tagSel: LOWER_IDENT | ELEMENT_CLASS_IDENT;
idSel: (ID_IDENT | COLOR | ID_CLASS_IDENT);

sel: (tagSel | classSel | idSel) (COMMA sel)*;

styleBody: (decl | ifStmt | varAssign)*;