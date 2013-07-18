package org.apache.cayenne.testdo.testmap.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _BlobTestEntity was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _BlobTestEntity extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    @Deprecated
    public static final String BLOB_COL_PROPERTY = "blobCol";

    public static final String BLOB_TEST_ID_PK_COLUMN = "BLOB_TEST_ID";

    public static final Property<byte[]> BLOB_COL = new Property<byte[]>("blobCol");

    public void setBlobCol(byte[] blobCol) {
        writeProperty("blobCol", blobCol);
    }
    public byte[] getBlobCol() {
        return (byte[])readProperty("blobCol");
    }

}
