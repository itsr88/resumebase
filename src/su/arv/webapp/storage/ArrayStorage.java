package su.arv.webapp.storage;

import su.arv.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private int size = 0;
    private Resume[] storage = new Resume[10000];

    private boolean isExistsResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid()))
                return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        if (isExistsResume(resume)) {
            System.out.println("Error: resume is exists");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid()))
                storage[i] = resume;
        }
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
