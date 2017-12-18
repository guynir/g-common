package gcommon.marshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Marshaller that serialize/deserialize objects to/from JSON format.
 *
 * @author Guy Raz Nir
 * @since 19/06/2016
 */
public class JsonObjectMarshaller implements ObjectMarshaller {

    /**
     * Object marshaller/unmarshaller.
     */
    private final ObjectMapper objectMapper;

    /**
     * Class constructor. Initialize the instance with default configuration.
     */
    public JsonObjectMarshaller() {
        this(defaultObjectMapper());
    }

    /**
     * Class constructor.
     *
     * @param objectMapper Object mapper to user for marshalling/unmarshalling. Value of {@code null} indicates to
     *                     use default configuration.
     */
    public JsonObjectMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper != null ? objectMapper : defaultObjectMapper();
    }

    @Override
    public byte[] marshal(Object object) throws MarshalException {
        try {
            return object == null ? null : this.objectMapper.writer().writeValueAsBytes(object);
        } catch (JsonProcessingException ex) {
            throw new MarshalException("Failed to serialize (marshal) object of type '" + object.getClass().getName() + "'.", ex);
        }
    }

    @Override
    public <T> T unmarshal(byte[] rawData, Class<T> clazz) throws MarshalException {
        if (rawData == null) {
            return null;
        }

        try {
            return this.objectMapper.reader().forType(clazz).readValue(rawData);
        } catch (IOException ex) {
            throw new MarshalException("Failed to deserialize (unmarshal) object from raw data.", ex);
        }
    }

    /**
     * @return New object mapper with default configuration.
     */
    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return objectMapper;
    }
}
