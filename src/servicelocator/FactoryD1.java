package servicelocator;

public class
FactoryD1 implements Factory {
    @Override
    public Object create(ServiceLocator sl) throws LocatorError {
        try {
            Integer d = (Integer) sl.getObject("2");
            return new ImplementationD1(d);
        } catch (ClassCastException ex) {
            throw new LocatorError("Hi ha hagut un problema al buscar les dependències al ServiceLocator: " + ex);
        }
    }
}
