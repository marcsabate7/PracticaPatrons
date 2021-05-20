package servicelocator2;

import java.util.HashMap;

public class SimpleServiceLocator implements ServiceLocatorGeneric {

    private final HashMap<Class<?>, servicelocator2.Factory<?>> factories;
    private final HashMap<Class<?>, Object> constants;


    public SimpleServiceLocator(){
        this.factories = new HashMap<>();
        this.constants = new HashMap<>();
    }

    @Override
    public <T> void setService(Class<T> klass, servicelocator2.Factory<T> factory) throws LocatorErrorGeneric {
        if (this.factories.containsKey(klass)){
            throw new LocatorErrorGeneric("Ja hi ha una factoria enregistrada amb aquest nom (factory utilitzat)");
        }else{
            this.factories.put(klass,factory);
        }
    }

    @Override
    public <T> void setConstant(Class<T> klass, T value) throws LocatorErrorGeneric {
        if(this.constants.containsKey(klass)){
            throw new LocatorErrorGeneric("Ja hi ha una factoria enregistrada amb aquest nom (constant utilitzat)");
        }else{
            this.constants.put(klass,value);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getObject(Class<T> klass) throws LocatorErrorGeneric {
        if(this.factories.containsKey(klass)){
            return (T) this.factories.get(klass).create(this);
        }else if(this.constants.containsKey(klass)){
            return (T) this.constants.get(klass);
        }else{
            throw new LocatorErrorGeneric("No hi ha cap factoria ni cap consant associada en aquest nom");
        }
    }
}