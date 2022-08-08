package su.arv.webapp.storage;

import su.arv.webapp.exception.ExistStorageException;
import su.arv.webapp.exception.NotExistStorageException;
import su.arv.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract List<Resume> doCopyAll();

    public void save(Resume resume) {
        SK searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public void update(Resume resume) {
        SK searchKey = getExistedSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public Resume get(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

}