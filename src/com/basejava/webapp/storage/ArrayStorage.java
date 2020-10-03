package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            System.out.println("----------------------------");
            System.out.println("Error. Resume " + resume.getUuid() + " already exists.");
            System.out.println("----------------------------");
        } else if (size >= storage.length) {
            System.out.println("----------------------------");
            System.out.println("Error. Storage is full.");
            System.out.println("----------------------------");
        } else {
            storage[size] = resume;
            size++;
        }
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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("----------------------------");
            System.out.println("Error. Resume " + uuid + " not found.");
            System.out.println("----------------------------");
            return null;
        }
            return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("----------------------------");
            System.out.println("Error. Resume " + uuid + " not found.");
            System.out.println("----------------------------");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
