package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SearchKey> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SearchKey key = existKey(resume.getUuid());
        doUpdate(resume, key);
    }

    private SearchKey existKey(String uuid) {
        SearchKey key = getSearchKey(uuid);
        if (!isExist(key)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return key;
    }

    private SearchKey notExistKey(String uuid) {
        SearchKey key = getSearchKey(uuid);
        if (isExist(key)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return key;
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SearchKey key = notExistKey(resume.getUuid());
        doSave(resume, key);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SearchKey key = existKey(uuid);
        return doGet(key);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SearchKey key = existKey(uuid);
        doDelete(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> doCopyAll();

    protected abstract boolean isExist(SearchKey key);

    protected abstract void doUpdate(Resume resume, SearchKey key);

    protected abstract void doSave(Resume resume, SearchKey key);

    protected abstract void doDelete(SearchKey key);

    protected abstract Resume doGet(SearchKey key);

    protected abstract SearchKey getSearchKey(String uuid);
}
