import Implementation.*;
import servicelocator2.*;
import Interfaces.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class ServiceLocator2Test{

    private ServiceLocatorGeneric simpleLocator;
    private ServiceLocatorGeneric cachedLocator;

    @Before
    public void SimpleTest(){
        simpleLocator = new SimpleServiceLocator();
        cachedLocator = new CachedServiceLocator();
    }

    @Test
    public void testD() throws LocatorErrorGeneric {
        simpleLocator.setConstant(Integer.class, 42);
        cachedLocator.setConstant(Integer.class, 42);
        simpleLocator.setService(InterfaceD.class, new FactoriesGeneric.FactoryD1());
        cachedLocator.setService(InterfaceD.class, new FactoriesGeneric.FactoryD1());
        InterfaceD d = simpleLocator.getObject(InterfaceD.class);
        InterfaceD cd = cachedLocator.getObject(InterfaceD.class);
        //assertEquals(cd, );
        ImplementationD1 cd1 = (ImplementationD1) cd;
        assertThat(cd1.i, is(42));
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        assertThat(d1.i, is(42));
    }

    @Test
    public void testC() throws LocatorErrorGeneric {
        simpleLocator.setConstant(String.class, "Testing");
        simpleLocator.setService(InterfaceC.class, new FactoriesGeneric.FactoryC1());
        InterfaceC c = simpleLocator.getObject(InterfaceC.class);
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        ImplementationC1 c1 = (ImplementationC1) c;
        assertThat(c1.s, is("Testing"));
    }

    @Test
    public void testB() throws LocatorErrorGeneric {
        simpleLocator.setConstant(Integer.class, 42);
        simpleLocator.setService(InterfaceD.class, new FactoriesGeneric.FactoryD1());
        simpleLocator.setService(InterfaceB.class, new FactoriesGeneric.FactoryB1());
        InterfaceB b = simpleLocator.getObject(InterfaceB.class);
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        ImplementationB1 b1 = (ImplementationB1) b;
        assertThat(b1.d, is(instanceOf(ImplementationD1.class)));
    }

    @Test
    public void testA() throws LocatorErrorGeneric {
        simpleLocator.setConstant(Integer.class, 42);
        simpleLocator.setConstant(String.class, "Testing");
        simpleLocator.setService(InterfaceD.class, new FactoriesGeneric.FactoryD1());
        simpleLocator.setService(InterfaceC.class, new FactoriesGeneric.FactoryC1());
        simpleLocator.setService(InterfaceB.class, new FactoriesGeneric.FactoryB1());
        simpleLocator.setService(InterfaceA.class, new FactoriesGeneric.FactoryA1());
        InterfaceA a = simpleLocator.getObject(InterfaceA.class);
        assertThat(a, is(instanceOf(ImplementationA1.class)));
        ImplementationA1 a1 = (ImplementationA1) a;
        assertThat(a1.b, is(instanceOf(ImplementationB1.class)));
        assertThat(a1.c, is(instanceOf(ImplementationC1.class)));
    }

    @Test(expected = LocatorErrorGeneric.class)
    public void alreadyExistingConstantInMapOfConstants() throws LocatorErrorGeneric{
        SimpleServiceLocator c = new SimpleServiceLocator();
        c.setConstant(Integer.class, 3);
        c.setConstant(Integer.class, 5);
    }

    @Test(expected = LocatorErrorGeneric.class)
    public void alreadyExistingFactoryInMapOfFactories() throws LocatorErrorGeneric{
        SimpleServiceLocator c = new SimpleServiceLocator();
        c.setService(InterfaceA.class, new FactoriesGeneric.FactoryA1());
        c.setService(InterfaceC.class, new FactoriesGeneric.FactoryC1());
        c.setService(InterfaceC.class, new FactoriesGeneric.FactoryC1());
    }

    @Test(expected = LocatorErrorGeneric.class)
    public void getObjectthatDoesntExistis() throws LocatorErrorGeneric{
        SimpleServiceLocator c = new SimpleServiceLocator();
        c.getObject(ImplementationC1.class);
    }
}