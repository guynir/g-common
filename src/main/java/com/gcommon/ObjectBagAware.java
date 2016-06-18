package com.gcommon;

/**
 * An object-bag aware is an object that can generate a list of properties stored in a single {@link ObjectsBag}.<br>
 * This function is useful for comparing two objects' states or generate hash code.<p>
 * An implementation can choose not to return all fields, depending the logic it incorporates (e.g.: sometimes part of
 * the object's fields are temporary and does not represents its full state).
 *
 * @author Guy Nir
 * @since 16/11/2011
 */
public interface ObjectBagAware {

    /**
     * @return An instance of objects bag representing the state of the object.
     */
    ObjectsBag objectsBag();
}
