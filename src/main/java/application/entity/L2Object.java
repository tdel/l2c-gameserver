package application.entity;

public class L2Object {

    private L2ObjectId objectId;

    public L2Object(L2ObjectId _objectId) {
        this.objectId = _objectId;
    }

    public L2ObjectId getObjectId() {
        return this.objectId;
    }

}
