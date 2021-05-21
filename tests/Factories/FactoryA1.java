package Factories;

import Implementation.ImplementationA1;
import Interfaces.InterfaceA;
import Interfaces.InterfaceB;
import Interfaces.InterfaceC;
import servicelocator.*;

public class FactoryA1 implements Factory {
    @Override
    public InterfaceA create(ServiceLocator sl) throws LocatorError {
        try {
            InterfaceB b = (InterfaceB) sl.getObject("B");
            InterfaceC c = (InterfaceC) sl.getObject("C");
            return new ImplementationA1(b, c);
        } catch (ClassCastException ex) {
            throw new LocatorError("Hi ha hagut un problema al buscar les dependències al ServiceLocator" + ex);
        }
    }
}

