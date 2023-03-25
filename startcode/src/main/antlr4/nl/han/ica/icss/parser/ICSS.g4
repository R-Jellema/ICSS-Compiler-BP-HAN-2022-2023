grammar ICSS;

//--- LEXER: ---

// IF support:
IF: 'if';
ELSE: 'else';
BOX_BRACKET_OPEN: '[';
BOX_BRACKET_CLOSE: ']';

//Literals
TRUE: 'true';
FALSE: 'false';
PIXELSIZE: [0-9]+ 'px';
PERCENTAGE: [0-9]+ '%';
SCALAR: [0-9]+;

//Color value takes precedence over id idents
COLOR: '#' [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f] [0-9a-f];

//Specific identifiers for id's and css classes
ID_IDENT: '#' [a-z0-9-]+;
CLASS_IDENT: '.' [a-z0-9-]+;

//General identifiers
LOWER_IDENT: [a-z] [a-z0-9-];
CAPITAL_IDENT: [A-Z] [A-Za-z0-9_];

//All whitespace is ignored
WS: [ \t\r\n]+ -> skip;

//
OPEN_BRACE: '{';
CLOSE_BRACE: '}';
SEMICOLON: ';';
COLON: ':';
PLUS: '+';
MIN: '-';
MUL: '*';
ASSIGNMENT_OPERATOR: ':=';

//--- PARSER: ---
icss: varAssign* styleRule* EOF;
styleRule: selector '{' ruleBody '}';
property: propertyName COLON expression SEMICOLON;
propertyName: LOWER_IDENT;

varAssign: varReference ASSIGNMENT_OPERATOR expression+ SEMICOLON;

ifStmt: IF BOX_BRACKET_OPEN (varReference | boolLit) BOX_BRACKET_CLOSE '{' ruleBody '}' elseStmt?;
elseStmt: ELSE '{' ruleBody '}';

expression: literal | expression (MUL | DIV) expression | expression (PLUS | MIN) expression;

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

ruleBody: (property | ifStmt | varAssign)*;