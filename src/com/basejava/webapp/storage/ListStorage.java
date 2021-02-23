package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> list = new ArrayList<Resume>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
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
    protected void doUpdate(Resume resume, Integer key) {
        list.set(key, resume);
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    protected void doSave(Resume resume, Integer key) {
        list.add(resume);
    }

    @Override
    protected Resume doGet(Integer key) {
        return list.get(key);
    }

    @Override
    protected void doDelete(Integer key) {
        list.remove(key.intValue());
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

}
