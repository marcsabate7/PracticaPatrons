import Implementation.*;
import Factories.*;
import servicelocator2.*;
import Interfaces.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;


public class ServiceLocator2Test{

    private ServiceLocatorGeneric locator;

    @Before
    public void SimpleTest(){
        locator = new SimpleServiceLocator();
    }

    @Test
    public void testD() throws LocatorErrorGeneric {
        locator.setConstant(Integer.class, 42);
        locator.setService(InterfaceD.class, new FactoriesGeneric.FactoryD1());
        InterfaceD d = locator.getObject(InterfaceD.class);
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        ImplementationD1 d1 = (ImplementationD1) d;
        assertThat(d1.i, is(42));
    }

    @Test
    public void testC() throws LocatorErrorGeneric {
        locator.setConstant(String.class, "Testing");
        locator.setService(InterfaceC.class, new FactoriesGeneric.FactoryC1());
        InterfaceC c = locator.getObject(InterfaceC.class);
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        ImplementationC1 c1 = (ImplementationC1) c;
        assertThat(c1.s, is("Testing"));
    }

    @Test
    public void testB() throws LocatorErrorGeneric {
        locator.setConstant(Integer.class, 42);
        locator.setService(InterfaceD.class, new FactoriesGeneric.FactoryD1());
        locator.setService(InterfaceB.class, new FactoriesGeneric.FactoryB1());
        InterfaceB b = locator.getObject(InterfaceB.class);
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        ImplementationB1 b1 = (ImplementationB1) b;
        assertThat(b1.d, is(instanceOf(ImplementationD1.class)));
    }

    @Test
    public void testA() throws LocatorErrorGeneric {
        locator.setConstant(Integer.class, 42);
        locator.setConstant(String.class, "Testing");
        locator.setService(InterfaceD.class, new FactoriesGeneric.FactoryD1());
        locator.setService(InterfaceC.class, new FactoriesGeneric.FactoryC1());
        locator.setService(InterfaceB.class, new FactoriesGeneric.FactoryB1());
        locator.setService(InterfaceA.class, new FactoriesGeneric.FactoryA1());
        InterfaceA a = locator.getObject(InterfaceA.class);
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
