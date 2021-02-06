package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected void runUpdate(Resume resume, Resume key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void runSave(Resume resume, Resume key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void runDelete(Resume key) {
        map.remove(key.getUuid());
    }

    @Override
    protected Resume runGet(Resume key) {
        return key;
    }

    @Override
    protected Resume getKey(String uuid) {
        return map.get(uuid);
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
