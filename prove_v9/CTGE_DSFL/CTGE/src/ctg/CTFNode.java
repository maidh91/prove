/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ctg;

import transform.AST.AST;

/**
 *
 * @author ZoZo
 */
public class CTFNode {
    public AST m_AST;
    public String m_strCode;
    
    public CTFNode(AST ast)
    {
        m_AST = ast;
    }

    @Override
    public String toString() {
        return m_strCode;
    }
    
}
