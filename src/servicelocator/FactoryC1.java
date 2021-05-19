package servicelocator;

public class FactoryC1 implements Factory{
    @Override
    public Object create(ServiceLocator sl) throws LocatorError {
        try {
            String c = (String) sl.getObject("C");
            return new ImplementationC1(c);
        } catch (ClassCastException ex) {
            throw new LocatorError(ex);
        }
    }
}
