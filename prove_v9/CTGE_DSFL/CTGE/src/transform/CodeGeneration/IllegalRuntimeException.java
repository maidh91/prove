/**
 * @author Dr.Nguyen Hua Phung
 * @version 1.0
 *          28/6/2006
 *          Exception that may happen when running code
 * 
 */
package transform.CodeGeneration;

import transform.CompilationException;

@SuppressWarnings("serial")
public class IllegalRuntimeException
        extends CompilationException
{
    public IllegalRuntimeException()
    {
    }
    
    public IllegalRuntimeException(String s)
    {
    }
    
    @Override
    public String getMessage()
    {
        return "Illegal Runtime Exception";
    }
}
