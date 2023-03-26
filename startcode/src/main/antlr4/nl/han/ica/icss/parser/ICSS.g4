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
LOWER_IDENT: [a-z] [a-z0-9\-]*;
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_]*;

//All whitespace is skipped
WS: [ \t\r\n]+ -> skip;

//Comments and line comments are skipped
COMMENT : '/*' (COMMENT|.)*? '*/' -> skip;
LINE_COMMENT  : '//' .*? '\n' -> skip;

//
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
stylesheet: varAssignment* styleRule* EOF;
styleRule: selector OPEN_BRACE styleBody CLOSE_BRACE;
declaration: propertyName COLON expression SEMICOLON;
propertyName: LOWER_IDENT;

varAssignment: varReference ASSIGNMENT_OPERATOR expression+ SEMICOLON;

ifStmt: IF BOX_BRACKET_OPEN (varReference | boolLit) BOX_BRACKET_CLOSE OPEN_BRACE styleBody CLOSE_BRACE elseStmt?;
elseStmt: ELSE OPEN_BRACE styleBody CLOSE_BRACE;

expression: literal | expression (MUL) expression | expression (PLUS | MIN) expression;

boolLit: TRUE | FALSE;
colorLit: COLOR;
percentLit: PERCENTAGE;
pixelLit: PIXELSIZE;
scalarLit: SCALAR;
varReference: CAPITAL_IDENT;
literal: boolLit | colorLit | percentLit | pixelLit | scalarLit | varReference;

classSel: CLASS_IDENT;
tagSel: LOWER_IDENT;
idSel: ID_IDENT | COLOR;
selector: (tagSel | classSel | idSel) (COMMA selector)*;

styleBody: (declaration | ifStmt | varAssignment)*;