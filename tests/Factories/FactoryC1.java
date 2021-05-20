package Factories;

import Interfaces.InterfaceC;
import servicelocator.Factory;
import Implementation.ImplementationC1;
import servicelocator.LocatorError;
import servicelocator.ServiceLocator;

public class FactoryC1 implements Factory {
    @Override
    public InterfaceC create(ServiceLocator sl) throws LocatorError {
        try {
            String c = (String) sl.getObject("S");
            return new ImplementationC1(c);
        } catch (ClassCastException ex) {
            throw new LocatorError("Hi ha hagut un problema al buscar les dependències al ServiceLocator: " + ex);
        }
    }
}
