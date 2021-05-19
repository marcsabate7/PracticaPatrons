package servicelocator;

import java.util.HashMap;

public class SimpleServiceLocator implements ServiceLocator {

    private HashMap<String, Factory> factories;
    private HashMap<String, Object> constants;

    public SimpleServiceLocator(){

    }



    @Override
    public void setService(String name, Factory factory) throws LocatorError {
        if (this.factories.containsValue(name)){
            throw new LocatorError("Ja hi ha una factoria enregistrada amb aquest nom (factory utilitzat)");
        }else{
            this.factories.put(name,factory);
        }
    }

    @Override
    public void setConstant(String name, Object value) throws LocatorError {
        if(this.constants.containsValue(name)){
            throw new LocatorError("Ja hi ha una factoria enregistrada amb aquest nom (constant utilitzat)");
        }else{
            this.constants.put(name,value);
        }
    }

    @Override
    public Object getObject(String name) throws LocatorError {
        if(this.factories.containsKey(name)){
            return this.factories.get(name).create(this);
        }else if(this.constants.containsKey(name)){
            return this.constants.get(name);
        }else{
            throw new LocatorError("TU PUTA MADRE");
        }
    }
}
