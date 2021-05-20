package FactoriesGeneric;

import Implementation.ImplementationD1;
import Interfaces.InterfaceD;
import servicelocator2.LocatorErrorGeneric;
import servicelocator2.ServiceLocatorGeneric;

public class FactoryD1 implements servicelocator2.Factory<InterfaceD> {

    public InterfaceD create(ServiceLocatorGeneric sl) throws LocatorErrorGeneric {
        Integer d = sl.getObject(Integer.class);
        return new ImplementationD1(d);
    }
}
