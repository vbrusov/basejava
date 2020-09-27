import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index = 0;

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume resume) {
        storage[index] = resume;
        index++;
    }

    Resume get(String uuid) {
        for (index = 0; index == size(); index++) {
            if (storage[index].uuid.equals(uuid)) {
                break;
            }
        }
        return storage[index];
    }

    void delete(String uuid) {
        for (index = 0; index < storage.length; index++) {
            if (storage[index].uuid.equals(uuid)) {
                break;
            }
        }
        if (storage.length - 1 - index >= 0)
            System.arraycopy(storage, index + 1, storage, index, storage.length - 1 - index);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        for (index = 0; index < storage.length; index++) {
            if (storage[index] == null) {
                break;
            }
        }
        return index;
    }
}
