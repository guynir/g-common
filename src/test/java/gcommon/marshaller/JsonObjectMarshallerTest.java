package gcommon.marshaller;

import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for {@link JsonObjectMarshaller}.
 *
 * @author Guy Raz Nir
 * @since 20/06/2016
 */
public class JsonObjectMarshallerTest {

    /**
     * Marshaller under test.
     */
    private JsonObjectMarshaller marshaller = new JsonObjectMarshaller();

    /**
     * Test that marshaller returns {@code null} when input object is {@code null}.
     */
    @Test
    public void testNullValueMarshalling() {
        Assert.assertNull(marshaller.marshal(null));
    }

    /**
     * Test that unmarshaller returns {@code null} when raw input data is {@code null}.
     */
    @Test
    public void testNullRawDataUnmarshalling() {
        Assert.assertNull(marshaller.unmarshal(null, Object.class));
    }

    /**
     * Test marshalling and unmarshalling a Java object.
     */
    @Test
    public void testMarshallingAndUnmarshalling() {
        SampleBean source = new SampleBean("Some name", 99);
        byte[] raw = marshaller.marshal(source);

        SampleBean unmarshalledObject = marshaller.unmarshal(raw, SampleBean.class);

        Assert.assertEquals(source, unmarshalledObject);
    }

    /**
     * Sample bean to perform tests on.
     */
    private static class SampleBean {

        private String name;

        private int age;

        public SampleBean() {
        }

        SampleBean(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SampleBean that = (SampleBean) o;
            return age == that.age && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
    }


}
