import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int index = 0;

    void clear() {
        Arrays.fill(storage, 0, index, null);
    }

    void save(Resume resume) {
        storage[index] = resume;
        index++;
    }

    Resume get(String uuid) {
        for (int i = 0; i == index; i++) {
            if (storage[i].uuid.equals(uuid)) {
                break;
            }
            index = i;
        }
        return storage[index];
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, storage.length - 1 - index);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, index);
    }

    int size() {
        return index;
    }
}
