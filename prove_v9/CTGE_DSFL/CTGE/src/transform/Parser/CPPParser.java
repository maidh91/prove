// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 CPP.g 2011-11-11 01:32:30

package transform.Parser;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

import transform.AST.AST;
import transform.AST.ArrayInitializerAST;
import transform.AST.ArrayInitializerListAST;
import transform.AST.ArrayTypeAST;
import transform.AST.BinExprAST;
import transform.AST.BoolTypeAST;
import transform.AST.BreakStmtAST;
import transform.AST.CallExprAST;
import transform.AST.CallStmtAST;
import transform.AST.CaseStmtAST;
import transform.AST.CharLiteralAST;
import transform.AST.CharTypeAST;
import transform.AST.CompStmtAST;
import transform.AST.ContStmtAST;
import transform.AST.DeclarationAST;
import transform.AST.DeclarationListAST;
import transform.AST.DeclarationStmtAST;
import transform.AST.DefaultStmtAST;
import transform.AST.DefineDirectiveAST;
import transform.AST.DoStmtAST;
import transform.AST.DoubleTypeAST;
import transform.AST.EleExprAST;
import transform.AST.EmptyArrayInitializerAST;
import transform.AST.EmptyArrayInitializerListAST;
import transform.AST.EmptyDeclarationListAST;
import transform.AST.EmptyExprListAST;
import transform.AST.EmptyParaListAST;
import transform.AST.EmptyStmtListAST;
import transform.AST.EmptyTypeListAST;
import transform.AST.EmptyVarInitializerListAST;
import transform.AST.ExprAST;
import transform.AST.ExprListAST;
import transform.AST.ExprStmtAST;
import transform.AST.FloatLiteralAST;
import transform.AST.FloatTypeAST;
import transform.AST.ForInitAST;
import transform.AST.ForStmtAST;
import transform.AST.FuncDeclAST;
import transform.AST.IfThenElseStmtAST;
import transform.AST.IfThenStmtAST;
import transform.AST.InitializerAST;
import transform.AST.IntLiteralAST;
import transform.AST.IntTypeAST;
import transform.AST.LongTypeAST;
import transform.AST.NullExprAST;
import transform.AST.OneStmtAST;
import transform.AST.ParaAST;
import transform.AST.ParaListAST;
import transform.AST.PointerTypeAST;
import transform.AST.ProgramAST;
import transform.AST.RetStmtAST;
import transform.AST.ShortTypeAST;
import transform.AST.SignedTypeAST;
import transform.AST.StmtListAST;
import transform.AST.StringLiteralAST;
import transform.AST.SwitchStmtAST;
import transform.AST.TernaryExprAST;
import transform.AST.TypeAST;
import transform.AST.TypeListAST;
import transform.AST.UnaryExprAST;
import transform.AST.UnsignedTypeAST;
import transform.AST.VarDeclAST;
import transform.AST.VarExprAST;
import transform.AST.VarInitializerAST;
import transform.AST.VarInitializerListAST;
import transform.AST.VoidTypeAST;
import transform.AST.WCharTTypeAST;
import transform.AST.WhileStmtAST;

