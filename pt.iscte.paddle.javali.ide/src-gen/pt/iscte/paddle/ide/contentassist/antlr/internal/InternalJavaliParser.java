package pt.iscte.paddle.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import pt.iscte.paddle.services.JavaliGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJavaliParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ML_COMMENT_DOC", "RULE_PRIMITIVE_VALUE", "RULE_ID", "RULE_INTEGER", "RULE_DOUBLE", "RULE_BOOLEAN", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "'=='", "'!='", "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", "'/'", "'%'", "'final'", "'='", "';'", "'class'", "'{'", "'}'", "'('", "')'", "','", "'return'", "'break'", "'continue'", "'if'", "'else'", "'while'", "'for'", "'do'", "'++'", "'--'", "'||'", "'^'", "'&&'", "'!'", "'null'", "'['", "']'", "'.'", "'new'", "'static'", "'void'"
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

    	public void setGrammarAccess(JavaliGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleModule"
    // InternalJavali.g:53:1: entryRuleModule : ruleModule EOF ;
    public final void entryRuleModule() throws RecognitionException {
        try {
            // InternalJavali.g:54:1: ( ruleModule EOF )
            // InternalJavali.g:55:1: ruleModule EOF
            {
             before(grammarAccess.getModuleRule()); 
            pushFollow(FOLLOW_1);
            ruleModule();

            state._fsp--;

             after(grammarAccess.getModuleRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModule"


    // $ANTLR start "ruleModule"
    // InternalJavali.g:62:1: ruleModule : ( ( rule__Module__Alternatives )* ) ;
    public final void ruleModule() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:66:2: ( ( ( rule__Module__Alternatives )* ) )
            // InternalJavali.g:67:2: ( ( rule__Module__Alternatives )* )
            {
            // InternalJavali.g:67:2: ( ( rule__Module__Alternatives )* )
            // InternalJavali.g:68:3: ( rule__Module__Alternatives )*
            {
             before(grammarAccess.getModuleAccess().getAlternatives()); 
            // InternalJavali.g:69:3: ( rule__Module__Alternatives )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_ML_COMMENT_DOC||LA1_0==RULE_ID||LA1_0==24||LA1_0==27||(LA1_0>=52 && LA1_0<=53)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalJavali.g:69:4: rule__Module__Alternatives
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__Module__Alternatives();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getModuleAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModule"


    // $ANTLR start "entryRuleConstant"
    // InternalJavali.g:78:1: entryRuleConstant : ruleConstant EOF ;
    public final void entryRuleConstant() throws RecognitionException {
        try {
            // InternalJavali.g:79:1: ( ruleConstant EOF )
            // InternalJavali.g:80:1: ruleConstant EOF
            {
             before(grammarAccess.getConstantRule()); 
            pushFollow(FOLLOW_1);
            ruleConstant();

            state._fsp--;

             after(grammarAccess.getConstantRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConstant"


    // $ANTLR start "ruleConstant"
    // InternalJavali.g:87:1: ruleConstant : ( ( rule__Constant__Group__0 ) ) ;
    public final void ruleConstant() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:91:2: ( ( ( rule__Constant__Group__0 ) ) )
            // InternalJavali.g:92:2: ( ( rule__Constant__Group__0 ) )
            {
            // InternalJavali.g:92:2: ( ( rule__Constant__Group__0 ) )
            // InternalJavali.g:93:3: ( rule__Constant__Group__0 )
            {
             before(grammarAccess.getConstantAccess().getGroup()); 
            // InternalJavali.g:94:3: ( rule__Constant__Group__0 )
            // InternalJavali.g:94:4: rule__Constant__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Constant__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConstantAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConstant"


    // $ANTLR start "entryRuleRecord"
    // InternalJavali.g:103:1: entryRuleRecord : ruleRecord EOF ;
    public final void entryRuleRecord() throws RecognitionException {
        try {
            // InternalJavali.g:104:1: ( ruleRecord EOF )
            // InternalJavali.g:105:1: ruleRecord EOF
            {
             before(grammarAccess.getRecordRule()); 
            pushFollow(FOLLOW_1);
            ruleRecord();

            state._fsp--;

             after(grammarAccess.getRecordRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRecord"


    // $ANTLR start "ruleRecord"
    // InternalJavali.g:112:1: ruleRecord : ( ( rule__Record__Group__0 ) ) ;
    public final void ruleRecord() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:116:2: ( ( ( rule__Record__Group__0 ) ) )
            // InternalJavali.g:117:2: ( ( rule__Record__Group__0 ) )
            {
            // InternalJavali.g:117:2: ( ( rule__Record__Group__0 ) )
            // InternalJavali.g:118:3: ( rule__Record__Group__0 )
            {
             before(grammarAccess.getRecordAccess().getGroup()); 
            // InternalJavali.g:119:3: ( rule__Record__Group__0 )
            // InternalJavali.g:119:4: rule__Record__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Record__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRecordAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRecord"


    // $ANTLR start "entryRuleProcedure"
    // InternalJavali.g:128:1: entryRuleProcedure : ruleProcedure EOF ;
    public final void entryRuleProcedure() throws RecognitionException {
        try {
            // InternalJavali.g:129:1: ( ruleProcedure EOF )
            // InternalJavali.g:130:1: ruleProcedure EOF
            {
             before(grammarAccess.getProcedureRule()); 
            pushFollow(FOLLOW_1);
            ruleProcedure();

            state._fsp--;

             after(grammarAccess.getProcedureRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleProcedure"


    // $ANTLR start "ruleProcedure"
    // InternalJavali.g:137:1: ruleProcedure : ( ( rule__Procedure__Group__0 ) ) ;
    public final void ruleProcedure() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:141:2: ( ( ( rule__Procedure__Group__0 ) ) )
            // InternalJavali.g:142:2: ( ( rule__Procedure__Group__0 ) )
            {
            // InternalJavali.g:142:2: ( ( rule__Procedure__Group__0 ) )
            // InternalJavali.g:143:3: ( rule__Procedure__Group__0 )
            {
             before(grammarAccess.getProcedureAccess().getGroup()); 
            // InternalJavali.g:144:3: ( rule__Procedure__Group__0 )
            // InternalJavali.g:144:4: rule__Procedure__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getProcedureAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleProcedure"


    // $ANTLR start "entryRuleBlock"
    // InternalJavali.g:153:1: entryRuleBlock : ruleBlock EOF ;
    public final void entryRuleBlock() throws RecognitionException {
        try {
            // InternalJavali.g:154:1: ( ruleBlock EOF )
            // InternalJavali.g:155:1: ruleBlock EOF
            {
             before(grammarAccess.getBlockRule()); 
            pushFollow(FOLLOW_1);
            ruleBlock();

            state._fsp--;

             after(grammarAccess.getBlockRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBlock"


    // $ANTLR start "ruleBlock"
    // InternalJavali.g:162:1: ruleBlock : ( ( rule__Block__Group__0 ) ) ;
    public final void ruleBlock() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:166:2: ( ( ( rule__Block__Group__0 ) ) )
            // InternalJavali.g:167:2: ( ( rule__Block__Group__0 ) )
            {
            // InternalJavali.g:167:2: ( ( rule__Block__Group__0 ) )
            // InternalJavali.g:168:3: ( rule__Block__Group__0 )
            {
             before(grammarAccess.getBlockAccess().getGroup()); 
            // InternalJavali.g:169:3: ( rule__Block__Group__0 )
            // InternalJavali.g:169:4: rule__Block__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Block__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBlockAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBlock"


    // $ANTLR start "entryRuleStatement"
    // InternalJavali.g:178:1: entryRuleStatement : ruleStatement EOF ;
    public final void entryRuleStatement() throws RecognitionException {
        try {
            // InternalJavali.g:179:1: ( ruleStatement EOF )
            // InternalJavali.g:180:1: ruleStatement EOF
            {
             before(grammarAccess.getStatementRule()); 
            pushFollow(FOLLOW_1);
            ruleStatement();

            state._fsp--;

             after(grammarAccess.getStatementRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStatement"


    // $ANTLR start "ruleStatement"
    // InternalJavali.g:187:1: ruleStatement : ( ( rule__Statement__Alternatives ) ) ;
    public final void ruleStatement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:191:2: ( ( ( rule__Statement__Alternatives ) ) )
            // InternalJavali.g:192:2: ( ( rule__Statement__Alternatives ) )
            {
            // InternalJavali.g:192:2: ( ( rule__Statement__Alternatives ) )
            // InternalJavali.g:193:3: ( rule__Statement__Alternatives )
            {
             before(grammarAccess.getStatementAccess().getAlternatives()); 
            // InternalJavali.g:194:3: ( rule__Statement__Alternatives )
            // InternalJavali.g:194:4: rule__Statement__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getStatementAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStatement"


    // $ANTLR start "entryRuleReturn"
    // InternalJavali.g:203:1: entryRuleReturn : ruleReturn EOF ;
    public final void entryRuleReturn() throws RecognitionException {
        try {
            // InternalJavali.g:204:1: ( ruleReturn EOF )
            // InternalJavali.g:205:1: ruleReturn EOF
            {
             before(grammarAccess.getReturnRule()); 
            pushFollow(FOLLOW_1);
            ruleReturn();

            state._fsp--;

             after(grammarAccess.getReturnRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleReturn"


    // $ANTLR start "ruleReturn"
    // InternalJavali.g:212:1: ruleReturn : ( ( rule__Return__Group__0 ) ) ;
    public final void ruleReturn() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:216:2: ( ( ( rule__Return__Group__0 ) ) )
            // InternalJavali.g:217:2: ( ( rule__Return__Group__0 ) )
            {
            // InternalJavali.g:217:2: ( ( rule__Return__Group__0 ) )
            // InternalJavali.g:218:3: ( rule__Return__Group__0 )
            {
             before(grammarAccess.getReturnAccess().getGroup()); 
            // InternalJavali.g:219:3: ( rule__Return__Group__0 )
            // InternalJavali.g:219:4: rule__Return__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Return__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getReturnAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleReturn"


    // $ANTLR start "entryRuleBreak"
    // InternalJavali.g:228:1: entryRuleBreak : ruleBreak EOF ;
    public final void entryRuleBreak() throws RecognitionException {
        try {
            // InternalJavali.g:229:1: ( ruleBreak EOF )
            // InternalJavali.g:230:1: ruleBreak EOF
            {
             before(grammarAccess.getBreakRule()); 
            pushFollow(FOLLOW_1);
            ruleBreak();

            state._fsp--;

             after(grammarAccess.getBreakRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBreak"


    // $ANTLR start "ruleBreak"
    // InternalJavali.g:237:1: ruleBreak : ( ( rule__Break__Group__0 ) ) ;
    public final void ruleBreak() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:241:2: ( ( ( rule__Break__Group__0 ) ) )
            // InternalJavali.g:242:2: ( ( rule__Break__Group__0 ) )
            {
            // InternalJavali.g:242:2: ( ( rule__Break__Group__0 ) )
            // InternalJavali.g:243:3: ( rule__Break__Group__0 )
            {
             before(grammarAccess.getBreakAccess().getGroup()); 
            // InternalJavali.g:244:3: ( rule__Break__Group__0 )
            // InternalJavali.g:244:4: rule__Break__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Break__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBreakAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBreak"


    // $ANTLR start "entryRuleContinue"
    // InternalJavali.g:253:1: entryRuleContinue : ruleContinue EOF ;
    public final void entryRuleContinue() throws RecognitionException {
        try {
            // InternalJavali.g:254:1: ( ruleContinue EOF )
            // InternalJavali.g:255:1: ruleContinue EOF
            {
             before(grammarAccess.getContinueRule()); 
            pushFollow(FOLLOW_1);
            ruleContinue();

            state._fsp--;

             after(grammarAccess.getContinueRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleContinue"


    // $ANTLR start "ruleContinue"
    // InternalJavali.g:262:1: ruleContinue : ( ( rule__Continue__Group__0 ) ) ;
    public final void ruleContinue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:266:2: ( ( ( rule__Continue__Group__0 ) ) )
            // InternalJavali.g:267:2: ( ( rule__Continue__Group__0 ) )
            {
            // InternalJavali.g:267:2: ( ( rule__Continue__Group__0 ) )
            // InternalJavali.g:268:3: ( rule__Continue__Group__0 )
            {
             before(grammarAccess.getContinueAccess().getGroup()); 
            // InternalJavali.g:269:3: ( rule__Continue__Group__0 )
            // InternalJavali.g:269:4: rule__Continue__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Continue__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getContinueAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleContinue"


    // $ANTLR start "entryRuleVarDeclaration"
    // InternalJavali.g:278:1: entryRuleVarDeclaration : ruleVarDeclaration EOF ;
    public final void entryRuleVarDeclaration() throws RecognitionException {
        try {
            // InternalJavali.g:279:1: ( ruleVarDeclaration EOF )
            // InternalJavali.g:280:1: ruleVarDeclaration EOF
            {
             before(grammarAccess.getVarDeclarationRule()); 
            pushFollow(FOLLOW_1);
            ruleVarDeclaration();

            state._fsp--;

             after(grammarAccess.getVarDeclarationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVarDeclaration"


    // $ANTLR start "ruleVarDeclaration"
    // InternalJavali.g:287:1: ruleVarDeclaration : ( ( rule__VarDeclaration__Group__0 ) ) ;
    public final void ruleVarDeclaration() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:291:2: ( ( ( rule__VarDeclaration__Group__0 ) ) )
            // InternalJavali.g:292:2: ( ( rule__VarDeclaration__Group__0 ) )
            {
            // InternalJavali.g:292:2: ( ( rule__VarDeclaration__Group__0 ) )
            // InternalJavali.g:293:3: ( rule__VarDeclaration__Group__0 )
            {
             before(grammarAccess.getVarDeclarationAccess().getGroup()); 
            // InternalJavali.g:294:3: ( rule__VarDeclaration__Group__0 )
            // InternalJavali.g:294:4: rule__VarDeclaration__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclaration__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVarDeclarationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVarDeclaration"


    // $ANTLR start "entryRuleVarDeclarationAssign"
    // InternalJavali.g:303:1: entryRuleVarDeclarationAssign : ruleVarDeclarationAssign EOF ;
    public final void entryRuleVarDeclarationAssign() throws RecognitionException {
        try {
            // InternalJavali.g:304:1: ( ruleVarDeclarationAssign EOF )
            // InternalJavali.g:305:1: ruleVarDeclarationAssign EOF
            {
             before(grammarAccess.getVarDeclarationAssignRule()); 
            pushFollow(FOLLOW_1);
            ruleVarDeclarationAssign();

            state._fsp--;

             after(grammarAccess.getVarDeclarationAssignRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVarDeclarationAssign"


    // $ANTLR start "ruleVarDeclarationAssign"
    // InternalJavali.g:312:1: ruleVarDeclarationAssign : ( ( rule__VarDeclarationAssign__Group__0 ) ) ;
    public final void ruleVarDeclarationAssign() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:316:2: ( ( ( rule__VarDeclarationAssign__Group__0 ) ) )
            // InternalJavali.g:317:2: ( ( rule__VarDeclarationAssign__Group__0 ) )
            {
            // InternalJavali.g:317:2: ( ( rule__VarDeclarationAssign__Group__0 ) )
            // InternalJavali.g:318:3: ( rule__VarDeclarationAssign__Group__0 )
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getGroup()); 
            // InternalJavali.g:319:3: ( rule__VarDeclarationAssign__Group__0 )
            // InternalJavali.g:319:4: rule__VarDeclarationAssign__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVarDeclarationAssignAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVarDeclarationAssign"


    // $ANTLR start "entryRuleVarAssign"
    // InternalJavali.g:328:1: entryRuleVarAssign : ruleVarAssign EOF ;
    public final void entryRuleVarAssign() throws RecognitionException {
        try {
            // InternalJavali.g:329:1: ( ruleVarAssign EOF )
            // InternalJavali.g:330:1: ruleVarAssign EOF
            {
             before(grammarAccess.getVarAssignRule()); 
            pushFollow(FOLLOW_1);
            ruleVarAssign();

            state._fsp--;

             after(grammarAccess.getVarAssignRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVarAssign"


    // $ANTLR start "ruleVarAssign"
    // InternalJavali.g:337:1: ruleVarAssign : ( ( rule__VarAssign__Group__0 ) ) ;
    public final void ruleVarAssign() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:341:2: ( ( ( rule__VarAssign__Group__0 ) ) )
            // InternalJavali.g:342:2: ( ( rule__VarAssign__Group__0 ) )
            {
            // InternalJavali.g:342:2: ( ( rule__VarAssign__Group__0 ) )
            // InternalJavali.g:343:3: ( rule__VarAssign__Group__0 )
            {
             before(grammarAccess.getVarAssignAccess().getGroup()); 
            // InternalJavali.g:344:3: ( rule__VarAssign__Group__0 )
            // InternalJavali.g:344:4: rule__VarAssign__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VarAssign__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVarAssignAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVarAssign"


    // $ANTLR start "entryRuleIfElse"
    // InternalJavali.g:353:1: entryRuleIfElse : ruleIfElse EOF ;
    public final void entryRuleIfElse() throws RecognitionException {
        try {
            // InternalJavali.g:354:1: ( ruleIfElse EOF )
            // InternalJavali.g:355:1: ruleIfElse EOF
            {
             before(grammarAccess.getIfElseRule()); 
            pushFollow(FOLLOW_1);
            ruleIfElse();

            state._fsp--;

             after(grammarAccess.getIfElseRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIfElse"


    // $ANTLR start "ruleIfElse"
    // InternalJavali.g:362:1: ruleIfElse : ( ( rule__IfElse__Group__0 ) ) ;
    public final void ruleIfElse() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:366:2: ( ( ( rule__IfElse__Group__0 ) ) )
            // InternalJavali.g:367:2: ( ( rule__IfElse__Group__0 ) )
            {
            // InternalJavali.g:367:2: ( ( rule__IfElse__Group__0 ) )
            // InternalJavali.g:368:3: ( rule__IfElse__Group__0 )
            {
             before(grammarAccess.getIfElseAccess().getGroup()); 
            // InternalJavali.g:369:3: ( rule__IfElse__Group__0 )
            // InternalJavali.g:369:4: rule__IfElse__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__IfElse__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIfElseAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIfElse"


    // $ANTLR start "entryRuleWhile"
    // InternalJavali.g:378:1: entryRuleWhile : ruleWhile EOF ;
    public final void entryRuleWhile() throws RecognitionException {
        try {
            // InternalJavali.g:379:1: ( ruleWhile EOF )
            // InternalJavali.g:380:1: ruleWhile EOF
            {
             before(grammarAccess.getWhileRule()); 
            pushFollow(FOLLOW_1);
            ruleWhile();

            state._fsp--;

             after(grammarAccess.getWhileRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWhile"


    // $ANTLR start "ruleWhile"
    // InternalJavali.g:387:1: ruleWhile : ( ( rule__While__Group__0 ) ) ;
    public final void ruleWhile() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:391:2: ( ( ( rule__While__Group__0 ) ) )
            // InternalJavali.g:392:2: ( ( rule__While__Group__0 ) )
            {
            // InternalJavali.g:392:2: ( ( rule__While__Group__0 ) )
            // InternalJavali.g:393:3: ( rule__While__Group__0 )
            {
             before(grammarAccess.getWhileAccess().getGroup()); 
            // InternalJavali.g:394:3: ( rule__While__Group__0 )
            // InternalJavali.g:394:4: rule__While__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__While__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getWhileAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWhile"


    // $ANTLR start "entryRuleFor"
    // InternalJavali.g:403:1: entryRuleFor : ruleFor EOF ;
    public final void entryRuleFor() throws RecognitionException {
        try {
            // InternalJavali.g:404:1: ( ruleFor EOF )
            // InternalJavali.g:405:1: ruleFor EOF
            {
             before(grammarAccess.getForRule()); 
            pushFollow(FOLLOW_1);
            ruleFor();

            state._fsp--;

             after(grammarAccess.getForRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFor"


    // $ANTLR start "ruleFor"
    // InternalJavali.g:412:1: ruleFor : ( ( rule__For__Group__0 ) ) ;
    public final void ruleFor() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:416:2: ( ( ( rule__For__Group__0 ) ) )
            // InternalJavali.g:417:2: ( ( rule__For__Group__0 ) )
            {
            // InternalJavali.g:417:2: ( ( rule__For__Group__0 ) )
            // InternalJavali.g:418:3: ( rule__For__Group__0 )
            {
             before(grammarAccess.getForAccess().getGroup()); 
            // InternalJavali.g:419:3: ( rule__For__Group__0 )
            // InternalJavali.g:419:4: rule__For__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__For__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getForAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFor"


    // $ANTLR start "entryRuleDoWhile"
    // InternalJavali.g:428:1: entryRuleDoWhile : ruleDoWhile EOF ;
    public final void entryRuleDoWhile() throws RecognitionException {
        try {
            // InternalJavali.g:429:1: ( ruleDoWhile EOF )
            // InternalJavali.g:430:1: ruleDoWhile EOF
            {
             before(grammarAccess.getDoWhileRule()); 
            pushFollow(FOLLOW_1);
            ruleDoWhile();

            state._fsp--;

             after(grammarAccess.getDoWhileRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDoWhile"


    // $ANTLR start "ruleDoWhile"
    // InternalJavali.g:437:1: ruleDoWhile : ( ( rule__DoWhile__Group__0 ) ) ;
    public final void ruleDoWhile() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:441:2: ( ( ( rule__DoWhile__Group__0 ) ) )
            // InternalJavali.g:442:2: ( ( rule__DoWhile__Group__0 ) )
            {
            // InternalJavali.g:442:2: ( ( rule__DoWhile__Group__0 ) )
            // InternalJavali.g:443:3: ( rule__DoWhile__Group__0 )
            {
             before(grammarAccess.getDoWhileAccess().getGroup()); 
            // InternalJavali.g:444:3: ( rule__DoWhile__Group__0 )
            // InternalJavali.g:444:4: rule__DoWhile__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__DoWhile__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDoWhileAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDoWhile"


    // $ANTLR start "entryRuleForStatementInit"
    // InternalJavali.g:453:1: entryRuleForStatementInit : ruleForStatementInit EOF ;
    public final void entryRuleForStatementInit() throws RecognitionException {
        try {
            // InternalJavali.g:454:1: ( ruleForStatementInit EOF )
            // InternalJavali.g:455:1: ruleForStatementInit EOF
            {
             before(grammarAccess.getForStatementInitRule()); 
            pushFollow(FOLLOW_1);
            ruleForStatementInit();

            state._fsp--;

             after(grammarAccess.getForStatementInitRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleForStatementInit"


    // $ANTLR start "ruleForStatementInit"
    // InternalJavali.g:462:1: ruleForStatementInit : ( ( rule__ForStatementInit__Alternatives ) ) ;
    public final void ruleForStatementInit() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:466:2: ( ( ( rule__ForStatementInit__Alternatives ) ) )
            // InternalJavali.g:467:2: ( ( rule__ForStatementInit__Alternatives ) )
            {
            // InternalJavali.g:467:2: ( ( rule__ForStatementInit__Alternatives ) )
            // InternalJavali.g:468:3: ( rule__ForStatementInit__Alternatives )
            {
             before(grammarAccess.getForStatementInitAccess().getAlternatives()); 
            // InternalJavali.g:469:3: ( rule__ForStatementInit__Alternatives )
            // InternalJavali.g:469:4: rule__ForStatementInit__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ForStatementInit__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getForStatementInitAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleForStatementInit"


    // $ANTLR start "entryRuleForStatement"
    // InternalJavali.g:478:1: entryRuleForStatement : ruleForStatement EOF ;
    public final void entryRuleForStatement() throws RecognitionException {
        try {
            // InternalJavali.g:479:1: ( ruleForStatement EOF )
            // InternalJavali.g:480:1: ruleForStatement EOF
            {
             before(grammarAccess.getForStatementRule()); 
            pushFollow(FOLLOW_1);
            ruleForStatement();

            state._fsp--;

             after(grammarAccess.getForStatementRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleForStatement"


    // $ANTLR start "ruleForStatement"
    // InternalJavali.g:487:1: ruleForStatement : ( ( rule__ForStatement__Alternatives ) ) ;
    public final void ruleForStatement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:491:2: ( ( ( rule__ForStatement__Alternatives ) ) )
            // InternalJavali.g:492:2: ( ( rule__ForStatement__Alternatives ) )
            {
            // InternalJavali.g:492:2: ( ( rule__ForStatement__Alternatives ) )
            // InternalJavali.g:493:3: ( rule__ForStatement__Alternatives )
            {
             before(grammarAccess.getForStatementAccess().getAlternatives()); 
            // InternalJavali.g:494:3: ( rule__ForStatement__Alternatives )
            // InternalJavali.g:494:4: rule__ForStatement__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ForStatement__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getForStatementAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleForStatement"


    // $ANTLR start "entryRuleIncrement"
    // InternalJavali.g:503:1: entryRuleIncrement : ruleIncrement EOF ;
    public final void entryRuleIncrement() throws RecognitionException {
        try {
            // InternalJavali.g:504:1: ( ruleIncrement EOF )
            // InternalJavali.g:505:1: ruleIncrement EOF
            {
             before(grammarAccess.getIncrementRule()); 
            pushFollow(FOLLOW_1);
            ruleIncrement();

            state._fsp--;

             after(grammarAccess.getIncrementRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIncrement"


    // $ANTLR start "ruleIncrement"
    // InternalJavali.g:512:1: ruleIncrement : ( ( rule__Increment__Group__0 ) ) ;
    public final void ruleIncrement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:516:2: ( ( ( rule__Increment__Group__0 ) ) )
            // InternalJavali.g:517:2: ( ( rule__Increment__Group__0 ) )
            {
            // InternalJavali.g:517:2: ( ( rule__Increment__Group__0 ) )
            // InternalJavali.g:518:3: ( rule__Increment__Group__0 )
            {
             before(grammarAccess.getIncrementAccess().getGroup()); 
            // InternalJavali.g:519:3: ( rule__Increment__Group__0 )
            // InternalJavali.g:519:4: rule__Increment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Increment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIncrementAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIncrement"


    // $ANTLR start "entryRuleDecrement"
    // InternalJavali.g:528:1: entryRuleDecrement : ruleDecrement EOF ;
    public final void entryRuleDecrement() throws RecognitionException {
        try {
            // InternalJavali.g:529:1: ( ruleDecrement EOF )
            // InternalJavali.g:530:1: ruleDecrement EOF
            {
             before(grammarAccess.getDecrementRule()); 
            pushFollow(FOLLOW_1);
            ruleDecrement();

            state._fsp--;

             after(grammarAccess.getDecrementRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDecrement"


    // $ANTLR start "ruleDecrement"
    // InternalJavali.g:537:1: ruleDecrement : ( ( rule__Decrement__Group__0 ) ) ;
    public final void ruleDecrement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:541:2: ( ( ( rule__Decrement__Group__0 ) ) )
            // InternalJavali.g:542:2: ( ( rule__Decrement__Group__0 ) )
            {
            // InternalJavali.g:542:2: ( ( rule__Decrement__Group__0 ) )
            // InternalJavali.g:543:3: ( rule__Decrement__Group__0 )
            {
             before(grammarAccess.getDecrementAccess().getGroup()); 
            // InternalJavali.g:544:3: ( rule__Decrement__Group__0 )
            // InternalJavali.g:544:4: rule__Decrement__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Decrement__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDecrementAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDecrement"


    // $ANTLR start "entryRuleExpression"
    // InternalJavali.g:553:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // InternalJavali.g:554:1: ( ruleExpression EOF )
            // InternalJavali.g:555:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalJavali.g:562:1: ruleExpression : ( ruleOr ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:566:2: ( ( ruleOr ) )
            // InternalJavali.g:567:2: ( ruleOr )
            {
            // InternalJavali.g:567:2: ( ruleOr )
            // InternalJavali.g:568:3: ruleOr
            {
             before(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 
            pushFollow(FOLLOW_2);
            ruleOr();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getOrParserRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleOr"
    // InternalJavali.g:578:1: entryRuleOr : ruleOr EOF ;
    public final void entryRuleOr() throws RecognitionException {
        try {
            // InternalJavali.g:579:1: ( ruleOr EOF )
            // InternalJavali.g:580:1: ruleOr EOF
            {
             before(grammarAccess.getOrRule()); 
            pushFollow(FOLLOW_1);
            ruleOr();

            state._fsp--;

             after(grammarAccess.getOrRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOr"


    // $ANTLR start "ruleOr"
    // InternalJavali.g:587:1: ruleOr : ( ( rule__Or__Group__0 ) ) ;
    public final void ruleOr() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:591:2: ( ( ( rule__Or__Group__0 ) ) )
            // InternalJavali.g:592:2: ( ( rule__Or__Group__0 ) )
            {
            // InternalJavali.g:592:2: ( ( rule__Or__Group__0 ) )
            // InternalJavali.g:593:3: ( rule__Or__Group__0 )
            {
             before(grammarAccess.getOrAccess().getGroup()); 
            // InternalJavali.g:594:3: ( rule__Or__Group__0 )
            // InternalJavali.g:594:4: rule__Or__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Or__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOrAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOr"


    // $ANTLR start "entryRuleXor"
    // InternalJavali.g:603:1: entryRuleXor : ruleXor EOF ;
    public final void entryRuleXor() throws RecognitionException {
        try {
            // InternalJavali.g:604:1: ( ruleXor EOF )
            // InternalJavali.g:605:1: ruleXor EOF
            {
             before(grammarAccess.getXorRule()); 
            pushFollow(FOLLOW_1);
            ruleXor();

            state._fsp--;

             after(grammarAccess.getXorRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleXor"


    // $ANTLR start "ruleXor"
    // InternalJavali.g:612:1: ruleXor : ( ( rule__Xor__Group__0 ) ) ;
    public final void ruleXor() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:616:2: ( ( ( rule__Xor__Group__0 ) ) )
            // InternalJavali.g:617:2: ( ( rule__Xor__Group__0 ) )
            {
            // InternalJavali.g:617:2: ( ( rule__Xor__Group__0 ) )
            // InternalJavali.g:618:3: ( rule__Xor__Group__0 )
            {
             before(grammarAccess.getXorAccess().getGroup()); 
            // InternalJavali.g:619:3: ( rule__Xor__Group__0 )
            // InternalJavali.g:619:4: rule__Xor__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Xor__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getXorAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleXor"


    // $ANTLR start "entryRuleAnd"
    // InternalJavali.g:628:1: entryRuleAnd : ruleAnd EOF ;
    public final void entryRuleAnd() throws RecognitionException {
        try {
            // InternalJavali.g:629:1: ( ruleAnd EOF )
            // InternalJavali.g:630:1: ruleAnd EOF
            {
             before(grammarAccess.getAndRule()); 
            pushFollow(FOLLOW_1);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getAndRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAnd"


    // $ANTLR start "ruleAnd"
    // InternalJavali.g:637:1: ruleAnd : ( ( rule__And__Group__0 ) ) ;
    public final void ruleAnd() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:641:2: ( ( ( rule__And__Group__0 ) ) )
            // InternalJavali.g:642:2: ( ( rule__And__Group__0 ) )
            {
            // InternalJavali.g:642:2: ( ( rule__And__Group__0 ) )
            // InternalJavali.g:643:3: ( rule__And__Group__0 )
            {
             before(grammarAccess.getAndAccess().getGroup()); 
            // InternalJavali.g:644:3: ( rule__And__Group__0 )
            // InternalJavali.g:644:4: rule__And__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__And__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAndAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAnd"


    // $ANTLR start "entryRuleEquality"
    // InternalJavali.g:653:1: entryRuleEquality : ruleEquality EOF ;
    public final void entryRuleEquality() throws RecognitionException {
        try {
            // InternalJavali.g:654:1: ( ruleEquality EOF )
            // InternalJavali.g:655:1: ruleEquality EOF
            {
             before(grammarAccess.getEqualityRule()); 
            pushFollow(FOLLOW_1);
            ruleEquality();

            state._fsp--;

             after(grammarAccess.getEqualityRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEquality"


    // $ANTLR start "ruleEquality"
    // InternalJavali.g:662:1: ruleEquality : ( ( rule__Equality__Group__0 ) ) ;
    public final void ruleEquality() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:666:2: ( ( ( rule__Equality__Group__0 ) ) )
            // InternalJavali.g:667:2: ( ( rule__Equality__Group__0 ) )
            {
            // InternalJavali.g:667:2: ( ( rule__Equality__Group__0 ) )
            // InternalJavali.g:668:3: ( rule__Equality__Group__0 )
            {
             before(grammarAccess.getEqualityAccess().getGroup()); 
            // InternalJavali.g:669:3: ( rule__Equality__Group__0 )
            // InternalJavali.g:669:4: rule__Equality__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Equality__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEqualityAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEquality"


    // $ANTLR start "entryRuleRelation"
    // InternalJavali.g:678:1: entryRuleRelation : ruleRelation EOF ;
    public final void entryRuleRelation() throws RecognitionException {
        try {
            // InternalJavali.g:679:1: ( ruleRelation EOF )
            // InternalJavali.g:680:1: ruleRelation EOF
            {
             before(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_1);
            ruleRelation();

            state._fsp--;

             after(grammarAccess.getRelationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRelation"


    // $ANTLR start "ruleRelation"
    // InternalJavali.g:687:1: ruleRelation : ( ( rule__Relation__Group__0 ) ) ;
    public final void ruleRelation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:691:2: ( ( ( rule__Relation__Group__0 ) ) )
            // InternalJavali.g:692:2: ( ( rule__Relation__Group__0 ) )
            {
            // InternalJavali.g:692:2: ( ( rule__Relation__Group__0 ) )
            // InternalJavali.g:693:3: ( rule__Relation__Group__0 )
            {
             before(grammarAccess.getRelationAccess().getGroup()); 
            // InternalJavali.g:694:3: ( rule__Relation__Group__0 )
            // InternalJavali.g:694:4: rule__Relation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleAddition"
    // InternalJavali.g:703:1: entryRuleAddition : ruleAddition EOF ;
    public final void entryRuleAddition() throws RecognitionException {
        try {
            // InternalJavali.g:704:1: ( ruleAddition EOF )
            // InternalJavali.g:705:1: ruleAddition EOF
            {
             before(grammarAccess.getAdditionRule()); 
            pushFollow(FOLLOW_1);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getAdditionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAddition"


    // $ANTLR start "ruleAddition"
    // InternalJavali.g:712:1: ruleAddition : ( ( rule__Addition__Group__0 ) ) ;
    public final void ruleAddition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:716:2: ( ( ( rule__Addition__Group__0 ) ) )
            // InternalJavali.g:717:2: ( ( rule__Addition__Group__0 ) )
            {
            // InternalJavali.g:717:2: ( ( rule__Addition__Group__0 ) )
            // InternalJavali.g:718:3: ( rule__Addition__Group__0 )
            {
             before(grammarAccess.getAdditionAccess().getGroup()); 
            // InternalJavali.g:719:3: ( rule__Addition__Group__0 )
            // InternalJavali.g:719:4: rule__Addition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAddition"


    // $ANTLR start "entryRuleMultiplication"
    // InternalJavali.g:728:1: entryRuleMultiplication : ruleMultiplication EOF ;
    public final void entryRuleMultiplication() throws RecognitionException {
        try {
            // InternalJavali.g:729:1: ( ruleMultiplication EOF )
            // InternalJavali.g:730:1: ruleMultiplication EOF
            {
             before(grammarAccess.getMultiplicationRule()); 
            pushFollow(FOLLOW_1);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getMultiplicationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMultiplication"


    // $ANTLR start "ruleMultiplication"
    // InternalJavali.g:737:1: ruleMultiplication : ( ( rule__Multiplication__Group__0 ) ) ;
    public final void ruleMultiplication() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:741:2: ( ( ( rule__Multiplication__Group__0 ) ) )
            // InternalJavali.g:742:2: ( ( rule__Multiplication__Group__0 ) )
            {
            // InternalJavali.g:742:2: ( ( rule__Multiplication__Group__0 ) )
            // InternalJavali.g:743:3: ( rule__Multiplication__Group__0 )
            {
             before(grammarAccess.getMultiplicationAccess().getGroup()); 
            // InternalJavali.g:744:3: ( rule__Multiplication__Group__0 )
            // InternalJavali.g:744:4: rule__Multiplication__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMultiplication"


    // $ANTLR start "entryRulePrimary"
    // InternalJavali.g:753:1: entryRulePrimary : rulePrimary EOF ;
    public final void entryRulePrimary() throws RecognitionException {
        try {
            // InternalJavali.g:754:1: ( rulePrimary EOF )
            // InternalJavali.g:755:1: rulePrimary EOF
            {
             before(grammarAccess.getPrimaryRule()); 
            pushFollow(FOLLOW_1);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePrimary"


    // $ANTLR start "rulePrimary"
    // InternalJavali.g:762:1: rulePrimary : ( ( rule__Primary__Alternatives ) ) ;
    public final void rulePrimary() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:766:2: ( ( ( rule__Primary__Alternatives ) ) )
            // InternalJavali.g:767:2: ( ( rule__Primary__Alternatives ) )
            {
            // InternalJavali.g:767:2: ( ( rule__Primary__Alternatives ) )
            // InternalJavali.g:768:3: ( rule__Primary__Alternatives )
            {
             before(grammarAccess.getPrimaryAccess().getAlternatives()); 
            // InternalJavali.g:769:3: ( rule__Primary__Alternatives )
            // InternalJavali.g:769:4: rule__Primary__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Primary__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePrimary"


    // $ANTLR start "entryRuleLiteral"
    // InternalJavali.g:778:1: entryRuleLiteral : ruleLiteral EOF ;
    public final void entryRuleLiteral() throws RecognitionException {
        try {
            // InternalJavali.g:779:1: ( ruleLiteral EOF )
            // InternalJavali.g:780:1: ruleLiteral EOF
            {
             before(grammarAccess.getLiteralRule()); 
            pushFollow(FOLLOW_1);
            ruleLiteral();

            state._fsp--;

             after(grammarAccess.getLiteralRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLiteral"


    // $ANTLR start "ruleLiteral"
    // InternalJavali.g:787:1: ruleLiteral : ( ( rule__Literal__ValueAssignment ) ) ;
    public final void ruleLiteral() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:791:2: ( ( ( rule__Literal__ValueAssignment ) ) )
            // InternalJavali.g:792:2: ( ( rule__Literal__ValueAssignment ) )
            {
            // InternalJavali.g:792:2: ( ( rule__Literal__ValueAssignment ) )
            // InternalJavali.g:793:3: ( rule__Literal__ValueAssignment )
            {
             before(grammarAccess.getLiteralAccess().getValueAssignment()); 
            // InternalJavali.g:794:3: ( rule__Literal__ValueAssignment )
            // InternalJavali.g:794:4: rule__Literal__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Literal__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getLiteralAccess().getValueAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLiteral"


    // $ANTLR start "entryRuleNull"
    // InternalJavali.g:803:1: entryRuleNull : ruleNull EOF ;
    public final void entryRuleNull() throws RecognitionException {
        try {
            // InternalJavali.g:804:1: ( ruleNull EOF )
            // InternalJavali.g:805:1: ruleNull EOF
            {
             before(grammarAccess.getNullRule()); 
            pushFollow(FOLLOW_1);
            ruleNull();

            state._fsp--;

             after(grammarAccess.getNullRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNull"


    // $ANTLR start "ruleNull"
    // InternalJavali.g:812:1: ruleNull : ( ( rule__Null__Group__0 ) ) ;
    public final void ruleNull() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:816:2: ( ( ( rule__Null__Group__0 ) ) )
            // InternalJavali.g:817:2: ( ( rule__Null__Group__0 ) )
            {
            // InternalJavali.g:817:2: ( ( rule__Null__Group__0 ) )
            // InternalJavali.g:818:3: ( rule__Null__Group__0 )
            {
             before(grammarAccess.getNullAccess().getGroup()); 
            // InternalJavali.g:819:3: ( rule__Null__Group__0 )
            // InternalJavali.g:819:4: rule__Null__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Null__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNullAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNull"


    // $ANTLR start "entryRuleVarExpression"
    // InternalJavali.g:828:1: entryRuleVarExpression : ruleVarExpression EOF ;
    public final void entryRuleVarExpression() throws RecognitionException {
        try {
            // InternalJavali.g:829:1: ( ruleVarExpression EOF )
            // InternalJavali.g:830:1: ruleVarExpression EOF
            {
             before(grammarAccess.getVarExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleVarExpression();

            state._fsp--;

             after(grammarAccess.getVarExpressionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVarExpression"


    // $ANTLR start "ruleVarExpression"
    // InternalJavali.g:837:1: ruleVarExpression : ( ( rule__VarExpression__Group__0 ) ) ;
    public final void ruleVarExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:841:2: ( ( ( rule__VarExpression__Group__0 ) ) )
            // InternalJavali.g:842:2: ( ( rule__VarExpression__Group__0 ) )
            {
            // InternalJavali.g:842:2: ( ( rule__VarExpression__Group__0 ) )
            // InternalJavali.g:843:3: ( rule__VarExpression__Group__0 )
            {
             before(grammarAccess.getVarExpressionAccess().getGroup()); 
            // InternalJavali.g:844:3: ( rule__VarExpression__Group__0 )
            // InternalJavali.g:844:4: rule__VarExpression__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getVarExpressionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVarExpression"


    // $ANTLR start "entryRuleProcCall"
    // InternalJavali.g:853:1: entryRuleProcCall : ruleProcCall EOF ;
    public final void entryRuleProcCall() throws RecognitionException {
        try {
            // InternalJavali.g:854:1: ( ruleProcCall EOF )
            // InternalJavali.g:855:1: ruleProcCall EOF
            {
             before(grammarAccess.getProcCallRule()); 
            pushFollow(FOLLOW_1);
            ruleProcCall();

            state._fsp--;

             after(grammarAccess.getProcCallRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleProcCall"


    // $ANTLR start "ruleProcCall"
    // InternalJavali.g:862:1: ruleProcCall : ( ( rule__ProcCall__Group__0 ) ) ;
    public final void ruleProcCall() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:866:2: ( ( ( rule__ProcCall__Group__0 ) ) )
            // InternalJavali.g:867:2: ( ( rule__ProcCall__Group__0 ) )
            {
            // InternalJavali.g:867:2: ( ( rule__ProcCall__Group__0 ) )
            // InternalJavali.g:868:3: ( rule__ProcCall__Group__0 )
            {
             before(grammarAccess.getProcCallAccess().getGroup()); 
            // InternalJavali.g:869:3: ( rule__ProcCall__Group__0 )
            // InternalJavali.g:869:4: rule__ProcCall__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ProcCall__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getProcCallAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleProcCall"


    // $ANTLR start "entryRuleType"
    // InternalJavali.g:878:1: entryRuleType : ruleType EOF ;
    public final void entryRuleType() throws RecognitionException {
        try {
            // InternalJavali.g:879:1: ( ruleType EOF )
            // InternalJavali.g:880:1: ruleType EOF
            {
             before(grammarAccess.getTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleType();

            state._fsp--;

             after(grammarAccess.getTypeRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleType"


    // $ANTLR start "ruleType"
    // InternalJavali.g:887:1: ruleType : ( ( rule__Type__Group__0 ) ) ;
    public final void ruleType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:891:2: ( ( ( rule__Type__Group__0 ) ) )
            // InternalJavali.g:892:2: ( ( rule__Type__Group__0 ) )
            {
            // InternalJavali.g:892:2: ( ( rule__Type__Group__0 ) )
            // InternalJavali.g:893:3: ( rule__Type__Group__0 )
            {
             before(grammarAccess.getTypeAccess().getGroup()); 
            // InternalJavali.g:894:3: ( rule__Type__Group__0 )
            // InternalJavali.g:894:4: rule__Type__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Type__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTypeAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleType"


    // $ANTLR start "entryRuleNewArray"
    // InternalJavali.g:903:1: entryRuleNewArray : ruleNewArray EOF ;
    public final void entryRuleNewArray() throws RecognitionException {
        try {
            // InternalJavali.g:904:1: ( ruleNewArray EOF )
            // InternalJavali.g:905:1: ruleNewArray EOF
            {
             before(grammarAccess.getNewArrayRule()); 
            pushFollow(FOLLOW_1);
            ruleNewArray();

            state._fsp--;

             after(grammarAccess.getNewArrayRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNewArray"


    // $ANTLR start "ruleNewArray"
    // InternalJavali.g:912:1: ruleNewArray : ( ( rule__NewArray__Group__0 ) ) ;
    public final void ruleNewArray() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:916:2: ( ( ( rule__NewArray__Group__0 ) ) )
            // InternalJavali.g:917:2: ( ( rule__NewArray__Group__0 ) )
            {
            // InternalJavali.g:917:2: ( ( rule__NewArray__Group__0 ) )
            // InternalJavali.g:918:3: ( rule__NewArray__Group__0 )
            {
             before(grammarAccess.getNewArrayAccess().getGroup()); 
            // InternalJavali.g:919:3: ( rule__NewArray__Group__0 )
            // InternalJavali.g:919:4: rule__NewArray__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NewArray__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNewArrayAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNewArray"


    // $ANTLR start "entryRuleNewObject"
    // InternalJavali.g:928:1: entryRuleNewObject : ruleNewObject EOF ;
    public final void entryRuleNewObject() throws RecognitionException {
        try {
            // InternalJavali.g:929:1: ( ruleNewObject EOF )
            // InternalJavali.g:930:1: ruleNewObject EOF
            {
             before(grammarAccess.getNewObjectRule()); 
            pushFollow(FOLLOW_1);
            ruleNewObject();

            state._fsp--;

             after(grammarAccess.getNewObjectRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNewObject"


    // $ANTLR start "ruleNewObject"
    // InternalJavali.g:937:1: ruleNewObject : ( ( rule__NewObject__Group__0 ) ) ;
    public final void ruleNewObject() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:941:2: ( ( ( rule__NewObject__Group__0 ) ) )
            // InternalJavali.g:942:2: ( ( rule__NewObject__Group__0 ) )
            {
            // InternalJavali.g:942:2: ( ( rule__NewObject__Group__0 ) )
            // InternalJavali.g:943:3: ( rule__NewObject__Group__0 )
            {
             before(grammarAccess.getNewObjectAccess().getGroup()); 
            // InternalJavali.g:944:3: ( rule__NewObject__Group__0 )
            // InternalJavali.g:944:4: rule__NewObject__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NewObject__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNewObjectAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNewObject"


    // $ANTLR start "entryRuleIdentifier"
    // InternalJavali.g:953:1: entryRuleIdentifier : ruleIdentifier EOF ;
    public final void entryRuleIdentifier() throws RecognitionException {
        try {
            // InternalJavali.g:954:1: ( ruleIdentifier EOF )
            // InternalJavali.g:955:1: ruleIdentifier EOF
            {
             before(grammarAccess.getIdentifierRule()); 
            pushFollow(FOLLOW_1);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getIdentifierRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleIdentifier"


    // $ANTLR start "ruleIdentifier"
    // InternalJavali.g:962:1: ruleIdentifier : ( ( rule__Identifier__IdAssignment ) ) ;
    public final void ruleIdentifier() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:966:2: ( ( ( rule__Identifier__IdAssignment ) ) )
            // InternalJavali.g:967:2: ( ( rule__Identifier__IdAssignment ) )
            {
            // InternalJavali.g:967:2: ( ( rule__Identifier__IdAssignment ) )
            // InternalJavali.g:968:3: ( rule__Identifier__IdAssignment )
            {
             before(grammarAccess.getIdentifierAccess().getIdAssignment()); 
            // InternalJavali.g:969:3: ( rule__Identifier__IdAssignment )
            // InternalJavali.g:969:4: rule__Identifier__IdAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Identifier__IdAssignment();

            state._fsp--;


            }

             after(grammarAccess.getIdentifierAccess().getIdAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleIdentifier"


    // $ANTLR start "rule__Module__Alternatives"
    // InternalJavali.g:977:1: rule__Module__Alternatives : ( ( ( rule__Module__ConstantsAssignment_0 ) ) | ( ( rule__Module__RecordsAssignment_1 ) ) | ( ( rule__Module__ProceduresAssignment_2 ) ) );
    public final void rule__Module__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:981:1: ( ( ( rule__Module__ConstantsAssignment_0 ) ) | ( ( rule__Module__RecordsAssignment_1 ) ) | ( ( rule__Module__ProceduresAssignment_2 ) ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 52:
                {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==24) ) {
                    alt2=1;
                }
                else if ( (LA2_1==RULE_ID||LA2_1==53) ) {
                    alt2=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
                }
                break;
            case 24:
                {
                alt2=1;
                }
                break;
            case 27:
                {
                alt2=2;
                }
                break;
            case RULE_ML_COMMENT_DOC:
            case RULE_ID:
            case 53:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalJavali.g:982:2: ( ( rule__Module__ConstantsAssignment_0 ) )
                    {
                    // InternalJavali.g:982:2: ( ( rule__Module__ConstantsAssignment_0 ) )
                    // InternalJavali.g:983:3: ( rule__Module__ConstantsAssignment_0 )
                    {
                     before(grammarAccess.getModuleAccess().getConstantsAssignment_0()); 
                    // InternalJavali.g:984:3: ( rule__Module__ConstantsAssignment_0 )
                    // InternalJavali.g:984:4: rule__Module__ConstantsAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Module__ConstantsAssignment_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getModuleAccess().getConstantsAssignment_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:988:2: ( ( rule__Module__RecordsAssignment_1 ) )
                    {
                    // InternalJavali.g:988:2: ( ( rule__Module__RecordsAssignment_1 ) )
                    // InternalJavali.g:989:3: ( rule__Module__RecordsAssignment_1 )
                    {
                     before(grammarAccess.getModuleAccess().getRecordsAssignment_1()); 
                    // InternalJavali.g:990:3: ( rule__Module__RecordsAssignment_1 )
                    // InternalJavali.g:990:4: rule__Module__RecordsAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Module__RecordsAssignment_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getModuleAccess().getRecordsAssignment_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalJavali.g:994:2: ( ( rule__Module__ProceduresAssignment_2 ) )
                    {
                    // InternalJavali.g:994:2: ( ( rule__Module__ProceduresAssignment_2 ) )
                    // InternalJavali.g:995:3: ( rule__Module__ProceduresAssignment_2 )
                    {
                     before(grammarAccess.getModuleAccess().getProceduresAssignment_2()); 
                    // InternalJavali.g:996:3: ( rule__Module__ProceduresAssignment_2 )
                    // InternalJavali.g:996:4: rule__Module__ProceduresAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Module__ProceduresAssignment_2();

                    state._fsp--;


                    }

                     after(grammarAccess.getModuleAccess().getProceduresAssignment_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Module__Alternatives"


    // $ANTLR start "rule__Procedure__Alternatives_2"
    // InternalJavali.g:1004:1: rule__Procedure__Alternatives_2 : ( ( ( rule__Procedure__RetTypeAssignment_2_0 ) ) | ( ( rule__Procedure__VoidAssignment_2_1 ) ) );
    public final void rule__Procedure__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1008:1: ( ( ( rule__Procedure__RetTypeAssignment_2_0 ) ) | ( ( rule__Procedure__VoidAssignment_2_1 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            else if ( (LA3_0==53) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalJavali.g:1009:2: ( ( rule__Procedure__RetTypeAssignment_2_0 ) )
                    {
                    // InternalJavali.g:1009:2: ( ( rule__Procedure__RetTypeAssignment_2_0 ) )
                    // InternalJavali.g:1010:3: ( rule__Procedure__RetTypeAssignment_2_0 )
                    {
                     before(grammarAccess.getProcedureAccess().getRetTypeAssignment_2_0()); 
                    // InternalJavali.g:1011:3: ( rule__Procedure__RetTypeAssignment_2_0 )
                    // InternalJavali.g:1011:4: rule__Procedure__RetTypeAssignment_2_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Procedure__RetTypeAssignment_2_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getProcedureAccess().getRetTypeAssignment_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1015:2: ( ( rule__Procedure__VoidAssignment_2_1 ) )
                    {
                    // InternalJavali.g:1015:2: ( ( rule__Procedure__VoidAssignment_2_1 ) )
                    // InternalJavali.g:1016:3: ( rule__Procedure__VoidAssignment_2_1 )
                    {
                     before(grammarAccess.getProcedureAccess().getVoidAssignment_2_1()); 
                    // InternalJavali.g:1017:3: ( rule__Procedure__VoidAssignment_2_1 )
                    // InternalJavali.g:1017:4: rule__Procedure__VoidAssignment_2_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Procedure__VoidAssignment_2_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getProcedureAccess().getVoidAssignment_2_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Alternatives_2"


    // $ANTLR start "rule__Statement__Alternatives"
    // InternalJavali.g:1025:1: rule__Statement__Alternatives : ( ( ( rule__Statement__Group_0__0 ) ) | ( ( rule__Statement__Group_1__0 ) ) | ( ( rule__Statement__Group_2__0 ) ) | ( ( rule__Statement__Group_3__0 ) ) | ( ( rule__Statement__Group_4__0 ) ) | ( ( rule__Statement__Group_5__0 ) ) | ( ( rule__Statement__Group_6__0 ) ) | ( ( rule__Statement__Group_7__0 ) ) | ( ruleIfElse ) | ( ruleWhile ) | ( ruleFor ) | ( ( rule__Statement__Group_11__0 ) ) );
    public final void rule__Statement__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1029:1: ( ( ( rule__Statement__Group_0__0 ) ) | ( ( rule__Statement__Group_1__0 ) ) | ( ( rule__Statement__Group_2__0 ) ) | ( ( rule__Statement__Group_3__0 ) ) | ( ( rule__Statement__Group_4__0 ) ) | ( ( rule__Statement__Group_5__0 ) ) | ( ( rule__Statement__Group_6__0 ) ) | ( ( rule__Statement__Group_7__0 ) ) | ( ruleIfElse ) | ( ruleWhile ) | ( ruleFor ) | ( ( rule__Statement__Group_11__0 ) ) )
            int alt4=12;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // InternalJavali.g:1030:2: ( ( rule__Statement__Group_0__0 ) )
                    {
                    // InternalJavali.g:1030:2: ( ( rule__Statement__Group_0__0 ) )
                    // InternalJavali.g:1031:3: ( rule__Statement__Group_0__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_0()); 
                    // InternalJavali.g:1032:3: ( rule__Statement__Group_0__0 )
                    // InternalJavali.g:1032:4: rule__Statement__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1036:2: ( ( rule__Statement__Group_1__0 ) )
                    {
                    // InternalJavali.g:1036:2: ( ( rule__Statement__Group_1__0 ) )
                    // InternalJavali.g:1037:3: ( rule__Statement__Group_1__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_1()); 
                    // InternalJavali.g:1038:3: ( rule__Statement__Group_1__0 )
                    // InternalJavali.g:1038:4: rule__Statement__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalJavali.g:1042:2: ( ( rule__Statement__Group_2__0 ) )
                    {
                    // InternalJavali.g:1042:2: ( ( rule__Statement__Group_2__0 ) )
                    // InternalJavali.g:1043:3: ( rule__Statement__Group_2__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_2()); 
                    // InternalJavali.g:1044:3: ( rule__Statement__Group_2__0 )
                    // InternalJavali.g:1044:4: rule__Statement__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalJavali.g:1048:2: ( ( rule__Statement__Group_3__0 ) )
                    {
                    // InternalJavali.g:1048:2: ( ( rule__Statement__Group_3__0 ) )
                    // InternalJavali.g:1049:3: ( rule__Statement__Group_3__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_3()); 
                    // InternalJavali.g:1050:3: ( rule__Statement__Group_3__0 )
                    // InternalJavali.g:1050:4: rule__Statement__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalJavali.g:1054:2: ( ( rule__Statement__Group_4__0 ) )
                    {
                    // InternalJavali.g:1054:2: ( ( rule__Statement__Group_4__0 ) )
                    // InternalJavali.g:1055:3: ( rule__Statement__Group_4__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_4()); 
                    // InternalJavali.g:1056:3: ( rule__Statement__Group_4__0 )
                    // InternalJavali.g:1056:4: rule__Statement__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_4__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalJavali.g:1060:2: ( ( rule__Statement__Group_5__0 ) )
                    {
                    // InternalJavali.g:1060:2: ( ( rule__Statement__Group_5__0 ) )
                    // InternalJavali.g:1061:3: ( rule__Statement__Group_5__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_5()); 
                    // InternalJavali.g:1062:3: ( rule__Statement__Group_5__0 )
                    // InternalJavali.g:1062:4: rule__Statement__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_5__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalJavali.g:1066:2: ( ( rule__Statement__Group_6__0 ) )
                    {
                    // InternalJavali.g:1066:2: ( ( rule__Statement__Group_6__0 ) )
                    // InternalJavali.g:1067:3: ( rule__Statement__Group_6__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_6()); 
                    // InternalJavali.g:1068:3: ( rule__Statement__Group_6__0 )
                    // InternalJavali.g:1068:4: rule__Statement__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_6__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalJavali.g:1072:2: ( ( rule__Statement__Group_7__0 ) )
                    {
                    // InternalJavali.g:1072:2: ( ( rule__Statement__Group_7__0 ) )
                    // InternalJavali.g:1073:3: ( rule__Statement__Group_7__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_7()); 
                    // InternalJavali.g:1074:3: ( rule__Statement__Group_7__0 )
                    // InternalJavali.g:1074:4: rule__Statement__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_7__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalJavali.g:1078:2: ( ruleIfElse )
                    {
                    // InternalJavali.g:1078:2: ( ruleIfElse )
                    // InternalJavali.g:1079:3: ruleIfElse
                    {
                     before(grammarAccess.getStatementAccess().getIfElseParserRuleCall_8()); 
                    pushFollow(FOLLOW_2);
                    ruleIfElse();

                    state._fsp--;

                     after(grammarAccess.getStatementAccess().getIfElseParserRuleCall_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalJavali.g:1084:2: ( ruleWhile )
                    {
                    // InternalJavali.g:1084:2: ( ruleWhile )
                    // InternalJavali.g:1085:3: ruleWhile
                    {
                     before(grammarAccess.getStatementAccess().getWhileParserRuleCall_9()); 
                    pushFollow(FOLLOW_2);
                    ruleWhile();

                    state._fsp--;

                     after(grammarAccess.getStatementAccess().getWhileParserRuleCall_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalJavali.g:1090:2: ( ruleFor )
                    {
                    // InternalJavali.g:1090:2: ( ruleFor )
                    // InternalJavali.g:1091:3: ruleFor
                    {
                     before(grammarAccess.getStatementAccess().getForParserRuleCall_10()); 
                    pushFollow(FOLLOW_2);
                    ruleFor();

                    state._fsp--;

                     after(grammarAccess.getStatementAccess().getForParserRuleCall_10()); 

                    }


                    }
                    break;
                case 12 :
                    // InternalJavali.g:1096:2: ( ( rule__Statement__Group_11__0 ) )
                    {
                    // InternalJavali.g:1096:2: ( ( rule__Statement__Group_11__0 ) )
                    // InternalJavali.g:1097:3: ( rule__Statement__Group_11__0 )
                    {
                     before(grammarAccess.getStatementAccess().getGroup_11()); 
                    // InternalJavali.g:1098:3: ( rule__Statement__Group_11__0 )
                    // InternalJavali.g:1098:4: rule__Statement__Group_11__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Statement__Group_11__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getStatementAccess().getGroup_11()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Alternatives"


    // $ANTLR start "rule__ForStatementInit__Alternatives"
    // InternalJavali.g:1106:1: rule__ForStatementInit__Alternatives : ( ( ruleVarDeclarationAssign ) | ( ruleVarAssign ) );
    public final void rule__ForStatementInit__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1110:1: ( ( ruleVarDeclarationAssign ) | ( ruleVarAssign ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 48:
                    {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==49) ) {
                        alt5=1;
                    }
                    else if ( ((LA5_2>=RULE_PRIMITIVE_VALUE && LA5_2<=RULE_ID)||LA5_2==30||(LA5_2>=46 && LA5_2<=47)||LA5_2==51) ) {
                        alt5=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;
                    }
                    }
                    break;
                case 25:
                case 50:
                    {
                    alt5=2;
                    }
                    break;
                case RULE_ID:
                    {
                    alt5=1;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalJavali.g:1111:2: ( ruleVarDeclarationAssign )
                    {
                    // InternalJavali.g:1111:2: ( ruleVarDeclarationAssign )
                    // InternalJavali.g:1112:3: ruleVarDeclarationAssign
                    {
                     before(grammarAccess.getForStatementInitAccess().getVarDeclarationAssignParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleVarDeclarationAssign();

                    state._fsp--;

                     after(grammarAccess.getForStatementInitAccess().getVarDeclarationAssignParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1117:2: ( ruleVarAssign )
                    {
                    // InternalJavali.g:1117:2: ( ruleVarAssign )
                    // InternalJavali.g:1118:3: ruleVarAssign
                    {
                     before(grammarAccess.getForStatementInitAccess().getVarAssignParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleVarAssign();

                    state._fsp--;

                     after(grammarAccess.getForStatementInitAccess().getVarAssignParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForStatementInit__Alternatives"


    // $ANTLR start "rule__ForStatement__Alternatives"
    // InternalJavali.g:1127:1: rule__ForStatement__Alternatives : ( ( ruleVarAssign ) | ( ruleIncrement ) | ( ruleDecrement ) );
    public final void rule__ForStatement__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1131:1: ( ( ruleVarAssign ) | ( ruleIncrement ) | ( ruleDecrement ) )
            int alt6=3;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                switch ( input.LA(2) ) {
                case 25:
                case 48:
                case 50:
                    {
                    alt6=1;
                    }
                    break;
                case 41:
                    {
                    alt6=2;
                    }
                    break;
                case 42:
                    {
                    alt6=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalJavali.g:1132:2: ( ruleVarAssign )
                    {
                    // InternalJavali.g:1132:2: ( ruleVarAssign )
                    // InternalJavali.g:1133:3: ruleVarAssign
                    {
                     before(grammarAccess.getForStatementAccess().getVarAssignParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleVarAssign();

                    state._fsp--;

                     after(grammarAccess.getForStatementAccess().getVarAssignParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1138:2: ( ruleIncrement )
                    {
                    // InternalJavali.g:1138:2: ( ruleIncrement )
                    // InternalJavali.g:1139:3: ruleIncrement
                    {
                     before(grammarAccess.getForStatementAccess().getIncrementParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleIncrement();

                    state._fsp--;

                     after(grammarAccess.getForStatementAccess().getIncrementParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalJavali.g:1144:2: ( ruleDecrement )
                    {
                    // InternalJavali.g:1144:2: ( ruleDecrement )
                    // InternalJavali.g:1145:3: ruleDecrement
                    {
                     before(grammarAccess.getForStatementAccess().getDecrementParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleDecrement();

                    state._fsp--;

                     after(grammarAccess.getForStatementAccess().getDecrementParserRuleCall_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ForStatement__Alternatives"


    // $ANTLR start "rule__Equality__OperatorAlternatives_1_1_0"
    // InternalJavali.g:1154:1: rule__Equality__OperatorAlternatives_1_1_0 : ( ( '==' ) | ( '!=' ) );
    public final void rule__Equality__OperatorAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1158:1: ( ( '==' ) | ( '!=' ) )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==13) ) {
                alt7=1;
            }
            else if ( (LA7_0==14) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalJavali.g:1159:2: ( '==' )
                    {
                    // InternalJavali.g:1159:2: ( '==' )
                    // InternalJavali.g:1160:3: '=='
                    {
                     before(grammarAccess.getEqualityAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_0()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getEqualityAccess().getOperatorEqualsSignEqualsSignKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1165:2: ( '!=' )
                    {
                    // InternalJavali.g:1165:2: ( '!=' )
                    // InternalJavali.g:1166:3: '!='
                    {
                     before(grammarAccess.getEqualityAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_1()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getEqualityAccess().getOperatorExclamationMarkEqualsSignKeyword_1_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__OperatorAlternatives_1_1_0"


    // $ANTLR start "rule__Relation__OperatorAlternatives_1_1_0"
    // InternalJavali.g:1175:1: rule__Relation__OperatorAlternatives_1_1_0 : ( ( '<' ) | ( '<=' ) | ( '>' ) | ( '>=' ) );
    public final void rule__Relation__OperatorAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1179:1: ( ( '<' ) | ( '<=' ) | ( '>' ) | ( '>=' ) )
            int alt8=4;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt8=1;
                }
                break;
            case 16:
                {
                alt8=2;
                }
                break;
            case 17:
                {
                alt8=3;
                }
                break;
            case 18:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalJavali.g:1180:2: ( '<' )
                    {
                    // InternalJavali.g:1180:2: ( '<' )
                    // InternalJavali.g:1181:3: '<'
                    {
                     before(grammarAccess.getRelationAccess().getOperatorLessThanSignKeyword_1_1_0_0()); 
                    match(input,15,FOLLOW_2); 
                     after(grammarAccess.getRelationAccess().getOperatorLessThanSignKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1186:2: ( '<=' )
                    {
                    // InternalJavali.g:1186:2: ( '<=' )
                    // InternalJavali.g:1187:3: '<='
                    {
                     before(grammarAccess.getRelationAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_1()); 
                    match(input,16,FOLLOW_2); 
                     after(grammarAccess.getRelationAccess().getOperatorLessThanSignEqualsSignKeyword_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalJavali.g:1192:2: ( '>' )
                    {
                    // InternalJavali.g:1192:2: ( '>' )
                    // InternalJavali.g:1193:3: '>'
                    {
                     before(grammarAccess.getRelationAccess().getOperatorGreaterThanSignKeyword_1_1_0_2()); 
                    match(input,17,FOLLOW_2); 
                     after(grammarAccess.getRelationAccess().getOperatorGreaterThanSignKeyword_1_1_0_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalJavali.g:1198:2: ( '>=' )
                    {
                    // InternalJavali.g:1198:2: ( '>=' )
                    // InternalJavali.g:1199:3: '>='
                    {
                     before(grammarAccess.getRelationAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_3()); 
                    match(input,18,FOLLOW_2); 
                     after(grammarAccess.getRelationAccess().getOperatorGreaterThanSignEqualsSignKeyword_1_1_0_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__OperatorAlternatives_1_1_0"


    // $ANTLR start "rule__Addition__OperatorAlternatives_1_1_0"
    // InternalJavali.g:1208:1: rule__Addition__OperatorAlternatives_1_1_0 : ( ( '+' ) | ( '-' ) );
    public final void rule__Addition__OperatorAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1212:1: ( ( '+' ) | ( '-' ) )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==19) ) {
                alt9=1;
            }
            else if ( (LA9_0==20) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // InternalJavali.g:1213:2: ( '+' )
                    {
                    // InternalJavali.g:1213:2: ( '+' )
                    // InternalJavali.g:1214:3: '+'
                    {
                     before(grammarAccess.getAdditionAccess().getOperatorPlusSignKeyword_1_1_0_0()); 
                    match(input,19,FOLLOW_2); 
                     after(grammarAccess.getAdditionAccess().getOperatorPlusSignKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1219:2: ( '-' )
                    {
                    // InternalJavali.g:1219:2: ( '-' )
                    // InternalJavali.g:1220:3: '-'
                    {
                     before(grammarAccess.getAdditionAccess().getOperatorHyphenMinusKeyword_1_1_0_1()); 
                    match(input,20,FOLLOW_2); 
                     after(grammarAccess.getAdditionAccess().getOperatorHyphenMinusKeyword_1_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__OperatorAlternatives_1_1_0"


    // $ANTLR start "rule__Multiplication__OperatorAlternatives_1_1_0"
    // InternalJavali.g:1229:1: rule__Multiplication__OperatorAlternatives_1_1_0 : ( ( '*' ) | ( '/' ) | ( '%' ) );
    public final void rule__Multiplication__OperatorAlternatives_1_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1233:1: ( ( '*' ) | ( '/' ) | ( '%' ) )
            int alt10=3;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt10=1;
                }
                break;
            case 22:
                {
                alt10=2;
                }
                break;
            case 23:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalJavali.g:1234:2: ( '*' )
                    {
                    // InternalJavali.g:1234:2: ( '*' )
                    // InternalJavali.g:1235:3: '*'
                    {
                     before(grammarAccess.getMultiplicationAccess().getOperatorAsteriskKeyword_1_1_0_0()); 
                    match(input,21,FOLLOW_2); 
                     after(grammarAccess.getMultiplicationAccess().getOperatorAsteriskKeyword_1_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1240:2: ( '/' )
                    {
                    // InternalJavali.g:1240:2: ( '/' )
                    // InternalJavali.g:1241:3: '/'
                    {
                     before(grammarAccess.getMultiplicationAccess().getOperatorSolidusKeyword_1_1_0_1()); 
                    match(input,22,FOLLOW_2); 
                     after(grammarAccess.getMultiplicationAccess().getOperatorSolidusKeyword_1_1_0_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalJavali.g:1246:2: ( '%' )
                    {
                    // InternalJavali.g:1246:2: ( '%' )
                    // InternalJavali.g:1247:3: '%'
                    {
                     before(grammarAccess.getMultiplicationAccess().getOperatorPercentSignKeyword_1_1_0_2()); 
                    match(input,23,FOLLOW_2); 
                     after(grammarAccess.getMultiplicationAccess().getOperatorPercentSignKeyword_1_1_0_2()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__OperatorAlternatives_1_1_0"


    // $ANTLR start "rule__Primary__Alternatives"
    // InternalJavali.g:1256:1: rule__Primary__Alternatives : ( ( ruleLiteral ) | ( ruleNull ) | ( ruleProcCall ) | ( ruleVarExpression ) | ( ruleNewArray ) | ( ruleNewObject ) | ( ( rule__Primary__Group_6__0 ) ) | ( ( rule__Primary__Group_7__0 ) ) );
    public final void rule__Primary__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1260:1: ( ( ruleLiteral ) | ( ruleNull ) | ( ruleProcCall ) | ( ruleVarExpression ) | ( ruleNewArray ) | ( ruleNewObject ) | ( ( rule__Primary__Group_6__0 ) ) | ( ( rule__Primary__Group_7__0 ) ) )
            int alt11=8;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // InternalJavali.g:1261:2: ( ruleLiteral )
                    {
                    // InternalJavali.g:1261:2: ( ruleLiteral )
                    // InternalJavali.g:1262:3: ruleLiteral
                    {
                     before(grammarAccess.getPrimaryAccess().getLiteralParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleLiteral();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getLiteralParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalJavali.g:1267:2: ( ruleNull )
                    {
                    // InternalJavali.g:1267:2: ( ruleNull )
                    // InternalJavali.g:1268:3: ruleNull
                    {
                     before(grammarAccess.getPrimaryAccess().getNullParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleNull();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getNullParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalJavali.g:1273:2: ( ruleProcCall )
                    {
                    // InternalJavali.g:1273:2: ( ruleProcCall )
                    // InternalJavali.g:1274:3: ruleProcCall
                    {
                     before(grammarAccess.getPrimaryAccess().getProcCallParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleProcCall();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getProcCallParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalJavali.g:1279:2: ( ruleVarExpression )
                    {
                    // InternalJavali.g:1279:2: ( ruleVarExpression )
                    // InternalJavali.g:1280:3: ruleVarExpression
                    {
                     before(grammarAccess.getPrimaryAccess().getVarExpressionParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleVarExpression();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getVarExpressionParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalJavali.g:1285:2: ( ruleNewArray )
                    {
                    // InternalJavali.g:1285:2: ( ruleNewArray )
                    // InternalJavali.g:1286:3: ruleNewArray
                    {
                     before(grammarAccess.getPrimaryAccess().getNewArrayParserRuleCall_4()); 
                    pushFollow(FOLLOW_2);
                    ruleNewArray();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getNewArrayParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalJavali.g:1291:2: ( ruleNewObject )
                    {
                    // InternalJavali.g:1291:2: ( ruleNewObject )
                    // InternalJavali.g:1292:3: ruleNewObject
                    {
                     before(grammarAccess.getPrimaryAccess().getNewObjectParserRuleCall_5()); 
                    pushFollow(FOLLOW_2);
                    ruleNewObject();

                    state._fsp--;

                     after(grammarAccess.getPrimaryAccess().getNewObjectParserRuleCall_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalJavali.g:1297:2: ( ( rule__Primary__Group_6__0 ) )
                    {
                    // InternalJavali.g:1297:2: ( ( rule__Primary__Group_6__0 ) )
                    // InternalJavali.g:1298:3: ( rule__Primary__Group_6__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_6()); 
                    // InternalJavali.g:1299:3: ( rule__Primary__Group_6__0 )
                    // InternalJavali.g:1299:4: rule__Primary__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Primary__Group_6__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalJavali.g:1303:2: ( ( rule__Primary__Group_7__0 ) )
                    {
                    // InternalJavali.g:1303:2: ( ( rule__Primary__Group_7__0 ) )
                    // InternalJavali.g:1304:3: ( rule__Primary__Group_7__0 )
                    {
                     before(grammarAccess.getPrimaryAccess().getGroup_7()); 
                    // InternalJavali.g:1305:3: ( rule__Primary__Group_7__0 )
                    // InternalJavali.g:1305:4: rule__Primary__Group_7__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Primary__Group_7__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryAccess().getGroup_7()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Alternatives"


    // $ANTLR start "rule__Constant__Group__0"
    // InternalJavali.g:1313:1: rule__Constant__Group__0 : rule__Constant__Group__0__Impl rule__Constant__Group__1 ;
    public final void rule__Constant__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1317:1: ( rule__Constant__Group__0__Impl rule__Constant__Group__1 )
            // InternalJavali.g:1318:2: rule__Constant__Group__0__Impl rule__Constant__Group__1
            {
            pushFollow(FOLLOW_4);
            rule__Constant__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Constant__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__0"


    // $ANTLR start "rule__Constant__Group__0__Impl"
    // InternalJavali.g:1325:1: rule__Constant__Group__0__Impl : ( ( rule__Constant__StaticAssignment_0 )? ) ;
    public final void rule__Constant__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1329:1: ( ( ( rule__Constant__StaticAssignment_0 )? ) )
            // InternalJavali.g:1330:1: ( ( rule__Constant__StaticAssignment_0 )? )
            {
            // InternalJavali.g:1330:1: ( ( rule__Constant__StaticAssignment_0 )? )
            // InternalJavali.g:1331:2: ( rule__Constant__StaticAssignment_0 )?
            {
             before(grammarAccess.getConstantAccess().getStaticAssignment_0()); 
            // InternalJavali.g:1332:2: ( rule__Constant__StaticAssignment_0 )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==52) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalJavali.g:1332:3: rule__Constant__StaticAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Constant__StaticAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getConstantAccess().getStaticAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__0__Impl"


    // $ANTLR start "rule__Constant__Group__1"
    // InternalJavali.g:1340:1: rule__Constant__Group__1 : rule__Constant__Group__1__Impl rule__Constant__Group__2 ;
    public final void rule__Constant__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1344:1: ( rule__Constant__Group__1__Impl rule__Constant__Group__2 )
            // InternalJavali.g:1345:2: rule__Constant__Group__1__Impl rule__Constant__Group__2
            {
            pushFollow(FOLLOW_5);
            rule__Constant__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Constant__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__1"


    // $ANTLR start "rule__Constant__Group__1__Impl"
    // InternalJavali.g:1352:1: rule__Constant__Group__1__Impl : ( 'final' ) ;
    public final void rule__Constant__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1356:1: ( ( 'final' ) )
            // InternalJavali.g:1357:1: ( 'final' )
            {
            // InternalJavali.g:1357:1: ( 'final' )
            // InternalJavali.g:1358:2: 'final'
            {
             before(grammarAccess.getConstantAccess().getFinalKeyword_1()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getConstantAccess().getFinalKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__1__Impl"


    // $ANTLR start "rule__Constant__Group__2"
    // InternalJavali.g:1367:1: rule__Constant__Group__2 : rule__Constant__Group__2__Impl rule__Constant__Group__3 ;
    public final void rule__Constant__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1371:1: ( rule__Constant__Group__2__Impl rule__Constant__Group__3 )
            // InternalJavali.g:1372:2: rule__Constant__Group__2__Impl rule__Constant__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Constant__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Constant__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__2"


    // $ANTLR start "rule__Constant__Group__2__Impl"
    // InternalJavali.g:1379:1: rule__Constant__Group__2__Impl : ( ( rule__Constant__TypeAssignment_2 ) ) ;
    public final void rule__Constant__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1383:1: ( ( ( rule__Constant__TypeAssignment_2 ) ) )
            // InternalJavali.g:1384:1: ( ( rule__Constant__TypeAssignment_2 ) )
            {
            // InternalJavali.g:1384:1: ( ( rule__Constant__TypeAssignment_2 ) )
            // InternalJavali.g:1385:2: ( rule__Constant__TypeAssignment_2 )
            {
             before(grammarAccess.getConstantAccess().getTypeAssignment_2()); 
            // InternalJavali.g:1386:2: ( rule__Constant__TypeAssignment_2 )
            // InternalJavali.g:1386:3: rule__Constant__TypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Constant__TypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getConstantAccess().getTypeAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__2__Impl"


    // $ANTLR start "rule__Constant__Group__3"
    // InternalJavali.g:1394:1: rule__Constant__Group__3 : rule__Constant__Group__3__Impl rule__Constant__Group__4 ;
    public final void rule__Constant__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1398:1: ( rule__Constant__Group__3__Impl rule__Constant__Group__4 )
            // InternalJavali.g:1399:2: rule__Constant__Group__3__Impl rule__Constant__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Constant__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Constant__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__3"


    // $ANTLR start "rule__Constant__Group__3__Impl"
    // InternalJavali.g:1406:1: rule__Constant__Group__3__Impl : ( ( rule__Constant__IdAssignment_3 ) ) ;
    public final void rule__Constant__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1410:1: ( ( ( rule__Constant__IdAssignment_3 ) ) )
            // InternalJavali.g:1411:1: ( ( rule__Constant__IdAssignment_3 ) )
            {
            // InternalJavali.g:1411:1: ( ( rule__Constant__IdAssignment_3 ) )
            // InternalJavali.g:1412:2: ( rule__Constant__IdAssignment_3 )
            {
             before(grammarAccess.getConstantAccess().getIdAssignment_3()); 
            // InternalJavali.g:1413:2: ( rule__Constant__IdAssignment_3 )
            // InternalJavali.g:1413:3: rule__Constant__IdAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Constant__IdAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getConstantAccess().getIdAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__3__Impl"


    // $ANTLR start "rule__Constant__Group__4"
    // InternalJavali.g:1421:1: rule__Constant__Group__4 : rule__Constant__Group__4__Impl rule__Constant__Group__5 ;
    public final void rule__Constant__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1425:1: ( rule__Constant__Group__4__Impl rule__Constant__Group__5 )
            // InternalJavali.g:1426:2: rule__Constant__Group__4__Impl rule__Constant__Group__5
            {
            pushFollow(FOLLOW_7);
            rule__Constant__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Constant__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__4"


    // $ANTLR start "rule__Constant__Group__4__Impl"
    // InternalJavali.g:1433:1: rule__Constant__Group__4__Impl : ( '=' ) ;
    public final void rule__Constant__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1437:1: ( ( '=' ) )
            // InternalJavali.g:1438:1: ( '=' )
            {
            // InternalJavali.g:1438:1: ( '=' )
            // InternalJavali.g:1439:2: '='
            {
             before(grammarAccess.getConstantAccess().getEqualsSignKeyword_4()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getConstantAccess().getEqualsSignKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__4__Impl"


    // $ANTLR start "rule__Constant__Group__5"
    // InternalJavali.g:1448:1: rule__Constant__Group__5 : rule__Constant__Group__5__Impl rule__Constant__Group__6 ;
    public final void rule__Constant__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1452:1: ( rule__Constant__Group__5__Impl rule__Constant__Group__6 )
            // InternalJavali.g:1453:2: rule__Constant__Group__5__Impl rule__Constant__Group__6
            {
            pushFollow(FOLLOW_8);
            rule__Constant__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Constant__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__5"


    // $ANTLR start "rule__Constant__Group__5__Impl"
    // InternalJavali.g:1460:1: rule__Constant__Group__5__Impl : ( ( rule__Constant__ValueAssignment_5 ) ) ;
    public final void rule__Constant__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1464:1: ( ( ( rule__Constant__ValueAssignment_5 ) ) )
            // InternalJavali.g:1465:1: ( ( rule__Constant__ValueAssignment_5 ) )
            {
            // InternalJavali.g:1465:1: ( ( rule__Constant__ValueAssignment_5 ) )
            // InternalJavali.g:1466:2: ( rule__Constant__ValueAssignment_5 )
            {
             before(grammarAccess.getConstantAccess().getValueAssignment_5()); 
            // InternalJavali.g:1467:2: ( rule__Constant__ValueAssignment_5 )
            // InternalJavali.g:1467:3: rule__Constant__ValueAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__Constant__ValueAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getConstantAccess().getValueAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__5__Impl"


    // $ANTLR start "rule__Constant__Group__6"
    // InternalJavali.g:1475:1: rule__Constant__Group__6 : rule__Constant__Group__6__Impl ;
    public final void rule__Constant__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1479:1: ( rule__Constant__Group__6__Impl )
            // InternalJavali.g:1480:2: rule__Constant__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Constant__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__6"


    // $ANTLR start "rule__Constant__Group__6__Impl"
    // InternalJavali.g:1486:1: rule__Constant__Group__6__Impl : ( ';' ) ;
    public final void rule__Constant__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1490:1: ( ( ';' ) )
            // InternalJavali.g:1491:1: ( ';' )
            {
            // InternalJavali.g:1491:1: ( ';' )
            // InternalJavali.g:1492:2: ';'
            {
             before(grammarAccess.getConstantAccess().getSemicolonKeyword_6()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getConstantAccess().getSemicolonKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__Group__6__Impl"


    // $ANTLR start "rule__Record__Group__0"
    // InternalJavali.g:1502:1: rule__Record__Group__0 : rule__Record__Group__0__Impl rule__Record__Group__1 ;
    public final void rule__Record__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1506:1: ( rule__Record__Group__0__Impl rule__Record__Group__1 )
            // InternalJavali.g:1507:2: rule__Record__Group__0__Impl rule__Record__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Record__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Record__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__0"


    // $ANTLR start "rule__Record__Group__0__Impl"
    // InternalJavali.g:1514:1: rule__Record__Group__0__Impl : ( 'class' ) ;
    public final void rule__Record__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1518:1: ( ( 'class' ) )
            // InternalJavali.g:1519:1: ( 'class' )
            {
            // InternalJavali.g:1519:1: ( 'class' )
            // InternalJavali.g:1520:2: 'class'
            {
             before(grammarAccess.getRecordAccess().getClassKeyword_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getRecordAccess().getClassKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__0__Impl"


    // $ANTLR start "rule__Record__Group__1"
    // InternalJavali.g:1529:1: rule__Record__Group__1 : rule__Record__Group__1__Impl rule__Record__Group__2 ;
    public final void rule__Record__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1533:1: ( rule__Record__Group__1__Impl rule__Record__Group__2 )
            // InternalJavali.g:1534:2: rule__Record__Group__1__Impl rule__Record__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Record__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Record__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__1"


    // $ANTLR start "rule__Record__Group__1__Impl"
    // InternalJavali.g:1541:1: rule__Record__Group__1__Impl : ( ( rule__Record__IdAssignment_1 ) ) ;
    public final void rule__Record__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1545:1: ( ( ( rule__Record__IdAssignment_1 ) ) )
            // InternalJavali.g:1546:1: ( ( rule__Record__IdAssignment_1 ) )
            {
            // InternalJavali.g:1546:1: ( ( rule__Record__IdAssignment_1 ) )
            // InternalJavali.g:1547:2: ( rule__Record__IdAssignment_1 )
            {
             before(grammarAccess.getRecordAccess().getIdAssignment_1()); 
            // InternalJavali.g:1548:2: ( rule__Record__IdAssignment_1 )
            // InternalJavali.g:1548:3: rule__Record__IdAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Record__IdAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRecordAccess().getIdAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__1__Impl"


    // $ANTLR start "rule__Record__Group__2"
    // InternalJavali.g:1556:1: rule__Record__Group__2 : rule__Record__Group__2__Impl rule__Record__Group__3 ;
    public final void rule__Record__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1560:1: ( rule__Record__Group__2__Impl rule__Record__Group__3 )
            // InternalJavali.g:1561:2: rule__Record__Group__2__Impl rule__Record__Group__3
            {
            pushFollow(FOLLOW_10);
            rule__Record__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Record__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__2"


    // $ANTLR start "rule__Record__Group__2__Impl"
    // InternalJavali.g:1568:1: rule__Record__Group__2__Impl : ( '{' ) ;
    public final void rule__Record__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1572:1: ( ( '{' ) )
            // InternalJavali.g:1573:1: ( '{' )
            {
            // InternalJavali.g:1573:1: ( '{' )
            // InternalJavali.g:1574:2: '{'
            {
             before(grammarAccess.getRecordAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getRecordAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__2__Impl"


    // $ANTLR start "rule__Record__Group__3"
    // InternalJavali.g:1583:1: rule__Record__Group__3 : rule__Record__Group__3__Impl rule__Record__Group__4 ;
    public final void rule__Record__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1587:1: ( rule__Record__Group__3__Impl rule__Record__Group__4 )
            // InternalJavali.g:1588:2: rule__Record__Group__3__Impl rule__Record__Group__4
            {
            pushFollow(FOLLOW_10);
            rule__Record__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Record__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__3"


    // $ANTLR start "rule__Record__Group__3__Impl"
    // InternalJavali.g:1595:1: rule__Record__Group__3__Impl : ( ( rule__Record__Group_3__0 )* ) ;
    public final void rule__Record__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1599:1: ( ( ( rule__Record__Group_3__0 )* ) )
            // InternalJavali.g:1600:1: ( ( rule__Record__Group_3__0 )* )
            {
            // InternalJavali.g:1600:1: ( ( rule__Record__Group_3__0 )* )
            // InternalJavali.g:1601:2: ( rule__Record__Group_3__0 )*
            {
             before(grammarAccess.getRecordAccess().getGroup_3()); 
            // InternalJavali.g:1602:2: ( rule__Record__Group_3__0 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_ID) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalJavali.g:1602:3: rule__Record__Group_3__0
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__Record__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getRecordAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__3__Impl"


    // $ANTLR start "rule__Record__Group__4"
    // InternalJavali.g:1610:1: rule__Record__Group__4 : rule__Record__Group__4__Impl ;
    public final void rule__Record__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1614:1: ( rule__Record__Group__4__Impl )
            // InternalJavali.g:1615:2: rule__Record__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Record__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__4"


    // $ANTLR start "rule__Record__Group__4__Impl"
    // InternalJavali.g:1621:1: rule__Record__Group__4__Impl : ( '}' ) ;
    public final void rule__Record__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1625:1: ( ( '}' ) )
            // InternalJavali.g:1626:1: ( '}' )
            {
            // InternalJavali.g:1626:1: ( '}' )
            // InternalJavali.g:1627:2: '}'
            {
             before(grammarAccess.getRecordAccess().getRightCurlyBracketKeyword_4()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getRecordAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group__4__Impl"


    // $ANTLR start "rule__Record__Group_3__0"
    // InternalJavali.g:1637:1: rule__Record__Group_3__0 : rule__Record__Group_3__0__Impl rule__Record__Group_3__1 ;
    public final void rule__Record__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1641:1: ( rule__Record__Group_3__0__Impl rule__Record__Group_3__1 )
            // InternalJavali.g:1642:2: rule__Record__Group_3__0__Impl rule__Record__Group_3__1
            {
            pushFollow(FOLLOW_8);
            rule__Record__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Record__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group_3__0"


    // $ANTLR start "rule__Record__Group_3__0__Impl"
    // InternalJavali.g:1649:1: rule__Record__Group_3__0__Impl : ( ( rule__Record__FieldsAssignment_3_0 ) ) ;
    public final void rule__Record__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1653:1: ( ( ( rule__Record__FieldsAssignment_3_0 ) ) )
            // InternalJavali.g:1654:1: ( ( rule__Record__FieldsAssignment_3_0 ) )
            {
            // InternalJavali.g:1654:1: ( ( rule__Record__FieldsAssignment_3_0 ) )
            // InternalJavali.g:1655:2: ( rule__Record__FieldsAssignment_3_0 )
            {
             before(grammarAccess.getRecordAccess().getFieldsAssignment_3_0()); 
            // InternalJavali.g:1656:2: ( rule__Record__FieldsAssignment_3_0 )
            // InternalJavali.g:1656:3: rule__Record__FieldsAssignment_3_0
            {
            pushFollow(FOLLOW_2);
            rule__Record__FieldsAssignment_3_0();

            state._fsp--;


            }

             after(grammarAccess.getRecordAccess().getFieldsAssignment_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group_3__0__Impl"


    // $ANTLR start "rule__Record__Group_3__1"
    // InternalJavali.g:1664:1: rule__Record__Group_3__1 : rule__Record__Group_3__1__Impl ;
    public final void rule__Record__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1668:1: ( rule__Record__Group_3__1__Impl )
            // InternalJavali.g:1669:2: rule__Record__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Record__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group_3__1"


    // $ANTLR start "rule__Record__Group_3__1__Impl"
    // InternalJavali.g:1675:1: rule__Record__Group_3__1__Impl : ( ';' ) ;
    public final void rule__Record__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1679:1: ( ( ';' ) )
            // InternalJavali.g:1680:1: ( ';' )
            {
            // InternalJavali.g:1680:1: ( ';' )
            // InternalJavali.g:1681:2: ';'
            {
             before(grammarAccess.getRecordAccess().getSemicolonKeyword_3_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getRecordAccess().getSemicolonKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__Group_3__1__Impl"


    // $ANTLR start "rule__Procedure__Group__0"
    // InternalJavali.g:1691:1: rule__Procedure__Group__0 : rule__Procedure__Group__0__Impl rule__Procedure__Group__1 ;
    public final void rule__Procedure__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1695:1: ( rule__Procedure__Group__0__Impl rule__Procedure__Group__1 )
            // InternalJavali.g:1696:2: rule__Procedure__Group__0__Impl rule__Procedure__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Procedure__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__0"


    // $ANTLR start "rule__Procedure__Group__0__Impl"
    // InternalJavali.g:1703:1: rule__Procedure__Group__0__Impl : ( ( rule__Procedure__CommentAssignment_0 )? ) ;
    public final void rule__Procedure__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1707:1: ( ( ( rule__Procedure__CommentAssignment_0 )? ) )
            // InternalJavali.g:1708:1: ( ( rule__Procedure__CommentAssignment_0 )? )
            {
            // InternalJavali.g:1708:1: ( ( rule__Procedure__CommentAssignment_0 )? )
            // InternalJavali.g:1709:2: ( rule__Procedure__CommentAssignment_0 )?
            {
             before(grammarAccess.getProcedureAccess().getCommentAssignment_0()); 
            // InternalJavali.g:1710:2: ( rule__Procedure__CommentAssignment_0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_ML_COMMENT_DOC) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalJavali.g:1710:3: rule__Procedure__CommentAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Procedure__CommentAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProcedureAccess().getCommentAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__0__Impl"


    // $ANTLR start "rule__Procedure__Group__1"
    // InternalJavali.g:1718:1: rule__Procedure__Group__1 : rule__Procedure__Group__1__Impl rule__Procedure__Group__2 ;
    public final void rule__Procedure__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1722:1: ( rule__Procedure__Group__1__Impl rule__Procedure__Group__2 )
            // InternalJavali.g:1723:2: rule__Procedure__Group__1__Impl rule__Procedure__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__Procedure__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__1"


    // $ANTLR start "rule__Procedure__Group__1__Impl"
    // InternalJavali.g:1730:1: rule__Procedure__Group__1__Impl : ( ( rule__Procedure__StaticAssignment_1 )? ) ;
    public final void rule__Procedure__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1734:1: ( ( ( rule__Procedure__StaticAssignment_1 )? ) )
            // InternalJavali.g:1735:1: ( ( rule__Procedure__StaticAssignment_1 )? )
            {
            // InternalJavali.g:1735:1: ( ( rule__Procedure__StaticAssignment_1 )? )
            // InternalJavali.g:1736:2: ( rule__Procedure__StaticAssignment_1 )?
            {
             before(grammarAccess.getProcedureAccess().getStaticAssignment_1()); 
            // InternalJavali.g:1737:2: ( rule__Procedure__StaticAssignment_1 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==52) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalJavali.g:1737:3: rule__Procedure__StaticAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__Procedure__StaticAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProcedureAccess().getStaticAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__1__Impl"


    // $ANTLR start "rule__Procedure__Group__2"
    // InternalJavali.g:1745:1: rule__Procedure__Group__2 : rule__Procedure__Group__2__Impl rule__Procedure__Group__3 ;
    public final void rule__Procedure__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1749:1: ( rule__Procedure__Group__2__Impl rule__Procedure__Group__3 )
            // InternalJavali.g:1750:2: rule__Procedure__Group__2__Impl rule__Procedure__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Procedure__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__2"


    // $ANTLR start "rule__Procedure__Group__2__Impl"
    // InternalJavali.g:1757:1: rule__Procedure__Group__2__Impl : ( ( rule__Procedure__Alternatives_2 ) ) ;
    public final void rule__Procedure__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1761:1: ( ( ( rule__Procedure__Alternatives_2 ) ) )
            // InternalJavali.g:1762:1: ( ( rule__Procedure__Alternatives_2 ) )
            {
            // InternalJavali.g:1762:1: ( ( rule__Procedure__Alternatives_2 ) )
            // InternalJavali.g:1763:2: ( rule__Procedure__Alternatives_2 )
            {
             before(grammarAccess.getProcedureAccess().getAlternatives_2()); 
            // InternalJavali.g:1764:2: ( rule__Procedure__Alternatives_2 )
            // InternalJavali.g:1764:3: rule__Procedure__Alternatives_2
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getProcedureAccess().getAlternatives_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__2__Impl"


    // $ANTLR start "rule__Procedure__Group__3"
    // InternalJavali.g:1772:1: rule__Procedure__Group__3 : rule__Procedure__Group__3__Impl rule__Procedure__Group__4 ;
    public final void rule__Procedure__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1776:1: ( rule__Procedure__Group__3__Impl rule__Procedure__Group__4 )
            // InternalJavali.g:1777:2: rule__Procedure__Group__3__Impl rule__Procedure__Group__4
            {
            pushFollow(FOLLOW_13);
            rule__Procedure__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__3"


    // $ANTLR start "rule__Procedure__Group__3__Impl"
    // InternalJavali.g:1784:1: rule__Procedure__Group__3__Impl : ( ( rule__Procedure__IdAssignment_3 ) ) ;
    public final void rule__Procedure__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1788:1: ( ( ( rule__Procedure__IdAssignment_3 ) ) )
            // InternalJavali.g:1789:1: ( ( rule__Procedure__IdAssignment_3 ) )
            {
            // InternalJavali.g:1789:1: ( ( rule__Procedure__IdAssignment_3 ) )
            // InternalJavali.g:1790:2: ( rule__Procedure__IdAssignment_3 )
            {
             before(grammarAccess.getProcedureAccess().getIdAssignment_3()); 
            // InternalJavali.g:1791:2: ( rule__Procedure__IdAssignment_3 )
            // InternalJavali.g:1791:3: rule__Procedure__IdAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__IdAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getProcedureAccess().getIdAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__3__Impl"


    // $ANTLR start "rule__Procedure__Group__4"
    // InternalJavali.g:1799:1: rule__Procedure__Group__4 : rule__Procedure__Group__4__Impl rule__Procedure__Group__5 ;
    public final void rule__Procedure__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1803:1: ( rule__Procedure__Group__4__Impl rule__Procedure__Group__5 )
            // InternalJavali.g:1804:2: rule__Procedure__Group__4__Impl rule__Procedure__Group__5
            {
            pushFollow(FOLLOW_14);
            rule__Procedure__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__4"


    // $ANTLR start "rule__Procedure__Group__4__Impl"
    // InternalJavali.g:1811:1: rule__Procedure__Group__4__Impl : ( '(' ) ;
    public final void rule__Procedure__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1815:1: ( ( '(' ) )
            // InternalJavali.g:1816:1: ( '(' )
            {
            // InternalJavali.g:1816:1: ( '(' )
            // InternalJavali.g:1817:2: '('
            {
             before(grammarAccess.getProcedureAccess().getLeftParenthesisKeyword_4()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getProcedureAccess().getLeftParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__4__Impl"


    // $ANTLR start "rule__Procedure__Group__5"
    // InternalJavali.g:1826:1: rule__Procedure__Group__5 : rule__Procedure__Group__5__Impl rule__Procedure__Group__6 ;
    public final void rule__Procedure__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1830:1: ( rule__Procedure__Group__5__Impl rule__Procedure__Group__6 )
            // InternalJavali.g:1831:2: rule__Procedure__Group__5__Impl rule__Procedure__Group__6
            {
            pushFollow(FOLLOW_14);
            rule__Procedure__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__5"


    // $ANTLR start "rule__Procedure__Group__5__Impl"
    // InternalJavali.g:1838:1: rule__Procedure__Group__5__Impl : ( ( rule__Procedure__Group_5__0 )? ) ;
    public final void rule__Procedure__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1842:1: ( ( ( rule__Procedure__Group_5__0 )? ) )
            // InternalJavali.g:1843:1: ( ( rule__Procedure__Group_5__0 )? )
            {
            // InternalJavali.g:1843:1: ( ( rule__Procedure__Group_5__0 )? )
            // InternalJavali.g:1844:2: ( rule__Procedure__Group_5__0 )?
            {
             before(grammarAccess.getProcedureAccess().getGroup_5()); 
            // InternalJavali.g:1845:2: ( rule__Procedure__Group_5__0 )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalJavali.g:1845:3: rule__Procedure__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Procedure__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProcedureAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__5__Impl"


    // $ANTLR start "rule__Procedure__Group__6"
    // InternalJavali.g:1853:1: rule__Procedure__Group__6 : rule__Procedure__Group__6__Impl rule__Procedure__Group__7 ;
    public final void rule__Procedure__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1857:1: ( rule__Procedure__Group__6__Impl rule__Procedure__Group__7 )
            // InternalJavali.g:1858:2: rule__Procedure__Group__6__Impl rule__Procedure__Group__7
            {
            pushFollow(FOLLOW_9);
            rule__Procedure__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__6"


    // $ANTLR start "rule__Procedure__Group__6__Impl"
    // InternalJavali.g:1865:1: rule__Procedure__Group__6__Impl : ( ')' ) ;
    public final void rule__Procedure__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1869:1: ( ( ')' ) )
            // InternalJavali.g:1870:1: ( ')' )
            {
            // InternalJavali.g:1870:1: ( ')' )
            // InternalJavali.g:1871:2: ')'
            {
             before(grammarAccess.getProcedureAccess().getRightParenthesisKeyword_6()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getProcedureAccess().getRightParenthesisKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__6__Impl"


    // $ANTLR start "rule__Procedure__Group__7"
    // InternalJavali.g:1880:1: rule__Procedure__Group__7 : rule__Procedure__Group__7__Impl ;
    public final void rule__Procedure__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1884:1: ( rule__Procedure__Group__7__Impl )
            // InternalJavali.g:1885:2: rule__Procedure__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__7"


    // $ANTLR start "rule__Procedure__Group__7__Impl"
    // InternalJavali.g:1891:1: rule__Procedure__Group__7__Impl : ( ( rule__Procedure__BodyAssignment_7 ) ) ;
    public final void rule__Procedure__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1895:1: ( ( ( rule__Procedure__BodyAssignment_7 ) ) )
            // InternalJavali.g:1896:1: ( ( rule__Procedure__BodyAssignment_7 ) )
            {
            // InternalJavali.g:1896:1: ( ( rule__Procedure__BodyAssignment_7 ) )
            // InternalJavali.g:1897:2: ( rule__Procedure__BodyAssignment_7 )
            {
             before(grammarAccess.getProcedureAccess().getBodyAssignment_7()); 
            // InternalJavali.g:1898:2: ( rule__Procedure__BodyAssignment_7 )
            // InternalJavali.g:1898:3: rule__Procedure__BodyAssignment_7
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__BodyAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getProcedureAccess().getBodyAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group__7__Impl"


    // $ANTLR start "rule__Procedure__Group_5__0"
    // InternalJavali.g:1907:1: rule__Procedure__Group_5__0 : rule__Procedure__Group_5__0__Impl rule__Procedure__Group_5__1 ;
    public final void rule__Procedure__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1911:1: ( rule__Procedure__Group_5__0__Impl rule__Procedure__Group_5__1 )
            // InternalJavali.g:1912:2: rule__Procedure__Group_5__0__Impl rule__Procedure__Group_5__1
            {
            pushFollow(FOLLOW_15);
            rule__Procedure__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5__0"


    // $ANTLR start "rule__Procedure__Group_5__0__Impl"
    // InternalJavali.g:1919:1: rule__Procedure__Group_5__0__Impl : ( ( rule__Procedure__ParamsAssignment_5_0 ) ) ;
    public final void rule__Procedure__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1923:1: ( ( ( rule__Procedure__ParamsAssignment_5_0 ) ) )
            // InternalJavali.g:1924:1: ( ( rule__Procedure__ParamsAssignment_5_0 ) )
            {
            // InternalJavali.g:1924:1: ( ( rule__Procedure__ParamsAssignment_5_0 ) )
            // InternalJavali.g:1925:2: ( rule__Procedure__ParamsAssignment_5_0 )
            {
             before(grammarAccess.getProcedureAccess().getParamsAssignment_5_0()); 
            // InternalJavali.g:1926:2: ( rule__Procedure__ParamsAssignment_5_0 )
            // InternalJavali.g:1926:3: rule__Procedure__ParamsAssignment_5_0
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__ParamsAssignment_5_0();

            state._fsp--;


            }

             after(grammarAccess.getProcedureAccess().getParamsAssignment_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5__0__Impl"


    // $ANTLR start "rule__Procedure__Group_5__1"
    // InternalJavali.g:1934:1: rule__Procedure__Group_5__1 : rule__Procedure__Group_5__1__Impl ;
    public final void rule__Procedure__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1938:1: ( rule__Procedure__Group_5__1__Impl )
            // InternalJavali.g:1939:2: rule__Procedure__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5__1"


    // $ANTLR start "rule__Procedure__Group_5__1__Impl"
    // InternalJavali.g:1945:1: rule__Procedure__Group_5__1__Impl : ( ( rule__Procedure__Group_5_1__0 )* ) ;
    public final void rule__Procedure__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1949:1: ( ( ( rule__Procedure__Group_5_1__0 )* ) )
            // InternalJavali.g:1950:1: ( ( rule__Procedure__Group_5_1__0 )* )
            {
            // InternalJavali.g:1950:1: ( ( rule__Procedure__Group_5_1__0 )* )
            // InternalJavali.g:1951:2: ( rule__Procedure__Group_5_1__0 )*
            {
             before(grammarAccess.getProcedureAccess().getGroup_5_1()); 
            // InternalJavali.g:1952:2: ( rule__Procedure__Group_5_1__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==32) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalJavali.g:1952:3: rule__Procedure__Group_5_1__0
            	    {
            	    pushFollow(FOLLOW_16);
            	    rule__Procedure__Group_5_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getProcedureAccess().getGroup_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5__1__Impl"


    // $ANTLR start "rule__Procedure__Group_5_1__0"
    // InternalJavali.g:1961:1: rule__Procedure__Group_5_1__0 : rule__Procedure__Group_5_1__0__Impl rule__Procedure__Group_5_1__1 ;
    public final void rule__Procedure__Group_5_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1965:1: ( rule__Procedure__Group_5_1__0__Impl rule__Procedure__Group_5_1__1 )
            // InternalJavali.g:1966:2: rule__Procedure__Group_5_1__0__Impl rule__Procedure__Group_5_1__1
            {
            pushFollow(FOLLOW_5);
            rule__Procedure__Group_5_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Procedure__Group_5_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5_1__0"


    // $ANTLR start "rule__Procedure__Group_5_1__0__Impl"
    // InternalJavali.g:1973:1: rule__Procedure__Group_5_1__0__Impl : ( ',' ) ;
    public final void rule__Procedure__Group_5_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1977:1: ( ( ',' ) )
            // InternalJavali.g:1978:1: ( ',' )
            {
            // InternalJavali.g:1978:1: ( ',' )
            // InternalJavali.g:1979:2: ','
            {
             before(grammarAccess.getProcedureAccess().getCommaKeyword_5_1_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getProcedureAccess().getCommaKeyword_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5_1__0__Impl"


    // $ANTLR start "rule__Procedure__Group_5_1__1"
    // InternalJavali.g:1988:1: rule__Procedure__Group_5_1__1 : rule__Procedure__Group_5_1__1__Impl ;
    public final void rule__Procedure__Group_5_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:1992:1: ( rule__Procedure__Group_5_1__1__Impl )
            // InternalJavali.g:1993:2: rule__Procedure__Group_5_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__Group_5_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5_1__1"


    // $ANTLR start "rule__Procedure__Group_5_1__1__Impl"
    // InternalJavali.g:1999:1: rule__Procedure__Group_5_1__1__Impl : ( ( rule__Procedure__ParamsAssignment_5_1_1 ) ) ;
    public final void rule__Procedure__Group_5_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2003:1: ( ( ( rule__Procedure__ParamsAssignment_5_1_1 ) ) )
            // InternalJavali.g:2004:1: ( ( rule__Procedure__ParamsAssignment_5_1_1 ) )
            {
            // InternalJavali.g:2004:1: ( ( rule__Procedure__ParamsAssignment_5_1_1 ) )
            // InternalJavali.g:2005:2: ( rule__Procedure__ParamsAssignment_5_1_1 )
            {
             before(grammarAccess.getProcedureAccess().getParamsAssignment_5_1_1()); 
            // InternalJavali.g:2006:2: ( rule__Procedure__ParamsAssignment_5_1_1 )
            // InternalJavali.g:2006:3: rule__Procedure__ParamsAssignment_5_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Procedure__ParamsAssignment_5_1_1();

            state._fsp--;


            }

             after(grammarAccess.getProcedureAccess().getParamsAssignment_5_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__Group_5_1__1__Impl"


    // $ANTLR start "rule__Block__Group__0"
    // InternalJavali.g:2015:1: rule__Block__Group__0 : rule__Block__Group__0__Impl rule__Block__Group__1 ;
    public final void rule__Block__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2019:1: ( rule__Block__Group__0__Impl rule__Block__Group__1 )
            // InternalJavali.g:2020:2: rule__Block__Group__0__Impl rule__Block__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__Block__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Block__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__0"


    // $ANTLR start "rule__Block__Group__0__Impl"
    // InternalJavali.g:2027:1: rule__Block__Group__0__Impl : ( () ) ;
    public final void rule__Block__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2031:1: ( ( () ) )
            // InternalJavali.g:2032:1: ( () )
            {
            // InternalJavali.g:2032:1: ( () )
            // InternalJavali.g:2033:2: ()
            {
             before(grammarAccess.getBlockAccess().getBlockAction_0()); 
            // InternalJavali.g:2034:2: ()
            // InternalJavali.g:2034:3: 
            {
            }

             after(grammarAccess.getBlockAccess().getBlockAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__0__Impl"


    // $ANTLR start "rule__Block__Group__1"
    // InternalJavali.g:2042:1: rule__Block__Group__1 : rule__Block__Group__1__Impl rule__Block__Group__2 ;
    public final void rule__Block__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2046:1: ( rule__Block__Group__1__Impl rule__Block__Group__2 )
            // InternalJavali.g:2047:2: rule__Block__Group__1__Impl rule__Block__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__Block__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Block__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__1"


    // $ANTLR start "rule__Block__Group__1__Impl"
    // InternalJavali.g:2054:1: rule__Block__Group__1__Impl : ( '{' ) ;
    public final void rule__Block__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2058:1: ( ( '{' ) )
            // InternalJavali.g:2059:1: ( '{' )
            {
            // InternalJavali.g:2059:1: ( '{' )
            // InternalJavali.g:2060:2: '{'
            {
             before(grammarAccess.getBlockAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getBlockAccess().getLeftCurlyBracketKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__1__Impl"


    // $ANTLR start "rule__Block__Group__2"
    // InternalJavali.g:2069:1: rule__Block__Group__2 : rule__Block__Group__2__Impl rule__Block__Group__3 ;
    public final void rule__Block__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2073:1: ( rule__Block__Group__2__Impl rule__Block__Group__3 )
            // InternalJavali.g:2074:2: rule__Block__Group__2__Impl rule__Block__Group__3
            {
            pushFollow(FOLLOW_17);
            rule__Block__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Block__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__2"


    // $ANTLR start "rule__Block__Group__2__Impl"
    // InternalJavali.g:2081:1: rule__Block__Group__2__Impl : ( ( rule__Block__StatementsAssignment_2 )* ) ;
    public final void rule__Block__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2085:1: ( ( ( rule__Block__StatementsAssignment_2 )* ) )
            // InternalJavali.g:2086:1: ( ( rule__Block__StatementsAssignment_2 )* )
            {
            // InternalJavali.g:2086:1: ( ( rule__Block__StatementsAssignment_2 )* )
            // InternalJavali.g:2087:2: ( rule__Block__StatementsAssignment_2 )*
            {
             before(grammarAccess.getBlockAccess().getStatementsAssignment_2()); 
            // InternalJavali.g:2088:2: ( rule__Block__StatementsAssignment_2 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_ID||(LA18_0>=33 && LA18_0<=36)||(LA18_0>=38 && LA18_0<=40)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalJavali.g:2088:3: rule__Block__StatementsAssignment_2
            	    {
            	    pushFollow(FOLLOW_18);
            	    rule__Block__StatementsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getBlockAccess().getStatementsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__2__Impl"


    // $ANTLR start "rule__Block__Group__3"
    // InternalJavali.g:2096:1: rule__Block__Group__3 : rule__Block__Group__3__Impl ;
    public final void rule__Block__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2100:1: ( rule__Block__Group__3__Impl )
            // InternalJavali.g:2101:2: rule__Block__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Block__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__3"


    // $ANTLR start "rule__Block__Group__3__Impl"
    // InternalJavali.g:2107:1: rule__Block__Group__3__Impl : ( '}' ) ;
    public final void rule__Block__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2111:1: ( ( '}' ) )
            // InternalJavali.g:2112:1: ( '}' )
            {
            // InternalJavali.g:2112:1: ( '}' )
            // InternalJavali.g:2113:2: '}'
            {
             before(grammarAccess.getBlockAccess().getRightCurlyBracketKeyword_3()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getBlockAccess().getRightCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__Group__3__Impl"


    // $ANTLR start "rule__Statement__Group_0__0"
    // InternalJavali.g:2123:1: rule__Statement__Group_0__0 : rule__Statement__Group_0__0__Impl rule__Statement__Group_0__1 ;
    public final void rule__Statement__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2127:1: ( rule__Statement__Group_0__0__Impl rule__Statement__Group_0__1 )
            // InternalJavali.g:2128:2: rule__Statement__Group_0__0__Impl rule__Statement__Group_0__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_0__0"


    // $ANTLR start "rule__Statement__Group_0__0__Impl"
    // InternalJavali.g:2135:1: rule__Statement__Group_0__0__Impl : ( ruleReturn ) ;
    public final void rule__Statement__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2139:1: ( ( ruleReturn ) )
            // InternalJavali.g:2140:1: ( ruleReturn )
            {
            // InternalJavali.g:2140:1: ( ruleReturn )
            // InternalJavali.g:2141:2: ruleReturn
            {
             before(grammarAccess.getStatementAccess().getReturnParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleReturn();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getReturnParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_0__0__Impl"


    // $ANTLR start "rule__Statement__Group_0__1"
    // InternalJavali.g:2150:1: rule__Statement__Group_0__1 : rule__Statement__Group_0__1__Impl ;
    public final void rule__Statement__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2154:1: ( rule__Statement__Group_0__1__Impl )
            // InternalJavali.g:2155:2: rule__Statement__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_0__1"


    // $ANTLR start "rule__Statement__Group_0__1__Impl"
    // InternalJavali.g:2161:1: rule__Statement__Group_0__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2165:1: ( ( ';' ) )
            // InternalJavali.g:2166:1: ( ';' )
            {
            // InternalJavali.g:2166:1: ( ';' )
            // InternalJavali.g:2167:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_0_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_0__1__Impl"


    // $ANTLR start "rule__Statement__Group_1__0"
    // InternalJavali.g:2177:1: rule__Statement__Group_1__0 : rule__Statement__Group_1__0__Impl rule__Statement__Group_1__1 ;
    public final void rule__Statement__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2181:1: ( rule__Statement__Group_1__0__Impl rule__Statement__Group_1__1 )
            // InternalJavali.g:2182:2: rule__Statement__Group_1__0__Impl rule__Statement__Group_1__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_1__0"


    // $ANTLR start "rule__Statement__Group_1__0__Impl"
    // InternalJavali.g:2189:1: rule__Statement__Group_1__0__Impl : ( ruleBreak ) ;
    public final void rule__Statement__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2193:1: ( ( ruleBreak ) )
            // InternalJavali.g:2194:1: ( ruleBreak )
            {
            // InternalJavali.g:2194:1: ( ruleBreak )
            // InternalJavali.g:2195:2: ruleBreak
            {
             before(grammarAccess.getStatementAccess().getBreakParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleBreak();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getBreakParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_1__0__Impl"


    // $ANTLR start "rule__Statement__Group_1__1"
    // InternalJavali.g:2204:1: rule__Statement__Group_1__1 : rule__Statement__Group_1__1__Impl ;
    public final void rule__Statement__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2208:1: ( rule__Statement__Group_1__1__Impl )
            // InternalJavali.g:2209:2: rule__Statement__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_1__1"


    // $ANTLR start "rule__Statement__Group_1__1__Impl"
    // InternalJavali.g:2215:1: rule__Statement__Group_1__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2219:1: ( ( ';' ) )
            // InternalJavali.g:2220:1: ( ';' )
            {
            // InternalJavali.g:2220:1: ( ';' )
            // InternalJavali.g:2221:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_1_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_1__1__Impl"


    // $ANTLR start "rule__Statement__Group_2__0"
    // InternalJavali.g:2231:1: rule__Statement__Group_2__0 : rule__Statement__Group_2__0__Impl rule__Statement__Group_2__1 ;
    public final void rule__Statement__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2235:1: ( rule__Statement__Group_2__0__Impl rule__Statement__Group_2__1 )
            // InternalJavali.g:2236:2: rule__Statement__Group_2__0__Impl rule__Statement__Group_2__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_2__0"


    // $ANTLR start "rule__Statement__Group_2__0__Impl"
    // InternalJavali.g:2243:1: rule__Statement__Group_2__0__Impl : ( ruleContinue ) ;
    public final void rule__Statement__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2247:1: ( ( ruleContinue ) )
            // InternalJavali.g:2248:1: ( ruleContinue )
            {
            // InternalJavali.g:2248:1: ( ruleContinue )
            // InternalJavali.g:2249:2: ruleContinue
            {
             before(grammarAccess.getStatementAccess().getContinueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleContinue();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getContinueParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_2__0__Impl"


    // $ANTLR start "rule__Statement__Group_2__1"
    // InternalJavali.g:2258:1: rule__Statement__Group_2__1 : rule__Statement__Group_2__1__Impl ;
    public final void rule__Statement__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2262:1: ( rule__Statement__Group_2__1__Impl )
            // InternalJavali.g:2263:2: rule__Statement__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_2__1"


    // $ANTLR start "rule__Statement__Group_2__1__Impl"
    // InternalJavali.g:2269:1: rule__Statement__Group_2__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2273:1: ( ( ';' ) )
            // InternalJavali.g:2274:1: ( ';' )
            {
            // InternalJavali.g:2274:1: ( ';' )
            // InternalJavali.g:2275:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_2_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_2__1__Impl"


    // $ANTLR start "rule__Statement__Group_3__0"
    // InternalJavali.g:2285:1: rule__Statement__Group_3__0 : rule__Statement__Group_3__0__Impl rule__Statement__Group_3__1 ;
    public final void rule__Statement__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2289:1: ( rule__Statement__Group_3__0__Impl rule__Statement__Group_3__1 )
            // InternalJavali.g:2290:2: rule__Statement__Group_3__0__Impl rule__Statement__Group_3__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_3__0"


    // $ANTLR start "rule__Statement__Group_3__0__Impl"
    // InternalJavali.g:2297:1: rule__Statement__Group_3__0__Impl : ( ruleVarDeclarationAssign ) ;
    public final void rule__Statement__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2301:1: ( ( ruleVarDeclarationAssign ) )
            // InternalJavali.g:2302:1: ( ruleVarDeclarationAssign )
            {
            // InternalJavali.g:2302:1: ( ruleVarDeclarationAssign )
            // InternalJavali.g:2303:2: ruleVarDeclarationAssign
            {
             before(grammarAccess.getStatementAccess().getVarDeclarationAssignParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleVarDeclarationAssign();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getVarDeclarationAssignParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_3__0__Impl"


    // $ANTLR start "rule__Statement__Group_3__1"
    // InternalJavali.g:2312:1: rule__Statement__Group_3__1 : rule__Statement__Group_3__1__Impl ;
    public final void rule__Statement__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2316:1: ( rule__Statement__Group_3__1__Impl )
            // InternalJavali.g:2317:2: rule__Statement__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_3__1"


    // $ANTLR start "rule__Statement__Group_3__1__Impl"
    // InternalJavali.g:2323:1: rule__Statement__Group_3__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2327:1: ( ( ';' ) )
            // InternalJavali.g:2328:1: ( ';' )
            {
            // InternalJavali.g:2328:1: ( ';' )
            // InternalJavali.g:2329:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_3_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_3__1__Impl"


    // $ANTLR start "rule__Statement__Group_4__0"
    // InternalJavali.g:2339:1: rule__Statement__Group_4__0 : rule__Statement__Group_4__0__Impl rule__Statement__Group_4__1 ;
    public final void rule__Statement__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2343:1: ( rule__Statement__Group_4__0__Impl rule__Statement__Group_4__1 )
            // InternalJavali.g:2344:2: rule__Statement__Group_4__0__Impl rule__Statement__Group_4__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_4__0"


    // $ANTLR start "rule__Statement__Group_4__0__Impl"
    // InternalJavali.g:2351:1: rule__Statement__Group_4__0__Impl : ( ruleVarAssign ) ;
    public final void rule__Statement__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2355:1: ( ( ruleVarAssign ) )
            // InternalJavali.g:2356:1: ( ruleVarAssign )
            {
            // InternalJavali.g:2356:1: ( ruleVarAssign )
            // InternalJavali.g:2357:2: ruleVarAssign
            {
             before(grammarAccess.getStatementAccess().getVarAssignParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleVarAssign();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getVarAssignParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_4__0__Impl"


    // $ANTLR start "rule__Statement__Group_4__1"
    // InternalJavali.g:2366:1: rule__Statement__Group_4__1 : rule__Statement__Group_4__1__Impl ;
    public final void rule__Statement__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2370:1: ( rule__Statement__Group_4__1__Impl )
            // InternalJavali.g:2371:2: rule__Statement__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_4__1"


    // $ANTLR start "rule__Statement__Group_4__1__Impl"
    // InternalJavali.g:2377:1: rule__Statement__Group_4__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2381:1: ( ( ';' ) )
            // InternalJavali.g:2382:1: ( ';' )
            {
            // InternalJavali.g:2382:1: ( ';' )
            // InternalJavali.g:2383:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_4_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_4__1__Impl"


    // $ANTLR start "rule__Statement__Group_5__0"
    // InternalJavali.g:2393:1: rule__Statement__Group_5__0 : rule__Statement__Group_5__0__Impl rule__Statement__Group_5__1 ;
    public final void rule__Statement__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2397:1: ( rule__Statement__Group_5__0__Impl rule__Statement__Group_5__1 )
            // InternalJavali.g:2398:2: rule__Statement__Group_5__0__Impl rule__Statement__Group_5__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_5__0"


    // $ANTLR start "rule__Statement__Group_5__0__Impl"
    // InternalJavali.g:2405:1: rule__Statement__Group_5__0__Impl : ( ruleIncrement ) ;
    public final void rule__Statement__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2409:1: ( ( ruleIncrement ) )
            // InternalJavali.g:2410:1: ( ruleIncrement )
            {
            // InternalJavali.g:2410:1: ( ruleIncrement )
            // InternalJavali.g:2411:2: ruleIncrement
            {
             before(grammarAccess.getStatementAccess().getIncrementParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleIncrement();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getIncrementParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_5__0__Impl"


    // $ANTLR start "rule__Statement__Group_5__1"
    // InternalJavali.g:2420:1: rule__Statement__Group_5__1 : rule__Statement__Group_5__1__Impl ;
    public final void rule__Statement__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2424:1: ( rule__Statement__Group_5__1__Impl )
            // InternalJavali.g:2425:2: rule__Statement__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_5__1"


    // $ANTLR start "rule__Statement__Group_5__1__Impl"
    // InternalJavali.g:2431:1: rule__Statement__Group_5__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2435:1: ( ( ';' ) )
            // InternalJavali.g:2436:1: ( ';' )
            {
            // InternalJavali.g:2436:1: ( ';' )
            // InternalJavali.g:2437:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_5_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_5__1__Impl"


    // $ANTLR start "rule__Statement__Group_6__0"
    // InternalJavali.g:2447:1: rule__Statement__Group_6__0 : rule__Statement__Group_6__0__Impl rule__Statement__Group_6__1 ;
    public final void rule__Statement__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2451:1: ( rule__Statement__Group_6__0__Impl rule__Statement__Group_6__1 )
            // InternalJavali.g:2452:2: rule__Statement__Group_6__0__Impl rule__Statement__Group_6__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_6__0"


    // $ANTLR start "rule__Statement__Group_6__0__Impl"
    // InternalJavali.g:2459:1: rule__Statement__Group_6__0__Impl : ( ruleDecrement ) ;
    public final void rule__Statement__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2463:1: ( ( ruleDecrement ) )
            // InternalJavali.g:2464:1: ( ruleDecrement )
            {
            // InternalJavali.g:2464:1: ( ruleDecrement )
            // InternalJavali.g:2465:2: ruleDecrement
            {
             before(grammarAccess.getStatementAccess().getDecrementParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleDecrement();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getDecrementParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_6__0__Impl"


    // $ANTLR start "rule__Statement__Group_6__1"
    // InternalJavali.g:2474:1: rule__Statement__Group_6__1 : rule__Statement__Group_6__1__Impl ;
    public final void rule__Statement__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2478:1: ( rule__Statement__Group_6__1__Impl )
            // InternalJavali.g:2479:2: rule__Statement__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_6__1"


    // $ANTLR start "rule__Statement__Group_6__1__Impl"
    // InternalJavali.g:2485:1: rule__Statement__Group_6__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2489:1: ( ( ';' ) )
            // InternalJavali.g:2490:1: ( ';' )
            {
            // InternalJavali.g:2490:1: ( ';' )
            // InternalJavali.g:2491:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_6_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_6__1__Impl"


    // $ANTLR start "rule__Statement__Group_7__0"
    // InternalJavali.g:2501:1: rule__Statement__Group_7__0 : rule__Statement__Group_7__0__Impl rule__Statement__Group_7__1 ;
    public final void rule__Statement__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2505:1: ( rule__Statement__Group_7__0__Impl rule__Statement__Group_7__1 )
            // InternalJavali.g:2506:2: rule__Statement__Group_7__0__Impl rule__Statement__Group_7__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_7__0"


    // $ANTLR start "rule__Statement__Group_7__0__Impl"
    // InternalJavali.g:2513:1: rule__Statement__Group_7__0__Impl : ( ruleProcCall ) ;
    public final void rule__Statement__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2517:1: ( ( ruleProcCall ) )
            // InternalJavali.g:2518:1: ( ruleProcCall )
            {
            // InternalJavali.g:2518:1: ( ruleProcCall )
            // InternalJavali.g:2519:2: ruleProcCall
            {
             before(grammarAccess.getStatementAccess().getProcCallParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleProcCall();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getProcCallParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_7__0__Impl"


    // $ANTLR start "rule__Statement__Group_7__1"
    // InternalJavali.g:2528:1: rule__Statement__Group_7__1 : rule__Statement__Group_7__1__Impl ;
    public final void rule__Statement__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2532:1: ( rule__Statement__Group_7__1__Impl )
            // InternalJavali.g:2533:2: rule__Statement__Group_7__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_7__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_7__1"


    // $ANTLR start "rule__Statement__Group_7__1__Impl"
    // InternalJavali.g:2539:1: rule__Statement__Group_7__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2543:1: ( ( ';' ) )
            // InternalJavali.g:2544:1: ( ';' )
            {
            // InternalJavali.g:2544:1: ( ';' )
            // InternalJavali.g:2545:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_7_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_7__1__Impl"


    // $ANTLR start "rule__Statement__Group_11__0"
    // InternalJavali.g:2555:1: rule__Statement__Group_11__0 : rule__Statement__Group_11__0__Impl rule__Statement__Group_11__1 ;
    public final void rule__Statement__Group_11__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2559:1: ( rule__Statement__Group_11__0__Impl rule__Statement__Group_11__1 )
            // InternalJavali.g:2560:2: rule__Statement__Group_11__0__Impl rule__Statement__Group_11__1
            {
            pushFollow(FOLLOW_8);
            rule__Statement__Group_11__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Statement__Group_11__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_11__0"


    // $ANTLR start "rule__Statement__Group_11__0__Impl"
    // InternalJavali.g:2567:1: rule__Statement__Group_11__0__Impl : ( ruleDoWhile ) ;
    public final void rule__Statement__Group_11__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2571:1: ( ( ruleDoWhile ) )
            // InternalJavali.g:2572:1: ( ruleDoWhile )
            {
            // InternalJavali.g:2572:1: ( ruleDoWhile )
            // InternalJavali.g:2573:2: ruleDoWhile
            {
             before(grammarAccess.getStatementAccess().getDoWhileParserRuleCall_11_0()); 
            pushFollow(FOLLOW_2);
            ruleDoWhile();

            state._fsp--;

             after(grammarAccess.getStatementAccess().getDoWhileParserRuleCall_11_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_11__0__Impl"


    // $ANTLR start "rule__Statement__Group_11__1"
    // InternalJavali.g:2582:1: rule__Statement__Group_11__1 : rule__Statement__Group_11__1__Impl ;
    public final void rule__Statement__Group_11__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2586:1: ( rule__Statement__Group_11__1__Impl )
            // InternalJavali.g:2587:2: rule__Statement__Group_11__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Statement__Group_11__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_11__1"


    // $ANTLR start "rule__Statement__Group_11__1__Impl"
    // InternalJavali.g:2593:1: rule__Statement__Group_11__1__Impl : ( ';' ) ;
    public final void rule__Statement__Group_11__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2597:1: ( ( ';' ) )
            // InternalJavali.g:2598:1: ( ';' )
            {
            // InternalJavali.g:2598:1: ( ';' )
            // InternalJavali.g:2599:2: ';'
            {
             before(grammarAccess.getStatementAccess().getSemicolonKeyword_11_1()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getStatementAccess().getSemicolonKeyword_11_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Statement__Group_11__1__Impl"


    // $ANTLR start "rule__Return__Group__0"
    // InternalJavali.g:2609:1: rule__Return__Group__0 : rule__Return__Group__0__Impl rule__Return__Group__1 ;
    public final void rule__Return__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2613:1: ( rule__Return__Group__0__Impl rule__Return__Group__1 )
            // InternalJavali.g:2614:2: rule__Return__Group__0__Impl rule__Return__Group__1
            {
            pushFollow(FOLLOW_19);
            rule__Return__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Return__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Return__Group__0"


    // $ANTLR start "rule__Return__Group__0__Impl"
    // InternalJavali.g:2621:1: rule__Return__Group__0__Impl : ( () ) ;
    public final void rule__Return__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2625:1: ( ( () ) )
            // InternalJavali.g:2626:1: ( () )
            {
            // InternalJavali.g:2626:1: ( () )
            // InternalJavali.g:2627:2: ()
            {
             before(grammarAccess.getReturnAccess().getReturnAction_0()); 
            // InternalJavali.g:2628:2: ()
            // InternalJavali.g:2628:3: 
            {
            }

             after(grammarAccess.getReturnAccess().getReturnAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Return__Group__0__Impl"


    // $ANTLR start "rule__Return__Group__1"
    // InternalJavali.g:2636:1: rule__Return__Group__1 : rule__Return__Group__1__Impl rule__Return__Group__2 ;
    public final void rule__Return__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2640:1: ( rule__Return__Group__1__Impl rule__Return__Group__2 )
            // InternalJavali.g:2641:2: rule__Return__Group__1__Impl rule__Return__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__Return__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Return__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Return__Group__1"


    // $ANTLR start "rule__Return__Group__1__Impl"
    // InternalJavali.g:2648:1: rule__Return__Group__1__Impl : ( 'return' ) ;
    public final void rule__Return__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2652:1: ( ( 'return' ) )
            // InternalJavali.g:2653:1: ( 'return' )
            {
            // InternalJavali.g:2653:1: ( 'return' )
            // InternalJavali.g:2654:2: 'return'
            {
             before(grammarAccess.getReturnAccess().getReturnKeyword_1()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getReturnAccess().getReturnKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Return__Group__1__Impl"


    // $ANTLR start "rule__Return__Group__2"
    // InternalJavali.g:2663:1: rule__Return__Group__2 : rule__Return__Group__2__Impl ;
    public final void rule__Return__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2667:1: ( rule__Return__Group__2__Impl )
            // InternalJavali.g:2668:2: rule__Return__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Return__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Return__Group__2"


    // $ANTLR start "rule__Return__Group__2__Impl"
    // InternalJavali.g:2674:1: rule__Return__Group__2__Impl : ( ( rule__Return__ExpAssignment_2 )? ) ;
    public final void rule__Return__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2678:1: ( ( ( rule__Return__ExpAssignment_2 )? ) )
            // InternalJavali.g:2679:1: ( ( rule__Return__ExpAssignment_2 )? )
            {
            // InternalJavali.g:2679:1: ( ( rule__Return__ExpAssignment_2 )? )
            // InternalJavali.g:2680:2: ( rule__Return__ExpAssignment_2 )?
            {
             before(grammarAccess.getReturnAccess().getExpAssignment_2()); 
            // InternalJavali.g:2681:2: ( rule__Return__ExpAssignment_2 )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_PRIMITIVE_VALUE && LA19_0<=RULE_ID)||LA19_0==30||(LA19_0>=46 && LA19_0<=47)||LA19_0==51) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalJavali.g:2681:3: rule__Return__ExpAssignment_2
                    {
                    pushFollow(FOLLOW_2);
                    rule__Return__ExpAssignment_2();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getReturnAccess().getExpAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Return__Group__2__Impl"


    // $ANTLR start "rule__Break__Group__0"
    // InternalJavali.g:2690:1: rule__Break__Group__0 : rule__Break__Group__0__Impl rule__Break__Group__1 ;
    public final void rule__Break__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2694:1: ( rule__Break__Group__0__Impl rule__Break__Group__1 )
            // InternalJavali.g:2695:2: rule__Break__Group__0__Impl rule__Break__Group__1
            {
            pushFollow(FOLLOW_21);
            rule__Break__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Break__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Break__Group__0"


    // $ANTLR start "rule__Break__Group__0__Impl"
    // InternalJavali.g:2702:1: rule__Break__Group__0__Impl : ( () ) ;
    public final void rule__Break__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2706:1: ( ( () ) )
            // InternalJavali.g:2707:1: ( () )
            {
            // InternalJavali.g:2707:1: ( () )
            // InternalJavali.g:2708:2: ()
            {
             before(grammarAccess.getBreakAccess().getBreakAction_0()); 
            // InternalJavali.g:2709:2: ()
            // InternalJavali.g:2709:3: 
            {
            }

             after(grammarAccess.getBreakAccess().getBreakAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Break__Group__0__Impl"


    // $ANTLR start "rule__Break__Group__1"
    // InternalJavali.g:2717:1: rule__Break__Group__1 : rule__Break__Group__1__Impl ;
    public final void rule__Break__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2721:1: ( rule__Break__Group__1__Impl )
            // InternalJavali.g:2722:2: rule__Break__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Break__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Break__Group__1"


    // $ANTLR start "rule__Break__Group__1__Impl"
    // InternalJavali.g:2728:1: rule__Break__Group__1__Impl : ( 'break' ) ;
    public final void rule__Break__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2732:1: ( ( 'break' ) )
            // InternalJavali.g:2733:1: ( 'break' )
            {
            // InternalJavali.g:2733:1: ( 'break' )
            // InternalJavali.g:2734:2: 'break'
            {
             before(grammarAccess.getBreakAccess().getBreakKeyword_1()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getBreakAccess().getBreakKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Break__Group__1__Impl"


    // $ANTLR start "rule__Continue__Group__0"
    // InternalJavali.g:2744:1: rule__Continue__Group__0 : rule__Continue__Group__0__Impl rule__Continue__Group__1 ;
    public final void rule__Continue__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2748:1: ( rule__Continue__Group__0__Impl rule__Continue__Group__1 )
            // InternalJavali.g:2749:2: rule__Continue__Group__0__Impl rule__Continue__Group__1
            {
            pushFollow(FOLLOW_22);
            rule__Continue__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Continue__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Continue__Group__0"


    // $ANTLR start "rule__Continue__Group__0__Impl"
    // InternalJavali.g:2756:1: rule__Continue__Group__0__Impl : ( () ) ;
    public final void rule__Continue__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2760:1: ( ( () ) )
            // InternalJavali.g:2761:1: ( () )
            {
            // InternalJavali.g:2761:1: ( () )
            // InternalJavali.g:2762:2: ()
            {
             before(grammarAccess.getContinueAccess().getContinueAction_0()); 
            // InternalJavali.g:2763:2: ()
            // InternalJavali.g:2763:3: 
            {
            }

             after(grammarAccess.getContinueAccess().getContinueAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Continue__Group__0__Impl"


    // $ANTLR start "rule__Continue__Group__1"
    // InternalJavali.g:2771:1: rule__Continue__Group__1 : rule__Continue__Group__1__Impl ;
    public final void rule__Continue__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2775:1: ( rule__Continue__Group__1__Impl )
            // InternalJavali.g:2776:2: rule__Continue__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Continue__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Continue__Group__1"


    // $ANTLR start "rule__Continue__Group__1__Impl"
    // InternalJavali.g:2782:1: rule__Continue__Group__1__Impl : ( 'continue' ) ;
    public final void rule__Continue__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2786:1: ( ( 'continue' ) )
            // InternalJavali.g:2787:1: ( 'continue' )
            {
            // InternalJavali.g:2787:1: ( 'continue' )
            // InternalJavali.g:2788:2: 'continue'
            {
             before(grammarAccess.getContinueAccess().getContinueKeyword_1()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getContinueAccess().getContinueKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Continue__Group__1__Impl"


    // $ANTLR start "rule__VarDeclaration__Group__0"
    // InternalJavali.g:2798:1: rule__VarDeclaration__Group__0 : rule__VarDeclaration__Group__0__Impl rule__VarDeclaration__Group__1 ;
    public final void rule__VarDeclaration__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2802:1: ( rule__VarDeclaration__Group__0__Impl rule__VarDeclaration__Group__1 )
            // InternalJavali.g:2803:2: rule__VarDeclaration__Group__0__Impl rule__VarDeclaration__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__VarDeclaration__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarDeclaration__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclaration__Group__0"


    // $ANTLR start "rule__VarDeclaration__Group__0__Impl"
    // InternalJavali.g:2810:1: rule__VarDeclaration__Group__0__Impl : ( ( rule__VarDeclaration__TypeAssignment_0 ) ) ;
    public final void rule__VarDeclaration__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2814:1: ( ( ( rule__VarDeclaration__TypeAssignment_0 ) ) )
            // InternalJavali.g:2815:1: ( ( rule__VarDeclaration__TypeAssignment_0 ) )
            {
            // InternalJavali.g:2815:1: ( ( rule__VarDeclaration__TypeAssignment_0 ) )
            // InternalJavali.g:2816:2: ( rule__VarDeclaration__TypeAssignment_0 )
            {
             before(grammarAccess.getVarDeclarationAccess().getTypeAssignment_0()); 
            // InternalJavali.g:2817:2: ( rule__VarDeclaration__TypeAssignment_0 )
            // InternalJavali.g:2817:3: rule__VarDeclaration__TypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclaration__TypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getVarDeclarationAccess().getTypeAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclaration__Group__0__Impl"


    // $ANTLR start "rule__VarDeclaration__Group__1"
    // InternalJavali.g:2825:1: rule__VarDeclaration__Group__1 : rule__VarDeclaration__Group__1__Impl ;
    public final void rule__VarDeclaration__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2829:1: ( rule__VarDeclaration__Group__1__Impl )
            // InternalJavali.g:2830:2: rule__VarDeclaration__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclaration__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclaration__Group__1"


    // $ANTLR start "rule__VarDeclaration__Group__1__Impl"
    // InternalJavali.g:2836:1: rule__VarDeclaration__Group__1__Impl : ( ( rule__VarDeclaration__IdAssignment_1 ) ) ;
    public final void rule__VarDeclaration__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2840:1: ( ( ( rule__VarDeclaration__IdAssignment_1 ) ) )
            // InternalJavali.g:2841:1: ( ( rule__VarDeclaration__IdAssignment_1 ) )
            {
            // InternalJavali.g:2841:1: ( ( rule__VarDeclaration__IdAssignment_1 ) )
            // InternalJavali.g:2842:2: ( rule__VarDeclaration__IdAssignment_1 )
            {
             before(grammarAccess.getVarDeclarationAccess().getIdAssignment_1()); 
            // InternalJavali.g:2843:2: ( rule__VarDeclaration__IdAssignment_1 )
            // InternalJavali.g:2843:3: rule__VarDeclaration__IdAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclaration__IdAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVarDeclarationAccess().getIdAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclaration__Group__1__Impl"


    // $ANTLR start "rule__VarDeclarationAssign__Group__0"
    // InternalJavali.g:2852:1: rule__VarDeclarationAssign__Group__0 : rule__VarDeclarationAssign__Group__0__Impl rule__VarDeclarationAssign__Group__1 ;
    public final void rule__VarDeclarationAssign__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2856:1: ( rule__VarDeclarationAssign__Group__0__Impl rule__VarDeclarationAssign__Group__1 )
            // InternalJavali.g:2857:2: rule__VarDeclarationAssign__Group__0__Impl rule__VarDeclarationAssign__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__VarDeclarationAssign__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group__0"


    // $ANTLR start "rule__VarDeclarationAssign__Group__0__Impl"
    // InternalJavali.g:2864:1: rule__VarDeclarationAssign__Group__0__Impl : ( ( rule__VarDeclarationAssign__TypeAssignment_0 ) ) ;
    public final void rule__VarDeclarationAssign__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2868:1: ( ( ( rule__VarDeclarationAssign__TypeAssignment_0 ) ) )
            // InternalJavali.g:2869:1: ( ( rule__VarDeclarationAssign__TypeAssignment_0 ) )
            {
            // InternalJavali.g:2869:1: ( ( rule__VarDeclarationAssign__TypeAssignment_0 ) )
            // InternalJavali.g:2870:2: ( rule__VarDeclarationAssign__TypeAssignment_0 )
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getTypeAssignment_0()); 
            // InternalJavali.g:2871:2: ( rule__VarDeclarationAssign__TypeAssignment_0 )
            // InternalJavali.g:2871:3: rule__VarDeclarationAssign__TypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__TypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getVarDeclarationAssignAccess().getTypeAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group__0__Impl"


    // $ANTLR start "rule__VarDeclarationAssign__Group__1"
    // InternalJavali.g:2879:1: rule__VarDeclarationAssign__Group__1 : rule__VarDeclarationAssign__Group__1__Impl rule__VarDeclarationAssign__Group__2 ;
    public final void rule__VarDeclarationAssign__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2883:1: ( rule__VarDeclarationAssign__Group__1__Impl rule__VarDeclarationAssign__Group__2 )
            // InternalJavali.g:2884:2: rule__VarDeclarationAssign__Group__1__Impl rule__VarDeclarationAssign__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__VarDeclarationAssign__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group__1"


    // $ANTLR start "rule__VarDeclarationAssign__Group__1__Impl"
    // InternalJavali.g:2891:1: rule__VarDeclarationAssign__Group__1__Impl : ( ( rule__VarDeclarationAssign__IdAssignment_1 ) ) ;
    public final void rule__VarDeclarationAssign__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2895:1: ( ( ( rule__VarDeclarationAssign__IdAssignment_1 ) ) )
            // InternalJavali.g:2896:1: ( ( rule__VarDeclarationAssign__IdAssignment_1 ) )
            {
            // InternalJavali.g:2896:1: ( ( rule__VarDeclarationAssign__IdAssignment_1 ) )
            // InternalJavali.g:2897:2: ( rule__VarDeclarationAssign__IdAssignment_1 )
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getIdAssignment_1()); 
            // InternalJavali.g:2898:2: ( rule__VarDeclarationAssign__IdAssignment_1 )
            // InternalJavali.g:2898:3: rule__VarDeclarationAssign__IdAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__IdAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getVarDeclarationAssignAccess().getIdAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group__1__Impl"


    // $ANTLR start "rule__VarDeclarationAssign__Group__2"
    // InternalJavali.g:2906:1: rule__VarDeclarationAssign__Group__2 : rule__VarDeclarationAssign__Group__2__Impl ;
    public final void rule__VarDeclarationAssign__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2910:1: ( rule__VarDeclarationAssign__Group__2__Impl )
            // InternalJavali.g:2911:2: rule__VarDeclarationAssign__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group__2"


    // $ANTLR start "rule__VarDeclarationAssign__Group__2__Impl"
    // InternalJavali.g:2917:1: rule__VarDeclarationAssign__Group__2__Impl : ( ( rule__VarDeclarationAssign__Group_2__0 )? ) ;
    public final void rule__VarDeclarationAssign__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2921:1: ( ( ( rule__VarDeclarationAssign__Group_2__0 )? ) )
            // InternalJavali.g:2922:1: ( ( rule__VarDeclarationAssign__Group_2__0 )? )
            {
            // InternalJavali.g:2922:1: ( ( rule__VarDeclarationAssign__Group_2__0 )? )
            // InternalJavali.g:2923:2: ( rule__VarDeclarationAssign__Group_2__0 )?
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getGroup_2()); 
            // InternalJavali.g:2924:2: ( rule__VarDeclarationAssign__Group_2__0 )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==25) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalJavali.g:2924:3: rule__VarDeclarationAssign__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__VarDeclarationAssign__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getVarDeclarationAssignAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group__2__Impl"


    // $ANTLR start "rule__VarDeclarationAssign__Group_2__0"
    // InternalJavali.g:2933:1: rule__VarDeclarationAssign__Group_2__0 : rule__VarDeclarationAssign__Group_2__0__Impl rule__VarDeclarationAssign__Group_2__1 ;
    public final void rule__VarDeclarationAssign__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2937:1: ( rule__VarDeclarationAssign__Group_2__0__Impl rule__VarDeclarationAssign__Group_2__1 )
            // InternalJavali.g:2938:2: rule__VarDeclarationAssign__Group_2__0__Impl rule__VarDeclarationAssign__Group_2__1
            {
            pushFollow(FOLLOW_20);
            rule__VarDeclarationAssign__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group_2__0"


    // $ANTLR start "rule__VarDeclarationAssign__Group_2__0__Impl"
    // InternalJavali.g:2945:1: rule__VarDeclarationAssign__Group_2__0__Impl : ( '=' ) ;
    public final void rule__VarDeclarationAssign__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2949:1: ( ( '=' ) )
            // InternalJavali.g:2950:1: ( '=' )
            {
            // InternalJavali.g:2950:1: ( '=' )
            // InternalJavali.g:2951:2: '='
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getEqualsSignKeyword_2_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getVarDeclarationAssignAccess().getEqualsSignKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group_2__0__Impl"


    // $ANTLR start "rule__VarDeclarationAssign__Group_2__1"
    // InternalJavali.g:2960:1: rule__VarDeclarationAssign__Group_2__1 : rule__VarDeclarationAssign__Group_2__1__Impl ;
    public final void rule__VarDeclarationAssign__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2964:1: ( rule__VarDeclarationAssign__Group_2__1__Impl )
            // InternalJavali.g:2965:2: rule__VarDeclarationAssign__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group_2__1"


    // $ANTLR start "rule__VarDeclarationAssign__Group_2__1__Impl"
    // InternalJavali.g:2971:1: rule__VarDeclarationAssign__Group_2__1__Impl : ( ( rule__VarDeclarationAssign__InitAssignment_2_1 ) ) ;
    public final void rule__VarDeclarationAssign__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2975:1: ( ( ( rule__VarDeclarationAssign__InitAssignment_2_1 ) ) )
            // InternalJavali.g:2976:1: ( ( rule__VarDeclarationAssign__InitAssignment_2_1 ) )
            {
            // InternalJavali.g:2976:1: ( ( rule__VarDeclarationAssign__InitAssignment_2_1 ) )
            // InternalJavali.g:2977:2: ( rule__VarDeclarationAssign__InitAssignment_2_1 )
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getInitAssignment_2_1()); 
            // InternalJavali.g:2978:2: ( rule__VarDeclarationAssign__InitAssignment_2_1 )
            // InternalJavali.g:2978:3: rule__VarDeclarationAssign__InitAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__VarDeclarationAssign__InitAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVarDeclarationAssignAccess().getInitAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__Group_2__1__Impl"


    // $ANTLR start "rule__VarAssign__Group__0"
    // InternalJavali.g:2987:1: rule__VarAssign__Group__0 : rule__VarAssign__Group__0__Impl rule__VarAssign__Group__1 ;
    public final void rule__VarAssign__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:2991:1: ( rule__VarAssign__Group__0__Impl rule__VarAssign__Group__1 )
            // InternalJavali.g:2992:2: rule__VarAssign__Group__0__Impl rule__VarAssign__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__VarAssign__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarAssign__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__Group__0"


    // $ANTLR start "rule__VarAssign__Group__0__Impl"
    // InternalJavali.g:2999:1: rule__VarAssign__Group__0__Impl : ( ( rule__VarAssign__VarAssignment_0 ) ) ;
    public final void rule__VarAssign__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3003:1: ( ( ( rule__VarAssign__VarAssignment_0 ) ) )
            // InternalJavali.g:3004:1: ( ( rule__VarAssign__VarAssignment_0 ) )
            {
            // InternalJavali.g:3004:1: ( ( rule__VarAssign__VarAssignment_0 ) )
            // InternalJavali.g:3005:2: ( rule__VarAssign__VarAssignment_0 )
            {
             before(grammarAccess.getVarAssignAccess().getVarAssignment_0()); 
            // InternalJavali.g:3006:2: ( rule__VarAssign__VarAssignment_0 )
            // InternalJavali.g:3006:3: rule__VarAssign__VarAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__VarAssign__VarAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getVarAssignAccess().getVarAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__Group__0__Impl"


    // $ANTLR start "rule__VarAssign__Group__1"
    // InternalJavali.g:3014:1: rule__VarAssign__Group__1 : rule__VarAssign__Group__1__Impl rule__VarAssign__Group__2 ;
    public final void rule__VarAssign__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3018:1: ( rule__VarAssign__Group__1__Impl rule__VarAssign__Group__2 )
            // InternalJavali.g:3019:2: rule__VarAssign__Group__1__Impl rule__VarAssign__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__VarAssign__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarAssign__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__Group__1"


    // $ANTLR start "rule__VarAssign__Group__1__Impl"
    // InternalJavali.g:3026:1: rule__VarAssign__Group__1__Impl : ( '=' ) ;
    public final void rule__VarAssign__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3030:1: ( ( '=' ) )
            // InternalJavali.g:3031:1: ( '=' )
            {
            // InternalJavali.g:3031:1: ( '=' )
            // InternalJavali.g:3032:2: '='
            {
             before(grammarAccess.getVarAssignAccess().getEqualsSignKeyword_1()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getVarAssignAccess().getEqualsSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__Group__1__Impl"


    // $ANTLR start "rule__VarAssign__Group__2"
    // InternalJavali.g:3041:1: rule__VarAssign__Group__2 : rule__VarAssign__Group__2__Impl ;
    public final void rule__VarAssign__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3045:1: ( rule__VarAssign__Group__2__Impl )
            // InternalJavali.g:3046:2: rule__VarAssign__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarAssign__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__Group__2"


    // $ANTLR start "rule__VarAssign__Group__2__Impl"
    // InternalJavali.g:3052:1: rule__VarAssign__Group__2__Impl : ( ( rule__VarAssign__ExpAssignment_2 ) ) ;
    public final void rule__VarAssign__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3056:1: ( ( ( rule__VarAssign__ExpAssignment_2 ) ) )
            // InternalJavali.g:3057:1: ( ( rule__VarAssign__ExpAssignment_2 ) )
            {
            // InternalJavali.g:3057:1: ( ( rule__VarAssign__ExpAssignment_2 ) )
            // InternalJavali.g:3058:2: ( rule__VarAssign__ExpAssignment_2 )
            {
             before(grammarAccess.getVarAssignAccess().getExpAssignment_2()); 
            // InternalJavali.g:3059:2: ( rule__VarAssign__ExpAssignment_2 )
            // InternalJavali.g:3059:3: rule__VarAssign__ExpAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__VarAssign__ExpAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getVarAssignAccess().getExpAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__Group__2__Impl"


    // $ANTLR start "rule__IfElse__Group__0"
    // InternalJavali.g:3068:1: rule__IfElse__Group__0 : rule__IfElse__Group__0__Impl rule__IfElse__Group__1 ;
    public final void rule__IfElse__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3072:1: ( rule__IfElse__Group__0__Impl rule__IfElse__Group__1 )
            // InternalJavali.g:3073:2: rule__IfElse__Group__0__Impl rule__IfElse__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__IfElse__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IfElse__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__0"


    // $ANTLR start "rule__IfElse__Group__0__Impl"
    // InternalJavali.g:3080:1: rule__IfElse__Group__0__Impl : ( 'if' ) ;
    public final void rule__IfElse__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3084:1: ( ( 'if' ) )
            // InternalJavali.g:3085:1: ( 'if' )
            {
            // InternalJavali.g:3085:1: ( 'if' )
            // InternalJavali.g:3086:2: 'if'
            {
             before(grammarAccess.getIfElseAccess().getIfKeyword_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getIfElseAccess().getIfKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__0__Impl"


    // $ANTLR start "rule__IfElse__Group__1"
    // InternalJavali.g:3095:1: rule__IfElse__Group__1 : rule__IfElse__Group__1__Impl rule__IfElse__Group__2 ;
    public final void rule__IfElse__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3099:1: ( rule__IfElse__Group__1__Impl rule__IfElse__Group__2 )
            // InternalJavali.g:3100:2: rule__IfElse__Group__1__Impl rule__IfElse__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__IfElse__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IfElse__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__1"


    // $ANTLR start "rule__IfElse__Group__1__Impl"
    // InternalJavali.g:3107:1: rule__IfElse__Group__1__Impl : ( '(' ) ;
    public final void rule__IfElse__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3111:1: ( ( '(' ) )
            // InternalJavali.g:3112:1: ( '(' )
            {
            // InternalJavali.g:3112:1: ( '(' )
            // InternalJavali.g:3113:2: '('
            {
             before(grammarAccess.getIfElseAccess().getLeftParenthesisKeyword_1()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getIfElseAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__1__Impl"


    // $ANTLR start "rule__IfElse__Group__2"
    // InternalJavali.g:3122:1: rule__IfElse__Group__2 : rule__IfElse__Group__2__Impl rule__IfElse__Group__3 ;
    public final void rule__IfElse__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3126:1: ( rule__IfElse__Group__2__Impl rule__IfElse__Group__3 )
            // InternalJavali.g:3127:2: rule__IfElse__Group__2__Impl rule__IfElse__Group__3
            {
            pushFollow(FOLLOW_23);
            rule__IfElse__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IfElse__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__2"


    // $ANTLR start "rule__IfElse__Group__2__Impl"
    // InternalJavali.g:3134:1: rule__IfElse__Group__2__Impl : ( ( rule__IfElse__GuardAssignment_2 ) ) ;
    public final void rule__IfElse__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3138:1: ( ( ( rule__IfElse__GuardAssignment_2 ) ) )
            // InternalJavali.g:3139:1: ( ( rule__IfElse__GuardAssignment_2 ) )
            {
            // InternalJavali.g:3139:1: ( ( rule__IfElse__GuardAssignment_2 ) )
            // InternalJavali.g:3140:2: ( rule__IfElse__GuardAssignment_2 )
            {
             before(grammarAccess.getIfElseAccess().getGuardAssignment_2()); 
            // InternalJavali.g:3141:2: ( rule__IfElse__GuardAssignment_2 )
            // InternalJavali.g:3141:3: rule__IfElse__GuardAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__IfElse__GuardAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getIfElseAccess().getGuardAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__2__Impl"


    // $ANTLR start "rule__IfElse__Group__3"
    // InternalJavali.g:3149:1: rule__IfElse__Group__3 : rule__IfElse__Group__3__Impl rule__IfElse__Group__4 ;
    public final void rule__IfElse__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3153:1: ( rule__IfElse__Group__3__Impl rule__IfElse__Group__4 )
            // InternalJavali.g:3154:2: rule__IfElse__Group__3__Impl rule__IfElse__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__IfElse__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IfElse__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__3"


    // $ANTLR start "rule__IfElse__Group__3__Impl"
    // InternalJavali.g:3161:1: rule__IfElse__Group__3__Impl : ( ')' ) ;
    public final void rule__IfElse__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3165:1: ( ( ')' ) )
            // InternalJavali.g:3166:1: ( ')' )
            {
            // InternalJavali.g:3166:1: ( ')' )
            // InternalJavali.g:3167:2: ')'
            {
             before(grammarAccess.getIfElseAccess().getRightParenthesisKeyword_3()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getIfElseAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__3__Impl"


    // $ANTLR start "rule__IfElse__Group__4"
    // InternalJavali.g:3176:1: rule__IfElse__Group__4 : rule__IfElse__Group__4__Impl rule__IfElse__Group__5 ;
    public final void rule__IfElse__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3180:1: ( rule__IfElse__Group__4__Impl rule__IfElse__Group__5 )
            // InternalJavali.g:3181:2: rule__IfElse__Group__4__Impl rule__IfElse__Group__5
            {
            pushFollow(FOLLOW_24);
            rule__IfElse__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IfElse__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__4"


    // $ANTLR start "rule__IfElse__Group__4__Impl"
    // InternalJavali.g:3188:1: rule__IfElse__Group__4__Impl : ( ( rule__IfElse__SelectionBlockAssignment_4 ) ) ;
    public final void rule__IfElse__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3192:1: ( ( ( rule__IfElse__SelectionBlockAssignment_4 ) ) )
            // InternalJavali.g:3193:1: ( ( rule__IfElse__SelectionBlockAssignment_4 ) )
            {
            // InternalJavali.g:3193:1: ( ( rule__IfElse__SelectionBlockAssignment_4 ) )
            // InternalJavali.g:3194:2: ( rule__IfElse__SelectionBlockAssignment_4 )
            {
             before(grammarAccess.getIfElseAccess().getSelectionBlockAssignment_4()); 
            // InternalJavali.g:3195:2: ( rule__IfElse__SelectionBlockAssignment_4 )
            // InternalJavali.g:3195:3: rule__IfElse__SelectionBlockAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__IfElse__SelectionBlockAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getIfElseAccess().getSelectionBlockAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__4__Impl"


    // $ANTLR start "rule__IfElse__Group__5"
    // InternalJavali.g:3203:1: rule__IfElse__Group__5 : rule__IfElse__Group__5__Impl ;
    public final void rule__IfElse__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3207:1: ( rule__IfElse__Group__5__Impl )
            // InternalJavali.g:3208:2: rule__IfElse__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IfElse__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__5"


    // $ANTLR start "rule__IfElse__Group__5__Impl"
    // InternalJavali.g:3214:1: rule__IfElse__Group__5__Impl : ( ( rule__IfElse__Group_5__0 )? ) ;
    public final void rule__IfElse__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3218:1: ( ( ( rule__IfElse__Group_5__0 )? ) )
            // InternalJavali.g:3219:1: ( ( rule__IfElse__Group_5__0 )? )
            {
            // InternalJavali.g:3219:1: ( ( rule__IfElse__Group_5__0 )? )
            // InternalJavali.g:3220:2: ( rule__IfElse__Group_5__0 )?
            {
             before(grammarAccess.getIfElseAccess().getGroup_5()); 
            // InternalJavali.g:3221:2: ( rule__IfElse__Group_5__0 )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==37) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalJavali.g:3221:3: rule__IfElse__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__IfElse__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getIfElseAccess().getGroup_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group__5__Impl"


    // $ANTLR start "rule__IfElse__Group_5__0"
    // InternalJavali.g:3230:1: rule__IfElse__Group_5__0 : rule__IfElse__Group_5__0__Impl rule__IfElse__Group_5__1 ;
    public final void rule__IfElse__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3234:1: ( rule__IfElse__Group_5__0__Impl rule__IfElse__Group_5__1 )
            // InternalJavali.g:3235:2: rule__IfElse__Group_5__0__Impl rule__IfElse__Group_5__1
            {
            pushFollow(FOLLOW_9);
            rule__IfElse__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__IfElse__Group_5__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group_5__0"


    // $ANTLR start "rule__IfElse__Group_5__0__Impl"
    // InternalJavali.g:3242:1: rule__IfElse__Group_5__0__Impl : ( 'else' ) ;
    public final void rule__IfElse__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3246:1: ( ( 'else' ) )
            // InternalJavali.g:3247:1: ( 'else' )
            {
            // InternalJavali.g:3247:1: ( 'else' )
            // InternalJavali.g:3248:2: 'else'
            {
             before(grammarAccess.getIfElseAccess().getElseKeyword_5_0()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getIfElseAccess().getElseKeyword_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group_5__0__Impl"


    // $ANTLR start "rule__IfElse__Group_5__1"
    // InternalJavali.g:3257:1: rule__IfElse__Group_5__1 : rule__IfElse__Group_5__1__Impl ;
    public final void rule__IfElse__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3261:1: ( rule__IfElse__Group_5__1__Impl )
            // InternalJavali.g:3262:2: rule__IfElse__Group_5__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__IfElse__Group_5__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group_5__1"


    // $ANTLR start "rule__IfElse__Group_5__1__Impl"
    // InternalJavali.g:3268:1: rule__IfElse__Group_5__1__Impl : ( ( rule__IfElse__AlternativeBlockAssignment_5_1 ) ) ;
    public final void rule__IfElse__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3272:1: ( ( ( rule__IfElse__AlternativeBlockAssignment_5_1 ) ) )
            // InternalJavali.g:3273:1: ( ( rule__IfElse__AlternativeBlockAssignment_5_1 ) )
            {
            // InternalJavali.g:3273:1: ( ( rule__IfElse__AlternativeBlockAssignment_5_1 ) )
            // InternalJavali.g:3274:2: ( rule__IfElse__AlternativeBlockAssignment_5_1 )
            {
             before(grammarAccess.getIfElseAccess().getAlternativeBlockAssignment_5_1()); 
            // InternalJavali.g:3275:2: ( rule__IfElse__AlternativeBlockAssignment_5_1 )
            // InternalJavali.g:3275:3: rule__IfElse__AlternativeBlockAssignment_5_1
            {
            pushFollow(FOLLOW_2);
            rule__IfElse__AlternativeBlockAssignment_5_1();

            state._fsp--;


            }

             after(grammarAccess.getIfElseAccess().getAlternativeBlockAssignment_5_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__Group_5__1__Impl"


    // $ANTLR start "rule__While__Group__0"
    // InternalJavali.g:3284:1: rule__While__Group__0 : rule__While__Group__0__Impl rule__While__Group__1 ;
    public final void rule__While__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3288:1: ( rule__While__Group__0__Impl rule__While__Group__1 )
            // InternalJavali.g:3289:2: rule__While__Group__0__Impl rule__While__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__While__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__While__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__0"


    // $ANTLR start "rule__While__Group__0__Impl"
    // InternalJavali.g:3296:1: rule__While__Group__0__Impl : ( 'while' ) ;
    public final void rule__While__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3300:1: ( ( 'while' ) )
            // InternalJavali.g:3301:1: ( 'while' )
            {
            // InternalJavali.g:3301:1: ( 'while' )
            // InternalJavali.g:3302:2: 'while'
            {
             before(grammarAccess.getWhileAccess().getWhileKeyword_0()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getWhileAccess().getWhileKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__0__Impl"


    // $ANTLR start "rule__While__Group__1"
    // InternalJavali.g:3311:1: rule__While__Group__1 : rule__While__Group__1__Impl rule__While__Group__2 ;
    public final void rule__While__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3315:1: ( rule__While__Group__1__Impl rule__While__Group__2 )
            // InternalJavali.g:3316:2: rule__While__Group__1__Impl rule__While__Group__2
            {
            pushFollow(FOLLOW_20);
            rule__While__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__While__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__1"


    // $ANTLR start "rule__While__Group__1__Impl"
    // InternalJavali.g:3323:1: rule__While__Group__1__Impl : ( '(' ) ;
    public final void rule__While__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3327:1: ( ( '(' ) )
            // InternalJavali.g:3328:1: ( '(' )
            {
            // InternalJavali.g:3328:1: ( '(' )
            // InternalJavali.g:3329:2: '('
            {
             before(grammarAccess.getWhileAccess().getLeftParenthesisKeyword_1()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getWhileAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__1__Impl"


    // $ANTLR start "rule__While__Group__2"
    // InternalJavali.g:3338:1: rule__While__Group__2 : rule__While__Group__2__Impl rule__While__Group__3 ;
    public final void rule__While__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3342:1: ( rule__While__Group__2__Impl rule__While__Group__3 )
            // InternalJavali.g:3343:2: rule__While__Group__2__Impl rule__While__Group__3
            {
            pushFollow(FOLLOW_23);
            rule__While__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__While__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__2"


    // $ANTLR start "rule__While__Group__2__Impl"
    // InternalJavali.g:3350:1: rule__While__Group__2__Impl : ( ( rule__While__GuardAssignment_2 ) ) ;
    public final void rule__While__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3354:1: ( ( ( rule__While__GuardAssignment_2 ) ) )
            // InternalJavali.g:3355:1: ( ( rule__While__GuardAssignment_2 ) )
            {
            // InternalJavali.g:3355:1: ( ( rule__While__GuardAssignment_2 ) )
            // InternalJavali.g:3356:2: ( rule__While__GuardAssignment_2 )
            {
             before(grammarAccess.getWhileAccess().getGuardAssignment_2()); 
            // InternalJavali.g:3357:2: ( rule__While__GuardAssignment_2 )
            // InternalJavali.g:3357:3: rule__While__GuardAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__While__GuardAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getWhileAccess().getGuardAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__2__Impl"


    // $ANTLR start "rule__While__Group__3"
    // InternalJavali.g:3365:1: rule__While__Group__3 : rule__While__Group__3__Impl rule__While__Group__4 ;
    public final void rule__While__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3369:1: ( rule__While__Group__3__Impl rule__While__Group__4 )
            // InternalJavali.g:3370:2: rule__While__Group__3__Impl rule__While__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__While__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__While__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__3"


    // $ANTLR start "rule__While__Group__3__Impl"
    // InternalJavali.g:3377:1: rule__While__Group__3__Impl : ( ')' ) ;
    public final void rule__While__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3381:1: ( ( ')' ) )
            // InternalJavali.g:3382:1: ( ')' )
            {
            // InternalJavali.g:3382:1: ( ')' )
            // InternalJavali.g:3383:2: ')'
            {
             before(grammarAccess.getWhileAccess().getRightParenthesisKeyword_3()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getWhileAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__3__Impl"


    // $ANTLR start "rule__While__Group__4"
    // InternalJavali.g:3392:1: rule__While__Group__4 : rule__While__Group__4__Impl ;
    public final void rule__While__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3396:1: ( rule__While__Group__4__Impl )
            // InternalJavali.g:3397:2: rule__While__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__While__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__4"


    // $ANTLR start "rule__While__Group__4__Impl"
    // InternalJavali.g:3403:1: rule__While__Group__4__Impl : ( ( rule__While__BlockAssignment_4 ) ) ;
    public final void rule__While__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3407:1: ( ( ( rule__While__BlockAssignment_4 ) ) )
            // InternalJavali.g:3408:1: ( ( rule__While__BlockAssignment_4 ) )
            {
            // InternalJavali.g:3408:1: ( ( rule__While__BlockAssignment_4 ) )
            // InternalJavali.g:3409:2: ( rule__While__BlockAssignment_4 )
            {
             before(grammarAccess.getWhileAccess().getBlockAssignment_4()); 
            // InternalJavali.g:3410:2: ( rule__While__BlockAssignment_4 )
            // InternalJavali.g:3410:3: rule__While__BlockAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__While__BlockAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getWhileAccess().getBlockAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__Group__4__Impl"


    // $ANTLR start "rule__For__Group__0"
    // InternalJavali.g:3419:1: rule__For__Group__0 : rule__For__Group__0__Impl rule__For__Group__1 ;
    public final void rule__For__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3423:1: ( rule__For__Group__0__Impl rule__For__Group__1 )
            // InternalJavali.g:3424:2: rule__For__Group__0__Impl rule__For__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__For__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__0"


    // $ANTLR start "rule__For__Group__0__Impl"
    // InternalJavali.g:3431:1: rule__For__Group__0__Impl : ( 'for' ) ;
    public final void rule__For__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3435:1: ( ( 'for' ) )
            // InternalJavali.g:3436:1: ( 'for' )
            {
            // InternalJavali.g:3436:1: ( 'for' )
            // InternalJavali.g:3437:2: 'for'
            {
             before(grammarAccess.getForAccess().getForKeyword_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getForAccess().getForKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__0__Impl"


    // $ANTLR start "rule__For__Group__1"
    // InternalJavali.g:3446:1: rule__For__Group__1 : rule__For__Group__1__Impl rule__For__Group__2 ;
    public final void rule__For__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3450:1: ( rule__For__Group__1__Impl rule__For__Group__2 )
            // InternalJavali.g:3451:2: rule__For__Group__1__Impl rule__For__Group__2
            {
            pushFollow(FOLLOW_25);
            rule__For__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__1"


    // $ANTLR start "rule__For__Group__1__Impl"
    // InternalJavali.g:3458:1: rule__For__Group__1__Impl : ( '(' ) ;
    public final void rule__For__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3462:1: ( ( '(' ) )
            // InternalJavali.g:3463:1: ( '(' )
            {
            // InternalJavali.g:3463:1: ( '(' )
            // InternalJavali.g:3464:2: '('
            {
             before(grammarAccess.getForAccess().getLeftParenthesisKeyword_1()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getForAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__1__Impl"


    // $ANTLR start "rule__For__Group__2"
    // InternalJavali.g:3473:1: rule__For__Group__2 : rule__For__Group__2__Impl rule__For__Group__3 ;
    public final void rule__For__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3477:1: ( rule__For__Group__2__Impl rule__For__Group__3 )
            // InternalJavali.g:3478:2: rule__For__Group__2__Impl rule__For__Group__3
            {
            pushFollow(FOLLOW_25);
            rule__For__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__2"


    // $ANTLR start "rule__For__Group__2__Impl"
    // InternalJavali.g:3485:1: rule__For__Group__2__Impl : ( ( rule__For__Group_2__0 )? ) ;
    public final void rule__For__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3489:1: ( ( ( rule__For__Group_2__0 )? ) )
            // InternalJavali.g:3490:1: ( ( rule__For__Group_2__0 )? )
            {
            // InternalJavali.g:3490:1: ( ( rule__For__Group_2__0 )? )
            // InternalJavali.g:3491:2: ( rule__For__Group_2__0 )?
            {
             before(grammarAccess.getForAccess().getGroup_2()); 
            // InternalJavali.g:3492:2: ( rule__For__Group_2__0 )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==RULE_ID) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalJavali.g:3492:3: rule__For__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__For__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getForAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__2__Impl"


    // $ANTLR start "rule__For__Group__3"
    // InternalJavali.g:3500:1: rule__For__Group__3 : rule__For__Group__3__Impl rule__For__Group__4 ;
    public final void rule__For__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3504:1: ( rule__For__Group__3__Impl rule__For__Group__4 )
            // InternalJavali.g:3505:2: rule__For__Group__3__Impl rule__For__Group__4
            {
            pushFollow(FOLLOW_20);
            rule__For__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__3"


    // $ANTLR start "rule__For__Group__3__Impl"
    // InternalJavali.g:3512:1: rule__For__Group__3__Impl : ( ';' ) ;
    public final void rule__For__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3516:1: ( ( ';' ) )
            // InternalJavali.g:3517:1: ( ';' )
            {
            // InternalJavali.g:3517:1: ( ';' )
            // InternalJavali.g:3518:2: ';'
            {
             before(grammarAccess.getForAccess().getSemicolonKeyword_3()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getForAccess().getSemicolonKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__3__Impl"


    // $ANTLR start "rule__For__Group__4"
    // InternalJavali.g:3527:1: rule__For__Group__4 : rule__For__Group__4__Impl rule__For__Group__5 ;
    public final void rule__For__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3531:1: ( rule__For__Group__4__Impl rule__For__Group__5 )
            // InternalJavali.g:3532:2: rule__For__Group__4__Impl rule__For__Group__5
            {
            pushFollow(FOLLOW_8);
            rule__For__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__4"


    // $ANTLR start "rule__For__Group__4__Impl"
    // InternalJavali.g:3539:1: rule__For__Group__4__Impl : ( ( rule__For__GuardAssignment_4 ) ) ;
    public final void rule__For__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3543:1: ( ( ( rule__For__GuardAssignment_4 ) ) )
            // InternalJavali.g:3544:1: ( ( rule__For__GuardAssignment_4 ) )
            {
            // InternalJavali.g:3544:1: ( ( rule__For__GuardAssignment_4 ) )
            // InternalJavali.g:3545:2: ( rule__For__GuardAssignment_4 )
            {
             before(grammarAccess.getForAccess().getGuardAssignment_4()); 
            // InternalJavali.g:3546:2: ( rule__For__GuardAssignment_4 )
            // InternalJavali.g:3546:3: rule__For__GuardAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__For__GuardAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getForAccess().getGuardAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__4__Impl"


    // $ANTLR start "rule__For__Group__5"
    // InternalJavali.g:3554:1: rule__For__Group__5 : rule__For__Group__5__Impl rule__For__Group__6 ;
    public final void rule__For__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3558:1: ( rule__For__Group__5__Impl rule__For__Group__6 )
            // InternalJavali.g:3559:2: rule__For__Group__5__Impl rule__For__Group__6
            {
            pushFollow(FOLLOW_14);
            rule__For__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__5"


    // $ANTLR start "rule__For__Group__5__Impl"
    // InternalJavali.g:3566:1: rule__For__Group__5__Impl : ( ';' ) ;
    public final void rule__For__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3570:1: ( ( ';' ) )
            // InternalJavali.g:3571:1: ( ';' )
            {
            // InternalJavali.g:3571:1: ( ';' )
            // InternalJavali.g:3572:2: ';'
            {
             before(grammarAccess.getForAccess().getSemicolonKeyword_5()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getForAccess().getSemicolonKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__5__Impl"


    // $ANTLR start "rule__For__Group__6"
    // InternalJavali.g:3581:1: rule__For__Group__6 : rule__For__Group__6__Impl rule__For__Group__7 ;
    public final void rule__For__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3585:1: ( rule__For__Group__6__Impl rule__For__Group__7 )
            // InternalJavali.g:3586:2: rule__For__Group__6__Impl rule__For__Group__7
            {
            pushFollow(FOLLOW_14);
            rule__For__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__6"


    // $ANTLR start "rule__For__Group__6__Impl"
    // InternalJavali.g:3593:1: rule__For__Group__6__Impl : ( ( rule__For__Group_6__0 )? ) ;
    public final void rule__For__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3597:1: ( ( ( rule__For__Group_6__0 )? ) )
            // InternalJavali.g:3598:1: ( ( rule__For__Group_6__0 )? )
            {
            // InternalJavali.g:3598:1: ( ( rule__For__Group_6__0 )? )
            // InternalJavali.g:3599:2: ( rule__For__Group_6__0 )?
            {
             before(grammarAccess.getForAccess().getGroup_6()); 
            // InternalJavali.g:3600:2: ( rule__For__Group_6__0 )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==RULE_ID) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalJavali.g:3600:3: rule__For__Group_6__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__For__Group_6__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getForAccess().getGroup_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__6__Impl"


    // $ANTLR start "rule__For__Group__7"
    // InternalJavali.g:3608:1: rule__For__Group__7 : rule__For__Group__7__Impl rule__For__Group__8 ;
    public final void rule__For__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3612:1: ( rule__For__Group__7__Impl rule__For__Group__8 )
            // InternalJavali.g:3613:2: rule__For__Group__7__Impl rule__For__Group__8
            {
            pushFollow(FOLLOW_9);
            rule__For__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__7"


    // $ANTLR start "rule__For__Group__7__Impl"
    // InternalJavali.g:3620:1: rule__For__Group__7__Impl : ( ')' ) ;
    public final void rule__For__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3624:1: ( ( ')' ) )
            // InternalJavali.g:3625:1: ( ')' )
            {
            // InternalJavali.g:3625:1: ( ')' )
            // InternalJavali.g:3626:2: ')'
            {
             before(grammarAccess.getForAccess().getRightParenthesisKeyword_7()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getForAccess().getRightParenthesisKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__7__Impl"


    // $ANTLR start "rule__For__Group__8"
    // InternalJavali.g:3635:1: rule__For__Group__8 : rule__For__Group__8__Impl ;
    public final void rule__For__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3639:1: ( rule__For__Group__8__Impl )
            // InternalJavali.g:3640:2: rule__For__Group__8__Impl
            {
            pushFollow(FOLLOW_2);
            rule__For__Group__8__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__8"


    // $ANTLR start "rule__For__Group__8__Impl"
    // InternalJavali.g:3646:1: rule__For__Group__8__Impl : ( ( rule__For__BlockAssignment_8 ) ) ;
    public final void rule__For__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3650:1: ( ( ( rule__For__BlockAssignment_8 ) ) )
            // InternalJavali.g:3651:1: ( ( rule__For__BlockAssignment_8 ) )
            {
            // InternalJavali.g:3651:1: ( ( rule__For__BlockAssignment_8 ) )
            // InternalJavali.g:3652:2: ( rule__For__BlockAssignment_8 )
            {
             before(grammarAccess.getForAccess().getBlockAssignment_8()); 
            // InternalJavali.g:3653:2: ( rule__For__BlockAssignment_8 )
            // InternalJavali.g:3653:3: rule__For__BlockAssignment_8
            {
            pushFollow(FOLLOW_2);
            rule__For__BlockAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getForAccess().getBlockAssignment_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group__8__Impl"


    // $ANTLR start "rule__For__Group_2__0"
    // InternalJavali.g:3662:1: rule__For__Group_2__0 : rule__For__Group_2__0__Impl rule__For__Group_2__1 ;
    public final void rule__For__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3666:1: ( rule__For__Group_2__0__Impl rule__For__Group_2__1 )
            // InternalJavali.g:3667:2: rule__For__Group_2__0__Impl rule__For__Group_2__1
            {
            pushFollow(FOLLOW_15);
            rule__For__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2__0"


    // $ANTLR start "rule__For__Group_2__0__Impl"
    // InternalJavali.g:3674:1: rule__For__Group_2__0__Impl : ( ( rule__For__InitStatementsAssignment_2_0 ) ) ;
    public final void rule__For__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3678:1: ( ( ( rule__For__InitStatementsAssignment_2_0 ) ) )
            // InternalJavali.g:3679:1: ( ( rule__For__InitStatementsAssignment_2_0 ) )
            {
            // InternalJavali.g:3679:1: ( ( rule__For__InitStatementsAssignment_2_0 ) )
            // InternalJavali.g:3680:2: ( rule__For__InitStatementsAssignment_2_0 )
            {
             before(grammarAccess.getForAccess().getInitStatementsAssignment_2_0()); 
            // InternalJavali.g:3681:2: ( rule__For__InitStatementsAssignment_2_0 )
            // InternalJavali.g:3681:3: rule__For__InitStatementsAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__For__InitStatementsAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getForAccess().getInitStatementsAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2__0__Impl"


    // $ANTLR start "rule__For__Group_2__1"
    // InternalJavali.g:3689:1: rule__For__Group_2__1 : rule__For__Group_2__1__Impl ;
    public final void rule__For__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3693:1: ( rule__For__Group_2__1__Impl )
            // InternalJavali.g:3694:2: rule__For__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__For__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2__1"


    // $ANTLR start "rule__For__Group_2__1__Impl"
    // InternalJavali.g:3700:1: rule__For__Group_2__1__Impl : ( ( rule__For__Group_2_1__0 )* ) ;
    public final void rule__For__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3704:1: ( ( ( rule__For__Group_2_1__0 )* ) )
            // InternalJavali.g:3705:1: ( ( rule__For__Group_2_1__0 )* )
            {
            // InternalJavali.g:3705:1: ( ( rule__For__Group_2_1__0 )* )
            // InternalJavali.g:3706:2: ( rule__For__Group_2_1__0 )*
            {
             before(grammarAccess.getForAccess().getGroup_2_1()); 
            // InternalJavali.g:3707:2: ( rule__For__Group_2_1__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==32) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalJavali.g:3707:3: rule__For__Group_2_1__0
            	    {
            	    pushFollow(FOLLOW_16);
            	    rule__For__Group_2_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getForAccess().getGroup_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2__1__Impl"


    // $ANTLR start "rule__For__Group_2_1__0"
    // InternalJavali.g:3716:1: rule__For__Group_2_1__0 : rule__For__Group_2_1__0__Impl rule__For__Group_2_1__1 ;
    public final void rule__For__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3720:1: ( rule__For__Group_2_1__0__Impl rule__For__Group_2_1__1 )
            // InternalJavali.g:3721:2: rule__For__Group_2_1__0__Impl rule__For__Group_2_1__1
            {
            pushFollow(FOLLOW_5);
            rule__For__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group_2_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2_1__0"


    // $ANTLR start "rule__For__Group_2_1__0__Impl"
    // InternalJavali.g:3728:1: rule__For__Group_2_1__0__Impl : ( ',' ) ;
    public final void rule__For__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3732:1: ( ( ',' ) )
            // InternalJavali.g:3733:1: ( ',' )
            {
            // InternalJavali.g:3733:1: ( ',' )
            // InternalJavali.g:3734:2: ','
            {
             before(grammarAccess.getForAccess().getCommaKeyword_2_1_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getForAccess().getCommaKeyword_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2_1__0__Impl"


    // $ANTLR start "rule__For__Group_2_1__1"
    // InternalJavali.g:3743:1: rule__For__Group_2_1__1 : rule__For__Group_2_1__1__Impl ;
    public final void rule__For__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3747:1: ( rule__For__Group_2_1__1__Impl )
            // InternalJavali.g:3748:2: rule__For__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__For__Group_2_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2_1__1"


    // $ANTLR start "rule__For__Group_2_1__1__Impl"
    // InternalJavali.g:3754:1: rule__For__Group_2_1__1__Impl : ( ( rule__For__InitStatementsAssignment_2_1_1 ) ) ;
    public final void rule__For__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3758:1: ( ( ( rule__For__InitStatementsAssignment_2_1_1 ) ) )
            // InternalJavali.g:3759:1: ( ( rule__For__InitStatementsAssignment_2_1_1 ) )
            {
            // InternalJavali.g:3759:1: ( ( rule__For__InitStatementsAssignment_2_1_1 ) )
            // InternalJavali.g:3760:2: ( rule__For__InitStatementsAssignment_2_1_1 )
            {
             before(grammarAccess.getForAccess().getInitStatementsAssignment_2_1_1()); 
            // InternalJavali.g:3761:2: ( rule__For__InitStatementsAssignment_2_1_1 )
            // InternalJavali.g:3761:3: rule__For__InitStatementsAssignment_2_1_1
            {
            pushFollow(FOLLOW_2);
            rule__For__InitStatementsAssignment_2_1_1();

            state._fsp--;


            }

             after(grammarAccess.getForAccess().getInitStatementsAssignment_2_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_2_1__1__Impl"


    // $ANTLR start "rule__For__Group_6__0"
    // InternalJavali.g:3770:1: rule__For__Group_6__0 : rule__For__Group_6__0__Impl rule__For__Group_6__1 ;
    public final void rule__For__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3774:1: ( rule__For__Group_6__0__Impl rule__For__Group_6__1 )
            // InternalJavali.g:3775:2: rule__For__Group_6__0__Impl rule__For__Group_6__1
            {
            pushFollow(FOLLOW_15);
            rule__For__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6__0"


    // $ANTLR start "rule__For__Group_6__0__Impl"
    // InternalJavali.g:3782:1: rule__For__Group_6__0__Impl : ( ( rule__For__ProgressStatementsAssignment_6_0 ) ) ;
    public final void rule__For__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3786:1: ( ( ( rule__For__ProgressStatementsAssignment_6_0 ) ) )
            // InternalJavali.g:3787:1: ( ( rule__For__ProgressStatementsAssignment_6_0 ) )
            {
            // InternalJavali.g:3787:1: ( ( rule__For__ProgressStatementsAssignment_6_0 ) )
            // InternalJavali.g:3788:2: ( rule__For__ProgressStatementsAssignment_6_0 )
            {
             before(grammarAccess.getForAccess().getProgressStatementsAssignment_6_0()); 
            // InternalJavali.g:3789:2: ( rule__For__ProgressStatementsAssignment_6_0 )
            // InternalJavali.g:3789:3: rule__For__ProgressStatementsAssignment_6_0
            {
            pushFollow(FOLLOW_2);
            rule__For__ProgressStatementsAssignment_6_0();

            state._fsp--;


            }

             after(grammarAccess.getForAccess().getProgressStatementsAssignment_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6__0__Impl"


    // $ANTLR start "rule__For__Group_6__1"
    // InternalJavali.g:3797:1: rule__For__Group_6__1 : rule__For__Group_6__1__Impl ;
    public final void rule__For__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3801:1: ( rule__For__Group_6__1__Impl )
            // InternalJavali.g:3802:2: rule__For__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__For__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6__1"


    // $ANTLR start "rule__For__Group_6__1__Impl"
    // InternalJavali.g:3808:1: rule__For__Group_6__1__Impl : ( ( rule__For__Group_6_1__0 )* ) ;
    public final void rule__For__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3812:1: ( ( ( rule__For__Group_6_1__0 )* ) )
            // InternalJavali.g:3813:1: ( ( rule__For__Group_6_1__0 )* )
            {
            // InternalJavali.g:3813:1: ( ( rule__For__Group_6_1__0 )* )
            // InternalJavali.g:3814:2: ( rule__For__Group_6_1__0 )*
            {
             before(grammarAccess.getForAccess().getGroup_6_1()); 
            // InternalJavali.g:3815:2: ( rule__For__Group_6_1__0 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==32) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalJavali.g:3815:3: rule__For__Group_6_1__0
            	    {
            	    pushFollow(FOLLOW_16);
            	    rule__For__Group_6_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

             after(grammarAccess.getForAccess().getGroup_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6__1__Impl"


    // $ANTLR start "rule__For__Group_6_1__0"
    // InternalJavali.g:3824:1: rule__For__Group_6_1__0 : rule__For__Group_6_1__0__Impl rule__For__Group_6_1__1 ;
    public final void rule__For__Group_6_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3828:1: ( rule__For__Group_6_1__0__Impl rule__For__Group_6_1__1 )
            // InternalJavali.g:3829:2: rule__For__Group_6_1__0__Impl rule__For__Group_6_1__1
            {
            pushFollow(FOLLOW_5);
            rule__For__Group_6_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__For__Group_6_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6_1__0"


    // $ANTLR start "rule__For__Group_6_1__0__Impl"
    // InternalJavali.g:3836:1: rule__For__Group_6_1__0__Impl : ( ',' ) ;
    public final void rule__For__Group_6_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3840:1: ( ( ',' ) )
            // InternalJavali.g:3841:1: ( ',' )
            {
            // InternalJavali.g:3841:1: ( ',' )
            // InternalJavali.g:3842:2: ','
            {
             before(grammarAccess.getForAccess().getCommaKeyword_6_1_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getForAccess().getCommaKeyword_6_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6_1__0__Impl"


    // $ANTLR start "rule__For__Group_6_1__1"
    // InternalJavali.g:3851:1: rule__For__Group_6_1__1 : rule__For__Group_6_1__1__Impl ;
    public final void rule__For__Group_6_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3855:1: ( rule__For__Group_6_1__1__Impl )
            // InternalJavali.g:3856:2: rule__For__Group_6_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__For__Group_6_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6_1__1"


    // $ANTLR start "rule__For__Group_6_1__1__Impl"
    // InternalJavali.g:3862:1: rule__For__Group_6_1__1__Impl : ( ( rule__For__ProgressStatementsAssignment_6_1_1 ) ) ;
    public final void rule__For__Group_6_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3866:1: ( ( ( rule__For__ProgressStatementsAssignment_6_1_1 ) ) )
            // InternalJavali.g:3867:1: ( ( rule__For__ProgressStatementsAssignment_6_1_1 ) )
            {
            // InternalJavali.g:3867:1: ( ( rule__For__ProgressStatementsAssignment_6_1_1 ) )
            // InternalJavali.g:3868:2: ( rule__For__ProgressStatementsAssignment_6_1_1 )
            {
             before(grammarAccess.getForAccess().getProgressStatementsAssignment_6_1_1()); 
            // InternalJavali.g:3869:2: ( rule__For__ProgressStatementsAssignment_6_1_1 )
            // InternalJavali.g:3869:3: rule__For__ProgressStatementsAssignment_6_1_1
            {
            pushFollow(FOLLOW_2);
            rule__For__ProgressStatementsAssignment_6_1_1();

            state._fsp--;


            }

             after(grammarAccess.getForAccess().getProgressStatementsAssignment_6_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__Group_6_1__1__Impl"


    // $ANTLR start "rule__DoWhile__Group__0"
    // InternalJavali.g:3878:1: rule__DoWhile__Group__0 : rule__DoWhile__Group__0__Impl rule__DoWhile__Group__1 ;
    public final void rule__DoWhile__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3882:1: ( rule__DoWhile__Group__0__Impl rule__DoWhile__Group__1 )
            // InternalJavali.g:3883:2: rule__DoWhile__Group__0__Impl rule__DoWhile__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__DoWhile__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DoWhile__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__0"


    // $ANTLR start "rule__DoWhile__Group__0__Impl"
    // InternalJavali.g:3890:1: rule__DoWhile__Group__0__Impl : ( 'do' ) ;
    public final void rule__DoWhile__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3894:1: ( ( 'do' ) )
            // InternalJavali.g:3895:1: ( 'do' )
            {
            // InternalJavali.g:3895:1: ( 'do' )
            // InternalJavali.g:3896:2: 'do'
            {
             before(grammarAccess.getDoWhileAccess().getDoKeyword_0()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getDoWhileAccess().getDoKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__0__Impl"


    // $ANTLR start "rule__DoWhile__Group__1"
    // InternalJavali.g:3905:1: rule__DoWhile__Group__1 : rule__DoWhile__Group__1__Impl rule__DoWhile__Group__2 ;
    public final void rule__DoWhile__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3909:1: ( rule__DoWhile__Group__1__Impl rule__DoWhile__Group__2 )
            // InternalJavali.g:3910:2: rule__DoWhile__Group__1__Impl rule__DoWhile__Group__2
            {
            pushFollow(FOLLOW_26);
            rule__DoWhile__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DoWhile__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__1"


    // $ANTLR start "rule__DoWhile__Group__1__Impl"
    // InternalJavali.g:3917:1: rule__DoWhile__Group__1__Impl : ( ( rule__DoWhile__BlockAssignment_1 ) ) ;
    public final void rule__DoWhile__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3921:1: ( ( ( rule__DoWhile__BlockAssignment_1 ) ) )
            // InternalJavali.g:3922:1: ( ( rule__DoWhile__BlockAssignment_1 ) )
            {
            // InternalJavali.g:3922:1: ( ( rule__DoWhile__BlockAssignment_1 ) )
            // InternalJavali.g:3923:2: ( rule__DoWhile__BlockAssignment_1 )
            {
             before(grammarAccess.getDoWhileAccess().getBlockAssignment_1()); 
            // InternalJavali.g:3924:2: ( rule__DoWhile__BlockAssignment_1 )
            // InternalJavali.g:3924:3: rule__DoWhile__BlockAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__DoWhile__BlockAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getDoWhileAccess().getBlockAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__1__Impl"


    // $ANTLR start "rule__DoWhile__Group__2"
    // InternalJavali.g:3932:1: rule__DoWhile__Group__2 : rule__DoWhile__Group__2__Impl rule__DoWhile__Group__3 ;
    public final void rule__DoWhile__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3936:1: ( rule__DoWhile__Group__2__Impl rule__DoWhile__Group__3 )
            // InternalJavali.g:3937:2: rule__DoWhile__Group__2__Impl rule__DoWhile__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__DoWhile__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DoWhile__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__2"


    // $ANTLR start "rule__DoWhile__Group__2__Impl"
    // InternalJavali.g:3944:1: rule__DoWhile__Group__2__Impl : ( 'while' ) ;
    public final void rule__DoWhile__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3948:1: ( ( 'while' ) )
            // InternalJavali.g:3949:1: ( 'while' )
            {
            // InternalJavali.g:3949:1: ( 'while' )
            // InternalJavali.g:3950:2: 'while'
            {
             before(grammarAccess.getDoWhileAccess().getWhileKeyword_2()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getDoWhileAccess().getWhileKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__2__Impl"


    // $ANTLR start "rule__DoWhile__Group__3"
    // InternalJavali.g:3959:1: rule__DoWhile__Group__3 : rule__DoWhile__Group__3__Impl rule__DoWhile__Group__4 ;
    public final void rule__DoWhile__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3963:1: ( rule__DoWhile__Group__3__Impl rule__DoWhile__Group__4 )
            // InternalJavali.g:3964:2: rule__DoWhile__Group__3__Impl rule__DoWhile__Group__4
            {
            pushFollow(FOLLOW_20);
            rule__DoWhile__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DoWhile__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__3"


    // $ANTLR start "rule__DoWhile__Group__3__Impl"
    // InternalJavali.g:3971:1: rule__DoWhile__Group__3__Impl : ( '(' ) ;
    public final void rule__DoWhile__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3975:1: ( ( '(' ) )
            // InternalJavali.g:3976:1: ( '(' )
            {
            // InternalJavali.g:3976:1: ( '(' )
            // InternalJavali.g:3977:2: '('
            {
             before(grammarAccess.getDoWhileAccess().getLeftParenthesisKeyword_3()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getDoWhileAccess().getLeftParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__3__Impl"


    // $ANTLR start "rule__DoWhile__Group__4"
    // InternalJavali.g:3986:1: rule__DoWhile__Group__4 : rule__DoWhile__Group__4__Impl rule__DoWhile__Group__5 ;
    public final void rule__DoWhile__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:3990:1: ( rule__DoWhile__Group__4__Impl rule__DoWhile__Group__5 )
            // InternalJavali.g:3991:2: rule__DoWhile__Group__4__Impl rule__DoWhile__Group__5
            {
            pushFollow(FOLLOW_23);
            rule__DoWhile__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__DoWhile__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__4"


    // $ANTLR start "rule__DoWhile__Group__4__Impl"
    // InternalJavali.g:3998:1: rule__DoWhile__Group__4__Impl : ( ( rule__DoWhile__GuardAssignment_4 ) ) ;
    public final void rule__DoWhile__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4002:1: ( ( ( rule__DoWhile__GuardAssignment_4 ) ) )
            // InternalJavali.g:4003:1: ( ( rule__DoWhile__GuardAssignment_4 ) )
            {
            // InternalJavali.g:4003:1: ( ( rule__DoWhile__GuardAssignment_4 ) )
            // InternalJavali.g:4004:2: ( rule__DoWhile__GuardAssignment_4 )
            {
             before(grammarAccess.getDoWhileAccess().getGuardAssignment_4()); 
            // InternalJavali.g:4005:2: ( rule__DoWhile__GuardAssignment_4 )
            // InternalJavali.g:4005:3: rule__DoWhile__GuardAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__DoWhile__GuardAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getDoWhileAccess().getGuardAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__4__Impl"


    // $ANTLR start "rule__DoWhile__Group__5"
    // InternalJavali.g:4013:1: rule__DoWhile__Group__5 : rule__DoWhile__Group__5__Impl ;
    public final void rule__DoWhile__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4017:1: ( rule__DoWhile__Group__5__Impl )
            // InternalJavali.g:4018:2: rule__DoWhile__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__DoWhile__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__5"


    // $ANTLR start "rule__DoWhile__Group__5__Impl"
    // InternalJavali.g:4024:1: rule__DoWhile__Group__5__Impl : ( ')' ) ;
    public final void rule__DoWhile__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4028:1: ( ( ')' ) )
            // InternalJavali.g:4029:1: ( ')' )
            {
            // InternalJavali.g:4029:1: ( ')' )
            // InternalJavali.g:4030:2: ')'
            {
             before(grammarAccess.getDoWhileAccess().getRightParenthesisKeyword_5()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getDoWhileAccess().getRightParenthesisKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__Group__5__Impl"


    // $ANTLR start "rule__Increment__Group__0"
    // InternalJavali.g:4040:1: rule__Increment__Group__0 : rule__Increment__Group__0__Impl rule__Increment__Group__1 ;
    public final void rule__Increment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4044:1: ( rule__Increment__Group__0__Impl rule__Increment__Group__1 )
            // InternalJavali.g:4045:2: rule__Increment__Group__0__Impl rule__Increment__Group__1
            {
            pushFollow(FOLLOW_27);
            rule__Increment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Increment__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Increment__Group__0"


    // $ANTLR start "rule__Increment__Group__0__Impl"
    // InternalJavali.g:4052:1: rule__Increment__Group__0__Impl : ( ( rule__Increment__IdAssignment_0 ) ) ;
    public final void rule__Increment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4056:1: ( ( ( rule__Increment__IdAssignment_0 ) ) )
            // InternalJavali.g:4057:1: ( ( rule__Increment__IdAssignment_0 ) )
            {
            // InternalJavali.g:4057:1: ( ( rule__Increment__IdAssignment_0 ) )
            // InternalJavali.g:4058:2: ( rule__Increment__IdAssignment_0 )
            {
             before(grammarAccess.getIncrementAccess().getIdAssignment_0()); 
            // InternalJavali.g:4059:2: ( rule__Increment__IdAssignment_0 )
            // InternalJavali.g:4059:3: rule__Increment__IdAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Increment__IdAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getIncrementAccess().getIdAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Increment__Group__0__Impl"


    // $ANTLR start "rule__Increment__Group__1"
    // InternalJavali.g:4067:1: rule__Increment__Group__1 : rule__Increment__Group__1__Impl ;
    public final void rule__Increment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4071:1: ( rule__Increment__Group__1__Impl )
            // InternalJavali.g:4072:2: rule__Increment__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Increment__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Increment__Group__1"


    // $ANTLR start "rule__Increment__Group__1__Impl"
    // InternalJavali.g:4078:1: rule__Increment__Group__1__Impl : ( '++' ) ;
    public final void rule__Increment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4082:1: ( ( '++' ) )
            // InternalJavali.g:4083:1: ( '++' )
            {
            // InternalJavali.g:4083:1: ( '++' )
            // InternalJavali.g:4084:2: '++'
            {
             before(grammarAccess.getIncrementAccess().getPlusSignPlusSignKeyword_1()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getIncrementAccess().getPlusSignPlusSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Increment__Group__1__Impl"


    // $ANTLR start "rule__Decrement__Group__0"
    // InternalJavali.g:4094:1: rule__Decrement__Group__0 : rule__Decrement__Group__0__Impl rule__Decrement__Group__1 ;
    public final void rule__Decrement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4098:1: ( rule__Decrement__Group__0__Impl rule__Decrement__Group__1 )
            // InternalJavali.g:4099:2: rule__Decrement__Group__0__Impl rule__Decrement__Group__1
            {
            pushFollow(FOLLOW_28);
            rule__Decrement__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Decrement__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Decrement__Group__0"


    // $ANTLR start "rule__Decrement__Group__0__Impl"
    // InternalJavali.g:4106:1: rule__Decrement__Group__0__Impl : ( ( rule__Decrement__IdAssignment_0 ) ) ;
    public final void rule__Decrement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4110:1: ( ( ( rule__Decrement__IdAssignment_0 ) ) )
            // InternalJavali.g:4111:1: ( ( rule__Decrement__IdAssignment_0 ) )
            {
            // InternalJavali.g:4111:1: ( ( rule__Decrement__IdAssignment_0 ) )
            // InternalJavali.g:4112:2: ( rule__Decrement__IdAssignment_0 )
            {
             before(grammarAccess.getDecrementAccess().getIdAssignment_0()); 
            // InternalJavali.g:4113:2: ( rule__Decrement__IdAssignment_0 )
            // InternalJavali.g:4113:3: rule__Decrement__IdAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Decrement__IdAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDecrementAccess().getIdAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Decrement__Group__0__Impl"


    // $ANTLR start "rule__Decrement__Group__1"
    // InternalJavali.g:4121:1: rule__Decrement__Group__1 : rule__Decrement__Group__1__Impl ;
    public final void rule__Decrement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4125:1: ( rule__Decrement__Group__1__Impl )
            // InternalJavali.g:4126:2: rule__Decrement__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Decrement__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Decrement__Group__1"


    // $ANTLR start "rule__Decrement__Group__1__Impl"
    // InternalJavali.g:4132:1: rule__Decrement__Group__1__Impl : ( '--' ) ;
    public final void rule__Decrement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4136:1: ( ( '--' ) )
            // InternalJavali.g:4137:1: ( '--' )
            {
            // InternalJavali.g:4137:1: ( '--' )
            // InternalJavali.g:4138:2: '--'
            {
             before(grammarAccess.getDecrementAccess().getHyphenMinusHyphenMinusKeyword_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getDecrementAccess().getHyphenMinusHyphenMinusKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Decrement__Group__1__Impl"


    // $ANTLR start "rule__Or__Group__0"
    // InternalJavali.g:4148:1: rule__Or__Group__0 : rule__Or__Group__0__Impl rule__Or__Group__1 ;
    public final void rule__Or__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4152:1: ( rule__Or__Group__0__Impl rule__Or__Group__1 )
            // InternalJavali.g:4153:2: rule__Or__Group__0__Impl rule__Or__Group__1
            {
            pushFollow(FOLLOW_29);
            rule__Or__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Or__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__0"


    // $ANTLR start "rule__Or__Group__0__Impl"
    // InternalJavali.g:4160:1: rule__Or__Group__0__Impl : ( ruleXor ) ;
    public final void rule__Or__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4164:1: ( ( ruleXor ) )
            // InternalJavali.g:4165:1: ( ruleXor )
            {
            // InternalJavali.g:4165:1: ( ruleXor )
            // InternalJavali.g:4166:2: ruleXor
            {
             before(grammarAccess.getOrAccess().getXorParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleXor();

            state._fsp--;

             after(grammarAccess.getOrAccess().getXorParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__0__Impl"


    // $ANTLR start "rule__Or__Group__1"
    // InternalJavali.g:4175:1: rule__Or__Group__1 : rule__Or__Group__1__Impl ;
    public final void rule__Or__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4179:1: ( rule__Or__Group__1__Impl )
            // InternalJavali.g:4180:2: rule__Or__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Or__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__1"


    // $ANTLR start "rule__Or__Group__1__Impl"
    // InternalJavali.g:4186:1: rule__Or__Group__1__Impl : ( ( rule__Or__Group_1__0 )* ) ;
    public final void rule__Or__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4190:1: ( ( ( rule__Or__Group_1__0 )* ) )
            // InternalJavali.g:4191:1: ( ( rule__Or__Group_1__0 )* )
            {
            // InternalJavali.g:4191:1: ( ( rule__Or__Group_1__0 )* )
            // InternalJavali.g:4192:2: ( rule__Or__Group_1__0 )*
            {
             before(grammarAccess.getOrAccess().getGroup_1()); 
            // InternalJavali.g:4193:2: ( rule__Or__Group_1__0 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==43) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalJavali.g:4193:3: rule__Or__Group_1__0
            	    {
            	    pushFollow(FOLLOW_30);
            	    rule__Or__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getOrAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group__1__Impl"


    // $ANTLR start "rule__Or__Group_1__0"
    // InternalJavali.g:4202:1: rule__Or__Group_1__0 : rule__Or__Group_1__0__Impl rule__Or__Group_1__1 ;
    public final void rule__Or__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4206:1: ( rule__Or__Group_1__0__Impl rule__Or__Group_1__1 )
            // InternalJavali.g:4207:2: rule__Or__Group_1__0__Impl rule__Or__Group_1__1
            {
            pushFollow(FOLLOW_29);
            rule__Or__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Or__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__0"


    // $ANTLR start "rule__Or__Group_1__0__Impl"
    // InternalJavali.g:4214:1: rule__Or__Group_1__0__Impl : ( () ) ;
    public final void rule__Or__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4218:1: ( ( () ) )
            // InternalJavali.g:4219:1: ( () )
            {
            // InternalJavali.g:4219:1: ( () )
            // InternalJavali.g:4220:2: ()
            {
             before(grammarAccess.getOrAccess().getOrLeftAction_1_0()); 
            // InternalJavali.g:4221:2: ()
            // InternalJavali.g:4221:3: 
            {
            }

             after(grammarAccess.getOrAccess().getOrLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__0__Impl"


    // $ANTLR start "rule__Or__Group_1__1"
    // InternalJavali.g:4229:1: rule__Or__Group_1__1 : rule__Or__Group_1__1__Impl rule__Or__Group_1__2 ;
    public final void rule__Or__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4233:1: ( rule__Or__Group_1__1__Impl rule__Or__Group_1__2 )
            // InternalJavali.g:4234:2: rule__Or__Group_1__1__Impl rule__Or__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__Or__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Or__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__1"


    // $ANTLR start "rule__Or__Group_1__1__Impl"
    // InternalJavali.g:4241:1: rule__Or__Group_1__1__Impl : ( '||' ) ;
    public final void rule__Or__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4245:1: ( ( '||' ) )
            // InternalJavali.g:4246:1: ( '||' )
            {
            // InternalJavali.g:4246:1: ( '||' )
            // InternalJavali.g:4247:2: '||'
            {
             before(grammarAccess.getOrAccess().getVerticalLineVerticalLineKeyword_1_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getOrAccess().getVerticalLineVerticalLineKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__1__Impl"


    // $ANTLR start "rule__Or__Group_1__2"
    // InternalJavali.g:4256:1: rule__Or__Group_1__2 : rule__Or__Group_1__2__Impl ;
    public final void rule__Or__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4260:1: ( rule__Or__Group_1__2__Impl )
            // InternalJavali.g:4261:2: rule__Or__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Or__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__2"


    // $ANTLR start "rule__Or__Group_1__2__Impl"
    // InternalJavali.g:4267:1: rule__Or__Group_1__2__Impl : ( ( rule__Or__RightAssignment_1_2 ) ) ;
    public final void rule__Or__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4271:1: ( ( ( rule__Or__RightAssignment_1_2 ) ) )
            // InternalJavali.g:4272:1: ( ( rule__Or__RightAssignment_1_2 ) )
            {
            // InternalJavali.g:4272:1: ( ( rule__Or__RightAssignment_1_2 ) )
            // InternalJavali.g:4273:2: ( rule__Or__RightAssignment_1_2 )
            {
             before(grammarAccess.getOrAccess().getRightAssignment_1_2()); 
            // InternalJavali.g:4274:2: ( rule__Or__RightAssignment_1_2 )
            // InternalJavali.g:4274:3: rule__Or__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Or__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getOrAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__Group_1__2__Impl"


    // $ANTLR start "rule__Xor__Group__0"
    // InternalJavali.g:4283:1: rule__Xor__Group__0 : rule__Xor__Group__0__Impl rule__Xor__Group__1 ;
    public final void rule__Xor__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4287:1: ( rule__Xor__Group__0__Impl rule__Xor__Group__1 )
            // InternalJavali.g:4288:2: rule__Xor__Group__0__Impl rule__Xor__Group__1
            {
            pushFollow(FOLLOW_31);
            rule__Xor__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Xor__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group__0"


    // $ANTLR start "rule__Xor__Group__0__Impl"
    // InternalJavali.g:4295:1: rule__Xor__Group__0__Impl : ( ruleAnd ) ;
    public final void rule__Xor__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4299:1: ( ( ruleAnd ) )
            // InternalJavali.g:4300:1: ( ruleAnd )
            {
            // InternalJavali.g:4300:1: ( ruleAnd )
            // InternalJavali.g:4301:2: ruleAnd
            {
             before(grammarAccess.getXorAccess().getAndParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getXorAccess().getAndParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group__0__Impl"


    // $ANTLR start "rule__Xor__Group__1"
    // InternalJavali.g:4310:1: rule__Xor__Group__1 : rule__Xor__Group__1__Impl ;
    public final void rule__Xor__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4314:1: ( rule__Xor__Group__1__Impl )
            // InternalJavali.g:4315:2: rule__Xor__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Xor__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group__1"


    // $ANTLR start "rule__Xor__Group__1__Impl"
    // InternalJavali.g:4321:1: rule__Xor__Group__1__Impl : ( ( rule__Xor__Group_1__0 )* ) ;
    public final void rule__Xor__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4325:1: ( ( ( rule__Xor__Group_1__0 )* ) )
            // InternalJavali.g:4326:1: ( ( rule__Xor__Group_1__0 )* )
            {
            // InternalJavali.g:4326:1: ( ( rule__Xor__Group_1__0 )* )
            // InternalJavali.g:4327:2: ( rule__Xor__Group_1__0 )*
            {
             before(grammarAccess.getXorAccess().getGroup_1()); 
            // InternalJavali.g:4328:2: ( rule__Xor__Group_1__0 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==44) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalJavali.g:4328:3: rule__Xor__Group_1__0
            	    {
            	    pushFollow(FOLLOW_32);
            	    rule__Xor__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getXorAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group__1__Impl"


    // $ANTLR start "rule__Xor__Group_1__0"
    // InternalJavali.g:4337:1: rule__Xor__Group_1__0 : rule__Xor__Group_1__0__Impl rule__Xor__Group_1__1 ;
    public final void rule__Xor__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4341:1: ( rule__Xor__Group_1__0__Impl rule__Xor__Group_1__1 )
            // InternalJavali.g:4342:2: rule__Xor__Group_1__0__Impl rule__Xor__Group_1__1
            {
            pushFollow(FOLLOW_31);
            rule__Xor__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Xor__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group_1__0"


    // $ANTLR start "rule__Xor__Group_1__0__Impl"
    // InternalJavali.g:4349:1: rule__Xor__Group_1__0__Impl : ( () ) ;
    public final void rule__Xor__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4353:1: ( ( () ) )
            // InternalJavali.g:4354:1: ( () )
            {
            // InternalJavali.g:4354:1: ( () )
            // InternalJavali.g:4355:2: ()
            {
             before(grammarAccess.getXorAccess().getXorLeftAction_1_0()); 
            // InternalJavali.g:4356:2: ()
            // InternalJavali.g:4356:3: 
            {
            }

             after(grammarAccess.getXorAccess().getXorLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group_1__0__Impl"


    // $ANTLR start "rule__Xor__Group_1__1"
    // InternalJavali.g:4364:1: rule__Xor__Group_1__1 : rule__Xor__Group_1__1__Impl rule__Xor__Group_1__2 ;
    public final void rule__Xor__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4368:1: ( rule__Xor__Group_1__1__Impl rule__Xor__Group_1__2 )
            // InternalJavali.g:4369:2: rule__Xor__Group_1__1__Impl rule__Xor__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__Xor__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Xor__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group_1__1"


    // $ANTLR start "rule__Xor__Group_1__1__Impl"
    // InternalJavali.g:4376:1: rule__Xor__Group_1__1__Impl : ( '^' ) ;
    public final void rule__Xor__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4380:1: ( ( '^' ) )
            // InternalJavali.g:4381:1: ( '^' )
            {
            // InternalJavali.g:4381:1: ( '^' )
            // InternalJavali.g:4382:2: '^'
            {
             before(grammarAccess.getXorAccess().getCircumflexAccentKeyword_1_1()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getXorAccess().getCircumflexAccentKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group_1__1__Impl"


    // $ANTLR start "rule__Xor__Group_1__2"
    // InternalJavali.g:4391:1: rule__Xor__Group_1__2 : rule__Xor__Group_1__2__Impl ;
    public final void rule__Xor__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4395:1: ( rule__Xor__Group_1__2__Impl )
            // InternalJavali.g:4396:2: rule__Xor__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Xor__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group_1__2"


    // $ANTLR start "rule__Xor__Group_1__2__Impl"
    // InternalJavali.g:4402:1: rule__Xor__Group_1__2__Impl : ( ( rule__Xor__RightAssignment_1_2 ) ) ;
    public final void rule__Xor__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4406:1: ( ( ( rule__Xor__RightAssignment_1_2 ) ) )
            // InternalJavali.g:4407:1: ( ( rule__Xor__RightAssignment_1_2 ) )
            {
            // InternalJavali.g:4407:1: ( ( rule__Xor__RightAssignment_1_2 ) )
            // InternalJavali.g:4408:2: ( rule__Xor__RightAssignment_1_2 )
            {
             before(grammarAccess.getXorAccess().getRightAssignment_1_2()); 
            // InternalJavali.g:4409:2: ( rule__Xor__RightAssignment_1_2 )
            // InternalJavali.g:4409:3: rule__Xor__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Xor__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getXorAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__Group_1__2__Impl"


    // $ANTLR start "rule__And__Group__0"
    // InternalJavali.g:4418:1: rule__And__Group__0 : rule__And__Group__0__Impl rule__And__Group__1 ;
    public final void rule__And__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4422:1: ( rule__And__Group__0__Impl rule__And__Group__1 )
            // InternalJavali.g:4423:2: rule__And__Group__0__Impl rule__And__Group__1
            {
            pushFollow(FOLLOW_33);
            rule__And__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__And__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__0"


    // $ANTLR start "rule__And__Group__0__Impl"
    // InternalJavali.g:4430:1: rule__And__Group__0__Impl : ( ruleEquality ) ;
    public final void rule__And__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4434:1: ( ( ruleEquality ) )
            // InternalJavali.g:4435:1: ( ruleEquality )
            {
            // InternalJavali.g:4435:1: ( ruleEquality )
            // InternalJavali.g:4436:2: ruleEquality
            {
             before(grammarAccess.getAndAccess().getEqualityParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleEquality();

            state._fsp--;

             after(grammarAccess.getAndAccess().getEqualityParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__0__Impl"


    // $ANTLR start "rule__And__Group__1"
    // InternalJavali.g:4445:1: rule__And__Group__1 : rule__And__Group__1__Impl ;
    public final void rule__And__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4449:1: ( rule__And__Group__1__Impl )
            // InternalJavali.g:4450:2: rule__And__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__And__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__1"


    // $ANTLR start "rule__And__Group__1__Impl"
    // InternalJavali.g:4456:1: rule__And__Group__1__Impl : ( ( rule__And__Group_1__0 )* ) ;
    public final void rule__And__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4460:1: ( ( ( rule__And__Group_1__0 )* ) )
            // InternalJavali.g:4461:1: ( ( rule__And__Group_1__0 )* )
            {
            // InternalJavali.g:4461:1: ( ( rule__And__Group_1__0 )* )
            // InternalJavali.g:4462:2: ( rule__And__Group_1__0 )*
            {
             before(grammarAccess.getAndAccess().getGroup_1()); 
            // InternalJavali.g:4463:2: ( rule__And__Group_1__0 )*
            loop28:
            do {
                int alt28=2;
                int LA28_0 = input.LA(1);

                if ( (LA28_0==45) ) {
                    alt28=1;
                }


                switch (alt28) {
            	case 1 :
            	    // InternalJavali.g:4463:3: rule__And__Group_1__0
            	    {
            	    pushFollow(FOLLOW_34);
            	    rule__And__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop28;
                }
            } while (true);

             after(grammarAccess.getAndAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group__1__Impl"


    // $ANTLR start "rule__And__Group_1__0"
    // InternalJavali.g:4472:1: rule__And__Group_1__0 : rule__And__Group_1__0__Impl rule__And__Group_1__1 ;
    public final void rule__And__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4476:1: ( rule__And__Group_1__0__Impl rule__And__Group_1__1 )
            // InternalJavali.g:4477:2: rule__And__Group_1__0__Impl rule__And__Group_1__1
            {
            pushFollow(FOLLOW_33);
            rule__And__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__And__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__0"


    // $ANTLR start "rule__And__Group_1__0__Impl"
    // InternalJavali.g:4484:1: rule__And__Group_1__0__Impl : ( () ) ;
    public final void rule__And__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4488:1: ( ( () ) )
            // InternalJavali.g:4489:1: ( () )
            {
            // InternalJavali.g:4489:1: ( () )
            // InternalJavali.g:4490:2: ()
            {
             before(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 
            // InternalJavali.g:4491:2: ()
            // InternalJavali.g:4491:3: 
            {
            }

             after(grammarAccess.getAndAccess().getAndLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__0__Impl"


    // $ANTLR start "rule__And__Group_1__1"
    // InternalJavali.g:4499:1: rule__And__Group_1__1 : rule__And__Group_1__1__Impl rule__And__Group_1__2 ;
    public final void rule__And__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4503:1: ( rule__And__Group_1__1__Impl rule__And__Group_1__2 )
            // InternalJavali.g:4504:2: rule__And__Group_1__1__Impl rule__And__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__And__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__And__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__1"


    // $ANTLR start "rule__And__Group_1__1__Impl"
    // InternalJavali.g:4511:1: rule__And__Group_1__1__Impl : ( '&&' ) ;
    public final void rule__And__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4515:1: ( ( '&&' ) )
            // InternalJavali.g:4516:1: ( '&&' )
            {
            // InternalJavali.g:4516:1: ( '&&' )
            // InternalJavali.g:4517:2: '&&'
            {
             before(grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getAndAccess().getAmpersandAmpersandKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__1__Impl"


    // $ANTLR start "rule__And__Group_1__2"
    // InternalJavali.g:4526:1: rule__And__Group_1__2 : rule__And__Group_1__2__Impl ;
    public final void rule__And__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4530:1: ( rule__And__Group_1__2__Impl )
            // InternalJavali.g:4531:2: rule__And__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__And__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__2"


    // $ANTLR start "rule__And__Group_1__2__Impl"
    // InternalJavali.g:4537:1: rule__And__Group_1__2__Impl : ( ( rule__And__RightAssignment_1_2 ) ) ;
    public final void rule__And__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4541:1: ( ( ( rule__And__RightAssignment_1_2 ) ) )
            // InternalJavali.g:4542:1: ( ( rule__And__RightAssignment_1_2 ) )
            {
            // InternalJavali.g:4542:1: ( ( rule__And__RightAssignment_1_2 ) )
            // InternalJavali.g:4543:2: ( rule__And__RightAssignment_1_2 )
            {
             before(grammarAccess.getAndAccess().getRightAssignment_1_2()); 
            // InternalJavali.g:4544:2: ( rule__And__RightAssignment_1_2 )
            // InternalJavali.g:4544:3: rule__And__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__And__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getAndAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__Group_1__2__Impl"


    // $ANTLR start "rule__Equality__Group__0"
    // InternalJavali.g:4553:1: rule__Equality__Group__0 : rule__Equality__Group__0__Impl rule__Equality__Group__1 ;
    public final void rule__Equality__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4557:1: ( rule__Equality__Group__0__Impl rule__Equality__Group__1 )
            // InternalJavali.g:4558:2: rule__Equality__Group__0__Impl rule__Equality__Group__1
            {
            pushFollow(FOLLOW_35);
            rule__Equality__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Equality__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group__0"


    // $ANTLR start "rule__Equality__Group__0__Impl"
    // InternalJavali.g:4565:1: rule__Equality__Group__0__Impl : ( ruleRelation ) ;
    public final void rule__Equality__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4569:1: ( ( ruleRelation ) )
            // InternalJavali.g:4570:1: ( ruleRelation )
            {
            // InternalJavali.g:4570:1: ( ruleRelation )
            // InternalJavali.g:4571:2: ruleRelation
            {
             before(grammarAccess.getEqualityAccess().getRelationParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleRelation();

            state._fsp--;

             after(grammarAccess.getEqualityAccess().getRelationParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group__0__Impl"


    // $ANTLR start "rule__Equality__Group__1"
    // InternalJavali.g:4580:1: rule__Equality__Group__1 : rule__Equality__Group__1__Impl ;
    public final void rule__Equality__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4584:1: ( rule__Equality__Group__1__Impl )
            // InternalJavali.g:4585:2: rule__Equality__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Equality__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group__1"


    // $ANTLR start "rule__Equality__Group__1__Impl"
    // InternalJavali.g:4591:1: rule__Equality__Group__1__Impl : ( ( rule__Equality__Group_1__0 )* ) ;
    public final void rule__Equality__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4595:1: ( ( ( rule__Equality__Group_1__0 )* ) )
            // InternalJavali.g:4596:1: ( ( rule__Equality__Group_1__0 )* )
            {
            // InternalJavali.g:4596:1: ( ( rule__Equality__Group_1__0 )* )
            // InternalJavali.g:4597:2: ( rule__Equality__Group_1__0 )*
            {
             before(grammarAccess.getEqualityAccess().getGroup_1()); 
            // InternalJavali.g:4598:2: ( rule__Equality__Group_1__0 )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=13 && LA29_0<=14)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // InternalJavali.g:4598:3: rule__Equality__Group_1__0
            	    {
            	    pushFollow(FOLLOW_36);
            	    rule__Equality__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

             after(grammarAccess.getEqualityAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group__1__Impl"


    // $ANTLR start "rule__Equality__Group_1__0"
    // InternalJavali.g:4607:1: rule__Equality__Group_1__0 : rule__Equality__Group_1__0__Impl rule__Equality__Group_1__1 ;
    public final void rule__Equality__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4611:1: ( rule__Equality__Group_1__0__Impl rule__Equality__Group_1__1 )
            // InternalJavali.g:4612:2: rule__Equality__Group_1__0__Impl rule__Equality__Group_1__1
            {
            pushFollow(FOLLOW_35);
            rule__Equality__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Equality__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group_1__0"


    // $ANTLR start "rule__Equality__Group_1__0__Impl"
    // InternalJavali.g:4619:1: rule__Equality__Group_1__0__Impl : ( () ) ;
    public final void rule__Equality__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4623:1: ( ( () ) )
            // InternalJavali.g:4624:1: ( () )
            {
            // InternalJavali.g:4624:1: ( () )
            // InternalJavali.g:4625:2: ()
            {
             before(grammarAccess.getEqualityAccess().getEqualityLeftAction_1_0()); 
            // InternalJavali.g:4626:2: ()
            // InternalJavali.g:4626:3: 
            {
            }

             after(grammarAccess.getEqualityAccess().getEqualityLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group_1__0__Impl"


    // $ANTLR start "rule__Equality__Group_1__1"
    // InternalJavali.g:4634:1: rule__Equality__Group_1__1 : rule__Equality__Group_1__1__Impl rule__Equality__Group_1__2 ;
    public final void rule__Equality__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4638:1: ( rule__Equality__Group_1__1__Impl rule__Equality__Group_1__2 )
            // InternalJavali.g:4639:2: rule__Equality__Group_1__1__Impl rule__Equality__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__Equality__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Equality__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group_1__1"


    // $ANTLR start "rule__Equality__Group_1__1__Impl"
    // InternalJavali.g:4646:1: rule__Equality__Group_1__1__Impl : ( ( rule__Equality__OperatorAssignment_1_1 ) ) ;
    public final void rule__Equality__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4650:1: ( ( ( rule__Equality__OperatorAssignment_1_1 ) ) )
            // InternalJavali.g:4651:1: ( ( rule__Equality__OperatorAssignment_1_1 ) )
            {
            // InternalJavali.g:4651:1: ( ( rule__Equality__OperatorAssignment_1_1 ) )
            // InternalJavali.g:4652:2: ( rule__Equality__OperatorAssignment_1_1 )
            {
             before(grammarAccess.getEqualityAccess().getOperatorAssignment_1_1()); 
            // InternalJavali.g:4653:2: ( rule__Equality__OperatorAssignment_1_1 )
            // InternalJavali.g:4653:3: rule__Equality__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Equality__OperatorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getEqualityAccess().getOperatorAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group_1__1__Impl"


    // $ANTLR start "rule__Equality__Group_1__2"
    // InternalJavali.g:4661:1: rule__Equality__Group_1__2 : rule__Equality__Group_1__2__Impl ;
    public final void rule__Equality__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4665:1: ( rule__Equality__Group_1__2__Impl )
            // InternalJavali.g:4666:2: rule__Equality__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Equality__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group_1__2"


    // $ANTLR start "rule__Equality__Group_1__2__Impl"
    // InternalJavali.g:4672:1: rule__Equality__Group_1__2__Impl : ( ( rule__Equality__RightAssignment_1_2 ) ) ;
    public final void rule__Equality__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4676:1: ( ( ( rule__Equality__RightAssignment_1_2 ) ) )
            // InternalJavali.g:4677:1: ( ( rule__Equality__RightAssignment_1_2 ) )
            {
            // InternalJavali.g:4677:1: ( ( rule__Equality__RightAssignment_1_2 ) )
            // InternalJavali.g:4678:2: ( rule__Equality__RightAssignment_1_2 )
            {
             before(grammarAccess.getEqualityAccess().getRightAssignment_1_2()); 
            // InternalJavali.g:4679:2: ( rule__Equality__RightAssignment_1_2 )
            // InternalJavali.g:4679:3: rule__Equality__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Equality__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getEqualityAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__Group_1__2__Impl"


    // $ANTLR start "rule__Relation__Group__0"
    // InternalJavali.g:4688:1: rule__Relation__Group__0 : rule__Relation__Group__0__Impl rule__Relation__Group__1 ;
    public final void rule__Relation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4692:1: ( rule__Relation__Group__0__Impl rule__Relation__Group__1 )
            // InternalJavali.g:4693:2: rule__Relation__Group__0__Impl rule__Relation__Group__1
            {
            pushFollow(FOLLOW_37);
            rule__Relation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Relation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__0"


    // $ANTLR start "rule__Relation__Group__0__Impl"
    // InternalJavali.g:4700:1: rule__Relation__Group__0__Impl : ( ruleAddition ) ;
    public final void rule__Relation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4704:1: ( ( ruleAddition ) )
            // InternalJavali.g:4705:1: ( ruleAddition )
            {
            // InternalJavali.g:4705:1: ( ruleAddition )
            // InternalJavali.g:4706:2: ruleAddition
            {
             before(grammarAccess.getRelationAccess().getAdditionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getRelationAccess().getAdditionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__0__Impl"


    // $ANTLR start "rule__Relation__Group__1"
    // InternalJavali.g:4715:1: rule__Relation__Group__1 : rule__Relation__Group__1__Impl ;
    public final void rule__Relation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4719:1: ( rule__Relation__Group__1__Impl )
            // InternalJavali.g:4720:2: rule__Relation__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__1"


    // $ANTLR start "rule__Relation__Group__1__Impl"
    // InternalJavali.g:4726:1: rule__Relation__Group__1__Impl : ( ( rule__Relation__Group_1__0 )* ) ;
    public final void rule__Relation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4730:1: ( ( ( rule__Relation__Group_1__0 )* ) )
            // InternalJavali.g:4731:1: ( ( rule__Relation__Group_1__0 )* )
            {
            // InternalJavali.g:4731:1: ( ( rule__Relation__Group_1__0 )* )
            // InternalJavali.g:4732:2: ( rule__Relation__Group_1__0 )*
            {
             before(grammarAccess.getRelationAccess().getGroup_1()); 
            // InternalJavali.g:4733:2: ( rule__Relation__Group_1__0 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>=15 && LA30_0<=18)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalJavali.g:4733:3: rule__Relation__Group_1__0
            	    {
            	    pushFollow(FOLLOW_38);
            	    rule__Relation__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getRelationAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__1__Impl"


    // $ANTLR start "rule__Relation__Group_1__0"
    // InternalJavali.g:4742:1: rule__Relation__Group_1__0 : rule__Relation__Group_1__0__Impl rule__Relation__Group_1__1 ;
    public final void rule__Relation__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4746:1: ( rule__Relation__Group_1__0__Impl rule__Relation__Group_1__1 )
            // InternalJavali.g:4747:2: rule__Relation__Group_1__0__Impl rule__Relation__Group_1__1
            {
            pushFollow(FOLLOW_37);
            rule__Relation__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Relation__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_1__0"


    // $ANTLR start "rule__Relation__Group_1__0__Impl"
    // InternalJavali.g:4754:1: rule__Relation__Group_1__0__Impl : ( () ) ;
    public final void rule__Relation__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4758:1: ( ( () ) )
            // InternalJavali.g:4759:1: ( () )
            {
            // InternalJavali.g:4759:1: ( () )
            // InternalJavali.g:4760:2: ()
            {
             before(grammarAccess.getRelationAccess().getRelationLeftAction_1_0()); 
            // InternalJavali.g:4761:2: ()
            // InternalJavali.g:4761:3: 
            {
            }

             after(grammarAccess.getRelationAccess().getRelationLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_1__0__Impl"


    // $ANTLR start "rule__Relation__Group_1__1"
    // InternalJavali.g:4769:1: rule__Relation__Group_1__1 : rule__Relation__Group_1__1__Impl rule__Relation__Group_1__2 ;
    public final void rule__Relation__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4773:1: ( rule__Relation__Group_1__1__Impl rule__Relation__Group_1__2 )
            // InternalJavali.g:4774:2: rule__Relation__Group_1__1__Impl rule__Relation__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__Relation__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Relation__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_1__1"


    // $ANTLR start "rule__Relation__Group_1__1__Impl"
    // InternalJavali.g:4781:1: rule__Relation__Group_1__1__Impl : ( ( rule__Relation__OperatorAssignment_1_1 ) ) ;
    public final void rule__Relation__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4785:1: ( ( ( rule__Relation__OperatorAssignment_1_1 ) ) )
            // InternalJavali.g:4786:1: ( ( rule__Relation__OperatorAssignment_1_1 ) )
            {
            // InternalJavali.g:4786:1: ( ( rule__Relation__OperatorAssignment_1_1 ) )
            // InternalJavali.g:4787:2: ( rule__Relation__OperatorAssignment_1_1 )
            {
             before(grammarAccess.getRelationAccess().getOperatorAssignment_1_1()); 
            // InternalJavali.g:4788:2: ( rule__Relation__OperatorAssignment_1_1 )
            // InternalJavali.g:4788:3: rule__Relation__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Relation__OperatorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getOperatorAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_1__1__Impl"


    // $ANTLR start "rule__Relation__Group_1__2"
    // InternalJavali.g:4796:1: rule__Relation__Group_1__2 : rule__Relation__Group_1__2__Impl ;
    public final void rule__Relation__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4800:1: ( rule__Relation__Group_1__2__Impl )
            // InternalJavali.g:4801:2: rule__Relation__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_1__2"


    // $ANTLR start "rule__Relation__Group_1__2__Impl"
    // InternalJavali.g:4807:1: rule__Relation__Group_1__2__Impl : ( ( rule__Relation__RightAssignment_1_2 ) ) ;
    public final void rule__Relation__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4811:1: ( ( ( rule__Relation__RightAssignment_1_2 ) ) )
            // InternalJavali.g:4812:1: ( ( rule__Relation__RightAssignment_1_2 ) )
            {
            // InternalJavali.g:4812:1: ( ( rule__Relation__RightAssignment_1_2 ) )
            // InternalJavali.g:4813:2: ( rule__Relation__RightAssignment_1_2 )
            {
             before(grammarAccess.getRelationAccess().getRightAssignment_1_2()); 
            // InternalJavali.g:4814:2: ( rule__Relation__RightAssignment_1_2 )
            // InternalJavali.g:4814:3: rule__Relation__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Relation__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_1__2__Impl"


    // $ANTLR start "rule__Addition__Group__0"
    // InternalJavali.g:4823:1: rule__Addition__Group__0 : rule__Addition__Group__0__Impl rule__Addition__Group__1 ;
    public final void rule__Addition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4827:1: ( rule__Addition__Group__0__Impl rule__Addition__Group__1 )
            // InternalJavali.g:4828:2: rule__Addition__Group__0__Impl rule__Addition__Group__1
            {
            pushFollow(FOLLOW_39);
            rule__Addition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__0"


    // $ANTLR start "rule__Addition__Group__0__Impl"
    // InternalJavali.g:4835:1: rule__Addition__Group__0__Impl : ( ruleMultiplication ) ;
    public final void rule__Addition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4839:1: ( ( ruleMultiplication ) )
            // InternalJavali.g:4840:1: ( ruleMultiplication )
            {
            // InternalJavali.g:4840:1: ( ruleMultiplication )
            // InternalJavali.g:4841:2: ruleMultiplication
            {
             before(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__0__Impl"


    // $ANTLR start "rule__Addition__Group__1"
    // InternalJavali.g:4850:1: rule__Addition__Group__1 : rule__Addition__Group__1__Impl ;
    public final void rule__Addition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4854:1: ( rule__Addition__Group__1__Impl )
            // InternalJavali.g:4855:2: rule__Addition__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__1"


    // $ANTLR start "rule__Addition__Group__1__Impl"
    // InternalJavali.g:4861:1: rule__Addition__Group__1__Impl : ( ( rule__Addition__Group_1__0 )* ) ;
    public final void rule__Addition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4865:1: ( ( ( rule__Addition__Group_1__0 )* ) )
            // InternalJavali.g:4866:1: ( ( rule__Addition__Group_1__0 )* )
            {
            // InternalJavali.g:4866:1: ( ( rule__Addition__Group_1__0 )* )
            // InternalJavali.g:4867:2: ( rule__Addition__Group_1__0 )*
            {
             before(grammarAccess.getAdditionAccess().getGroup_1()); 
            // InternalJavali.g:4868:2: ( rule__Addition__Group_1__0 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0>=19 && LA31_0<=20)) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalJavali.g:4868:3: rule__Addition__Group_1__0
            	    {
            	    pushFollow(FOLLOW_40);
            	    rule__Addition__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

             after(grammarAccess.getAdditionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group__1__Impl"


    // $ANTLR start "rule__Addition__Group_1__0"
    // InternalJavali.g:4877:1: rule__Addition__Group_1__0 : rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 ;
    public final void rule__Addition__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4881:1: ( rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 )
            // InternalJavali.g:4882:2: rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1
            {
            pushFollow(FOLLOW_39);
            rule__Addition__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__0"


    // $ANTLR start "rule__Addition__Group_1__0__Impl"
    // InternalJavali.g:4889:1: rule__Addition__Group_1__0__Impl : ( () ) ;
    public final void rule__Addition__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4893:1: ( ( () ) )
            // InternalJavali.g:4894:1: ( () )
            {
            // InternalJavali.g:4894:1: ( () )
            // InternalJavali.g:4895:2: ()
            {
             before(grammarAccess.getAdditionAccess().getAdditionLeftAction_1_0()); 
            // InternalJavali.g:4896:2: ()
            // InternalJavali.g:4896:3: 
            {
            }

             after(grammarAccess.getAdditionAccess().getAdditionLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__0__Impl"


    // $ANTLR start "rule__Addition__Group_1__1"
    // InternalJavali.g:4904:1: rule__Addition__Group_1__1 : rule__Addition__Group_1__1__Impl rule__Addition__Group_1__2 ;
    public final void rule__Addition__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4908:1: ( rule__Addition__Group_1__1__Impl rule__Addition__Group_1__2 )
            // InternalJavali.g:4909:2: rule__Addition__Group_1__1__Impl rule__Addition__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__Addition__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__1"


    // $ANTLR start "rule__Addition__Group_1__1__Impl"
    // InternalJavali.g:4916:1: rule__Addition__Group_1__1__Impl : ( ( rule__Addition__OperatorAssignment_1_1 ) ) ;
    public final void rule__Addition__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4920:1: ( ( ( rule__Addition__OperatorAssignment_1_1 ) ) )
            // InternalJavali.g:4921:1: ( ( rule__Addition__OperatorAssignment_1_1 ) )
            {
            // InternalJavali.g:4921:1: ( ( rule__Addition__OperatorAssignment_1_1 ) )
            // InternalJavali.g:4922:2: ( rule__Addition__OperatorAssignment_1_1 )
            {
             before(grammarAccess.getAdditionAccess().getOperatorAssignment_1_1()); 
            // InternalJavali.g:4923:2: ( rule__Addition__OperatorAssignment_1_1 )
            // InternalJavali.g:4923:3: rule__Addition__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Addition__OperatorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getOperatorAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__1__Impl"


    // $ANTLR start "rule__Addition__Group_1__2"
    // InternalJavali.g:4931:1: rule__Addition__Group_1__2 : rule__Addition__Group_1__2__Impl ;
    public final void rule__Addition__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4935:1: ( rule__Addition__Group_1__2__Impl )
            // InternalJavali.g:4936:2: rule__Addition__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__2"


    // $ANTLR start "rule__Addition__Group_1__2__Impl"
    // InternalJavali.g:4942:1: rule__Addition__Group_1__2__Impl : ( ( rule__Addition__RightAssignment_1_2 ) ) ;
    public final void rule__Addition__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4946:1: ( ( ( rule__Addition__RightAssignment_1_2 ) ) )
            // InternalJavali.g:4947:1: ( ( rule__Addition__RightAssignment_1_2 ) )
            {
            // InternalJavali.g:4947:1: ( ( rule__Addition__RightAssignment_1_2 ) )
            // InternalJavali.g:4948:2: ( rule__Addition__RightAssignment_1_2 )
            {
             before(grammarAccess.getAdditionAccess().getRightAssignment_1_2()); 
            // InternalJavali.g:4949:2: ( rule__Addition__RightAssignment_1_2 )
            // InternalJavali.g:4949:3: rule__Addition__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Addition__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1__2__Impl"


    // $ANTLR start "rule__Multiplication__Group__0"
    // InternalJavali.g:4958:1: rule__Multiplication__Group__0 : rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 ;
    public final void rule__Multiplication__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4962:1: ( rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 )
            // InternalJavali.g:4963:2: rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1
            {
            pushFollow(FOLLOW_41);
            rule__Multiplication__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__0"


    // $ANTLR start "rule__Multiplication__Group__0__Impl"
    // InternalJavali.g:4970:1: rule__Multiplication__Group__0__Impl : ( rulePrimary ) ;
    public final void rule__Multiplication__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4974:1: ( ( rulePrimary ) )
            // InternalJavali.g:4975:1: ( rulePrimary )
            {
            // InternalJavali.g:4975:1: ( rulePrimary )
            // InternalJavali.g:4976:2: rulePrimary
            {
             before(grammarAccess.getMultiplicationAccess().getPrimaryParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getMultiplicationAccess().getPrimaryParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__0__Impl"


    // $ANTLR start "rule__Multiplication__Group__1"
    // InternalJavali.g:4985:1: rule__Multiplication__Group__1 : rule__Multiplication__Group__1__Impl ;
    public final void rule__Multiplication__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:4989:1: ( rule__Multiplication__Group__1__Impl )
            // InternalJavali.g:4990:2: rule__Multiplication__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__1"


    // $ANTLR start "rule__Multiplication__Group__1__Impl"
    // InternalJavali.g:4996:1: rule__Multiplication__Group__1__Impl : ( ( rule__Multiplication__Group_1__0 )* ) ;
    public final void rule__Multiplication__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5000:1: ( ( ( rule__Multiplication__Group_1__0 )* ) )
            // InternalJavali.g:5001:1: ( ( rule__Multiplication__Group_1__0 )* )
            {
            // InternalJavali.g:5001:1: ( ( rule__Multiplication__Group_1__0 )* )
            // InternalJavali.g:5002:2: ( rule__Multiplication__Group_1__0 )*
            {
             before(grammarAccess.getMultiplicationAccess().getGroup_1()); 
            // InternalJavali.g:5003:2: ( rule__Multiplication__Group_1__0 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( ((LA32_0>=21 && LA32_0<=23)) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalJavali.g:5003:3: rule__Multiplication__Group_1__0
            	    {
            	    pushFollow(FOLLOW_42);
            	    rule__Multiplication__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

             after(grammarAccess.getMultiplicationAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1__0"
    // InternalJavali.g:5012:1: rule__Multiplication__Group_1__0 : rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 ;
    public final void rule__Multiplication__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5016:1: ( rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 )
            // InternalJavali.g:5017:2: rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1
            {
            pushFollow(FOLLOW_41);
            rule__Multiplication__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__0"


    // $ANTLR start "rule__Multiplication__Group_1__0__Impl"
    // InternalJavali.g:5024:1: rule__Multiplication__Group_1__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5028:1: ( ( () ) )
            // InternalJavali.g:5029:1: ( () )
            {
            // InternalJavali.g:5029:1: ( () )
            // InternalJavali.g:5030:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getMultiplicationLeftAction_1_0()); 
            // InternalJavali.g:5031:2: ()
            // InternalJavali.g:5031:3: 
            {
            }

             after(grammarAccess.getMultiplicationAccess().getMultiplicationLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1__1"
    // InternalJavali.g:5039:1: rule__Multiplication__Group_1__1 : rule__Multiplication__Group_1__1__Impl rule__Multiplication__Group_1__2 ;
    public final void rule__Multiplication__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5043:1: ( rule__Multiplication__Group_1__1__Impl rule__Multiplication__Group_1__2 )
            // InternalJavali.g:5044:2: rule__Multiplication__Group_1__1__Impl rule__Multiplication__Group_1__2
            {
            pushFollow(FOLLOW_20);
            rule__Multiplication__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__1"


    // $ANTLR start "rule__Multiplication__Group_1__1__Impl"
    // InternalJavali.g:5051:1: rule__Multiplication__Group_1__1__Impl : ( ( rule__Multiplication__OperatorAssignment_1_1 ) ) ;
    public final void rule__Multiplication__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5055:1: ( ( ( rule__Multiplication__OperatorAssignment_1_1 ) ) )
            // InternalJavali.g:5056:1: ( ( rule__Multiplication__OperatorAssignment_1_1 ) )
            {
            // InternalJavali.g:5056:1: ( ( rule__Multiplication__OperatorAssignment_1_1 ) )
            // InternalJavali.g:5057:2: ( rule__Multiplication__OperatorAssignment_1_1 )
            {
             before(grammarAccess.getMultiplicationAccess().getOperatorAssignment_1_1()); 
            // InternalJavali.g:5058:2: ( rule__Multiplication__OperatorAssignment_1_1 )
            // InternalJavali.g:5058:3: rule__Multiplication__OperatorAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__OperatorAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getOperatorAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1__2"
    // InternalJavali.g:5066:1: rule__Multiplication__Group_1__2 : rule__Multiplication__Group_1__2__Impl ;
    public final void rule__Multiplication__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5070:1: ( rule__Multiplication__Group_1__2__Impl )
            // InternalJavali.g:5071:2: rule__Multiplication__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__2"


    // $ANTLR start "rule__Multiplication__Group_1__2__Impl"
    // InternalJavali.g:5077:1: rule__Multiplication__Group_1__2__Impl : ( ( rule__Multiplication__RightAssignment_1_2 ) ) ;
    public final void rule__Multiplication__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5081:1: ( ( ( rule__Multiplication__RightAssignment_1_2 ) ) )
            // InternalJavali.g:5082:1: ( ( rule__Multiplication__RightAssignment_1_2 ) )
            {
            // InternalJavali.g:5082:1: ( ( rule__Multiplication__RightAssignment_1_2 ) )
            // InternalJavali.g:5083:2: ( rule__Multiplication__RightAssignment_1_2 )
            {
             before(grammarAccess.getMultiplicationAccess().getRightAssignment_1_2()); 
            // InternalJavali.g:5084:2: ( rule__Multiplication__RightAssignment_1_2 )
            // InternalJavali.g:5084:3: rule__Multiplication__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getRightAssignment_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1__2__Impl"


    // $ANTLR start "rule__Primary__Group_6__0"
    // InternalJavali.g:5093:1: rule__Primary__Group_6__0 : rule__Primary__Group_6__0__Impl rule__Primary__Group_6__1 ;
    public final void rule__Primary__Group_6__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5097:1: ( rule__Primary__Group_6__0__Impl rule__Primary__Group_6__1 )
            // InternalJavali.g:5098:2: rule__Primary__Group_6__0__Impl rule__Primary__Group_6__1
            {
            pushFollow(FOLLOW_20);
            rule__Primary__Group_6__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Primary__Group_6__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_6__0"


    // $ANTLR start "rule__Primary__Group_6__0__Impl"
    // InternalJavali.g:5105:1: rule__Primary__Group_6__0__Impl : ( '!' ) ;
    public final void rule__Primary__Group_6__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5109:1: ( ( '!' ) )
            // InternalJavali.g:5110:1: ( '!' )
            {
            // InternalJavali.g:5110:1: ( '!' )
            // InternalJavali.g:5111:2: '!'
            {
             before(grammarAccess.getPrimaryAccess().getExclamationMarkKeyword_6_0()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getPrimaryAccess().getExclamationMarkKeyword_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_6__0__Impl"


    // $ANTLR start "rule__Primary__Group_6__1"
    // InternalJavali.g:5120:1: rule__Primary__Group_6__1 : rule__Primary__Group_6__1__Impl ;
    public final void rule__Primary__Group_6__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5124:1: ( rule__Primary__Group_6__1__Impl )
            // InternalJavali.g:5125:2: rule__Primary__Group_6__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Primary__Group_6__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_6__1"


    // $ANTLR start "rule__Primary__Group_6__1__Impl"
    // InternalJavali.g:5131:1: rule__Primary__Group_6__1__Impl : ( rulePrimary ) ;
    public final void rule__Primary__Group_6__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5135:1: ( ( rulePrimary ) )
            // InternalJavali.g:5136:1: ( rulePrimary )
            {
            // InternalJavali.g:5136:1: ( rulePrimary )
            // InternalJavali.g:5137:2: rulePrimary
            {
             before(grammarAccess.getPrimaryAccess().getPrimaryParserRuleCall_6_1()); 
            pushFollow(FOLLOW_2);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getPrimaryAccess().getPrimaryParserRuleCall_6_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_6__1__Impl"


    // $ANTLR start "rule__Primary__Group_7__0"
    // InternalJavali.g:5147:1: rule__Primary__Group_7__0 : rule__Primary__Group_7__0__Impl rule__Primary__Group_7__1 ;
    public final void rule__Primary__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5151:1: ( rule__Primary__Group_7__0__Impl rule__Primary__Group_7__1 )
            // InternalJavali.g:5152:2: rule__Primary__Group_7__0__Impl rule__Primary__Group_7__1
            {
            pushFollow(FOLLOW_20);
            rule__Primary__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Primary__Group_7__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_7__0"


    // $ANTLR start "rule__Primary__Group_7__0__Impl"
    // InternalJavali.g:5159:1: rule__Primary__Group_7__0__Impl : ( '(' ) ;
    public final void rule__Primary__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5163:1: ( ( '(' ) )
            // InternalJavali.g:5164:1: ( '(' )
            {
            // InternalJavali.g:5164:1: ( '(' )
            // InternalJavali.g:5165:2: '('
            {
             before(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_7_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getPrimaryAccess().getLeftParenthesisKeyword_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_7__0__Impl"


    // $ANTLR start "rule__Primary__Group_7__1"
    // InternalJavali.g:5174:1: rule__Primary__Group_7__1 : rule__Primary__Group_7__1__Impl rule__Primary__Group_7__2 ;
    public final void rule__Primary__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5178:1: ( rule__Primary__Group_7__1__Impl rule__Primary__Group_7__2 )
            // InternalJavali.g:5179:2: rule__Primary__Group_7__1__Impl rule__Primary__Group_7__2
            {
            pushFollow(FOLLOW_23);
            rule__Primary__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Primary__Group_7__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_7__1"


    // $ANTLR start "rule__Primary__Group_7__1__Impl"
    // InternalJavali.g:5186:1: rule__Primary__Group_7__1__Impl : ( ruleExpression ) ;
    public final void rule__Primary__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5190:1: ( ( ruleExpression ) )
            // InternalJavali.g:5191:1: ( ruleExpression )
            {
            // InternalJavali.g:5191:1: ( ruleExpression )
            // InternalJavali.g:5192:2: ruleExpression
            {
             before(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_7_1()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getPrimaryAccess().getExpressionParserRuleCall_7_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_7__1__Impl"


    // $ANTLR start "rule__Primary__Group_7__2"
    // InternalJavali.g:5201:1: rule__Primary__Group_7__2 : rule__Primary__Group_7__2__Impl ;
    public final void rule__Primary__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5205:1: ( rule__Primary__Group_7__2__Impl )
            // InternalJavali.g:5206:2: rule__Primary__Group_7__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Primary__Group_7__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_7__2"


    // $ANTLR start "rule__Primary__Group_7__2__Impl"
    // InternalJavali.g:5212:1: rule__Primary__Group_7__2__Impl : ( ')' ) ;
    public final void rule__Primary__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5216:1: ( ( ')' ) )
            // InternalJavali.g:5217:1: ( ')' )
            {
            // InternalJavali.g:5217:1: ( ')' )
            // InternalJavali.g:5218:2: ')'
            {
             before(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_7_2()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getPrimaryAccess().getRightParenthesisKeyword_7_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Primary__Group_7__2__Impl"


    // $ANTLR start "rule__Null__Group__0"
    // InternalJavali.g:5228:1: rule__Null__Group__0 : rule__Null__Group__0__Impl rule__Null__Group__1 ;
    public final void rule__Null__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5232:1: ( rule__Null__Group__0__Impl rule__Null__Group__1 )
            // InternalJavali.g:5233:2: rule__Null__Group__0__Impl rule__Null__Group__1
            {
            pushFollow(FOLLOW_43);
            rule__Null__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Null__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Null__Group__0"


    // $ANTLR start "rule__Null__Group__0__Impl"
    // InternalJavali.g:5240:1: rule__Null__Group__0__Impl : ( () ) ;
    public final void rule__Null__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5244:1: ( ( () ) )
            // InternalJavali.g:5245:1: ( () )
            {
            // InternalJavali.g:5245:1: ( () )
            // InternalJavali.g:5246:2: ()
            {
             before(grammarAccess.getNullAccess().getNullAction_0()); 
            // InternalJavali.g:5247:2: ()
            // InternalJavali.g:5247:3: 
            {
            }

             after(grammarAccess.getNullAccess().getNullAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Null__Group__0__Impl"


    // $ANTLR start "rule__Null__Group__1"
    // InternalJavali.g:5255:1: rule__Null__Group__1 : rule__Null__Group__1__Impl ;
    public final void rule__Null__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5259:1: ( rule__Null__Group__1__Impl )
            // InternalJavali.g:5260:2: rule__Null__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Null__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Null__Group__1"


    // $ANTLR start "rule__Null__Group__1__Impl"
    // InternalJavali.g:5266:1: rule__Null__Group__1__Impl : ( 'null' ) ;
    public final void rule__Null__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5270:1: ( ( 'null' ) )
            // InternalJavali.g:5271:1: ( 'null' )
            {
            // InternalJavali.g:5271:1: ( 'null' )
            // InternalJavali.g:5272:2: 'null'
            {
             before(grammarAccess.getNullAccess().getNullKeyword_1()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getNullAccess().getNullKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Null__Group__1__Impl"


    // $ANTLR start "rule__VarExpression__Group__0"
    // InternalJavali.g:5282:1: rule__VarExpression__Group__0 : rule__VarExpression__Group__0__Impl rule__VarExpression__Group__1 ;
    public final void rule__VarExpression__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5286:1: ( rule__VarExpression__Group__0__Impl rule__VarExpression__Group__1 )
            // InternalJavali.g:5287:2: rule__VarExpression__Group__0__Impl rule__VarExpression__Group__1
            {
            pushFollow(FOLLOW_44);
            rule__VarExpression__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group__0"


    // $ANTLR start "rule__VarExpression__Group__0__Impl"
    // InternalJavali.g:5294:1: rule__VarExpression__Group__0__Impl : ( ( rule__VarExpression__Group_0__0 ) ) ;
    public final void rule__VarExpression__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5298:1: ( ( ( rule__VarExpression__Group_0__0 ) ) )
            // InternalJavali.g:5299:1: ( ( rule__VarExpression__Group_0__0 ) )
            {
            // InternalJavali.g:5299:1: ( ( rule__VarExpression__Group_0__0 ) )
            // InternalJavali.g:5300:2: ( rule__VarExpression__Group_0__0 )
            {
             before(grammarAccess.getVarExpressionAccess().getGroup_0()); 
            // InternalJavali.g:5301:2: ( rule__VarExpression__Group_0__0 )
            // InternalJavali.g:5301:3: rule__VarExpression__Group_0__0
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_0__0();

            state._fsp--;


            }

             after(grammarAccess.getVarExpressionAccess().getGroup_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group__0__Impl"


    // $ANTLR start "rule__VarExpression__Group__1"
    // InternalJavali.g:5309:1: rule__VarExpression__Group__1 : rule__VarExpression__Group__1__Impl ;
    public final void rule__VarExpression__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5313:1: ( rule__VarExpression__Group__1__Impl )
            // InternalJavali.g:5314:2: rule__VarExpression__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group__1"


    // $ANTLR start "rule__VarExpression__Group__1__Impl"
    // InternalJavali.g:5320:1: rule__VarExpression__Group__1__Impl : ( ( rule__VarExpression__Group_1__0 )* ) ;
    public final void rule__VarExpression__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5324:1: ( ( ( rule__VarExpression__Group_1__0 )* ) )
            // InternalJavali.g:5325:1: ( ( rule__VarExpression__Group_1__0 )* )
            {
            // InternalJavali.g:5325:1: ( ( rule__VarExpression__Group_1__0 )* )
            // InternalJavali.g:5326:2: ( rule__VarExpression__Group_1__0 )*
            {
             before(grammarAccess.getVarExpressionAccess().getGroup_1()); 
            // InternalJavali.g:5327:2: ( rule__VarExpression__Group_1__0 )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==50) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalJavali.g:5327:3: rule__VarExpression__Group_1__0
            	    {
            	    pushFollow(FOLLOW_45);
            	    rule__VarExpression__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

             after(grammarAccess.getVarExpressionAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group__1__Impl"


    // $ANTLR start "rule__VarExpression__Group_0__0"
    // InternalJavali.g:5336:1: rule__VarExpression__Group_0__0 : rule__VarExpression__Group_0__0__Impl rule__VarExpression__Group_0__1 ;
    public final void rule__VarExpression__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5340:1: ( rule__VarExpression__Group_0__0__Impl rule__VarExpression__Group_0__1 )
            // InternalJavali.g:5341:2: rule__VarExpression__Group_0__0__Impl rule__VarExpression__Group_0__1
            {
            pushFollow(FOLLOW_46);
            rule__VarExpression__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0__0"


    // $ANTLR start "rule__VarExpression__Group_0__0__Impl"
    // InternalJavali.g:5348:1: rule__VarExpression__Group_0__0__Impl : ( ( rule__VarExpression__PartsAssignment_0_0 ) ) ;
    public final void rule__VarExpression__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5352:1: ( ( ( rule__VarExpression__PartsAssignment_0_0 ) ) )
            // InternalJavali.g:5353:1: ( ( rule__VarExpression__PartsAssignment_0_0 ) )
            {
            // InternalJavali.g:5353:1: ( ( rule__VarExpression__PartsAssignment_0_0 ) )
            // InternalJavali.g:5354:2: ( rule__VarExpression__PartsAssignment_0_0 )
            {
             before(grammarAccess.getVarExpressionAccess().getPartsAssignment_0_0()); 
            // InternalJavali.g:5355:2: ( rule__VarExpression__PartsAssignment_0_0 )
            // InternalJavali.g:5355:3: rule__VarExpression__PartsAssignment_0_0
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__PartsAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getVarExpressionAccess().getPartsAssignment_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0__0__Impl"


    // $ANTLR start "rule__VarExpression__Group_0__1"
    // InternalJavali.g:5363:1: rule__VarExpression__Group_0__1 : rule__VarExpression__Group_0__1__Impl ;
    public final void rule__VarExpression__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5367:1: ( rule__VarExpression__Group_0__1__Impl )
            // InternalJavali.g:5368:2: rule__VarExpression__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0__1"


    // $ANTLR start "rule__VarExpression__Group_0__1__Impl"
    // InternalJavali.g:5374:1: rule__VarExpression__Group_0__1__Impl : ( ( rule__VarExpression__Group_0_1__0 )* ) ;
    public final void rule__VarExpression__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5378:1: ( ( ( rule__VarExpression__Group_0_1__0 )* ) )
            // InternalJavali.g:5379:1: ( ( rule__VarExpression__Group_0_1__0 )* )
            {
            // InternalJavali.g:5379:1: ( ( rule__VarExpression__Group_0_1__0 )* )
            // InternalJavali.g:5380:2: ( rule__VarExpression__Group_0_1__0 )*
            {
             before(grammarAccess.getVarExpressionAccess().getGroup_0_1()); 
            // InternalJavali.g:5381:2: ( rule__VarExpression__Group_0_1__0 )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==48) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalJavali.g:5381:3: rule__VarExpression__Group_0_1__0
            	    {
            	    pushFollow(FOLLOW_47);
            	    rule__VarExpression__Group_0_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

             after(grammarAccess.getVarExpressionAccess().getGroup_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0__1__Impl"


    // $ANTLR start "rule__VarExpression__Group_0_1__0"
    // InternalJavali.g:5390:1: rule__VarExpression__Group_0_1__0 : rule__VarExpression__Group_0_1__0__Impl rule__VarExpression__Group_0_1__1 ;
    public final void rule__VarExpression__Group_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5394:1: ( rule__VarExpression__Group_0_1__0__Impl rule__VarExpression__Group_0_1__1 )
            // InternalJavali.g:5395:2: rule__VarExpression__Group_0_1__0__Impl rule__VarExpression__Group_0_1__1
            {
            pushFollow(FOLLOW_20);
            rule__VarExpression__Group_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_0_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0_1__0"


    // $ANTLR start "rule__VarExpression__Group_0_1__0__Impl"
    // InternalJavali.g:5402:1: rule__VarExpression__Group_0_1__0__Impl : ( '[' ) ;
    public final void rule__VarExpression__Group_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5406:1: ( ( '[' ) )
            // InternalJavali.g:5407:1: ( '[' )
            {
            // InternalJavali.g:5407:1: ( '[' )
            // InternalJavali.g:5408:2: '['
            {
             before(grammarAccess.getVarExpressionAccess().getLeftSquareBracketKeyword_0_1_0()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getVarExpressionAccess().getLeftSquareBracketKeyword_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0_1__0__Impl"


    // $ANTLR start "rule__VarExpression__Group_0_1__1"
    // InternalJavali.g:5417:1: rule__VarExpression__Group_0_1__1 : rule__VarExpression__Group_0_1__1__Impl rule__VarExpression__Group_0_1__2 ;
    public final void rule__VarExpression__Group_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5421:1: ( rule__VarExpression__Group_0_1__1__Impl rule__VarExpression__Group_0_1__2 )
            // InternalJavali.g:5422:2: rule__VarExpression__Group_0_1__1__Impl rule__VarExpression__Group_0_1__2
            {
            pushFollow(FOLLOW_48);
            rule__VarExpression__Group_0_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_0_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0_1__1"


    // $ANTLR start "rule__VarExpression__Group_0_1__1__Impl"
    // InternalJavali.g:5429:1: rule__VarExpression__Group_0_1__1__Impl : ( ( rule__VarExpression__ArrayIndexesAssignment_0_1_1 ) ) ;
    public final void rule__VarExpression__Group_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5433:1: ( ( ( rule__VarExpression__ArrayIndexesAssignment_0_1_1 ) ) )
            // InternalJavali.g:5434:1: ( ( rule__VarExpression__ArrayIndexesAssignment_0_1_1 ) )
            {
            // InternalJavali.g:5434:1: ( ( rule__VarExpression__ArrayIndexesAssignment_0_1_1 ) )
            // InternalJavali.g:5435:2: ( rule__VarExpression__ArrayIndexesAssignment_0_1_1 )
            {
             before(grammarAccess.getVarExpressionAccess().getArrayIndexesAssignment_0_1_1()); 
            // InternalJavali.g:5436:2: ( rule__VarExpression__ArrayIndexesAssignment_0_1_1 )
            // InternalJavali.g:5436:3: rule__VarExpression__ArrayIndexesAssignment_0_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__ArrayIndexesAssignment_0_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVarExpressionAccess().getArrayIndexesAssignment_0_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0_1__1__Impl"


    // $ANTLR start "rule__VarExpression__Group_0_1__2"
    // InternalJavali.g:5444:1: rule__VarExpression__Group_0_1__2 : rule__VarExpression__Group_0_1__2__Impl ;
    public final void rule__VarExpression__Group_0_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5448:1: ( rule__VarExpression__Group_0_1__2__Impl )
            // InternalJavali.g:5449:2: rule__VarExpression__Group_0_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_0_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0_1__2"


    // $ANTLR start "rule__VarExpression__Group_0_1__2__Impl"
    // InternalJavali.g:5455:1: rule__VarExpression__Group_0_1__2__Impl : ( ']' ) ;
    public final void rule__VarExpression__Group_0_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5459:1: ( ( ']' ) )
            // InternalJavali.g:5460:1: ( ']' )
            {
            // InternalJavali.g:5460:1: ( ']' )
            // InternalJavali.g:5461:2: ']'
            {
             before(grammarAccess.getVarExpressionAccess().getRightSquareBracketKeyword_0_1_2()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getVarExpressionAccess().getRightSquareBracketKeyword_0_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_0_1__2__Impl"


    // $ANTLR start "rule__VarExpression__Group_1__0"
    // InternalJavali.g:5471:1: rule__VarExpression__Group_1__0 : rule__VarExpression__Group_1__0__Impl rule__VarExpression__Group_1__1 ;
    public final void rule__VarExpression__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5475:1: ( rule__VarExpression__Group_1__0__Impl rule__VarExpression__Group_1__1 )
            // InternalJavali.g:5476:2: rule__VarExpression__Group_1__0__Impl rule__VarExpression__Group_1__1
            {
            pushFollow(FOLLOW_5);
            rule__VarExpression__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1__0"


    // $ANTLR start "rule__VarExpression__Group_1__0__Impl"
    // InternalJavali.g:5483:1: rule__VarExpression__Group_1__0__Impl : ( '.' ) ;
    public final void rule__VarExpression__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5487:1: ( ( '.' ) )
            // InternalJavali.g:5488:1: ( '.' )
            {
            // InternalJavali.g:5488:1: ( '.' )
            // InternalJavali.g:5489:2: '.'
            {
             before(grammarAccess.getVarExpressionAccess().getFullStopKeyword_1_0()); 
            match(input,50,FOLLOW_2); 
             after(grammarAccess.getVarExpressionAccess().getFullStopKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1__0__Impl"


    // $ANTLR start "rule__VarExpression__Group_1__1"
    // InternalJavali.g:5498:1: rule__VarExpression__Group_1__1 : rule__VarExpression__Group_1__1__Impl rule__VarExpression__Group_1__2 ;
    public final void rule__VarExpression__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5502:1: ( rule__VarExpression__Group_1__1__Impl rule__VarExpression__Group_1__2 )
            // InternalJavali.g:5503:2: rule__VarExpression__Group_1__1__Impl rule__VarExpression__Group_1__2
            {
            pushFollow(FOLLOW_46);
            rule__VarExpression__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1__1"


    // $ANTLR start "rule__VarExpression__Group_1__1__Impl"
    // InternalJavali.g:5510:1: rule__VarExpression__Group_1__1__Impl : ( ( rule__VarExpression__PartsAssignment_1_1 ) ) ;
    public final void rule__VarExpression__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5514:1: ( ( ( rule__VarExpression__PartsAssignment_1_1 ) ) )
            // InternalJavali.g:5515:1: ( ( rule__VarExpression__PartsAssignment_1_1 ) )
            {
            // InternalJavali.g:5515:1: ( ( rule__VarExpression__PartsAssignment_1_1 ) )
            // InternalJavali.g:5516:2: ( rule__VarExpression__PartsAssignment_1_1 )
            {
             before(grammarAccess.getVarExpressionAccess().getPartsAssignment_1_1()); 
            // InternalJavali.g:5517:2: ( rule__VarExpression__PartsAssignment_1_1 )
            // InternalJavali.g:5517:3: rule__VarExpression__PartsAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__PartsAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getVarExpressionAccess().getPartsAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1__1__Impl"


    // $ANTLR start "rule__VarExpression__Group_1__2"
    // InternalJavali.g:5525:1: rule__VarExpression__Group_1__2 : rule__VarExpression__Group_1__2__Impl ;
    public final void rule__VarExpression__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5529:1: ( rule__VarExpression__Group_1__2__Impl )
            // InternalJavali.g:5530:2: rule__VarExpression__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1__2"


    // $ANTLR start "rule__VarExpression__Group_1__2__Impl"
    // InternalJavali.g:5536:1: rule__VarExpression__Group_1__2__Impl : ( ( rule__VarExpression__Group_1_2__0 )* ) ;
    public final void rule__VarExpression__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5540:1: ( ( ( rule__VarExpression__Group_1_2__0 )* ) )
            // InternalJavali.g:5541:1: ( ( rule__VarExpression__Group_1_2__0 )* )
            {
            // InternalJavali.g:5541:1: ( ( rule__VarExpression__Group_1_2__0 )* )
            // InternalJavali.g:5542:2: ( rule__VarExpression__Group_1_2__0 )*
            {
             before(grammarAccess.getVarExpressionAccess().getGroup_1_2()); 
            // InternalJavali.g:5543:2: ( rule__VarExpression__Group_1_2__0 )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==48) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalJavali.g:5543:3: rule__VarExpression__Group_1_2__0
            	    {
            	    pushFollow(FOLLOW_47);
            	    rule__VarExpression__Group_1_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

             after(grammarAccess.getVarExpressionAccess().getGroup_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1__2__Impl"


    // $ANTLR start "rule__VarExpression__Group_1_2__0"
    // InternalJavali.g:5552:1: rule__VarExpression__Group_1_2__0 : rule__VarExpression__Group_1_2__0__Impl rule__VarExpression__Group_1_2__1 ;
    public final void rule__VarExpression__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5556:1: ( rule__VarExpression__Group_1_2__0__Impl rule__VarExpression__Group_1_2__1 )
            // InternalJavali.g:5557:2: rule__VarExpression__Group_1_2__0__Impl rule__VarExpression__Group_1_2__1
            {
            pushFollow(FOLLOW_20);
            rule__VarExpression__Group_1_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_1_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1_2__0"


    // $ANTLR start "rule__VarExpression__Group_1_2__0__Impl"
    // InternalJavali.g:5564:1: rule__VarExpression__Group_1_2__0__Impl : ( '[' ) ;
    public final void rule__VarExpression__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5568:1: ( ( '[' ) )
            // InternalJavali.g:5569:1: ( '[' )
            {
            // InternalJavali.g:5569:1: ( '[' )
            // InternalJavali.g:5570:2: '['
            {
             before(grammarAccess.getVarExpressionAccess().getLeftSquareBracketKeyword_1_2_0()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getVarExpressionAccess().getLeftSquareBracketKeyword_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1_2__0__Impl"


    // $ANTLR start "rule__VarExpression__Group_1_2__1"
    // InternalJavali.g:5579:1: rule__VarExpression__Group_1_2__1 : rule__VarExpression__Group_1_2__1__Impl rule__VarExpression__Group_1_2__2 ;
    public final void rule__VarExpression__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5583:1: ( rule__VarExpression__Group_1_2__1__Impl rule__VarExpression__Group_1_2__2 )
            // InternalJavali.g:5584:2: rule__VarExpression__Group_1_2__1__Impl rule__VarExpression__Group_1_2__2
            {
            pushFollow(FOLLOW_48);
            rule__VarExpression__Group_1_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_1_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1_2__1"


    // $ANTLR start "rule__VarExpression__Group_1_2__1__Impl"
    // InternalJavali.g:5591:1: rule__VarExpression__Group_1_2__1__Impl : ( ( rule__VarExpression__ArrayIndexesAssignment_1_2_1 ) ) ;
    public final void rule__VarExpression__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5595:1: ( ( ( rule__VarExpression__ArrayIndexesAssignment_1_2_1 ) ) )
            // InternalJavali.g:5596:1: ( ( rule__VarExpression__ArrayIndexesAssignment_1_2_1 ) )
            {
            // InternalJavali.g:5596:1: ( ( rule__VarExpression__ArrayIndexesAssignment_1_2_1 ) )
            // InternalJavali.g:5597:2: ( rule__VarExpression__ArrayIndexesAssignment_1_2_1 )
            {
             before(grammarAccess.getVarExpressionAccess().getArrayIndexesAssignment_1_2_1()); 
            // InternalJavali.g:5598:2: ( rule__VarExpression__ArrayIndexesAssignment_1_2_1 )
            // InternalJavali.g:5598:3: rule__VarExpression__ArrayIndexesAssignment_1_2_1
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__ArrayIndexesAssignment_1_2_1();

            state._fsp--;


            }

             after(grammarAccess.getVarExpressionAccess().getArrayIndexesAssignment_1_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1_2__1__Impl"


    // $ANTLR start "rule__VarExpression__Group_1_2__2"
    // InternalJavali.g:5606:1: rule__VarExpression__Group_1_2__2 : rule__VarExpression__Group_1_2__2__Impl ;
    public final void rule__VarExpression__Group_1_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5610:1: ( rule__VarExpression__Group_1_2__2__Impl )
            // InternalJavali.g:5611:2: rule__VarExpression__Group_1_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__VarExpression__Group_1_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1_2__2"


    // $ANTLR start "rule__VarExpression__Group_1_2__2__Impl"
    // InternalJavali.g:5617:1: rule__VarExpression__Group_1_2__2__Impl : ( ']' ) ;
    public final void rule__VarExpression__Group_1_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5621:1: ( ( ']' ) )
            // InternalJavali.g:5622:1: ( ']' )
            {
            // InternalJavali.g:5622:1: ( ']' )
            // InternalJavali.g:5623:2: ']'
            {
             before(grammarAccess.getVarExpressionAccess().getRightSquareBracketKeyword_1_2_2()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getVarExpressionAccess().getRightSquareBracketKeyword_1_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__Group_1_2__2__Impl"


    // $ANTLR start "rule__ProcCall__Group__0"
    // InternalJavali.g:5633:1: rule__ProcCall__Group__0 : rule__ProcCall__Group__0__Impl rule__ProcCall__Group__1 ;
    public final void rule__ProcCall__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5637:1: ( rule__ProcCall__Group__0__Impl rule__ProcCall__Group__1 )
            // InternalJavali.g:5638:2: rule__ProcCall__Group__0__Impl rule__ProcCall__Group__1
            {
            pushFollow(FOLLOW_13);
            rule__ProcCall__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProcCall__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__0"


    // $ANTLR start "rule__ProcCall__Group__0__Impl"
    // InternalJavali.g:5645:1: rule__ProcCall__Group__0__Impl : ( ( rule__ProcCall__IdAssignment_0 ) ) ;
    public final void rule__ProcCall__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5649:1: ( ( ( rule__ProcCall__IdAssignment_0 ) ) )
            // InternalJavali.g:5650:1: ( ( rule__ProcCall__IdAssignment_0 ) )
            {
            // InternalJavali.g:5650:1: ( ( rule__ProcCall__IdAssignment_0 ) )
            // InternalJavali.g:5651:2: ( rule__ProcCall__IdAssignment_0 )
            {
             before(grammarAccess.getProcCallAccess().getIdAssignment_0()); 
            // InternalJavali.g:5652:2: ( rule__ProcCall__IdAssignment_0 )
            // InternalJavali.g:5652:3: rule__ProcCall__IdAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ProcCall__IdAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getProcCallAccess().getIdAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__0__Impl"


    // $ANTLR start "rule__ProcCall__Group__1"
    // InternalJavali.g:5660:1: rule__ProcCall__Group__1 : rule__ProcCall__Group__1__Impl rule__ProcCall__Group__2 ;
    public final void rule__ProcCall__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5664:1: ( rule__ProcCall__Group__1__Impl rule__ProcCall__Group__2 )
            // InternalJavali.g:5665:2: rule__ProcCall__Group__1__Impl rule__ProcCall__Group__2
            {
            pushFollow(FOLLOW_49);
            rule__ProcCall__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProcCall__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__1"


    // $ANTLR start "rule__ProcCall__Group__1__Impl"
    // InternalJavali.g:5672:1: rule__ProcCall__Group__1__Impl : ( '(' ) ;
    public final void rule__ProcCall__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5676:1: ( ( '(' ) )
            // InternalJavali.g:5677:1: ( '(' )
            {
            // InternalJavali.g:5677:1: ( '(' )
            // InternalJavali.g:5678:2: '('
            {
             before(grammarAccess.getProcCallAccess().getLeftParenthesisKeyword_1()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getProcCallAccess().getLeftParenthesisKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__1__Impl"


    // $ANTLR start "rule__ProcCall__Group__2"
    // InternalJavali.g:5687:1: rule__ProcCall__Group__2 : rule__ProcCall__Group__2__Impl rule__ProcCall__Group__3 ;
    public final void rule__ProcCall__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5691:1: ( rule__ProcCall__Group__2__Impl rule__ProcCall__Group__3 )
            // InternalJavali.g:5692:2: rule__ProcCall__Group__2__Impl rule__ProcCall__Group__3
            {
            pushFollow(FOLLOW_49);
            rule__ProcCall__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProcCall__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__2"


    // $ANTLR start "rule__ProcCall__Group__2__Impl"
    // InternalJavali.g:5699:1: rule__ProcCall__Group__2__Impl : ( ( rule__ProcCall__Group_2__0 )? ) ;
    public final void rule__ProcCall__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5703:1: ( ( ( rule__ProcCall__Group_2__0 )? ) )
            // InternalJavali.g:5704:1: ( ( rule__ProcCall__Group_2__0 )? )
            {
            // InternalJavali.g:5704:1: ( ( rule__ProcCall__Group_2__0 )? )
            // InternalJavali.g:5705:2: ( rule__ProcCall__Group_2__0 )?
            {
             before(grammarAccess.getProcCallAccess().getGroup_2()); 
            // InternalJavali.g:5706:2: ( rule__ProcCall__Group_2__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=RULE_PRIMITIVE_VALUE && LA36_0<=RULE_ID)||LA36_0==30||(LA36_0>=46 && LA36_0<=47)||LA36_0==51) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // InternalJavali.g:5706:3: rule__ProcCall__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ProcCall__Group_2__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getProcCallAccess().getGroup_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__2__Impl"


    // $ANTLR start "rule__ProcCall__Group__3"
    // InternalJavali.g:5714:1: rule__ProcCall__Group__3 : rule__ProcCall__Group__3__Impl ;
    public final void rule__ProcCall__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5718:1: ( rule__ProcCall__Group__3__Impl )
            // InternalJavali.g:5719:2: rule__ProcCall__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProcCall__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__3"


    // $ANTLR start "rule__ProcCall__Group__3__Impl"
    // InternalJavali.g:5725:1: rule__ProcCall__Group__3__Impl : ( ')' ) ;
    public final void rule__ProcCall__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5729:1: ( ( ')' ) )
            // InternalJavali.g:5730:1: ( ')' )
            {
            // InternalJavali.g:5730:1: ( ')' )
            // InternalJavali.g:5731:2: ')'
            {
             before(grammarAccess.getProcCallAccess().getRightParenthesisKeyword_3()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getProcCallAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group__3__Impl"


    // $ANTLR start "rule__ProcCall__Group_2__0"
    // InternalJavali.g:5741:1: rule__ProcCall__Group_2__0 : rule__ProcCall__Group_2__0__Impl rule__ProcCall__Group_2__1 ;
    public final void rule__ProcCall__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5745:1: ( rule__ProcCall__Group_2__0__Impl rule__ProcCall__Group_2__1 )
            // InternalJavali.g:5746:2: rule__ProcCall__Group_2__0__Impl rule__ProcCall__Group_2__1
            {
            pushFollow(FOLLOW_15);
            rule__ProcCall__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProcCall__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2__0"


    // $ANTLR start "rule__ProcCall__Group_2__0__Impl"
    // InternalJavali.g:5753:1: rule__ProcCall__Group_2__0__Impl : ( ( rule__ProcCall__ArgsAssignment_2_0 ) ) ;
    public final void rule__ProcCall__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5757:1: ( ( ( rule__ProcCall__ArgsAssignment_2_0 ) ) )
            // InternalJavali.g:5758:1: ( ( rule__ProcCall__ArgsAssignment_2_0 ) )
            {
            // InternalJavali.g:5758:1: ( ( rule__ProcCall__ArgsAssignment_2_0 ) )
            // InternalJavali.g:5759:2: ( rule__ProcCall__ArgsAssignment_2_0 )
            {
             before(grammarAccess.getProcCallAccess().getArgsAssignment_2_0()); 
            // InternalJavali.g:5760:2: ( rule__ProcCall__ArgsAssignment_2_0 )
            // InternalJavali.g:5760:3: rule__ProcCall__ArgsAssignment_2_0
            {
            pushFollow(FOLLOW_2);
            rule__ProcCall__ArgsAssignment_2_0();

            state._fsp--;


            }

             after(grammarAccess.getProcCallAccess().getArgsAssignment_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2__0__Impl"


    // $ANTLR start "rule__ProcCall__Group_2__1"
    // InternalJavali.g:5768:1: rule__ProcCall__Group_2__1 : rule__ProcCall__Group_2__1__Impl ;
    public final void rule__ProcCall__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5772:1: ( rule__ProcCall__Group_2__1__Impl )
            // InternalJavali.g:5773:2: rule__ProcCall__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProcCall__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2__1"


    // $ANTLR start "rule__ProcCall__Group_2__1__Impl"
    // InternalJavali.g:5779:1: rule__ProcCall__Group_2__1__Impl : ( ( rule__ProcCall__Group_2_1__0 )* ) ;
    public final void rule__ProcCall__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5783:1: ( ( ( rule__ProcCall__Group_2_1__0 )* ) )
            // InternalJavali.g:5784:1: ( ( rule__ProcCall__Group_2_1__0 )* )
            {
            // InternalJavali.g:5784:1: ( ( rule__ProcCall__Group_2_1__0 )* )
            // InternalJavali.g:5785:2: ( rule__ProcCall__Group_2_1__0 )*
            {
             before(grammarAccess.getProcCallAccess().getGroup_2_1()); 
            // InternalJavali.g:5786:2: ( rule__ProcCall__Group_2_1__0 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==32) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalJavali.g:5786:3: rule__ProcCall__Group_2_1__0
            	    {
            	    pushFollow(FOLLOW_16);
            	    rule__ProcCall__Group_2_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

             after(grammarAccess.getProcCallAccess().getGroup_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2__1__Impl"


    // $ANTLR start "rule__ProcCall__Group_2_1__0"
    // InternalJavali.g:5795:1: rule__ProcCall__Group_2_1__0 : rule__ProcCall__Group_2_1__0__Impl rule__ProcCall__Group_2_1__1 ;
    public final void rule__ProcCall__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5799:1: ( rule__ProcCall__Group_2_1__0__Impl rule__ProcCall__Group_2_1__1 )
            // InternalJavali.g:5800:2: rule__ProcCall__Group_2_1__0__Impl rule__ProcCall__Group_2_1__1
            {
            pushFollow(FOLLOW_20);
            rule__ProcCall__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ProcCall__Group_2_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2_1__0"


    // $ANTLR start "rule__ProcCall__Group_2_1__0__Impl"
    // InternalJavali.g:5807:1: rule__ProcCall__Group_2_1__0__Impl : ( ',' ) ;
    public final void rule__ProcCall__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5811:1: ( ( ',' ) )
            // InternalJavali.g:5812:1: ( ',' )
            {
            // InternalJavali.g:5812:1: ( ',' )
            // InternalJavali.g:5813:2: ','
            {
             before(grammarAccess.getProcCallAccess().getCommaKeyword_2_1_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getProcCallAccess().getCommaKeyword_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2_1__0__Impl"


    // $ANTLR start "rule__ProcCall__Group_2_1__1"
    // InternalJavali.g:5822:1: rule__ProcCall__Group_2_1__1 : rule__ProcCall__Group_2_1__1__Impl ;
    public final void rule__ProcCall__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5826:1: ( rule__ProcCall__Group_2_1__1__Impl )
            // InternalJavali.g:5827:2: rule__ProcCall__Group_2_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ProcCall__Group_2_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2_1__1"


    // $ANTLR start "rule__ProcCall__Group_2_1__1__Impl"
    // InternalJavali.g:5833:1: rule__ProcCall__Group_2_1__1__Impl : ( ( rule__ProcCall__ArgsAssignment_2_1_1 ) ) ;
    public final void rule__ProcCall__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5837:1: ( ( ( rule__ProcCall__ArgsAssignment_2_1_1 ) ) )
            // InternalJavali.g:5838:1: ( ( rule__ProcCall__ArgsAssignment_2_1_1 ) )
            {
            // InternalJavali.g:5838:1: ( ( rule__ProcCall__ArgsAssignment_2_1_1 ) )
            // InternalJavali.g:5839:2: ( rule__ProcCall__ArgsAssignment_2_1_1 )
            {
             before(grammarAccess.getProcCallAccess().getArgsAssignment_2_1_1()); 
            // InternalJavali.g:5840:2: ( rule__ProcCall__ArgsAssignment_2_1_1 )
            // InternalJavali.g:5840:3: rule__ProcCall__ArgsAssignment_2_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ProcCall__ArgsAssignment_2_1_1();

            state._fsp--;


            }

             after(grammarAccess.getProcCallAccess().getArgsAssignment_2_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__Group_2_1__1__Impl"


    // $ANTLR start "rule__Type__Group__0"
    // InternalJavali.g:5849:1: rule__Type__Group__0 : rule__Type__Group__0__Impl rule__Type__Group__1 ;
    public final void rule__Type__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5853:1: ( rule__Type__Group__0__Impl rule__Type__Group__1 )
            // InternalJavali.g:5854:2: rule__Type__Group__0__Impl rule__Type__Group__1
            {
            pushFollow(FOLLOW_46);
            rule__Type__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Type__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group__0"


    // $ANTLR start "rule__Type__Group__0__Impl"
    // InternalJavali.g:5861:1: rule__Type__Group__0__Impl : ( ( rule__Type__IdAssignment_0 ) ) ;
    public final void rule__Type__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5865:1: ( ( ( rule__Type__IdAssignment_0 ) ) )
            // InternalJavali.g:5866:1: ( ( rule__Type__IdAssignment_0 ) )
            {
            // InternalJavali.g:5866:1: ( ( rule__Type__IdAssignment_0 ) )
            // InternalJavali.g:5867:2: ( rule__Type__IdAssignment_0 )
            {
             before(grammarAccess.getTypeAccess().getIdAssignment_0()); 
            // InternalJavali.g:5868:2: ( rule__Type__IdAssignment_0 )
            // InternalJavali.g:5868:3: rule__Type__IdAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Type__IdAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getTypeAccess().getIdAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group__0__Impl"


    // $ANTLR start "rule__Type__Group__1"
    // InternalJavali.g:5876:1: rule__Type__Group__1 : rule__Type__Group__1__Impl ;
    public final void rule__Type__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5880:1: ( rule__Type__Group__1__Impl )
            // InternalJavali.g:5881:2: rule__Type__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Type__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group__1"


    // $ANTLR start "rule__Type__Group__1__Impl"
    // InternalJavali.g:5887:1: rule__Type__Group__1__Impl : ( ( rule__Type__Group_1__0 )* ) ;
    public final void rule__Type__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5891:1: ( ( ( rule__Type__Group_1__0 )* ) )
            // InternalJavali.g:5892:1: ( ( rule__Type__Group_1__0 )* )
            {
            // InternalJavali.g:5892:1: ( ( rule__Type__Group_1__0 )* )
            // InternalJavali.g:5893:2: ( rule__Type__Group_1__0 )*
            {
             before(grammarAccess.getTypeAccess().getGroup_1()); 
            // InternalJavali.g:5894:2: ( rule__Type__Group_1__0 )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==48) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalJavali.g:5894:3: rule__Type__Group_1__0
            	    {
            	    pushFollow(FOLLOW_47);
            	    rule__Type__Group_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);

             after(grammarAccess.getTypeAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group__1__Impl"


    // $ANTLR start "rule__Type__Group_1__0"
    // InternalJavali.g:5903:1: rule__Type__Group_1__0 : rule__Type__Group_1__0__Impl rule__Type__Group_1__1 ;
    public final void rule__Type__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5907:1: ( rule__Type__Group_1__0__Impl rule__Type__Group_1__1 )
            // InternalJavali.g:5908:2: rule__Type__Group_1__0__Impl rule__Type__Group_1__1
            {
            pushFollow(FOLLOW_48);
            rule__Type__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Type__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group_1__0"


    // $ANTLR start "rule__Type__Group_1__0__Impl"
    // InternalJavali.g:5915:1: rule__Type__Group_1__0__Impl : ( ( rule__Type__ArrayDimsAssignment_1_0 ) ) ;
    public final void rule__Type__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5919:1: ( ( ( rule__Type__ArrayDimsAssignment_1_0 ) ) )
            // InternalJavali.g:5920:1: ( ( rule__Type__ArrayDimsAssignment_1_0 ) )
            {
            // InternalJavali.g:5920:1: ( ( rule__Type__ArrayDimsAssignment_1_0 ) )
            // InternalJavali.g:5921:2: ( rule__Type__ArrayDimsAssignment_1_0 )
            {
             before(grammarAccess.getTypeAccess().getArrayDimsAssignment_1_0()); 
            // InternalJavali.g:5922:2: ( rule__Type__ArrayDimsAssignment_1_0 )
            // InternalJavali.g:5922:3: rule__Type__ArrayDimsAssignment_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Type__ArrayDimsAssignment_1_0();

            state._fsp--;


            }

             after(grammarAccess.getTypeAccess().getArrayDimsAssignment_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group_1__0__Impl"


    // $ANTLR start "rule__Type__Group_1__1"
    // InternalJavali.g:5930:1: rule__Type__Group_1__1 : rule__Type__Group_1__1__Impl ;
    public final void rule__Type__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5934:1: ( rule__Type__Group_1__1__Impl )
            // InternalJavali.g:5935:2: rule__Type__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Type__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group_1__1"


    // $ANTLR start "rule__Type__Group_1__1__Impl"
    // InternalJavali.g:5941:1: rule__Type__Group_1__1__Impl : ( ']' ) ;
    public final void rule__Type__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5945:1: ( ( ']' ) )
            // InternalJavali.g:5946:1: ( ']' )
            {
            // InternalJavali.g:5946:1: ( ']' )
            // InternalJavali.g:5947:2: ']'
            {
             before(grammarAccess.getTypeAccess().getRightSquareBracketKeyword_1_1()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getTypeAccess().getRightSquareBracketKeyword_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__Group_1__1__Impl"


    // $ANTLR start "rule__NewArray__Group__0"
    // InternalJavali.g:5957:1: rule__NewArray__Group__0 : rule__NewArray__Group__0__Impl rule__NewArray__Group__1 ;
    public final void rule__NewArray__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5961:1: ( rule__NewArray__Group__0__Impl rule__NewArray__Group__1 )
            // InternalJavali.g:5962:2: rule__NewArray__Group__0__Impl rule__NewArray__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__NewArray__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NewArray__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group__0"


    // $ANTLR start "rule__NewArray__Group__0__Impl"
    // InternalJavali.g:5969:1: rule__NewArray__Group__0__Impl : ( 'new' ) ;
    public final void rule__NewArray__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5973:1: ( ( 'new' ) )
            // InternalJavali.g:5974:1: ( 'new' )
            {
            // InternalJavali.g:5974:1: ( 'new' )
            // InternalJavali.g:5975:2: 'new'
            {
             before(grammarAccess.getNewArrayAccess().getNewKeyword_0()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getNewArrayAccess().getNewKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group__0__Impl"


    // $ANTLR start "rule__NewArray__Group__1"
    // InternalJavali.g:5984:1: rule__NewArray__Group__1 : rule__NewArray__Group__1__Impl rule__NewArray__Group__2 ;
    public final void rule__NewArray__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:5988:1: ( rule__NewArray__Group__1__Impl rule__NewArray__Group__2 )
            // InternalJavali.g:5989:2: rule__NewArray__Group__1__Impl rule__NewArray__Group__2
            {
            pushFollow(FOLLOW_46);
            rule__NewArray__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NewArray__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group__1"


    // $ANTLR start "rule__NewArray__Group__1__Impl"
    // InternalJavali.g:5996:1: rule__NewArray__Group__1__Impl : ( ( rule__NewArray__TypeAssignment_1 ) ) ;
    public final void rule__NewArray__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6000:1: ( ( ( rule__NewArray__TypeAssignment_1 ) ) )
            // InternalJavali.g:6001:1: ( ( rule__NewArray__TypeAssignment_1 ) )
            {
            // InternalJavali.g:6001:1: ( ( rule__NewArray__TypeAssignment_1 ) )
            // InternalJavali.g:6002:2: ( rule__NewArray__TypeAssignment_1 )
            {
             before(grammarAccess.getNewArrayAccess().getTypeAssignment_1()); 
            // InternalJavali.g:6003:2: ( rule__NewArray__TypeAssignment_1 )
            // InternalJavali.g:6003:3: rule__NewArray__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__NewArray__TypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getNewArrayAccess().getTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group__1__Impl"


    // $ANTLR start "rule__NewArray__Group__2"
    // InternalJavali.g:6011:1: rule__NewArray__Group__2 : rule__NewArray__Group__2__Impl ;
    public final void rule__NewArray__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6015:1: ( rule__NewArray__Group__2__Impl )
            // InternalJavali.g:6016:2: rule__NewArray__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NewArray__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group__2"


    // $ANTLR start "rule__NewArray__Group__2__Impl"
    // InternalJavali.g:6022:1: rule__NewArray__Group__2__Impl : ( ( ( rule__NewArray__Group_2__0 ) ) ( ( rule__NewArray__Group_2__0 )* ) ) ;
    public final void rule__NewArray__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6026:1: ( ( ( ( rule__NewArray__Group_2__0 ) ) ( ( rule__NewArray__Group_2__0 )* ) ) )
            // InternalJavali.g:6027:1: ( ( ( rule__NewArray__Group_2__0 ) ) ( ( rule__NewArray__Group_2__0 )* ) )
            {
            // InternalJavali.g:6027:1: ( ( ( rule__NewArray__Group_2__0 ) ) ( ( rule__NewArray__Group_2__0 )* ) )
            // InternalJavali.g:6028:2: ( ( rule__NewArray__Group_2__0 ) ) ( ( rule__NewArray__Group_2__0 )* )
            {
            // InternalJavali.g:6028:2: ( ( rule__NewArray__Group_2__0 ) )
            // InternalJavali.g:6029:3: ( rule__NewArray__Group_2__0 )
            {
             before(grammarAccess.getNewArrayAccess().getGroup_2()); 
            // InternalJavali.g:6030:3: ( rule__NewArray__Group_2__0 )
            // InternalJavali.g:6030:4: rule__NewArray__Group_2__0
            {
            pushFollow(FOLLOW_47);
            rule__NewArray__Group_2__0();

            state._fsp--;


            }

             after(grammarAccess.getNewArrayAccess().getGroup_2()); 

            }

            // InternalJavali.g:6033:2: ( ( rule__NewArray__Group_2__0 )* )
            // InternalJavali.g:6034:3: ( rule__NewArray__Group_2__0 )*
            {
             before(grammarAccess.getNewArrayAccess().getGroup_2()); 
            // InternalJavali.g:6035:3: ( rule__NewArray__Group_2__0 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==48) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalJavali.g:6035:4: rule__NewArray__Group_2__0
            	    {
            	    pushFollow(FOLLOW_47);
            	    rule__NewArray__Group_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

             after(grammarAccess.getNewArrayAccess().getGroup_2()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group__2__Impl"


    // $ANTLR start "rule__NewArray__Group_2__0"
    // InternalJavali.g:6045:1: rule__NewArray__Group_2__0 : rule__NewArray__Group_2__0__Impl rule__NewArray__Group_2__1 ;
    public final void rule__NewArray__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6049:1: ( rule__NewArray__Group_2__0__Impl rule__NewArray__Group_2__1 )
            // InternalJavali.g:6050:2: rule__NewArray__Group_2__0__Impl rule__NewArray__Group_2__1
            {
            pushFollow(FOLLOW_20);
            rule__NewArray__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NewArray__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group_2__0"


    // $ANTLR start "rule__NewArray__Group_2__0__Impl"
    // InternalJavali.g:6057:1: rule__NewArray__Group_2__0__Impl : ( '[' ) ;
    public final void rule__NewArray__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6061:1: ( ( '[' ) )
            // InternalJavali.g:6062:1: ( '[' )
            {
            // InternalJavali.g:6062:1: ( '[' )
            // InternalJavali.g:6063:2: '['
            {
             before(grammarAccess.getNewArrayAccess().getLeftSquareBracketKeyword_2_0()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getNewArrayAccess().getLeftSquareBracketKeyword_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group_2__0__Impl"


    // $ANTLR start "rule__NewArray__Group_2__1"
    // InternalJavali.g:6072:1: rule__NewArray__Group_2__1 : rule__NewArray__Group_2__1__Impl rule__NewArray__Group_2__2 ;
    public final void rule__NewArray__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6076:1: ( rule__NewArray__Group_2__1__Impl rule__NewArray__Group_2__2 )
            // InternalJavali.g:6077:2: rule__NewArray__Group_2__1__Impl rule__NewArray__Group_2__2
            {
            pushFollow(FOLLOW_48);
            rule__NewArray__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NewArray__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group_2__1"


    // $ANTLR start "rule__NewArray__Group_2__1__Impl"
    // InternalJavali.g:6084:1: rule__NewArray__Group_2__1__Impl : ( ( rule__NewArray__ArrayDimsAssignment_2_1 ) ) ;
    public final void rule__NewArray__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6088:1: ( ( ( rule__NewArray__ArrayDimsAssignment_2_1 ) ) )
            // InternalJavali.g:6089:1: ( ( rule__NewArray__ArrayDimsAssignment_2_1 ) )
            {
            // InternalJavali.g:6089:1: ( ( rule__NewArray__ArrayDimsAssignment_2_1 ) )
            // InternalJavali.g:6090:2: ( rule__NewArray__ArrayDimsAssignment_2_1 )
            {
             before(grammarAccess.getNewArrayAccess().getArrayDimsAssignment_2_1()); 
            // InternalJavali.g:6091:2: ( rule__NewArray__ArrayDimsAssignment_2_1 )
            // InternalJavali.g:6091:3: rule__NewArray__ArrayDimsAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__NewArray__ArrayDimsAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getNewArrayAccess().getArrayDimsAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group_2__1__Impl"


    // $ANTLR start "rule__NewArray__Group_2__2"
    // InternalJavali.g:6099:1: rule__NewArray__Group_2__2 : rule__NewArray__Group_2__2__Impl ;
    public final void rule__NewArray__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6103:1: ( rule__NewArray__Group_2__2__Impl )
            // InternalJavali.g:6104:2: rule__NewArray__Group_2__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NewArray__Group_2__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group_2__2"


    // $ANTLR start "rule__NewArray__Group_2__2__Impl"
    // InternalJavali.g:6110:1: rule__NewArray__Group_2__2__Impl : ( ']' ) ;
    public final void rule__NewArray__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6114:1: ( ( ']' ) )
            // InternalJavali.g:6115:1: ( ']' )
            {
            // InternalJavali.g:6115:1: ( ']' )
            // InternalJavali.g:6116:2: ']'
            {
             before(grammarAccess.getNewArrayAccess().getRightSquareBracketKeyword_2_2()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getNewArrayAccess().getRightSquareBracketKeyword_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__Group_2__2__Impl"


    // $ANTLR start "rule__NewObject__Group__0"
    // InternalJavali.g:6126:1: rule__NewObject__Group__0 : rule__NewObject__Group__0__Impl rule__NewObject__Group__1 ;
    public final void rule__NewObject__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6130:1: ( rule__NewObject__Group__0__Impl rule__NewObject__Group__1 )
            // InternalJavali.g:6131:2: rule__NewObject__Group__0__Impl rule__NewObject__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__NewObject__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NewObject__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__0"


    // $ANTLR start "rule__NewObject__Group__0__Impl"
    // InternalJavali.g:6138:1: rule__NewObject__Group__0__Impl : ( 'new' ) ;
    public final void rule__NewObject__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6142:1: ( ( 'new' ) )
            // InternalJavali.g:6143:1: ( 'new' )
            {
            // InternalJavali.g:6143:1: ( 'new' )
            // InternalJavali.g:6144:2: 'new'
            {
             before(grammarAccess.getNewObjectAccess().getNewKeyword_0()); 
            match(input,51,FOLLOW_2); 
             after(grammarAccess.getNewObjectAccess().getNewKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__0__Impl"


    // $ANTLR start "rule__NewObject__Group__1"
    // InternalJavali.g:6153:1: rule__NewObject__Group__1 : rule__NewObject__Group__1__Impl rule__NewObject__Group__2 ;
    public final void rule__NewObject__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6157:1: ( rule__NewObject__Group__1__Impl rule__NewObject__Group__2 )
            // InternalJavali.g:6158:2: rule__NewObject__Group__1__Impl rule__NewObject__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__NewObject__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NewObject__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__1"


    // $ANTLR start "rule__NewObject__Group__1__Impl"
    // InternalJavali.g:6165:1: rule__NewObject__Group__1__Impl : ( ( rule__NewObject__TypeAssignment_1 ) ) ;
    public final void rule__NewObject__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6169:1: ( ( ( rule__NewObject__TypeAssignment_1 ) ) )
            // InternalJavali.g:6170:1: ( ( rule__NewObject__TypeAssignment_1 ) )
            {
            // InternalJavali.g:6170:1: ( ( rule__NewObject__TypeAssignment_1 ) )
            // InternalJavali.g:6171:2: ( rule__NewObject__TypeAssignment_1 )
            {
             before(grammarAccess.getNewObjectAccess().getTypeAssignment_1()); 
            // InternalJavali.g:6172:2: ( rule__NewObject__TypeAssignment_1 )
            // InternalJavali.g:6172:3: rule__NewObject__TypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__NewObject__TypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getNewObjectAccess().getTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__1__Impl"


    // $ANTLR start "rule__NewObject__Group__2"
    // InternalJavali.g:6180:1: rule__NewObject__Group__2 : rule__NewObject__Group__2__Impl rule__NewObject__Group__3 ;
    public final void rule__NewObject__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6184:1: ( rule__NewObject__Group__2__Impl rule__NewObject__Group__3 )
            // InternalJavali.g:6185:2: rule__NewObject__Group__2__Impl rule__NewObject__Group__3
            {
            pushFollow(FOLLOW_23);
            rule__NewObject__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NewObject__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__2"


    // $ANTLR start "rule__NewObject__Group__2__Impl"
    // InternalJavali.g:6192:1: rule__NewObject__Group__2__Impl : ( '(' ) ;
    public final void rule__NewObject__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6196:1: ( ( '(' ) )
            // InternalJavali.g:6197:1: ( '(' )
            {
            // InternalJavali.g:6197:1: ( '(' )
            // InternalJavali.g:6198:2: '('
            {
             before(grammarAccess.getNewObjectAccess().getLeftParenthesisKeyword_2()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getNewObjectAccess().getLeftParenthesisKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__2__Impl"


    // $ANTLR start "rule__NewObject__Group__3"
    // InternalJavali.g:6207:1: rule__NewObject__Group__3 : rule__NewObject__Group__3__Impl ;
    public final void rule__NewObject__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6211:1: ( rule__NewObject__Group__3__Impl )
            // InternalJavali.g:6212:2: rule__NewObject__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NewObject__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__3"


    // $ANTLR start "rule__NewObject__Group__3__Impl"
    // InternalJavali.g:6218:1: rule__NewObject__Group__3__Impl : ( ')' ) ;
    public final void rule__NewObject__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6222:1: ( ( ')' ) )
            // InternalJavali.g:6223:1: ( ')' )
            {
            // InternalJavali.g:6223:1: ( ')' )
            // InternalJavali.g:6224:2: ')'
            {
             before(grammarAccess.getNewObjectAccess().getRightParenthesisKeyword_3()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getNewObjectAccess().getRightParenthesisKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__Group__3__Impl"


    // $ANTLR start "rule__Module__ConstantsAssignment_0"
    // InternalJavali.g:6234:1: rule__Module__ConstantsAssignment_0 : ( ruleConstant ) ;
    public final void rule__Module__ConstantsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6238:1: ( ( ruleConstant ) )
            // InternalJavali.g:6239:2: ( ruleConstant )
            {
            // InternalJavali.g:6239:2: ( ruleConstant )
            // InternalJavali.g:6240:3: ruleConstant
            {
             before(grammarAccess.getModuleAccess().getConstantsConstantParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleConstant();

            state._fsp--;

             after(grammarAccess.getModuleAccess().getConstantsConstantParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Module__ConstantsAssignment_0"


    // $ANTLR start "rule__Module__RecordsAssignment_1"
    // InternalJavali.g:6249:1: rule__Module__RecordsAssignment_1 : ( ruleRecord ) ;
    public final void rule__Module__RecordsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6253:1: ( ( ruleRecord ) )
            // InternalJavali.g:6254:2: ( ruleRecord )
            {
            // InternalJavali.g:6254:2: ( ruleRecord )
            // InternalJavali.g:6255:3: ruleRecord
            {
             before(grammarAccess.getModuleAccess().getRecordsRecordParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleRecord();

            state._fsp--;

             after(grammarAccess.getModuleAccess().getRecordsRecordParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Module__RecordsAssignment_1"


    // $ANTLR start "rule__Module__ProceduresAssignment_2"
    // InternalJavali.g:6264:1: rule__Module__ProceduresAssignment_2 : ( ruleProcedure ) ;
    public final void rule__Module__ProceduresAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6268:1: ( ( ruleProcedure ) )
            // InternalJavali.g:6269:2: ( ruleProcedure )
            {
            // InternalJavali.g:6269:2: ( ruleProcedure )
            // InternalJavali.g:6270:3: ruleProcedure
            {
             before(grammarAccess.getModuleAccess().getProceduresProcedureParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleProcedure();

            state._fsp--;

             after(grammarAccess.getModuleAccess().getProceduresProcedureParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Module__ProceduresAssignment_2"


    // $ANTLR start "rule__Constant__StaticAssignment_0"
    // InternalJavali.g:6279:1: rule__Constant__StaticAssignment_0 : ( ( 'static' ) ) ;
    public final void rule__Constant__StaticAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6283:1: ( ( ( 'static' ) ) )
            // InternalJavali.g:6284:2: ( ( 'static' ) )
            {
            // InternalJavali.g:6284:2: ( ( 'static' ) )
            // InternalJavali.g:6285:3: ( 'static' )
            {
             before(grammarAccess.getConstantAccess().getStaticStaticKeyword_0_0()); 
            // InternalJavali.g:6286:3: ( 'static' )
            // InternalJavali.g:6287:4: 'static'
            {
             before(grammarAccess.getConstantAccess().getStaticStaticKeyword_0_0()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getConstantAccess().getStaticStaticKeyword_0_0()); 

            }

             after(grammarAccess.getConstantAccess().getStaticStaticKeyword_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__StaticAssignment_0"


    // $ANTLR start "rule__Constant__TypeAssignment_2"
    // InternalJavali.g:6298:1: rule__Constant__TypeAssignment_2 : ( ruleType ) ;
    public final void rule__Constant__TypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6302:1: ( ( ruleType ) )
            // InternalJavali.g:6303:2: ( ruleType )
            {
            // InternalJavali.g:6303:2: ( ruleType )
            // InternalJavali.g:6304:3: ruleType
            {
             before(grammarAccess.getConstantAccess().getTypeTypeParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;

             after(grammarAccess.getConstantAccess().getTypeTypeParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__TypeAssignment_2"


    // $ANTLR start "rule__Constant__IdAssignment_3"
    // InternalJavali.g:6313:1: rule__Constant__IdAssignment_3 : ( ruleIdentifier ) ;
    public final void rule__Constant__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6317:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:6318:2: ( ruleIdentifier )
            {
            // InternalJavali.g:6318:2: ( ruleIdentifier )
            // InternalJavali.g:6319:3: ruleIdentifier
            {
             before(grammarAccess.getConstantAccess().getIdIdentifierParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getConstantAccess().getIdIdentifierParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__IdAssignment_3"


    // $ANTLR start "rule__Constant__ValueAssignment_5"
    // InternalJavali.g:6328:1: rule__Constant__ValueAssignment_5 : ( ruleLiteral ) ;
    public final void rule__Constant__ValueAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6332:1: ( ( ruleLiteral ) )
            // InternalJavali.g:6333:2: ( ruleLiteral )
            {
            // InternalJavali.g:6333:2: ( ruleLiteral )
            // InternalJavali.g:6334:3: ruleLiteral
            {
             before(grammarAccess.getConstantAccess().getValueLiteralParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleLiteral();

            state._fsp--;

             after(grammarAccess.getConstantAccess().getValueLiteralParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Constant__ValueAssignment_5"


    // $ANTLR start "rule__Record__IdAssignment_1"
    // InternalJavali.g:6343:1: rule__Record__IdAssignment_1 : ( ruleIdentifier ) ;
    public final void rule__Record__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6347:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:6348:2: ( ruleIdentifier )
            {
            // InternalJavali.g:6348:2: ( ruleIdentifier )
            // InternalJavali.g:6349:3: ruleIdentifier
            {
             before(grammarAccess.getRecordAccess().getIdIdentifierParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getRecordAccess().getIdIdentifierParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__IdAssignment_1"


    // $ANTLR start "rule__Record__FieldsAssignment_3_0"
    // InternalJavali.g:6358:1: rule__Record__FieldsAssignment_3_0 : ( ruleVarDeclaration ) ;
    public final void rule__Record__FieldsAssignment_3_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6362:1: ( ( ruleVarDeclaration ) )
            // InternalJavali.g:6363:2: ( ruleVarDeclaration )
            {
            // InternalJavali.g:6363:2: ( ruleVarDeclaration )
            // InternalJavali.g:6364:3: ruleVarDeclaration
            {
             before(grammarAccess.getRecordAccess().getFieldsVarDeclarationParserRuleCall_3_0_0()); 
            pushFollow(FOLLOW_2);
            ruleVarDeclaration();

            state._fsp--;

             after(grammarAccess.getRecordAccess().getFieldsVarDeclarationParserRuleCall_3_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Record__FieldsAssignment_3_0"


    // $ANTLR start "rule__Procedure__CommentAssignment_0"
    // InternalJavali.g:6373:1: rule__Procedure__CommentAssignment_0 : ( RULE_ML_COMMENT_DOC ) ;
    public final void rule__Procedure__CommentAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6377:1: ( ( RULE_ML_COMMENT_DOC ) )
            // InternalJavali.g:6378:2: ( RULE_ML_COMMENT_DOC )
            {
            // InternalJavali.g:6378:2: ( RULE_ML_COMMENT_DOC )
            // InternalJavali.g:6379:3: RULE_ML_COMMENT_DOC
            {
             before(grammarAccess.getProcedureAccess().getCommentML_COMMENT_DOCTerminalRuleCall_0_0()); 
            match(input,RULE_ML_COMMENT_DOC,FOLLOW_2); 
             after(grammarAccess.getProcedureAccess().getCommentML_COMMENT_DOCTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__CommentAssignment_0"


    // $ANTLR start "rule__Procedure__StaticAssignment_1"
    // InternalJavali.g:6388:1: rule__Procedure__StaticAssignment_1 : ( ( 'static' ) ) ;
    public final void rule__Procedure__StaticAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6392:1: ( ( ( 'static' ) ) )
            // InternalJavali.g:6393:2: ( ( 'static' ) )
            {
            // InternalJavali.g:6393:2: ( ( 'static' ) )
            // InternalJavali.g:6394:3: ( 'static' )
            {
             before(grammarAccess.getProcedureAccess().getStaticStaticKeyword_1_0()); 
            // InternalJavali.g:6395:3: ( 'static' )
            // InternalJavali.g:6396:4: 'static'
            {
             before(grammarAccess.getProcedureAccess().getStaticStaticKeyword_1_0()); 
            match(input,52,FOLLOW_2); 
             after(grammarAccess.getProcedureAccess().getStaticStaticKeyword_1_0()); 

            }

             after(grammarAccess.getProcedureAccess().getStaticStaticKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__StaticAssignment_1"


    // $ANTLR start "rule__Procedure__RetTypeAssignment_2_0"
    // InternalJavali.g:6407:1: rule__Procedure__RetTypeAssignment_2_0 : ( ruleType ) ;
    public final void rule__Procedure__RetTypeAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6411:1: ( ( ruleType ) )
            // InternalJavali.g:6412:2: ( ruleType )
            {
            // InternalJavali.g:6412:2: ( ruleType )
            // InternalJavali.g:6413:3: ruleType
            {
             before(grammarAccess.getProcedureAccess().getRetTypeTypeParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;

             after(grammarAccess.getProcedureAccess().getRetTypeTypeParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__RetTypeAssignment_2_0"


    // $ANTLR start "rule__Procedure__VoidAssignment_2_1"
    // InternalJavali.g:6422:1: rule__Procedure__VoidAssignment_2_1 : ( ( 'void' ) ) ;
    public final void rule__Procedure__VoidAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6426:1: ( ( ( 'void' ) ) )
            // InternalJavali.g:6427:2: ( ( 'void' ) )
            {
            // InternalJavali.g:6427:2: ( ( 'void' ) )
            // InternalJavali.g:6428:3: ( 'void' )
            {
             before(grammarAccess.getProcedureAccess().getVoidVoidKeyword_2_1_0()); 
            // InternalJavali.g:6429:3: ( 'void' )
            // InternalJavali.g:6430:4: 'void'
            {
             before(grammarAccess.getProcedureAccess().getVoidVoidKeyword_2_1_0()); 
            match(input,53,FOLLOW_2); 
             after(grammarAccess.getProcedureAccess().getVoidVoidKeyword_2_1_0()); 

            }

             after(grammarAccess.getProcedureAccess().getVoidVoidKeyword_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__VoidAssignment_2_1"


    // $ANTLR start "rule__Procedure__IdAssignment_3"
    // InternalJavali.g:6441:1: rule__Procedure__IdAssignment_3 : ( ruleIdentifier ) ;
    public final void rule__Procedure__IdAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6445:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:6446:2: ( ruleIdentifier )
            {
            // InternalJavali.g:6446:2: ( ruleIdentifier )
            // InternalJavali.g:6447:3: ruleIdentifier
            {
             before(grammarAccess.getProcedureAccess().getIdIdentifierParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getProcedureAccess().getIdIdentifierParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__IdAssignment_3"


    // $ANTLR start "rule__Procedure__ParamsAssignment_5_0"
    // InternalJavali.g:6456:1: rule__Procedure__ParamsAssignment_5_0 : ( ruleVarDeclaration ) ;
    public final void rule__Procedure__ParamsAssignment_5_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6460:1: ( ( ruleVarDeclaration ) )
            // InternalJavali.g:6461:2: ( ruleVarDeclaration )
            {
            // InternalJavali.g:6461:2: ( ruleVarDeclaration )
            // InternalJavali.g:6462:3: ruleVarDeclaration
            {
             before(grammarAccess.getProcedureAccess().getParamsVarDeclarationParserRuleCall_5_0_0()); 
            pushFollow(FOLLOW_2);
            ruleVarDeclaration();

            state._fsp--;

             after(grammarAccess.getProcedureAccess().getParamsVarDeclarationParserRuleCall_5_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__ParamsAssignment_5_0"


    // $ANTLR start "rule__Procedure__ParamsAssignment_5_1_1"
    // InternalJavali.g:6471:1: rule__Procedure__ParamsAssignment_5_1_1 : ( ruleVarDeclaration ) ;
    public final void rule__Procedure__ParamsAssignment_5_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6475:1: ( ( ruleVarDeclaration ) )
            // InternalJavali.g:6476:2: ( ruleVarDeclaration )
            {
            // InternalJavali.g:6476:2: ( ruleVarDeclaration )
            // InternalJavali.g:6477:3: ruleVarDeclaration
            {
             before(grammarAccess.getProcedureAccess().getParamsVarDeclarationParserRuleCall_5_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleVarDeclaration();

            state._fsp--;

             after(grammarAccess.getProcedureAccess().getParamsVarDeclarationParserRuleCall_5_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__ParamsAssignment_5_1_1"


    // $ANTLR start "rule__Procedure__BodyAssignment_7"
    // InternalJavali.g:6486:1: rule__Procedure__BodyAssignment_7 : ( ruleBlock ) ;
    public final void rule__Procedure__BodyAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6490:1: ( ( ruleBlock ) )
            // InternalJavali.g:6491:2: ( ruleBlock )
            {
            // InternalJavali.g:6491:2: ( ruleBlock )
            // InternalJavali.g:6492:3: ruleBlock
            {
             before(grammarAccess.getProcedureAccess().getBodyBlockParserRuleCall_7_0()); 
            pushFollow(FOLLOW_2);
            ruleBlock();

            state._fsp--;

             after(grammarAccess.getProcedureAccess().getBodyBlockParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Procedure__BodyAssignment_7"


    // $ANTLR start "rule__Block__StatementsAssignment_2"
    // InternalJavali.g:6501:1: rule__Block__StatementsAssignment_2 : ( ruleStatement ) ;
    public final void rule__Block__StatementsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6505:1: ( ( ruleStatement ) )
            // InternalJavali.g:6506:2: ( ruleStatement )
            {
            // InternalJavali.g:6506:2: ( ruleStatement )
            // InternalJavali.g:6507:3: ruleStatement
            {
             before(grammarAccess.getBlockAccess().getStatementsStatementParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleStatement();

            state._fsp--;

             after(grammarAccess.getBlockAccess().getStatementsStatementParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Block__StatementsAssignment_2"


    // $ANTLR start "rule__Return__ExpAssignment_2"
    // InternalJavali.g:6516:1: rule__Return__ExpAssignment_2 : ( ruleExpression ) ;
    public final void rule__Return__ExpAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6520:1: ( ( ruleExpression ) )
            // InternalJavali.g:6521:2: ( ruleExpression )
            {
            // InternalJavali.g:6521:2: ( ruleExpression )
            // InternalJavali.g:6522:3: ruleExpression
            {
             before(grammarAccess.getReturnAccess().getExpExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getReturnAccess().getExpExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Return__ExpAssignment_2"


    // $ANTLR start "rule__VarDeclaration__TypeAssignment_0"
    // InternalJavali.g:6531:1: rule__VarDeclaration__TypeAssignment_0 : ( ruleType ) ;
    public final void rule__VarDeclaration__TypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6535:1: ( ( ruleType ) )
            // InternalJavali.g:6536:2: ( ruleType )
            {
            // InternalJavali.g:6536:2: ( ruleType )
            // InternalJavali.g:6537:3: ruleType
            {
             before(grammarAccess.getVarDeclarationAccess().getTypeTypeParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;

             after(grammarAccess.getVarDeclarationAccess().getTypeTypeParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclaration__TypeAssignment_0"


    // $ANTLR start "rule__VarDeclaration__IdAssignment_1"
    // InternalJavali.g:6546:1: rule__VarDeclaration__IdAssignment_1 : ( ruleIdentifier ) ;
    public final void rule__VarDeclaration__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6550:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:6551:2: ( ruleIdentifier )
            {
            // InternalJavali.g:6551:2: ( ruleIdentifier )
            // InternalJavali.g:6552:3: ruleIdentifier
            {
             before(grammarAccess.getVarDeclarationAccess().getIdIdentifierParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getVarDeclarationAccess().getIdIdentifierParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclaration__IdAssignment_1"


    // $ANTLR start "rule__VarDeclarationAssign__TypeAssignment_0"
    // InternalJavali.g:6561:1: rule__VarDeclarationAssign__TypeAssignment_0 : ( ruleType ) ;
    public final void rule__VarDeclarationAssign__TypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6565:1: ( ( ruleType ) )
            // InternalJavali.g:6566:2: ( ruleType )
            {
            // InternalJavali.g:6566:2: ( ruleType )
            // InternalJavali.g:6567:3: ruleType
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getTypeTypeParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleType();

            state._fsp--;

             after(grammarAccess.getVarDeclarationAssignAccess().getTypeTypeParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__TypeAssignment_0"


    // $ANTLR start "rule__VarDeclarationAssign__IdAssignment_1"
    // InternalJavali.g:6576:1: rule__VarDeclarationAssign__IdAssignment_1 : ( ruleIdentifier ) ;
    public final void rule__VarDeclarationAssign__IdAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6580:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:6581:2: ( ruleIdentifier )
            {
            // InternalJavali.g:6581:2: ( ruleIdentifier )
            // InternalJavali.g:6582:3: ruleIdentifier
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getIdIdentifierParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getVarDeclarationAssignAccess().getIdIdentifierParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__IdAssignment_1"


    // $ANTLR start "rule__VarDeclarationAssign__InitAssignment_2_1"
    // InternalJavali.g:6591:1: rule__VarDeclarationAssign__InitAssignment_2_1 : ( ruleExpression ) ;
    public final void rule__VarDeclarationAssign__InitAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6595:1: ( ( ruleExpression ) )
            // InternalJavali.g:6596:2: ( ruleExpression )
            {
            // InternalJavali.g:6596:2: ( ruleExpression )
            // InternalJavali.g:6597:3: ruleExpression
            {
             before(grammarAccess.getVarDeclarationAssignAccess().getInitExpressionParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getVarDeclarationAssignAccess().getInitExpressionParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarDeclarationAssign__InitAssignment_2_1"


    // $ANTLR start "rule__VarAssign__VarAssignment_0"
    // InternalJavali.g:6606:1: rule__VarAssign__VarAssignment_0 : ( ruleVarExpression ) ;
    public final void rule__VarAssign__VarAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6610:1: ( ( ruleVarExpression ) )
            // InternalJavali.g:6611:2: ( ruleVarExpression )
            {
            // InternalJavali.g:6611:2: ( ruleVarExpression )
            // InternalJavali.g:6612:3: ruleVarExpression
            {
             before(grammarAccess.getVarAssignAccess().getVarVarExpressionParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleVarExpression();

            state._fsp--;

             after(grammarAccess.getVarAssignAccess().getVarVarExpressionParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__VarAssignment_0"


    // $ANTLR start "rule__VarAssign__ExpAssignment_2"
    // InternalJavali.g:6621:1: rule__VarAssign__ExpAssignment_2 : ( ruleExpression ) ;
    public final void rule__VarAssign__ExpAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6625:1: ( ( ruleExpression ) )
            // InternalJavali.g:6626:2: ( ruleExpression )
            {
            // InternalJavali.g:6626:2: ( ruleExpression )
            // InternalJavali.g:6627:3: ruleExpression
            {
             before(grammarAccess.getVarAssignAccess().getExpExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getVarAssignAccess().getExpExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarAssign__ExpAssignment_2"


    // $ANTLR start "rule__IfElse__GuardAssignment_2"
    // InternalJavali.g:6636:1: rule__IfElse__GuardAssignment_2 : ( ruleExpression ) ;
    public final void rule__IfElse__GuardAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6640:1: ( ( ruleExpression ) )
            // InternalJavali.g:6641:2: ( ruleExpression )
            {
            // InternalJavali.g:6641:2: ( ruleExpression )
            // InternalJavali.g:6642:3: ruleExpression
            {
             before(grammarAccess.getIfElseAccess().getGuardExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getIfElseAccess().getGuardExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__GuardAssignment_2"


    // $ANTLR start "rule__IfElse__SelectionBlockAssignment_4"
    // InternalJavali.g:6651:1: rule__IfElse__SelectionBlockAssignment_4 : ( ruleBlock ) ;
    public final void rule__IfElse__SelectionBlockAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6655:1: ( ( ruleBlock ) )
            // InternalJavali.g:6656:2: ( ruleBlock )
            {
            // InternalJavali.g:6656:2: ( ruleBlock )
            // InternalJavali.g:6657:3: ruleBlock
            {
             before(grammarAccess.getIfElseAccess().getSelectionBlockBlockParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleBlock();

            state._fsp--;

             after(grammarAccess.getIfElseAccess().getSelectionBlockBlockParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__SelectionBlockAssignment_4"


    // $ANTLR start "rule__IfElse__AlternativeBlockAssignment_5_1"
    // InternalJavali.g:6666:1: rule__IfElse__AlternativeBlockAssignment_5_1 : ( ruleBlock ) ;
    public final void rule__IfElse__AlternativeBlockAssignment_5_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6670:1: ( ( ruleBlock ) )
            // InternalJavali.g:6671:2: ( ruleBlock )
            {
            // InternalJavali.g:6671:2: ( ruleBlock )
            // InternalJavali.g:6672:3: ruleBlock
            {
             before(grammarAccess.getIfElseAccess().getAlternativeBlockBlockParserRuleCall_5_1_0()); 
            pushFollow(FOLLOW_2);
            ruleBlock();

            state._fsp--;

             after(grammarAccess.getIfElseAccess().getAlternativeBlockBlockParserRuleCall_5_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__IfElse__AlternativeBlockAssignment_5_1"


    // $ANTLR start "rule__While__GuardAssignment_2"
    // InternalJavali.g:6681:1: rule__While__GuardAssignment_2 : ( ruleExpression ) ;
    public final void rule__While__GuardAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6685:1: ( ( ruleExpression ) )
            // InternalJavali.g:6686:2: ( ruleExpression )
            {
            // InternalJavali.g:6686:2: ( ruleExpression )
            // InternalJavali.g:6687:3: ruleExpression
            {
             before(grammarAccess.getWhileAccess().getGuardExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getWhileAccess().getGuardExpressionParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__GuardAssignment_2"


    // $ANTLR start "rule__While__BlockAssignment_4"
    // InternalJavali.g:6696:1: rule__While__BlockAssignment_4 : ( ruleBlock ) ;
    public final void rule__While__BlockAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6700:1: ( ( ruleBlock ) )
            // InternalJavali.g:6701:2: ( ruleBlock )
            {
            // InternalJavali.g:6701:2: ( ruleBlock )
            // InternalJavali.g:6702:3: ruleBlock
            {
             before(grammarAccess.getWhileAccess().getBlockBlockParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleBlock();

            state._fsp--;

             after(grammarAccess.getWhileAccess().getBlockBlockParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__While__BlockAssignment_4"


    // $ANTLR start "rule__For__InitStatementsAssignment_2_0"
    // InternalJavali.g:6711:1: rule__For__InitStatementsAssignment_2_0 : ( ruleForStatementInit ) ;
    public final void rule__For__InitStatementsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6715:1: ( ( ruleForStatementInit ) )
            // InternalJavali.g:6716:2: ( ruleForStatementInit )
            {
            // InternalJavali.g:6716:2: ( ruleForStatementInit )
            // InternalJavali.g:6717:3: ruleForStatementInit
            {
             before(grammarAccess.getForAccess().getInitStatementsForStatementInitParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleForStatementInit();

            state._fsp--;

             after(grammarAccess.getForAccess().getInitStatementsForStatementInitParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__InitStatementsAssignment_2_0"


    // $ANTLR start "rule__For__InitStatementsAssignment_2_1_1"
    // InternalJavali.g:6726:1: rule__For__InitStatementsAssignment_2_1_1 : ( ruleForStatementInit ) ;
    public final void rule__For__InitStatementsAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6730:1: ( ( ruleForStatementInit ) )
            // InternalJavali.g:6731:2: ( ruleForStatementInit )
            {
            // InternalJavali.g:6731:2: ( ruleForStatementInit )
            // InternalJavali.g:6732:3: ruleForStatementInit
            {
             before(grammarAccess.getForAccess().getInitStatementsForStatementInitParserRuleCall_2_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleForStatementInit();

            state._fsp--;

             after(grammarAccess.getForAccess().getInitStatementsForStatementInitParserRuleCall_2_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__InitStatementsAssignment_2_1_1"


    // $ANTLR start "rule__For__GuardAssignment_4"
    // InternalJavali.g:6741:1: rule__For__GuardAssignment_4 : ( ruleExpression ) ;
    public final void rule__For__GuardAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6745:1: ( ( ruleExpression ) )
            // InternalJavali.g:6746:2: ( ruleExpression )
            {
            // InternalJavali.g:6746:2: ( ruleExpression )
            // InternalJavali.g:6747:3: ruleExpression
            {
             before(grammarAccess.getForAccess().getGuardExpressionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getForAccess().getGuardExpressionParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__GuardAssignment_4"


    // $ANTLR start "rule__For__ProgressStatementsAssignment_6_0"
    // InternalJavali.g:6756:1: rule__For__ProgressStatementsAssignment_6_0 : ( ruleForStatement ) ;
    public final void rule__For__ProgressStatementsAssignment_6_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6760:1: ( ( ruleForStatement ) )
            // InternalJavali.g:6761:2: ( ruleForStatement )
            {
            // InternalJavali.g:6761:2: ( ruleForStatement )
            // InternalJavali.g:6762:3: ruleForStatement
            {
             before(grammarAccess.getForAccess().getProgressStatementsForStatementParserRuleCall_6_0_0()); 
            pushFollow(FOLLOW_2);
            ruleForStatement();

            state._fsp--;

             after(grammarAccess.getForAccess().getProgressStatementsForStatementParserRuleCall_6_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__ProgressStatementsAssignment_6_0"


    // $ANTLR start "rule__For__ProgressStatementsAssignment_6_1_1"
    // InternalJavali.g:6771:1: rule__For__ProgressStatementsAssignment_6_1_1 : ( ruleForStatement ) ;
    public final void rule__For__ProgressStatementsAssignment_6_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6775:1: ( ( ruleForStatement ) )
            // InternalJavali.g:6776:2: ( ruleForStatement )
            {
            // InternalJavali.g:6776:2: ( ruleForStatement )
            // InternalJavali.g:6777:3: ruleForStatement
            {
             before(grammarAccess.getForAccess().getProgressStatementsForStatementParserRuleCall_6_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleForStatement();

            state._fsp--;

             after(grammarAccess.getForAccess().getProgressStatementsForStatementParserRuleCall_6_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__ProgressStatementsAssignment_6_1_1"


    // $ANTLR start "rule__For__BlockAssignment_8"
    // InternalJavali.g:6786:1: rule__For__BlockAssignment_8 : ( ruleBlock ) ;
    public final void rule__For__BlockAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6790:1: ( ( ruleBlock ) )
            // InternalJavali.g:6791:2: ( ruleBlock )
            {
            // InternalJavali.g:6791:2: ( ruleBlock )
            // InternalJavali.g:6792:3: ruleBlock
            {
             before(grammarAccess.getForAccess().getBlockBlockParserRuleCall_8_0()); 
            pushFollow(FOLLOW_2);
            ruleBlock();

            state._fsp--;

             after(grammarAccess.getForAccess().getBlockBlockParserRuleCall_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__For__BlockAssignment_8"


    // $ANTLR start "rule__DoWhile__BlockAssignment_1"
    // InternalJavali.g:6801:1: rule__DoWhile__BlockAssignment_1 : ( ruleBlock ) ;
    public final void rule__DoWhile__BlockAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6805:1: ( ( ruleBlock ) )
            // InternalJavali.g:6806:2: ( ruleBlock )
            {
            // InternalJavali.g:6806:2: ( ruleBlock )
            // InternalJavali.g:6807:3: ruleBlock
            {
             before(grammarAccess.getDoWhileAccess().getBlockBlockParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleBlock();

            state._fsp--;

             after(grammarAccess.getDoWhileAccess().getBlockBlockParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__BlockAssignment_1"


    // $ANTLR start "rule__DoWhile__GuardAssignment_4"
    // InternalJavali.g:6816:1: rule__DoWhile__GuardAssignment_4 : ( ruleExpression ) ;
    public final void rule__DoWhile__GuardAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6820:1: ( ( ruleExpression ) )
            // InternalJavali.g:6821:2: ( ruleExpression )
            {
            // InternalJavali.g:6821:2: ( ruleExpression )
            // InternalJavali.g:6822:3: ruleExpression
            {
             before(grammarAccess.getDoWhileAccess().getGuardExpressionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getDoWhileAccess().getGuardExpressionParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__DoWhile__GuardAssignment_4"


    // $ANTLR start "rule__Increment__IdAssignment_0"
    // InternalJavali.g:6831:1: rule__Increment__IdAssignment_0 : ( ruleIdentifier ) ;
    public final void rule__Increment__IdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6835:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:6836:2: ( ruleIdentifier )
            {
            // InternalJavali.g:6836:2: ( ruleIdentifier )
            // InternalJavali.g:6837:3: ruleIdentifier
            {
             before(grammarAccess.getIncrementAccess().getIdIdentifierParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getIncrementAccess().getIdIdentifierParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Increment__IdAssignment_0"


    // $ANTLR start "rule__Decrement__IdAssignment_0"
    // InternalJavali.g:6846:1: rule__Decrement__IdAssignment_0 : ( ruleIdentifier ) ;
    public final void rule__Decrement__IdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6850:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:6851:2: ( ruleIdentifier )
            {
            // InternalJavali.g:6851:2: ( ruleIdentifier )
            // InternalJavali.g:6852:3: ruleIdentifier
            {
             before(grammarAccess.getDecrementAccess().getIdIdentifierParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getDecrementAccess().getIdIdentifierParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Decrement__IdAssignment_0"


    // $ANTLR start "rule__Or__RightAssignment_1_2"
    // InternalJavali.g:6861:1: rule__Or__RightAssignment_1_2 : ( ruleXor ) ;
    public final void rule__Or__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6865:1: ( ( ruleXor ) )
            // InternalJavali.g:6866:2: ( ruleXor )
            {
            // InternalJavali.g:6866:2: ( ruleXor )
            // InternalJavali.g:6867:3: ruleXor
            {
             before(grammarAccess.getOrAccess().getRightXorParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleXor();

            state._fsp--;

             after(grammarAccess.getOrAccess().getRightXorParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Or__RightAssignment_1_2"


    // $ANTLR start "rule__Xor__RightAssignment_1_2"
    // InternalJavali.g:6876:1: rule__Xor__RightAssignment_1_2 : ( ruleAnd ) ;
    public final void rule__Xor__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6880:1: ( ( ruleAnd ) )
            // InternalJavali.g:6881:2: ( ruleAnd )
            {
            // InternalJavali.g:6881:2: ( ruleAnd )
            // InternalJavali.g:6882:3: ruleAnd
            {
             before(grammarAccess.getXorAccess().getRightAndParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAnd();

            state._fsp--;

             after(grammarAccess.getXorAccess().getRightAndParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Xor__RightAssignment_1_2"


    // $ANTLR start "rule__And__RightAssignment_1_2"
    // InternalJavali.g:6891:1: rule__And__RightAssignment_1_2 : ( ruleEquality ) ;
    public final void rule__And__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6895:1: ( ( ruleEquality ) )
            // InternalJavali.g:6896:2: ( ruleEquality )
            {
            // InternalJavali.g:6896:2: ( ruleEquality )
            // InternalJavali.g:6897:3: ruleEquality
            {
             before(grammarAccess.getAndAccess().getRightEqualityParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleEquality();

            state._fsp--;

             after(grammarAccess.getAndAccess().getRightEqualityParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__And__RightAssignment_1_2"


    // $ANTLR start "rule__Equality__OperatorAssignment_1_1"
    // InternalJavali.g:6906:1: rule__Equality__OperatorAssignment_1_1 : ( ( rule__Equality__OperatorAlternatives_1_1_0 ) ) ;
    public final void rule__Equality__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6910:1: ( ( ( rule__Equality__OperatorAlternatives_1_1_0 ) ) )
            // InternalJavali.g:6911:2: ( ( rule__Equality__OperatorAlternatives_1_1_0 ) )
            {
            // InternalJavali.g:6911:2: ( ( rule__Equality__OperatorAlternatives_1_1_0 ) )
            // InternalJavali.g:6912:3: ( rule__Equality__OperatorAlternatives_1_1_0 )
            {
             before(grammarAccess.getEqualityAccess().getOperatorAlternatives_1_1_0()); 
            // InternalJavali.g:6913:3: ( rule__Equality__OperatorAlternatives_1_1_0 )
            // InternalJavali.g:6913:4: rule__Equality__OperatorAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Equality__OperatorAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getEqualityAccess().getOperatorAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__OperatorAssignment_1_1"


    // $ANTLR start "rule__Equality__RightAssignment_1_2"
    // InternalJavali.g:6921:1: rule__Equality__RightAssignment_1_2 : ( ruleRelation ) ;
    public final void rule__Equality__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6925:1: ( ( ruleRelation ) )
            // InternalJavali.g:6926:2: ( ruleRelation )
            {
            // InternalJavali.g:6926:2: ( ruleRelation )
            // InternalJavali.g:6927:3: ruleRelation
            {
             before(grammarAccess.getEqualityAccess().getRightRelationParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleRelation();

            state._fsp--;

             after(grammarAccess.getEqualityAccess().getRightRelationParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Equality__RightAssignment_1_2"


    // $ANTLR start "rule__Relation__OperatorAssignment_1_1"
    // InternalJavali.g:6936:1: rule__Relation__OperatorAssignment_1_1 : ( ( rule__Relation__OperatorAlternatives_1_1_0 ) ) ;
    public final void rule__Relation__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6940:1: ( ( ( rule__Relation__OperatorAlternatives_1_1_0 ) ) )
            // InternalJavali.g:6941:2: ( ( rule__Relation__OperatorAlternatives_1_1_0 ) )
            {
            // InternalJavali.g:6941:2: ( ( rule__Relation__OperatorAlternatives_1_1_0 ) )
            // InternalJavali.g:6942:3: ( rule__Relation__OperatorAlternatives_1_1_0 )
            {
             before(grammarAccess.getRelationAccess().getOperatorAlternatives_1_1_0()); 
            // InternalJavali.g:6943:3: ( rule__Relation__OperatorAlternatives_1_1_0 )
            // InternalJavali.g:6943:4: rule__Relation__OperatorAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Relation__OperatorAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getOperatorAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__OperatorAssignment_1_1"


    // $ANTLR start "rule__Relation__RightAssignment_1_2"
    // InternalJavali.g:6951:1: rule__Relation__RightAssignment_1_2 : ( ruleAddition ) ;
    public final void rule__Relation__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6955:1: ( ( ruleAddition ) )
            // InternalJavali.g:6956:2: ( ruleAddition )
            {
            // InternalJavali.g:6956:2: ( ruleAddition )
            // InternalJavali.g:6957:3: ruleAddition
            {
             before(grammarAccess.getRelationAccess().getRightAdditionParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getRelationAccess().getRightAdditionParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__RightAssignment_1_2"


    // $ANTLR start "rule__Addition__OperatorAssignment_1_1"
    // InternalJavali.g:6966:1: rule__Addition__OperatorAssignment_1_1 : ( ( rule__Addition__OperatorAlternatives_1_1_0 ) ) ;
    public final void rule__Addition__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6970:1: ( ( ( rule__Addition__OperatorAlternatives_1_1_0 ) ) )
            // InternalJavali.g:6971:2: ( ( rule__Addition__OperatorAlternatives_1_1_0 ) )
            {
            // InternalJavali.g:6971:2: ( ( rule__Addition__OperatorAlternatives_1_1_0 ) )
            // InternalJavali.g:6972:3: ( rule__Addition__OperatorAlternatives_1_1_0 )
            {
             before(grammarAccess.getAdditionAccess().getOperatorAlternatives_1_1_0()); 
            // InternalJavali.g:6973:3: ( rule__Addition__OperatorAlternatives_1_1_0 )
            // InternalJavali.g:6973:4: rule__Addition__OperatorAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Addition__OperatorAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getOperatorAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__OperatorAssignment_1_1"


    // $ANTLR start "rule__Addition__RightAssignment_1_2"
    // InternalJavali.g:6981:1: rule__Addition__RightAssignment_1_2 : ( ruleMultiplication ) ;
    public final void rule__Addition__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:6985:1: ( ( ruleMultiplication ) )
            // InternalJavali.g:6986:2: ( ruleMultiplication )
            {
            // InternalJavali.g:6986:2: ( ruleMultiplication )
            // InternalJavali.g:6987:3: ruleMultiplication
            {
             before(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getAdditionAccess().getRightMultiplicationParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__RightAssignment_1_2"


    // $ANTLR start "rule__Multiplication__OperatorAssignment_1_1"
    // InternalJavali.g:6996:1: rule__Multiplication__OperatorAssignment_1_1 : ( ( rule__Multiplication__OperatorAlternatives_1_1_0 ) ) ;
    public final void rule__Multiplication__OperatorAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7000:1: ( ( ( rule__Multiplication__OperatorAlternatives_1_1_0 ) ) )
            // InternalJavali.g:7001:2: ( ( rule__Multiplication__OperatorAlternatives_1_1_0 ) )
            {
            // InternalJavali.g:7001:2: ( ( rule__Multiplication__OperatorAlternatives_1_1_0 ) )
            // InternalJavali.g:7002:3: ( rule__Multiplication__OperatorAlternatives_1_1_0 )
            {
             before(grammarAccess.getMultiplicationAccess().getOperatorAlternatives_1_1_0()); 
            // InternalJavali.g:7003:3: ( rule__Multiplication__OperatorAlternatives_1_1_0 )
            // InternalJavali.g:7003:4: rule__Multiplication__OperatorAlternatives_1_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__OperatorAlternatives_1_1_0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getOperatorAlternatives_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__OperatorAssignment_1_1"


    // $ANTLR start "rule__Multiplication__RightAssignment_1_2"
    // InternalJavali.g:7011:1: rule__Multiplication__RightAssignment_1_2 : ( rulePrimary ) ;
    public final void rule__Multiplication__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7015:1: ( ( rulePrimary ) )
            // InternalJavali.g:7016:2: ( rulePrimary )
            {
            // InternalJavali.g:7016:2: ( rulePrimary )
            // InternalJavali.g:7017:3: rulePrimary
            {
             before(grammarAccess.getMultiplicationAccess().getRightPrimaryParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            rulePrimary();

            state._fsp--;

             after(grammarAccess.getMultiplicationAccess().getRightPrimaryParserRuleCall_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__RightAssignment_1_2"


    // $ANTLR start "rule__Literal__ValueAssignment"
    // InternalJavali.g:7026:1: rule__Literal__ValueAssignment : ( RULE_PRIMITIVE_VALUE ) ;
    public final void rule__Literal__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7030:1: ( ( RULE_PRIMITIVE_VALUE ) )
            // InternalJavali.g:7031:2: ( RULE_PRIMITIVE_VALUE )
            {
            // InternalJavali.g:7031:2: ( RULE_PRIMITIVE_VALUE )
            // InternalJavali.g:7032:3: RULE_PRIMITIVE_VALUE
            {
             before(grammarAccess.getLiteralAccess().getValuePRIMITIVE_VALUETerminalRuleCall_0()); 
            match(input,RULE_PRIMITIVE_VALUE,FOLLOW_2); 
             after(grammarAccess.getLiteralAccess().getValuePRIMITIVE_VALUETerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Literal__ValueAssignment"


    // $ANTLR start "rule__VarExpression__PartsAssignment_0_0"
    // InternalJavali.g:7041:1: rule__VarExpression__PartsAssignment_0_0 : ( ruleIdentifier ) ;
    public final void rule__VarExpression__PartsAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7045:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:7046:2: ( ruleIdentifier )
            {
            // InternalJavali.g:7046:2: ( ruleIdentifier )
            // InternalJavali.g:7047:3: ruleIdentifier
            {
             before(grammarAccess.getVarExpressionAccess().getPartsIdentifierParserRuleCall_0_0_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getVarExpressionAccess().getPartsIdentifierParserRuleCall_0_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__PartsAssignment_0_0"


    // $ANTLR start "rule__VarExpression__ArrayIndexesAssignment_0_1_1"
    // InternalJavali.g:7056:1: rule__VarExpression__ArrayIndexesAssignment_0_1_1 : ( ruleExpression ) ;
    public final void rule__VarExpression__ArrayIndexesAssignment_0_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7060:1: ( ( ruleExpression ) )
            // InternalJavali.g:7061:2: ( ruleExpression )
            {
            // InternalJavali.g:7061:2: ( ruleExpression )
            // InternalJavali.g:7062:3: ruleExpression
            {
             before(grammarAccess.getVarExpressionAccess().getArrayIndexesExpressionParserRuleCall_0_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getVarExpressionAccess().getArrayIndexesExpressionParserRuleCall_0_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__ArrayIndexesAssignment_0_1_1"


    // $ANTLR start "rule__VarExpression__PartsAssignment_1_1"
    // InternalJavali.g:7071:1: rule__VarExpression__PartsAssignment_1_1 : ( ruleIdentifier ) ;
    public final void rule__VarExpression__PartsAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7075:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:7076:2: ( ruleIdentifier )
            {
            // InternalJavali.g:7076:2: ( ruleIdentifier )
            // InternalJavali.g:7077:3: ruleIdentifier
            {
             before(grammarAccess.getVarExpressionAccess().getPartsIdentifierParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getVarExpressionAccess().getPartsIdentifierParserRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__PartsAssignment_1_1"


    // $ANTLR start "rule__VarExpression__ArrayIndexesAssignment_1_2_1"
    // InternalJavali.g:7086:1: rule__VarExpression__ArrayIndexesAssignment_1_2_1 : ( ruleExpression ) ;
    public final void rule__VarExpression__ArrayIndexesAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7090:1: ( ( ruleExpression ) )
            // InternalJavali.g:7091:2: ( ruleExpression )
            {
            // InternalJavali.g:7091:2: ( ruleExpression )
            // InternalJavali.g:7092:3: ruleExpression
            {
             before(grammarAccess.getVarExpressionAccess().getArrayIndexesExpressionParserRuleCall_1_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getVarExpressionAccess().getArrayIndexesExpressionParserRuleCall_1_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__VarExpression__ArrayIndexesAssignment_1_2_1"


    // $ANTLR start "rule__ProcCall__IdAssignment_0"
    // InternalJavali.g:7101:1: rule__ProcCall__IdAssignment_0 : ( ruleIdentifier ) ;
    public final void rule__ProcCall__IdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7105:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:7106:2: ( ruleIdentifier )
            {
            // InternalJavali.g:7106:2: ( ruleIdentifier )
            // InternalJavali.g:7107:3: ruleIdentifier
            {
             before(grammarAccess.getProcCallAccess().getIdIdentifierParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getProcCallAccess().getIdIdentifierParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__IdAssignment_0"


    // $ANTLR start "rule__ProcCall__ArgsAssignment_2_0"
    // InternalJavali.g:7116:1: rule__ProcCall__ArgsAssignment_2_0 : ( ruleExpression ) ;
    public final void rule__ProcCall__ArgsAssignment_2_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7120:1: ( ( ruleExpression ) )
            // InternalJavali.g:7121:2: ( ruleExpression )
            {
            // InternalJavali.g:7121:2: ( ruleExpression )
            // InternalJavali.g:7122:3: ruleExpression
            {
             before(grammarAccess.getProcCallAccess().getArgsExpressionParserRuleCall_2_0_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getProcCallAccess().getArgsExpressionParserRuleCall_2_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__ArgsAssignment_2_0"


    // $ANTLR start "rule__ProcCall__ArgsAssignment_2_1_1"
    // InternalJavali.g:7131:1: rule__ProcCall__ArgsAssignment_2_1_1 : ( ruleExpression ) ;
    public final void rule__ProcCall__ArgsAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7135:1: ( ( ruleExpression ) )
            // InternalJavali.g:7136:2: ( ruleExpression )
            {
            // InternalJavali.g:7136:2: ( ruleExpression )
            // InternalJavali.g:7137:3: ruleExpression
            {
             before(grammarAccess.getProcCallAccess().getArgsExpressionParserRuleCall_2_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getProcCallAccess().getArgsExpressionParserRuleCall_2_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcCall__ArgsAssignment_2_1_1"


    // $ANTLR start "rule__Type__IdAssignment_0"
    // InternalJavali.g:7146:1: rule__Type__IdAssignment_0 : ( ruleIdentifier ) ;
    public final void rule__Type__IdAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7150:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:7151:2: ( ruleIdentifier )
            {
            // InternalJavali.g:7151:2: ( ruleIdentifier )
            // InternalJavali.g:7152:3: ruleIdentifier
            {
             before(grammarAccess.getTypeAccess().getIdIdentifierParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getTypeAccess().getIdIdentifierParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__IdAssignment_0"


    // $ANTLR start "rule__Type__ArrayDimsAssignment_1_0"
    // InternalJavali.g:7161:1: rule__Type__ArrayDimsAssignment_1_0 : ( ( '[' ) ) ;
    public final void rule__Type__ArrayDimsAssignment_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7165:1: ( ( ( '[' ) ) )
            // InternalJavali.g:7166:2: ( ( '[' ) )
            {
            // InternalJavali.g:7166:2: ( ( '[' ) )
            // InternalJavali.g:7167:3: ( '[' )
            {
             before(grammarAccess.getTypeAccess().getArrayDimsLeftSquareBracketKeyword_1_0_0()); 
            // InternalJavali.g:7168:3: ( '[' )
            // InternalJavali.g:7169:4: '['
            {
             before(grammarAccess.getTypeAccess().getArrayDimsLeftSquareBracketKeyword_1_0_0()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getTypeAccess().getArrayDimsLeftSquareBracketKeyword_1_0_0()); 

            }

             after(grammarAccess.getTypeAccess().getArrayDimsLeftSquareBracketKeyword_1_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Type__ArrayDimsAssignment_1_0"


    // $ANTLR start "rule__NewArray__TypeAssignment_1"
    // InternalJavali.g:7180:1: rule__NewArray__TypeAssignment_1 : ( ruleIdentifier ) ;
    public final void rule__NewArray__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7184:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:7185:2: ( ruleIdentifier )
            {
            // InternalJavali.g:7185:2: ( ruleIdentifier )
            // InternalJavali.g:7186:3: ruleIdentifier
            {
             before(grammarAccess.getNewArrayAccess().getTypeIdentifierParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getNewArrayAccess().getTypeIdentifierParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__TypeAssignment_1"


    // $ANTLR start "rule__NewArray__ArrayDimsAssignment_2_1"
    // InternalJavali.g:7195:1: rule__NewArray__ArrayDimsAssignment_2_1 : ( ruleExpression ) ;
    public final void rule__NewArray__ArrayDimsAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7199:1: ( ( ruleExpression ) )
            // InternalJavali.g:7200:2: ( ruleExpression )
            {
            // InternalJavali.g:7200:2: ( ruleExpression )
            // InternalJavali.g:7201:3: ruleExpression
            {
             before(grammarAccess.getNewArrayAccess().getArrayDimsExpressionParserRuleCall_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getNewArrayAccess().getArrayDimsExpressionParserRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewArray__ArrayDimsAssignment_2_1"


    // $ANTLR start "rule__NewObject__TypeAssignment_1"
    // InternalJavali.g:7210:1: rule__NewObject__TypeAssignment_1 : ( ruleIdentifier ) ;
    public final void rule__NewObject__TypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7214:1: ( ( ruleIdentifier ) )
            // InternalJavali.g:7215:2: ( ruleIdentifier )
            {
            // InternalJavali.g:7215:2: ( ruleIdentifier )
            // InternalJavali.g:7216:3: ruleIdentifier
            {
             before(grammarAccess.getNewObjectAccess().getTypeIdentifierParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleIdentifier();

            state._fsp--;

             after(grammarAccess.getNewObjectAccess().getTypeIdentifierParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NewObject__TypeAssignment_1"


    // $ANTLR start "rule__Identifier__IdAssignment"
    // InternalJavali.g:7225:1: rule__Identifier__IdAssignment : ( RULE_ID ) ;
    public final void rule__Identifier__IdAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalJavali.g:7229:1: ( ( RULE_ID ) )
            // InternalJavali.g:7230:2: ( RULE_ID )
            {
            // InternalJavali.g:7230:2: ( RULE_ID )
            // InternalJavali.g:7231:3: RULE_ID
            {
             before(grammarAccess.getIdentifierAccess().getIdIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getIdentifierAccess().getIdIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Identifier__IdAssignment"

    // Delegated rules


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA11 dfa11 = new DFA11(this);
    static final String dfa_1s = "\17\uffff";
    static final String dfa_2s = "\1\6\3\uffff\1\6\7\uffff\1\5\2\uffff";
    static final String dfa_3s = "\1\50\3\uffff\1\62\7\uffff\1\63\2\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\2\1\3\1\uffff\1\11\1\12\1\13\1\14\1\6\1\10\1\7\1\uffff\1\5\1\4";
    static final String dfa_5s = "\17\uffff}>";
    static final String[] dfa_6s = {
            "\1\4\32\uffff\1\1\1\2\1\3\1\5\1\uffff\1\6\1\7\1\10",
            "",
            "",
            "",
            "\1\16\22\uffff\1\15\4\uffff\1\12\12\uffff\1\11\1\13\5\uffff\1\14\1\uffff\1\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\2\15\27\uffff\1\15\17\uffff\2\15\1\uffff\1\16\1\uffff\1\15",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "1025:1: rule__Statement__Alternatives : ( ( ( rule__Statement__Group_0__0 ) ) | ( ( rule__Statement__Group_1__0 ) ) | ( ( rule__Statement__Group_2__0 ) ) | ( ( rule__Statement__Group_3__0 ) ) | ( ( rule__Statement__Group_4__0 ) ) | ( ( rule__Statement__Group_5__0 ) ) | ( ( rule__Statement__Group_6__0 ) ) | ( ( rule__Statement__Group_7__0 ) ) | ( ruleIfElse ) | ( ruleWhile ) | ( ruleFor ) | ( ( rule__Statement__Group_11__0 ) ) );";
        }
    }
    static final String dfa_7s = "\14\uffff";
    static final String dfa_8s = "\3\uffff\1\7\10\uffff";
    static final String dfa_9s = "\1\5\2\uffff\1\15\1\6\4\uffff\1\36\2\uffff";
    static final String dfa_10s = "\1\63\2\uffff\1\62\1\6\4\uffff\1\60\2\uffff";
    static final String dfa_11s = "\1\uffff\1\1\1\2\2\uffff\1\7\1\10\1\4\1\3\1\uffff\1\6\1\5";
    static final String dfa_12s = "\14\uffff}>";
    static final String[] dfa_13s = {
            "\1\1\1\3\27\uffff\1\6\17\uffff\1\5\1\2\3\uffff\1\4",
            "",
            "",
            "\13\7\2\uffff\1\7\3\uffff\1\10\2\7\12\uffff\3\7\2\uffff\3\7",
            "\1\11",
            "",
            "",
            "",
            "",
            "\1\12\21\uffff\1\13",
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

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "1256:1: rule__Primary__Alternatives : ( ( ruleLiteral ) | ( ruleNull ) | ( ruleProcCall ) | ( ruleVarExpression ) | ( ruleNewArray ) | ( ruleNewObject ) | ( ( rule__Primary__Group_6__0 ) ) | ( ( rule__Primary__Group_7__0 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0030000009000052L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0010000001000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000020000040L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0030000009000050L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000080000040L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000001DE20000040L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x000001DE00000042L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0008C00040000060L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000004000040L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000080000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000100000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000006002L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000078000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000078002L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000000180002L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000000E00000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000000E00002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0001000000000002L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0008C000C0000060L});

}