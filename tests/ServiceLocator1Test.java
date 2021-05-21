import Factories.FactoryA1;
import Factories.FactoryB1;
import Factories.FactoryC1;
import Factories.FactoryD1;
import Implementation.ImplementationB1;
import Implementation.ImplementationC1;
import Implementation.ImplementationD1;
import Interfaces.InterfaceA;
import Interfaces.InterfaceB;
import Interfaces.InterfaceC;
import Interfaces.InterfaceD;
import org.junit.Before;
import org.junit.Test;
import servicelocator.CachedServiceLocator;
import servicelocator.LocatorError;
import servicelocator.ServiceLocator;
import servicelocator.SimpleServiceLocator;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;


public class ServiceLocator1Test {

    private ServiceLocator simpleLocator;
    private ServiceLocator cachedLocator;

    @Before
    public void FirstTest() {
        simpleLocator = new SimpleServiceLocator();
        cachedLocator = new CachedServiceLocator();
    }

    @Test
    public void assertSameConstants() throws LocatorError {
        simpleLocator.setConstant("I", 42);
        Integer i = (Integer) simpleLocator.getObject("I");
        assertSame(i, simpleLocator.getObject("I"));
        simpleLocator.setConstant("F", 30);
        Integer f = (Integer) simpleLocator.getObject("F");
        assertNotSame(f, i);

        cachedLocator.setConstant("I", 42);
        Integer ci = (Integer) cachedLocator.getObject("I");
        assertSame(ci, cachedLocator.getObject("I"));
        cachedLocator.setConstant("F", 30);
        Integer cf = (Integer) cachedLocator.getObject("F");
        assertNotSame(cf, ci);
    }

    @Test
    public void getInstanceOfFactoryD1() throws LocatorError {
        simpleLocator.setConstant("I", 42);
        simpleLocator.setService("E", new FactoryD1());
        InterfaceD d = (InterfaceD) simpleLocator.getObject("E");
        assertThat(d, is(instanceOf(ImplementationD1.class)));

        cachedLocator.setConstant("I", 42);
        cachedLocator.setService("E", new FactoryD1());
        InterfaceD cd = (InterfaceD) cachedLocator.getObject("E");
        assertSame(cd, cachedLocator.getObject("E"));
    }

    @Test
    public void getInstanceOfFactoryC1() throws LocatorError {
        simpleLocator.setConstant("S", "patata");
        simpleLocator.setService("C", new FactoryC1());
        InterfaceC c = (InterfaceC) simpleLocator.getObject("C");
        assertThat(c, is(instanceOf(ImplementationC1.class)));

        cachedLocator.setConstant("S", "patata");
        cachedLocator.setService("C", new FactoryC1());
        InterfaceC cc = (InterfaceC) cachedLocator.getObject("C");
        assertSame(cc, cachedLocator.getObject("C"));
    }

    @Test
    public void getInstaceOfFactoryB1() throws LocatorError {
        simpleLocator.setConstant("I", 42);
        simpleLocator.setService("D", new FactoryD1());
        simpleLocator.setService("B", new FactoryB1());
        InterfaceB b = (InterfaceB) simpleLocator.getObject("B");
        assertThat(b, is(instanceOf(ImplementationB1.class)));

        cachedLocator.setConstant("I", 42);
        cachedLocator.setService("D", new FactoryD1());
        cachedLocator.setService("B", new FactoryB1());
        InterfaceB cb = (InterfaceB) cachedLocator.getObject("B");
        assertSame(cb, cachedLocator.getObject("B"));
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryC1WithoutConstant() throws LocatorError {
        simpleLocator.setService("A", new FactoryA1());
        InterfaceA a = (InterfaceA) simpleLocator.getObject("A");
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryA1withBadArgumentsSimple() throws LocatorError {
        setConstantsSimple();
        simpleLocator.setService("A", new FactoryA1());
        InterfaceA a = (InterfaceA) simpleLocator.getObject("A");
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryB1WithBadArgumentsSimple() throws LocatorError {
        setConstantsSimple();
        simpleLocator.setService("B", new FactoryB1());
        InterfaceB b = (InterfaceB) simpleLocator.getObject("B");
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryB1WithBadArgumentsCached() throws LocatorError {
        setConstantsCached();
        cachedLocator.setService("B", new FactoryB1());
        InterfaceB cb = (InterfaceB) cachedLocator.getObject("B");
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void getFactoryD1WithBadArguments() throws LocatorError {
        simpleLocator.setConstant("Int", 42);
        simpleLocator.setService("D", new FactoryD1());
        InterfaceD d = (InterfaceD) simpleLocator.getObject("D");
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void getFactoryD1WithBadArgumentsCached() throws LocatorError {
        cachedLocator.setConstant("Int", 42);
        cachedLocator.setService("D", new FactoryD1());
        InterfaceD cd = (InterfaceD) cachedLocator.getObject("D");
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void registerTwoTimesSameConstant() throws LocatorError {
        simpleLocator.setConstant("S", 42);
        simpleLocator.setConstant("S", 42);
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void registerTwoTimesSameFactory() throws LocatorError {
        simpleLocator.setService("B", new FactoryB1());
        simpleLocator.setService("B", new FactoryB1());
        System.out.println("TEST FAILED");
    }

    @Test(expected = LocatorError.class)
    public void registerTwoTimesSameFactoryCached() throws LocatorError {
        cachedLocator.setService("B", new FactoryB1());
        cachedLocator.setService("B", new FactoryB1());
        System.out.println("TEST FAILED");
    }

    private void setConstantsSimple() throws LocatorError {
        simpleLocator.setConstant("I", 42);
        simpleLocator.setConstant("S", "patata");
    }

    private void setConstantsCached() throws LocatorError {
        cachedLocator.setConstant("I", 42);
        cachedLocator.setConstant("S", "patata");
    }
}