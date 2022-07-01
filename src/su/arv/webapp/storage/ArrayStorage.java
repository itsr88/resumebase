package su.arv.webapp.storage;

import su.arv.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (isExist(resume.getUuid()) || size == STORAGE_LIMIT) {
            System.out.println("SAVE Error: resume is exist or array is full");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        if (!isExist(resume.getUuid())) {
            System.out.println("UPDATE Error: resume doesnt exist");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid()))
                    storage[i] = resume;
            }
        }
    }

    public Resume get(String uuid) {
        if (!isExist(uuid)) {
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
        if (!isExist(uuid)) {
            System.out.println("DELETE Error: resume doesnt exist");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    System.arraycopy(storage, i + 1, storage, i, size - (i + 1));
                    size--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    private boolean isExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return true;
        }
        return false;
    }
}
