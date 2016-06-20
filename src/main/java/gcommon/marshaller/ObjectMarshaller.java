package gcommon.marshaller;

/**
 * Definition of marshaller/unmarshaller.
 *
 * @author Guy Raz Nir
 * @since 14/06/2016
 */
public interface ObjectMarshaller {

    /**
     * Marshal a given object to raw data.
     *
     * @param object Object to convert.
     * @return Data in raw format or {@code null} if <i>object</i> is {@code null}.
     * @throws MarshalException If object could not be serialized.
     */
    byte[] marshal(Object object) throws MarshalException;

    /**
     * Unmarshal a raw data into a Java object.
     *
     * @param rawData Raw data to convert.
     * @param clazz   Class type of unmarshalled object.
     * @param <T>     Type of object.
     * @return Unmarshalled object, or {@code null} if <i>rawData</i> is {@code null}.
     * @throws MarshalException If raw data could not be converted to Java object.
     */
    <T> T unmarshal(byte[] rawData, Class<T> clazz) throws MarshalException;
}
