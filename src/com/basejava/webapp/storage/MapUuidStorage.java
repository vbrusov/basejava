package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage {

    private final Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected boolean isExist(Object uuid) {
        return map.containsKey((String) uuid);
    }

    @Override
    protected void runUpdate(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void runSave(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    @Override
    protected void runDelete(Object uuid) {
        map.remove(uuid);
    }

    @Override
    protected Resume runGet(Object uuid) {
        return map.get(uuid);
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> runCopyAll() {
        ArrayList<Resume> resumeArray = new ArrayList<>(map.values());
        return Arrays.asList(resumeArray.toArray(new Resume[map.size()]));
    }

    @Override
    public int size() {
        return map.size();
    }
}