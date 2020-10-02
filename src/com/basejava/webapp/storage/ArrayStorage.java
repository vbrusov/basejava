package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int index = 0;

    public void clear() {
        Arrays.fill(storage, 0, index, null);
        index = 0;
    }

    public void save(Resume resume) {
        storage[index] = resume;
        index++;
    }

    public void update(Resume resume) {

    }

    public Resume get(String uuid) {
        for (int i = 0; i == index; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                break;
            }
            index = i;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, storage.length - 1 - index);
                index--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, index);
    }

    public int size() {
        return index;
    }
}
