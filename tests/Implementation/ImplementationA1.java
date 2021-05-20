package Implementation;

import Interfaces.InterfaceA;
import Interfaces.InterfaceB;
import Interfaces.InterfaceC;

public class ImplementationA1 implements InterfaceA {
    public InterfaceB b;
    public InterfaceC c;

    public ImplementationA1(InterfaceB b, InterfaceC c) {
        this.b = b;
        this.c = c;
    }
}
