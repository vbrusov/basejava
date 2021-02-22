package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> list = new ArrayList<Resume>();

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    protected void runUpdate(Resume resume, Integer key) {
        list.set(key, resume);
    }

    @Override
    protected void runSave(Resume resume, Integer key) {
        list.add(resume);
    }

    @Override
    protected void runDelete(Integer key) {
        list.remove(key.intValue());
    }

    @Override
    protected Resume runGet(Integer key) {
        return list.get(key);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
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
    public List<Resume> runCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }
}
