package FactoriesGeneric;

import Implementation.ImplementationC1;
import Interfaces.InterfaceC;
import servicelocator2.LocatorErrorGeneric;
import servicelocator2.ServiceLocatorGeneric;

public class FactoryC1 implements servicelocator2.Factory<InterfaceC> {

    public InterfaceC create(ServiceLocatorGeneric sl) throws LocatorErrorGeneric {
        String c = sl.getObject(String.class);
        return new ImplementationC1(c);
    }
}
