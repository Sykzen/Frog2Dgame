package graphicalElements;

import util.Case;
import util.Static;


public class Element extends Case {
    /* Create an ENUM for graphical LOGO */
    final Static aStatic;

    public Element(int absc, int ord, Static aStatic) {
        super(absc, ord);
        this.aStatic = aStatic;
    }
    
    public Element(Case c, Static aStatic) {
        super(c.absc, c.ord);
        this.aStatic = aStatic;
    }
    
}
