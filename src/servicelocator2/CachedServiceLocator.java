package servicelocator2;


import java.util.HashMap;

public class CachedServiceLocator implements ServiceLocatorGeneric {

    private final HashMap<Class<?>, Object> factories;
    private final HashMap<Class<?>, Object> constants;

    public CachedServiceLocator(){
        this.factories = new HashMap<>();
        this.constants = new HashMap<>();
    }
    /**
     *
     *  Següent mètode instal·la una NOVA factoria en el HashMap de factories creant-lo com a Object.
     */

    @Override
    public <T> void setService(Class<T> klass, servicelocator2.Factory<T> factory) throws LocatorErrorGeneric {
        if (this.factories.containsKey(klass)){
            throw new LocatorErrorGeneric("Ja hi ha una factoria enregistrada amb aquest nom (factory utilitzat)");
        }else{
            this.factories.put(klass, factory.create(this));
        }
    }
    /**
     * Afegeix la constant al Hashmap i llança excepció LocatorError en cas que ja hi sigui
     *
     */

    @Override
    public <T> void setConstant(Class<T> klass, T value) throws LocatorErrorGeneric {
        if(this.constants.containsKey(klass)){
            throw new LocatorErrorGeneric("Ja hi ha una factoria enregistrada amb aquest nom (constant utilitzat)");
        }else{
            this.constants.put(klass,value);
        }
    }
    /**
    * Retorna sigui constant o factoria la mateixa instància d'Object emmagatzemada anteriorment
    *
    */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getObject(Class<T> klass) throws LocatorErrorGeneric {
        if(this.factories.containsKey(klass)){
            return (T) this.factories.get(klass);
        }else if(this.constants.containsKey(klass)){
            return (T) this.constants.get(klass);
        }else{
            throw new LocatorErrorGeneric("No hi ha cap factoria ni cap consant associada en aquest nom");
        }
    }
}
