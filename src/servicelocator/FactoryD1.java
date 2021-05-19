package servicelocator;

public class FactoryD1 implements Factory {
    @Override
    public Object create(ServiceLocator sl) throws LocatorError {
        try {
            Integer d = (Integer) sl.getObject("D2");
            return new ImplementationD1(d);
        } catch (ClassCastException ex) {
            throw new LocatorError(ex);
        }
    }
}
