package servicelocator2;

public interface Factory<T> {
    T create(ServiceLocatorGeneric sl) throws LocatorErrorGeneric;
}