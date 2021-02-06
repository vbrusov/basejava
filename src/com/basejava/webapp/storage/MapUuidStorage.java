package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {

    private final Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected boolean isExist(String uuid) {
        return map.containsKey(uuid);
    }

    @Override
    protected void runUpdate(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected void runSave(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    @Override
    protected void runDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected Resume runGet(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected String getKey(String uuid) {
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