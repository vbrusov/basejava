package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> list = new ArrayList<Resume>();

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void runUpdate(Resume resume, Object key) {
        list.set((Integer) key, resume);
    }

    @Override
    protected void runSave(Resume resume, Object key) {
        list.add(resume);
    }

    @Override
    protected void runDelete(Object key) {
        list.remove(((Integer) key).intValue());
    }

    @Override
    protected Resume runGet(Object key) {
        return list.get((Integer) key);
    }

    @Override
    protected Integer getKey(String uuid) {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
