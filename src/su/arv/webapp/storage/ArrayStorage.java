package su.arv.webapp.storage;

import su.arv.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int size = 0;
    private Resume[] storage = new Resume[10000];

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return storage[i];
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, size - i);
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumeWithoutNull = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumeWithoutNull[i] = storage[i];
        }
        return resumeWithoutNull;
    }

    public int size() {
        return size;
    }
}
