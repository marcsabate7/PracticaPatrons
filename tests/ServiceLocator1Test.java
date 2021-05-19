import Factories.*;
import Implementation.*;
import Interfaces.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;
import servicelocator.LocatorError;
import servicelocator.ServiceLocator;
import servicelocator.SimpleServiceLocator;

/**
 * @author roger
 */
public class ServiceLocator1Test {

    private ServiceLocator locator;

    @Before
    public void FirstTest() {
        locator = new SimpleServiceLocator();
    }

    @Test
    public void getInstanceOfFactoryD1() throws LocatorError {
        System.out.println("************ Starting Test0 ************");
        System.out.println("Testing FactoryD1 is correctly registered and get a instance of it.");
        locator.setConstant("I", 42);
        locator.setService("D", new FactoryD1());
        InterfaceD d = (InterfaceD) locator.getObject("D");
        assertThat(d, is(instanceOf(ImplementationD1.class)));
        System.out.println("************ End of Test0 **************");
    }

    @Test
    public void getInstanceOfFactoryC1() throws LocatorError {
        System.out.println("************ Starting Test1 ************");
        System.out.println("Testing FactoryC1 is correctly registered and get a instance of it.");
        locator.setConstant("S", "patata");
        locator.setService("C", new FactoryC1());
        InterfaceC c = (InterfaceC) locator.getObject("C");
        assertThat(c, is(instanceOf(ImplementationC1.class)));
        System.out.println("************ End of Test1 **************");
    }

    @Test
    public void getInstaceOfFactoryB1() throws LocatorError {
        System.out.println("************ Starting Test2 ************");
        System.out.println("Testing FactoryB1 is correctly registered and get a instance of it.");
        locator.setConstant("I", 42);
        locator.setService("D", new FactoryD1());
        locator.setService("B", new FactoryB1());
        InterfaceB b = (InterfaceB) locator.getObject("B");
        assertThat(b, is(instanceOf(ImplementationB1.class)));
        System.out.println("************ End of Test2 **************");
    }

    @Test
    public void getInstanceOfFactoryA1() throws LocatorError {
        System.out.println("************ Starting Test3 ************");
        System.out.println("Testing FactoryA1 is correctly registered and get a instance of it.");
        setConstants();
        setAllFactories();
        InterfaceA a = (InterfaceA) locator.getObject("A");
        assertThat(a, is(instanceOf(ImplementationA1.class)));
        System.out.println("************ End of Test3 **************");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryA1WithoutConstant() throws LocatorError {
        System.out.println("************ Starting Test4 ************");
        System.out.println("Testing cration a FactoryA1 instance without a constant needed.");
        locator.setService("A", new FactoryA1());
        InterfaceA a = (InterfaceA) locator.getObject("A");
        System.out.println("************ End of Test4 **************");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryA1withBadArguments() throws LocatorError {
        System.out.println("************ Starting Test5 ************");
        System.out.println("Testing cration a FactoryA1 instance with bad arguments.");
        setConstants();
        locator.setService("A", new FactoryA1());
        InterfaceA a = (InterfaceA) locator.getObject("A");
        System.out.println("************ End of Test5 **************");
    }

    @Test(expected = LocatorError.class)
    public void creationFactoryB1WithBadArguments() throws LocatorError {
        System.out.println("************ Starting Test6 ************");
        System.out.println("Testing cration a FactoryB1 instance with bad arguments.");
        setConstants();
        locator.setService("B", new FactoryB1());
        InterfaceB b = (InterfaceB) locator.getObject("B");
        System.out.println("************ End of Test6 **************");
    }

    @Test(expected = LocatorError.class)
    public void getFactoryD1WithBadArguments() throws LocatorError {
        System.out.println("************ Starting Test7 ************");
        System.out.println("Testing FactoryD1 is correctly registeret but not with correct arguments and trying to get a instance of it.");
        locator.setConstant("I", 30);
        locator.setService("D", new FactoryD1());
        InterfaceD d = (InterfaceD) locator.getObject("D");
        System.out.println("************ End of Test7 **************");
    }

    @Test(expected = LocatorError.class)
    public void registerTwoTimesSameConstant() throws LocatorError {
        System.out.println("************ Starting Test8 ************");
        System.out.println("Testing to register two times the same constant.");
        locator.setConstant("S", 42);
        locator.setConstant("S", 42);
        System.out.println("************ End of Test8 **************");
    }

    @Test(expected = LocatorError.class)
    public void registerTwoTimesSameFactory() throws LocatorError {
        System.out.println("************ Starting Test9 ************");
        System.out.println("Testing to register two times the same factory.");
        locator.setService("B", new FactoryB1());
        locator.setService("B", new FactoryB1());
        System.out.println("************ End of Test9 **************");
    }

    @Test
    public void checkGetObjectReturnsDifferentsObjects() throws LocatorError {
        System.out.println("************ Starting Test10 ************");
        System.out.println("Testing to get different objects");
        setConstants();
        setAllFactories();
        ImplementationA1 A1 = (ImplementationA1) locator.getObject("A");
        ImplementationA1 A2 = (ImplementationA1) locator.getObject("A");
        ImplementationB1 B1 = (ImplementationB1) locator.getObject("B");
        ImplementationB1 B2 = (ImplementationB1) locator.getObject("B");
        ImplementationC1 C1 = (ImplementationC1) locator.getObject("C");
        ImplementationC1 C2 = (ImplementationC1) locator.getObject("C");
        ImplementationD1 D1 = (ImplementationD1) locator.getObject("D");
        ImplementationD1 D2 = (ImplementationD1) locator.getObject("D");
        assertNotSame(A1, A2);
        assertNotSame(B1, B2);
        assertNotSame(C1, C2);
        assertNotSame(D1, D2);
        System.out.println("************ End of Test10 **************");
    }

    private void setAllFactories() throws LocatorError {
        locator.setService("A", new FactoryA1());
        locator.setService("B", new FactoryB1());
        locator.setService("C", new FactoryC1());
        locator.setService("D", new FactoryD1());
    }

    private void setConstants() throws LocatorError {
        locator.setConstant("I", 42);
        locator.setConstant("S", "patata");
    }
}