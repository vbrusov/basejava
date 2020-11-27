package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> map = new LinkedHashMap<>();

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
        Resume[] resumeArray = new Resume[map.size()];
        Set entries = map.entrySet();
        Iterator entriesIterator = entries.iterator();

        int i = 0;
        while (entriesIterator.hasNext()) {
            Map.Entry mapping = (Map.Entry) entriesIterator.next();
            resumeArray[i] = (Resume) mapping.getValue();
            i++;
        }
        return resumeArray;
    }

    @Override
    public int size() {
        return map.size();
    }
}
