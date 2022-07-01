package su.arv.webapp.storage;

import su.arv.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 100000;
    protected int size = 0;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1 || size == STORAGE_LIMIT) {
            System.out.println("SAVE Error: resume is exist or array is full");
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    public void update(Resume resume) {
        if (getIndex(resume.getUuid()) == -1) {
            System.out.println("UPDATE Error: resume doesnt exist");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid()))
                    storage[i] = resume;
            }
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) == -1) {
            System.out.println("GET Error: resume doesnt exist");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid))
                    return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("DELETE Error: resume doesnt exist");
        } else {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);

    protected abstract int getIndex(String uuid);
}
