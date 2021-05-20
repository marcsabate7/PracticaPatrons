package Factories;

import Implementation.ImplementationB1;
import Interfaces.InterfaceB;
import Interfaces.InterfaceD;
import servicelocator.*;

public class FactoryB1 implements Factory {
    @Override
    public InterfaceB create(ServiceLocator sl) throws LocatorError {
        try {
            InterfaceD d = (InterfaceD) sl.getObject("D");
            return new ImplementationB1(d);
        } catch (ClassCastException ex) {
            throw new LocatorError("Hi ha hagut un problema al buscar les dependències al ServiceLocator: " + ex);
        }
    }
}
