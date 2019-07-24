package pt.iscte.paddle.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJavaliLexer extends Lexer {
    public static final int T__50=50;
    public static final int RULE_BOOLEAN=9;
    public static final int T__19=19;
    public static final int RULE_PRIMITIVE_VALUE=5;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int RULE_ID=6;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=10;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_SL_COMMENT=11;
    public static final int T__37=37;
    public static final int RULE_DOUBLE=8;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ML_COMMENT_DOC=4;
    public static final int RULE_WS=12;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int RULE_INTEGER=7;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators

    public InternalJavaliLexer() {;} 
    public InternalJavaliLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalJavaliLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalJavali.g"; }

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:11:7: ( 'static' )
            // InternalJavali.g:11:9: 'static'
            {
            match("static"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:12:7: ( 'final' )
            // InternalJavali.g:12:9: 'final'
            {
            match("final"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:13:7: ( '=' )
            // InternalJavali.g:13:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:14:7: ( ';' )
            // InternalJavali.g:14:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:15:7: ( 'class' )
            // InternalJavali.g:15:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:16:7: ( '{' )
            // InternalJavali.g:16:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:17:7: ( '}' )
            // InternalJavali.g:17:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:18:7: ( 'void' )
            // InternalJavali.g:18:9: 'void'
            {
            match("void"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:19:7: ( '(' )
            // InternalJavali.g:19:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:20:7: ( ',' )
            // InternalJavali.g:20:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:21:7: ( ')' )
            // InternalJavali.g:21:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:22:7: ( 'return' )
            // InternalJavali.g:22:9: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:23:7: ( 'break' )
            // InternalJavali.g:23:9: 'break'
            {
            match("break"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:24:7: ( 'continue' )
            // InternalJavali.g:24:9: 'continue'
            {
            match("continue"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:25:7: ( 'if' )
            // InternalJavali.g:25:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:26:7: ( 'else' )
            // InternalJavali.g:26:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:27:7: ( 'while' )
            // InternalJavali.g:27:9: 'while'
            {
            match("while"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:28:7: ( 'for' )
            // InternalJavali.g:28:9: 'for'
            {
            match("for"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:29:7: ( 'do' )
            // InternalJavali.g:29:9: 'do'
            {
            match("do"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:30:7: ( '++' )
            // InternalJavali.g:30:9: '++'
            {
            match("++"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:31:7: ( '--' )
            // InternalJavali.g:31:9: '--'
            {
            match("--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:32:7: ( '||' )
            // InternalJavali.g:32:9: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:33:7: ( '^' )
            // InternalJavali.g:33:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:34:7: ( '&&' )
            // InternalJavali.g:34:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:35:7: ( '==' )
            // InternalJavali.g:35:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:36:7: ( '!=' )
            // InternalJavali.g:36:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:37:7: ( '<' )
            // InternalJavali.g:37:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:38:7: ( '<=' )
            // InternalJavali.g:38:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:39:7: ( '>' )
            // InternalJavali.g:39:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:40:7: ( '>=' )
            // InternalJavali.g:40:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:41:7: ( '+' )
            // InternalJavali.g:41:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:42:7: ( '-' )
            // InternalJavali.g:42:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:43:7: ( '*' )
            // InternalJavali.g:43:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:44:7: ( '/' )
            // InternalJavali.g:44:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:45:7: ( '%' )
            // InternalJavali.g:45:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:46:7: ( '!' )
            // InternalJavali.g:46:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:47:7: ( 'null' )
            // InternalJavali.g:47:9: 'null'
            {
            match("null"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:48:7: ( '[' )
            // InternalJavali.g:48:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:49:7: ( ']' )
            // InternalJavali.g:49:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:50:7: ( '.' )
            // InternalJavali.g:50:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:51:7: ( 'new' )
            // InternalJavali.g:51:9: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "RULE_INTEGER"
    public final void mRULE_INTEGER() throws RecognitionException {
        try {
            // InternalJavali.g:2772:23: ( ( '-' )? ( '0' .. '9' )+ )
            // InternalJavali.g:2772:25: ( '-' )? ( '0' .. '9' )+
            {
            // InternalJavali.g:2772:25: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalJavali.g:2772:25: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // InternalJavali.g:2772:30: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalJavali.g:2772:31: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_INTEGER"

    // $ANTLR start "RULE_DOUBLE"
    public final void mRULE_DOUBLE() throws RecognitionException {
        try {
            // InternalJavali.g:2774:22: ( RULE_INTEGER '.' ( '0' .. '9' )+ )
            // InternalJavali.g:2774:24: RULE_INTEGER '.' ( '0' .. '9' )+
            {
            mRULE_INTEGER(); 
            match('.'); 
            // InternalJavali.g:2774:41: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalJavali.g:2774:42: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_DOUBLE"

    // $ANTLR start "RULE_BOOLEAN"
    public final void mRULE_BOOLEAN() throws RecognitionException {
        try {
            // InternalJavali.g:2776:23: ( ( 'true' | 'false' ) )
            // InternalJavali.g:2776:25: ( 'true' | 'false' )
            {
            // InternalJavali.g:2776:25: ( 'true' | 'false' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='t') ) {
                alt4=1;
            }
            else if ( (LA4_0=='f') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalJavali.g:2776:26: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // InternalJavali.g:2776:33: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_BOOLEAN"

    // $ANTLR start "RULE_PRIMITIVE_VALUE"
    public final void mRULE_PRIMITIVE_VALUE() throws RecognitionException {
        try {
            int _type = RULE_PRIMITIVE_VALUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:2778:22: ( ( RULE_INTEGER | RULE_DOUBLE | RULE_BOOLEAN ) )
            // InternalJavali.g:2778:24: ( RULE_INTEGER | RULE_DOUBLE | RULE_BOOLEAN )
            {
            // InternalJavali.g:2778:24: ( RULE_INTEGER | RULE_DOUBLE | RULE_BOOLEAN )
            int alt5=3;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // InternalJavali.g:2778:25: RULE_INTEGER
                    {
                    mRULE_INTEGER(); 

                    }
                    break;
                case 2 :
                    // InternalJavali.g:2778:38: RULE_DOUBLE
                    {
                    mRULE_DOUBLE(); 

                    }
                    break;
                case 3 :
                    // InternalJavali.g:2778:50: RULE_BOOLEAN
                    {
                    mRULE_BOOLEAN(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_PRIMITIVE_VALUE"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:2780:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalJavali.g:2780:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalJavali.g:2780:35: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')||(LA6_0>='A' && LA6_0<='Z')||LA6_0=='_'||(LA6_0>='a' && LA6_0<='z')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalJavali.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_ML_COMMENT_DOC"
    public final void mRULE_ML_COMMENT_DOC() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT_DOC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:2782:21: ( '/**' ( options {greedy=false; } : . )* '*/' )
            // InternalJavali.g:2782:23: '/**' ( options {greedy=false; } : . )* '*/'
            {
            match("/**"); 

            // InternalJavali.g:2782:29: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalJavali.g:2782:57: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT_DOC"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:2784:17: ( '/*' ~ ( '*' ) ( options {greedy=false; } : . )* '*/' )
            // InternalJavali.g:2784:19: '/*' ~ ( '*' ) ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            if ( (input.LA(1)>='\u0000' && input.LA(1)<=')')||(input.LA(1)>='+' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalJavali.g:2784:31: ( options {greedy=false; } : . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<='.')||(LA8_1>='0' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalJavali.g:2784:59: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:2786:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalJavali.g:2786:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalJavali.g:2786:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalJavali.g:2786:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // InternalJavali.g:2786:40: ( ( '\\r' )? '\\n' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\n'||LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalJavali.g:2786:41: ( '\\r' )? '\\n'
                    {
                    // InternalJavali.g:2786:41: ( '\\r' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\r') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // InternalJavali.g:2786:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalJavali.g:2788:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalJavali.g:2788:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalJavali.g:2788:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalJavali.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    public void mTokens() throws RecognitionException {
        // InternalJavali.g:1:8: ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | RULE_PRIMITIVE_VALUE | RULE_ID | RULE_ML_COMMENT_DOC | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS )
        int alt13=47;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // InternalJavali.g:1:10: T__13
                {
                mT__13(); 

                }
                break;
            case 2 :
                // InternalJavali.g:1:16: T__14
                {
                mT__14(); 

                }
                break;
            case 3 :
                // InternalJavali.g:1:22: T__15
                {
                mT__15(); 

                }
                break;
            case 4 :
                // InternalJavali.g:1:28: T__16
                {
                mT__16(); 

                }
                break;
            case 5 :
                // InternalJavali.g:1:34: T__17
                {
                mT__17(); 

                }
                break;
            case 6 :
                // InternalJavali.g:1:40: T__18
                {
                mT__18(); 

                }
                break;
            case 7 :
                // InternalJavali.g:1:46: T__19
                {
                mT__19(); 

                }
                break;
            case 8 :
                // InternalJavali.g:1:52: T__20
                {
                mT__20(); 

                }
                break;
            case 9 :
                // InternalJavali.g:1:58: T__21
                {
                mT__21(); 

                }
                break;
            case 10 :
                // InternalJavali.g:1:64: T__22
                {
                mT__22(); 

                }
                break;
            case 11 :
                // InternalJavali.g:1:70: T__23
                {
                mT__23(); 

                }
                break;
            case 12 :
                // InternalJavali.g:1:76: T__24
                {
                mT__24(); 

                }
                break;
            case 13 :
                // InternalJavali.g:1:82: T__25
                {
                mT__25(); 

                }
                break;
            case 14 :
                // InternalJavali.g:1:88: T__26
                {
                mT__26(); 

                }
                break;
            case 15 :
                // InternalJavali.g:1:94: T__27
                {
                mT__27(); 

                }
                break;
            case 16 :
                // InternalJavali.g:1:100: T__28
                {
                mT__28(); 

                }
                break;
            case 17 :
                // InternalJavali.g:1:106: T__29
                {
                mT__29(); 

                }
                break;
            case 18 :
                // InternalJavali.g:1:112: T__30
                {
                mT__30(); 

                }
                break;
            case 19 :
                // InternalJavali.g:1:118: T__31
                {
                mT__31(); 

                }
                break;
            case 20 :
                // InternalJavali.g:1:124: T__32
                {
                mT__32(); 

                }
                break;
            case 21 :
                // InternalJavali.g:1:130: T__33
                {
                mT__33(); 

                }
                break;
            case 22 :
                // InternalJavali.g:1:136: T__34
                {
                mT__34(); 

                }
                break;
            case 23 :
                // InternalJavali.g:1:142: T__35
                {
                mT__35(); 

                }
                break;
            case 24 :
                // InternalJavali.g:1:148: T__36
                {
                mT__36(); 

                }
                break;
            case 25 :
                // InternalJavali.g:1:154: T__37
                {
                mT__37(); 

                }
                break;
            case 26 :
                // InternalJavali.g:1:160: T__38
                {
                mT__38(); 

                }
                break;
            case 27 :
                // InternalJavali.g:1:166: T__39
                {
                mT__39(); 

                }
                break;
            case 28 :
                // InternalJavali.g:1:172: T__40
                {
                mT__40(); 

                }
                break;
            case 29 :
                // InternalJavali.g:1:178: T__41
                {
                mT__41(); 

                }
                break;
            case 30 :
                // InternalJavali.g:1:184: T__42
                {
                mT__42(); 

                }
                break;
            case 31 :
                // InternalJavali.g:1:190: T__43
                {
                mT__43(); 

                }
                break;
            case 32 :
                // InternalJavali.g:1:196: T__44
                {
                mT__44(); 

                }
                break;
            case 33 :
                // InternalJavali.g:1:202: T__45
                {
                mT__45(); 

                }
                break;
            case 34 :
                // InternalJavali.g:1:208: T__46
                {
                mT__46(); 

                }
                break;
            case 35 :
                // InternalJavali.g:1:214: T__47
                {
                mT__47(); 

                }
                break;
            case 36 :
                // InternalJavali.g:1:220: T__48
                {
                mT__48(); 

                }
                break;
            case 37 :
                // InternalJavali.g:1:226: T__49
                {
                mT__49(); 

                }
                break;
            case 38 :
                // InternalJavali.g:1:232: T__50
                {
                mT__50(); 

                }
                break;
            case 39 :
                // InternalJavali.g:1:238: T__51
                {
                mT__51(); 

                }
                break;
            case 40 :
                // InternalJavali.g:1:244: T__52
                {
                mT__52(); 

                }
                break;
            case 41 :
                // InternalJavali.g:1:250: T__53
                {
                mT__53(); 

                }
                break;
            case 42 :
                // InternalJavali.g:1:256: RULE_PRIMITIVE_VALUE
                {
                mRULE_PRIMITIVE_VALUE(); 

                }
                break;
            case 43 :
                // InternalJavali.g:1:277: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 44 :
                // InternalJavali.g:1:285: RULE_ML_COMMENT_DOC
                {
                mRULE_ML_COMMENT_DOC(); 

                }
                break;
            case 45 :
                // InternalJavali.g:1:305: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 46 :
                // InternalJavali.g:1:321: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 47 :
                // InternalJavali.g:1:337: RULE_WS
                {
                mRULE_WS(); 

                }
                break;

        }

    }


    protected DFA5 dfa5 = new DFA5(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA5_eotS =
        "\2\uffff\1\4\3\uffff";
    static final String DFA5_eofS =
        "\6\uffff";
    static final String DFA5_minS =
        "\1\55\1\60\1\56\3\uffff";
    static final String DFA5_maxS =
        "\1\164\2\71\3\uffff";
    static final String DFA5_acceptS =
        "\3\uffff\1\3\1\1\1\2";
    static final String DFA5_specialS =
        "\6\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1\2\uffff\12\2\54\uffff\1\3\15\uffff\1\3",
            "\12\2",
            "\1\5\1\uffff\12\2",
            "",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "2778:24: ( RULE_INTEGER | RULE_DOUBLE | RULE_BOOLEAN )";
        }
    }
    static final String DFA13_eotS =
        "\1\uffff\2\43\1\52\1\uffff\1\43\2\uffff\1\43\3\uffff\6\43\1\65\1\67\3\uffff\1\71\1\73\1\75\1\uffff\1\100\1\uffff\1\43\4\uffff\1\43\2\uffff\4\43\2\uffff\5\43\1\115\2\43\1\120\15\uffff\5\43\1\130\6\43\1\uffff\2\43\3\uffff\1\43\1\142\3\43\1\uffff\3\43\1\151\2\43\1\154\1\43\1\156\1\uffff\1\41\1\43\1\160\1\41\1\161\1\43\1\uffff\1\43\1\164\1\uffff\1\165\1\uffff\1\166\2\uffff\1\43\1\170\3\uffff\1\43\1\uffff\1\172\1\uffff";
    static final String DFA13_eofS =
        "\173\uffff";
    static final String DFA13_minS =
        "\1\11\1\164\1\141\1\75\1\uffff\1\154\2\uffff\1\157\3\uffff\1\145\1\162\1\146\1\154\1\150\1\157\1\53\1\55\3\uffff\3\75\1\uffff\1\52\1\uffff\1\145\4\uffff\1\162\2\uffff\1\141\1\156\1\162\1\154\2\uffff\1\141\1\156\1\151\1\164\1\145\1\60\1\163\1\151\1\60\12\uffff\1\0\2\uffff\1\154\1\167\1\165\1\164\1\141\1\60\2\163\1\164\1\144\1\165\1\141\1\uffff\1\145\1\154\3\uffff\1\154\1\60\1\145\1\151\1\154\1\uffff\1\145\1\163\1\151\1\60\1\162\1\153\1\60\1\145\1\60\1\uffff\1\60\1\143\3\60\1\156\1\uffff\1\156\1\60\1\uffff\1\60\1\uffff\1\60\2\uffff\1\165\1\60\3\uffff\1\145\1\uffff\1\60\1\uffff";
    static final String DFA13_maxS =
        "\1\175\1\164\1\157\1\75\1\uffff\1\157\2\uffff\1\157\3\uffff\1\145\1\162\1\146\1\154\1\150\1\157\1\53\1\71\3\uffff\3\75\1\uffff\1\57\1\uffff\1\165\4\uffff\1\162\2\uffff\1\141\1\156\1\162\1\154\2\uffff\1\141\1\156\1\151\1\164\1\145\1\172\1\163\1\151\1\172\12\uffff\1\uffff\2\uffff\1\154\1\167\1\165\1\164\1\141\1\172\2\163\1\164\1\144\1\165\1\141\1\uffff\1\145\1\154\3\uffff\1\154\1\172\1\145\1\151\1\154\1\uffff\1\145\1\163\1\151\1\172\1\162\1\153\1\172\1\145\1\172\1\uffff\1\172\1\143\3\172\1\156\1\uffff\1\156\1\172\1\uffff\1\172\1\uffff\1\172\2\uffff\1\165\1\172\3\uffff\1\145\1\uffff\1\172\1\uffff";
    static final String DFA13_acceptS =
        "\4\uffff\1\4\1\uffff\1\6\1\7\1\uffff\1\11\1\12\1\13\10\uffff\1\26\1\27\1\30\3\uffff\1\41\1\uffff\1\43\1\uffff\1\46\1\47\1\50\1\52\1\uffff\1\53\1\57\4\uffff\1\31\1\3\11\uffff\1\24\1\37\1\25\1\40\1\32\1\44\1\34\1\33\1\36\1\35\1\uffff\1\56\1\42\14\uffff\1\17\2\uffff\1\23\1\54\1\55\5\uffff\1\22\11\uffff\1\51\6\uffff\1\10\2\uffff\1\20\1\uffff\1\45\1\uffff\1\2\1\5\2\uffff\1\15\1\21\1\1\1\uffff\1\14\1\uffff\1\16";
    static final String DFA13_specialS =
        "\76\uffff\1\0\74\uffff}>";
    static final String[] DFA13_transitionS = {
            "\2\44\2\uffff\1\44\22\uffff\1\44\1\27\3\uffff\1\34\1\26\1\uffff\1\11\1\13\1\32\1\22\1\12\1\23\1\40\1\33\12\41\1\uffff\1\4\1\30\1\3\1\31\2\uffff\32\43\1\36\1\uffff\1\37\1\25\1\43\1\uffff\1\43\1\15\1\5\1\21\1\17\1\2\2\43\1\16\4\43\1\35\3\43\1\14\1\1\1\42\1\43\1\10\1\20\3\43\1\6\1\24\1\7",
            "\1\45",
            "\1\50\7\uffff\1\46\5\uffff\1\47",
            "\1\51",
            "",
            "\1\53\2\uffff\1\54",
            "",
            "",
            "\1\55",
            "",
            "",
            "",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\66\2\uffff\12\41",
            "",
            "",
            "",
            "\1\70",
            "\1\72",
            "\1\74",
            "",
            "\1\76\4\uffff\1\77",
            "",
            "\1\102\17\uffff\1\101",
            "",
            "",
            "",
            "",
            "\1\103",
            "",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "",
            "",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\116",
            "\1\117",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\52\122\1\121\uffd5\122",
            "",
            "",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "",
            "\1\137",
            "\1\140",
            "",
            "",
            "",
            "\1\141",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\143",
            "\1\144",
            "\1\145",
            "",
            "\1\146",
            "\1\147",
            "\1\150",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\152",
            "\1\153",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\155",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\157",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "\1\162",
            "",
            "\1\163",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "",
            "\1\167",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            "",
            "",
            "",
            "\1\171",
            "",
            "\12\43\7\uffff\32\43\4\uffff\1\43\1\uffff\32\43",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | RULE_PRIMITIVE_VALUE | RULE_ID | RULE_ML_COMMENT_DOC | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_62 = input.LA(1);

                        s = -1;
                        if ( (LA13_62=='*') ) {s = 81;}

                        else if ( ((LA13_62>='\u0000' && LA13_62<=')')||(LA13_62>='+' && LA13_62<='\uFFFF')) ) {s = 82;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}