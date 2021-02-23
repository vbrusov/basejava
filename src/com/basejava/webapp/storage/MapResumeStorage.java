package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> map = new LinkedHashMap<>();

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void doUpdate(Resume resume, Resume key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected void doSave(Resume resume, Resume key) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume key) {
        return key;
    }

    @Override
    protected void doDelete(Resume key) {
        map.remove(key.getUuid());
    }

    @Override
    public List<Resume> doCopyAll() {
        ArrayList<Resume> resumeArray = new ArrayList<>(map.values());
        return Arrays.asList(resumeArray.toArray(new Resume[map.size()]));
    }


}
