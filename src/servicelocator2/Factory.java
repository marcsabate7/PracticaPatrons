package servicelocator2;

import servicelocator.LocatorError;
import servicelocator.ServiceLocator;

public interface Factory<T> {
    T create(ServiceLocatorComplex sl) throws LocatorErrorComplex;
}