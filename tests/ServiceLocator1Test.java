import Factories.*;
import Implementation.*;
import Interfaces.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import servicelocator.*;

public class ServiceLocator1Test {

    private ServiceLocator simpleLocator;
    private ServiceLocator cachedLocator;

    @Before
    public void FirstTest() {
        simpleLocator = new SimpleServiceLocator();
        cachedLocator = new CachedServiceLocator();
    }

    /**
     *
     *
     * @throws LocatorError
     */
    @Test
    public void assertSameConstants() throws LocatorError{
        System.out.println("************ Starting Test0 ************");
        System.out.println("Testing FactoryD1 is correctly registered and get a instance of it.");
        simpleLocator.setConstant("I", 42);
        Integer i = (Integer) simpleLocator.getObject("I");
        assertSame(i, simpleLocator.getObject("I"));
        simpleLocator.setConstant("F", 30);
        Integer f = (Integer) simpleLocator.getObject("F");
        assertNotSame(f, i);
        System.out.println("************ End of Test0 **************");
    }

    @Test
    public void getInstanceOfFactoryD1() throws LocatorError {
        System.out.println("************ Starting Test0 ************");
        System.out.println("Testing FactoryD1 is correctly registered and get a instance of it.");
        simpleLocator.setConstant("I", 42);
        simpleLocator.setService("E", new FactoryD1());
        InterfaceD d = (InterfaceD) simpleLocator.getObject("E");
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        System.out.println("************ End of Test0 **************");
    }

    @Test
    public void getInstanceOfFactoryC1() throws LocatorError {
        System.out.println("************ Starting Test1 ************");
        System.out.println("Testing FactoryC1 is correctly registered and get a instance of it.");
        simpleLocator.setConstant("S", "patata");
        simpleLocator.setService("C", new FactoryC1());
        InterfaceC c = (InterfaceC) simpleLocator.getObject("C");
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        cachedLocator.setConstant("S", "patata");
        cachedLocator.setService("C", new FactoryC1());
        InterfaceC cc = (InterfaceC) cachedLocator.getObject("C");
        assertSame(cc, cachedLocator.getObject("C"));
        System.out.println("************ End of Test1 **************");
    }

    @Test
    public void getInstaceOfFactoryB1() throws LocatorError {
        System.out.println("************ Starting Test2 ************");
        System.out.println("Testing FactoryB1 is correctly registered and get a instance of it.");
        simpleLocator.setConstant("I", 42);
        simpleLocator.setService("D", new FactoryD1());
        simpleLocator.setService("B", new FactoryB1());
        InterfaceB b = (InterfaceB) simpleLocator.getObject("B");
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        System.out.println("************ End of Test2 **************");
    }

    @Test
    public void getInstanceOfFactoryA1() throws LocatorError {
        System.out.println("************ Starting Test3 ************");
        System.out.println("Testing FactoryA1 is correctly registered and get a instance of it.");
        setConstants();
        setAllFactories();
        InterfaceA a = (InterfaceA) simpleLocator.getObject("A");
        assertThat(a, is(instanceOf(ImplementationA1.class)));
        System.out.println("************ End of Test3 **************");
    }

    @Test//(expected = LocatorError.class)
    public void creationFactoryC1WithoutConstant() throws LocatorError {
        System.out.println("************ Starting Test4 ************");
        System.out.println("Testing cration a FactoryA1 instance without a constant needed.");
        simpleLocator.setService("A", new FactoryA1());
        InterfaceA a = (InterfaceA) simpleLocator.getObject("A");
        System.out.println("************ End of Test4 **************");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryA1withBadArguments() throws LocatorError {
        System.out.println("************ Starting Test5 ************");
        System.out.println("Testing cration a FactoryA1 instance with bad arguments.");
        setConstants();
        simpleLocator.setService("A", new FactoryA1());
        InterfaceA a = (InterfaceA) simpleLocator.getObject("A");
        System.out.println("************ End of Test5 **************");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryB1WithBadArguments() throws LocatorError {
        System.out.println("************ Starting Test6 ************");
        System.out.println("Testing cration a FactoryB1 instance with bad arguments.");
        setConstants();
        simpleLocator.setService("B", new FactoryB1());
        InterfaceB b = (InterfaceB) simpleLocator.getObject("B");
        System.out.println("************ End of Test6 **************");
    }

    @Test(expected = LocatorError.class)
    public void getFactoryD1WithBadArguments() throws LocatorError {
        System.out.println("************ Starting Test7 ************");
        System.out.println("Testing FactoryD1 is correctly registeret but not with correct arguments and trying to get a instance of it.");
        simpleLocator.setConstant("Int", 42);
        simpleLocator.setService("D", new FactoryD1());
        InterfaceD d = (InterfaceD) simpleLocator.getObject("D");
        System.out.println("************ End of Test7 **************");
    }

    @Test(expected = LocatorError.class)
    public void registerTwoTimesSameConstant() throws LocatorError {
        System.out.println("************ Starting Test8 ************");
        System.out.println("Testing to register two times the same constant.");
        simpleLocator.setConstant("S", 42);
        simpleLocator.setConstant("S", 42);
        System.out.println("************ End of Test8 **************");
    }

    @Test(expected = LocatorError.class)
    public void registerTwoTimesSameFactory() throws LocatorError {
        System.out.println("************ Starting Test9 ************");
        System.out.println("Testing to register two times the same factory.");
        simpleLocator.setService("B", new FactoryB1());
        simpleLocator.setService("B", new FactoryB1());
        System.out.println("************ End of Test9 **************");
    }

    @Test
    public void checkGetObjectReturnsDifferentsObjects() throws LocatorError {
        System.out.println("************ Starting Test10 ************");
        System.out.println("Testing to get different objects");
        setConstants();
        setAllFactories();
        ImplementationA1 A1 = (ImplementationA1) simpleLocator.getObject("A");
        ImplementationA1 A2 = (ImplementationA1) simpleLocator.getObject("A");
        ImplementationB1 B1 = (ImplementationB1) simpleLocator.getObject("B");
        ImplementationB1 B2 = (ImplementationB1) simpleLocator.getObject("B");
        ImplementationC1 C1 = (ImplementationC1) simpleLocator.getObject("C");
        ImplementationC1 C2 = (ImplementationC1) simpleLocator.getObject("C");
        ImplementationD1 D1 = (ImplementationD1) simpleLocator.getObject("D");
        ImplementationD1 D2 = (ImplementationD1) simpleLocator.getObject("D");
        assertNotSame(A1, A2);
        assertNotSame(B1, B2);
        assertNotSame(C1, C2);
        assertNotSame(D1, D2);
        System.out.println("************ End of Test10 **************");
    }

    private void setAllFactories() throws LocatorError {
        simpleLocator.setService("A", new FactoryA1());
        simpleLocator.setService("B", new FactoryB1());
        simpleLocator.setService("C", new FactoryC1());
        simpleLocator.setService("D", new FactoryD1());
    }

    private void setConstants() throws LocatorError {
        simpleLocator.setConstant("I", 42);
        simpleLocator.setConstant("S", "patata");
    }
}