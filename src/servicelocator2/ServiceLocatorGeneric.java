package servicelocator2;

public interface ServiceLocatorGeneric {
    <T> void setService(Class<T> klass, Factory<T> factory) throws LocatorErrorGeneric;
    <T> void setConstant(Class<T> klass, T value) throws LocatorErrorGeneric;
    <T> T getObject(Class<T> klass) throws LocatorErrorGeneric;
}
