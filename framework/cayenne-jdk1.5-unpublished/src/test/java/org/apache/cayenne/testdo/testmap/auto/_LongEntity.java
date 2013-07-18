package org.apache.cayenne.testdo.testmap.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _LongEntity was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _LongEntity extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    @Deprecated
    public static final String LONG_FIELD_PROPERTY = "longField";

    public static final String ID_PK_COLUMN = "ID";

    public static final Property<Long> LONG_FIELD = new Property<Long>("longField");

    public void setLongField(Long longField) {
        writeProperty("longField", longField);
    }
    public Long getLongField() {
        return (Long)readProperty("longField");
    }

}
