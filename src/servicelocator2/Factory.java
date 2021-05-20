package servicelocator2;

public interface Factory<T> {
    T create(ServiceLocatorComplex sl) throws LocatorErrorComplex;
}