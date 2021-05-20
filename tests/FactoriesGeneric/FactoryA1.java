package FactoriesGeneric;

import servicelocator2.*;
import Implementation.ImplementationA1;
import Interfaces.InterfaceA;
import Interfaces.InterfaceB;
import Interfaces.InterfaceC;

public class FactoryA1 implements servicelocator2.Factory<InterfaceA> {

    public InterfaceA create(ServiceLocatorGeneric sl) throws LocatorErrorGeneric {
        InterfaceB b = sl.getObject(InterfaceB.class);
        InterfaceC c = sl.getObject(InterfaceC.class);
        return new ImplementationA1(b, c);
    }
}

