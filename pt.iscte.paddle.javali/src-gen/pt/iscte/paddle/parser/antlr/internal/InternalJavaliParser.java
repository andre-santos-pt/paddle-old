package pt.iscte.paddle.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import pt.iscte.paddle.services.JavaliGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJavaliParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ML_COMMENT_DOC", "RULE_PRIMITIVE_VALUE", "RULE_ID", "RULE_INTEGER", "RULE_DOUBLE", "RULE_BOOLEAN", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'static'", "'final'", "'='", "';'", "'class'", "'{'", "'}'", "'void'", "'('", "','", "')'", "'return'", "'break'", "'continue'", "'if'", "'else'", "'while'", "'for'", "'do'", "'++'", "'--'", "'||'", "'^'", "'&&'", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'!'", "'null'", "'['", "']'", "'.'", "'new'"
    };
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


        public InternalJavaliParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalJavaliParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalJavaliParser.tokenNames; }
    public String getGrammarFileName() { return "InternalJavali.g"; }



     	private JavaliGrammarAccess grammarAccess;

        public InternalJavaliParser(TokenStream input, JavaliGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Module";
       	}

       	@Override
       	protected JavaliGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModule"
    // InternalJavali.g:64:1: entryRuleModule returns [EObject current=null] : iv_ruleModule= ruleModule EOF ;
    public final EObject entryRuleModule() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModule = null;


        try {
            // InternalJavali.g:64:47: (iv_ruleModule= ruleModule EOF )
            // InternalJavali.g:65:2: iv_ruleModule= ruleModule EOF
            {
             newCompositeNode(grammarAccess.getModuleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModule=ruleModule();

            state._fsp--;

             current =iv_ruleModule; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModule"


    // $ANTLR start "ruleModule"
    // InternalJavali.g:71:1: ruleModule returns [EObject current=null] : ( ( (lv_constants_0_0= ruleConstant ) ) | ( (lv_records_1_0= ruleRecord ) ) | ( (lv_procedures_2_0= ruleProcedure ) ) )* ;
    public final EObject ruleModule() throws RecognitionException {
        EObject current = null;

        EObject lv_constants_0_0 = null;

        EObject lv_records_1_0 = null;

        EObject lv_procedures_2_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:77:2: ( ( ( (lv_constants_0_0= ruleConstant ) ) | ( (lv_records_1_0= ruleRecord ) ) | ( (lv_procedures_2_0= ruleProcedure ) ) )* )
            // InternalJavali.g:78:2: ( ( (lv_constants_0_0= ruleConstant ) ) | ( (lv_records_1_0= ruleRecord ) ) | ( (lv_procedures_2_0= ruleProcedure ) ) )*
            {
            // InternalJavali.g:78:2: ( ( (lv_constants_0_0= ruleConstant ) ) | ( (lv_records_1_0= ruleRecord ) ) | ( (lv_procedures_2_0= ruleProcedure ) ) )*
            loop1:
            do {
                int alt1=4;
                switch ( input.LA(1) ) {
                case 13:
                    {
                    int LA1_2 = input.LA(2);

                    if ( (LA1_2==14) ) {
                        alt1=1;
                    }
                    else if ( (LA1_2==RULE_ID||LA1_2==20) ) {
                        alt1=3;
                    }


                    }
                    break;
                case 14:
                    {
                    alt1=1;
                    }
                    break;
                case 17:
                    {
                    alt1=2;
                    }
                    break;
                case RULE_ML_COMMENT_DOC:
                case RULE_ID:
                case 20:
                    {
                    alt1=3;
                    }
                    break;

                }

                switch (alt1) {
            	case 1 :
            	    // InternalJavali.g:79:3: ( (lv_constants_0_0= ruleConstant ) )
            	    {
            	    // InternalJavali.g:79:3: ( (lv_constants_0_0= ruleConstant ) )
            	    // InternalJavali.g:80:4: (lv_constants_0_0= ruleConstant )
            	    {
            	    // InternalJavali.g:80:4: (lv_constants_0_0= ruleConstant )
            	    // InternalJavali.g:81:5: lv_constants_0_0= ruleConstant
            	    {

            	    					newCompositeNode(grammarAccess.getModuleAccess().getConstantsConstantParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_constants_0_0=ruleConstant();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getModuleRule());
            	    					}
            	    					add(
            	    						current,
            	    						"constants",
            	    						lv_constants_0_0,
            	    						"pt.iscte.paddle.Javali.Constant");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalJavali.g:99:3: ( (lv_records_1_0= ruleRecord ) )
            	    {
            	    // InternalJavali.g:99:3: ( (lv_records_1_0= ruleRecord ) )
            	    // InternalJavali.g:100:4: (lv_records_1_0= ruleRecord )
            	    {
            	    // InternalJavali.g:100:4: (lv_records_1_0= ruleRecord )
            	    // InternalJavali.g:101:5: lv_records_1_0= ruleRecord
            	    {

            	    					newCompositeNode(grammarAccess.getModuleAccess().getRecordsRecordParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_records_1_0=ruleRecord();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getModuleRule());
            	    					}
            	    					add(
            	    						current,
            	    						"records",
            	    						lv_records_1_0,
            	    						"pt.iscte.paddle.Javali.Record");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }


            	    }
            	    break;
            	case 3 :
            	    // InternalJavali.g:119:3: ( (lv_procedures_2_0= ruleProcedure ) )
            	    {
            	    // InternalJavali.g:119:3: ( (lv_procedures_2_0= ruleProcedure ) )
            	    // InternalJavali.g:120:4: (lv_procedures_2_0= ruleProcedure )
            	    {
            	    // InternalJavali.g:120:4: (lv_procedures_2_0= ruleProcedure )
            	    // InternalJavali.g:121:5: lv_procedures_2_0= ruleProcedure
            	    {

            	    					newCompositeNode(grammarAccess.getModuleAccess().getProceduresProcedureParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_procedures_2_0=ruleProcedure();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getModuleRule());
            	    					}
            	    					add(
            	    						current,
            	    						"procedures",
            	    						lv_procedures_2_0,
            	    						"pt.iscte.paddle.Javali.Procedure");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModule"


    // $ANTLR start "entryRuleConstant"
    // InternalJavali.g:142:1: entryRuleConstant returns [EObject current=null] : iv_ruleConstant= ruleConstant EOF ;
    public final EObject entryRuleConstant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstant = null;


        try {
            // InternalJavali.g:142:49: (iv_ruleConstant= ruleConstant EOF )
            // InternalJavali.g:143:2: iv_ruleConstant= ruleConstant EOF
            {
             newCompositeNode(grammarAccess.getConstantRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstant=ruleConstant();

            state._fsp--;

             current =iv_ruleConstant; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstant"


    // $ANTLR start "ruleConstant"
    // InternalJavali.g:149:1: ruleConstant returns [EObject current=null] : ( ( (lv_static_0_0= 'static' ) )? otherlv_1= 'final' ( (lv_type_2_0= ruleType ) ) ( (lv_id_3_0= ruleIdentifier ) ) otherlv_4= '=' ( (lv_value_5_0= ruleLiteral ) ) otherlv_6= ';' ) ;
    public final EObject ruleConstant() throws RecognitionException {
        EObject current = null;

        Token lv_static_0_0=null;
        Token otherlv_1=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_type_2_0 = null;

        EObject lv_id_3_0 = null;

        EObject lv_value_5_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:155:2: ( ( ( (lv_static_0_0= 'static' ) )? otherlv_1= 'final' ( (lv_type_2_0= ruleType ) ) ( (lv_id_3_0= ruleIdentifier ) ) otherlv_4= '=' ( (lv_value_5_0= ruleLiteral ) ) otherlv_6= ';' ) )
            // InternalJavali.g:156:2: ( ( (lv_static_0_0= 'static' ) )? otherlv_1= 'final' ( (lv_type_2_0= ruleType ) ) ( (lv_id_3_0= ruleIdentifier ) ) otherlv_4= '=' ( (lv_value_5_0= ruleLiteral ) ) otherlv_6= ';' )
            {
            // InternalJavali.g:156:2: ( ( (lv_static_0_0= 'static' ) )? otherlv_1= 'final' ( (lv_type_2_0= ruleType ) ) ( (lv_id_3_0= ruleIdentifier ) ) otherlv_4= '=' ( (lv_value_5_0= ruleLiteral ) ) otherlv_6= ';' )
            // InternalJavali.g:157:3: ( (lv_static_0_0= 'static' ) )? otherlv_1= 'final' ( (lv_type_2_0= ruleType ) ) ( (lv_id_3_0= ruleIdentifier ) ) otherlv_4= '=' ( (lv_value_5_0= ruleLiteral ) ) otherlv_6= ';'
            {
            // InternalJavali.g:157:3: ( (lv_static_0_0= 'static' ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalJavali.g:158:4: (lv_static_0_0= 'static' )
                    {
                    // InternalJavali.g:158:4: (lv_static_0_0= 'static' )
                    // InternalJavali.g:159:5: lv_static_0_0= 'static'
                    {
                    lv_static_0_0=(Token)match(input,13,FOLLOW_4); 

                    					newLeafNode(lv_static_0_0, grammarAccess.getConstantAccess().getStaticStaticKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getConstantRule());
                    					}
                    					setWithLastConsumed(current, "static", true, "static");
                    				

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,14,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getConstantAccess().getFinalKeyword_1());
            		
            // InternalJavali.g:175:3: ( (lv_type_2_0= ruleType ) )
            // InternalJavali.g:176:4: (lv_type_2_0= ruleType )
            {
            // InternalJavali.g:176:4: (lv_type_2_0= ruleType )
            // InternalJavali.g:177:5: lv_type_2_0= ruleType
            {

            					newCompositeNode(grammarAccess.getConstantAccess().getTypeTypeParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_5);
            lv_type_2_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConstantRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_2_0,
            						"pt.iscte.paddle.Javali.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJavali.g:194:3: ( (lv_id_3_0= ruleIdentifier ) )
            // InternalJavali.g:195:4: (lv_id_3_0= ruleIdentifier )
            {
            // InternalJavali.g:195:4: (lv_id_3_0= ruleIdentifier )
            // InternalJavali.g:196:5: lv_id_3_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getConstantAccess().getIdIdentifierParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_6);
            lv_id_3_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConstantRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_3_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_7); 

            			newLeafNode(otherlv_4, grammarAccess.getConstantAccess().getEqualsSignKeyword_4());
            		
            // InternalJavali.g:217:3: ( (lv_value_5_0= ruleLiteral ) )
            // InternalJavali.g:218:4: (lv_value_5_0= ruleLiteral )
            {
            // InternalJavali.g:218:4: (lv_value_5_0= ruleLiteral )
            // InternalJavali.g:219:5: lv_value_5_0= ruleLiteral
            {

            					newCompositeNode(grammarAccess.getConstantAccess().getValueLiteralParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_8);
            lv_value_5_0=ruleLiteral();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConstantRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_5_0,
            						"pt.iscte.paddle.Javali.Literal");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getConstantAccess().getSemicolonKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstant"


    // $ANTLR start "entryRuleRecord"
    // InternalJavali.g:244:1: entryRuleRecord returns [EObject current=null] : iv_ruleRecord= ruleRecord EOF ;
    public final EObject entryRuleRecord() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRecord = null;


        try {
            // InternalJavali.g:244:47: (iv_ruleRecord= ruleRecord EOF )
            // InternalJavali.g:245:2: iv_ruleRecord= ruleRecord EOF
            {
             newCompositeNode(grammarAccess.getRecordRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRecord=ruleRecord();

            state._fsp--;

             current =iv_ruleRecord; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRecord"


    // $ANTLR start "ruleRecord"
    // InternalJavali.g:251:1: ruleRecord returns [EObject current=null] : (otherlv_0= 'class' ( (lv_id_1_0= ruleIdentifier ) ) otherlv_2= '{' ( ( (lv_fields_3_0= ruleVarDeclaration ) ) otherlv_4= ';' )* otherlv_5= '}' ) ;
    public final EObject ruleRecord() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        EObject lv_id_1_0 = null;

        EObject lv_fields_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:257:2: ( (otherlv_0= 'class' ( (lv_id_1_0= ruleIdentifier ) ) otherlv_2= '{' ( ( (lv_fields_3_0= ruleVarDeclaration ) ) otherlv_4= ';' )* otherlv_5= '}' ) )
            // InternalJavali.g:258:2: (otherlv_0= 'class' ( (lv_id_1_0= ruleIdentifier ) ) otherlv_2= '{' ( ( (lv_fields_3_0= ruleVarDeclaration ) ) otherlv_4= ';' )* otherlv_5= '}' )
            {
            // InternalJavali.g:258:2: (otherlv_0= 'class' ( (lv_id_1_0= ruleIdentifier ) ) otherlv_2= '{' ( ( (lv_fields_3_0= ruleVarDeclaration ) ) otherlv_4= ';' )* otherlv_5= '}' )
            // InternalJavali.g:259:3: otherlv_0= 'class' ( (lv_id_1_0= ruleIdentifier ) ) otherlv_2= '{' ( ( (lv_fields_3_0= ruleVarDeclaration ) ) otherlv_4= ';' )* otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,17,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getRecordAccess().getClassKeyword_0());
            		
            // InternalJavali.g:263:3: ( (lv_id_1_0= ruleIdentifier ) )
            // InternalJavali.g:264:4: (lv_id_1_0= ruleIdentifier )
            {
            // InternalJavali.g:264:4: (lv_id_1_0= ruleIdentifier )
            // InternalJavali.g:265:5: lv_id_1_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getRecordAccess().getIdIdentifierParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_9);
            lv_id_1_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRecordRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_1_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,18,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getRecordAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalJavali.g:286:3: ( ( (lv_fields_3_0= ruleVarDeclaration ) ) otherlv_4= ';' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalJavali.g:287:4: ( (lv_fields_3_0= ruleVarDeclaration ) ) otherlv_4= ';'
            	    {
            	    // InternalJavali.g:287:4: ( (lv_fields_3_0= ruleVarDeclaration ) )
            	    // InternalJavali.g:288:5: (lv_fields_3_0= ruleVarDeclaration )
            	    {
            	    // InternalJavali.g:288:5: (lv_fields_3_0= ruleVarDeclaration )
            	    // InternalJavali.g:289:6: lv_fields_3_0= ruleVarDeclaration
            	    {

            	    						newCompositeNode(grammarAccess.getRecordAccess().getFieldsVarDeclarationParserRuleCall_3_0_0());
            	    					
            	    pushFollow(FOLLOW_8);
            	    lv_fields_3_0=ruleVarDeclaration();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getRecordRule());
            	    						}
            	    						add(
            	    							current,
            	    							"fields",
            	    							lv_fields_3_0,
            	    							"pt.iscte.paddle.Javali.VarDeclaration");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_4=(Token)match(input,16,FOLLOW_10); 

            	    				newLeafNode(otherlv_4, grammarAccess.getRecordAccess().getSemicolonKeyword_3_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_5=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getRecordAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRecord"


    // $ANTLR start "entryRuleProcedure"
    // InternalJavali.g:319:1: entryRuleProcedure returns [EObject current=null] : iv_ruleProcedure= ruleProcedure EOF ;
    public final EObject entryRuleProcedure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcedure = null;


        try {
            // InternalJavali.g:319:50: (iv_ruleProcedure= ruleProcedure EOF )
            // InternalJavali.g:320:2: iv_ruleProcedure= ruleProcedure EOF
            {
             newCompositeNode(grammarAccess.getProcedureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcedure=ruleProcedure();

            state._fsp--;

             current =iv_ruleProcedure; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcedure"


    // $ANTLR start "ruleProcedure"
    // InternalJavali.g:326:1: ruleProcedure returns [EObject current=null] : ( ( (lv_comment_0_0= RULE_ML_COMMENT_DOC ) )? ( (lv_static_1_0= 'static' ) )? ( ( (lv_retType_2_0= ruleType ) ) | ( (lv_void_3_0= 'void' ) ) ) ( (lv_id_4_0= ruleIdentifier ) ) otherlv_5= '(' ( ( (lv_params_6_0= ruleVarDeclaration ) ) (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )* )? otherlv_9= ')' ( (lv_body_10_0= ruleBlock ) ) ) ;
    public final EObject ruleProcedure() throws RecognitionException {
        EObject current = null;

        Token lv_comment_0_0=null;
        Token lv_static_1_0=null;
        Token lv_void_3_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        EObject lv_retType_2_0 = null;

        EObject lv_id_4_0 = null;

        EObject lv_params_6_0 = null;

        EObject lv_params_8_0 = null;

        EObject lv_body_10_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:332:2: ( ( ( (lv_comment_0_0= RULE_ML_COMMENT_DOC ) )? ( (lv_static_1_0= 'static' ) )? ( ( (lv_retType_2_0= ruleType ) ) | ( (lv_void_3_0= 'void' ) ) ) ( (lv_id_4_0= ruleIdentifier ) ) otherlv_5= '(' ( ( (lv_params_6_0= ruleVarDeclaration ) ) (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )* )? otherlv_9= ')' ( (lv_body_10_0= ruleBlock ) ) ) )
            // InternalJavali.g:333:2: ( ( (lv_comment_0_0= RULE_ML_COMMENT_DOC ) )? ( (lv_static_1_0= 'static' ) )? ( ( (lv_retType_2_0= ruleType ) ) | ( (lv_void_3_0= 'void' ) ) ) ( (lv_id_4_0= ruleIdentifier ) ) otherlv_5= '(' ( ( (lv_params_6_0= ruleVarDeclaration ) ) (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )* )? otherlv_9= ')' ( (lv_body_10_0= ruleBlock ) ) )
            {
            // InternalJavali.g:333:2: ( ( (lv_comment_0_0= RULE_ML_COMMENT_DOC ) )? ( (lv_static_1_0= 'static' ) )? ( ( (lv_retType_2_0= ruleType ) ) | ( (lv_void_3_0= 'void' ) ) ) ( (lv_id_4_0= ruleIdentifier ) ) otherlv_5= '(' ( ( (lv_params_6_0= ruleVarDeclaration ) ) (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )* )? otherlv_9= ')' ( (lv_body_10_0= ruleBlock ) ) )
            // InternalJavali.g:334:3: ( (lv_comment_0_0= RULE_ML_COMMENT_DOC ) )? ( (lv_static_1_0= 'static' ) )? ( ( (lv_retType_2_0= ruleType ) ) | ( (lv_void_3_0= 'void' ) ) ) ( (lv_id_4_0= ruleIdentifier ) ) otherlv_5= '(' ( ( (lv_params_6_0= ruleVarDeclaration ) ) (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )* )? otherlv_9= ')' ( (lv_body_10_0= ruleBlock ) )
            {
            // InternalJavali.g:334:3: ( (lv_comment_0_0= RULE_ML_COMMENT_DOC ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ML_COMMENT_DOC) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalJavali.g:335:4: (lv_comment_0_0= RULE_ML_COMMENT_DOC )
                    {
                    // InternalJavali.g:335:4: (lv_comment_0_0= RULE_ML_COMMENT_DOC )
                    // InternalJavali.g:336:5: lv_comment_0_0= RULE_ML_COMMENT_DOC
                    {
                    lv_comment_0_0=(Token)match(input,RULE_ML_COMMENT_DOC,FOLLOW_11); 

                    					newLeafNode(lv_comment_0_0, grammarAccess.getProcedureAccess().getCommentML_COMMENT_DOCTerminalRuleCall_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getProcedureRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"comment",
                    						lv_comment_0_0,
                    						"pt.iscte.paddle.Javali.ML_COMMENT_DOC");
                    				

                    }


                    }
                    break;

            }

            // InternalJavali.g:352:3: ( (lv_static_1_0= 'static' ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==13) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalJavali.g:353:4: (lv_static_1_0= 'static' )
                    {
                    // InternalJavali.g:353:4: (lv_static_1_0= 'static' )
                    // InternalJavali.g:354:5: lv_static_1_0= 'static'
                    {
                    lv_static_1_0=(Token)match(input,13,FOLLOW_12); 

                    					newLeafNode(lv_static_1_0, grammarAccess.getProcedureAccess().getStaticStaticKeyword_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getProcedureRule());
                    					}
                    					setWithLastConsumed(current, "static", true, "static");
                    				

                    }


                    }
                    break;

            }

            // InternalJavali.g:366:3: ( ( (lv_retType_2_0= ruleType ) ) | ( (lv_void_3_0= 'void' ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            else if ( (LA6_0==20) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalJavali.g:367:4: ( (lv_retType_2_0= ruleType ) )
                    {
                    // InternalJavali.g:367:4: ( (lv_retType_2_0= ruleType ) )
                    // InternalJavali.g:368:5: (lv_retType_2_0= ruleType )
                    {
                    // InternalJavali.g:368:5: (lv_retType_2_0= ruleType )
                    // InternalJavali.g:369:6: lv_retType_2_0= ruleType
                    {

                    						newCompositeNode(grammarAccess.getProcedureAccess().getRetTypeTypeParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_5);
                    lv_retType_2_0=ruleType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getProcedureRule());
                    						}
                    						set(
                    							current,
                    							"retType",
                    							lv_retType_2_0,
                    							"pt.iscte.paddle.Javali.Type");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:387:4: ( (lv_void_3_0= 'void' ) )
                    {
                    // InternalJavali.g:387:4: ( (lv_void_3_0= 'void' ) )
                    // InternalJavali.g:388:5: (lv_void_3_0= 'void' )
                    {
                    // InternalJavali.g:388:5: (lv_void_3_0= 'void' )
                    // InternalJavali.g:389:6: lv_void_3_0= 'void'
                    {
                    lv_void_3_0=(Token)match(input,20,FOLLOW_5); 

                    						newLeafNode(lv_void_3_0, grammarAccess.getProcedureAccess().getVoidVoidKeyword_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProcedureRule());
                    						}
                    						setWithLastConsumed(current, "void", true, "void");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalJavali.g:402:3: ( (lv_id_4_0= ruleIdentifier ) )
            // InternalJavali.g:403:4: (lv_id_4_0= ruleIdentifier )
            {
            // InternalJavali.g:403:4: (lv_id_4_0= ruleIdentifier )
            // InternalJavali.g:404:5: lv_id_4_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getProcedureAccess().getIdIdentifierParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_13);
            lv_id_4_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcedureRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_4_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,21,FOLLOW_14); 

            			newLeafNode(otherlv_5, grammarAccess.getProcedureAccess().getLeftParenthesisKeyword_4());
            		
            // InternalJavali.g:425:3: ( ( (lv_params_6_0= ruleVarDeclaration ) ) (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalJavali.g:426:4: ( (lv_params_6_0= ruleVarDeclaration ) ) (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )*
                    {
                    // InternalJavali.g:426:4: ( (lv_params_6_0= ruleVarDeclaration ) )
                    // InternalJavali.g:427:5: (lv_params_6_0= ruleVarDeclaration )
                    {
                    // InternalJavali.g:427:5: (lv_params_6_0= ruleVarDeclaration )
                    // InternalJavali.g:428:6: lv_params_6_0= ruleVarDeclaration
                    {

                    						newCompositeNode(grammarAccess.getProcedureAccess().getParamsVarDeclarationParserRuleCall_5_0_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_params_6_0=ruleVarDeclaration();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getProcedureRule());
                    						}
                    						add(
                    							current,
                    							"params",
                    							lv_params_6_0,
                    							"pt.iscte.paddle.Javali.VarDeclaration");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalJavali.g:445:4: (otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==22) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalJavali.g:446:5: otherlv_7= ',' ( (lv_params_8_0= ruleVarDeclaration ) )
                    	    {
                    	    otherlv_7=(Token)match(input,22,FOLLOW_5); 

                    	    					newLeafNode(otherlv_7, grammarAccess.getProcedureAccess().getCommaKeyword_5_1_0());
                    	    				
                    	    // InternalJavali.g:450:5: ( (lv_params_8_0= ruleVarDeclaration ) )
                    	    // InternalJavali.g:451:6: (lv_params_8_0= ruleVarDeclaration )
                    	    {
                    	    // InternalJavali.g:451:6: (lv_params_8_0= ruleVarDeclaration )
                    	    // InternalJavali.g:452:7: lv_params_8_0= ruleVarDeclaration
                    	    {

                    	    							newCompositeNode(grammarAccess.getProcedureAccess().getParamsVarDeclarationParserRuleCall_5_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_15);
                    	    lv_params_8_0=ruleVarDeclaration();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getProcedureRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"params",
                    	    								lv_params_8_0,
                    	    								"pt.iscte.paddle.Javali.VarDeclaration");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_9=(Token)match(input,23,FOLLOW_9); 

            			newLeafNode(otherlv_9, grammarAccess.getProcedureAccess().getRightParenthesisKeyword_6());
            		
            // InternalJavali.g:475:3: ( (lv_body_10_0= ruleBlock ) )
            // InternalJavali.g:476:4: (lv_body_10_0= ruleBlock )
            {
            // InternalJavali.g:476:4: (lv_body_10_0= ruleBlock )
            // InternalJavali.g:477:5: lv_body_10_0= ruleBlock
            {

            					newCompositeNode(grammarAccess.getProcedureAccess().getBodyBlockParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_2);
            lv_body_10_0=ruleBlock();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcedureRule());
            					}
            					set(
            						current,
            						"body",
            						lv_body_10_0,
            						"pt.iscte.paddle.Javali.Block");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcedure"


    // $ANTLR start "entryRuleBlock"
    // InternalJavali.g:498:1: entryRuleBlock returns [EObject current=null] : iv_ruleBlock= ruleBlock EOF ;
    public final EObject entryRuleBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlock = null;


        try {
            // InternalJavali.g:498:46: (iv_ruleBlock= ruleBlock EOF )
            // InternalJavali.g:499:2: iv_ruleBlock= ruleBlock EOF
            {
             newCompositeNode(grammarAccess.getBlockRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBlock=ruleBlock();

            state._fsp--;

             current =iv_ruleBlock; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBlock"


    // $ANTLR start "ruleBlock"
    // InternalJavali.g:505:1: ruleBlock returns [EObject current=null] : ( () otherlv_1= '{' ( (lv_statements_2_0= ruleStatement ) )* otherlv_3= '}' ) ;
    public final EObject ruleBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_statements_2_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:511:2: ( ( () otherlv_1= '{' ( (lv_statements_2_0= ruleStatement ) )* otherlv_3= '}' ) )
            // InternalJavali.g:512:2: ( () otherlv_1= '{' ( (lv_statements_2_0= ruleStatement ) )* otherlv_3= '}' )
            {
            // InternalJavali.g:512:2: ( () otherlv_1= '{' ( (lv_statements_2_0= ruleStatement ) )* otherlv_3= '}' )
            // InternalJavali.g:513:3: () otherlv_1= '{' ( (lv_statements_2_0= ruleStatement ) )* otherlv_3= '}'
            {
            // InternalJavali.g:513:3: ()
            // InternalJavali.g:514:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBlockAccess().getBlockAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,18,FOLLOW_16); 

            			newLeafNode(otherlv_1, grammarAccess.getBlockAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalJavali.g:524:3: ( (lv_statements_2_0= ruleStatement ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_ID||(LA9_0>=24 && LA9_0<=27)||(LA9_0>=29 && LA9_0<=31)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalJavali.g:525:4: (lv_statements_2_0= ruleStatement )
            	    {
            	    // InternalJavali.g:525:4: (lv_statements_2_0= ruleStatement )
            	    // InternalJavali.g:526:5: lv_statements_2_0= ruleStatement
            	    {

            	    					newCompositeNode(grammarAccess.getBlockAccess().getStatementsStatementParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_16);
            	    lv_statements_2_0=ruleStatement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getBlockRule());
            	    					}
            	    					add(
            	    						current,
            	    						"statements",
            	    						lv_statements_2_0,
            	    						"pt.iscte.paddle.Javali.Statement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            otherlv_3=(Token)match(input,19,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getBlockAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBlock"


    // $ANTLR start "entryRuleStatement"
    // InternalJavali.g:551:1: entryRuleStatement returns [EObject current=null] : iv_ruleStatement= ruleStatement EOF ;
    public final EObject entryRuleStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatement = null;


        try {
            // InternalJavali.g:551:50: (iv_ruleStatement= ruleStatement EOF )
            // InternalJavali.g:552:2: iv_ruleStatement= ruleStatement EOF
            {
             newCompositeNode(grammarAccess.getStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStatement=ruleStatement();

            state._fsp--;

             current =iv_ruleStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatement"


    // $ANTLR start "ruleStatement"
    // InternalJavali.g:558:1: ruleStatement returns [EObject current=null] : ( (this_Return_0= ruleReturn otherlv_1= ';' ) | (this_Break_2= ruleBreak otherlv_3= ';' ) | (this_Continue_4= ruleContinue otherlv_5= ';' ) | (this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';' ) | (this_VarAssign_8= ruleVarAssign otherlv_9= ';' ) | (this_Increment_10= ruleIncrement otherlv_11= ';' ) | (this_Decrement_12= ruleDecrement otherlv_13= ';' ) | (this_ProcCall_14= ruleProcCall otherlv_15= ';' ) | this_IfElse_16= ruleIfElse | this_While_17= ruleWhile | this_For_18= ruleFor | (this_DoWhile_19= ruleDoWhile otherlv_20= ';' ) ) ;
    public final EObject ruleStatement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_20=null;
        EObject this_Return_0 = null;

        EObject this_Break_2 = null;

        EObject this_Continue_4 = null;

        EObject this_VarDeclarationAssign_6 = null;

        EObject this_VarAssign_8 = null;

        EObject this_Increment_10 = null;

        EObject this_Decrement_12 = null;

        EObject this_ProcCall_14 = null;

        EObject this_IfElse_16 = null;

        EObject this_While_17 = null;

        EObject this_For_18 = null;

        EObject this_DoWhile_19 = null;



        	enterRule();

        try {
            // InternalJavali.g:564:2: ( ( (this_Return_0= ruleReturn otherlv_1= ';' ) | (this_Break_2= ruleBreak otherlv_3= ';' ) | (this_Continue_4= ruleContinue otherlv_5= ';' ) | (this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';' ) | (this_VarAssign_8= ruleVarAssign otherlv_9= ';' ) | (this_Increment_10= ruleIncrement otherlv_11= ';' ) | (this_Decrement_12= ruleDecrement otherlv_13= ';' ) | (this_ProcCall_14= ruleProcCall otherlv_15= ';' ) | this_IfElse_16= ruleIfElse | this_While_17= ruleWhile | this_For_18= ruleFor | (this_DoWhile_19= ruleDoWhile otherlv_20= ';' ) ) )
            // InternalJavali.g:565:2: ( (this_Return_0= ruleReturn otherlv_1= ';' ) | (this_Break_2= ruleBreak otherlv_3= ';' ) | (this_Continue_4= ruleContinue otherlv_5= ';' ) | (this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';' ) | (this_VarAssign_8= ruleVarAssign otherlv_9= ';' ) | (this_Increment_10= ruleIncrement otherlv_11= ';' ) | (this_Decrement_12= ruleDecrement otherlv_13= ';' ) | (this_ProcCall_14= ruleProcCall otherlv_15= ';' ) | this_IfElse_16= ruleIfElse | this_While_17= ruleWhile | this_For_18= ruleFor | (this_DoWhile_19= ruleDoWhile otherlv_20= ';' ) )
            {
            // InternalJavali.g:565:2: ( (this_Return_0= ruleReturn otherlv_1= ';' ) | (this_Break_2= ruleBreak otherlv_3= ';' ) | (this_Continue_4= ruleContinue otherlv_5= ';' ) | (this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';' ) | (this_VarAssign_8= ruleVarAssign otherlv_9= ';' ) | (this_Increment_10= ruleIncrement otherlv_11= ';' ) | (this_Decrement_12= ruleDecrement otherlv_13= ';' ) | (this_ProcCall_14= ruleProcCall otherlv_15= ';' ) | this_IfElse_16= ruleIfElse | this_While_17= ruleWhile | this_For_18= ruleFor | (this_DoWhile_19= ruleDoWhile otherlv_20= ';' ) )
            int alt10=12;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // InternalJavali.g:566:3: (this_Return_0= ruleReturn otherlv_1= ';' )
                    {
                    // InternalJavali.g:566:3: (this_Return_0= ruleReturn otherlv_1= ';' )
                    // InternalJavali.g:567:4: this_Return_0= ruleReturn otherlv_1= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getReturnParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_Return_0=ruleReturn();

                    state._fsp--;


                    				current = this_Return_0;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_1=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_1, grammarAccess.getStatementAccess().getSemicolonKeyword_0_1());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:581:3: (this_Break_2= ruleBreak otherlv_3= ';' )
                    {
                    // InternalJavali.g:581:3: (this_Break_2= ruleBreak otherlv_3= ';' )
                    // InternalJavali.g:582:4: this_Break_2= ruleBreak otherlv_3= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getBreakParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_Break_2=ruleBreak();

                    state._fsp--;


                    				current = this_Break_2;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_3=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_3, grammarAccess.getStatementAccess().getSemicolonKeyword_1_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalJavali.g:596:3: (this_Continue_4= ruleContinue otherlv_5= ';' )
                    {
                    // InternalJavali.g:596:3: (this_Continue_4= ruleContinue otherlv_5= ';' )
                    // InternalJavali.g:597:4: this_Continue_4= ruleContinue otherlv_5= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getContinueParserRuleCall_2_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_Continue_4=ruleContinue();

                    state._fsp--;


                    				current = this_Continue_4;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_5=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getStatementAccess().getSemicolonKeyword_2_1());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalJavali.g:611:3: (this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';' )
                    {
                    // InternalJavali.g:611:3: (this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';' )
                    // InternalJavali.g:612:4: this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getVarDeclarationAssignParserRuleCall_3_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_VarDeclarationAssign_6=ruleVarDeclarationAssign();

                    state._fsp--;


                    				current = this_VarDeclarationAssign_6;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_7=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_7, grammarAccess.getStatementAccess().getSemicolonKeyword_3_1());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalJavali.g:626:3: (this_VarAssign_8= ruleVarAssign otherlv_9= ';' )
                    {
                    // InternalJavali.g:626:3: (this_VarAssign_8= ruleVarAssign otherlv_9= ';' )
                    // InternalJavali.g:627:4: this_VarAssign_8= ruleVarAssign otherlv_9= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getVarAssignParserRuleCall_4_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_VarAssign_8=ruleVarAssign();

                    state._fsp--;


                    				current = this_VarAssign_8;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_9=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_9, grammarAccess.getStatementAccess().getSemicolonKeyword_4_1());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalJavali.g:641:3: (this_Increment_10= ruleIncrement otherlv_11= ';' )
                    {
                    // InternalJavali.g:641:3: (this_Increment_10= ruleIncrement otherlv_11= ';' )
                    // InternalJavali.g:642:4: this_Increment_10= ruleIncrement otherlv_11= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getIncrementParserRuleCall_5_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_Increment_10=ruleIncrement();

                    state._fsp--;


                    				current = this_Increment_10;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_11=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_11, grammarAccess.getStatementAccess().getSemicolonKeyword_5_1());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalJavali.g:656:3: (this_Decrement_12= ruleDecrement otherlv_13= ';' )
                    {
                    // InternalJavali.g:656:3: (this_Decrement_12= ruleDecrement otherlv_13= ';' )
                    // InternalJavali.g:657:4: this_Decrement_12= ruleDecrement otherlv_13= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getDecrementParserRuleCall_6_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_Decrement_12=ruleDecrement();

                    state._fsp--;


                    				current = this_Decrement_12;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_13=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_13, grammarAccess.getStatementAccess().getSemicolonKeyword_6_1());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalJavali.g:671:3: (this_ProcCall_14= ruleProcCall otherlv_15= ';' )
                    {
                    // InternalJavali.g:671:3: (this_ProcCall_14= ruleProcCall otherlv_15= ';' )
                    // InternalJavali.g:672:4: this_ProcCall_14= ruleProcCall otherlv_15= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getProcCallParserRuleCall_7_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_ProcCall_14=ruleProcCall();

                    state._fsp--;


                    				current = this_ProcCall_14;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_15=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_15, grammarAccess.getStatementAccess().getSemicolonKeyword_7_1());
                    			

                    }


                    }
                    break;
                case 9 :
                    // InternalJavali.g:686:3: this_IfElse_16= ruleIfElse
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getIfElseParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_IfElse_16=ruleIfElse();

                    state._fsp--;


                    			current = this_IfElse_16;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalJavali.g:695:3: this_While_17= ruleWhile
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getWhileParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_While_17=ruleWhile();

                    state._fsp--;


                    			current = this_While_17;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalJavali.g:704:3: this_For_18= ruleFor
                    {

                    			newCompositeNode(grammarAccess.getStatementAccess().getForParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_For_18=ruleFor();

                    state._fsp--;


                    			current = this_For_18;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 12 :
                    // InternalJavali.g:713:3: (this_DoWhile_19= ruleDoWhile otherlv_20= ';' )
                    {
                    // InternalJavali.g:713:3: (this_DoWhile_19= ruleDoWhile otherlv_20= ';' )
                    // InternalJavali.g:714:4: this_DoWhile_19= ruleDoWhile otherlv_20= ';'
                    {

                    				newCompositeNode(grammarAccess.getStatementAccess().getDoWhileParserRuleCall_11_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_DoWhile_19=ruleDoWhile();

                    state._fsp--;


                    				current = this_DoWhile_19;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_20=(Token)match(input,16,FOLLOW_2); 

                    				newLeafNode(otherlv_20, grammarAccess.getStatementAccess().getSemicolonKeyword_11_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatement"


    // $ANTLR start "entryRuleReturn"
    // InternalJavali.g:731:1: entryRuleReturn returns [EObject current=null] : iv_ruleReturn= ruleReturn EOF ;
    public final EObject entryRuleReturn() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleReturn = null;


        try {
            // InternalJavali.g:731:47: (iv_ruleReturn= ruleReturn EOF )
            // InternalJavali.g:732:2: iv_ruleReturn= ruleReturn EOF
            {
             newCompositeNode(grammarAccess.getReturnRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleReturn=ruleReturn();

            state._fsp--;

             current =iv_ruleReturn; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleReturn"


    // $ANTLR start "ruleReturn"
    // InternalJavali.g:738:1: ruleReturn returns [EObject current=null] : ( () otherlv_1= 'return' ( (lv_exp_2_0= ruleExpression ) )? ) ;
    public final EObject ruleReturn() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exp_2_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:744:2: ( ( () otherlv_1= 'return' ( (lv_exp_2_0= ruleExpression ) )? ) )
            // InternalJavali.g:745:2: ( () otherlv_1= 'return' ( (lv_exp_2_0= ruleExpression ) )? )
            {
            // InternalJavali.g:745:2: ( () otherlv_1= 'return' ( (lv_exp_2_0= ruleExpression ) )? )
            // InternalJavali.g:746:3: () otherlv_1= 'return' ( (lv_exp_2_0= ruleExpression ) )?
            {
            // InternalJavali.g:746:3: ()
            // InternalJavali.g:747:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getReturnAccess().getReturnAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,24,FOLLOW_17); 

            			newLeafNode(otherlv_1, grammarAccess.getReturnAccess().getReturnKeyword_1());
            		
            // InternalJavali.g:757:3: ( (lv_exp_2_0= ruleExpression ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( ((LA11_0>=RULE_PRIMITIVE_VALUE && LA11_0<=RULE_ID)||LA11_0==21||(LA11_0>=48 && LA11_0<=49)||LA11_0==53) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalJavali.g:758:4: (lv_exp_2_0= ruleExpression )
                    {
                    // InternalJavali.g:758:4: (lv_exp_2_0= ruleExpression )
                    // InternalJavali.g:759:5: lv_exp_2_0= ruleExpression
                    {

                    					newCompositeNode(grammarAccess.getReturnAccess().getExpExpressionParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_exp_2_0=ruleExpression();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getReturnRule());
                    					}
                    					set(
                    						current,
                    						"exp",
                    						lv_exp_2_0,
                    						"pt.iscte.paddle.Javali.Expression");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleReturn"


    // $ANTLR start "entryRuleBreak"
    // InternalJavali.g:780:1: entryRuleBreak returns [EObject current=null] : iv_ruleBreak= ruleBreak EOF ;
    public final EObject entryRuleBreak() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBreak = null;


        try {
            // InternalJavali.g:780:46: (iv_ruleBreak= ruleBreak EOF )
            // InternalJavali.g:781:2: iv_ruleBreak= ruleBreak EOF
            {
             newCompositeNode(grammarAccess.getBreakRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBreak=ruleBreak();

            state._fsp--;

             current =iv_ruleBreak; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBreak"


    // $ANTLR start "ruleBreak"
    // InternalJavali.g:787:1: ruleBreak returns [EObject current=null] : ( () otherlv_1= 'break' ) ;
    public final EObject ruleBreak() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalJavali.g:793:2: ( ( () otherlv_1= 'break' ) )
            // InternalJavali.g:794:2: ( () otherlv_1= 'break' )
            {
            // InternalJavali.g:794:2: ( () otherlv_1= 'break' )
            // InternalJavali.g:795:3: () otherlv_1= 'break'
            {
            // InternalJavali.g:795:3: ()
            // InternalJavali.g:796:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBreakAccess().getBreakAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,25,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getBreakAccess().getBreakKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBreak"


    // $ANTLR start "entryRuleContinue"
    // InternalJavali.g:810:1: entryRuleContinue returns [EObject current=null] : iv_ruleContinue= ruleContinue EOF ;
    public final EObject entryRuleContinue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContinue = null;


        try {
            // InternalJavali.g:810:49: (iv_ruleContinue= ruleContinue EOF )
            // InternalJavali.g:811:2: iv_ruleContinue= ruleContinue EOF
            {
             newCompositeNode(grammarAccess.getContinueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleContinue=ruleContinue();

            state._fsp--;

             current =iv_ruleContinue; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleContinue"


    // $ANTLR start "ruleContinue"
    // InternalJavali.g:817:1: ruleContinue returns [EObject current=null] : ( () otherlv_1= 'continue' ) ;
    public final EObject ruleContinue() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalJavali.g:823:2: ( ( () otherlv_1= 'continue' ) )
            // InternalJavali.g:824:2: ( () otherlv_1= 'continue' )
            {
            // InternalJavali.g:824:2: ( () otherlv_1= 'continue' )
            // InternalJavali.g:825:3: () otherlv_1= 'continue'
            {
            // InternalJavali.g:825:3: ()
            // InternalJavali.g:826:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getContinueAccess().getContinueAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,26,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getContinueAccess().getContinueKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleContinue"


    // $ANTLR start "entryRuleVarDeclaration"
    // InternalJavali.g:840:1: entryRuleVarDeclaration returns [EObject current=null] : iv_ruleVarDeclaration= ruleVarDeclaration EOF ;
    public final EObject entryRuleVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarDeclaration = null;


        try {
            // InternalJavali.g:840:55: (iv_ruleVarDeclaration= ruleVarDeclaration EOF )
            // InternalJavali.g:841:2: iv_ruleVarDeclaration= ruleVarDeclaration EOF
            {
             newCompositeNode(grammarAccess.getVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarDeclaration=ruleVarDeclaration();

            state._fsp--;

             current =iv_ruleVarDeclaration; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarDeclaration"


    // $ANTLR start "ruleVarDeclaration"
    // InternalJavali.g:847:1: ruleVarDeclaration returns [EObject current=null] : ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) ) ;
    public final EObject ruleVarDeclaration() throws RecognitionException {
        EObject current = null;

        EObject lv_type_0_0 = null;

        EObject lv_id_1_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:853:2: ( ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) ) )
            // InternalJavali.g:854:2: ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) )
            {
            // InternalJavali.g:854:2: ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) )
            // InternalJavali.g:855:3: ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) )
            {
            // InternalJavali.g:855:3: ( (lv_type_0_0= ruleType ) )
            // InternalJavali.g:856:4: (lv_type_0_0= ruleType )
            {
            // InternalJavali.g:856:4: (lv_type_0_0= ruleType )
            // InternalJavali.g:857:5: lv_type_0_0= ruleType
            {

            					newCompositeNode(grammarAccess.getVarDeclarationAccess().getTypeTypeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_5);
            lv_type_0_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarDeclarationRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_0_0,
            						"pt.iscte.paddle.Javali.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJavali.g:874:3: ( (lv_id_1_0= ruleIdentifier ) )
            // InternalJavali.g:875:4: (lv_id_1_0= ruleIdentifier )
            {
            // InternalJavali.g:875:4: (lv_id_1_0= ruleIdentifier )
            // InternalJavali.g:876:5: lv_id_1_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getVarDeclarationAccess().getIdIdentifierParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_id_1_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarDeclarationRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_1_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarDeclaration"


    // $ANTLR start "entryRuleVarDeclarationAssign"
    // InternalJavali.g:897:1: entryRuleVarDeclarationAssign returns [EObject current=null] : iv_ruleVarDeclarationAssign= ruleVarDeclarationAssign EOF ;
    public final EObject entryRuleVarDeclarationAssign() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarDeclarationAssign = null;


        try {
            // InternalJavali.g:897:61: (iv_ruleVarDeclarationAssign= ruleVarDeclarationAssign EOF )
            // InternalJavali.g:898:2: iv_ruleVarDeclarationAssign= ruleVarDeclarationAssign EOF
            {
             newCompositeNode(grammarAccess.getVarDeclarationAssignRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarDeclarationAssign=ruleVarDeclarationAssign();

            state._fsp--;

             current =iv_ruleVarDeclarationAssign; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarDeclarationAssign"


    // $ANTLR start "ruleVarDeclarationAssign"
    // InternalJavali.g:904:1: ruleVarDeclarationAssign returns [EObject current=null] : ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) (otherlv_2= '=' ( (lv_init_3_0= ruleExpression ) ) )? ) ;
    public final EObject ruleVarDeclarationAssign() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_type_0_0 = null;

        EObject lv_id_1_0 = null;

        EObject lv_init_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:910:2: ( ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) (otherlv_2= '=' ( (lv_init_3_0= ruleExpression ) ) )? ) )
            // InternalJavali.g:911:2: ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) (otherlv_2= '=' ( (lv_init_3_0= ruleExpression ) ) )? )
            {
            // InternalJavali.g:911:2: ( ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) (otherlv_2= '=' ( (lv_init_3_0= ruleExpression ) ) )? )
            // InternalJavali.g:912:3: ( (lv_type_0_0= ruleType ) ) ( (lv_id_1_0= ruleIdentifier ) ) (otherlv_2= '=' ( (lv_init_3_0= ruleExpression ) ) )?
            {
            // InternalJavali.g:912:3: ( (lv_type_0_0= ruleType ) )
            // InternalJavali.g:913:4: (lv_type_0_0= ruleType )
            {
            // InternalJavali.g:913:4: (lv_type_0_0= ruleType )
            // InternalJavali.g:914:5: lv_type_0_0= ruleType
            {

            					newCompositeNode(grammarAccess.getVarDeclarationAssignAccess().getTypeTypeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_5);
            lv_type_0_0=ruleType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarDeclarationAssignRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_0_0,
            						"pt.iscte.paddle.Javali.Type");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJavali.g:931:3: ( (lv_id_1_0= ruleIdentifier ) )
            // InternalJavali.g:932:4: (lv_id_1_0= ruleIdentifier )
            {
            // InternalJavali.g:932:4: (lv_id_1_0= ruleIdentifier )
            // InternalJavali.g:933:5: lv_id_1_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getVarDeclarationAssignAccess().getIdIdentifierParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_18);
            lv_id_1_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarDeclarationAssignRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_1_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJavali.g:950:3: (otherlv_2= '=' ( (lv_init_3_0= ruleExpression ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==15) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalJavali.g:951:4: otherlv_2= '=' ( (lv_init_3_0= ruleExpression ) )
                    {
                    otherlv_2=(Token)match(input,15,FOLLOW_19); 

                    				newLeafNode(otherlv_2, grammarAccess.getVarDeclarationAssignAccess().getEqualsSignKeyword_2_0());
                    			
                    // InternalJavali.g:955:4: ( (lv_init_3_0= ruleExpression ) )
                    // InternalJavali.g:956:5: (lv_init_3_0= ruleExpression )
                    {
                    // InternalJavali.g:956:5: (lv_init_3_0= ruleExpression )
                    // InternalJavali.g:957:6: lv_init_3_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getVarDeclarationAssignAccess().getInitExpressionParserRuleCall_2_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_init_3_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getVarDeclarationAssignRule());
                    						}
                    						set(
                    							current,
                    							"init",
                    							lv_init_3_0,
                    							"pt.iscte.paddle.Javali.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarDeclarationAssign"


    // $ANTLR start "entryRuleVarAssign"
    // InternalJavali.g:979:1: entryRuleVarAssign returns [EObject current=null] : iv_ruleVarAssign= ruleVarAssign EOF ;
    public final EObject entryRuleVarAssign() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarAssign = null;


        try {
            // InternalJavali.g:979:50: (iv_ruleVarAssign= ruleVarAssign EOF )
            // InternalJavali.g:980:2: iv_ruleVarAssign= ruleVarAssign EOF
            {
             newCompositeNode(grammarAccess.getVarAssignRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarAssign=ruleVarAssign();

            state._fsp--;

             current =iv_ruleVarAssign; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarAssign"


    // $ANTLR start "ruleVarAssign"
    // InternalJavali.g:986:1: ruleVarAssign returns [EObject current=null] : ( ( (lv_var_0_0= ruleVarExpression ) ) otherlv_1= '=' ( (lv_exp_2_0= ruleExpression ) ) ) ;
    public final EObject ruleVarAssign() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_var_0_0 = null;

        EObject lv_exp_2_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:992:2: ( ( ( (lv_var_0_0= ruleVarExpression ) ) otherlv_1= '=' ( (lv_exp_2_0= ruleExpression ) ) ) )
            // InternalJavali.g:993:2: ( ( (lv_var_0_0= ruleVarExpression ) ) otherlv_1= '=' ( (lv_exp_2_0= ruleExpression ) ) )
            {
            // InternalJavali.g:993:2: ( ( (lv_var_0_0= ruleVarExpression ) ) otherlv_1= '=' ( (lv_exp_2_0= ruleExpression ) ) )
            // InternalJavali.g:994:3: ( (lv_var_0_0= ruleVarExpression ) ) otherlv_1= '=' ( (lv_exp_2_0= ruleExpression ) )
            {
            // InternalJavali.g:994:3: ( (lv_var_0_0= ruleVarExpression ) )
            // InternalJavali.g:995:4: (lv_var_0_0= ruleVarExpression )
            {
            // InternalJavali.g:995:4: (lv_var_0_0= ruleVarExpression )
            // InternalJavali.g:996:5: lv_var_0_0= ruleVarExpression
            {

            					newCompositeNode(grammarAccess.getVarAssignAccess().getVarVarExpressionParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_6);
            lv_var_0_0=ruleVarExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarAssignRule());
            					}
            					set(
            						current,
            						"var",
            						lv_var_0_0,
            						"pt.iscte.paddle.Javali.VarExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,15,FOLLOW_19); 

            			newLeafNode(otherlv_1, grammarAccess.getVarAssignAccess().getEqualsSignKeyword_1());
            		
            // InternalJavali.g:1017:3: ( (lv_exp_2_0= ruleExpression ) )
            // InternalJavali.g:1018:4: (lv_exp_2_0= ruleExpression )
            {
            // InternalJavali.g:1018:4: (lv_exp_2_0= ruleExpression )
            // InternalJavali.g:1019:5: lv_exp_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getVarAssignAccess().getExpExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_exp_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarAssignRule());
            					}
            					set(
            						current,
            						"exp",
            						lv_exp_2_0,
            						"pt.iscte.paddle.Javali.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarAssign"


    // $ANTLR start "entryRuleIfElse"
    // InternalJavali.g:1040:1: entryRuleIfElse returns [EObject current=null] : iv_ruleIfElse= ruleIfElse EOF ;
    public final EObject entryRuleIfElse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfElse = null;


        try {
            // InternalJavali.g:1040:47: (iv_ruleIfElse= ruleIfElse EOF )
            // InternalJavali.g:1041:2: iv_ruleIfElse= ruleIfElse EOF
            {
             newCompositeNode(grammarAccess.getIfElseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIfElse=ruleIfElse();

            state._fsp--;

             current =iv_ruleIfElse; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIfElse"


    // $ANTLR start "ruleIfElse"
    // InternalJavali.g:1047:1: ruleIfElse returns [EObject current=null] : (otherlv_0= 'if' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_selectionBlock_4_0= ruleBlock ) ) (otherlv_5= 'else' ( (lv_alternativeBlock_6_0= ruleBlock ) ) )? ) ;
    public final EObject ruleIfElse() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_guard_2_0 = null;

        EObject lv_selectionBlock_4_0 = null;

        EObject lv_alternativeBlock_6_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1053:2: ( (otherlv_0= 'if' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_selectionBlock_4_0= ruleBlock ) ) (otherlv_5= 'else' ( (lv_alternativeBlock_6_0= ruleBlock ) ) )? ) )
            // InternalJavali.g:1054:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_selectionBlock_4_0= ruleBlock ) ) (otherlv_5= 'else' ( (lv_alternativeBlock_6_0= ruleBlock ) ) )? )
            {
            // InternalJavali.g:1054:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_selectionBlock_4_0= ruleBlock ) ) (otherlv_5= 'else' ( (lv_alternativeBlock_6_0= ruleBlock ) ) )? )
            // InternalJavali.g:1055:3: otherlv_0= 'if' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_selectionBlock_4_0= ruleBlock ) ) (otherlv_5= 'else' ( (lv_alternativeBlock_6_0= ruleBlock ) ) )?
            {
            otherlv_0=(Token)match(input,27,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getIfElseAccess().getIfKeyword_0());
            		
            otherlv_1=(Token)match(input,21,FOLLOW_19); 

            			newLeafNode(otherlv_1, grammarAccess.getIfElseAccess().getLeftParenthesisKeyword_1());
            		
            // InternalJavali.g:1063:3: ( (lv_guard_2_0= ruleExpression ) )
            // InternalJavali.g:1064:4: (lv_guard_2_0= ruleExpression )
            {
            // InternalJavali.g:1064:4: (lv_guard_2_0= ruleExpression )
            // InternalJavali.g:1065:5: lv_guard_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getIfElseAccess().getGuardExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_20);
            lv_guard_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfElseRule());
            					}
            					set(
            						current,
            						"guard",
            						lv_guard_2_0,
            						"pt.iscte.paddle.Javali.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,23,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getIfElseAccess().getRightParenthesisKeyword_3());
            		
            // InternalJavali.g:1086:3: ( (lv_selectionBlock_4_0= ruleBlock ) )
            // InternalJavali.g:1087:4: (lv_selectionBlock_4_0= ruleBlock )
            {
            // InternalJavali.g:1087:4: (lv_selectionBlock_4_0= ruleBlock )
            // InternalJavali.g:1088:5: lv_selectionBlock_4_0= ruleBlock
            {

            					newCompositeNode(grammarAccess.getIfElseAccess().getSelectionBlockBlockParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_21);
            lv_selectionBlock_4_0=ruleBlock();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfElseRule());
            					}
            					set(
            						current,
            						"selectionBlock",
            						lv_selectionBlock_4_0,
            						"pt.iscte.paddle.Javali.Block");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJavali.g:1105:3: (otherlv_5= 'else' ( (lv_alternativeBlock_6_0= ruleBlock ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==28) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalJavali.g:1106:4: otherlv_5= 'else' ( (lv_alternativeBlock_6_0= ruleBlock ) )
                    {
                    otherlv_5=(Token)match(input,28,FOLLOW_9); 

                    				newLeafNode(otherlv_5, grammarAccess.getIfElseAccess().getElseKeyword_5_0());
                    			
                    // InternalJavali.g:1110:4: ( (lv_alternativeBlock_6_0= ruleBlock ) )
                    // InternalJavali.g:1111:5: (lv_alternativeBlock_6_0= ruleBlock )
                    {
                    // InternalJavali.g:1111:5: (lv_alternativeBlock_6_0= ruleBlock )
                    // InternalJavali.g:1112:6: lv_alternativeBlock_6_0= ruleBlock
                    {

                    						newCompositeNode(grammarAccess.getIfElseAccess().getAlternativeBlockBlockParserRuleCall_5_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_alternativeBlock_6_0=ruleBlock();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIfElseRule());
                    						}
                    						set(
                    							current,
                    							"alternativeBlock",
                    							lv_alternativeBlock_6_0,
                    							"pt.iscte.paddle.Javali.Block");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIfElse"


    // $ANTLR start "entryRuleWhile"
    // InternalJavali.g:1134:1: entryRuleWhile returns [EObject current=null] : iv_ruleWhile= ruleWhile EOF ;
    public final EObject entryRuleWhile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhile = null;


        try {
            // InternalJavali.g:1134:46: (iv_ruleWhile= ruleWhile EOF )
            // InternalJavali.g:1135:2: iv_ruleWhile= ruleWhile EOF
            {
             newCompositeNode(grammarAccess.getWhileRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWhile=ruleWhile();

            state._fsp--;

             current =iv_ruleWhile; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhile"


    // $ANTLR start "ruleWhile"
    // InternalJavali.g:1141:1: ruleWhile returns [EObject current=null] : (otherlv_0= 'while' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) ) ;
    public final EObject ruleWhile() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_guard_2_0 = null;

        EObject lv_block_4_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1147:2: ( (otherlv_0= 'while' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) ) )
            // InternalJavali.g:1148:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) )
            {
            // InternalJavali.g:1148:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) ) )
            // InternalJavali.g:1149:3: otherlv_0= 'while' otherlv_1= '(' ( (lv_guard_2_0= ruleExpression ) ) otherlv_3= ')' ( (lv_block_4_0= ruleBlock ) )
            {
            otherlv_0=(Token)match(input,29,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getWhileAccess().getWhileKeyword_0());
            		
            otherlv_1=(Token)match(input,21,FOLLOW_19); 

            			newLeafNode(otherlv_1, grammarAccess.getWhileAccess().getLeftParenthesisKeyword_1());
            		
            // InternalJavali.g:1157:3: ( (lv_guard_2_0= ruleExpression ) )
            // InternalJavali.g:1158:4: (lv_guard_2_0= ruleExpression )
            {
            // InternalJavali.g:1158:4: (lv_guard_2_0= ruleExpression )
            // InternalJavali.g:1159:5: lv_guard_2_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getWhileAccess().getGuardExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_20);
            lv_guard_2_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileRule());
            					}
            					set(
            						current,
            						"guard",
            						lv_guard_2_0,
            						"pt.iscte.paddle.Javali.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,23,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getWhileAccess().getRightParenthesisKeyword_3());
            		
            // InternalJavali.g:1180:3: ( (lv_block_4_0= ruleBlock ) )
            // InternalJavali.g:1181:4: (lv_block_4_0= ruleBlock )
            {
            // InternalJavali.g:1181:4: (lv_block_4_0= ruleBlock )
            // InternalJavali.g:1182:5: lv_block_4_0= ruleBlock
            {

            					newCompositeNode(grammarAccess.getWhileAccess().getBlockBlockParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_block_4_0=ruleBlock();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileRule());
            					}
            					set(
            						current,
            						"block",
            						lv_block_4_0,
            						"pt.iscte.paddle.Javali.Block");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWhile"


    // $ANTLR start "entryRuleFor"
    // InternalJavali.g:1203:1: entryRuleFor returns [EObject current=null] : iv_ruleFor= ruleFor EOF ;
    public final EObject entryRuleFor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFor = null;


        try {
            // InternalJavali.g:1203:44: (iv_ruleFor= ruleFor EOF )
            // InternalJavali.g:1204:2: iv_ruleFor= ruleFor EOF
            {
             newCompositeNode(grammarAccess.getForRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFor=ruleFor();

            state._fsp--;

             current =iv_ruleFor; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFor"


    // $ANTLR start "ruleFor"
    // InternalJavali.g:1210:1: ruleFor returns [EObject current=null] : (otherlv_0= 'for' otherlv_1= '(' ( ( (lv_initStatements_2_0= ruleForStatementInit ) ) (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )* )? otherlv_5= ';' ( (lv_guard_6_0= ruleExpression ) ) otherlv_7= ';' ( ( (lv_progressStatements_8_0= ruleForStatement ) ) (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )* )? otherlv_11= ')' ( (lv_block_12_0= ruleBlock ) ) ) ;
    public final EObject ruleFor() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_initStatements_2_0 = null;

        EObject lv_initStatements_4_0 = null;

        EObject lv_guard_6_0 = null;

        EObject lv_progressStatements_8_0 = null;

        EObject lv_progressStatements_10_0 = null;

        EObject lv_block_12_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1216:2: ( (otherlv_0= 'for' otherlv_1= '(' ( ( (lv_initStatements_2_0= ruleForStatementInit ) ) (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )* )? otherlv_5= ';' ( (lv_guard_6_0= ruleExpression ) ) otherlv_7= ';' ( ( (lv_progressStatements_8_0= ruleForStatement ) ) (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )* )? otherlv_11= ')' ( (lv_block_12_0= ruleBlock ) ) ) )
            // InternalJavali.g:1217:2: (otherlv_0= 'for' otherlv_1= '(' ( ( (lv_initStatements_2_0= ruleForStatementInit ) ) (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )* )? otherlv_5= ';' ( (lv_guard_6_0= ruleExpression ) ) otherlv_7= ';' ( ( (lv_progressStatements_8_0= ruleForStatement ) ) (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )* )? otherlv_11= ')' ( (lv_block_12_0= ruleBlock ) ) )
            {
            // InternalJavali.g:1217:2: (otherlv_0= 'for' otherlv_1= '(' ( ( (lv_initStatements_2_0= ruleForStatementInit ) ) (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )* )? otherlv_5= ';' ( (lv_guard_6_0= ruleExpression ) ) otherlv_7= ';' ( ( (lv_progressStatements_8_0= ruleForStatement ) ) (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )* )? otherlv_11= ')' ( (lv_block_12_0= ruleBlock ) ) )
            // InternalJavali.g:1218:3: otherlv_0= 'for' otherlv_1= '(' ( ( (lv_initStatements_2_0= ruleForStatementInit ) ) (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )* )? otherlv_5= ';' ( (lv_guard_6_0= ruleExpression ) ) otherlv_7= ';' ( ( (lv_progressStatements_8_0= ruleForStatement ) ) (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )* )? otherlv_11= ')' ( (lv_block_12_0= ruleBlock ) )
            {
            otherlv_0=(Token)match(input,30,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getForAccess().getForKeyword_0());
            		
            otherlv_1=(Token)match(input,21,FOLLOW_22); 

            			newLeafNode(otherlv_1, grammarAccess.getForAccess().getLeftParenthesisKeyword_1());
            		
            // InternalJavali.g:1226:3: ( ( (lv_initStatements_2_0= ruleForStatementInit ) ) (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalJavali.g:1227:4: ( (lv_initStatements_2_0= ruleForStatementInit ) ) (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )*
                    {
                    // InternalJavali.g:1227:4: ( (lv_initStatements_2_0= ruleForStatementInit ) )
                    // InternalJavali.g:1228:5: (lv_initStatements_2_0= ruleForStatementInit )
                    {
                    // InternalJavali.g:1228:5: (lv_initStatements_2_0= ruleForStatementInit )
                    // InternalJavali.g:1229:6: lv_initStatements_2_0= ruleForStatementInit
                    {

                    						newCompositeNode(grammarAccess.getForAccess().getInitStatementsForStatementInitParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_23);
                    lv_initStatements_2_0=ruleForStatementInit();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getForRule());
                    						}
                    						add(
                    							current,
                    							"initStatements",
                    							lv_initStatements_2_0,
                    							"pt.iscte.paddle.Javali.ForStatementInit");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalJavali.g:1246:4: (otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==22) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // InternalJavali.g:1247:5: otherlv_3= ',' ( (lv_initStatements_4_0= ruleForStatementInit ) )
                    	    {
                    	    otherlv_3=(Token)match(input,22,FOLLOW_5); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getForAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    // InternalJavali.g:1251:5: ( (lv_initStatements_4_0= ruleForStatementInit ) )
                    	    // InternalJavali.g:1252:6: (lv_initStatements_4_0= ruleForStatementInit )
                    	    {
                    	    // InternalJavali.g:1252:6: (lv_initStatements_4_0= ruleForStatementInit )
                    	    // InternalJavali.g:1253:7: lv_initStatements_4_0= ruleForStatementInit
                    	    {

                    	    							newCompositeNode(grammarAccess.getForAccess().getInitStatementsForStatementInitParserRuleCall_2_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_23);
                    	    lv_initStatements_4_0=ruleForStatementInit();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getForRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"initStatements",
                    	    								lv_initStatements_4_0,
                    	    								"pt.iscte.paddle.Javali.ForStatementInit");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,16,FOLLOW_19); 

            			newLeafNode(otherlv_5, grammarAccess.getForAccess().getSemicolonKeyword_3());
            		
            // InternalJavali.g:1276:3: ( (lv_guard_6_0= ruleExpression ) )
            // InternalJavali.g:1277:4: (lv_guard_6_0= ruleExpression )
            {
            // InternalJavali.g:1277:4: (lv_guard_6_0= ruleExpression )
            // InternalJavali.g:1278:5: lv_guard_6_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getForAccess().getGuardExpressionParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_8);
            lv_guard_6_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForRule());
            					}
            					set(
            						current,
            						"guard",
            						lv_guard_6_0,
            						"pt.iscte.paddle.Javali.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,16,FOLLOW_14); 

            			newLeafNode(otherlv_7, grammarAccess.getForAccess().getSemicolonKeyword_5());
            		
            // InternalJavali.g:1299:3: ( ( (lv_progressStatements_8_0= ruleForStatement ) ) (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )* )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_ID) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalJavali.g:1300:4: ( (lv_progressStatements_8_0= ruleForStatement ) ) (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )*
                    {
                    // InternalJavali.g:1300:4: ( (lv_progressStatements_8_0= ruleForStatement ) )
                    // InternalJavali.g:1301:5: (lv_progressStatements_8_0= ruleForStatement )
                    {
                    // InternalJavali.g:1301:5: (lv_progressStatements_8_0= ruleForStatement )
                    // InternalJavali.g:1302:6: lv_progressStatements_8_0= ruleForStatement
                    {

                    						newCompositeNode(grammarAccess.getForAccess().getProgressStatementsForStatementParserRuleCall_6_0_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_progressStatements_8_0=ruleForStatement();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getForRule());
                    						}
                    						add(
                    							current,
                    							"progressStatements",
                    							lv_progressStatements_8_0,
                    							"pt.iscte.paddle.Javali.ForStatement");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalJavali.g:1319:4: (otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( (LA16_0==22) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // InternalJavali.g:1320:5: otherlv_9= ',' ( (lv_progressStatements_10_0= ruleForStatement ) )
                    	    {
                    	    otherlv_9=(Token)match(input,22,FOLLOW_5); 

                    	    					newLeafNode(otherlv_9, grammarAccess.getForAccess().getCommaKeyword_6_1_0());
                    	    				
                    	    // InternalJavali.g:1324:5: ( (lv_progressStatements_10_0= ruleForStatement ) )
                    	    // InternalJavali.g:1325:6: (lv_progressStatements_10_0= ruleForStatement )
                    	    {
                    	    // InternalJavali.g:1325:6: (lv_progressStatements_10_0= ruleForStatement )
                    	    // InternalJavali.g:1326:7: lv_progressStatements_10_0= ruleForStatement
                    	    {

                    	    							newCompositeNode(grammarAccess.getForAccess().getProgressStatementsForStatementParserRuleCall_6_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_15);
                    	    lv_progressStatements_10_0=ruleForStatement();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getForRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"progressStatements",
                    	    								lv_progressStatements_10_0,
                    	    								"pt.iscte.paddle.Javali.ForStatement");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_11=(Token)match(input,23,FOLLOW_9); 

            			newLeafNode(otherlv_11, grammarAccess.getForAccess().getRightParenthesisKeyword_7());
            		
            // InternalJavali.g:1349:3: ( (lv_block_12_0= ruleBlock ) )
            // InternalJavali.g:1350:4: (lv_block_12_0= ruleBlock )
            {
            // InternalJavali.g:1350:4: (lv_block_12_0= ruleBlock )
            // InternalJavali.g:1351:5: lv_block_12_0= ruleBlock
            {

            					newCompositeNode(grammarAccess.getForAccess().getBlockBlockParserRuleCall_8_0());
            				
            pushFollow(FOLLOW_2);
            lv_block_12_0=ruleBlock();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForRule());
            					}
            					set(
            						current,
            						"block",
            						lv_block_12_0,
            						"pt.iscte.paddle.Javali.Block");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFor"


    // $ANTLR start "entryRuleDoWhile"
    // InternalJavali.g:1372:1: entryRuleDoWhile returns [EObject current=null] : iv_ruleDoWhile= ruleDoWhile EOF ;
    public final EObject entryRuleDoWhile() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDoWhile = null;


        try {
            // InternalJavali.g:1372:48: (iv_ruleDoWhile= ruleDoWhile EOF )
            // InternalJavali.g:1373:2: iv_ruleDoWhile= ruleDoWhile EOF
            {
             newCompositeNode(grammarAccess.getDoWhileRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDoWhile=ruleDoWhile();

            state._fsp--;

             current =iv_ruleDoWhile; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDoWhile"


    // $ANTLR start "ruleDoWhile"
    // InternalJavali.g:1379:1: ruleDoWhile returns [EObject current=null] : (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_guard_4_0= ruleExpression ) ) otherlv_5= ')' ) ;
    public final EObject ruleDoWhile() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_block_1_0 = null;

        EObject lv_guard_4_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1385:2: ( (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_guard_4_0= ruleExpression ) ) otherlv_5= ')' ) )
            // InternalJavali.g:1386:2: (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_guard_4_0= ruleExpression ) ) otherlv_5= ')' )
            {
            // InternalJavali.g:1386:2: (otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_guard_4_0= ruleExpression ) ) otherlv_5= ')' )
            // InternalJavali.g:1387:3: otherlv_0= 'do' ( (lv_block_1_0= ruleBlock ) ) otherlv_2= 'while' otherlv_3= '(' ( (lv_guard_4_0= ruleExpression ) ) otherlv_5= ')'
            {
            otherlv_0=(Token)match(input,31,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getDoWhileAccess().getDoKeyword_0());
            		
            // InternalJavali.g:1391:3: ( (lv_block_1_0= ruleBlock ) )
            // InternalJavali.g:1392:4: (lv_block_1_0= ruleBlock )
            {
            // InternalJavali.g:1392:4: (lv_block_1_0= ruleBlock )
            // InternalJavali.g:1393:5: lv_block_1_0= ruleBlock
            {

            					newCompositeNode(grammarAccess.getDoWhileAccess().getBlockBlockParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_24);
            lv_block_1_0=ruleBlock();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDoWhileRule());
            					}
            					set(
            						current,
            						"block",
            						lv_block_1_0,
            						"pt.iscte.paddle.Javali.Block");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,29,FOLLOW_13); 

            			newLeafNode(otherlv_2, grammarAccess.getDoWhileAccess().getWhileKeyword_2());
            		
            otherlv_3=(Token)match(input,21,FOLLOW_19); 

            			newLeafNode(otherlv_3, grammarAccess.getDoWhileAccess().getLeftParenthesisKeyword_3());
            		
            // InternalJavali.g:1418:3: ( (lv_guard_4_0= ruleExpression ) )
            // InternalJavali.g:1419:4: (lv_guard_4_0= ruleExpression )
            {
            // InternalJavali.g:1419:4: (lv_guard_4_0= ruleExpression )
            // InternalJavali.g:1420:5: lv_guard_4_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getDoWhileAccess().getGuardExpressionParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_20);
            lv_guard_4_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDoWhileRule());
            					}
            					set(
            						current,
            						"guard",
            						lv_guard_4_0,
            						"pt.iscte.paddle.Javali.Expression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,23,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getDoWhileAccess().getRightParenthesisKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDoWhile"


    // $ANTLR start "entryRuleForStatementInit"
    // InternalJavali.g:1445:1: entryRuleForStatementInit returns [EObject current=null] : iv_ruleForStatementInit= ruleForStatementInit EOF ;
    public final EObject entryRuleForStatementInit() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForStatementInit = null;


        try {
            // InternalJavali.g:1445:57: (iv_ruleForStatementInit= ruleForStatementInit EOF )
            // InternalJavali.g:1446:2: iv_ruleForStatementInit= ruleForStatementInit EOF
            {
             newCompositeNode(grammarAccess.getForStatementInitRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForStatementInit=ruleForStatementInit();

            state._fsp--;

             current =iv_ruleForStatementInit; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForStatementInit"


    // $ANTLR start "ruleForStatementInit"
    // InternalJavali.g:1452:1: ruleForStatementInit returns [EObject current=null] : (this_VarDeclarationAssign_0= ruleVarDeclarationAssign | this_VarAssign_1= ruleVarAssign ) ;
    public final EObject ruleForStatementInit() throws RecognitionException {
        EObject current = null;

        EObject this_VarDeclarationAssign_0 = null;

        EObject this_VarAssign_1 = null;



        	enterRule();

        try {
            // InternalJavali.g:1458:2: ( (this_VarDeclarationAssign_0= ruleVarDeclarationAssign | this_VarAssign_1= ruleVarAssign ) )
            // InternalJavali.g:1459:2: (this_VarDeclarationAssign_0= ruleVarDeclarationAssign | this_VarAssign_1= ruleVarAssign )
            {
            // InternalJavali.g:1459:2: (this_VarDeclarationAssign_0= ruleVarDeclarationAssign | this_VarAssign_1= ruleVarAssign )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 50:
                    {
                    int LA18_2 = input.LA(3);

                    if ( ((LA18_2>=RULE_PRIMITIVE_VALUE && LA18_2<=RULE_ID)||LA18_2==21||(LA18_2>=48 && LA18_2<=49)||LA18_2==53) ) {
                        alt18=2;
                    }
                    else if ( (LA18_2==51) ) {
                        alt18=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 18, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 15:
                case 52:
                    {
                    alt18=2;
                    }
                    break;
                case RULE_ID:
                    {
                    alt18=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalJavali.g:1460:3: this_VarDeclarationAssign_0= ruleVarDeclarationAssign
                    {

                    			newCompositeNode(grammarAccess.getForStatementInitAccess().getVarDeclarationAssignParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarDeclarationAssign_0=ruleVarDeclarationAssign();

                    state._fsp--;


                    			current = this_VarDeclarationAssign_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalJavali.g:1469:3: this_VarAssign_1= ruleVarAssign
                    {

                    			newCompositeNode(grammarAccess.getForStatementInitAccess().getVarAssignParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarAssign_1=ruleVarAssign();

                    state._fsp--;


                    			current = this_VarAssign_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForStatementInit"


    // $ANTLR start "entryRuleForStatement"
    // InternalJavali.g:1481:1: entryRuleForStatement returns [EObject current=null] : iv_ruleForStatement= ruleForStatement EOF ;
    public final EObject entryRuleForStatement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForStatement = null;


        try {
            // InternalJavali.g:1481:53: (iv_ruleForStatement= ruleForStatement EOF )
            // InternalJavali.g:1482:2: iv_ruleForStatement= ruleForStatement EOF
            {
             newCompositeNode(grammarAccess.getForStatementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForStatement=ruleForStatement();

            state._fsp--;

             current =iv_ruleForStatement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForStatement"


    // $ANTLR start "ruleForStatement"
    // InternalJavali.g:1488:1: ruleForStatement returns [EObject current=null] : (this_VarAssign_0= ruleVarAssign | this_Increment_1= ruleIncrement | this_Decrement_2= ruleDecrement ) ;
    public final EObject ruleForStatement() throws RecognitionException {
        EObject current = null;

        EObject this_VarAssign_0 = null;

        EObject this_Increment_1 = null;

        EObject this_Decrement_2 = null;



        	enterRule();

        try {
            // InternalJavali.g:1494:2: ( (this_VarAssign_0= ruleVarAssign | this_Increment_1= ruleIncrement | this_Decrement_2= ruleDecrement ) )
            // InternalJavali.g:1495:2: (this_VarAssign_0= ruleVarAssign | this_Increment_1= ruleIncrement | this_Decrement_2= ruleDecrement )
            {
            // InternalJavali.g:1495:2: (this_VarAssign_0= ruleVarAssign | this_Increment_1= ruleIncrement | this_Decrement_2= ruleDecrement )
            int alt19=3;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 33:
                    {
                    alt19=3;
                    }
                    break;
                case 15:
                case 50:
                case 52:
                    {
                    alt19=1;
                    }
                    break;
                case 32:
                    {
                    alt19=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 19, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalJavali.g:1496:3: this_VarAssign_0= ruleVarAssign
                    {

                    			newCompositeNode(grammarAccess.getForStatementAccess().getVarAssignParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarAssign_0=ruleVarAssign();

                    state._fsp--;


                    			current = this_VarAssign_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalJavali.g:1505:3: this_Increment_1= ruleIncrement
                    {

                    			newCompositeNode(grammarAccess.getForStatementAccess().getIncrementParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Increment_1=ruleIncrement();

                    state._fsp--;


                    			current = this_Increment_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalJavali.g:1514:3: this_Decrement_2= ruleDecrement
                    {

                    			newCompositeNode(grammarAccess.getForStatementAccess().getDecrementParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Decrement_2=ruleDecrement();

                    state._fsp--;


                    			current = this_Decrement_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForStatement"


    // $ANTLR start "entryRuleIncrement"
    // InternalJavali.g:1526:1: entryRuleIncrement returns [EObject current=null] : iv_ruleIncrement= ruleIncrement EOF ;
    public final EObject entryRuleIncrement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIncrement = null;


        try {
            // InternalJavali.g:1526:50: (iv_ruleIncrement= ruleIncrement EOF )
            // InternalJavali.g:1527:2: iv_ruleIncrement= ruleIncrement EOF
            {
             newCompositeNode(grammarAccess.getIncrementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIncrement=ruleIncrement();

            state._fsp--;

             current =iv_ruleIncrement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIncrement"


    // $ANTLR start "ruleIncrement"
    // InternalJavali.g:1533:1: ruleIncrement returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '++' ) ;
    public final EObject ruleIncrement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_id_0_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1539:2: ( ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '++' ) )
            // InternalJavali.g:1540:2: ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '++' )
            {
            // InternalJavali.g:1540:2: ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '++' )
            // InternalJavali.g:1541:3: ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '++'
            {
            // InternalJavali.g:1541:3: ( (lv_id_0_0= ruleIdentifier ) )
            // InternalJavali.g:1542:4: (lv_id_0_0= ruleIdentifier )
            {
            // InternalJavali.g:1542:4: (lv_id_0_0= ruleIdentifier )
            // InternalJavali.g:1543:5: lv_id_0_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getIncrementAccess().getIdIdentifierParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_25);
            lv_id_0_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIncrementRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_0_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,32,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getIncrementAccess().getPlusSignPlusSignKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIncrement"


    // $ANTLR start "entryRuleDecrement"
    // InternalJavali.g:1568:1: entryRuleDecrement returns [EObject current=null] : iv_ruleDecrement= ruleDecrement EOF ;
    public final EObject entryRuleDecrement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDecrement = null;


        try {
            // InternalJavali.g:1568:50: (iv_ruleDecrement= ruleDecrement EOF )
            // InternalJavali.g:1569:2: iv_ruleDecrement= ruleDecrement EOF
            {
             newCompositeNode(grammarAccess.getDecrementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDecrement=ruleDecrement();

            state._fsp--;

             current =iv_ruleDecrement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDecrement"


    // $ANTLR start "ruleDecrement"
    // InternalJavali.g:1575:1: ruleDecrement returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '--' ) ;
    public final EObject ruleDecrement() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_id_0_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1581:2: ( ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '--' ) )
            // InternalJavali.g:1582:2: ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '--' )
            {
            // InternalJavali.g:1582:2: ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '--' )
            // InternalJavali.g:1583:3: ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '--'
            {
            // InternalJavali.g:1583:3: ( (lv_id_0_0= ruleIdentifier ) )
            // InternalJavali.g:1584:4: (lv_id_0_0= ruleIdentifier )
            {
            // InternalJavali.g:1584:4: (lv_id_0_0= ruleIdentifier )
            // InternalJavali.g:1585:5: lv_id_0_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getDecrementAccess().getIdIdentifierParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_26);
            lv_id_0_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getDecrementRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_0_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,33,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getDecrementAccess().getHyphenMinusHyphenMinusKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDecrement"


    // $ANTLR start "entryRuleExpression"
    // InternalJavali.g:1610:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalJavali.g:1610:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalJavali.g:1611:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalJavali.g:1617:1: ruleExpression returns [EObject current=null] : this_Or_0= ruleOr ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Or_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1623:2: (this_Or_0= ruleOr )
            // InternalJavali.g:1624:2: this_Or_0= ruleOr
            {

            		newCompositeNode(grammarAccess.getExpressionAccess().getOrParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_Or_0=ruleOr();

            state._fsp--;


            		current = this_Or_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleOr"
    // InternalJavali.g:1635:1: entryRuleOr returns [EObject current=null] : iv_ruleOr= ruleOr EOF ;
    public final EObject entryRuleOr() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOr = null;


        try {
            // InternalJavali.g:1635:43: (iv_ruleOr= ruleOr EOF )
            // InternalJavali.g:1636:2: iv_ruleOr= ruleOr EOF
            {
             newCompositeNode(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOr=ruleOr();

            state._fsp--;

             current =iv_ruleOr; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOr"


    // $ANTLR start "ruleOr"
    // InternalJavali.g:1642:1: ruleOr returns [EObject current=null] : (this_Xor_0= ruleXor ( () otherlv_2= '||' ( (lv_right_3_0= ruleXor ) ) )* ) ;
    public final EObject ruleOr() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Xor_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1648:2: ( (this_Xor_0= ruleXor ( () otherlv_2= '||' ( (lv_right_3_0= ruleXor ) ) )* ) )
            // InternalJavali.g:1649:2: (this_Xor_0= ruleXor ( () otherlv_2= '||' ( (lv_right_3_0= ruleXor ) ) )* )
            {
            // InternalJavali.g:1649:2: (this_Xor_0= ruleXor ( () otherlv_2= '||' ( (lv_right_3_0= ruleXor ) ) )* )
            // InternalJavali.g:1650:3: this_Xor_0= ruleXor ( () otherlv_2= '||' ( (lv_right_3_0= ruleXor ) ) )*
            {

            			newCompositeNode(grammarAccess.getOrAccess().getXorParserRuleCall_0());
            		
            pushFollow(FOLLOW_27);
            this_Xor_0=ruleXor();

            state._fsp--;


            			current = this_Xor_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalJavali.g:1658:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleXor ) ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==34) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalJavali.g:1659:4: () otherlv_2= '||' ( (lv_right_3_0= ruleXor ) )
            	    {
            	    // InternalJavali.g:1659:4: ()
            	    // InternalJavali.g:1660:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getOrAccess().getOrLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,34,FOLLOW_19); 

            	    				newLeafNode(otherlv_2, grammarAccess.getOrAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalJavali.g:1670:4: ( (lv_right_3_0= ruleXor ) )
            	    // InternalJavali.g:1671:5: (lv_right_3_0= ruleXor )
            	    {
            	    // InternalJavali.g:1671:5: (lv_right_3_0= ruleXor )
            	    // InternalJavali.g:1672:6: lv_right_3_0= ruleXor
            	    {

            	    						newCompositeNode(grammarAccess.getOrAccess().getRightXorParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_27);
            	    lv_right_3_0=ruleXor();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOrRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"pt.iscte.paddle.Javali.Xor");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOr"


    // $ANTLR start "entryRuleXor"
    // InternalJavali.g:1694:1: entryRuleXor returns [EObject current=null] : iv_ruleXor= ruleXor EOF ;
    public final EObject entryRuleXor() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXor = null;


        try {
            // InternalJavali.g:1694:44: (iv_ruleXor= ruleXor EOF )
            // InternalJavali.g:1695:2: iv_ruleXor= ruleXor EOF
            {
             newCompositeNode(grammarAccess.getXorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXor=ruleXor();

            state._fsp--;

             current =iv_ruleXor; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXor"


    // $ANTLR start "ruleXor"
    // InternalJavali.g:1701:1: ruleXor returns [EObject current=null] : (this_And_0= ruleAnd ( () otherlv_2= '^' ( (lv_right_3_0= ruleAnd ) ) )* ) ;
    public final EObject ruleXor() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_And_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1707:2: ( (this_And_0= ruleAnd ( () otherlv_2= '^' ( (lv_right_3_0= ruleAnd ) ) )* ) )
            // InternalJavali.g:1708:2: (this_And_0= ruleAnd ( () otherlv_2= '^' ( (lv_right_3_0= ruleAnd ) ) )* )
            {
            // InternalJavali.g:1708:2: (this_And_0= ruleAnd ( () otherlv_2= '^' ( (lv_right_3_0= ruleAnd ) ) )* )
            // InternalJavali.g:1709:3: this_And_0= ruleAnd ( () otherlv_2= '^' ( (lv_right_3_0= ruleAnd ) ) )*
            {

            			newCompositeNode(grammarAccess.getXorAccess().getAndParserRuleCall_0());
            		
            pushFollow(FOLLOW_28);
            this_And_0=ruleAnd();

            state._fsp--;


            			current = this_And_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalJavali.g:1717:3: ( () otherlv_2= '^' ( (lv_right_3_0= ruleAnd ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==35) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalJavali.g:1718:4: () otherlv_2= '^' ( (lv_right_3_0= ruleAnd ) )
            	    {
            	    // InternalJavali.g:1718:4: ()
            	    // InternalJavali.g:1719:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXorAccess().getXorLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,35,FOLLOW_19); 

            	    				newLeafNode(otherlv_2, grammarAccess.getXorAccess().getCircumflexAccentKeyword_1_1());
            	    			
            	    // InternalJavali.g:1729:4: ( (lv_right_3_0= ruleAnd ) )
            	    // InternalJavali.g:1730:5: (lv_right_3_0= ruleAnd )
            	    {
            	    // InternalJavali.g:1730:5: (lv_right_3_0= ruleAnd )
            	    // InternalJavali.g:1731:6: lv_right_3_0= ruleAnd
            	    {

            	    						newCompositeNode(grammarAccess.getXorAccess().getRightAndParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_28);
            	    lv_right_3_0=ruleAnd();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXorRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"pt.iscte.paddle.Javali.And");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXor"


    // $ANTLR start "entryRuleAnd"
    // InternalJavali.g:1753:1: entryRuleAnd returns [EObject current=null] : iv_ruleAnd= ruleAnd EOF ;
    public final EObject entryRuleAnd() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAnd = null;


        try {
            // InternalJavali.g:1753:44: (iv_ruleAnd= ruleAnd EOF )
            // InternalJavali.g:1754:2: iv_ruleAnd= ruleAnd EOF
            {
             newCompositeNode(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAnd=ruleAnd();

            state._fsp--;

             current =iv_ruleAnd; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAnd"


    // $ANTLR start "ruleAnd"
    // InternalJavali.g:1760:1: ruleAnd returns [EObject current=null] : (this_Equality_0= ruleEquality ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEquality ) ) )* ) ;
    public final EObject ruleAnd() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_Equality_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1766:2: ( (this_Equality_0= ruleEquality ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEquality ) ) )* ) )
            // InternalJavali.g:1767:2: (this_Equality_0= ruleEquality ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEquality ) ) )* )
            {
            // InternalJavali.g:1767:2: (this_Equality_0= ruleEquality ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEquality ) ) )* )
            // InternalJavali.g:1768:3: this_Equality_0= ruleEquality ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEquality ) ) )*
            {

            			newCompositeNode(grammarAccess.getAndAccess().getEqualityParserRuleCall_0());
            		
            pushFollow(FOLLOW_29);
            this_Equality_0=ruleEquality();

            state._fsp--;


            			current = this_Equality_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalJavali.g:1776:3: ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEquality ) ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==36) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalJavali.g:1777:4: () otherlv_2= '&&' ( (lv_right_3_0= ruleEquality ) )
            	    {
            	    // InternalJavali.g:1777:4: ()
            	    // InternalJavali.g:1778:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAndAccess().getAndLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,36,FOLLOW_19); 

            	    				newLeafNode(otherlv_2, grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalJavali.g:1788:4: ( (lv_right_3_0= ruleEquality ) )
            	    // InternalJavali.g:1789:5: (lv_right_3_0= ruleEquality )
            	    {
            	    // InternalJavali.g:1789:5: (lv_right_3_0= ruleEquality )
            	    // InternalJavali.g:1790:6: lv_right_3_0= ruleEquality
            	    {

            	    						newCompositeNode(grammarAccess.getAndAccess().getRightEqualityParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_29);
            	    lv_right_3_0=ruleEquality();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAndRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"pt.iscte.paddle.Javali.Equality");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAnd"


    // $ANTLR start "entryRuleEquality"
    // InternalJavali.g:1812:1: entryRuleEquality returns [EObject current=null] : iv_ruleEquality= ruleEquality EOF ;
    public final EObject entryRuleEquality() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEquality = null;


        try {
            // InternalJavali.g:1812:49: (iv_ruleEquality= ruleEquality EOF )
            // InternalJavali.g:1813:2: iv_ruleEquality= ruleEquality EOF
            {
             newCompositeNode(grammarAccess.getEqualityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEquality=ruleEquality();

            state._fsp--;

             current =iv_ruleEquality; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEquality"


    // $ANTLR start "ruleEquality"
    // InternalJavali.g:1819:1: ruleEquality returns [EObject current=null] : (this_Relation_0= ruleRelation ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleRelation ) ) )* ) ;
    public final EObject ruleEquality() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_1=null;
        Token lv_operator_2_2=null;
        EObject this_Relation_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1825:2: ( (this_Relation_0= ruleRelation ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleRelation ) ) )* ) )
            // InternalJavali.g:1826:2: (this_Relation_0= ruleRelation ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleRelation ) ) )* )
            {
            // InternalJavali.g:1826:2: (this_Relation_0= ruleRelation ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleRelation ) ) )* )
            // InternalJavali.g:1827:3: this_Relation_0= ruleRelation ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleRelation ) ) )*
            {

            			newCompositeNode(grammarAccess.getEqualityAccess().getRelationParserRuleCall_0());
            		
            pushFollow(FOLLOW_30);
            this_Relation_0=ruleRelation();

            state._fsp--;


            			current = this_Relation_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalJavali.g:1835:3: ( () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleRelation ) ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=37 && LA24_0<=38)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalJavali.g:1836:4: () ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleRelation ) )
            	    {
            	    // InternalJavali.g:1836:4: ()
            	    // InternalJavali.g:1837:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getEqualityAccess().getEqualityLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalJavali.g:1843:4: ( ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) ) )
            	    // InternalJavali.g:1844:5: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) )
            	    {
            	    // InternalJavali.g:1844:5: ( (lv_operator_2_1= '==' | lv_operator_2_2= '!=' ) )
            	    // InternalJavali.g:1845:6: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' )
            	    {
            	    // InternalJavali.g:1845:6: (lv_operator_2_1= '==' | lv_operator_2_2= '!=' )
            	    int alt23=2;
            	    int LA23_0 = input.LA(1);

            	    if ( (LA23_0==37) ) {
            	        alt23=1;
            	    }
            	    else if ( (LA23_0==38) ) {
            	        alt23=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 23, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt23) {
            	        case 1 :
            	            // InternalJavali.g:1846:7: lv_operator_2_1= '=='
            	            {
            	            lv_operator_2_1=(Token)match(input,37,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_1, grammarAccess.getEqualityAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getEqualityRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalJavali.g:1857:7: lv_operator_2_2= '!='
            	            {
            	            lv_operator_2_2=(Token)match(input,38,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_2, grammarAccess.getEqualityAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getEqualityRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_2, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalJavali.g:1870:4: ( (lv_right_3_0= ruleRelation ) )
            	    // InternalJavali.g:1871:5: (lv_right_3_0= ruleRelation )
            	    {
            	    // InternalJavali.g:1871:5: (lv_right_3_0= ruleRelation )
            	    // InternalJavali.g:1872:6: lv_right_3_0= ruleRelation
            	    {

            	    						newCompositeNode(grammarAccess.getEqualityAccess().getRightRelationParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_30);
            	    lv_right_3_0=ruleRelation();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getEqualityRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"pt.iscte.paddle.Javali.Relation");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEquality"


    // $ANTLR start "entryRuleRelation"
    // InternalJavali.g:1894:1: entryRuleRelation returns [EObject current=null] : iv_ruleRelation= ruleRelation EOF ;
    public final EObject entryRuleRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelation = null;


        try {
            // InternalJavali.g:1894:49: (iv_ruleRelation= ruleRelation EOF )
            // InternalJavali.g:1895:2: iv_ruleRelation= ruleRelation EOF
            {
             newCompositeNode(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelation=ruleRelation();

            state._fsp--;

             current =iv_ruleRelation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelation"


    // $ANTLR start "ruleRelation"
    // InternalJavali.g:1901:1: ruleRelation returns [EObject current=null] : (this_Addition_0= ruleAddition ( () ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) ) ( (lv_right_3_0= ruleAddition ) ) )* ) ;
    public final EObject ruleRelation() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_1=null;
        Token lv_operator_2_2=null;
        Token lv_operator_2_3=null;
        Token lv_operator_2_4=null;
        EObject this_Addition_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:1907:2: ( (this_Addition_0= ruleAddition ( () ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) ) ( (lv_right_3_0= ruleAddition ) ) )* ) )
            // InternalJavali.g:1908:2: (this_Addition_0= ruleAddition ( () ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) ) ( (lv_right_3_0= ruleAddition ) ) )* )
            {
            // InternalJavali.g:1908:2: (this_Addition_0= ruleAddition ( () ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) ) ( (lv_right_3_0= ruleAddition ) ) )* )
            // InternalJavali.g:1909:3: this_Addition_0= ruleAddition ( () ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) ) ( (lv_right_3_0= ruleAddition ) ) )*
            {

            			newCompositeNode(grammarAccess.getRelationAccess().getAdditionParserRuleCall_0());
            		
            pushFollow(FOLLOW_31);
            this_Addition_0=ruleAddition();

            state._fsp--;


            			current = this_Addition_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalJavali.g:1917:3: ( () ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) ) ( (lv_right_3_0= ruleAddition ) ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=39 && LA26_0<=42)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalJavali.g:1918:4: () ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) ) ( (lv_right_3_0= ruleAddition ) )
            	    {
            	    // InternalJavali.g:1918:4: ()
            	    // InternalJavali.g:1919:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getRelationAccess().getRelationLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalJavali.g:1925:4: ( ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) ) )
            	    // InternalJavali.g:1926:5: ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) )
            	    {
            	    // InternalJavali.g:1926:5: ( (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' ) )
            	    // InternalJavali.g:1927:6: (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' )
            	    {
            	    // InternalJavali.g:1927:6: (lv_operator_2_1= '<' | lv_operator_2_2= '<=' | lv_operator_2_3= '>' | lv_operator_2_4= '>=' )
            	    int alt25=4;
            	    switch ( input.LA(1) ) {
            	    case 39:
            	        {
            	        alt25=1;
            	        }
            	        break;
            	    case 40:
            	        {
            	        alt25=2;
            	        }
            	        break;
            	    case 41:
            	        {
            	        alt25=3;
            	        }
            	        break;
            	    case 42:
            	        {
            	        alt25=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 25, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt25) {
            	        case 1 :
            	            // InternalJavali.g:1928:7: lv_operator_2_1= '<'
            	            {
            	            lv_operator_2_1=(Token)match(input,39,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_1, grammarAccess.getRelationAccess().getOperatorLessThanSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getRelationRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalJavali.g:1939:7: lv_operator_2_2= '<='
            	            {
            	            lv_operator_2_2=(Token)match(input,40,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_2, grammarAccess.getRelationAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getRelationRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalJavali.g:1950:7: lv_operator_2_3= '>'
            	            {
            	            lv_operator_2_3=(Token)match(input,41,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_3, grammarAccess.getRelationAccess().getOperatorGreaterThanSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getRelationRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalJavali.g:1961:7: lv_operator_2_4= '>='
            	            {
            	            lv_operator_2_4=(Token)match(input,42,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_4, grammarAccess.getRelationAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_3());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getRelationRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_4, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalJavali.g:1974:4: ( (lv_right_3_0= ruleAddition ) )
            	    // InternalJavali.g:1975:5: (lv_right_3_0= ruleAddition )
            	    {
            	    // InternalJavali.g:1975:5: (lv_right_3_0= ruleAddition )
            	    // InternalJavali.g:1976:6: lv_right_3_0= ruleAddition
            	    {

            	    						newCompositeNode(grammarAccess.getRelationAccess().getRightAdditionParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_31);
            	    lv_right_3_0=ruleAddition();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getRelationRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"pt.iscte.paddle.Javali.Addition");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleAddition"
    // InternalJavali.g:1998:1: entryRuleAddition returns [EObject current=null] : iv_ruleAddition= ruleAddition EOF ;
    public final EObject entryRuleAddition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddition = null;


        try {
            // InternalJavali.g:1998:49: (iv_ruleAddition= ruleAddition EOF )
            // InternalJavali.g:1999:2: iv_ruleAddition= ruleAddition EOF
            {
             newCompositeNode(grammarAccess.getAdditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAddition=ruleAddition();

            state._fsp--;

             current =iv_ruleAddition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAddition"


    // $ANTLR start "ruleAddition"
    // InternalJavali.g:2005:1: ruleAddition returns [EObject current=null] : (this_Multiplication_0= ruleMultiplication ( () ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) ) ( (lv_right_3_0= ruleMultiplication ) ) )* ) ;
    public final EObject ruleAddition() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_1=null;
        Token lv_operator_2_2=null;
        EObject this_Multiplication_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:2011:2: ( (this_Multiplication_0= ruleMultiplication ( () ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) ) ( (lv_right_3_0= ruleMultiplication ) ) )* ) )
            // InternalJavali.g:2012:2: (this_Multiplication_0= ruleMultiplication ( () ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) ) ( (lv_right_3_0= ruleMultiplication ) ) )* )
            {
            // InternalJavali.g:2012:2: (this_Multiplication_0= ruleMultiplication ( () ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) ) ( (lv_right_3_0= ruleMultiplication ) ) )* )
            // InternalJavali.g:2013:3: this_Multiplication_0= ruleMultiplication ( () ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) ) ( (lv_right_3_0= ruleMultiplication ) ) )*
            {

            			newCompositeNode(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0());
            		
            pushFollow(FOLLOW_32);
            this_Multiplication_0=ruleMultiplication();

            state._fsp--;


            			current = this_Multiplication_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalJavali.g:2021:3: ( () ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) ) ( (lv_right_3_0= ruleMultiplication ) ) )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( ((LA28_0>=43 && LA28_0<=44)) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalJavali.g:2022:4: () ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) ) ( (lv_right_3_0= ruleMultiplication ) )
            	    {
            	    // InternalJavali.g:2022:4: ()
            	    // InternalJavali.g:2023:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAdditionAccess().getAdditionLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalJavali.g:2029:4: ( ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) ) )
            	    // InternalJavali.g:2030:5: ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) )
            	    {
            	    // InternalJavali.g:2030:5: ( (lv_operator_2_1= '+' | lv_operator_2_2= '-' ) )
            	    // InternalJavali.g:2031:6: (lv_operator_2_1= '+' | lv_operator_2_2= '-' )
            	    {
            	    // InternalJavali.g:2031:6: (lv_operator_2_1= '+' | lv_operator_2_2= '-' )
            	    int alt27=2;
            	    int LA27_0 = input.LA(1);

            	    if ( (LA27_0==43) ) {
            	        alt27=1;
            	    }
            	    else if ( (LA27_0==44) ) {
            	        alt27=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 27, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt27) {
            	        case 1 :
            	            // InternalJavali.g:2032:7: lv_operator_2_1= '+'
            	            {
            	            lv_operator_2_1=(Token)match(input,43,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_1, grammarAccess.getAdditionAccess().getOperatorPlusSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getAdditionRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalJavali.g:2043:7: lv_operator_2_2= '-'
            	            {
            	            lv_operator_2_2=(Token)match(input,44,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_2, grammarAccess.getAdditionAccess().getOperatorHyphenMinusKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getAdditionRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_2, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalJavali.g:2056:4: ( (lv_right_3_0= ruleMultiplication ) )
            	    // InternalJavali.g:2057:5: (lv_right_3_0= ruleMultiplication )
            	    {
            	    // InternalJavali.g:2057:5: (lv_right_3_0= ruleMultiplication )
            	    // InternalJavali.g:2058:6: lv_right_3_0= ruleMultiplication
            	    {

            	    						newCompositeNode(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_32);
            	    lv_right_3_0=ruleMultiplication();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAdditionRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"pt.iscte.paddle.Javali.Multiplication");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAddition"


    // $ANTLR start "entryRuleMultiplication"
    // InternalJavali.g:2080:1: entryRuleMultiplication returns [EObject current=null] : iv_ruleMultiplication= ruleMultiplication EOF ;
    public final EObject entryRuleMultiplication() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplication = null;


        try {
            // InternalJavali.g:2080:55: (iv_ruleMultiplication= ruleMultiplication EOF )
            // InternalJavali.g:2081:2: iv_ruleMultiplication= ruleMultiplication EOF
            {
             newCompositeNode(grammarAccess.getMultiplicationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMultiplication=ruleMultiplication();

            state._fsp--;

             current =iv_ruleMultiplication; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMultiplication"


    // $ANTLR start "ruleMultiplication"
    // InternalJavali.g:2087:1: ruleMultiplication returns [EObject current=null] : (this_Primary_0= rulePrimary ( () ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) ) ( (lv_right_3_0= rulePrimary ) ) )* ) ;
    public final EObject ruleMultiplication() throws RecognitionException {
        EObject current = null;

        Token lv_operator_2_1=null;
        Token lv_operator_2_2=null;
        Token lv_operator_2_3=null;
        EObject this_Primary_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:2093:2: ( (this_Primary_0= rulePrimary ( () ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) ) ( (lv_right_3_0= rulePrimary ) ) )* ) )
            // InternalJavali.g:2094:2: (this_Primary_0= rulePrimary ( () ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) ) ( (lv_right_3_0= rulePrimary ) ) )* )
            {
            // InternalJavali.g:2094:2: (this_Primary_0= rulePrimary ( () ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) ) ( (lv_right_3_0= rulePrimary ) ) )* )
            // InternalJavali.g:2095:3: this_Primary_0= rulePrimary ( () ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) ) ( (lv_right_3_0= rulePrimary ) ) )*
            {

            			newCompositeNode(grammarAccess.getMultiplicationAccess().getPrimaryParserRuleCall_0());
            		
            pushFollow(FOLLOW_33);
            this_Primary_0=rulePrimary();

            state._fsp--;


            			current = this_Primary_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalJavali.g:2103:3: ( () ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) ) ( (lv_right_3_0= rulePrimary ) ) )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=45 && LA30_0<=47)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalJavali.g:2104:4: () ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) ) ( (lv_right_3_0= rulePrimary ) )
            	    {
            	    // InternalJavali.g:2104:4: ()
            	    // InternalJavali.g:2105:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMultiplicationAccess().getMultiplicationLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalJavali.g:2111:4: ( ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) ) )
            	    // InternalJavali.g:2112:5: ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) )
            	    {
            	    // InternalJavali.g:2112:5: ( (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' ) )
            	    // InternalJavali.g:2113:6: (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' )
            	    {
            	    // InternalJavali.g:2113:6: (lv_operator_2_1= '*' | lv_operator_2_2= '/' | lv_operator_2_3= '%' )
            	    int alt29=3;
            	    switch ( input.LA(1) ) {
            	    case 45:
            	        {
            	        alt29=1;
            	        }
            	        break;
            	    case 46:
            	        {
            	        alt29=2;
            	        }
            	        break;
            	    case 47:
            	        {
            	        alt29=3;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 29, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt29) {
            	        case 1 :
            	            // InternalJavali.g:2114:7: lv_operator_2_1= '*'
            	            {
            	            lv_operator_2_1=(Token)match(input,45,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_1, grammarAccess.getMultiplicationAccess().getOperatorAsteriskKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMultiplicationRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalJavali.g:2125:7: lv_operator_2_2= '/'
            	            {
            	            lv_operator_2_2=(Token)match(input,46,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_2, grammarAccess.getMultiplicationAccess().getOperatorSolidusKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMultiplicationRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalJavali.g:2136:7: lv_operator_2_3= '%'
            	            {
            	            lv_operator_2_3=(Token)match(input,47,FOLLOW_19); 

            	            							newLeafNode(lv_operator_2_3, grammarAccess.getMultiplicationAccess().getOperatorPercentSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMultiplicationRule());
            	            							}
            	            							setWithLastConsumed(current, "operator", lv_operator_2_3, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalJavali.g:2149:4: ( (lv_right_3_0= rulePrimary ) )
            	    // InternalJavali.g:2150:5: (lv_right_3_0= rulePrimary )
            	    {
            	    // InternalJavali.g:2150:5: (lv_right_3_0= rulePrimary )
            	    // InternalJavali.g:2151:6: lv_right_3_0= rulePrimary
            	    {

            	    						newCompositeNode(grammarAccess.getMultiplicationAccess().getRightPrimaryParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_33);
            	    lv_right_3_0=rulePrimary();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMultiplicationRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"pt.iscte.paddle.Javali.Primary");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMultiplication"


    // $ANTLR start "entryRulePrimary"
    // InternalJavali.g:2173:1: entryRulePrimary returns [EObject current=null] : iv_rulePrimary= rulePrimary EOF ;
    public final EObject entryRulePrimary() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimary = null;


        try {
            // InternalJavali.g:2173:48: (iv_rulePrimary= rulePrimary EOF )
            // InternalJavali.g:2174:2: iv_rulePrimary= rulePrimary EOF
            {
             newCompositeNode(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimary=rulePrimary();

            state._fsp--;

             current =iv_rulePrimary; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // InternalJavali.g:2180:1: rulePrimary returns [EObject current=null] : (this_Literal_0= ruleLiteral | this_Null_1= ruleNull | this_ProcCall_2= ruleProcCall | this_VarExpression_3= ruleVarExpression | this_NewArray_4= ruleNewArray | this_NewObject_5= ruleNewObject | (otherlv_6= '!' this_Primary_7= rulePrimary ) | (otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')' ) ) ;
    public final EObject rulePrimary() throws RecognitionException {
        EObject current = null;

        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject this_Literal_0 = null;

        EObject this_Null_1 = null;

        EObject this_ProcCall_2 = null;

        EObject this_VarExpression_3 = null;

        EObject this_NewArray_4 = null;

        EObject this_NewObject_5 = null;

        EObject this_Primary_7 = null;

        EObject this_Expression_9 = null;



        	enterRule();

        try {
            // InternalJavali.g:2186:2: ( (this_Literal_0= ruleLiteral | this_Null_1= ruleNull | this_ProcCall_2= ruleProcCall | this_VarExpression_3= ruleVarExpression | this_NewArray_4= ruleNewArray | this_NewObject_5= ruleNewObject | (otherlv_6= '!' this_Primary_7= rulePrimary ) | (otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')' ) ) )
            // InternalJavali.g:2187:2: (this_Literal_0= ruleLiteral | this_Null_1= ruleNull | this_ProcCall_2= ruleProcCall | this_VarExpression_3= ruleVarExpression | this_NewArray_4= ruleNewArray | this_NewObject_5= ruleNewObject | (otherlv_6= '!' this_Primary_7= rulePrimary ) | (otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')' ) )
            {
            // InternalJavali.g:2187:2: (this_Literal_0= ruleLiteral | this_Null_1= ruleNull | this_ProcCall_2= ruleProcCall | this_VarExpression_3= ruleVarExpression | this_NewArray_4= ruleNewArray | this_NewObject_5= ruleNewObject | (otherlv_6= '!' this_Primary_7= rulePrimary ) | (otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')' ) )
            int alt31=8;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // InternalJavali.g:2188:3: this_Literal_0= ruleLiteral
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getLiteralParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Literal_0=ruleLiteral();

                    state._fsp--;


                    			current = this_Literal_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalJavali.g:2197:3: this_Null_1= ruleNull
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getNullParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Null_1=ruleNull();

                    state._fsp--;


                    			current = this_Null_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalJavali.g:2206:3: this_ProcCall_2= ruleProcCall
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getProcCallParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ProcCall_2=ruleProcCall();

                    state._fsp--;


                    			current = this_ProcCall_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalJavali.g:2215:3: this_VarExpression_3= ruleVarExpression
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getVarExpressionParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarExpression_3=ruleVarExpression();

                    state._fsp--;


                    			current = this_VarExpression_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalJavali.g:2224:3: this_NewArray_4= ruleNewArray
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getNewArrayParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_NewArray_4=ruleNewArray();

                    state._fsp--;


                    			current = this_NewArray_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalJavali.g:2233:3: this_NewObject_5= ruleNewObject
                    {

                    			newCompositeNode(grammarAccess.getPrimaryAccess().getNewObjectParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_NewObject_5=ruleNewObject();

                    state._fsp--;


                    			current = this_NewObject_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalJavali.g:2242:3: (otherlv_6= '!' this_Primary_7= rulePrimary )
                    {
                    // InternalJavali.g:2242:3: (otherlv_6= '!' this_Primary_7= rulePrimary )
                    // InternalJavali.g:2243:4: otherlv_6= '!' this_Primary_7= rulePrimary
                    {
                    otherlv_6=(Token)match(input,48,FOLLOW_19); 

                    				newLeafNode(otherlv_6, grammarAccess.getPrimaryAccess().getExclamationMarkKeyword_6_0());
                    			

                    				newCompositeNode(grammarAccess.getPrimaryAccess().getPrimaryParserRuleCall_6_1());
                    			
                    pushFollow(FOLLOW_2);
                    this_Primary_7=rulePrimary();

                    state._fsp--;


                    				current = this_Primary_7;
                    				afterParserOrEnumRuleCall();
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalJavali.g:2257:3: (otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')' )
                    {
                    // InternalJavali.g:2257:3: (otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')' )
                    // InternalJavali.g:2258:4: otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')'
                    {
                    otherlv_8=(Token)match(input,21,FOLLOW_19); 

                    				newLeafNode(otherlv_8, grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_7_0());
                    			

                    				newCompositeNode(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_7_1());
                    			
                    pushFollow(FOLLOW_20);
                    this_Expression_9=ruleExpression();

                    state._fsp--;


                    				current = this_Expression_9;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_10=(Token)match(input,23,FOLLOW_2); 

                    				newLeafNode(otherlv_10, grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_7_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimary"


    // $ANTLR start "entryRuleLiteral"
    // InternalJavali.g:2279:1: entryRuleLiteral returns [EObject current=null] : iv_ruleLiteral= ruleLiteral EOF ;
    public final EObject entryRuleLiteral() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteral = null;


        try {
            // InternalJavali.g:2279:48: (iv_ruleLiteral= ruleLiteral EOF )
            // InternalJavali.g:2280:2: iv_ruleLiteral= ruleLiteral EOF
            {
             newCompositeNode(grammarAccess.getLiteralRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLiteral=ruleLiteral();

            state._fsp--;

             current =iv_ruleLiteral; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLiteral"


    // $ANTLR start "ruleLiteral"
    // InternalJavali.g:2286:1: ruleLiteral returns [EObject current=null] : ( (lv_value_0_0= RULE_PRIMITIVE_VALUE ) ) ;
    public final EObject ruleLiteral() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalJavali.g:2292:2: ( ( (lv_value_0_0= RULE_PRIMITIVE_VALUE ) ) )
            // InternalJavali.g:2293:2: ( (lv_value_0_0= RULE_PRIMITIVE_VALUE ) )
            {
            // InternalJavali.g:2293:2: ( (lv_value_0_0= RULE_PRIMITIVE_VALUE ) )
            // InternalJavali.g:2294:3: (lv_value_0_0= RULE_PRIMITIVE_VALUE )
            {
            // InternalJavali.g:2294:3: (lv_value_0_0= RULE_PRIMITIVE_VALUE )
            // InternalJavali.g:2295:4: lv_value_0_0= RULE_PRIMITIVE_VALUE
            {
            lv_value_0_0=(Token)match(input,RULE_PRIMITIVE_VALUE,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getLiteralAccess().getValuePRIMITIVE_VALUETerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getLiteralRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"pt.iscte.paddle.Javali.PRIMITIVE_VALUE");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLiteral"


    // $ANTLR start "entryRuleNull"
    // InternalJavali.g:2314:1: entryRuleNull returns [EObject current=null] : iv_ruleNull= ruleNull EOF ;
    public final EObject entryRuleNull() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNull = null;


        try {
            // InternalJavali.g:2314:45: (iv_ruleNull= ruleNull EOF )
            // InternalJavali.g:2315:2: iv_ruleNull= ruleNull EOF
            {
             newCompositeNode(grammarAccess.getNullRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNull=ruleNull();

            state._fsp--;

             current =iv_ruleNull; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNull"


    // $ANTLR start "ruleNull"
    // InternalJavali.g:2321:1: ruleNull returns [EObject current=null] : ( () otherlv_1= 'null' ) ;
    public final EObject ruleNull() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalJavali.g:2327:2: ( ( () otherlv_1= 'null' ) )
            // InternalJavali.g:2328:2: ( () otherlv_1= 'null' )
            {
            // InternalJavali.g:2328:2: ( () otherlv_1= 'null' )
            // InternalJavali.g:2329:3: () otherlv_1= 'null'
            {
            // InternalJavali.g:2329:3: ()
            // InternalJavali.g:2330:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getNullAccess().getNullAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,49,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getNullAccess().getNullKeyword_1());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNull"


    // $ANTLR start "entryRuleVarExpression"
    // InternalJavali.g:2344:1: entryRuleVarExpression returns [EObject current=null] : iv_ruleVarExpression= ruleVarExpression EOF ;
    public final EObject entryRuleVarExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarExpression = null;


        try {
            // InternalJavali.g:2344:54: (iv_ruleVarExpression= ruleVarExpression EOF )
            // InternalJavali.g:2345:2: iv_ruleVarExpression= ruleVarExpression EOF
            {
             newCompositeNode(grammarAccess.getVarExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarExpression=ruleVarExpression();

            state._fsp--;

             current =iv_ruleVarExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarExpression"


    // $ANTLR start "ruleVarExpression"
    // InternalJavali.g:2351:1: ruleVarExpression returns [EObject current=null] : ( ( ( (lv_parts_0_0= ruleIdentifier ) ) (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )* ) (otherlv_4= '.' ( (lv_parts_5_0= ruleIdentifier ) ) (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )* )* ) ;
    public final EObject ruleVarExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_parts_0_0 = null;

        EObject lv_arrayIndexes_2_0 = null;

        EObject lv_parts_5_0 = null;

        EObject lv_arrayIndexes_7_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:2357:2: ( ( ( ( (lv_parts_0_0= ruleIdentifier ) ) (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )* ) (otherlv_4= '.' ( (lv_parts_5_0= ruleIdentifier ) ) (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )* )* ) )
            // InternalJavali.g:2358:2: ( ( ( (lv_parts_0_0= ruleIdentifier ) ) (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )* ) (otherlv_4= '.' ( (lv_parts_5_0= ruleIdentifier ) ) (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )* )* )
            {
            // InternalJavali.g:2358:2: ( ( ( (lv_parts_0_0= ruleIdentifier ) ) (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )* ) (otherlv_4= '.' ( (lv_parts_5_0= ruleIdentifier ) ) (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )* )* )
            // InternalJavali.g:2359:3: ( ( (lv_parts_0_0= ruleIdentifier ) ) (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )* ) (otherlv_4= '.' ( (lv_parts_5_0= ruleIdentifier ) ) (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )* )*
            {
            // InternalJavali.g:2359:3: ( ( (lv_parts_0_0= ruleIdentifier ) ) (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )* )
            // InternalJavali.g:2360:4: ( (lv_parts_0_0= ruleIdentifier ) ) (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )*
            {
            // InternalJavali.g:2360:4: ( (lv_parts_0_0= ruleIdentifier ) )
            // InternalJavali.g:2361:5: (lv_parts_0_0= ruleIdentifier )
            {
            // InternalJavali.g:2361:5: (lv_parts_0_0= ruleIdentifier )
            // InternalJavali.g:2362:6: lv_parts_0_0= ruleIdentifier
            {

            						newCompositeNode(grammarAccess.getVarExpressionAccess().getPartsIdentifierParserRuleCall_0_0_0());
            					
            pushFollow(FOLLOW_34);
            lv_parts_0_0=ruleIdentifier();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getVarExpressionRule());
            						}
            						add(
            							current,
            							"parts",
            							lv_parts_0_0,
            							"pt.iscte.paddle.Javali.Identifier");
            						afterParserOrEnumRuleCall();
            					

            }


            }

            // InternalJavali.g:2379:4: (otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']' )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==50) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalJavali.g:2380:5: otherlv_1= '[' ( (lv_arrayIndexes_2_0= ruleExpression ) ) otherlv_3= ']'
            	    {
            	    otherlv_1=(Token)match(input,50,FOLLOW_19); 

            	    					newLeafNode(otherlv_1, grammarAccess.getVarExpressionAccess().getLeftSquareBracketKeyword_0_1_0());
            	    				
            	    // InternalJavali.g:2384:5: ( (lv_arrayIndexes_2_0= ruleExpression ) )
            	    // InternalJavali.g:2385:6: (lv_arrayIndexes_2_0= ruleExpression )
            	    {
            	    // InternalJavali.g:2385:6: (lv_arrayIndexes_2_0= ruleExpression )
            	    // InternalJavali.g:2386:7: lv_arrayIndexes_2_0= ruleExpression
            	    {

            	    							newCompositeNode(grammarAccess.getVarExpressionAccess().getArrayIndexesExpressionParserRuleCall_0_1_1_0());
            	    						
            	    pushFollow(FOLLOW_35);
            	    lv_arrayIndexes_2_0=ruleExpression();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getVarExpressionRule());
            	    							}
            	    							add(
            	    								current,
            	    								"arrayIndexes",
            	    								lv_arrayIndexes_2_0,
            	    								"pt.iscte.paddle.Javali.Expression");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }

            	    otherlv_3=(Token)match(input,51,FOLLOW_34); 

            	    					newLeafNode(otherlv_3, grammarAccess.getVarExpressionAccess().getRightSquareBracketKeyword_0_1_2());
            	    				

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }

            // InternalJavali.g:2409:3: (otherlv_4= '.' ( (lv_parts_5_0= ruleIdentifier ) ) (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )* )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==52) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalJavali.g:2410:4: otherlv_4= '.' ( (lv_parts_5_0= ruleIdentifier ) ) (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )*
            	    {
            	    otherlv_4=(Token)match(input,52,FOLLOW_5); 

            	    				newLeafNode(otherlv_4, grammarAccess.getVarExpressionAccess().getFullStopKeyword_1_0());
            	    			
            	    // InternalJavali.g:2414:4: ( (lv_parts_5_0= ruleIdentifier ) )
            	    // InternalJavali.g:2415:5: (lv_parts_5_0= ruleIdentifier )
            	    {
            	    // InternalJavali.g:2415:5: (lv_parts_5_0= ruleIdentifier )
            	    // InternalJavali.g:2416:6: lv_parts_5_0= ruleIdentifier
            	    {

            	    						newCompositeNode(grammarAccess.getVarExpressionAccess().getPartsIdentifierParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_parts_5_0=ruleIdentifier();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getVarExpressionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"parts",
            	    							lv_parts_5_0,
            	    							"pt.iscte.paddle.Javali.Identifier");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    // InternalJavali.g:2433:4: (otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']' )*
            	    loop33:
            	    do {
            	        int alt33=2;
            	        int LA33_0 = input.LA(1);

            	        if ( (LA33_0==50) ) {
            	            alt33=1;
            	        }


            	        switch (alt33) {
            	    	case 1 :
            	    	    // InternalJavali.g:2434:5: otherlv_6= '[' ( (lv_arrayIndexes_7_0= ruleExpression ) ) otherlv_8= ']'
            	    	    {
            	    	    otherlv_6=(Token)match(input,50,FOLLOW_19); 

            	    	    					newLeafNode(otherlv_6, grammarAccess.getVarExpressionAccess().getLeftSquareBracketKeyword_1_2_0());
            	    	    				
            	    	    // InternalJavali.g:2438:5: ( (lv_arrayIndexes_7_0= ruleExpression ) )
            	    	    // InternalJavali.g:2439:6: (lv_arrayIndexes_7_0= ruleExpression )
            	    	    {
            	    	    // InternalJavali.g:2439:6: (lv_arrayIndexes_7_0= ruleExpression )
            	    	    // InternalJavali.g:2440:7: lv_arrayIndexes_7_0= ruleExpression
            	    	    {

            	    	    							newCompositeNode(grammarAccess.getVarExpressionAccess().getArrayIndexesExpressionParserRuleCall_1_2_1_0());
            	    	    						
            	    	    pushFollow(FOLLOW_35);
            	    	    lv_arrayIndexes_7_0=ruleExpression();

            	    	    state._fsp--;


            	    	    							if (current==null) {
            	    	    								current = createModelElementForParent(grammarAccess.getVarExpressionRule());
            	    	    							}
            	    	    							add(
            	    	    								current,
            	    	    								"arrayIndexes",
            	    	    								lv_arrayIndexes_7_0,
            	    	    								"pt.iscte.paddle.Javali.Expression");
            	    	    							afterParserOrEnumRuleCall();
            	    	    						

            	    	    }


            	    	    }

            	    	    otherlv_8=(Token)match(input,51,FOLLOW_34); 

            	    	    					newLeafNode(otherlv_8, grammarAccess.getVarExpressionAccess().getRightSquareBracketKeyword_1_2_2());
            	    	    				

            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop33;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarExpression"


    // $ANTLR start "entryRuleProcCall"
    // InternalJavali.g:2467:1: entryRuleProcCall returns [EObject current=null] : iv_ruleProcCall= ruleProcCall EOF ;
    public final EObject entryRuleProcCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcCall = null;


        try {
            // InternalJavali.g:2467:49: (iv_ruleProcCall= ruleProcCall EOF )
            // InternalJavali.g:2468:2: iv_ruleProcCall= ruleProcCall EOF
            {
             newCompositeNode(grammarAccess.getProcCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcCall=ruleProcCall();

            state._fsp--;

             current =iv_ruleProcCall; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcCall"


    // $ANTLR start "ruleProcCall"
    // InternalJavali.g:2474:1: ruleProcCall returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) ;
    public final EObject ruleProcCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_id_0_0 = null;

        EObject lv_args_2_0 = null;

        EObject lv_args_4_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:2480:2: ( ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )* )? otherlv_5= ')' ) )
            // InternalJavali.g:2481:2: ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            {
            // InternalJavali.g:2481:2: ( ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )* )? otherlv_5= ')' )
            // InternalJavali.g:2482:3: ( (lv_id_0_0= ruleIdentifier ) ) otherlv_1= '(' ( ( (lv_args_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )* )? otherlv_5= ')'
            {
            // InternalJavali.g:2482:3: ( (lv_id_0_0= ruleIdentifier ) )
            // InternalJavali.g:2483:4: (lv_id_0_0= ruleIdentifier )
            {
            // InternalJavali.g:2483:4: (lv_id_0_0= ruleIdentifier )
            // InternalJavali.g:2484:5: lv_id_0_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getProcCallAccess().getIdIdentifierParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_13);
            lv_id_0_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getProcCallRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_0_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,21,FOLLOW_36); 

            			newLeafNode(otherlv_1, grammarAccess.getProcCallAccess().getLeftParenthesisKeyword_1());
            		
            // InternalJavali.g:2505:3: ( ( (lv_args_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )* )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=RULE_PRIMITIVE_VALUE && LA36_0<=RULE_ID)||LA36_0==21||(LA36_0>=48 && LA36_0<=49)||LA36_0==53) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalJavali.g:2506:4: ( (lv_args_2_0= ruleExpression ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )*
                    {
                    // InternalJavali.g:2506:4: ( (lv_args_2_0= ruleExpression ) )
                    // InternalJavali.g:2507:5: (lv_args_2_0= ruleExpression )
                    {
                    // InternalJavali.g:2507:5: (lv_args_2_0= ruleExpression )
                    // InternalJavali.g:2508:6: lv_args_2_0= ruleExpression
                    {

                    						newCompositeNode(grammarAccess.getProcCallAccess().getArgsExpressionParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_15);
                    lv_args_2_0=ruleExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getProcCallRule());
                    						}
                    						add(
                    							current,
                    							"args",
                    							lv_args_2_0,
                    							"pt.iscte.paddle.Javali.Expression");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalJavali.g:2525:4: (otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) ) )*
                    loop35:
                    do {
                        int alt35=2;
                        int LA35_0 = input.LA(1);

                        if ( (LA35_0==22) ) {
                            alt35=1;
                        }


                        switch (alt35) {
                    	case 1 :
                    	    // InternalJavali.g:2526:5: otherlv_3= ',' ( (lv_args_4_0= ruleExpression ) )
                    	    {
                    	    otherlv_3=(Token)match(input,22,FOLLOW_19); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getProcCallAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    // InternalJavali.g:2530:5: ( (lv_args_4_0= ruleExpression ) )
                    	    // InternalJavali.g:2531:6: (lv_args_4_0= ruleExpression )
                    	    {
                    	    // InternalJavali.g:2531:6: (lv_args_4_0= ruleExpression )
                    	    // InternalJavali.g:2532:7: lv_args_4_0= ruleExpression
                    	    {

                    	    							newCompositeNode(grammarAccess.getProcCallAccess().getArgsExpressionParserRuleCall_2_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_15);
                    	    lv_args_4_0=ruleExpression();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getProcCallRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"args",
                    	    								lv_args_4_0,
                    	    								"pt.iscte.paddle.Javali.Expression");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop35;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,23,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getProcCallAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcCall"


    // $ANTLR start "entryRuleType"
    // InternalJavali.g:2559:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // InternalJavali.g:2559:45: (iv_ruleType= ruleType EOF )
            // InternalJavali.g:2560:2: iv_ruleType= ruleType EOF
            {
             newCompositeNode(grammarAccess.getTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleType=ruleType();

            state._fsp--;

             current =iv_ruleType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalJavali.g:2566:1: ruleType returns [EObject current=null] : ( ( (lv_id_0_0= ruleIdentifier ) ) ( ( (lv_arrayDims_1_0= '[' ) ) otherlv_2= ']' )* ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        Token lv_arrayDims_1_0=null;
        Token otherlv_2=null;
        EObject lv_id_0_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:2572:2: ( ( ( (lv_id_0_0= ruleIdentifier ) ) ( ( (lv_arrayDims_1_0= '[' ) ) otherlv_2= ']' )* ) )
            // InternalJavali.g:2573:2: ( ( (lv_id_0_0= ruleIdentifier ) ) ( ( (lv_arrayDims_1_0= '[' ) ) otherlv_2= ']' )* )
            {
            // InternalJavali.g:2573:2: ( ( (lv_id_0_0= ruleIdentifier ) ) ( ( (lv_arrayDims_1_0= '[' ) ) otherlv_2= ']' )* )
            // InternalJavali.g:2574:3: ( (lv_id_0_0= ruleIdentifier ) ) ( ( (lv_arrayDims_1_0= '[' ) ) otherlv_2= ']' )*
            {
            // InternalJavali.g:2574:3: ( (lv_id_0_0= ruleIdentifier ) )
            // InternalJavali.g:2575:4: (lv_id_0_0= ruleIdentifier )
            {
            // InternalJavali.g:2575:4: (lv_id_0_0= ruleIdentifier )
            // InternalJavali.g:2576:5: lv_id_0_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getTypeAccess().getIdIdentifierParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_37);
            lv_id_0_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTypeRule());
            					}
            					set(
            						current,
            						"id",
            						lv_id_0_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJavali.g:2593:3: ( ( (lv_arrayDims_1_0= '[' ) ) otherlv_2= ']' )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==50) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalJavali.g:2594:4: ( (lv_arrayDims_1_0= '[' ) ) otherlv_2= ']'
            	    {
            	    // InternalJavali.g:2594:4: ( (lv_arrayDims_1_0= '[' ) )
            	    // InternalJavali.g:2595:5: (lv_arrayDims_1_0= '[' )
            	    {
            	    // InternalJavali.g:2595:5: (lv_arrayDims_1_0= '[' )
            	    // InternalJavali.g:2596:6: lv_arrayDims_1_0= '['
            	    {
            	    lv_arrayDims_1_0=(Token)match(input,50,FOLLOW_35); 

            	    						newLeafNode(lv_arrayDims_1_0, grammarAccess.getTypeAccess().getArrayDimsLeftSquareBracketKeyword_1_0_0());
            	    					

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getTypeRule());
            	    						}
            	    						addWithLastConsumed(current, "arrayDims", lv_arrayDims_1_0, "[");
            	    					

            	    }


            	    }

            	    otherlv_2=(Token)match(input,51,FOLLOW_37); 

            	    				newLeafNode(otherlv_2, grammarAccess.getTypeAccess().getRightSquareBracketKeyword_1_1());
            	    			

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleNewArray"
    // InternalJavali.g:2617:1: entryRuleNewArray returns [EObject current=null] : iv_ruleNewArray= ruleNewArray EOF ;
    public final EObject entryRuleNewArray() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNewArray = null;


        try {
            // InternalJavali.g:2617:49: (iv_ruleNewArray= ruleNewArray EOF )
            // InternalJavali.g:2618:2: iv_ruleNewArray= ruleNewArray EOF
            {
             newCompositeNode(grammarAccess.getNewArrayRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNewArray=ruleNewArray();

            state._fsp--;

             current =iv_ruleNewArray; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNewArray"


    // $ANTLR start "ruleNewArray"
    // InternalJavali.g:2624:1: ruleNewArray returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) (otherlv_2= '[' ( (lv_arrayDims_3_0= ruleExpression ) ) otherlv_4= ']' )+ ) ;
    public final EObject ruleNewArray() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_type_1_0 = null;

        EObject lv_arrayDims_3_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:2630:2: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) (otherlv_2= '[' ( (lv_arrayDims_3_0= ruleExpression ) ) otherlv_4= ']' )+ ) )
            // InternalJavali.g:2631:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) (otherlv_2= '[' ( (lv_arrayDims_3_0= ruleExpression ) ) otherlv_4= ']' )+ )
            {
            // InternalJavali.g:2631:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) (otherlv_2= '[' ( (lv_arrayDims_3_0= ruleExpression ) ) otherlv_4= ']' )+ )
            // InternalJavali.g:2632:3: otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) (otherlv_2= '[' ( (lv_arrayDims_3_0= ruleExpression ) ) otherlv_4= ']' )+
            {
            otherlv_0=(Token)match(input,53,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getNewArrayAccess().getNewKeyword_0());
            		
            // InternalJavali.g:2636:3: ( (lv_type_1_0= ruleIdentifier ) )
            // InternalJavali.g:2637:4: (lv_type_1_0= ruleIdentifier )
            {
            // InternalJavali.g:2637:4: (lv_type_1_0= ruleIdentifier )
            // InternalJavali.g:2638:5: lv_type_1_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getNewArrayAccess().getTypeIdentifierParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_38);
            lv_type_1_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNewArrayRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_1_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalJavali.g:2655:3: (otherlv_2= '[' ( (lv_arrayDims_3_0= ruleExpression ) ) otherlv_4= ']' )+
            int cnt38=0;
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==50) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalJavali.g:2656:4: otherlv_2= '[' ( (lv_arrayDims_3_0= ruleExpression ) ) otherlv_4= ']'
            	    {
            	    otherlv_2=(Token)match(input,50,FOLLOW_19); 

            	    				newLeafNode(otherlv_2, grammarAccess.getNewArrayAccess().getLeftSquareBracketKeyword_2_0());
            	    			
            	    // InternalJavali.g:2660:4: ( (lv_arrayDims_3_0= ruleExpression ) )
            	    // InternalJavali.g:2661:5: (lv_arrayDims_3_0= ruleExpression )
            	    {
            	    // InternalJavali.g:2661:5: (lv_arrayDims_3_0= ruleExpression )
            	    // InternalJavali.g:2662:6: lv_arrayDims_3_0= ruleExpression
            	    {

            	    						newCompositeNode(grammarAccess.getNewArrayAccess().getArrayDimsExpressionParserRuleCall_2_1_0());
            	    					
            	    pushFollow(FOLLOW_35);
            	    lv_arrayDims_3_0=ruleExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getNewArrayRule());
            	    						}
            	    						add(
            	    							current,
            	    							"arrayDims",
            	    							lv_arrayDims_3_0,
            	    							"pt.iscte.paddle.Javali.Expression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_4=(Token)match(input,51,FOLLOW_37); 

            	    				newLeafNode(otherlv_4, grammarAccess.getNewArrayAccess().getRightSquareBracketKeyword_2_2());
            	    			

            	    }
            	    break;

            	default :
            	    if ( cnt38 >= 1 ) break loop38;
                        EarlyExitException eee =
                            new EarlyExitException(38, input);
                        throw eee;
                }
                cnt38++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNewArray"


    // $ANTLR start "entryRuleNewObject"
    // InternalJavali.g:2688:1: entryRuleNewObject returns [EObject current=null] : iv_ruleNewObject= ruleNewObject EOF ;
    public final EObject entryRuleNewObject() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNewObject = null;


        try {
            // InternalJavali.g:2688:50: (iv_ruleNewObject= ruleNewObject EOF )
            // InternalJavali.g:2689:2: iv_ruleNewObject= ruleNewObject EOF
            {
             newCompositeNode(grammarAccess.getNewObjectRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNewObject=ruleNewObject();

            state._fsp--;

             current =iv_ruleNewObject; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNewObject"


    // $ANTLR start "ruleNewObject"
    // InternalJavali.g:2695:1: ruleNewObject returns [EObject current=null] : (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) otherlv_2= '(' otherlv_3= ')' ) ;
    public final EObject ruleNewObject() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        EObject lv_type_1_0 = null;



        	enterRule();

        try {
            // InternalJavali.g:2701:2: ( (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) otherlv_2= '(' otherlv_3= ')' ) )
            // InternalJavali.g:2702:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) otherlv_2= '(' otherlv_3= ')' )
            {
            // InternalJavali.g:2702:2: (otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) otherlv_2= '(' otherlv_3= ')' )
            // InternalJavali.g:2703:3: otherlv_0= 'new' ( (lv_type_1_0= ruleIdentifier ) ) otherlv_2= '(' otherlv_3= ')'
            {
            otherlv_0=(Token)match(input,53,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getNewObjectAccess().getNewKeyword_0());
            		
            // InternalJavali.g:2707:3: ( (lv_type_1_0= ruleIdentifier ) )
            // InternalJavali.g:2708:4: (lv_type_1_0= ruleIdentifier )
            {
            // InternalJavali.g:2708:4: (lv_type_1_0= ruleIdentifier )
            // InternalJavali.g:2709:5: lv_type_1_0= ruleIdentifier
            {

            					newCompositeNode(grammarAccess.getNewObjectAccess().getTypeIdentifierParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_13);
            lv_type_1_0=ruleIdentifier();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getNewObjectRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_1_0,
            						"pt.iscte.paddle.Javali.Identifier");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_20); 

            			newLeafNode(otherlv_2, grammarAccess.getNewObjectAccess().getLeftParenthesisKeyword_2());
            		
            otherlv_3=(Token)match(input,23,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getNewObjectAccess().getRightParenthesisKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNewObject"


    // $ANTLR start "entryRuleIdentifier"
    // InternalJavali.g:2738:1: entryRuleIdentifier returns [EObject current=null] : iv_ruleIdentifier= ruleIdentifier EOF ;
    public final EObject entryRuleIdentifier() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIdentifier = null;


        try {
            // InternalJavali.g:2738:51: (iv_ruleIdentifier= ruleIdentifier EOF )
            // InternalJavali.g:2739:2: iv_ruleIdentifier= ruleIdentifier EOF
            {
             newCompositeNode(grammarAccess.getIdentifierRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIdentifier=ruleIdentifier();

            state._fsp--;

             current =iv_ruleIdentifier; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIdentifier"


    // $ANTLR start "ruleIdentifier"
    // InternalJavali.g:2745:1: ruleIdentifier returns [EObject current=null] : ( (lv_id_0_0= RULE_ID ) ) ;
    public final EObject ruleIdentifier() throws RecognitionException {
        EObject current = null;

        Token lv_id_0_0=null;


        	enterRule();

        try {
            // InternalJavali.g:2751:2: ( ( (lv_id_0_0= RULE_ID ) ) )
            // InternalJavali.g:2752:2: ( (lv_id_0_0= RULE_ID ) )
            {
            // InternalJavali.g:2752:2: ( (lv_id_0_0= RULE_ID ) )
            // InternalJavali.g:2753:3: (lv_id_0_0= RULE_ID )
            {
            // InternalJavali.g:2753:3: (lv_id_0_0= RULE_ID )
            // InternalJavali.g:2754:4: lv_id_0_0= RULE_ID
            {
            lv_id_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(lv_id_0_0, grammarAccess.getIdentifierAccess().getIdIDTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getIdentifierRule());
            				}
            				setWithLastConsumed(
            					current,
            					"id",
            					lv_id_0_0,
            					"pt.iscte.paddle.Javali.ID");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIdentifier"

    // Delegated rules


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA31 dfa31 = new DFA31(this);
    static final String dfa_1s = "\17\uffff";
    static final String dfa_2s = "\1\6\3\uffff\1\6\4\uffff\1\5\5\uffff";
    static final String dfa_3s = "\1\37\3\uffff\1\64\4\uffff\1\65\5\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\2\1\3\1\uffff\1\11\1\12\1\13\1\14\1\uffff\1\5\1\4\1\7\1\10\1\6";
    static final String dfa_5s = "\17\uffff}>";
    static final String[] dfa_6s = {
            "\1\4\21\uffff\1\1\1\2\1\3\1\5\1\uffff\1\6\1\7\1\10",
            "",
            "",
            "",
            "\1\13\10\uffff\1\12\5\uffff\1\15\12\uffff\1\16\1\14\20\uffff\1\11\1\uffff\1\12",
            "",
            "",
            "",
            "",
            "\2\12\16\uffff\1\12\32\uffff\2\12\1\uffff\1\13\1\uffff\1\12",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "565:2: ( (this_Return_0= ruleReturn otherlv_1= ';' ) | (this_Break_2= ruleBreak otherlv_3= ';' ) | (this_Continue_4= ruleContinue otherlv_5= ';' ) | (this_VarDeclarationAssign_6= ruleVarDeclarationAssign otherlv_7= ';' ) | (this_VarAssign_8= ruleVarAssign otherlv_9= ';' ) | (this_Increment_10= ruleIncrement otherlv_11= ';' ) | (this_Decrement_12= ruleDecrement otherlv_13= ';' ) | (this_ProcCall_14= ruleProcCall otherlv_15= ';' ) | this_IfElse_16= ruleIfElse | this_While_17= ruleWhile | this_For_18= ruleFor | (this_DoWhile_19= ruleDoWhile otherlv_20= ';' ) )";
        }
    }
    static final String dfa_7s = "\14\uffff";
    static final String dfa_8s = "\3\uffff\1\10\10\uffff";
    static final String dfa_9s = "\1\5\2\uffff\1\20\1\6\4\uffff\1\25\2\uffff";
    static final String dfa_10s = "\1\65\2\uffff\1\64\1\6\4\uffff\1\62\2\uffff";
    static final String dfa_11s = "\1\uffff\1\1\1\2\2\uffff\1\7\1\10\1\3\1\4\1\uffff\1\5\1\6";
    static final String dfa_12s = "\14\uffff}>";
    static final String[] dfa_13s = {
            "\1\1\1\3\16\uffff\1\6\32\uffff\1\5\1\2\3\uffff\1\4",
            "",
            "",
            "\1\10\4\uffff\1\7\2\10\12\uffff\16\10\2\uffff\3\10",
            "\1\11",
            "",
            "",
            "",
            "",
            "\1\13\34\uffff\1\12",
            "",
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "2187:2: (this_Literal_0= ruleLiteral | this_Null_1= ruleNull | this_ProcCall_2= ruleProcCall | this_VarExpression_3= ruleVarExpression | this_NewArray_4= ruleNewArray | this_NewObject_5= ruleNewObject | (otherlv_6= '!' this_Primary_7= rulePrimary ) | (otherlv_8= '(' this_Expression_9= ruleExpression otherlv_10= ')' ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000126052L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000080040L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000102040L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000100040L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000800040L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x00000000EF080040L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0023000000200062L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0023000000200060L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000410000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000078000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000E00000000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0014000000000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0023000000A00060L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0004000000000000L});

}