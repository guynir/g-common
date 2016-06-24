package gcommon.objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for {@link ObjectPrinter}.
 *
 * @author Guy Raz Nir
 * @since 24/06/2016
 */
public class ObjectPrinterTest {

    /**
     * Test that all information is generated when printing object's entire hierarchy.
     */
    @Test
    public void testRecursivePrinter() {
        Object obj = new ChildClass();
        String str = ObjectPrinter.newPrinter(obj, true).toString();

        Assert.assertTrue(str.contains("ParentClass"));
        Assert.assertTrue(str.contains("parentName"));
        Assert.assertTrue(str.contains("ChildClass"));
        Assert.assertTrue(str.contains("childName"));
    }

    private static class ParentClass {
        public String parentName = "";
    }

    private static class ChildClass extends ParentClass {
        public String childName = "";
    }
}
