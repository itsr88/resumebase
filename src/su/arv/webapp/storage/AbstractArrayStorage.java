package su.arv.webapp.storage;

import su.arv.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 100000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return size;
    }
}
