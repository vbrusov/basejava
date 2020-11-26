package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        Object key = existKey(resume.getUuid());
        runUpdate(resume, key);
    }

    public void save(Resume resume) {
        Object key = notExistKey(resume.getUuid());
        runSave(resume, key);
    }

    public void delete(String uuid) {
        Object key = existKey(uuid);
        runDelete(key);
    }

    public Resume get(String uuid) {
        Object key = existKey(uuid);
        return runGet(key);
    }

    private Object existKey(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private Object notExistKey(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    protected abstract boolean isExist(Object key);

    protected abstract void runUpdate(Resume resume, Object key);

    protected abstract void runSave(Resume resume, Object key);

    protected abstract void runDelete(Object key);

    protected abstract Resume runGet(Object key);

    protected abstract Object getKey(String uuid);
}
