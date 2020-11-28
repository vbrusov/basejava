package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void runUpdate(Resume resume, Object key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void runSave(Resume resume, Object key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void runDelete(Object key) {
        map.remove(((Resume) key).getUuid());
    }

    @Override
    protected Resume runGet(Object key) {
        return (Resume) key;
    }

    @Override
    protected Object getKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        ArrayList<Resume> resumeArray = new ArrayList<>(map.values());
        return resumeArray.toArray(new Resume[map.size()]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
