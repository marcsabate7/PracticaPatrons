package FactoriesGeneric;

import Implementation.ImplementationB1;
import Implementation.ImplementationD1;
import Interfaces.InterfaceB;
import Interfaces.InterfaceD;
import servicelocator2.LocatorErrorGeneric;
import servicelocator2.ServiceLocatorGeneric;

public class FactoryB1 implements servicelocator2.Factory<InterfaceB> {

    public InterfaceB create(ServiceLocatorGeneric sl) throws LocatorErrorGeneric {
        InterfaceD d = sl.getObject(InterfaceD.class);
        return new ImplementationB1(d);
    }
}
