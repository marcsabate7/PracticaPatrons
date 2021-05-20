package servicelocator2;

public interface ServiceLocatorComplex {
    <T> void setService(Class<T> klass, Factory<T> factory) throws LocatorErrorComplex;
    <T> void setConstant(Class<T> klass, T value) throws LocatorErrorComplex;
    <T> T getObject(Class<T> klass) throws LocatorErrorComplex;
}
