package Implementation;

import Interfaces.InterfaceB;
import Interfaces.InterfaceD;

public class ImplementationB1 implements InterfaceB {
    public InterfaceD d;

    public ImplementationB1(InterfaceD d) {
        this.d = d;
    }
}
