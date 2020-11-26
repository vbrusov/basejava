package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void runUpdate(Resume resume, Object key) {
        storage[(Integer) key] = resume;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void runSave(Resume resume, Object key) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insertResume(resume, (Integer) key);
        size++;
    }

    @Override
    protected void runDelete(Object key) {
        replaceDeletedResume((Integer) key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume runGet(Object key) {
        return storage[(Integer) key];
    }

    @Override
    protected boolean isExist(Object key) {
        return (Integer) key >= 0;
    }

    protected abstract void replaceDeletedResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract Integer getKey(String uuid);
}
