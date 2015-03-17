// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 Checker.g 2010-05-19 21:36:55
package Checker;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class CheckerLexer extends Lexer {
    public static final int SHELL=9;
    public static final int HEAP=10;
    public static final int SHAKER=6;
    public static final int INSERTION=8;
    public static final int MERGE=11;
    public static final int GNOME=5;
    public static final int BUBBLE=4;
    public static final int EOF=-1;
    public static final int QUICK=12;
    public static final int SELECTION=7;
    public static final int OTHERS=13;

    	public void displayRecognitionError(String[] tokenNames, RecognitionException err) {
    	
    	}


    // delegates
    // delegators

    public CheckerLexer() {;} 
    public CheckerLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CheckerLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Checker.g"; }

    // $ANTLR start "BUBBLE"
    public final void mBUBBLE() throws RecognitionException {
        try {
            int _type = BUBBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:28:8: ( '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(2,1)(3,2)(1,1)(2,3)(2,2)(3,3)' | '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(3,2)(2,1)(2,3)(1,1)(3,3)(2,2)' | '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(0,1)(1,2)(1,0)(2,2)(0,0)(1,1)' | '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(1,2)(0,1)(2,2)(1,0)(1,1)(0,0)' )
            int alt1=4;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='(') ) {
                switch ( input.LA(2) ) {
                case '2':
                    {
                    alt1=1;
                    }
                    break;
                case '3':
                    {
                    alt1=2;
                    }
                    break;
                case '0':
                    {
                    alt1=3;
                    }
                    break;
                case '1':
                    {
                    alt1=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Checker.g:28:10: '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(2,1)(3,2)(1,1)(2,3)(2,2)(3,3)'
                    {
                    match("(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(2,1)(3,2)(1,1)(2,3)(2,2)(3,3)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:29:5: '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(3,2)(2,1)(2,3)(1,1)(3,3)(2,2)'
                    {
                    match("(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(3,2)(2,1)(2,3)(1,1)(3,3)(2,2)"); 


                    }
                    break;
                case 3 :
                    // Checker.g:30:5: '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(0,1)(1,2)(1,0)(2,2)(0,0)(1,1)'
                    {
                    match("(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(0,1)(1,2)(1,0)(2,2)(0,0)(1,1)"); 


                    }
                    break;
                case 4 :
                    // Checker.g:31:5: '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(1,2)(0,1)(2,2)(1,0)(1,1)(0,0)'
                    {
                    match("(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(1,2)(0,1)(2,2)(1,0)(1,1)(0,0)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BUBBLE"

    // $ANTLR start "GNOME"
    public final void mGNOME() throws RecognitionException {
        try {
            int _type = GNOME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:34:7: ( '(1,3)(0,2)(2,3)(1,1)(1,2)(0,1)(3,3)(2,0)(2,2)(1,0)(1,1)(0,0)' | '(0,2)(1,3)(1,1)(2,3)(0,1)(1,2)(2,0)(3,3)(1,0)(2,2)(0,0)(1,1)' | '(2,0)(3,1)(1,0)(2,2)(2,1)(3,2)(0,0)(1,3)(1,1)(2,3)(2,2)(3,3)' | '(3,1)(2,0)(2,2)(1,0)(3,2)(2,1)(1,3)(0,0)(2,3)(1,1)(3,3)(2,2)' )
            int alt2=4;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='(') ) {
                switch ( input.LA(2) ) {
                case '1':
                    {
                    alt2=1;
                    }
                    break;
                case '0':
                    {
                    alt2=2;
                    }
                    break;
                case '2':
                    {
                    alt2=3;
                    }
                    break;
                case '3':
                    {
                    alt2=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Checker.g:34:9: '(1,3)(0,2)(2,3)(1,1)(1,2)(0,1)(3,3)(2,0)(2,2)(1,0)(1,1)(0,0)'
                    {
                    match("(1,3)(0,2)(2,3)(1,1)(1,2)(0,1)(3,3)(2,0)(2,2)(1,0)(1,1)(0,0)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:35:4: '(0,2)(1,3)(1,1)(2,3)(0,1)(1,2)(2,0)(3,3)(1,0)(2,2)(0,0)(1,1)'
                    {
                    match("(0,2)(1,3)(1,1)(2,3)(0,1)(1,2)(2,0)(3,3)(1,0)(2,2)(0,0)(1,1)"); 


                    }
                    break;
                case 3 :
                    // Checker.g:36:4: '(2,0)(3,1)(1,0)(2,2)(2,1)(3,2)(0,0)(1,3)(1,1)(2,3)(2,2)(3,3)'
                    {
                    match("(2,0)(3,1)(1,0)(2,2)(2,1)(3,2)(0,0)(1,3)(1,1)(2,3)(2,2)(3,3)"); 


                    }
                    break;
                case 4 :
                    // Checker.g:37:4: '(3,1)(2,0)(2,2)(1,0)(3,2)(2,1)(1,3)(0,0)(2,3)(1,1)(3,3)(2,2)'
                    {
                    match("(3,1)(2,0)(2,2)(1,0)(3,2)(2,1)(1,3)(0,0)(2,3)(1,1)(3,3)(2,2)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GNOME"

    // $ANTLR start "SHAKER"
    public final void mSHAKER() throws RecognitionException {
        try {
            int _type = SHAKER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:40:8: ( '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(2,1)(1,0)(1,2)(0,0)(1,1)(2,2)' | '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(2,1)(1,0)(1,2)(0,0)(2,2)(1,1)' | '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(1,0)(2,1)(0,0)(1,2)(1,1)(2,2)' | '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(1,0)(2,1)(0,0)(1,2)(2,2)(1,1)' | '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(2,3)(1,2)(3,3)(2,1)(1,1)(2,2)' | '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(2,3)(1,2)(3,3)(2,1)(2,2)(1,1)' | '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(1,2)(2,3)(2,1)(3,3)(1,1)(2,2)' | '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(1,2)(2,3)(2,1)(3,3)(2,2)(1,1)' )
            int alt3=8;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // Checker.g:40:10: '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(2,1)(1,0)(1,2)(0,0)(1,1)(2,2)'
                    {
                    match("(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(2,1)(1,0)(1,2)(0,0)(1,1)(2,2)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:41:4: '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(2,1)(1,0)(1,2)(0,0)(2,2)(1,1)'
                    {
                    match("(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(2,1)(1,0)(1,2)(0,0)(2,2)(1,1)"); 


                    }
                    break;
                case 3 :
                    // Checker.g:42:4: '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(1,0)(2,1)(0,0)(1,2)(1,1)(2,2)'
                    {
                    match("(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(1,0)(2,1)(0,0)(1,2)(1,1)(2,2)"); 


                    }
                    break;
                case 4 :
                    // Checker.g:43:4: '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(1,0)(2,1)(0,0)(1,2)(2,2)(1,1)'
                    {
                    match("(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(1,0)(2,1)(0,0)(1,2)(2,2)(1,1)"); 


                    }
                    break;
                case 5 :
                    // Checker.g:44:4: '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(2,3)(1,2)(3,3)(2,1)(1,1)(2,2)'
                    {
                    match("(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(2,3)(1,2)(3,3)(2,1)(1,1)(2,2)"); 


                    }
                    break;
                case 6 :
                    // Checker.g:45:4: '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(2,3)(1,2)(3,3)(2,1)(2,2)(1,1)'
                    {
                    match("(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(2,3)(1,2)(3,3)(2,1)(2,2)(1,1)"); 


                    }
                    break;
                case 7 :
                    // Checker.g:46:4: '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(1,2)(2,3)(2,1)(3,3)(1,1)(2,2)'
                    {
                    match("(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(1,2)(2,3)(2,1)(3,3)(1,1)(2,2)"); 


                    }
                    break;
                case 8 :
                    // Checker.g:47:4: '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(1,2)(2,3)(2,1)(3,3)(2,2)(1,1)'
                    {
                    match("(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(1,2)(2,3)(2,1)(3,3)(2,2)(1,1)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHAKER"

    // $ANTLR start "SELECTION"
    public final void mSELECTION() throws RecognitionException {
        try {
            int _type = SELECTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:50:11: ( '(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)(3,3)(3,3)' | '(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)(3,3)(3,3)' | '(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)(0,0)(0,0)' | '(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)(0,0)(0,0)' | '(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)' | '(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)' | '(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)' | '(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)' | '(0,0)(3,3)(1,1)(2,2)' | '(3,3)(0,0)(2,2)(1,1)' )
            int alt4=10;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // Checker.g:50:13: '(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)(3,3)(3,3)'
                    {
                    match("(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)(3,3)(3,3)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:51:5: '(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)(3,3)(3,3)'
                    {
                    match("(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)(3,3)(3,3)"); 


                    }
                    break;
                case 3 :
                    // Checker.g:52:5: '(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)(0,0)(0,0)'
                    {
                    match("(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)(0,0)(0,0)"); 


                    }
                    break;
                case 4 :
                    // Checker.g:53:5: '(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)(0,0)(0,0)'
                    {
                    match("(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)(0,0)(0,0)"); 


                    }
                    break;
                case 5 :
                    // Checker.g:54:5: '(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)'
                    {
                    match("(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)"); 


                    }
                    break;
                case 6 :
                    // Checker.g:55:5: '(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)'
                    {
                    match("(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)"); 


                    }
                    break;
                case 7 :
                    // Checker.g:56:5: '(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)'
                    {
                    match("(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)"); 


                    }
                    break;
                case 8 :
                    // Checker.g:57:5: '(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)'
                    {
                    match("(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)"); 


                    }
                    break;
                case 9 :
                    // Checker.g:58:5: '(0,0)(3,3)(1,1)(2,2)'
                    {
                    match("(0,0)(3,3)(1,1)(2,2)"); 


                    }
                    break;
                case 10 :
                    // Checker.g:59:5: '(3,3)(0,0)(2,2)(1,1)'
                    {
                    match("(3,3)(0,0)(2,2)(1,1)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SELECTION"

    // $ANTLR start "INSERTION"
    public final void mINSERTION() throws RecognitionException {
        try {
            int _type = INSERTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:62:11: ( '(1,3)(0,2)(2,3)(1,2)(0,1)(3,3)(2,2)(1,1)(0,0)' | '(2,0)(3,1)(1,0)(2,1)(3,2)(0,0)(1,1)(2,2)(3,3)' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='(') ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1=='1') ) {
                    alt5=1;
                }
                else if ( (LA5_1=='2') ) {
                    alt5=2;
                }
                else {
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
                    // Checker.g:62:13: '(1,3)(0,2)(2,3)(1,2)(0,1)(3,3)(2,2)(1,1)(0,0)'
                    {
                    match("(1,3)(0,2)(2,3)(1,2)(0,1)(3,3)(2,2)(1,1)(0,0)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:63:5: '(2,0)(3,1)(1,0)(2,1)(3,2)(0,0)(1,1)(2,2)(3,3)'
                    {
                    match("(2,0)(3,1)(1,0)(2,1)(3,2)(0,0)(1,1)(2,2)(3,3)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INSERTION"

    // $ANTLR start "SHELL"
    public final void mSHELL() throws RecognitionException {
        try {
            int _type = SHELL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:66:7: ( '(2,3)(0,1)(3,2)(1,0)(1,1)(0,0)(3,3)(2,2)' | '(1,0)(3,2)(0,1)(2,3)(2,2)(3,3)(0,0)(1,1)' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='(') ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1=='2') ) {
                    alt6=1;
                }
                else if ( (LA6_1=='1') ) {
                    alt6=2;
                }
                else {
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
                    // Checker.g:66:9: '(2,3)(0,1)(3,2)(1,0)(1,1)(0,0)(3,3)(2,2)'
                    {
                    match("(2,3)(0,1)(3,2)(1,0)(1,1)(0,0)(3,3)(2,2)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:67:4: '(1,0)(3,2)(0,1)(2,3)(2,2)(3,3)(0,0)(1,1)'
                    {
                    match("(1,0)(3,2)(0,1)(2,3)(2,2)(3,3)(0,0)(1,1)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SHELL"

    // $ANTLR start "HEAP"
    public final void mHEAP() throws RecognitionException {
        try {
            int _type = HEAP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:70:6: ( '(0,0)(3,3)(0,2)(1,0)(0,1)(2,2)(0,0)(1,1)' | '(0,0)(3,3)(1,0)(0,2)(0,1)(2,2)(0,0)(1,1)' | '(3,3)(0,0)(0,2)(1,0)(2,2)(0,1)(1,1)(0,0)' | '(3,3)(0,0)(1,0)(0,2)(2,2)(0,1)(1,1)(0,0)' )
            int alt7=4;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // Checker.g:70:8: '(0,0)(3,3)(0,2)(1,0)(0,1)(2,2)(0,0)(1,1)'
                    {
                    match("(0,0)(3,3)(0,2)(1,0)(0,1)(2,2)(0,0)(1,1)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:71:4: '(0,0)(3,3)(1,0)(0,2)(0,1)(2,2)(0,0)(1,1)'
                    {
                    match("(0,0)(3,3)(1,0)(0,2)(0,1)(2,2)(0,0)(1,1)"); 


                    }
                    break;
                case 3 :
                    // Checker.g:72:4: '(3,3)(0,0)(0,2)(1,0)(2,2)(0,1)(1,1)(0,0)'
                    {
                    match("(3,3)(0,0)(0,2)(1,0)(2,2)(0,1)(1,1)(0,0)"); 


                    }
                    break;
                case 4 :
                    // Checker.g:73:4: '(3,3)(0,0)(1,0)(0,2)(2,2)(0,1)(1,1)(0,0)'
                    {
                    match("(3,3)(0,0)(1,0)(0,2)(2,2)(0,1)(1,1)(0,0)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HEAP"

    // $ANTLR start "MERGE"
    public final void mMERGE() throws RecognitionException {
        try {
            int _type = MERGE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:76:7: ( '(0,2)(1,3)(2,0)(3,1)(0,0)(1,1)(2,2)(3,3)' | '(2,0)(3,1)(0,2)(1,3)(0,0)(1,1)(2,2)(3,3)' | '(1,3)(0,2)(3,1)(2,0)(3,3)(2,2)(1,1)(0,0)' | '(3,1)(2,0)(1,3)(0,2)(3,3)(2,2)(1,1)(0,0)' )
            int alt8=4;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='(') ) {
                switch ( input.LA(2) ) {
                case '0':
                    {
                    alt8=1;
                    }
                    break;
                case '2':
                    {
                    alt8=2;
                    }
                    break;
                case '1':
                    {
                    alt8=3;
                    }
                    break;
                case '3':
                    {
                    alt8=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 8, 1, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // Checker.g:76:9: '(0,2)(1,3)(2,0)(3,1)(0,0)(1,1)(2,2)(3,3)'
                    {
                    match("(0,2)(1,3)(2,0)(3,1)(0,0)(1,1)(2,2)(3,3)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:77:4: '(2,0)(3,1)(0,2)(1,3)(0,0)(1,1)(2,2)(3,3)'
                    {
                    match("(2,0)(3,1)(0,2)(1,3)(0,0)(1,1)(2,2)(3,3)"); 


                    }
                    break;
                case 3 :
                    // Checker.g:78:4: '(1,3)(0,2)(3,1)(2,0)(3,3)(2,2)(1,1)(0,0)'
                    {
                    match("(1,3)(0,2)(3,1)(2,0)(3,3)(2,2)(1,1)(0,0)"); 


                    }
                    break;
                case 4 :
                    // Checker.g:79:4: '(3,1)(2,0)(1,3)(0,2)(3,3)(2,2)(1,1)(0,0)'
                    {
                    match("(3,1)(2,0)(1,3)(0,2)(3,3)(2,2)(1,1)(0,0)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MERGE"

    // $ANTLR start "QUICK"
    public final void mQUICK() throws RecognitionException {
        try {
            int _type = QUICK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:82:7: ( '(0,0)(3,3)(0,0)(0,0)(1,1)(2,2)' | '(3,3)(0,0)(0,0)(0,0)(2,2)(1,1)' | '(0,0)(3,3)(3,3)(3,3)(1,1)(2,2)' | '(3,3)(0,0)(3,3)(3,3)(2,2)(1,1)' )
            int alt9=4;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // Checker.g:82:9: '(0,0)(3,3)(0,0)(0,0)(1,1)(2,2)'
                    {
                    match("(0,0)(3,3)(0,0)(0,0)(1,1)(2,2)"); 


                    }
                    break;
                case 2 :
                    // Checker.g:83:4: '(3,3)(0,0)(0,0)(0,0)(2,2)(1,1)'
                    {
                    match("(3,3)(0,0)(0,0)(0,0)(2,2)(1,1)"); 


                    }
                    break;
                case 3 :
                    // Checker.g:84:4: '(0,0)(3,3)(3,3)(3,3)(1,1)(2,2)'
                    {
                    match("(0,0)(3,3)(3,3)(3,3)(1,1)(2,2)"); 


                    }
                    break;
                case 4 :
                    // Checker.g:85:4: '(3,3)(0,0)(3,3)(3,3)(2,2)(1,1)'
                    {
                    match("(3,3)(0,0)(3,3)(3,3)(2,2)(1,1)"); 


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUICK"

    // $ANTLR start "OTHERS"
    public final void mOTHERS() throws RecognitionException {
        try {
            int _type = OTHERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Checker.g:88:8: ( . )
            // Checker.g:88:10: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OTHERS"

    public void mTokens() throws RecognitionException {
        // Checker.g:1:8: ( BUBBLE | GNOME | SHAKER | SELECTION | INSERTION | SHELL | HEAP | MERGE | QUICK | OTHERS )
        int alt10=10;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // Checker.g:1:10: BUBBLE
                {
                mBUBBLE(); 

                }
                break;
            case 2 :
                // Checker.g:1:17: GNOME
                {
                mGNOME(); 

                }
                break;
            case 3 :
                // Checker.g:1:23: SHAKER
                {
                mSHAKER(); 

                }
                break;
            case 4 :
                // Checker.g:1:30: SELECTION
                {
                mSELECTION(); 

                }
                break;
            case 5 :
                // Checker.g:1:40: INSERTION
                {
                mINSERTION(); 

                }
                break;
            case 6 :
                // Checker.g:1:50: SHELL
                {
                mSHELL(); 

                }
                break;
            case 7 :
                // Checker.g:1:56: HEAP
                {
                mHEAP(); 

                }
                break;
            case 8 :
                // Checker.g:1:61: MERGE
                {
                mMERGE(); 

                }
                break;
            case 9 :
                // Checker.g:1:67: QUICK
                {
                mQUICK(); 

                }
                break;
            case 10 :
                // Checker.g:1:73: OTHERS
                {
                mOTHERS(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA4 dfa4 = new DFA4(this);
    protected DFA7 dfa7 = new DFA7(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA3_eotS =
        "\u0082\uffff";
    static final String DFA3_eofS =
        "\u0082\uffff";
    static final String DFA3_minS =
        "\1\50\1\60\4\54\1\62\1\63\1\60\1\61\4\51\4\50\1\61\1\60\1\63\1\62"+
        "\4\54\1\63\1\62\1\61\1\60\4\51\4\50\1\61\1\62\1\61\1\62\4\54\1\61"+
        "\1\63\1\60\1\62\4\51\4\50\1\62\1\61\1\62\1\61\4\54\1\63\1\61\1\62"+
        "\1\60\4\51\4\50\1\62\1\63\1\60\1\61\4\54\1\60\1\63\1\60\1\63\4\51"+
        "\4\50\1\63\1\62\1\61\1\60\4\54\1\63\1\60\1\63\1\60\4\51\4\50\4\61"+
        "\10\uffff";
    static final String DFA3_maxS =
        "\1\50\1\63\4\54\1\62\1\63\1\60\1\61\4\51\4\50\1\61\1\60\1\63\1\62"+
        "\4\54\1\63\1\62\1\61\1\60\4\51\4\50\1\61\1\62\1\61\1\62\4\54\1\61"+
        "\1\63\1\60\1\62\4\51\4\50\1\62\1\61\1\62\1\61\4\54\1\63\1\61\1\62"+
        "\1\60\4\51\4\50\1\62\1\63\1\60\1\61\4\54\1\60\1\63\1\60\1\63\4\51"+
        "\4\50\1\63\1\62\1\61\1\60\4\54\1\63\1\60\1\63\1\60\4\51\4\50\4\62"+
        "\10\uffff";
    static final String DFA3_acceptS =
        "\172\uffff\1\1\1\3\1\2\1\4\1\5\1\7\1\6\1\10";
    static final String DFA3_specialS =
        "\u0082\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1",
            "\1\2\1\3\1\4\1\5",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13",
            "\1\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\173\1\172",
            "\1\175\1\174",
            "\1\177\1\176",
            "\1\u0081\1\u0080",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "40:1: SHAKER : ( '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(2,1)(1,0)(1,2)(0,0)(1,1)(2,2)' | '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(2,1)(1,0)(1,2)(0,0)(2,2)(1,1)' | '(0,2)(1,3)(1,1)(2,3)(2,0)(3,3)(1,0)(2,1)(0,0)(1,2)(1,1)(2,2)' | '(1,3)(0,2)(2,3)(1,1)(3,3)(2,0)(1,0)(2,1)(0,0)(1,2)(2,2)(1,1)' | '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(2,3)(1,2)(3,3)(2,1)(1,1)(2,2)' | '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(2,3)(1,2)(3,3)(2,1)(2,2)(1,1)' | '(2,0)(3,1)(1,0)(2,2)(0,0)(1,3)(1,2)(2,3)(2,1)(3,3)(1,1)(2,2)' | '(3,1)(2,0)(2,2)(1,0)(1,3)(0,0)(1,2)(2,3)(2,1)(3,3)(2,2)(1,1)' );";
        }
    }
    static final String DFA4_eotS =
        "\46\uffff\1\51\1\53\44\uffff\1\121\1\123\1\125\1\127\10\uffff";
    static final String DFA4_eofS =
        "\130\uffff";
    static final String DFA4_minS =
        "\1\50\1\60\2\54\1\60\1\63\2\51\2\50\1\63\1\60\2\54\1\63\1\60\2\51"+
        "\2\50\1\61\1\62\2\54\1\61\1\62\2\51\2\50\1\62\1\61\2\54\1\62\1\61"+
        "\2\51\2\50\1\61\1\uffff\1\61\1\uffff\4\54\1\62\1\61\1\62\1\61\4"+
        "\51\4\50\1\62\1\61\1\62\1\61\4\54\1\62\1\61\1\62\1\61\4\51\4\50"+
        "\10\uffff";
    static final String DFA4_maxS =
        "\1\50\1\63\2\54\1\60\1\63\2\51\2\50\1\63\1\60\2\54\1\63\1\60\2\51"+
        "\2\50\1\61\1\62\2\54\1\61\1\62\2\51\2\50\1\62\1\61\2\54\1\62\1\61"+
        "\2\51\2\50\1\62\1\uffff\1\62\1\uffff\4\54\1\62\1\61\1\62\1\61\4"+
        "\51\4\50\1\62\1\61\1\62\1\61\4\54\1\62\1\61\1\62\1\61\4\51\4\50"+
        "\10\uffff";
    static final String DFA4_acceptS =
        "\51\uffff\1\11\1\uffff\1\12\44\uffff\1\1\1\5\1\3\1\7\1\2\1\6\1\4"+
        "\1\10";
    static final String DFA4_specialS =
        "\130\uffff}>";
    static final String[] DFA4_transitionS = {
            "\1\1",
            "\1\2\2\uffff\1\3",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13",
            "\1\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\52",
            "\1\55\1\54",
            "",
            "\1\57\1\56",
            "",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\122",
            "\1\124",
            "\1\126",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "50:1: SELECTION : ( '(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)(3,3)(3,3)' | '(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)(3,3)(3,3)' | '(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)(0,0)(0,0)' | '(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)(0,0)(0,0)' | '(0,0)(3,3)(1,1)(2,2)(2,2)(2,2)' | '(3,3)(0,0)(2,2)(1,1)(2,2)(2,2)' | '(0,0)(3,3)(1,1)(2,2)(1,1)(1,1)' | '(3,3)(0,0)(2,2)(1,1)(1,1)(1,1)' | '(0,0)(3,3)(1,1)(2,2)' | '(3,3)(0,0)(2,2)(1,1)' );";
        }
    }
    static final String DFA7_eotS =
        "\32\uffff";
    static final String DFA7_eofS =
        "\32\uffff";
    static final String DFA7_minS =
        "\1\50\1\60\2\54\1\60\1\63\2\51\2\50\1\63\1\60\2\54\1\63\1\60\2\51"+
        "\2\50\2\60\4\uffff";
    static final String DFA7_maxS =
        "\1\50\1\63\2\54\1\60\1\63\2\51\2\50\1\63\1\60\2\54\1\63\1\60\2\51"+
        "\2\50\2\61\4\uffff";
    static final String DFA7_acceptS =
        "\26\uffff\1\1\1\2\1\3\1\4";
    static final String DFA7_specialS =
        "\32\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1",
            "\1\2\2\uffff\1\3",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13",
            "\1\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26\1\27",
            "\1\30\1\31",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "70:1: HEAP : ( '(0,0)(3,3)(0,2)(1,0)(0,1)(2,2)(0,0)(1,1)' | '(0,0)(3,3)(1,0)(0,2)(0,1)(2,2)(0,0)(1,1)' | '(3,3)(0,0)(0,2)(1,0)(2,2)(0,1)(1,1)(0,0)' | '(3,3)(0,0)(1,0)(0,2)(2,2)(0,1)(1,1)(0,0)' );";
        }
    }
    static final String DFA9_eotS =
        "\32\uffff";
    static final String DFA9_eofS =
        "\32\uffff";
    static final String DFA9_minS =
        "\1\50\1\60\2\54\1\60\1\63\2\51\2\50\1\63\1\60\2\54\1\63\1\60\2\51"+
        "\2\50\2\60\4\uffff";
    static final String DFA9_maxS =
        "\1\50\1\63\2\54\1\60\1\63\2\51\2\50\1\63\1\60\2\54\1\63\1\60\2\51"+
        "\2\50\2\63\4\uffff";
    static final String DFA9_acceptS =
        "\26\uffff\1\1\1\3\1\2\1\4";
    static final String DFA9_specialS =
        "\32\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1",
            "\1\2\2\uffff\1\3",
            "\1\4",
            "\1\5",
            "\1\6",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13",
            "\1\14",
            "\1\15",
            "\1\16",
            "\1\17",
            "\1\20",
            "\1\21",
            "\1\22",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26\2\uffff\1\27",
            "\1\30\2\uffff\1\31",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "82:1: QUICK : ( '(0,0)(3,3)(0,0)(0,0)(1,1)(2,2)' | '(3,3)(0,0)(0,0)(0,0)(2,2)(1,1)' | '(0,0)(3,3)(3,3)(3,3)(1,1)(2,2)' | '(3,3)(0,0)(3,3)(3,3)(2,2)(1,1)' );";
        }
    }
    static final String DFA10_eotS =
        "\1\uffff\1\2\u009c\uffff";
    static final String DFA10_eofS =
        "\u009e\uffff";
    static final String DFA10_minS =
        "\1\0\1\60\1\uffff\4\54\1\60\1\61\2\60\1\51\1\uffff\5\51\6\50\1\63"+
        "\1\62\1\60\1\61\1\63\1\60\6\54\1\61\2\60\2\63\1\62\6\51\6\50\1\60"+
        "\1\61\1\60\1\61\1\60\1\62\1\54\1\uffff\1\54\1\uffff\1\54\2\uffff"+
        "\4\54\1\60\1\62\1\60\1\61\2\60\1\63\4\51\4\50\1\62\1\61\1\62\1\61"+
        "\4\54\1\61\1\60\1\63\1\61\1\51\1\uffff\3\51\4\50\1\60\1\61\1\60"+
        "\1\61\1\54\1\uffff\3\54\1\60\1\63\1\60\1\63\4\51\4\50\1\61\1\60"+
        "\1\63\1\62\4\54\1\63\1\60\1\63\1\60\4\51\4\50\2\61\1\60\1\61\1\54"+
        "\2\uffff\1\54\1\61\1\60";
    static final String DFA10_maxS =
        "\1\uffff\1\63\1\uffff\4\54\2\63\1\62\1\63\1\51\1\uffff\5\51\6\50"+
        "\1\63\1\62\1\60\1\61\1\63\1\60\6\54\1\61\2\60\2\63\1\62\6\51\6\50"+
        "\1\61\1\62\1\63\1\62\2\63\1\54\1\uffff\1\54\1\uffff\1\54\2\uffff"+
        "\4\54\1\60\2\62\2\61\1\62\1\63\4\51\4\50\1\62\1\61\1\62\1\61\4\54"+
        "\1\62\1\60\1\63\1\62\1\51\1\uffff\3\51\4\50\1\62\1\63\1\62\1\63"+
        "\1\54\1\uffff\3\54\1\60\1\63\1\60\1\63\4\51\4\50\1\61\1\60\1\63"+
        "\1\62\4\54\1\63\1\60\1\63\1\60\4\51\4\50\1\62\1\63\2\62\1\54\2\uffff"+
        "\1\54\1\63\1\62";
    static final String DFA10_acceptS =
        "\2\uffff\1\12\11\uffff\1\6\60\uffff\1\10\1\uffff\1\4\1\uffff\1\7"+
        "\1\11\40\uffff\1\5\14\uffff\1\2\50\uffff\1\3\1\1\3\uffff";
    static final String DFA10_specialS =
        "\1\0\u009d\uffff}>";
    static final String[] DFA10_transitionS = {
            "\50\2\1\1\uffd7\2",
            "\1\5\1\6\1\3\1\4",
            "",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13\2\uffff\1\14",
            "\1\15\1\uffff\1\16",
            "\1\20\1\uffff\1\17",
            "\1\14\2\uffff\1\21",
            "\1\22",
            "",
            "\1\23",
            "\1\24",
            "\1\25",
            "\1\26",
            "\1\27",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "\1\41",
            "\1\42",
            "\1\43",
            "\1\44",
            "\1\45",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\72",
            "\1\73",
            "\1\75\1\74",
            "\1\75\1\76",
            "\1\100\1\101\1\77\1\102",
            "\1\103\1\75",
            "\1\105\1\104\1\uffff\1\102",
            "\1\106\1\75",
            "\1\107",
            "",
            "\1\110",
            "",
            "\1\111",
            "",
            "",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\102\1\uffff\1\101",
            "\1\120",
            "\1\101\1\77",
            "\1\102\1\uffff\1\101",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\143\1\142",
            "\1\144",
            "\1\145",
            "\1\146\1\143",
            "\1\147",
            "",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157\1\uffff\1\160",
            "\1\161\1\uffff\1\160",
            "\1\160\1\uffff\1\162",
            "\1\160\1\uffff\1\163",
            "\1\164",
            "",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0099\1\u0098",
            "\2\u0099\1\u009a",
            "\1\u009a\2\u0099",
            "\1\u009b\1\u0099",
            "\1\u009c",
            "",
            "",
            "\1\u009d",
            "\1\u009a\1\uffff\1\u0099",
            "\1\u0099\1\uffff\1\u009a"
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( BUBBLE | GNOME | SHAKER | SELECTION | INSERTION | SHELL | HEAP | MERGE | QUICK | OTHERS );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_0 = input.LA(1);

                        s = -1;
                        if ( (LA10_0=='(') ) {s = 1;}

                        else if ( ((LA10_0>='\u0000' && LA10_0<='\'')||(LA10_0>=')' && LA10_0<='\uFFFF')) ) {s = 2;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}
