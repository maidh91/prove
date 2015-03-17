package transform;

import org.antlr.runtime.Token;

@SuppressWarnings("serial")
public class CompilationException
        extends Exception
{
    public Token t;
    
    public CompilationException()
    {
        this.t = null;
    }
    
    public CompilationException(Token t)
    {
        this.t = t;
    }
    
    public Token getToken()
    {
        return this.t;
    }
}
