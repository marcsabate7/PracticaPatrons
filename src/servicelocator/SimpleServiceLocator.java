package servicelocator;

import java.util.HashMap;

public class SimpleServiceLocator implements ServiceLocator {

    private HashMap<String, Factory> factories;

    public SimpleServiceLocator(){

    }


    @Override
    public void setService(String name, Factory factory) throws LocatorError {
        this.factories.put(name,factory);
    }

    @Override
    public void setConstant(String name, Object value) throws LocatorError {

    }

    @Override
    public Object getObject(String name) throws LocatorError {
        return null;
    }
}
