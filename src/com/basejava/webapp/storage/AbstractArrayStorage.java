package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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
    protected void runUpdate(Resume resume, Integer key) {
        storage[key] = resume;
    }

    @Override
    public List<Resume> runCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    @Override
    protected void runSave(Resume resume, Integer key) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        insertResume(resume, key);
        size++;
    }

    @Override
    protected void runDelete(Integer key) {
        replaceDeletedResume(key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume runGet(Integer key) {
        return storage[key];
    }

    @Override
    protected boolean isExist(Integer key) {
        return key >= 0;
    }

    protected abstract void replaceDeletedResume(int index);

    protected abstract void insertResume(Resume resume, int index);

    protected abstract Integer getSearchKey(String uuid);
}
