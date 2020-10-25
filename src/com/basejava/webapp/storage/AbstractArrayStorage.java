package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("----------------------------");
            System.out.println("Error. Resume " + resume.getUuid() + " not found.");
            System.out.println("----------------------------");
        } else {
            storage[index] = resume;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > 0) {
            System.out.println("----------------------------");
            System.out.println("Error. Resume " + resume.getUuid() + " already exists.");
            System.out.println("----------------------------");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("----------------------------");
            System.out.println("Error. Storage is full.");
            System.out.println("----------------------------");
        } else {
            insertIndex(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("----------------------------");
            System.out.println("Error. Resume " + uuid + " not found.");
            System.out.println("----------------------------");
        } else {
            replaceDeletedIndex(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("----------------------------");
            System.out.println("Error. Resume " + uuid + " not found.");
            System.out.println("----------------------------");
            return null;
        }
        return storage[index];
    }

    protected abstract void replaceDeletedIndex(int index);

    protected abstract void insertIndex(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}
