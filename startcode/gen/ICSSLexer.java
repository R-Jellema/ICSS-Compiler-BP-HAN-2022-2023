// Generated from java-escape by ANTLR 4.11.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, BOX_BRACKET_OPEN=3, BOX_BRACKET_CLOSE=4, TRUE=5, FALSE=6, 
		PIXELSIZE=7, PERCENTAGE=8, SCALAR=9, COLOR=10, ID_IDENT=11, CLASS_IDENT=12, 
		LOWER_IDENT=13, CAPITAL_IDENT=14, WS=15, OPEN_BRACE=16, CLOSE_BRACE=17, 
		SEMICOLON=18, COLON=19, PLUS=20, MIN=21, MUL=22, ASSIGNMENT_OPERATOR=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", "FALSE", 
			"PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'['", "']'", "'true'", "'false'", null, null, 
			null, null, null, null, null, null, null, "'{'", "'}'", "';'", "':'", 
			"'+'", "'-'", "'*'", "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", 
			"FALSE", "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COLON", "PLUS", "MIN", "MUL", "ASSIGNMENT_OPERATOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ICSSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0017\u008c\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0004\u0006H\b\u0006"+
		"\u000b\u0006\f\u0006I\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0004\u0007P\b\u0007\u000b\u0007\f\u0007Q\u0001\u0007\u0001\u0007\u0001"+
		"\b\u0004\bW\b\b\u000b\b\f\bX\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0004\ne\b\n\u000b\n\f\nf\u0001\u000b"+
		"\u0001\u000b\u0004\u000bk\b\u000b\u000b\u000b\f\u000bl\u0001\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0004\u000ev\b\u000e\u000b"+
		"\u000e\f\u000ew\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0000\u0000\u0017\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017\u0001\u0000\u0007\u0001\u0000"+
		"09\u0002\u000009af\u0003\u0000--09az\u0001\u0000az\u0001\u0000AZ\u0004"+
		"\u000009AZ__az\u0003\u0000\t\n\r\r  \u0091\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u0001/\u0001\u0000\u0000\u0000\u00032"+
		"\u0001\u0000\u0000\u0000\u00057\u0001\u0000\u0000\u0000\u00079\u0001\u0000"+
		"\u0000\u0000\t;\u0001\u0000\u0000\u0000\u000b@\u0001\u0000\u0000\u0000"+
		"\rG\u0001\u0000\u0000\u0000\u000fO\u0001\u0000\u0000\u0000\u0011V\u0001"+
		"\u0000\u0000\u0000\u0013Z\u0001\u0000\u0000\u0000\u0015b\u0001\u0000\u0000"+
		"\u0000\u0017h\u0001\u0000\u0000\u0000\u0019n\u0001\u0000\u0000\u0000\u001b"+
		"q\u0001\u0000\u0000\u0000\u001du\u0001\u0000\u0000\u0000\u001f{\u0001"+
		"\u0000\u0000\u0000!}\u0001\u0000\u0000\u0000#\u007f\u0001\u0000\u0000"+
		"\u0000%\u0081\u0001\u0000\u0000\u0000\'\u0083\u0001\u0000\u0000\u0000"+
		")\u0085\u0001\u0000\u0000\u0000+\u0087\u0001\u0000\u0000\u0000-\u0089"+
		"\u0001\u0000\u0000\u0000/0\u0005i\u0000\u000001\u0005f\u0000\u00001\u0002"+
		"\u0001\u0000\u0000\u000023\u0005e\u0000\u000034\u0005l\u0000\u000045\u0005"+
		"s\u0000\u000056\u0005e\u0000\u00006\u0004\u0001\u0000\u0000\u000078\u0005"+
		"[\u0000\u00008\u0006\u0001\u0000\u0000\u00009:\u0005]\u0000\u0000:\b\u0001"+
		"\u0000\u0000\u0000;<\u0005t\u0000\u0000<=\u0005r\u0000\u0000=>\u0005u"+
		"\u0000\u0000>?\u0005e\u0000\u0000?\n\u0001\u0000\u0000\u0000@A\u0005f"+
		"\u0000\u0000AB\u0005a\u0000\u0000BC\u0005l\u0000\u0000CD\u0005s\u0000"+
		"\u0000DE\u0005e\u0000\u0000E\f\u0001\u0000\u0000\u0000FH\u0007\u0000\u0000"+
		"\u0000GF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IG\u0001\u0000"+
		"\u0000\u0000IJ\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KL\u0005"+
		"p\u0000\u0000LM\u0005x\u0000\u0000M\u000e\u0001\u0000\u0000\u0000NP\u0007"+
		"\u0000\u0000\u0000ON\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000"+
		"QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000"+
		"\u0000ST\u0005%\u0000\u0000T\u0010\u0001\u0000\u0000\u0000UW\u0007\u0000"+
		"\u0000\u0000VU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XV\u0001"+
		"\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y\u0012\u0001\u0000\u0000"+
		"\u0000Z[\u0005#\u0000\u0000[\\\u0007\u0001\u0000\u0000\\]\u0007\u0001"+
		"\u0000\u0000]^\u0007\u0001\u0000\u0000^_\u0007\u0001\u0000\u0000_`\u0007"+
		"\u0001\u0000\u0000`a\u0007\u0001\u0000\u0000a\u0014\u0001\u0000\u0000"+
		"\u0000bd\u0005#\u0000\u0000ce\u0007\u0002\u0000\u0000dc\u0001\u0000\u0000"+
		"\u0000ef\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000"+
		"\u0000\u0000g\u0016\u0001\u0000\u0000\u0000hj\u0005.\u0000\u0000ik\u0007"+
		"\u0002\u0000\u0000ji\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000"+
		"lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000m\u0018\u0001\u0000"+
		"\u0000\u0000no\u0007\u0003\u0000\u0000op\u0007\u0002\u0000\u0000p\u001a"+
		"\u0001\u0000\u0000\u0000qr\u0007\u0004\u0000\u0000rs\u0007\u0005\u0000"+
		"\u0000s\u001c\u0001\u0000\u0000\u0000tv\u0007\u0006\u0000\u0000ut\u0001"+
		"\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000"+
		"wx\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000yz\u0006\u000e\u0000"+
		"\u0000z\u001e\u0001\u0000\u0000\u0000{|\u0005{\u0000\u0000| \u0001\u0000"+
		"\u0000\u0000}~\u0005}\u0000\u0000~\"\u0001\u0000\u0000\u0000\u007f\u0080"+
		"\u0005;\u0000\u0000\u0080$\u0001\u0000\u0000\u0000\u0081\u0082\u0005:"+
		"\u0000\u0000\u0082&\u0001\u0000\u0000\u0000\u0083\u0084\u0005+\u0000\u0000"+
		"\u0084(\u0001\u0000\u0000\u0000\u0085\u0086\u0005-\u0000\u0000\u0086*"+
		"\u0001\u0000\u0000\u0000\u0087\u0088\u0005*\u0000\u0000\u0088,\u0001\u0000"+
		"\u0000\u0000\u0089\u008a\u0005:\u0000\u0000\u008a\u008b\u0005=\u0000\u0000"+
		"\u008b.\u0001\u0000\u0000\u0000\u0007\u0000IQXflw\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}