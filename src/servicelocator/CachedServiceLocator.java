package servicelocator;

public class CachedServiceLocator implements ServiceLocator{

    private String associada;

    @Override
    public void setService(String name, Factory factory) throws LocatorError {

    }

    @Override
    public void setConstant(String name, Object value) throws LocatorError {

    }

    @Override
    public Object getObject(String name) throws LocatorError {
        return null;
    }
}