public class CPPParser
        extends Parser
{
    public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>", "IDENTIFIER",
            "CHARACTER_LITERAL", "STRING_LITERAL", "HEX_LITERAL", "DECIMAL_LITERAL", "OCTAL_LITERAL",
            "FLOATING_POINT_LITERAL", "LETTER", "EscapeSequence", "HexDigit", "IntegerTypeSuffix", "Exponent",
            "FloatTypeSuffix", "OctalEscape", "UnicodeEscape", "WS", "COMMENT", "LINE_COMMENT", "'#define'", "';'",
            "','", "'['", "']'", "'='", "'{'", "'}'", "'*'", "'char'", "'wchar_t'", "'bool'", "'short'", "'int'",
            "'float'", "'double'", "'void'", "'signed'", "'unsigned'", "'long'", "'('", "')'", "'&'", "'if'", "'else'",
            "'for'", "'while'", "'do'", "'switch'", "'case'", "':'", "'default'", "'break'", "'continue'", "'return'",
            "'+='", "'-='", "'*='", "'/='", "'%='", "'<<='", "'>>='", "'&='", "'^='", "'|='", "'?'", "'||'", "'&&'",
            "'|'", "'^'", "'=='", "'!='", "'>'", "'>='", "'<'", "'<='", "'>>'", "'<<'", "'+'", "'-'", "'/'", "'%'",
            "'++'", "'--'", "'!'", "'~'" };
    public static final int T__68 = 68;
    public static final int T__69 = 69;
    public static final int T__66 = 66;
    public static final int T__67 = 67;
    public static final int T__64 = 64;
    public static final int T__29 = 29;
    public static final int T__65 = 65;
    public static final int T__28 = 28;
    public static final int T__62 = 62;
    public static final int T__27 = 27;
    public static final int T__63 = 63;
    public static final int T__26 = 26;
    public static final int FloatTypeSuffix = 16;
    public static final int T__25 = 25;
    public static final int T__24 = 24;
    public static final int LETTER = 11;
    public static final int T__23 = 23;
    public static final int T__22 = 22;
    public static final int Exponent = 15;
    public static final int T__61 = 61;
    public static final int EOF = -1;
    public static final int T__60 = 60;
    public static final int HexDigit = 13;
    public static final int T__55 = 55;
    public static final int T__56 = 56;
    public static final int T__57 = 57;
    public static final int T__58 = 58;
    public static final int STRING_LITERAL = 6;
    public static final int T__51 = 51;
    public static final int T__52 = 52;
    public static final int FLOATING_POINT_LITERAL = 10;
    public static final int T__53 = 53;
    public static final int T__54 = 54;
    public static final int IDENTIFIER = 4;
    public static final int T__59 = 59;
    public static final int HEX_LITERAL = 7;
    public static final int COMMENT = 20;
    public static final int T__50 = 50;
    public static final int T__42 = 42;
    public static final int T__43 = 43;
    public static final int T__40 = 40;
    public static final int T__41 = 41;
    public static final int T__80 = 80;
    public static final int T__46 = 46;
    public static final int T__81 = 81;
    public static final int T__47 = 47;
    public static final int T__82 = 82;
    public static final int T__44 = 44;
    public static final int T__83 = 83;
    public static final int T__45 = 45;
    public static final int LINE_COMMENT = 21;
    public static final int IntegerTypeSuffix = 14;
    public static final int T__48 = 48;
    public static final int T__49 = 49;
    public static final int OCTAL_LITERAL = 9;
    public static final int CHARACTER_LITERAL = 5;
    public static final int T__85 = 85;
    public static final int T__84 = 84;
    public static final int T__87 = 87;
    public static final int T__86 = 86;
    public static final int T__30 = 30;
    public static final int T__31 = 31;
    public static final int T__32 = 32;
    public static final int WS = 19;
    public static final int T__71 = 71;
    public static final int T__33 = 33;
    public static final int T__72 = 72;
    public static final int T__34 = 34;
    public static final int T__35 = 35;
    public static final int T__70 = 70;
    public static final int T__36 = 36;
    public static final int T__37 = 37;
    public static final int T__38 = 38;
    public static final int T__39 = 39;
    public static final int UnicodeEscape = 18;
    public static final int T__76 = 76;
    public static final int T__75 = 75;
    public static final int T__74 = 74;
    public static final int OctalEscape = 17;
    public static final int EscapeSequence = 12;
    public static final int DECIMAL_LITERAL = 8;
    public static final int T__73 = 73;
    public static final int T__79 = 79;
    public static final int T__78 = 78;
    public static final int T__77 = 77;
    
    // delegates
    // delegators
    
    public CPPParser(TokenStream input)
    {
        this(input, new RecognizerSharedState());
    }
    
    public CPPParser(TokenStream input, RecognizerSharedState state)
    {
        super(input, state);
        
    }
    
    @Override
    public String[] getTokenNames()
    {
        return CPPParser.tokenNames;
    }
    
    @Override
    public String getGrammarFileName()
    {
        return "CPP.g";
    }
    
    private AST in, out;
    
    public AST parse() throws RecognitionException
    {
        this.program();
        return this.out;
    }
    
    // $ANTLR start "program"
    // CPP.g:26:1: program : declarationList ;
    public final void program() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:30:3: ( declarationList )
            // CPP.g:30:5: declarationList
            {
                this.pushFollow(FOLLOW_declarationList_in_program59);
                this.declarationList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = new ProgramAST((DeclarationListAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "program"
    
    // $ANTLR start "declarationList"
    // CPP.g:33:1: declarationList : ( declaration declarationList | );
    public final void declarationList() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null, ast3 = null;
        
        try {
            // CPP.g:37:3: ( declaration declarationList | )
            int alt1 = 2;
            int LA1_0 = this.input.LA(1);
            
            if (((LA1_0 == 22) || ((LA1_0 >= 31) && (LA1_0 <= 41)))) {
                alt1 = 1;
            }
            else if ((LA1_0 == EOF)) {
                alt1 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 1, 0, this.input);
                
                throw nvae;
            }
            switch (alt1)
            {
                case 1:
                    // CPP.g:37:5: declaration declarationList
                {
                    this.pushFollow(FOLLOW_declaration_in_declarationList81);
                    this.declaration();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        
                        ast1 = this.out;
                        this.out = null;
                        if (this.in instanceof EmptyDeclarationListAST) {
                            ast3 = this.in.parent;
                        }
                        
                    }
                    this.pushFollow(FOLLOW_declarationList_in_declarationList95);
                    this.declarationList();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        
                        ast2 = this.out;
                        if (ast1 instanceof DeclarationAST) {
                            this.out = new DeclarationListAST((DeclarationAST) ast1, (DeclarationListAST) ast2);
                        }
                        else {
                            if (ast3 instanceof DeclarationListAST) {
                                ((DeclarationListAST) ast3).dl = (DeclarationListAST) ast2;
                                this.out = ast1;
                            }
                        }
                        
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:56:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyDeclarationListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "declarationList"
    
    // $ANTLR start "declaration"
    // CPP.g:58:1: declaration : ( varDecl | directive | funcDecl );
    public final void declaration() throws RecognitionException
    {
        try {
            // CPP.g:59:3: ( varDecl | directive | funcDecl )
            int alt2 = 3;
            alt2 = this.dfa2.predict(this.input);
            switch (alt2)
            {
                case 1:
                    // CPP.g:59:5: varDecl
                {
                    this.pushFollow(FOLLOW_varDecl_in_declaration121);
                    this.varDecl();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:60:5: directive
                {
                    this.pushFollow(FOLLOW_directive_in_declaration127);
                    this.directive();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 3:
                    // CPP.g:61:5: funcDecl
                {
                    this.pushFollow(FOLLOW_funcDecl_in_declaration133);
                    this.funcDecl();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "declaration"
    
    // $ANTLR start "directive"
    // CPP.g:64:1: directive : '#define' id= IDENTIFIER pp_token ;
    public final void directive() throws RecognitionException
    {
        Token id = null;
        
        AST ast1 = null;
        
        try {
            // CPP.g:68:3: ( '#define' id= IDENTIFIER pp_token )
            // CPP.g:68:5: '#define' id= IDENTIFIER pp_token
            {
                this.match(this.input, 22, FOLLOW_22_in_directive150);
                if (this.state.failed) {
                    return;
                }
                id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_directive154);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_pp_token_in_directive156);
                this.pp_token();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = new DefineDirectiveAST(id, (ExprAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "directive"
    
    // $ANTLR start "pp_token"
    // CPP.g:71:1: pp_token : (id= IDENTIFIER | literal );
    public final void pp_token() throws RecognitionException
    {
        Token id = null;
        
        try {
            // CPP.g:72:3: (id= IDENTIFIER | literal )
            int alt3 = 2;
            int LA3_0 = this.input.LA(1);
            
            if ((LA3_0 == IDENTIFIER)) {
                alt3 = 1;
            }
            else if ((((LA3_0 >= CHARACTER_LITERAL) && (LA3_0 <= FLOATING_POINT_LITERAL)))) {
                alt3 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 3, 0, this.input);
                
                throw nvae;
            }
            switch (alt3)
            {
                case 1:
                    // CPP.g:72:5: id= IDENTIFIER
                {
                    id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_pp_token175);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new VarExprAST(id);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:73:5: literal
                {
                    this.pushFollow(FOLLOW_literal_in_pp_token183);
                    this.literal();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "pp_token"
    
    // $ANTLR start "varDecl"
    // CPP.g:76:1: varDecl : type varList ';' ;
    public final void varDecl() throws RecognitionException
    {
        try {
            // CPP.g:77:3: ( type varList ';' )
            // CPP.g:77:5: type varList ';'
            {
                this.pushFollow(FOLLOW_type_in_varDecl199);
                this.type();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                    this.out = null;
                }
                this.pushFollow(FOLLOW_varList_in_varDecl207);
                this.varList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 23, FOLLOW_23_in_varDecl209);
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "varDecl"
    
    // $ANTLR start "varList"
    // CPP.g:81:1: varList : var varListRest ;
    public final void varList() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:85:3: ( var varListRest )
            // CPP.g:85:5: var varListRest
            {
                this.pushFollow(FOLLOW_var_in_varList228);
                this.var();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.pushFollow(FOLLOW_varListRest_in_varList236);
                this.varListRest();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new DeclarationListAST((DeclarationAST) ast1, (DeclarationListAST) ast2);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "varList"
    
    // $ANTLR start "varListRest"
    // CPP.g:90:1: varListRest : ( ',' var varListRest | );
    public final void varListRest() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:94:3: ( ',' var varListRest | )
            int alt4 = 2;
            int LA4_0 = this.input.LA(1);
            
            if ((LA4_0 == 24)) {
                alt4 = 1;
            }
            else if (((LA4_0 == EOF) || (LA4_0 == 23))) {
                alt4 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 4, 0, this.input);
                
                throw nvae;
            }
            switch (alt4)
            {
                case 1:
                    // CPP.g:94:5: ',' var varListRest
                {
                    this.match(this.input, 24, FOLLOW_24_in_varListRest263);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_var_in_varListRest265);
                    this.var();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_varListRest_in_varListRest273);
                    this.varListRest();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new DeclarationListAST((DeclarationAST) ast1, (DeclarationListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:97:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyDeclarationListAST();
                        this.in = this.out;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "varListRest"
    
    // $ANTLR start "var"
    // CPP.g:100:1: var : id= IDENTIFIER arrayDecl ( initializer )? ;
    public final void var() throws RecognitionException
    {
        Token id = null;
        
        AST ast1 = null, ast2 = null, ast3 = null;
        
        try {
            // CPP.g:104:3: (id= IDENTIFIER arrayDecl ( initializer )? )
            // CPP.g:104:5: id= IDENTIFIER arrayDecl ( initializer )?
            {
                if (this.state.backtracking == 0) {
                    ast1 = this.in;
                }
                id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_var314);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_arrayDecl_in_var321);
                this.arrayDecl();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    if (!(ast2 instanceof EmptyExprListAST)) {
                        ast1 = new ArrayTypeAST((TypeAST) ast1, (ExprListAST) ast2);
                    }
                    
                }
                // CPP.g:110:5: ( initializer )?
                int alt5 = 2;
                int LA5_0 = this.input.LA(1);
                
                if ((LA5_0 == 27)) {
                    alt5 = 1;
                }
                switch (alt5)
                {
                    case 1:
                        // CPP.g:0:0: initializer
                    {
                        this.pushFollow(FOLLOW_initializer_in_var335);
                        this.initializer();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    
                }
                
                if (this.state.backtracking == 0) {
                    ast3 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new VarDeclAST(id, (TypeAST) ast1, (InitializerAST) ast3);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "var"
    
    // $ANTLR start "arrayDecl"
    // CPP.g:114:1: arrayDecl : ( ( '[' expr ']' | '[' ']' ) arrayDecl | );
    public final void arrayDecl() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:118:3: ( ( '[' expr ']' | '[' ']' ) arrayDecl | )
            int alt7 = 2;
            int LA7_0 = this.input.LA(1);
            
            if ((LA7_0 == 25)) {
                alt7 = 1;
            }
            else if (((LA7_0 == EOF) || ((LA7_0 >= 23) && (LA7_0 <= 24)) || (LA7_0 == 27) || (LA7_0 == 43))) {
                alt7 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 7, 0, this.input);
                
                throw nvae;
            }
            switch (alt7)
            {
                case 1:
                    // CPP.g:118:5: ( '[' expr ']' | '[' ']' ) arrayDecl
                {
                    // CPP.g:118:5: ( '[' expr ']' | '[' ']' )
                    int alt6 = 2;
                    int LA6_0 = this.input.LA(1);
                    
                    if ((LA6_0 == 25)) {
                        int LA6_1 = this.input.LA(2);
                        
                        if ((LA6_1 == 26)) {
                            alt6 = 2;
                        }
                        else if ((((LA6_1 >= IDENTIFIER) && (LA6_1 <= FLOATING_POINT_LITERAL)) || (LA6_1 == 30)
                                || (LA6_1 == 42) || (LA6_1 == 44) || ((LA6_1 >= 80) && (LA6_1 <= 81)) || ((LA6_1 >= 84) && (LA6_1 <= 87)))) {
                            alt6 = 1;
                        }
                        else {
                            if (this.state.backtracking > 0) {
                                this.state.failed = true;
                                return;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 6, 1, this.input);
                            
                            throw nvae;
                        }
                    }
                    else {
                        if (this.state.backtracking > 0) {
                            this.state.failed = true;
                            return;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 6, 0, this.input);
                        
                        throw nvae;
                    }
                    switch (alt6)
                    {
                        case 1:
                            // CPP.g:118:7: '[' expr ']'
                        {
                            this.match(this.input, 25, FOLLOW_25_in_arrayDecl365);
                            if (this.state.failed) {
                                return;
                            }
                            this.pushFollow(FOLLOW_expr_in_arrayDecl367);
                            this.expr();
                            
                            this.state._fsp--;
                            if (this.state.failed) {
                                return;
                            }
                            this.match(this.input, 26, FOLLOW_26_in_arrayDecl369);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                ast1 = this.out;
                                this.out = null;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:119:7: '[' ']'
                        {
                            this.match(this.input, 25, FOLLOW_25_in_arrayDecl379);
                            if (this.state.failed) {
                                return;
                            }
                            this.match(this.input, 26, FOLLOW_26_in_arrayDecl381);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                ast1 = new NullExprAST();
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_arrayDecl_in_arrayDecl391);
                    this.arrayDecl();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new ExprListAST((ExprAST) ast1, (ExprListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:122:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyExprListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "arrayDecl"
    
    // $ANTLR start "initializer"
    // CPP.g:126:1: initializer : '=' ( varInit | arrayInit | arrayInitList ) ;
    public final void initializer() throws RecognitionException
    {
        
        //AST ast1 = null;
        
        try {
            // CPP.g:130:3: ( '=' ( varInit | arrayInit | arrayInitList ) )
            // CPP.g:130:5: '=' ( varInit | arrayInit | arrayInitList )
            {
                this.match(this.input, 27, FOLLOW_27_in_initializer425);
                if (this.state.failed) {
                    return;
                }
                // CPP.g:131:3: ( varInit | arrayInit | arrayInitList )
                int alt8 = 3;
                int LA8_0 = this.input.LA(1);
                
                if ((((LA8_0 >= IDENTIFIER) && (LA8_0 <= FLOATING_POINT_LITERAL)) || (LA8_0 == 30) || (LA8_0 == 42)
                        || (LA8_0 == 44) || ((LA8_0 >= 80) && (LA8_0 <= 81)) || ((LA8_0 >= 84) && (LA8_0 <= 87)))) {
                    alt8 = 1;
                }
                else if ((LA8_0 == 28)) {
                    int LA8_2 = this.input.LA(2);
                    
                    if ((((LA8_2 >= IDENTIFIER) && (LA8_2 <= FLOATING_POINT_LITERAL))
                            || ((LA8_2 >= 29) && (LA8_2 <= 30)) || (LA8_2 == 42) || (LA8_2 == 44)
                            || ((LA8_2 >= 80) && (LA8_2 <= 81)) || ((LA8_2 >= 84) && (LA8_2 <= 87)))) {
                        alt8 = 2;
                    }
                    else if ((LA8_2 == 28)) {
                        alt8 = 3;
                    }
                    else {
                        if (this.state.backtracking > 0) {
                            this.state.failed = true;
                            return;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 8, 2, this.input);
                        
                        throw nvae;
                    }
                }
                else {
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 8, 0, this.input);
                    
                    throw nvae;
                }
                switch (alt8)
                {
                    case 1:
                        // CPP.g:131:5: varInit
                    {
                        this.pushFollow(FOLLOW_varInit_in_initializer431);
                        this.varInit();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    case 2:
                        // CPP.g:132:5: arrayInit
                    {
                        this.pushFollow(FOLLOW_arrayInit_in_initializer437);
                        this.arrayInit();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    case 3:
                        // CPP.g:133:5: arrayInitList
                    {
                        this.pushFollow(FOLLOW_arrayInitList_in_initializer443);
                        this.arrayInitList();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "initializer"
    
    // $ANTLR start "arrayInitList"
    // CPP.g:137:1: arrayInitList : '{' arrayInit arrayInitListEx '}' ;
    public final void arrayInitList() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:141:3: ( '{' arrayInit arrayInitListEx '}' )
            // CPP.g:141:5: '{' arrayInit arrayInitListEx '}'
            {
                this.match(this.input, 28, FOLLOW_28_in_arrayInitList466);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_arrayInit_in_arrayInitList473);
                this.arrayInit();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.pushFollow(FOLLOW_arrayInitListEx_in_arrayInitList481);
                this.arrayInitListEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                this.match(this.input, 29, FOLLOW_29_in_arrayInitList489);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new ArrayInitializerListAST((ArrayInitializerAST) ast1, (ArrayInitializerListAST) ast2);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "arrayInitList"
    
    // $ANTLR start "arrayInitListEx"
    // CPP.g:148:1: arrayInitListEx : ( ',' arrayInit arrayInitListEx | );
    public final void arrayInitListEx() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:152:3: ( ',' arrayInit arrayInitListEx | )
            int alt9 = 2;
            int LA9_0 = this.input.LA(1);
            
            if ((LA9_0 == 24)) {
                alt9 = 1;
            }
            else if (((LA9_0 == EOF) || (LA9_0 == 29))) {
                alt9 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 9, 0, this.input);
                
                throw nvae;
            }
            switch (alt9)
            {
                case 1:
                    // CPP.g:152:5: ',' arrayInit arrayInitListEx
                {
                    this.match(this.input, 24, FOLLOW_24_in_arrayInitListEx514);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_arrayInit_in_arrayInitListEx520);
                    this.arrayInit();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_arrayInitListEx_in_arrayInitListEx528);
                    this.arrayInitListEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out =
                                   new ArrayInitializerListAST((ArrayInitializerAST) ast1,
                                           (ArrayInitializerListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:156:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyArrayInitializerListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "arrayInitListEx"
    
    // $ANTLR start "arrayInit"
    // CPP.g:159:1: arrayInit : ( '{' varInit arrayInitEx '}' | '{' '}' );
    public final void arrayInit() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:163:3: ( '{' varInit arrayInitEx '}' | '{' '}' )
            int alt10 = 2;
            int LA10_0 = this.input.LA(1);
            
            if ((LA10_0 == 28)) {
                int LA10_1 = this.input.LA(2);
                
                if ((LA10_1 == 29)) {
                    alt10 = 2;
                }
                else if ((((LA10_1 >= IDENTIFIER) && (LA10_1 <= FLOATING_POINT_LITERAL)) || (LA10_1 == 30)
                        || (LA10_1 == 42) || (LA10_1 == 44) || ((LA10_1 >= 80) && (LA10_1 <= 81)) || ((LA10_1 >= 84) && (LA10_1 <= 87)))) {
                    alt10 = 1;
                }
                else {
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 10, 1, this.input);
                    
                    throw nvae;
                }
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 10, 0, this.input);
                
                throw nvae;
            }
            switch (alt10)
            {
                case 1:
                    // CPP.g:163:5: '{' varInit arrayInitEx '}'
                {
                    this.match(this.input, 28, FOLLOW_28_in_arrayInit561);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_varInit_in_arrayInit568);
                    this.varInit();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_arrayInitEx_in_arrayInit576);
                    this.arrayInitEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    this.match(this.input, 29, FOLLOW_29_in_arrayInit584);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out =
                                   new ArrayInitializerAST(new VarInitializerListAST((VarInitializerAST) ast1,
                                           (VarInitializerListAST) ast2));
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:168:5: '{' '}'
                {
                    this.match(this.input, 28, FOLLOW_28_in_arrayInit596);
                    if (this.state.failed) {
                        return;
                    }
                    this.match(this.input, 29, FOLLOW_29_in_arrayInit598);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyArrayInitializerAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "arrayInit"
    
    // $ANTLR start "arrayInitEx"
    // CPP.g:171:1: arrayInitEx : ( ',' varInit arrayInitEx | );
    public final void arrayInitEx() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:175:3: ( ',' varInit arrayInitEx | )
            int alt11 = 2;
            int LA11_0 = this.input.LA(1);
            
            if ((LA11_0 == 24)) {
                alt11 = 1;
            }
            else if (((LA11_0 == EOF) || (LA11_0 == 29))) {
                alt11 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 11, 0, this.input);
                
                throw nvae;
            }
            switch (alt11)
            {
                case 1:
                    // CPP.g:175:5: ',' varInit arrayInitEx
                {
                    this.match(this.input, 24, FOLLOW_24_in_arrayInitEx619);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_varInit_in_arrayInitEx626);
                    this.varInit();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_arrayInitEx_in_arrayInitEx634);
                    this.arrayInitEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new VarInitializerListAST((VarInitializerAST) ast1, (VarInitializerListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:179:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyVarInitializerListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "arrayInitEx"
    
    // $ANTLR start "varInit"
    // CPP.g:182:1: varInit : expr ;
    public final void varInit() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:186:3: ( expr )
            // CPP.g:186:5: expr
            {
                this.pushFollow(FOLLOW_expr_in_varInit667);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new VarInitializerAST((ExprAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "varInit"
    
    // $ANTLR start "type"
    // CPP.g:190:1: type : ( combinedType | pointerType );
    public final void type() throws RecognitionException
    {
        try {
            // CPP.g:191:3: ( combinedType | pointerType )
            int alt12 = 2;
            alt12 = this.dfa12.predict(this.input);
            switch (alt12)
            {
                case 1:
                    // CPP.g:191:5: combinedType
                {
                    this.pushFollow(FOLLOW_combinedType_in_type690);
                    this.combinedType();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:192:5: pointerType
                {
                    this.pushFollow(FOLLOW_pointerType_in_type696);
                    this.pointerType();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "type"
    
    // $ANTLR start "pointerType"
    // CPP.g:195:1: pointerType : combinedType pointerTypeRest ;
    public final void pointerType() throws RecognitionException
    {
        
        try {
            // CPP.g:199:3: ( combinedType pointerTypeRest )
            // CPP.g:199:5: combinedType pointerTypeRest
            {
                this.pushFollow(FOLLOW_combinedType_in_pointerType715);
                this.combinedType();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                    this.out = null;
                }
                this.pushFollow(FOLLOW_pointerTypeRest_in_pointerType723);
                this.pointerTypeRest();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "pointerType"
    
    // $ANTLR start "pointerTypeRest"
    // CPP.g:203:1: pointerTypeRest : ( '*' | '*' pointerTypeRest );
    public final void pointerTypeRest() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:207:3: ( '*' | '*' pointerTypeRest )
            int alt13 = 2;
            int LA13_0 = this.input.LA(1);
            
            if ((LA13_0 == 30)) {
                int LA13_1 = this.input.LA(2);
                
                if (((LA13_1 == IDENTIFIER) || (LA13_1 == 44))) {
                    alt13 = 1;
                }
                else if ((LA13_1 == 30)) {
                    alt13 = 2;
                }
                else {
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 13, 1, this.input);
                    
                    throw nvae;
                }
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 13, 0, this.input);
                
                throw nvae;
            }
            switch (alt13)
            {
                case 1:
                    // CPP.g:207:5: '*'
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    this.match(this.input, 30, FOLLOW_30_in_pointerTypeRest748);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new PointerTypeAST((TypeAST) ast1);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:209:5: '*' pointerTypeRest
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    this.match(this.input, 30, FOLLOW_30_in_pointerTypeRest762);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new PointerTypeAST((TypeAST) ast1);
                    }
                    this.pushFollow(FOLLOW_pointerTypeRest_in_pointerTypeRest770);
                    this.pointerTypeRest();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "pointerTypeRest"
    
    // $ANTLR start "combinedType"
    // CPP.g:214:1: combinedType : primitiveType ;
    public final void combinedType() throws RecognitionException
    {
        
        AST ast1 = null;
        //ast2 = null;
        
        try {
            // CPP.g:218:3: ( primitiveType )
            // CPP.g:218:5: primitiveType
            {
                this.pushFollow(FOLLOW_primitiveType_in_combinedType789);
                this.primitiveType();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = new TypeListAST((TypeAST) ast1, new EmptyTypeListAST());
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "combinedType"
    
    // $ANTLR start "primitiveType"
    // CPP.g:223:1: primitiveType : ( 'char' | 'wchar_t' | 'bool' | 'short' | 'int' | 'float' |
    // 'double' | 'void' | 'signed' | 'unsigned' | 'long' );
    public final void primitiveType() throws RecognitionException
    {
        try {
            // CPP.g:224:3: ( 'char' | 'wchar_t' | 'bool' | 'short' | 'int' | 'float' | 'double' |
            // 'void' | 'signed' | 'unsigned' | 'long' )
            int alt14 = 11;
            switch (this.input.LA(1))
            {
                case 31:
                {
                    alt14 = 1;
                }
                    break;
                case 32:
                {
                    alt14 = 2;
                }
                    break;
                case 33:
                {
                    alt14 = 3;
                }
                    break;
                case 34:
                {
                    alt14 = 4;
                }
                    break;
                case 35:
                {
                    alt14 = 5;
                }
                    break;
                case 36:
                {
                    alt14 = 6;
                }
                    break;
                case 37:
                {
                    alt14 = 7;
                }
                    break;
                case 38:
                {
                    alt14 = 8;
                }
                    break;
                case 39:
                {
                    alt14 = 9;
                }
                    break;
                case 40:
                {
                    alt14 = 10;
                }
                    break;
                case 41:
                {
                    alt14 = 11;
                }
                    break;
                default:
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 14, 0, this.input);
                    
                    throw nvae;
            }
            
            switch (alt14)
            {
                case 1:
                    // CPP.g:224:5: 'char'
                {
                    this.match(this.input, 31, FOLLOW_31_in_primitiveType813);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new CharTypeAST();
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:225:5: 'wchar_t'
                {
                    this.match(this.input, 32, FOLLOW_32_in_primitiveType821);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new WCharTTypeAST();
                    }
                    
                }
                    break;
                case 3:
                    // CPP.g:226:5: 'bool'
                {
                    this.match(this.input, 33, FOLLOW_33_in_primitiveType829);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new BoolTypeAST();
                    }
                    
                }
                    break;
                case 4:
                    // CPP.g:227:5: 'short'
                {
                    this.match(this.input, 34, FOLLOW_34_in_primitiveType837);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new ShortTypeAST();
                    }
                    
                }
                    break;
                case 5:
                    // CPP.g:228:5: 'int'
                {
                    this.match(this.input, 35, FOLLOW_35_in_primitiveType845);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new IntTypeAST();
                    }
                    
                }
                    break;
                case 6:
                    // CPP.g:229:5: 'float'
                {
                    this.match(this.input, 36, FOLLOW_36_in_primitiveType853);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new FloatTypeAST();
                    }
                    
                }
                    break;
                case 7:
                    // CPP.g:230:5: 'double'
                {
                    this.match(this.input, 37, FOLLOW_37_in_primitiveType861);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new DoubleTypeAST();
                    }
                    
                }
                    break;
                case 8:
                    // CPP.g:231:5: 'void'
                {
                    this.match(this.input, 38, FOLLOW_38_in_primitiveType869);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new VoidTypeAST();
                    }
                    
                }
                    break;
                case 9:
                    // CPP.g:232:5: 'signed'
                {
                    this.match(this.input, 39, FOLLOW_39_in_primitiveType877);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new SignedTypeAST();
                    }
                    
                }
                    break;
                case 10:
                    // CPP.g:233:5: 'unsigned'
                {
                    this.match(this.input, 40, FOLLOW_40_in_primitiveType885);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new UnsignedTypeAST();
                    }
                    
                }
                    break;
                case 11:
                    // CPP.g:234:5: 'long'
                {
                    this.match(this.input, 41, FOLLOW_41_in_primitiveType893);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new LongTypeAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "primitiveType"
    
    // $ANTLR start "funcDecl"
    // CPP.g:237:1: funcDecl : type id= IDENTIFIER '(' paraList ')' blockStmt ;
    public final void funcDecl() throws RecognitionException
    {
        Token id = null;
        
        AST ast1 = null, ast2 = null, ast3 = null;
        
        try {
            // CPP.g:241:3: ( type id= IDENTIFIER '(' paraList ')' blockStmt )
            // CPP.g:241:5: type id= IDENTIFIER '(' paraList ')' blockStmt
            {
                this.pushFollow(FOLLOW_type_in_funcDecl912);
                this.type();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_funcDecl922);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_funcDecl929);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_paraList_in_funcDecl936);
                this.paraList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_funcDecl944);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_blockStmt_in_funcDecl951);
                this.blockStmt();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast3 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new FuncDeclAST(id, (ParaListAST) ast2, (TypeAST) ast1, (CompStmtAST) ast3);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "funcDecl"
    
    // $ANTLR start "paraList"
    // CPP.g:250:1: paraList : ( para paraListRest | );
    public final void paraList() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:254:3: ( para paraListRest | )
            int alt15 = 2;
            int LA15_0 = this.input.LA(1);
            
            if ((((LA15_0 >= 31) && (LA15_0 <= 41)))) {
                alt15 = 1;
            }
            else if ((LA15_0 == 43)) {
                alt15 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 15, 0, this.input);
                
                throw nvae;
            }
            switch (alt15)
            {
                case 1:
                    // CPP.g:254:5: para paraListRest
                {
                    this.pushFollow(FOLLOW_para_in_paraList978);
                    this.para();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_paraListRest_in_paraList986);
                    this.paraListRest();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new ParaListAST((ParaAST) ast1, (ParaListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:257:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyParaListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "paraList"
    
    // $ANTLR start "para"
    // CPP.g:260:1: para : type (ref= '&' )? id= IDENTIFIER arrayDecl ;
    public final void para() throws RecognitionException
    {
        Token ref = null;
        Token id = null;
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:264:3: ( type (ref= '&' )? id= IDENTIFIER arrayDecl )
            // CPP.g:264:5: type (ref= '&' )? id= IDENTIFIER arrayDecl
            {
                this.pushFollow(FOLLOW_type_in_para1019);
                this.type();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                // CPP.g:265:5: (ref= '&' )?
                int alt16 = 2;
                int LA16_0 = this.input.LA(1);
                
                if ((LA16_0 == 44)) {
                    alt16 = 1;
                }
                switch (alt16)
                {
                    case 1:
                        // CPP.g:265:6: ref= '&'
                    {
                        ref = (Token) this.match(this.input, 44, FOLLOW_44_in_para1030);
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    
                }
                
                id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_para1040);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_arrayDecl_in_para1043);
                this.arrayDecl();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    
                    if (!(ast2 instanceof EmptyExprListAST)) {
                        this.out = new ParaAST(id, new ArrayTypeAST((TypeAST) ast1, (ExprListAST) ast2), ref != null);
                    }
                    else {
                        this.out = new ParaAST(id, (TypeAST) ast1, ref != null);
                    }
                    
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "para"
    
    // $ANTLR start "paraListRest"
    // CPP.g:276:1: paraListRest : ( ',' para paraListRest | );
    public final void paraListRest() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:280:3: ( ',' para paraListRest | )
            int alt17 = 2;
            int LA17_0 = this.input.LA(1);
            
            if ((LA17_0 == 24)) {
                alt17 = 1;
            }
            else if (((LA17_0 == EOF) || (LA17_0 == 43))) {
                alt17 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 17, 0, this.input);
                
                throw nvae;
            }
            switch (alt17)
            {
                case 1:
                    // CPP.g:280:5: ',' para paraListRest
                {
                    this.match(this.input, 24, FOLLOW_24_in_paraListRest1070);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_para_in_paraListRest1072);
                    this.para();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_paraListRest_in_paraListRest1080);
                    this.paraListRest();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new ParaListAST((ParaAST) ast1, (ParaListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:283:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyParaListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "paraListRest"
    
    // $ANTLR start "blockStmt"
    // CPP.g:286:1: blockStmt : '{' stmtList '}' ;
    public final void blockStmt() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:290:3: ( '{' stmtList '}' )
            // CPP.g:290:5: '{' stmtList '}'
            {
                this.match(this.input, 28, FOLLOW_28_in_blockStmt1113);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmtList_in_blockStmt1119);
                this.stmtList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 29, FOLLOW_29_in_blockStmt1127);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new CompStmtAST((StmtListAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "blockStmt"
    
    // $ANTLR start "stmtList"
    // CPP.g:296:1: stmtList : ( stmt stmtList | );
    public final void stmtList() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:300:3: ( stmt stmtList | )
            int alt18 = 2;
            alt18 = this.dfa18.predict(this.input);
            switch (alt18)
            {
                case 1:
                    // CPP.g:300:5: stmt stmtList
                {
                    this.pushFollow(FOLLOW_stmt_in_stmtList1152);
                    this.stmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_stmtList_in_stmtList1160);
                    this.stmtList();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new StmtListAST((OneStmtAST) ast1, (StmtListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:303:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyStmtListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "stmtList"
    
    // $ANTLR start "stmt"
    // CPP.g:306:1: stmt : ( blockStmt | exprStmt | ifStmt | forStmt | whileStmt | doStmt |
    // breakStmt | continueStmt | returnStmt | switchStmt | caseStmt | defaultStmt | declarationStmt
    // );
    public final void stmt() throws RecognitionException
    {
        try {
            // CPP.g:307:3: ( blockStmt | exprStmt | ifStmt | forStmt | whileStmt | doStmt |
            // breakStmt | continueStmt | returnStmt | switchStmt | caseStmt | defaultStmt |
            // declarationStmt )
            int alt19 = 13;
            switch (this.input.LA(1))
            {
                case 28:
                {
                    alt19 = 1;
                }
                    break;
                case IDENTIFIER:
                case CHARACTER_LITERAL:
                case STRING_LITERAL:
                case HEX_LITERAL:
                case DECIMAL_LITERAL:
                case OCTAL_LITERAL:
                case FLOATING_POINT_LITERAL:
                case 30:
                case 42:
                case 44:
                case 80:
                case 81:
                case 84:
                case 85:
                case 86:
                case 87:
                {
                    alt19 = 2;
                }
                    break;
                case 45:
                {
                    alt19 = 3;
                }
                    break;
                case 47:
                {
                    alt19 = 4;
                }
                    break;
                case 48:
                {
                    alt19 = 5;
                }
                    break;
                case 49:
                {
                    alt19 = 6;
                }
                    break;
                case 54:
                {
                    alt19 = 7;
                }
                    break;
                case 55:
                {
                    alt19 = 8;
                }
                    break;
                case 56:
                {
                    alt19 = 9;
                }
                    break;
                case 50:
                {
                    alt19 = 10;
                }
                    break;
                case 51:
                {
                    alt19 = 11;
                }
                    break;
                case 53:
                {
                    alt19 = 12;
                }
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                {
                    alt19 = 13;
                }
                    break;
                default:
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 19, 0, this.input);
                    
                    throw nvae;
            }
            
            switch (alt19)
            {
                case 1:
                    // CPP.g:307:5: blockStmt
                {
                    this.pushFollow(FOLLOW_blockStmt_in_stmt1189);
                    this.blockStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:309:5: exprStmt
                {
                    this.pushFollow(FOLLOW_exprStmt_in_stmt1197);
                    this.exprStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 3:
                    // CPP.g:310:5: ifStmt
                {
                    this.pushFollow(FOLLOW_ifStmt_in_stmt1203);
                    this.ifStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 4:
                    // CPP.g:311:5: forStmt
                {
                    this.pushFollow(FOLLOW_forStmt_in_stmt1210);
                    this.forStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 5:
                    // CPP.g:312:5: whileStmt
                {
                    this.pushFollow(FOLLOW_whileStmt_in_stmt1217);
                    this.whileStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 6:
                    // CPP.g:313:5: doStmt
                {
                    this.pushFollow(FOLLOW_doStmt_in_stmt1224);
                    this.doStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 7:
                    // CPP.g:314:5: breakStmt
                {
                    this.pushFollow(FOLLOW_breakStmt_in_stmt1231);
                    this.breakStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 8:
                    // CPP.g:315:5: continueStmt
                {
                    this.pushFollow(FOLLOW_continueStmt_in_stmt1238);
                    this.continueStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 9:
                    // CPP.g:316:5: returnStmt
                {
                    this.pushFollow(FOLLOW_returnStmt_in_stmt1245);
                    this.returnStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 10:
                    // CPP.g:317:5: switchStmt
                {
                    this.pushFollow(FOLLOW_switchStmt_in_stmt1251);
                    this.switchStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 11:
                    // CPP.g:318:5: caseStmt
                {
                    this.pushFollow(FOLLOW_caseStmt_in_stmt1257);
                    this.caseStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 12:
                    // CPP.g:319:5: defaultStmt
                {
                    this.pushFollow(FOLLOW_defaultStmt_in_stmt1263);
                    this.defaultStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 13:
                    // CPP.g:320:5: declarationStmt
                {
                    this.pushFollow(FOLLOW_declarationStmt_in_stmt1269);
                    this.declarationStmt();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "stmt"
    
    // $ANTLR start "declarationStmt"
    // CPP.g:324:1: declarationStmt : varDecl ;
    public final void declarationStmt() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:328:3: ( varDecl )
            // CPP.g:328:5: varDecl
            {
                this.pushFollow(FOLLOW_varDecl_in_declarationStmt1290);
                this.varDecl();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new DeclarationStmtAST((DeclarationListAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "declarationStmt"
    
    // $ANTLR start "exprStmt"
    // CPP.g:332:1: exprStmt : expr ';' ;
    public final void exprStmt() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:336:3: ( expr ';' )
            // CPP.g:336:5: expr ';'
            {
                this.pushFollow(FOLLOW_expr_in_exprStmt1317);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 23, FOLLOW_23_in_exprStmt1325);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new ExprStmtAST((ExprAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprStmt"
    
    // $ANTLR start "ifStmt"
    // CPP.g:341:1: ifStmt : 'if' '(' expr ')' stmt ( options {k=1; } : 'else' stmt )? ;
    public final void ifStmt() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null, ast3 = null;
        
        try {
            // CPP.g:345:3: ( 'if' '(' expr ')' stmt ( options {k=1; } : 'else' stmt )? )
            // CPP.g:345:5: 'if' '(' expr ')' stmt ( options {k=1; } : 'else' stmt )?
            {
                this.match(this.input, 45, FOLLOW_45_in_ifStmt1350);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_ifStmt1357);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_expr_in_ifStmt1364);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_ifStmt1372);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmt_in_ifStmt1379);
                this.stmt();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                // CPP.g:350:5: ( options {k=1; } : 'else' stmt )?
                int alt20 = 2;
                int LA20_0 = this.input.LA(1);
                
                if ((LA20_0 == 46)) {
                	
                    if ((this.synpred42_CPP())) {
                        alt20 = 1;
                    }
                }
                switch (alt20)
                {
                    case 1:
                        // CPP.g:351:5: 'else' stmt
                    {
                        this.match(this.input, 46, FOLLOW_46_in_ifStmt1400);
                        if (this.state.failed) {
                            return;
                        }
                        this.pushFollow(FOLLOW_stmt_in_ifStmt1407);
                        this.stmt();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        if (this.state.backtracking == 0) {
                            ast3 = this.out;
                            this.out = null;
                        }
                        
                    }
                        break;
                    
                }
                
                if (this.state.backtracking == 0) {
                    
                    if (ast3 == null) {
                        this.out = new IfThenStmtAST((ExprAST) ast1, (OneStmtAST) ast2);
                    }
                    else {
                        this.out = new IfThenElseStmtAST((ExprAST) ast1, (OneStmtAST) ast2, (OneStmtAST) ast3);
                    }
                    
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "ifStmt"
    
    // $ANTLR start "forStmt"
    // CPP.g:362:1: forStmt : 'for' '(' ( forInit )? ( expr )? ';' ( exprList )? ')' stmt ;
    public final void forStmt() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null, ast3 = null, ast4 = null;
        
        try {
            // CPP.g:366:3: ( 'for' '(' ( forInit )? ( expr )? ';' ( exprList )? ')' stmt )
            // CPP.g:366:5: 'for' '(' ( forInit )? ( expr )? ';' ( exprList )? ')' stmt
            {
                this.match(this.input, 47, FOLLOW_47_in_forStmt1441);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_forStmt1443);
                if (this.state.failed) {
                    return;
                }
                // CPP.g:367:5: ( forInit )?
                int alt21 = 2;
                alt21 = this.dfa21.predict(this.input);
                switch (alt21)
                {
                    case 1:
                        // CPP.g:0:0: forInit
                    {
                        this.pushFollow(FOLLOW_forInit_in_forStmt1450);
                        this.forInit();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    
                }
                
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                // CPP.g:368:5: ( expr )?
                int alt22 = 2;
                int LA22_0 = this.input.LA(1);
                
                if ((((LA22_0 >= IDENTIFIER) && (LA22_0 <= FLOATING_POINT_LITERAL)) || (LA22_0 == 30) || (LA22_0 == 42)
                        || (LA22_0 == 44) || ((LA22_0 >= 80) && (LA22_0 <= 81)) || ((LA22_0 >= 84) && (LA22_0 <= 87)))) {
                    alt22 = 1;
                }
                switch (alt22)
                {
                    case 1:
                        // CPP.g:0:0: expr
                    {
                        this.pushFollow(FOLLOW_expr_in_forStmt1459);
                        this.expr();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    
                }
                
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                this.match(this.input, 23, FOLLOW_23_in_forStmt1468);
                if (this.state.failed) {
                    return;
                }
                // CPP.g:370:5: ( exprList )?
                int alt23 = 2;
                int LA23_0 = this.input.LA(1);
                
                if ((((LA23_0 >= IDENTIFIER) && (LA23_0 <= FLOATING_POINT_LITERAL)) || (LA23_0 == 30) || (LA23_0 == 42)
                        || (LA23_0 == 44) || ((LA23_0 >= 80) && (LA23_0 <= 81)) || ((LA23_0 >= 84) && (LA23_0 <= 87)))) {
                    alt23 = 1;
                }
                else if ((LA23_0 == 43)) {
                    
                    if ((this.synpred45_CPP())) {
                        alt23 = 1;
                    }
                }
                switch (alt23)
                {
                    case 1:
                        // CPP.g:0:0: exprList
                    {
                        this.pushFollow(FOLLOW_exprList_in_forStmt1475);
                        this.exprList();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        
                    }
                        break;
                    
                }
                
                if (this.state.backtracking == 0) {
                    ast3 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_forStmt1484);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmt_in_forStmt1491);
                this.stmt();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast4 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new ForStmtAST((ForInitAST) ast1, (ExprAST) ast2, (ExprListAST) ast3, (OneStmtAST) ast4);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "forStmt"
    
    // $ANTLR start "forInit"
    // CPP.g:376:1: forInit : ( varDecl | exprList ';' | ';' );
    public final void forInit() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:380:3: ( varDecl | exprList ';' | ';' )
            int alt24 = 3;
            switch (this.input.LA(1))
            {
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                {
                    alt24 = 1;
                }
                    break;
                case IDENTIFIER:
                case CHARACTER_LITERAL:
                case STRING_LITERAL:
                case HEX_LITERAL:
                case DECIMAL_LITERAL:
                case OCTAL_LITERAL:
                case FLOATING_POINT_LITERAL:
                case 30:
                case 42:
                case 44:
                case 80:
                case 81:
                case 84:
                case 85:
                case 86:
                case 87:
                {
                    alt24 = 2;
                }
                    break;
                case 23:
                {
                    
                    if ((this.synpred47_CPP())) {
                        alt24 = 2;
                    }
                    else if ((true)) {
                        alt24 = 3;
                    }
                }
                    break;
                default:
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 24, 0, this.input);
                    
                    throw nvae;
            }
            
            switch (alt24)
            {
                case 1:
                    // CPP.g:380:5: varDecl
                {
                    this.pushFollow(FOLLOW_varDecl_in_forInit1518);
                    this.varDecl();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = new ForInitAST(1, (DeclarationListAST) ast1, null);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:381:5: exprList ';'
                {
                    this.pushFollow(FOLLOW_exprList_in_forInit1526);
                    this.exprList();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    this.match(this.input, 23, FOLLOW_23_in_forInit1528);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = new ForInitAST(2, null, (ExprListAST) ast1);
                    }
                    
                }
                    break;
                case 3:
                    // CPP.g:382:5: ';'
                {
                    this.match(this.input, 23, FOLLOW_23_in_forInit1536);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "forInit"
    
    // $ANTLR start "whileStmt"
    // CPP.g:385:1: whileStmt : 'while' '(' expr ')' stmt ;
    public final void whileStmt() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:389:3: ( 'while' '(' expr ')' stmt )
            // CPP.g:389:5: 'while' '(' expr ')' stmt
            {
                this.match(this.input, 48, FOLLOW_48_in_whileStmt1553);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_whileStmt1560);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_expr_in_whileStmt1567);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_whileStmt1575);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmt_in_whileStmt1582);
                this.stmt();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new WhileStmtAST((ExprAST) ast1, (OneStmtAST) ast2);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "whileStmt"
    
    // $ANTLR start "doStmt"
    // CPP.g:397:1: doStmt : 'do' stmt 'while' '(' expr ')' ';' ;
    public final void doStmt() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:401:3: ( 'do' stmt 'while' '(' expr ')' ';' )
            // CPP.g:401:5: 'do' stmt 'while' '(' expr ')' ';'
            {
                this.match(this.input, 49, FOLLOW_49_in_doStmt1609);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmt_in_doStmt1616);
                this.stmt();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 48, FOLLOW_48_in_doStmt1624);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_doStmt1631);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_expr_in_doStmt1638);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_doStmt1646);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 23, FOLLOW_23_in_doStmt1653);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new DoStmtAST((ExprAST) ast2, (OneStmtAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "doStmt"
    
    // $ANTLR start "switchStmt"
    // CPP.g:411:1: switchStmt : 'switch' '(' expr ')' stmt ;
    public final void switchStmt() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:415:3: ( 'switch' '(' expr ')' stmt )
            // CPP.g:415:5: 'switch' '(' expr ')' stmt
            {
                this.match(this.input, 50, FOLLOW_50_in_switchStmt1678);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_switchStmt1684);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_expr_in_switchStmt1690);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_switchStmt1698);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmt_in_switchStmt1704);
                this.stmt();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new SwitchStmtAST((ExprAST) ast1, (OneStmtAST) ast2);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "switchStmt"
    
    // $ANTLR start "caseStmt"
    // CPP.g:423:1: caseStmt : 'case' expr ':' stmtList ;
    public final void caseStmt() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:427:3: ( 'case' expr ':' stmtList )
            // CPP.g:427:5: 'case' expr ':' stmtList
            {
                this.match(this.input, 51, FOLLOW_51_in_caseStmt1731);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_expr_in_caseStmt1737);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 52, FOLLOW_52_in_caseStmt1745);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmtList_in_caseStmt1751);
                this.stmtList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new CaseStmtAST((ExprAST) ast1, (StmtListAST) ast2);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "caseStmt"
    
    // $ANTLR start "defaultStmt"
    // CPP.g:434:1: defaultStmt : 'default' ':' stmtList ;
    public final void defaultStmt() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:438:3: ( 'default' ':' stmtList )
            // CPP.g:438:5: 'default' ':' stmtList
            {
                this.match(this.input, 53, FOLLOW_53_in_defaultStmt1778);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 52, FOLLOW_52_in_defaultStmt1784);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_stmtList_in_defaultStmt1790);
                this.stmtList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    this.out = new DefaultStmtAST((StmtListAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "defaultStmt"
    
    // $ANTLR start "breakStmt"
    // CPP.g:445:1: breakStmt : br= 'break' ';' ;
    public final void breakStmt() throws RecognitionException
    {
        Token br = null;
        
        try {
            // CPP.g:446:3: (br= 'break' ';' )
            // CPP.g:446:5: br= 'break' ';'
            {
                br = (Token) this.match(this.input, 54, FOLLOW_54_in_breakStmt1820);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 23, FOLLOW_23_in_breakStmt1822);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new BreakStmtAST(br);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "breakStmt"
    
    // $ANTLR start "continueStmt"
    // CPP.g:450:1: continueStmt : cont= 'continue' ';' ;
    public final void continueStmt() throws RecognitionException
    {
        Token cont = null;
        
        try {
            // CPP.g:451:3: (cont= 'continue' ';' )
            // CPP.g:451:5: cont= 'continue' ';'
            {
                cont = (Token) this.match(this.input, 55, FOLLOW_55_in_continueStmt1845);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 23, FOLLOW_23_in_continueStmt1847);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new ContStmtAST(cont);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "continueStmt"
    
    // $ANTLR start "returnStmt"
    // CPP.g:455:1: returnStmt : 'return' ( expr )? ';' ;
    public final void returnStmt() throws RecognitionException
    {
        
        AST ast1 = null;
        
        try {
            // CPP.g:459:3: ( 'return' ( expr )? ';' )
            // CPP.g:459:5: 'return' ( expr )? ';'
            {
                this.match(this.input, 56, FOLLOW_56_in_returnStmt1872);
                if (this.state.failed) {
                    return;
                }
                // CPP.g:460:5: ( expr )?
                int alt25 = 2;
                int LA25_0 = this.input.LA(1);
                
                if ((((LA25_0 >= IDENTIFIER) && (LA25_0 <= FLOATING_POINT_LITERAL)) || (LA25_0 == 30) || (LA25_0 == 42)
                        || (LA25_0 == 44) || ((LA25_0 >= 80) && (LA25_0 <= 81)) || ((LA25_0 >= 84) && (LA25_0 <= 87)))) {
                    alt25 = 1;
                }
                switch (alt25)
                {
                    case 1:
                        // CPP.g:460:6: expr
                    {
                        this.pushFollow(FOLLOW_expr_in_returnStmt1880);
                        this.expr();
                        
                        this.state._fsp--;
                        if (this.state.failed) {
                            return;
                        }
                        if (this.state.backtracking == 0) {
                            ast1 = this.out;
                            this.out = null;
                        }
                        
                    }
                        break;
                    
                }
                
                this.match(this.input, 23, FOLLOW_23_in_returnStmt1891);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    
                    if (ast1 != null) {
                        this.out = new RetStmtAST((ExprAST) ast1);
                    }
                    else {
                        this.out = new RetStmtAST();
                    }
                    
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "returnStmt"
    
    // $ANTLR start "callStmt"
    // CPP.g:470:1: callStmt : id= IDENTIFIER '(' exprList ')' ';' ;
    public final void callStmt() throws RecognitionException
    {
        Token id = null;
        
        AST ast1 = null;
        
        try {
            // CPP.g:474:3: (id= IDENTIFIER '(' exprList ')' ';' )
            // CPP.g:474:5: id= IDENTIFIER '(' exprList ')' ';'
            {
                id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_callStmt1918);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_callStmt1925);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_exprList_in_callStmt1932);
                this.exprList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_callStmt1940);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 23, FOLLOW_23_in_callStmt1947);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new CallStmtAST(id, (ExprListAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "callStmt"
    
    // $ANTLR start "exprList"
    // CPP.g:482:1: exprList : ( expr exprListRest | );
    public final void exprList() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:486:3: ( expr exprListRest | )
            int alt26 = 2;
            int LA26_0 = this.input.LA(1);
            
            if ((((LA26_0 >= IDENTIFIER) && (LA26_0 <= FLOATING_POINT_LITERAL)) || (LA26_0 == 30) || (LA26_0 == 42)
                    || (LA26_0 == 44) || ((LA26_0 >= 80) && (LA26_0 <= 81)) || ((LA26_0 >= 84) && (LA26_0 <= 87)))) {
                alt26 = 1;
            }
            else if (((LA26_0 == EOF) || (LA26_0 == 23) || (LA26_0 == 43))) {
                alt26 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 26, 0, this.input);
                
                throw nvae;
            }
            switch (alt26)
            {
                case 1:
                    // CPP.g:486:5: expr exprListRest
                {
                    this.pushFollow(FOLLOW_expr_in_exprList1972);
                    this.expr();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_exprListRest_in_exprList1981);
                    this.exprListRest();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new ExprListAST((ExprAST) ast1, (ExprListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:489:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyExprListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprList"
    
    // $ANTLR start "exprListRest"
    // CPP.g:492:1: exprListRest : ( ',' expr exprListRest | );
    public final void exprListRest() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:496:3: ( ',' expr exprListRest | )
            int alt27 = 2;
            int LA27_0 = this.input.LA(1);
            
            if ((LA27_0 == 24)) {
                alt27 = 1;
            }
            else if (((LA27_0 == EOF) || (LA27_0 == 23) || (LA27_0 == 43))) {
                alt27 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 27, 0, this.input);
                
                throw nvae;
            }
            switch (alt27)
            {
                case 1:
                    // CPP.g:496:5: ',' expr exprListRest
                {
                    this.match(this.input, 24, FOLLOW_24_in_exprListRest2014);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_expr_in_exprListRest2021);
                    this.expr();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.pushFollow(FOLLOW_exprListRest_in_exprListRest2030);
                    this.exprListRest();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new ExprListAST((ExprAST) ast1, (ExprListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:500:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyExprListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprListRest"
    
    // $ANTLR start "expr"
    // CPP.g:503:1: expr : exprASSIGN ;
    public final void expr() throws RecognitionException
    {
        try {
            // CPP.g:504:3: ( exprASSIGN )
            // CPP.g:504:5: exprASSIGN
            {
                this.pushFollow(FOLLOW_exprASSIGN_in_expr2059);
                this.exprASSIGN();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "expr"
    
    // $ANTLR start "exprASSIGN"
    // CPP.g:507:1: exprASSIGN : ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op=
    // '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN |
    // exprTERNARY );
    public final void exprASSIGN() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        int type = 0;
        
        try {
            // CPP.g:512:3: ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' |
            // op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN |
            // exprTERNARY )
            int alt29 = 2;
            alt29 = this.dfa29.predict(this.input);
            switch (alt29)
            {
                case 1:
                    // CPP.g:512:5: exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op=
                    // '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' )
                    // exprASSIGN
                {
                    this.pushFollow(FOLLOW_exprLOGICAL_OR_in_exprASSIGN2078);
                    this.exprLOGICAL_OR();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    // CPP.g:513:5: (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%='
                    // | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' )
                    int alt28 = 11;
                    switch (this.input.LA(1))
                    {
                        case 27:
                        {
                            alt28 = 1;
                        }
                            break;
                        case 57:
                        {
                            alt28 = 2;
                        }
                            break;
                        case 58:
                        {
                            alt28 = 3;
                        }
                            break;
                        case 59:
                        {
                            alt28 = 4;
                        }
                            break;
                        case 60:
                        {
                            alt28 = 5;
                        }
                            break;
                        case 61:
                        {
                            alt28 = 6;
                        }
                            break;
                        case 62:
                        {
                            alt28 = 7;
                        }
                            break;
                        case 63:
                        {
                            alt28 = 8;
                        }
                            break;
                        case 64:
                        {
                            alt28 = 9;
                        }
                            break;
                        case 65:
                        {
                            alt28 = 10;
                        }
                            break;
                        case 66:
                        {
                            alt28 = 11;
                        }
                            break;
                        default:
                            if (this.state.backtracking > 0) {
                                this.state.failed = true;
                                return;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 28, 0, this.input);
                            
                            throw nvae;
                    }
                    
                    switch (alt28)
                    {
                        case 1:
                            // CPP.g:513:7: op= '='
                        {
                            op = (Token) this.match(this.input, 27, FOLLOW_27_in_exprASSIGN2090);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.ASSIGN;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:514:7: op= '+='
                        {
                            op = (Token) this.match(this.input, 57, FOLLOW_57_in_exprASSIGN2104);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.PLUS_ASSIGN;
                            }
                            
                        }
                            break;
                        case 3:
                            // CPP.g:515:7: op= '-='
                        {
                            op = (Token) this.match(this.input, 58, FOLLOW_58_in_exprASSIGN2117);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.MINUS_ASSIGN;
                            }
                            
                        }
                            break;
                        case 4:
                            // CPP.g:516:7: op= '*='
                        {
                            op = (Token) this.match(this.input, 59, FOLLOW_59_in_exprASSIGN2130);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.STAR_ASSIGN;
                            }
                            
                        }
                            break;
                        case 5:
                            // CPP.g:517:7: op= '/='
                        {
                            op = (Token) this.match(this.input, 60, FOLLOW_60_in_exprASSIGN2143);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.DIV_ASSIGN;
                            }
                            
                        }
                            break;
                        case 6:
                            // CPP.g:518:7: op= '%='
                        {
                            op = (Token) this.match(this.input, 61, FOLLOW_61_in_exprASSIGN2156);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.MOD_ASSIGN;
                            }
                            
                        }
                            break;
                        case 7:
                            // CPP.g:519:7: op= '<<='
                        {
                            op = (Token) this.match(this.input, 62, FOLLOW_62_in_exprASSIGN2169);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.SHIFT_LEFT_ASSIGN;
                            }
                            
                        }
                            break;
                        case 8:
                            // CPP.g:520:7: op= '>>='
                        {
                            op = (Token) this.match(this.input, 63, FOLLOW_63_in_exprASSIGN2181);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.SHIFT_RIGHT_ASSIGN;
                            }
                            
                        }
                            break;
                        case 9:
                            // CPP.g:521:7: op= '&='
                        {
                            op = (Token) this.match(this.input, 64, FOLLOW_64_in_exprASSIGN2193);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.AND_ASSIGN;
                            }
                            
                        }
                            break;
                        case 10:
                            // CPP.g:522:7: op= '^='
                        {
                            op = (Token) this.match(this.input, 65, FOLLOW_65_in_exprASSIGN2206);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.XOR_ASSIGN;
                            }
                            
                        }
                            break;
                        case 11:
                            // CPP.g:523:7: op= '|='
                        {
                            op = (Token) this.match(this.input, 66, FOLLOW_66_in_exprASSIGN2219);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.OR_ASSIGN;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_exprASSIGN_in_exprASSIGN2234);
                    this.exprASSIGN();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new BinExprAST((ExprAST) ast1, type, op, (ExprAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:527:5: exprTERNARY
                {
                    this.pushFollow(FOLLOW_exprTERNARY_in_exprASSIGN2248);
                    this.exprTERNARY();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprASSIGN"
    
    // $ANTLR start "exprTERNARY"
    // CPP.g:530:1: exprTERNARY : exprLOGICAL_OR exprTERNARYEx ;
    public final void exprTERNARY() throws RecognitionException
    {
        try {
            // CPP.g:531:3: ( exprLOGICAL_OR exprTERNARYEx )
            // CPP.g:531:5: exprLOGICAL_OR exprTERNARYEx
            {
                this.pushFollow(FOLLOW_exprLOGICAL_OR_in_exprTERNARY2263);
                this.exprLOGICAL_OR();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprTERNARYEx_in_exprTERNARY2271);
                this.exprTERNARYEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprTERNARY"
    
    // $ANTLR start "exprTERNARYEx"
    // CPP.g:535:1: exprTERNARYEx : ( '?' exprASSIGN ':' exprASSIGN | );
    public final void exprTERNARYEx() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null, ast3 = null;
        
        try {
            // CPP.g:539:3: ( '?' exprASSIGN ':' exprASSIGN | )
            int alt30 = 2;
            int LA30_0 = this.input.LA(1);
            
            if ((LA30_0 == 67)) {
                alt30 = 1;
            }
            else if (((LA30_0 == EOF) || ((LA30_0 >= 23) && (LA30_0 <= 24)) || (LA30_0 == 26) || (LA30_0 == 29)
                    || (LA30_0 == 43) || (LA30_0 == 52))) {
                alt30 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 30, 0, this.input);
                
                throw nvae;
            }
            switch (alt30)
            {
                case 1:
                    // CPP.g:539:5: '?' exprASSIGN ':' exprASSIGN
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    this.match(this.input, 67, FOLLOW_67_in_exprTERNARYEx2296);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_exprASSIGN_in_exprTERNARYEx2303);
                    this.exprASSIGN();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    this.match(this.input, 52, FOLLOW_52_in_exprTERNARYEx2311);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_exprASSIGN_in_exprTERNARYEx2318);
                    this.exprASSIGN();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast3 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new TernaryExprAST((ExprAST) ast1, (ExprAST) ast2, (ExprAST) ast3);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:546:3:
                {
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprTERNARYEx"
    
    // $ANTLR start "exprLOGICAL_OR"
    // CPP.g:548:1: exprLOGICAL_OR : exprLOGICAL_AND exprLOGICAL_OREx ;
    public final void exprLOGICAL_OR() throws RecognitionException
    {
        try {
            // CPP.g:549:3: ( exprLOGICAL_AND exprLOGICAL_OREx )
            // CPP.g:549:5: exprLOGICAL_AND exprLOGICAL_OREx
            {
                this.pushFollow(FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OR2343);
                this.exprLOGICAL_AND();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OR2351);
                this.exprLOGICAL_OREx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprLOGICAL_OR"
    
    // $ANTLR start "exprLOGICAL_OREx"
    // CPP.g:553:1: exprLOGICAL_OREx : (op= '||' exprLOGICAL_AND exprLOGICAL_OREx | );
    public final void exprLOGICAL_OREx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:557:3: (op= '||' exprLOGICAL_AND exprLOGICAL_OREx | )
            int alt31 = 2;
            int LA31_0 = this.input.LA(1);
            
            if ((LA31_0 == 68)) {
                alt31 = 1;
            }
            else if (((LA31_0 == EOF) || ((LA31_0 >= 23) && (LA31_0 <= 24)) || ((LA31_0 >= 26) && (LA31_0 <= 27))
                    || (LA31_0 == 29) || (LA31_0 == 43) || (LA31_0 == 52) || ((LA31_0 >= 57) && (LA31_0 <= 67)))) {
                alt31 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 31, 0, this.input);
                
                throw nvae;
            }
            switch (alt31)
            {
                case 1:
                    // CPP.g:557:5: op= '||' exprLOGICAL_AND exprLOGICAL_OREx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    op = (Token) this.match(this.input, 68, FOLLOW_68_in_exprLOGICAL_OREx2378);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OREx2385);
                    this.exprLOGICAL_AND();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, BinExprAST.LOGICAL_OR, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OREx2399);
                    this.exprLOGICAL_OREx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:562:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprLOGICAL_OREx"
    
    // $ANTLR start "exprLOGICAL_AND"
    // CPP.g:565:1: exprLOGICAL_AND : exprBITWISE_OR exprLOGICAL_ANDEx ;
    public final void exprLOGICAL_AND() throws RecognitionException
    {
        try {
            // CPP.g:566:3: ( exprBITWISE_OR exprLOGICAL_ANDEx )
            // CPP.g:566:5: exprBITWISE_OR exprLOGICAL_ANDEx
            {
                this.pushFollow(FOLLOW_exprBITWISE_OR_in_exprLOGICAL_AND2420);
                this.exprBITWISE_OR();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_AND2428);
                this.exprLOGICAL_ANDEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprLOGICAL_AND"
    
    // $ANTLR start "exprLOGICAL_ANDEx"
    // CPP.g:570:1: exprLOGICAL_ANDEx : (op= '&&' exprBITWISE_OR exprLOGICAL_ANDEx | );
    public final void exprLOGICAL_ANDEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:574:3: (op= '&&' exprBITWISE_OR exprLOGICAL_ANDEx | )
            int alt32 = 2;
            int LA32_0 = this.input.LA(1);
            
            if ((LA32_0 == 69)) {
                alt32 = 1;
            }
            else if (((LA32_0 == EOF) || ((LA32_0 >= 23) && (LA32_0 <= 24)) || ((LA32_0 >= 26) && (LA32_0 <= 27))
                    || (LA32_0 == 29) || (LA32_0 == 43) || (LA32_0 == 52) || ((LA32_0 >= 57) && (LA32_0 <= 68)))) {
                alt32 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 32, 0, this.input);
                
                throw nvae;
            }
            switch (alt32)
            {
                case 1:
                    // CPP.g:574:5: op= '&&' exprBITWISE_OR exprLOGICAL_ANDEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    op = (Token) this.match(this.input, 69, FOLLOW_69_in_exprLOGICAL_ANDEx2455);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_exprBITWISE_OR_in_exprLOGICAL_ANDEx2462);
                    this.exprBITWISE_OR();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, BinExprAST.LOGICAL_AND, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_ANDEx2476);
                    this.exprLOGICAL_ANDEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:579:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprLOGICAL_ANDEx"
    
    // $ANTLR start "exprBITWISE_OR"
    // CPP.g:582:1: exprBITWISE_OR : exprBITWISE_XOR exprBITWISE_OREx ;
    public final void exprBITWISE_OR() throws RecognitionException
    {
        try {
            // CPP.g:583:3: ( exprBITWISE_XOR exprBITWISE_OREx )
            // CPP.g:583:5: exprBITWISE_XOR exprBITWISE_OREx
            {
                this.pushFollow(FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OR2497);
                this.exprBITWISE_XOR();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OR2505);
                this.exprBITWISE_OREx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprBITWISE_OR"
    
    // $ANTLR start "exprBITWISE_OREx"
    // CPP.g:587:1: exprBITWISE_OREx : (op= '|' exprBITWISE_XOR exprBITWISE_OREx | );
    public final void exprBITWISE_OREx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:591:3: (op= '|' exprBITWISE_XOR exprBITWISE_OREx | )
            int alt33 = 2;
            int LA33_0 = this.input.LA(1);
            
            if ((LA33_0 == 70)) {
                alt33 = 1;
            }
            else if (((LA33_0 == EOF) || ((LA33_0 >= 23) && (LA33_0 <= 24)) || ((LA33_0 >= 26) && (LA33_0 <= 27))
                    || (LA33_0 == 29) || (LA33_0 == 43) || (LA33_0 == 52) || ((LA33_0 >= 57) && (LA33_0 <= 69)))) {
                alt33 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 33, 0, this.input);
                
                throw nvae;
            }
            switch (alt33)
            {
                case 1:
                    // CPP.g:591:5: op= '|' exprBITWISE_XOR exprBITWISE_OREx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    op = (Token) this.match(this.input, 70, FOLLOW_70_in_exprBITWISE_OREx2532);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OREx2539);
                    this.exprBITWISE_XOR();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, BinExprAST.OR, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OREx2553);
                    this.exprBITWISE_OREx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:596:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprBITWISE_OREx"
    
    // $ANTLR start "exprBITWISE_XOR"
    // CPP.g:599:1: exprBITWISE_XOR : exprBITWISE_AND exprBITWISE_XOREx ;
    public final void exprBITWISE_XOR() throws RecognitionException
    {
        try {
            // CPP.g:600:3: ( exprBITWISE_AND exprBITWISE_XOREx )
            // CPP.g:600:5: exprBITWISE_AND exprBITWISE_XOREx
            {
                this.pushFollow(FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOR2574);
                this.exprBITWISE_AND();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOR2582);
                this.exprBITWISE_XOREx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprBITWISE_XOR"
    
    // $ANTLR start "exprBITWISE_XOREx"
    // CPP.g:604:1: exprBITWISE_XOREx : (op= '^' exprBITWISE_AND exprBITWISE_XOREx | );
    public final void exprBITWISE_XOREx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:608:3: (op= '^' exprBITWISE_AND exprBITWISE_XOREx | )
            int alt34 = 2;
            int LA34_0 = this.input.LA(1);
            
            if ((LA34_0 == 71)) {
                alt34 = 1;
            }
            else if (((LA34_0 == EOF) || ((LA34_0 >= 23) && (LA34_0 <= 24)) || ((LA34_0 >= 26) && (LA34_0 <= 27))
                    || (LA34_0 == 29) || (LA34_0 == 43) || (LA34_0 == 52) || ((LA34_0 >= 57) && (LA34_0 <= 70)))) {
                alt34 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 34, 0, this.input);
                
                throw nvae;
            }
            switch (alt34)
            {
                case 1:
                    // CPP.g:608:5: op= '^' exprBITWISE_AND exprBITWISE_XOREx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    op = (Token) this.match(this.input, 71, FOLLOW_71_in_exprBITWISE_XOREx2609);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOREx2616);
                    this.exprBITWISE_AND();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, BinExprAST.XOR, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOREx2630);
                    this.exprBITWISE_XOREx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:613:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprBITWISE_XOREx"
    
    // $ANTLR start "exprBITWISE_AND"
    // CPP.g:616:1: exprBITWISE_AND : exprEQUALITY exprBITWISE_ANDEx ;
    public final void exprBITWISE_AND() throws RecognitionException
    {
        try {
            // CPP.g:617:3: ( exprEQUALITY exprBITWISE_ANDEx )
            // CPP.g:617:5: exprEQUALITY exprBITWISE_ANDEx
            {
                this.pushFollow(FOLLOW_exprEQUALITY_in_exprBITWISE_AND2651);
                this.exprEQUALITY();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_AND2659);
                this.exprBITWISE_ANDEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprBITWISE_AND"
    
    // $ANTLR start "exprBITWISE_ANDEx"
    // CPP.g:621:1: exprBITWISE_ANDEx : (op= '&' exprEQUALITY exprBITWISE_ANDEx | );
    public final void exprBITWISE_ANDEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:625:3: (op= '&' exprEQUALITY exprBITWISE_ANDEx | )
            int alt35 = 2;
            int LA35_0 = this.input.LA(1);
            
            if ((LA35_0 == 44)) {
                alt35 = 1;
            }
            else if (((LA35_0 == EOF) || ((LA35_0 >= 23) && (LA35_0 <= 24)) || ((LA35_0 >= 26) && (LA35_0 <= 27))
                    || (LA35_0 == 29) || (LA35_0 == 43) || (LA35_0 == 52) || ((LA35_0 >= 57) && (LA35_0 <= 71)))) {
                alt35 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 35, 0, this.input);
                
                throw nvae;
            }
            switch (alt35)
            {
                case 1:
                    // CPP.g:625:5: op= '&' exprEQUALITY exprBITWISE_ANDEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    op = (Token) this.match(this.input, 44, FOLLOW_44_in_exprBITWISE_ANDEx2686);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_exprEQUALITY_in_exprBITWISE_ANDEx2693);
                    this.exprEQUALITY();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, BinExprAST.AND, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_ANDEx2707);
                    this.exprBITWISE_ANDEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:630:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprBITWISE_ANDEx"
    
    // $ANTLR start "exprEQUALITY"
    // CPP.g:633:1: exprEQUALITY : exprRELATION exprEQUALITYEx ;
    public final void exprEQUALITY() throws RecognitionException
    {
        try {
            // CPP.g:634:3: ( exprRELATION exprEQUALITYEx )
            // CPP.g:634:5: exprRELATION exprEQUALITYEx
            {
                this.pushFollow(FOLLOW_exprRELATION_in_exprEQUALITY2728);
                this.exprRELATION();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprEQUALITYEx_in_exprEQUALITY2736);
                this.exprEQUALITYEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprEQUALITY"
    
    // $ANTLR start "exprEQUALITYEx"
    // CPP.g:638:1: exprEQUALITYEx : ( (op= '==' | op= '!=' ) exprRELATION exprEQUALITYEx | );
    public final void exprEQUALITYEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        int type = 0;
        
        try {
            // CPP.g:643:3: ( (op= '==' | op= '!=' ) exprRELATION exprEQUALITYEx | )
            int alt37 = 2;
            int LA37_0 = this.input.LA(1);
            
            if ((((LA37_0 >= 72) && (LA37_0 <= 73)))) {
                alt37 = 1;
            }
            else if (((LA37_0 == EOF) || ((LA37_0 >= 23) && (LA37_0 <= 24)) || ((LA37_0 >= 26) && (LA37_0 <= 27))
                    || (LA37_0 == 29) || ((LA37_0 >= 43) && (LA37_0 <= 44)) || (LA37_0 == 52) || ((LA37_0 >= 57) && (LA37_0 <= 71)))) {
                alt37 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 37, 0, this.input);
                
                throw nvae;
            }
            switch (alt37)
            {
                case 1:
                    // CPP.g:643:5: (op= '==' | op= '!=' ) exprRELATION exprEQUALITYEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    // CPP.g:644:5: (op= '==' | op= '!=' )
                    int alt36 = 2;
                    int LA36_0 = this.input.LA(1);
                    
                    if ((LA36_0 == 72)) {
                        alt36 = 1;
                    }
                    else if ((LA36_0 == 73)) {
                        alt36 = 2;
                    }
                    else {
                        if (this.state.backtracking > 0) {
                            this.state.failed = true;
                            return;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 36, 0, this.input);
                        
                        throw nvae;
                    }
                    switch (alt36)
                    {
                        case 1:
                            // CPP.g:644:7: op= '=='
                        {
                            op = (Token) this.match(this.input, 72, FOLLOW_72_in_exprEQUALITYEx2765);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.EQUAL;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:645:7: op= '!='
                        {
                            op = (Token) this.match(this.input, 73, FOLLOW_73_in_exprEQUALITYEx2778);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.NOT_EQUAL;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_exprRELATION_in_exprEQUALITYEx2790);
                    this.exprRELATION();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, type, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprEQUALITYEx_in_exprEQUALITYEx2804);
                    this.exprEQUALITYEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:650:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprEQUALITYEx"
    
    // $ANTLR start "exprRELATION"
    // CPP.g:653:1: exprRELATION : exprSHIFT exprRELATIONEx ;
    public final void exprRELATION() throws RecognitionException
    {
        try {
            // CPP.g:654:3: ( exprSHIFT exprRELATIONEx )
            // CPP.g:654:5: exprSHIFT exprRELATIONEx
            {
                this.pushFollow(FOLLOW_exprSHIFT_in_exprRELATION2825);
                this.exprSHIFT();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprRELATIONEx_in_exprRELATION2833);
                this.exprRELATIONEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprRELATION"
    
    // $ANTLR start "exprRELATIONEx"
    // CPP.g:658:1: exprRELATIONEx : ( (op= '>' | op= '>=' | op= '<' | op= '<=' ) exprSHIFT
    // exprRELATIONEx | );
    public final void exprRELATIONEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        int type = 0;
        
        try {
            // CPP.g:663:3: ( (op= '>' | op= '>=' | op= '<' | op= '<=' ) exprSHIFT exprRELATIONEx |
            // )
            int alt39 = 2;
            int LA39_0 = this.input.LA(1);
            
            if ((((LA39_0 >= 74) && (LA39_0 <= 77)))) {
                alt39 = 1;
            }
            else if (((LA39_0 == EOF) || ((LA39_0 >= 23) && (LA39_0 <= 24)) || ((LA39_0 >= 26) && (LA39_0 <= 27))
                    || (LA39_0 == 29) || ((LA39_0 >= 43) && (LA39_0 <= 44)) || (LA39_0 == 52) || ((LA39_0 >= 57) && (LA39_0 <= 73)))) {
                alt39 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 39, 0, this.input);
                
                throw nvae;
            }
            switch (alt39)
            {
                case 1:
                    // CPP.g:663:5: (op= '>' | op= '>=' | op= '<' | op= '<=' ) exprSHIFT
                    // exprRELATIONEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    // CPP.g:664:5: (op= '>' | op= '>=' | op= '<' | op= '<=' )
                    int alt38 = 4;
                    switch (this.input.LA(1))
                    {
                        case 74:
                        {
                            alt38 = 1;
                        }
                            break;
                        case 75:
                        {
                            alt38 = 2;
                        }
                            break;
                        case 76:
                        {
                            alt38 = 3;
                        }
                            break;
                        case 77:
                        {
                            alt38 = 4;
                        }
                            break;
                        default:
                            if (this.state.backtracking > 0) {
                                this.state.failed = true;
                                return;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 38, 0, this.input);
                            
                            throw nvae;
                    }
                    
                    switch (alt38)
                    {
                        case 1:
                            // CPP.g:664:7: op= '>'
                        {
                            op = (Token) this.match(this.input, 74, FOLLOW_74_in_exprRELATIONEx2862);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.GREATER_THAN;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:665:7: op= '>='
                        {
                            op = (Token) this.match(this.input, 75, FOLLOW_75_in_exprRELATIONEx2876);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.GREATER_OR_EQUAL;
                            }
                            
                        }
                            break;
                        case 3:
                            // CPP.g:666:7: op= '<'
                        {
                            op = (Token) this.match(this.input, 76, FOLLOW_76_in_exprRELATIONEx2889);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.LESS_THAN;
                            }
                            
                        }
                            break;
                        case 4:
                            // CPP.g:667:7: op= '<='
                        {
                            op = (Token) this.match(this.input, 77, FOLLOW_77_in_exprRELATIONEx2903);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.LESS_OR_EQUAL;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_exprSHIFT_in_exprRELATIONEx2919);
                    this.exprSHIFT();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, type, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprRELATIONEx_in_exprRELATIONEx2933);
                    this.exprRELATIONEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:672:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprRELATIONEx"
    
    // $ANTLR start "exprSHIFT"
    // CPP.g:675:1: exprSHIFT : exprADD exprSHIFTEx ;
    public final void exprSHIFT() throws RecognitionException
    {
        try {
            // CPP.g:676:3: ( exprADD exprSHIFTEx )
            // CPP.g:676:5: exprADD exprSHIFTEx
            {
                this.pushFollow(FOLLOW_exprADD_in_exprSHIFT2954);
                this.exprADD();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprSHIFTEx_in_exprSHIFT2962);
                this.exprSHIFTEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprSHIFT"
    
    // $ANTLR start "exprSHIFTEx"
    // CPP.g:680:1: exprSHIFTEx : ( (op= '>>' | op= '<<' ) exprADD exprSHIFTEx | );
    public final void exprSHIFTEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        int type = 0;
        
        try {
            // CPP.g:685:3: ( (op= '>>' | op= '<<' ) exprADD exprSHIFTEx | )
            int alt41 = 2;
            int LA41_0 = this.input.LA(1);
            
            if ((((LA41_0 >= 78) && (LA41_0 <= 79)))) {
                alt41 = 1;
            }
            else if (((LA41_0 == EOF) || ((LA41_0 >= 23) && (LA41_0 <= 24)) || ((LA41_0 >= 26) && (LA41_0 <= 27))
                    || (LA41_0 == 29) || ((LA41_0 >= 43) && (LA41_0 <= 44)) || (LA41_0 == 52) || ((LA41_0 >= 57) && (LA41_0 <= 77)))) {
                alt41 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 41, 0, this.input);
                
                throw nvae;
            }
            switch (alt41)
            {
                case 1:
                    // CPP.g:685:5: (op= '>>' | op= '<<' ) exprADD exprSHIFTEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    // CPP.g:686:5: (op= '>>' | op= '<<' )
                    int alt40 = 2;
                    int LA40_0 = this.input.LA(1);
                    
                    if ((LA40_0 == 78)) {
                        alt40 = 1;
                    }
                    else if ((LA40_0 == 79)) {
                        alt40 = 2;
                    }
                    else {
                        if (this.state.backtracking > 0) {
                            this.state.failed = true;
                            return;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 40, 0, this.input);
                        
                        throw nvae;
                    }
                    switch (alt40)
                    {
                        case 1:
                            // CPP.g:686:7: op= '>>'
                        {
                            op = (Token) this.match(this.input, 78, FOLLOW_78_in_exprSHIFTEx2991);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.SHIFT_RIGHT;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:687:7: op= '<<'
                        {
                            op = (Token) this.match(this.input, 79, FOLLOW_79_in_exprSHIFTEx3003);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.SHIFT_LEFT;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_exprADD_in_exprSHIFTEx3018);
                    this.exprADD();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, type, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprSHIFTEx_in_exprSHIFTEx3032);
                    this.exprSHIFTEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:692:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprSHIFTEx"
    
    // $ANTLR start "exprADD"
    // CPP.g:695:1: exprADD : exprMUL exprADDEx ;
    public final void exprADD() throws RecognitionException
    {
        try {
            // CPP.g:696:3: ( exprMUL exprADDEx )
            // CPP.g:696:5: exprMUL exprADDEx
            {
                this.pushFollow(FOLLOW_exprMUL_in_exprADD3053);
                this.exprMUL();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprADDEx_in_exprADD3061);
                this.exprADDEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprADD"
    
    // $ANTLR start "exprADDEx"
    // CPP.g:700:1: exprADDEx : ( (op= '+' | op= '-' ) exprMUL exprADDEx | );
    public final void exprADDEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        int type = 0;
        
        try {
            // CPP.g:705:3: ( (op= '+' | op= '-' ) exprMUL exprADDEx | )
            int alt43 = 2;
            int LA43_0 = this.input.LA(1);
            
            if ((((LA43_0 >= 80) && (LA43_0 <= 81)))) {
                alt43 = 1;
            }
            else if (((LA43_0 == EOF) || ((LA43_0 >= 23) && (LA43_0 <= 24)) || ((LA43_0 >= 26) && (LA43_0 <= 27))
                    || (LA43_0 == 29) || ((LA43_0 >= 43) && (LA43_0 <= 44)) || (LA43_0 == 52) || ((LA43_0 >= 57) && (LA43_0 <= 79)))) {
                alt43 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 43, 0, this.input);
                
                throw nvae;
            }
            switch (alt43)
            {
                case 1:
                    // CPP.g:705:5: (op= '+' | op= '-' ) exprMUL exprADDEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    // CPP.g:706:5: (op= '+' | op= '-' )
                    int alt42 = 2;
                    int LA42_0 = this.input.LA(1);
                    
                    if ((LA42_0 == 80)) {
                        alt42 = 1;
                    }
                    else if ((LA42_0 == 81)) {
                        alt42 = 2;
                    }
                    else {
                        if (this.state.backtracking > 0) {
                            this.state.failed = true;
                            return;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 42, 0, this.input);
                        
                        throw nvae;
                    }
                    switch (alt42)
                    {
                        case 1:
                            // CPP.g:706:7: op= '+'
                        {
                            op = (Token) this.match(this.input, 80, FOLLOW_80_in_exprADDEx3090);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.PLUS;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:707:7: op= '-'
                        {
                            op = (Token) this.match(this.input, 81, FOLLOW_81_in_exprADDEx3104);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.MINUS;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_exprMUL_in_exprADDEx3121);
                    this.exprMUL();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, type, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprADDEx_in_exprADDEx3135);
                    this.exprADDEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:712:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprADDEx"
    
    // $ANTLR start "exprMUL"
    // CPP.g:715:1: exprMUL : exprUNARY exprMULEx ;
    public final void exprMUL() throws RecognitionException
    {
        try {
            // CPP.g:716:3: ( exprUNARY exprMULEx )
            // CPP.g:716:5: exprUNARY exprMULEx
            {
                this.pushFollow(FOLLOW_exprUNARY_in_exprMUL3154);
                this.exprUNARY();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprMULEx_in_exprMUL3162);
                this.exprMULEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprMUL"
    
    // $ANTLR start "exprMULEx"
    // CPP.g:720:1: exprMULEx : ( (op= '*' | op= '/' | op= '%' ) exprUNARY exprMULEx | );
    public final void exprMULEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null, ast2 = null;
        int type = 0;
        
        try {
            // CPP.g:725:3: ( (op= '*' | op= '/' | op= '%' ) exprUNARY exprMULEx | )
            int alt45 = 2;
            int LA45_0 = this.input.LA(1);
            
            if (((LA45_0 == 30) || ((LA45_0 >= 82) && (LA45_0 <= 83)))) {
                alt45 = 1;
            }
            else if (((LA45_0 == EOF) || ((LA45_0 >= 23) && (LA45_0 <= 24)) || ((LA45_0 >= 26) && (LA45_0 <= 27))
                    || (LA45_0 == 29) || ((LA45_0 >= 43) && (LA45_0 <= 44)) || (LA45_0 == 52) || ((LA45_0 >= 57) && (LA45_0 <= 81)))) {
                alt45 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 45, 0, this.input);
                
                throw nvae;
            }
            switch (alt45)
            {
                case 1:
                    // CPP.g:725:5: (op= '*' | op= '/' | op= '%' ) exprUNARY exprMULEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    // CPP.g:726:5: (op= '*' | op= '/' | op= '%' )
                    int alt44 = 3;
                    switch (this.input.LA(1))
                    {
                        case 30:
                        {
                            alt44 = 1;
                        }
                            break;
                        case 82:
                        {
                            alt44 = 2;
                        }
                            break;
                        case 83:
                        {
                            alt44 = 3;
                        }
                            break;
                        default:
                            if (this.state.backtracking > 0) {
                                this.state.failed = true;
                                return;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 44, 0, this.input);
                            
                            throw nvae;
                    }
                    
                    switch (alt44)
                    {
                        case 1:
                            // CPP.g:726:7: op= '*'
                        {
                            op = (Token) this.match(this.input, 30, FOLLOW_30_in_exprMULEx3189);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.STAR;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:727:7: op= '/'
                        {
                            op = (Token) this.match(this.input, 82, FOLLOW_82_in_exprMULEx3203);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.DIV;
                            }
                            
                        }
                            break;
                        case 3:
                            // CPP.g:728:7: op= '%'
                        {
                            op = (Token) this.match(this.input, 83, FOLLOW_83_in_exprMULEx3217);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = BinExprAST.MOD;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_exprUNARY_in_exprMULEx3234);
                    this.exprUNARY();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.in = new BinExprAST((ExprAST) ast1, type, op, (ExprAST) ast2);
                    }
                    this.pushFollow(FOLLOW_exprMULEx_in_exprMULEx3248);
                    this.exprMULEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:733:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprMULEx"
    
    // $ANTLR start "exprUNARY"
    // CPP.g:736:1: exprUNARY : ( (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' | op=
    // '!' | op= '~' ) exprUNARY | exprPostfix );
    public final void exprUNARY() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null;
        int type = 0;
        
        try {
            // CPP.g:741:3: ( (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' | op= '!'
            // | op= '~' ) exprUNARY | exprPostfix )
            int alt47 = 2;
            int LA47_0 = this.input.LA(1);
            
            if (((LA47_0 == 30) || (LA47_0 == 44) || ((LA47_0 >= 80) && (LA47_0 <= 81)) || ((LA47_0 >= 84) && (LA47_0 <= 87)))) {
                alt47 = 1;
            }
            else if ((((LA47_0 >= IDENTIFIER) && (LA47_0 <= FLOATING_POINT_LITERAL)) || (LA47_0 == 42))) {
                alt47 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 47, 0, this.input);
                
                throw nvae;
            }
            switch (alt47)
            {
                case 1:
                    // CPP.g:741:5: (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' |
                    // op= '!' | op= '~' ) exprUNARY
                {
                    // CPP.g:741:5: (op= '+' | op= '-' | op= '++' | op= '--' | op= '*' | op= '&' |
                    // op= '!' | op= '~' )
                    int alt46 = 8;
                    switch (this.input.LA(1))
                    {
                        case 80:
                        {
                            alt46 = 1;
                        }
                            break;
                        case 81:
                        {
                            alt46 = 2;
                        }
                            break;
                        case 84:
                        {
                            alt46 = 3;
                        }
                            break;
                        case 85:
                        {
                            alt46 = 4;
                        }
                            break;
                        case 30:
                        {
                            alt46 = 5;
                        }
                            break;
                        case 44:
                        {
                            alt46 = 6;
                        }
                            break;
                        case 86:
                        {
                            alt46 = 7;
                        }
                            break;
                        case 87:
                        {
                            alt46 = 8;
                        }
                            break;
                        default:
                            if (this.state.backtracking > 0) {
                                this.state.failed = true;
                                return;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 46, 0, this.input);
                            
                            throw nvae;
                    }
                    
                    switch (alt46)
                    {
                        case 1:
                            // CPP.g:741:7: op= '+'
                        {
                            op = (Token) this.match(this.input, 80, FOLLOW_80_in_exprUNARY3277);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.UNARY_PLUS;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:742:7: op= '-'
                        {
                            op = (Token) this.match(this.input, 81, FOLLOW_81_in_exprUNARY3291);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.UNARY_MINUS;
                            }
                            
                        }
                            break;
                        case 3:
                            // CPP.g:743:7: op= '++'
                        {
                            op = (Token) this.match(this.input, 84, FOLLOW_84_in_exprUNARY3305);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.PRE_INC;
                            }
                            
                        }
                            break;
                        case 4:
                            // CPP.g:744:7: op= '--'
                        {
                            op = (Token) this.match(this.input, 85, FOLLOW_85_in_exprUNARY3318);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.PRE_DEC;
                            }
                            
                        }
                            break;
                        case 5:
                            // CPP.g:745:7: op= '*'
                        {
                            op = (Token) this.match(this.input, 30, FOLLOW_30_in_exprUNARY3331);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.INDIRECTION;
                            }
                            
                        }
                            break;
                        case 6:
                            // CPP.g:746:7: op= '&'
                        {
                            op = (Token) this.match(this.input, 44, FOLLOW_44_in_exprUNARY3345);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.ADDR_OF;
                            }
                            
                        }
                            break;
                        case 7:
                            // CPP.g:747:7: op= '!'
                        {
                            op = (Token) this.match(this.input, 86, FOLLOW_86_in_exprUNARY3359);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.LOGICAL_NOT;
                            }
                            
                        }
                            break;
                        case 8:
                            // CPP.g:748:7: op= '~'
                        {
                            op = (Token) this.match(this.input, 87, FOLLOW_87_in_exprUNARY3373);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.NOT;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    this.pushFollow(FOLLOW_exprUNARY_in_exprUNARY3389);
                    this.exprUNARY();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new UnaryExprAST(type, op, (ExprAST) ast1);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:752:5: exprPostfix
                {
                    this.pushFollow(FOLLOW_exprPostfix_in_exprUNARY3404);
                    this.exprPostfix();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprUNARY"
    
    // $ANTLR start "exprPostfix"
    // CPP.g:755:1: exprPostfix : exprPrimary exprPostfixEx ;
    public final void exprPostfix() throws RecognitionException
    {
        try {
            // CPP.g:756:3: ( exprPrimary exprPostfixEx )
            // CPP.g:756:5: exprPrimary exprPostfixEx
            {
                this.pushFollow(FOLLOW_exprPrimary_in_exprPostfix3419);
                this.exprPrimary();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.in = this.out;
                }
                this.pushFollow(FOLLOW_exprPostfixEx_in_exprPostfix3427);
                this.exprPostfixEx();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprPostfix"
    
    // $ANTLR start "exprPostfixEx"
    // CPP.g:760:1: exprPostfixEx : ( (op= '++' | op= '--' ) exprPostfixEx | );
    public final void exprPostfixEx() throws RecognitionException
    {
        Token op = null;
        
        AST ast1 = null;
        int type = 0;
        
        try {
            // CPP.g:765:3: ( (op= '++' | op= '--' ) exprPostfixEx | )
            int alt49 = 2;
            int LA49_0 = this.input.LA(1);
            
            if ((((LA49_0 >= 84) && (LA49_0 <= 85)))) {
                alt49 = 1;
            }
            else if (((LA49_0 == EOF) || ((LA49_0 >= 23) && (LA49_0 <= 24)) || ((LA49_0 >= 26) && (LA49_0 <= 27))
                    || ((LA49_0 >= 29) && (LA49_0 <= 30)) || ((LA49_0 >= 43) && (LA49_0 <= 44)) || (LA49_0 == 52) || ((LA49_0 >= 57) && (LA49_0 <= 83)))) {
                alt49 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 49, 0, this.input);
                
                throw nvae;
            }
            switch (alt49)
            {
                case 1:
                    // CPP.g:765:5: (op= '++' | op= '--' ) exprPostfixEx
                {
                    if (this.state.backtracking == 0) {
                        ast1 = this.in;
                    }
                    // CPP.g:766:5: (op= '++' | op= '--' )
                    int alt48 = 2;
                    int LA48_0 = this.input.LA(1);
                    
                    if ((LA48_0 == 84)) {
                        alt48 = 1;
                    }
                    else if ((LA48_0 == 85)) {
                        alt48 = 2;
                    }
                    else {
                        if (this.state.backtracking > 0) {
                            this.state.failed = true;
                            return;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 48, 0, this.input);
                        
                        throw nvae;
                    }
                    switch (alt48)
                    {
                        case 1:
                            // CPP.g:766:7: op= '++'
                        {
                            op = (Token) this.match(this.input, 84, FOLLOW_84_in_exprPostfixEx3456);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.POST_INC;
                            }
                            
                        }
                            break;
                        case 2:
                            // CPP.g:767:7: op= '--'
                        {
                            op = (Token) this.match(this.input, 85, FOLLOW_85_in_exprPostfixEx3469);
                            if (this.state.failed) {
                                return;
                            }
                            if (this.state.backtracking == 0) {
                                type = UnaryExprAST.POST_DEC;
                            }
                            
                        }
                            break;
                        
                    }
                    
                    if (this.state.backtracking == 0) {
                        this.in = new UnaryExprAST(type, op, (ExprAST) ast1);
                    }
                    this.pushFollow(FOLLOW_exprPostfixEx_in_exprPostfixEx3491);
                    this.exprPostfixEx();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:771:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = this.in;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprPostfixEx"
    
    // $ANTLR start "exprPrimary"
    // CPP.g:774:1: exprPrimary : ( literal | id= IDENTIFIER | '(' expr ')' | callExpr |
    // arrayElement );
    public final void exprPrimary() throws RecognitionException
    {
        Token id = null;
        
        try {
            // CPP.g:775:3: ( literal | id= IDENTIFIER | '(' expr ')' | callExpr | arrayElement )
            int alt50 = 5;
            switch (this.input.LA(1))
            {
                case CHARACTER_LITERAL:
                case STRING_LITERAL:
                case HEX_LITERAL:
                case DECIMAL_LITERAL:
                case OCTAL_LITERAL:
                case FLOATING_POINT_LITERAL:
                {
                    alt50 = 1;
                }
                    break;
                case IDENTIFIER:
                {
                    switch (this.input.LA(2))
                    {
                        case 42:
                        {
                            alt50 = 4;
                        }
                            break;
                        case 25:
                        {
                            alt50 = 5;
                        }
                            break;
                        case EOF:
                        case 23:
                        case 24:
                        case 26:
                        case 27:
                        case 29:
                        case 30:
                        case 43:
                        case 44:
                        case 52:
                        case 57:
                        case 58:
                        case 59:
                        case 60:
                        case 61:
                        case 62:
                        case 63:
                        case 64:
                        case 65:
                        case 66:
                        case 67:
                        case 68:
                        case 69:
                        case 70:
                        case 71:
                        case 72:
                        case 73:
                        case 74:
                        case 75:
                        case 76:
                        case 77:
                        case 78:
                        case 79:
                        case 80:
                        case 81:
                        case 82:
                        case 83:
                        case 84:
                        case 85:
                        {
                            alt50 = 2;
                        }
                            break;
                        default:
                            if (this.state.backtracking > 0) {
                                this.state.failed = true;
                                return;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 50, 2, this.input);
                            
                            throw nvae;
                    }
                    
                }
                    break;
                case 42:
                {
                    alt50 = 3;
                }
                    break;
                default:
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 50, 0, this.input);
                    
                    throw nvae;
            }
            
            switch (alt50)
            {
                case 1:
                    // CPP.g:775:5: literal
                {
                    this.pushFollow(FOLLOW_literal_in_exprPrimary3512);
                    this.literal();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:776:5: id= IDENTIFIER
                {
                    id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_exprPrimary3521);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new VarExprAST(id);
                    }
                    
                }
                    break;
                case 3:
                    // CPP.g:777:5: '(' expr ')'
                {
                    this.match(this.input, 42, FOLLOW_42_in_exprPrimary3529);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_expr_in_exprPrimary3531);
                    this.expr();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    this.match(this.input, 43, FOLLOW_43_in_exprPrimary3533);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 4:
                    // CPP.g:778:5: callExpr
                {
                    this.pushFollow(FOLLOW_callExpr_in_exprPrimary3540);
                    this.callExpr();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 5:
                    // CPP.g:779:5: arrayElement
                {
                    this.pushFollow(FOLLOW_arrayElement_in_exprPrimary3547);
                    this.arrayElement();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "exprPrimary"
    
    // $ANTLR start "callExpr"
    // CPP.g:782:1: callExpr : id= IDENTIFIER '(' exprList ')' ;
    public final void callExpr() throws RecognitionException
    {
        Token id = null;
        
        AST ast1 = null;
        
        try {
            // CPP.g:786:3: (id= IDENTIFIER '(' exprList ')' )
            // CPP.g:786:5: id= IDENTIFIER '(' exprList ')'
            {
                id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_callExpr3568);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 42, FOLLOW_42_in_callExpr3575);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_exprList_in_callExpr3582);
                this.exprList();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 43, FOLLOW_43_in_callExpr3591);
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    this.out = new CallExprAST(id, (ExprListAST) ast1);
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "callExpr"
    
    // $ANTLR start "arrayElement"
    // CPP.g:793:1: arrayElement : id= IDENTIFIER '[' expr ']' index ;
    public final void arrayElement() throws RecognitionException
    {
        Token id = null;
        
        AST ast1 = null, ast2 = null, ast3 = null;
        
        try {
            // CPP.g:797:3: (id= IDENTIFIER '[' expr ']' index )
            // CPP.g:797:5: id= IDENTIFIER '[' expr ']' index
            {
                id = (Token) this.match(this.input, IDENTIFIER, FOLLOW_IDENTIFIER_in_arrayElement3618);
                if (this.state.failed) {
                    return;
                }
                this.match(this.input, 25, FOLLOW_25_in_arrayElement3625);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_expr_in_arrayElement3632);
                this.expr();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast1 = this.out;
                    this.out = null;
                }
                this.match(this.input, 26, FOLLOW_26_in_arrayElement3640);
                if (this.state.failed) {
                    return;
                }
                this.pushFollow(FOLLOW_index_in_arrayElement3647);
                this.index();
                
                this.state._fsp--;
                if (this.state.failed) {
                    return;
                }
                if (this.state.backtracking == 0) {
                    ast2 = this.out;
                    this.out = null;
                }
                if (this.state.backtracking == 0) {
                    
                    ast3 = new ExprListAST((ExprAST) ast1, (ExprListAST) ast2);
                    this.out = new EleExprAST(id, (ExprListAST) ast3);
                    
                }
                
            }
            
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "arrayElement"
    
    // $ANTLR start "index"
    // CPP.g:808:1: index : ( '[' expr ']' index | );
    public final void index() throws RecognitionException
    {
        
        AST ast1 = null, ast2 = null;
        
        try {
            // CPP.g:812:3: ( '[' expr ']' index | )
            int alt51 = 2;
            int LA51_0 = this.input.LA(1);
            
            if ((LA51_0 == 25)) {
                alt51 = 1;
            }
            else if (((LA51_0 == EOF) || ((LA51_0 >= 23) && (LA51_0 <= 24)) || ((LA51_0 >= 26) && (LA51_0 <= 27))
                    || ((LA51_0 >= 29) && (LA51_0 <= 30)) || ((LA51_0 >= 43) && (LA51_0 <= 44)) || (LA51_0 == 52) || ((LA51_0 >= 57) && (LA51_0 <= 85)))) {
                alt51 = 2;
            }
            else {
                if (this.state.backtracking > 0) {
                    this.state.failed = true;
                    return;
                }
                NoViableAltException nvae = new NoViableAltException("", 51, 0, this.input);
                
                throw nvae;
            }
            switch (alt51)
            {
                case 1:
                    // CPP.g:812:5: '[' expr ']' index
                {
                    this.match(this.input, 25, FOLLOW_25_in_index3674);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_expr_in_index3681);
                    this.expr();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast1 = this.out;
                        this.out = null;
                    }
                    this.match(this.input, 26, FOLLOW_26_in_index3689);
                    if (this.state.failed) {
                        return;
                    }
                    this.pushFollow(FOLLOW_index_in_index3696);
                    this.index();
                    
                    this.state._fsp--;
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        ast2 = this.out;
                        this.out = null;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new ExprListAST((ExprAST) ast1, (ExprListAST) ast2);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:817:5:
                {
                    if (this.state.backtracking == 0) {
                        this.out = new EmptyExprListAST();
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "index"
    
    // $ANTLR start "literal"
    // CPP.g:820:1: literal : (val= CHARACTER_LITERAL | val= STRING_LITERAL | val= HEX_LITERAL |
    // val= DECIMAL_LITERAL | val= OCTAL_LITERAL | val= FLOATING_POINT_LITERAL );
    public final void literal() throws RecognitionException
    {
        Token val = null;
        
        try {
            // CPP.g:821:3: (val= CHARACTER_LITERAL | val= STRING_LITERAL | val= HEX_LITERAL | val=
            // DECIMAL_LITERAL | val= OCTAL_LITERAL | val= FLOATING_POINT_LITERAL )
            int alt52 = 6;
            switch (this.input.LA(1))
            {
                case CHARACTER_LITERAL:
                {
                    alt52 = 1;
                }
                    break;
                case STRING_LITERAL:
                {
                    alt52 = 2;
                }
                    break;
                case HEX_LITERAL:
                {
                    alt52 = 3;
                }
                    break;
                case DECIMAL_LITERAL:
                {
                    alt52 = 4;
                }
                    break;
                case OCTAL_LITERAL:
                {
                    alt52 = 5;
                }
                    break;
                case FLOATING_POINT_LITERAL:
                {
                    alt52 = 6;
                }
                    break;
                default:
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 52, 0, this.input);
                    
                    throw nvae;
            }
            
            switch (alt52)
            {
                case 1:
                    // CPP.g:821:5: val= CHARACTER_LITERAL
                {
                    val = (Token) this.match(this.input, CHARACTER_LITERAL, FOLLOW_CHARACTER_LITERAL_in_literal3727);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new CharLiteralAST(val);
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:822:5: val= STRING_LITERAL
                {
                    val = (Token) this.match(this.input, STRING_LITERAL, FOLLOW_STRING_LITERAL_in_literal3737);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new StringLiteralAST(val);
                    }
                    
                }
                    break;
                case 3:
                    // CPP.g:823:5: val= HEX_LITERAL
                {
                    val = (Token) this.match(this.input, HEX_LITERAL, FOLLOW_HEX_LITERAL_in_literal3747);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new IntLiteralAST(val);
                    }
                    
                }
                    break;
                case 4:
                    // CPP.g:824:5: val= DECIMAL_LITERAL
                {
                    val = (Token) this.match(this.input, DECIMAL_LITERAL, FOLLOW_DECIMAL_LITERAL_in_literal3757);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new IntLiteralAST(val);
                    }
                    
                }
                    break;
                case 5:
                    // CPP.g:825:5: val= OCTAL_LITERAL
                {
                    val = (Token) this.match(this.input, OCTAL_LITERAL, FOLLOW_OCTAL_LITERAL_in_literal3767);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new IntLiteralAST(val);
                    }
                    
                }
                    break;
                case 6:
                    // CPP.g:826:5: val= FLOATING_POINT_LITERAL
                {
                    val =
                          (Token) this.match(this.input, FLOATING_POINT_LITERAL,
                                             FOLLOW_FLOATING_POINT_LITERAL_in_literal3777);
                    if (this.state.failed) {
                        return;
                    }
                    if (this.state.backtracking == 0) {
                        this.out = new FloatLiteralAST(val);
                    }
                    
                }
                    break;
                
            }
        }
        catch (RecognitionException re) {
            this.reportError(re);
            this.recover(this.input, re);
        } finally {
        }
        return;
    }
    
    // $ANTLR end "literal"
    
    // $ANTLR start synpred2_CPP
    public final void synpred2_CPP_fragment() throws RecognitionException
    {
        // CPP.g:59:5: ( varDecl )
        // CPP.g:59:5: varDecl
        {
            this.pushFollow(FOLLOW_varDecl_in_synpred2_CPP121);
            this.varDecl();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            
        }
    }
    
    // $ANTLR end synpred2_CPP
    
    // $ANTLR start synpred29_CPP
    public final void synpred29_CPP_fragment() throws RecognitionException
    {
        // CPP.g:300:5: ( stmt stmtList )
        // CPP.g:300:5: stmt stmtList
        {
            this.pushFollow(FOLLOW_stmt_in_synpred29_CPP1152);
            this.stmt();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            this.pushFollow(FOLLOW_stmtList_in_synpred29_CPP1160);
            this.stmtList();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            
        }
    }
    
    // $ANTLR end synpred29_CPP
    
    // $ANTLR start synpred42_CPP
    public final void synpred42_CPP_fragment() throws RecognitionException
    {
        // CPP.g:351:5: ( 'else' stmt )
        // CPP.g:351:5: 'else' stmt
        {
            this.match(this.input, 46, FOLLOW_46_in_synpred42_CPP1400);
            if (this.state.failed) {
                return;
            }
            this.pushFollow(FOLLOW_stmt_in_synpred42_CPP1407);
            this.stmt();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            
        }
    }
    
    // $ANTLR end synpred42_CPP
    
    // $ANTLR start synpred43_CPP
    public final void synpred43_CPP_fragment() throws RecognitionException
    {
        // CPP.g:367:5: ( forInit )
        // CPP.g:367:5: forInit
        {
            this.pushFollow(FOLLOW_forInit_in_synpred43_CPP1450);
            this.forInit();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            
        }
    }
    
    // $ANTLR end synpred43_CPP
    
    // $ANTLR start synpred45_CPP
    public final void synpred45_CPP_fragment() throws RecognitionException
    {
        // CPP.g:370:5: ( exprList )
        // CPP.g:370:5: exprList
        {
            this.pushFollow(FOLLOW_exprList_in_synpred45_CPP1475);
            this.exprList();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            
        }
    }
    
    // $ANTLR end synpred45_CPP
    
    // $ANTLR start synpred47_CPP
    public final void synpred47_CPP_fragment() throws RecognitionException
    {
        // CPP.g:381:5: ( exprList ';' )
        // CPP.g:381:5: exprList ';'
        {
            this.pushFollow(FOLLOW_exprList_in_synpred47_CPP1526);
            this.exprList();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            this.match(this.input, 23, FOLLOW_23_in_synpred47_CPP1528);
            if (this.state.failed) {
                return;
            }
            
        }
    }
    
    // $ANTLR end synpred47_CPP
    
    // $ANTLR start synpred61_CPP
    public final void synpred61_CPP_fragment() throws RecognitionException
    {
       Token op = null;
        
        // CPP.g:512:5: ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op=
        // '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN )
        // CPP.g:512:5: exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op=
        // '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN
        {
            this.pushFollow(FOLLOW_exprLOGICAL_OR_in_synpred61_CPP2078);
            this.exprLOGICAL_OR();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            // CPP.g:513:5: (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op=
            // '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' )
            int alt54 = 11;
            switch (this.input.LA(1))
            {
                case 27:
                {
                    alt54 = 1;
                }
                    break;
                case 57:
                {
                    alt54 = 2;
                }
                    break;
                case 58:
                {
                    alt54 = 3;
                }
                    break;
                case 59:
                {
                    alt54 = 4;
                }
                    break;
                case 60:
                {
                    alt54 = 5;
                }
                    break;
                case 61:
                {
                    alt54 = 6;
                }
                    break;
                case 62:
                {
                    alt54 = 7;
                }
                    break;
                case 63:
                {
                    alt54 = 8;
                }
                    break;
                case 64:
                {
                    alt54 = 9;
                }
                    break;
                case 65:
                {
                    alt54 = 10;
                }
                    break;
                case 66:
                {
                    alt54 = 11;
                }
                    break;
                default:
                    if (this.state.backtracking > 0) {
                        this.state.failed = true;
                        return;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 54, 0, this.input);
                    
                    throw nvae;
            }
            
            switch (alt54)
            {
                case 1:
                    // CPP.g:513:7: op= '='
                {
                    op = (Token) this.match(this.input, 27, FOLLOW_27_in_synpred61_CPP2090);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 2:
                    // CPP.g:514:7: op= '+='
                {
                    op = (Token) this.match(this.input, 57, FOLLOW_57_in_synpred61_CPP2104);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 3:
                    // CPP.g:515:7: op= '-='
                {
                    op = (Token) this.match(this.input, 58, FOLLOW_58_in_synpred61_CPP2117);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 4:
                    // CPP.g:516:7: op= '*='
                {
                    op = (Token) this.match(this.input, 59, FOLLOW_59_in_synpred61_CPP2130);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 5:
                    // CPP.g:517:7: op= '/='
                {
                    op = (Token) this.match(this.input, 60, FOLLOW_60_in_synpred61_CPP2143);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 6:
                    // CPP.g:518:7: op= '%='
                {
                    op = (Token) this.match(this.input, 61, FOLLOW_61_in_synpred61_CPP2156);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 7:
                    // CPP.g:519:7: op= '<<='
                {
                    op = (Token) this.match(this.input, 62, FOLLOW_62_in_synpred61_CPP2169);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 8:
                    // CPP.g:520:7: op= '>>='
                {
                    op = (Token) this.match(this.input, 63, FOLLOW_63_in_synpred61_CPP2181);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 9:
                    // CPP.g:521:7: op= '&='
                {
                    op = (Token) this.match(this.input, 64, FOLLOW_64_in_synpred61_CPP2193);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 10:
                    // CPP.g:522:7: op= '^='
                {
                    op = (Token) this.match(this.input, 65, FOLLOW_65_in_synpred61_CPP2206);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                case 11:
                    // CPP.g:523:7: op= '|='
                {
                    op = (Token) this.match(this.input, 66, FOLLOW_66_in_synpred61_CPP2219);
                    if (this.state.failed) {
                        return;
                    }
                    
                }
                    break;
                
            }
            
            this.pushFollow(FOLLOW_exprASSIGN_in_synpred61_CPP2234);
            this.exprASSIGN();
            
            this.state._fsp--;
            if (this.state.failed) {
                return;
            }
            
        }
    }
    
    // $ANTLR end synpred61_CPP
    
    // Delegated rules
    
    public final boolean synpred29_CPP()
    {
        this.state.backtracking++;
        int start = this.input.mark();
        try {
            this.synpred29_CPP_fragment(); // can never throw exception
        }
        catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !this.state.failed;
        this.input.rewind(start);
        this.state.backtracking--;
        this.state.failed = false;
        return success;
    }
    
    public final boolean synpred47_CPP()
    {
        this.state.backtracking++;
        int start = this.input.mark();
        try {
            this.synpred47_CPP_fragment(); // can never throw exception
        }
        catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !this.state.failed;
        this.input.rewind(start);
        this.state.backtracking--;
        this.state.failed = false;
        return success;
    }
    
    public final boolean synpred61_CPP()
    {
        this.state.backtracking++;
        int start = this.input.mark();
        try {
            this.synpred61_CPP_fragment(); // can never throw exception
        }
        catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !this.state.failed;
        this.input.rewind(start);
        this.state.backtracking--;
        this.state.failed = false;
        return success;
    }
    
    public final boolean synpred43_CPP()
    {
        this.state.backtracking++;
        int start = this.input.mark();
        try {
            this.synpred43_CPP_fragment(); // can never throw exception
        }
        catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !this.state.failed;
        this.input.rewind(start);
        this.state.backtracking--;
        this.state.failed = false;
        return success;
    }
    
    public final boolean synpred2_CPP()
    {
        this.state.backtracking++;
        int start = this.input.mark();
        try {
            this.synpred2_CPP_fragment(); // can never throw exception
        }
        catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !this.state.failed;
        this.input.rewind(start);
        this.state.backtracking--;
        this.state.failed = false;
        return success;
    }
    
    public final boolean synpred45_CPP()
    {
        this.state.backtracking++;
        int start = this.input.mark();
        try {
            this.synpred45_CPP_fragment(); // can never throw exception
        }
        catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !this.state.failed;
        this.input.rewind(start);
        this.state.backtracking--;
        this.state.failed = false;
        return success;
    }
    
    public final boolean synpred42_CPP()
    {
        this.state.backtracking++;
        int start = this.input.mark();
        try {
            this.synpred42_CPP_fragment(); // can never throw exception
        }
        catch (RecognitionException re) {
            System.err.println("impossible: " + re);
        }
        boolean success = !this.state.failed;
        this.input.rewind(start);
        this.state.backtracking--;
        this.state.failed = false;
        return success;
    }
    
    protected DFA2 dfa2 = new DFA2(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA18 dfa18 = new DFA18(this);
    protected DFA21 dfa21 = new DFA21(this);
    protected DFA29 dfa29 = new DFA29(this);
    static final String DFA2_eotS = "\17\uffff";
    static final String DFA2_eofS = "\17\uffff";
    static final String DFA2_minS = "\1\26\13\0\3\uffff";
    static final String DFA2_maxS = "\1\51\13\0\3\uffff";
    static final String DFA2_acceptS = "\14\uffff\1\2\1\1\1\3";
    static final String DFA2_specialS = "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\3\uffff}>";
    static final String[] DFA2_transitionS = { "\1\14\10\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1" + "\13",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "", "", "" };
    
    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;
    
    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }
    
    class DFA2
            extends DFA
    {
        
        public DFA2(BaseRecognizer recognizer)
        {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        
        @Override
        public String getDescription()
        {
            return "58:1: declaration : ( varDecl | directive | funcDecl );";
        }
        
        @Override
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException
        {
            TokenStream input = (TokenStream) _input;
            int _s = s;
            switch (s)
            {
                case 0:
                    
                    int index2_1 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_1);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 1:
                    
                    int index2_2 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_2);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 2:
                    
                    int index2_3 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_3);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 3:
                    
                    int index2_4 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_4);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 4:
                    
                    int index2_5 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_5);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 5:
                    
                    int index2_6 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_6);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 6:
                    
                    int index2_7 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_7);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 7:
                    
                    int index2_8 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_8);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 8:
                    
                    int index2_9 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_9);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 9:
                    
                    int index2_10 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_10);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 10:
                    
                    int index2_11 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred2_CPP())) {
                        s = 13;
                    }
                    
                    else if ((true)) {
                        s = 14;
                    }
                    
                    input.seek(index2_11);
                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            if (CPPParser.this.state.backtracking > 0) {
                CPPParser.this.state.failed = true;
                return -1;
            }
            NoViableAltException nvae = new NoViableAltException(this.getDescription(), 2, _s, input);
            this.error(nvae);
            throw nvae;
        }
    }
    
    static final String DFA12_eotS = "\16\uffff";
    static final String DFA12_eofS = "\16\uffff";
    static final String DFA12_minS = "\1\37\13\4\2\uffff";
    static final String DFA12_maxS = "\1\51\13\54\2\uffff";
    static final String DFA12_acceptS = "\14\uffff\1\1\1\2";
    static final String DFA12_specialS = "\16\uffff}>";
    static final String[] DFA12_transitionS = { "\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13",
            "\1\14\31\uffff\1\15\15\uffff\1\14", "\1\14\31\uffff\1\15\15\uffff\1\14",
            "\1\14\31\uffff\1\15\15\uffff\1\14", "\1\14\31\uffff\1\15\15\uffff\1\14",
            "\1\14\31\uffff\1\15\15\uffff\1\14", "\1\14\31\uffff\1\15\15\uffff\1\14",
            "\1\14\31\uffff\1\15\15\uffff\1\14", "\1\14\31\uffff\1\15\15\uffff\1\14",
            "\1\14\31\uffff\1\15\15\uffff\1\14", "\1\14\31\uffff\1\15\15\uffff\1\14",
            "\1\14\31\uffff\1\15\15\uffff\1\14", "", "" };
    
    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;
    
    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }
    
    class DFA12
            extends DFA
    {
        
        public DFA12(BaseRecognizer recognizer)
        {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        
        @Override
        public String getDescription()
        {
            return "190:1: type : ( combinedType | pointerType );";
        }
    }
    
    static final String DFA18_eotS = "\53\uffff";
    static final String DFA18_eofS = "\1\47\52\uffff";
    static final String DFA18_minS = "\1\4\46\0\4\uffff";
    static final String DFA18_maxS = "\1\127\46\0\4\uffff";
    static final String DFA18_acceptS = "\47\uffff\1\2\2\uffff\1\1";
    static final String DFA18_specialS = "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"
            + "\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"
            + "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45" + "\4\uffff}>";
    static final String[] DFA18_transitionS = {
            "\1\20\1\12\1\13\1\14\1\15\1\16\1\17\21\uffff\1\1\1\47\1\6\1"
                    + "\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\21\1"
                    + "\uffff\1\7\1\22\1\47\1\23\1\24\1\25\1\31\1\32\1\uffff\1\33\1"
                    + "\26\1\27\1\30\27\uffff\1\2\1\3\2\uffff\1\4\1\5\1\10\1\11", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "", "", "",
            "" };
    
    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;
    
    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }
    
    class DFA18
            extends DFA
    {
        
        public DFA18(BaseRecognizer recognizer)
        {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        
        @Override
        public String getDescription()
        {
            return "296:1: stmtList : ( stmt stmtList | );";
        }
        
        @Override
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException
        {
            TokenStream input = (TokenStream) _input;
            int _s = s;
            switch (s)
            {
                case 0:
                    
                    int index18_1 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_1);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 1:
                    
                    int index18_2 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_2);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 2:
                    
                    int index18_3 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_3);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 3:
                    
                    int index18_4 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_4);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 4:
                    
                    int index18_5 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_5);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 5:
                    
                    int index18_6 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_6);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 6:
                    
                    int index18_7 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_7);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 7:
                    
                    int index18_8 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_8);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 8:
                    
                    int index18_9 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_9);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 9:
                    
                    int index18_10 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_10);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 10:
                    
                    int index18_11 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_11);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 11:
                    
                    int index18_12 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_12);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 12:
                    
                    int index18_13 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_13);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 13:
                    
                    int index18_14 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_14);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 14:
                    
                    int index18_15 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_15);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 15:
                    
                    int index18_16 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_16);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 16:
                    
                    int index18_17 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_17);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 17:
                    
                    int index18_18 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_18);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 18:
                    
                    int index18_19 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_19);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 19:
                    
                    int index18_20 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_20);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 20:
                    
                    int index18_21 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_21);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 21:
                    
                    int index18_22 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_22);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 22:
                    
                    int index18_23 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_23);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 23:
                    
                    int index18_24 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_24);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 24:
                    
                    int index18_25 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_25);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 25:
                    
                    int index18_26 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_26);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 26:
                    
                    int index18_27 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_27);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 27:
                    
                    int index18_28 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_28);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 28:
                    
                    int index18_29 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_29);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 29:
                    
                    int index18_30 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_30);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 30:
                    
                    int index18_31 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_31);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 31:
                    
                    int index18_32 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_32);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 32:
                    
                    int index18_33 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_33);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 33:
                    
                    int index18_34 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_34);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 34:
                    
                    int index18_35 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_35);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 35:
                    
                    int index18_36 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_36);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 36:
                    
                    int index18_37 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_37);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 37:
                    
                    int index18_38 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred29_CPP())) {
                        s = 42;
                    }
                    
                    else if ((true)) {
                        s = 39;
                    }
                    
                    input.seek(index18_38);
                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            if (CPPParser.this.state.backtracking > 0) {
                CPPParser.this.state.failed = true;
                return -1;
            }
            NoViableAltException nvae = new NoViableAltException(this.getDescription(), 18, _s, input);
            this.error(nvae);
            throw nvae;
        }
    }
    
    static final String DFA21_eotS = "\36\uffff";
    static final String DFA21_eofS = "\36\uffff";
    static final String DFA21_minS = "\1\4\13\uffff\21\0\1\uffff";
    static final String DFA21_maxS = "\1\127\13\uffff\21\0\1\uffff";
    static final String DFA21_acceptS = "\1\uffff\1\1\33\uffff\1\2";
    static final String DFA21_specialS = "\14\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"
            + "\14\1\15\1\16\1\17\1\20\1\uffff}>";
    static final String[] DFA21_transitionS = {
            "\1\32\1\24\1\25\1\26\1\27\1\30\1\31\14\uffff\1\34\6\uffff\1"
                    + "\20\13\1\1\33\1\uffff\1\21\43\uffff\1\14\1\15\2\uffff\1\16\1" + "\17\1\22\1\23", "", "", "", "",
            "", "", "", "", "", "", "", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "" };
    
    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;
    
    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }
    
    class DFA21
            extends DFA
    {
        
        public DFA21(BaseRecognizer recognizer)
        {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        
        @Override
        public String getDescription()
        {
            return "367:5: ( forInit )?";
        }
        
        @Override
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException
        {
            TokenStream input = (TokenStream) _input;
            int _s = s;
            switch (s)
            {
                case 0:
                    
                    int index21_12 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_12);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 1:
                    
                    int index21_13 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_13);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 2:
                    
                    int index21_14 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_14);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 3:
                    
                    int index21_15 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_15);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 4:
                    
                    int index21_16 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_16);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 5:
                    
                    int index21_17 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_17);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 6:
                    
                    int index21_18 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_18);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 7:
                    
                    int index21_19 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_19);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 8:
                    
                    int index21_20 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_20);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 9:
                    
                    int index21_21 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_21);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 10:
                    
                    int index21_22 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_22);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 11:
                    
                    int index21_23 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_23);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 12:
                    
                    int index21_24 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_24);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 13:
                    
                    int index21_25 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_25);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 14:
                    
                    int index21_26 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_26);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 15:
                    
                    int index21_27 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_27);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 16:
                    
                    int index21_28 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred43_CPP())) {
                        s = 1;
                    }
                    
                    else if ((true)) {
                        s = 29;
                    }
                    
                    input.seek(index21_28);
                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            if (CPPParser.this.state.backtracking > 0) {
                CPPParser.this.state.failed = true;
                return -1;
            }
            NoViableAltException nvae = new NoViableAltException(this.getDescription(), 21, _s, input);
            this.error(nvae);
            throw nvae;
        }
    }
    
    static final String DFA29_eotS = "\23\uffff";
    static final String DFA29_eofS = "\23\uffff";
    static final String DFA29_minS = "\1\4\20\0\2\uffff";
    static final String DFA29_maxS = "\1\127\20\0\2\uffff";
    static final String DFA29_acceptS = "\21\uffff\1\1\1\2";
    static final String DFA29_specialS = "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1"
            + "\14\1\15\1\16\1\17\2\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\17\1\11\1\12\1\13\1\14\1\15\1\16\23\uffff\1\5\13\uffff\1"
                    + "\20\1\uffff\1\6\43\uffff\1\1\1\2\2\uffff\1\3\1\4\1\7\1\10", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff",
            "\1\uffff", "\1\uffff", "\1\uffff", "\1\uffff", "", "" };
    
    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;
    
    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }
    
    class DFA29
            extends DFA
    {
        
        public DFA29(BaseRecognizer recognizer)
        {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        
        @Override
        public String getDescription()
        {
            return "507:1: exprASSIGN : ( exprLOGICAL_OR (op= '=' | op= '+=' | op= '-=' | op= '*=' | op= '/=' | op= '%=' | op= '<<=' | op= '>>=' | op= '&=' | op= '^=' | op= '|=' ) exprASSIGN | exprTERNARY );";
        }
        
        @Override
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException
        {
            TokenStream input = (TokenStream) _input;
            int _s = s;
            switch (s)
            {
                case 0:
                    
                    int index29_1 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_1);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 1:
                    
                    int index29_2 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_2);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 2:
                    
                    int index29_3 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_3);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 3:
                    
                    int index29_4 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_4);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 4:
                    
                    int index29_5 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_5);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 5:
                    
                    int index29_6 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_6);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 6:
                    
                    int index29_7 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_7);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 7:
                    
                    int index29_8 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_8);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 8:
                    
                    int index29_9 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_9);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 9:
                    
                    int index29_10 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_10);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 10:
                    
                    int index29_11 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_11);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 11:
                    
                    int index29_12 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_12);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 12:
                    
                    int index29_13 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_13);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 13:
                    
                    int index29_14 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_14);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 14:
                    
                    int index29_15 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_15);
                    if (s >= 0) {
                        return s;
                    }
                    break;
                case 15:
                    
                    int index29_16 = input.index();
                    input.rewind();
                    s = -1;
                    if ((CPPParser.this.synpred61_CPP())) {
                        s = 17;
                    }
                    
                    else if ((true)) {
                        s = 18;
                    }
                    
                    input.seek(index29_16);
                    if (s >= 0) {
                        return s;
                    }
                    break;
            }
            if (CPPParser.this.state.backtracking > 0) {
                CPPParser.this.state.failed = true;
                return -1;
            }
            NoViableAltException nvae = new NoViableAltException(this.getDescription(), 29, _s, input);
            this.error(nvae);
            throw nvae;
        }
    }
    
    public static final BitSet FOLLOW_declarationList_in_program59 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_declaration_in_declarationList81 = new BitSet(new long[] { 0x000003FF80400000L });
    public static final BitSet FOLLOW_declarationList_in_declarationList95 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_varDecl_in_declaration121 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_directive_in_declaration127 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_funcDecl_in_declaration133 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_22_in_directive150 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_IDENTIFIER_in_directive154 = new BitSet(new long[] { 0x00000000000007F0L });
    public static final BitSet FOLLOW_pp_token_in_directive156 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_IDENTIFIER_in_pp_token175 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_literal_in_pp_token183 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_type_in_varDecl199 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_varList_in_varDecl207 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_varDecl209 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_var_in_varList228 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_varListRest_in_varList236 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_24_in_varListRest263 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_var_in_varListRest265 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_varListRest_in_varListRest273 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_IDENTIFIER_in_var314 = new BitSet(new long[] { 0x000000000A000000L });
    public static final BitSet FOLLOW_arrayDecl_in_var321 = new BitSet(new long[] { 0x0000000008000002L });
    public static final BitSet FOLLOW_initializer_in_var335 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_25_in_arrayDecl365 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_arrayDecl367 = new BitSet(new long[] { 0x0000000004000000L });
    public static final BitSet FOLLOW_26_in_arrayDecl369 = new BitSet(new long[] { 0x0000000002000000L });
    public static final BitSet FOLLOW_25_in_arrayDecl379 = new BitSet(new long[] { 0x0000000004000000L });
    public static final BitSet FOLLOW_26_in_arrayDecl381 = new BitSet(new long[] { 0x0000000002000000L });
    public static final BitSet FOLLOW_arrayDecl_in_arrayDecl391 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_27_in_initializer425 = new BitSet(new long[] { 0x00001400500007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_varInit_in_initializer431 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_arrayInit_in_initializer437 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_arrayInitList_in_initializer443 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_28_in_arrayInitList466 = new BitSet(new long[] { 0x0000000010000000L });
    public static final BitSet FOLLOW_arrayInit_in_arrayInitList473 = new BitSet(new long[] { 0x0000000021000000L });
    public static final BitSet FOLLOW_arrayInitListEx_in_arrayInitList481 = new BitSet(
            new long[] { 0x0000000020000000L });
    public static final BitSet FOLLOW_29_in_arrayInitList489 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_24_in_arrayInitListEx514 = new BitSet(new long[] { 0x0000000010000000L });
    public static final BitSet FOLLOW_arrayInit_in_arrayInitListEx520 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_arrayInitListEx_in_arrayInitListEx528 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_28_in_arrayInit561 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_varInit_in_arrayInit568 = new BitSet(new long[] { 0x0000000021000000L });
    public static final BitSet FOLLOW_arrayInitEx_in_arrayInit576 = new BitSet(new long[] { 0x0000000020000000L });
    public static final BitSet FOLLOW_29_in_arrayInit584 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_28_in_arrayInit596 = new BitSet(new long[] { 0x0000000020000000L });
    public static final BitSet FOLLOW_29_in_arrayInit598 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_24_in_arrayInitEx619 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_varInit_in_arrayInitEx626 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_arrayInitEx_in_arrayInitEx634 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_expr_in_varInit667 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_combinedType_in_type690 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_pointerType_in_type696 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_combinedType_in_pointerType715 = new BitSet(new long[] { 0x0000000040000000L });
    public static final BitSet FOLLOW_pointerTypeRest_in_pointerType723 =
                                                                          new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_30_in_pointerTypeRest748 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_30_in_pointerTypeRest762 = new BitSet(new long[] { 0x0000000040000000L });
    public static final BitSet FOLLOW_pointerTypeRest_in_pointerTypeRest770 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_primitiveType_in_combinedType789 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_31_in_primitiveType813 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_32_in_primitiveType821 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_33_in_primitiveType829 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_34_in_primitiveType837 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_35_in_primitiveType845 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_36_in_primitiveType853 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_37_in_primitiveType861 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_38_in_primitiveType869 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_39_in_primitiveType877 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_40_in_primitiveType885 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_41_in_primitiveType893 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_type_in_funcDecl912 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_IDENTIFIER_in_funcDecl922 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_funcDecl929 = new BitSet(new long[] { 0x00000BFF80000000L });
    public static final BitSet FOLLOW_paraList_in_funcDecl936 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_funcDecl944 = new BitSet(new long[] { 0x0000000010000000L });
    public static final BitSet FOLLOW_blockStmt_in_funcDecl951 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_para_in_paraList978 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_paraListRest_in_paraList986 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_type_in_para1019 = new BitSet(new long[] { 0x0000100000000010L });
    public static final BitSet FOLLOW_44_in_para1030 = new BitSet(new long[] { 0x0000000000000010L });
    public static final BitSet FOLLOW_IDENTIFIER_in_para1040 = new BitSet(new long[] { 0x0000000002000000L });
    public static final BitSet FOLLOW_arrayDecl_in_para1043 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_24_in_paraListRest1070 = new BitSet(new long[] { 0x000003FF80000000L });
    public static final BitSet FOLLOW_para_in_paraListRest1072 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_paraListRest_in_paraListRest1080 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_28_in_blockStmt1113 = new BitSet(new long[] { 0x01EFB7FFF00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmtList_in_blockStmt1119 = new BitSet(new long[] { 0x0000000020000000L });
    public static final BitSet FOLLOW_29_in_blockStmt1127 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_stmt_in_stmtList1152 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmtList_in_stmtList1160 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_blockStmt_in_stmt1189 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprStmt_in_stmt1197 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_ifStmt_in_stmt1203 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_forStmt_in_stmt1210 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_whileStmt_in_stmt1217 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_doStmt_in_stmt1224 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_breakStmt_in_stmt1231 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_continueStmt_in_stmt1238 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_returnStmt_in_stmt1245 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_switchStmt_in_stmt1251 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_caseStmt_in_stmt1257 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_defaultStmt_in_stmt1263 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_declarationStmt_in_stmt1269 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_varDecl_in_declarationStmt1290 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_expr_in_exprStmt1317 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_exprStmt1325 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_45_in_ifStmt1350 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_ifStmt1357 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_ifStmt1364 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_ifStmt1372 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmt_in_ifStmt1379 = new BitSet(new long[] { 0x0000400000000002L });
    public static final BitSet FOLLOW_46_in_ifStmt1400 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmt_in_ifStmt1407 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_47_in_forStmt1441 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_forStmt1443 = new BitSet(new long[] { 0x000017FFC08007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_forInit_in_forStmt1450 = new BitSet(new long[] { 0x00001400408007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_forStmt1459 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_forStmt1468 = new BitSet(new long[] { 0x00001C00400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprList_in_forStmt1475 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_forStmt1484 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmt_in_forStmt1491 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_varDecl_in_forInit1518 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprList_in_forInit1526 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_forInit1528 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_23_in_forInit1536 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_48_in_whileStmt1553 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_whileStmt1560 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_whileStmt1567 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_whileStmt1575 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmt_in_whileStmt1582 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_49_in_doStmt1609 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmt_in_doStmt1616 = new BitSet(new long[] { 0x0001000000000000L });
    public static final BitSet FOLLOW_48_in_doStmt1624 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_doStmt1631 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_doStmt1638 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_doStmt1646 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_doStmt1653 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_50_in_switchStmt1678 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_switchStmt1684 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_switchStmt1690 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_switchStmt1698 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmt_in_switchStmt1704 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_51_in_caseStmt1731 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_caseStmt1737 = new BitSet(new long[] { 0x0010000000000000L });
    public static final BitSet FOLLOW_52_in_caseStmt1745 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmtList_in_caseStmt1751 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_53_in_defaultStmt1778 = new BitSet(new long[] { 0x0010000000000000L });
    public static final BitSet FOLLOW_52_in_defaultStmt1784 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmtList_in_defaultStmt1790 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_54_in_breakStmt1820 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_breakStmt1822 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_55_in_continueStmt1845 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_continueStmt1847 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_56_in_returnStmt1872 = new BitSet(new long[] { 0x00001400408007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_returnStmt1880 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_returnStmt1891 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_IDENTIFIER_in_callStmt1918 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_callStmt1925 = new BitSet(new long[] { 0x00001C00400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprList_in_callStmt1932 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_callStmt1940 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_callStmt1947 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_expr_in_exprList1972 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_exprListRest_in_exprList1981 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_24_in_exprListRest2014 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_exprListRest2021 = new BitSet(new long[] { 0x0000000001000000L });
    public static final BitSet FOLLOW_exprListRest_in_exprListRest2030 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprASSIGN_in_expr2059 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprLOGICAL_OR_in_exprASSIGN2078 = new BitSet(new long[] { 0xFE00000008000000L,
            0x0000000000000007L });
    public static final BitSet FOLLOW_27_in_exprASSIGN2090 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_57_in_exprASSIGN2104 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_58_in_exprASSIGN2117 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_59_in_exprASSIGN2130 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_60_in_exprASSIGN2143 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_61_in_exprASSIGN2156 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_62_in_exprASSIGN2169 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_63_in_exprASSIGN2181 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_64_in_exprASSIGN2193 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_65_in_exprASSIGN2206 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_66_in_exprASSIGN2219 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprASSIGN_in_exprASSIGN2234 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprTERNARY_in_exprASSIGN2248 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprLOGICAL_OR_in_exprTERNARY2263 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000000008L });
    public static final BitSet FOLLOW_exprTERNARYEx_in_exprTERNARY2271 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_67_in_exprTERNARYEx2296 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprASSIGN_in_exprTERNARYEx2303 = new BitSet(new long[] { 0x0010000000000000L });
    public static final BitSet FOLLOW_52_in_exprTERNARYEx2311 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprASSIGN_in_exprTERNARYEx2318 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OR2343 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000010L });
    public static final BitSet FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OR2351 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_68_in_exprLOGICAL_OREx2378 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprLOGICAL_AND_in_exprLOGICAL_OREx2385 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000010L });
    public static final BitSet FOLLOW_exprLOGICAL_OREx_in_exprLOGICAL_OREx2399 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprBITWISE_OR_in_exprLOGICAL_AND2420 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000020L });
    public static final BitSet FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_AND2428 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_69_in_exprLOGICAL_ANDEx2455 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprBITWISE_OR_in_exprLOGICAL_ANDEx2462 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000020L });
    public static final BitSet FOLLOW_exprLOGICAL_ANDEx_in_exprLOGICAL_ANDEx2476 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OR2497 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000040L });
    public static final BitSet FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OR2505 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_70_in_exprBITWISE_OREx2532 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprBITWISE_XOR_in_exprBITWISE_OREx2539 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000040L });
    public static final BitSet FOLLOW_exprBITWISE_OREx_in_exprBITWISE_OREx2553 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOR2574 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000080L });
    public static final BitSet FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOR2582 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_71_in_exprBITWISE_XOREx2609 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprBITWISE_AND_in_exprBITWISE_XOREx2616 = new BitSet(new long[] {
            0x0000000000000000L, 0x0000000000000080L });
    public static final BitSet FOLLOW_exprBITWISE_XOREx_in_exprBITWISE_XOREx2630 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprEQUALITY_in_exprBITWISE_AND2651 = new BitSet(
            new long[] { 0x0000100000000000L });
    public static final BitSet FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_AND2659 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_44_in_exprBITWISE_ANDEx2686 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprEQUALITY_in_exprBITWISE_ANDEx2693 = new BitSet(
            new long[] { 0x0000100000000000L });
    public static final BitSet FOLLOW_exprBITWISE_ANDEx_in_exprBITWISE_ANDEx2707 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprRELATION_in_exprEQUALITY2728 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000000300L });
    public static final BitSet FOLLOW_exprEQUALITYEx_in_exprEQUALITY2736 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_72_in_exprEQUALITYEx2765 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_73_in_exprEQUALITYEx2778 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprRELATION_in_exprEQUALITYEx2790 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000000300L });
    public static final BitSet FOLLOW_exprEQUALITYEx_in_exprEQUALITYEx2804 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprSHIFT_in_exprRELATION2825 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000003C00L });
    public static final BitSet FOLLOW_exprRELATIONEx_in_exprRELATION2833 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_74_in_exprRELATIONEx2862 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_75_in_exprRELATIONEx2876 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_76_in_exprRELATIONEx2889 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_77_in_exprRELATIONEx2903 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprSHIFT_in_exprRELATIONEx2919 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000003C00L });
    public static final BitSet FOLLOW_exprRELATIONEx_in_exprRELATIONEx2933 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprADD_in_exprSHIFT2954 = new BitSet(new long[] { 0x0000000000000000L,
            0x000000000000C000L });
    public static final BitSet FOLLOW_exprSHIFTEx_in_exprSHIFT2962 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_78_in_exprSHIFTEx2991 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_79_in_exprSHIFTEx3003 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprADD_in_exprSHIFTEx3018 = new BitSet(new long[] { 0x0000000000000000L,
            0x000000000000C000L });
    public static final BitSet FOLLOW_exprSHIFTEx_in_exprSHIFTEx3032 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprMUL_in_exprADD3053 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000030000L });
    public static final BitSet FOLLOW_exprADDEx_in_exprADD3061 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_80_in_exprADDEx3090 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_81_in_exprADDEx3104 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprMUL_in_exprADDEx3121 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000030000L });
    public static final BitSet FOLLOW_exprADDEx_in_exprADDEx3135 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprUNARY_in_exprMUL3154 = new BitSet(new long[] { 0x0000000040000000L,
            0x00000000000C0000L });
    public static final BitSet FOLLOW_exprMULEx_in_exprMUL3162 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_30_in_exprMULEx3189 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_82_in_exprMULEx3203 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_83_in_exprMULEx3217 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprUNARY_in_exprMULEx3234 = new BitSet(new long[] { 0x0000000040000000L,
            0x00000000000C0000L });
    public static final BitSet FOLLOW_exprMULEx_in_exprMULEx3248 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_80_in_exprUNARY3277 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_81_in_exprUNARY3291 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_84_in_exprUNARY3305 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_85_in_exprUNARY3318 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_30_in_exprUNARY3331 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_44_in_exprUNARY3345 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_86_in_exprUNARY3359 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_87_in_exprUNARY3373 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprUNARY_in_exprUNARY3389 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprPostfix_in_exprUNARY3404 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprPrimary_in_exprPostfix3419 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000300000L });
    public static final BitSet FOLLOW_exprPostfixEx_in_exprPostfix3427 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_84_in_exprPostfixEx3456 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000300000L });
    public static final BitSet FOLLOW_85_in_exprPostfixEx3469 = new BitSet(new long[] { 0x0000000000000000L,
            0x0000000000300000L });
    public static final BitSet FOLLOW_exprPostfixEx_in_exprPostfixEx3491 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_literal_in_exprPrimary3512 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_IDENTIFIER_in_exprPrimary3521 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_42_in_exprPrimary3529 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_exprPrimary3531 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_exprPrimary3533 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_callExpr_in_exprPrimary3540 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_arrayElement_in_exprPrimary3547 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_IDENTIFIER_in_callExpr3568 = new BitSet(new long[] { 0x0000040000000000L });
    public static final BitSet FOLLOW_42_in_callExpr3575 = new BitSet(new long[] { 0x00001C00400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprList_in_callExpr3582 = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet FOLLOW_43_in_callExpr3591 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_IDENTIFIER_in_arrayElement3618 = new BitSet(new long[] { 0x0000000002000000L });
    public static final BitSet FOLLOW_25_in_arrayElement3625 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_arrayElement3632 = new BitSet(new long[] { 0x0000000004000000L });
    public static final BitSet FOLLOW_26_in_arrayElement3640 = new BitSet(new long[] { 0x0000000002000000L });
    public static final BitSet FOLLOW_index_in_arrayElement3647 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_25_in_index3674 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_expr_in_index3681 = new BitSet(new long[] { 0x0000000004000000L });
    public static final BitSet FOLLOW_26_in_index3689 = new BitSet(new long[] { 0x0000000002000000L });
    public static final BitSet FOLLOW_index_in_index3696 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_CHARACTER_LITERAL_in_literal3727 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_STRING_LITERAL_in_literal3737 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_HEX_LITERAL_in_literal3747 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_DECIMAL_LITERAL_in_literal3757 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_OCTAL_LITERAL_in_literal3767 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_FLOATING_POINT_LITERAL_in_literal3777 = new BitSet(
            new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_varDecl_in_synpred2_CPP121 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_stmt_in_synpred29_CPP1152 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmtList_in_synpred29_CPP1160 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_46_in_synpred42_CPP1400 = new BitSet(new long[] { 0x01EFB7FFD00007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_stmt_in_synpred42_CPP1407 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_forInit_in_synpred43_CPP1450 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprList_in_synpred45_CPP1475 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprList_in_synpred47_CPP1526 = new BitSet(new long[] { 0x0000000000800000L });
    public static final BitSet FOLLOW_23_in_synpred47_CPP1528 = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet FOLLOW_exprLOGICAL_OR_in_synpred61_CPP2078 = new BitSet(new long[] {
            0xFE00000008000000L, 0x0000000000000007L });
    public static final BitSet FOLLOW_27_in_synpred61_CPP2090 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_57_in_synpred61_CPP2104 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_58_in_synpred61_CPP2117 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_59_in_synpred61_CPP2130 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_60_in_synpred61_CPP2143 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_61_in_synpred61_CPP2156 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_62_in_synpred61_CPP2169 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_63_in_synpred61_CPP2181 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_64_in_synpred61_CPP2193 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_65_in_synpred61_CPP2206 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_66_in_synpred61_CPP2219 = new BitSet(new long[] { 0x00001400400007F0L,
            0x0000000000F30000L });
    public static final BitSet FOLLOW_exprASSIGN_in_synpred61_CPP2234 = new BitSet(new long[] { 0x0000000000000002L });
    
}
