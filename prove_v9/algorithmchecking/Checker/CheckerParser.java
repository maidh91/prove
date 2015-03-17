// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 Checker.g 2010-05-19 21:36:55
package Checker;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class CheckerParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BUBBLE", "GNOME", "SHAKER", "SELECTION", "INSERTION", "SHELL", "HEAP", "MERGE", "QUICK", "OTHERS"
    };
    public static final int SHELL=9;
    public static final int HEAP=10;
    public static final int SHAKER=6;
    public static final int INSERTION=8;
    public static final int MERGE=11;
    public static final int BUBBLE=4;
    public static final int GNOME=5;
    public static final int EOF=-1;
    public static final int QUICK=12;
    public static final int OTHERS=13;
    public static final int SELECTION=7;

    // delegates
    // delegators


        public CheckerParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public CheckerParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
            this.state.ruleMemo = new HashMap[11+1];
             
             
        }
        

    public String[] getTokenNames() { return CheckerParser.tokenNames; }
    public String getGrammarFileName() { return "Checker.g"; }



    // $ANTLR start "check"
    // Checker.g:14:1: check returns [String s] : ( BUBBLE | GNOME | SHAKER | SELECTION | INSERTION | SHELL | HEAP | MERGE | QUICK | OTHERS | EOF );
    public final String check() throws RecognitionException {
        String s = null;
        int check_StartIndex = input.index();
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return s; }
            // Checker.g:15:2: ( BUBBLE | GNOME | SHAKER | SELECTION | INSERTION | SHELL | HEAP | MERGE | QUICK | OTHERS | EOF )
            int alt1=11;
            switch ( input.LA(1) ) {
            case BUBBLE:
                {
                alt1=1;
                }
                break;
            case GNOME:
                {
                alt1=2;
                }
                break;
            case SHAKER:
                {
                alt1=3;
                }
                break;
            case SELECTION:
                {
                alt1=4;
                }
                break;
            case INSERTION:
                {
                alt1=5;
                }
                break;
            case SHELL:
                {
                alt1=6;
                }
                break;
            case HEAP:
                {
                alt1=7;
                }
                break;
            case MERGE:
                {
                alt1=8;
                }
                break;
            case QUICK:
                {
                alt1=9;
                }
                break;
            case OTHERS:
                {
                alt1=10;
                }
                break;
            case EOF:
                {
                alt1=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return s;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Checker.g:15:4: BUBBLE
                    {
                    match(input,BUBBLE,FOLLOW_BUBBLE_in_check48); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Bubble sort";
                    }

                    }
                    break;
                case 2 :
                    // Checker.g:16:4: GNOME
                    {
                    match(input,GNOME,FOLLOW_GNOME_in_check55); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Gnome sort";
                    }

                    }
                    break;
                case 3 :
                    // Checker.g:17:4: SHAKER
                    {
                    match(input,SHAKER,FOLLOW_SHAKER_in_check62); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Shaker sort";
                    }

                    }
                    break;
                case 4 :
                    // Checker.g:18:4: SELECTION
                    {
                    match(input,SELECTION,FOLLOW_SELECTION_in_check69); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Selection sort";
                    }

                    }
                    break;
                case 5 :
                    // Checker.g:19:4: INSERTION
                    {
                    match(input,INSERTION,FOLLOW_INSERTION_in_check76); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Insertion sort";
                    }

                    }
                    break;
                case 6 :
                    // Checker.g:20:4: SHELL
                    {
                    match(input,SHELL,FOLLOW_SHELL_in_check83); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Shell sort";
                    }

                    }
                    break;
                case 7 :
                    // Checker.g:21:4: HEAP
                    {
                    match(input,HEAP,FOLLOW_HEAP_in_check90); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Heap sort";
                    }

                    }
                    break;
                case 8 :
                    // Checker.g:22:4: MERGE
                    {
                    match(input,MERGE,FOLLOW_MERGE_in_check97); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Merge sort";
                    }

                    }
                    break;
                case 9 :
                    // Checker.g:23:4: QUICK
                    {
                    match(input,QUICK,FOLLOW_QUICK_in_check104); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "Quick sort";
                    }

                    }
                    break;
                case 10 :
                    // Checker.g:24:4: OTHERS
                    {
                    match(input,OTHERS,FOLLOW_OTHERS_in_check111); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "False";
                    }

                    }
                    break;
                case 11 :
                    // Checker.g:25:4: EOF
                    {
                    match(input,EOF,FOLLOW_EOF_in_check118); if (state.failed) return s;
                    if ( state.backtracking==0 ) {
                      s = "False";
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
            if ( state.backtracking>0 ) { memoize(input, 1, check_StartIndex); }
        }
        return s;
    }
    // $ANTLR end "check"

    // Delegated rules


 

    public static final BitSet FOLLOW_BUBBLE_in_check48 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GNOME_in_check55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHAKER_in_check62 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECTION_in_check69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INSERTION_in_check76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SHELL_in_check83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_HEAP_in_check90 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MERGE_in_check97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUICK_in_check104 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_OTHERS_in_check111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EOF_in_check118 = new BitSet(new long[]{0x0000000000000002L});

}
