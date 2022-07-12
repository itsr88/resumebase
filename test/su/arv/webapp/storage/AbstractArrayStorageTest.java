package su.arv.webapp.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import su.arv.webapp.exception.StorageException;
import su.arv.webapp.model.Resume;

public class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveOverflow() {
        Assertions.assertThrows(StorageException.class, () -> {
            try {
                for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                    storage.save(new Resume("Name1" + i));
                }
            } catch (StorageException e) {
                Assertions.fail();
            }
            storage.save(new Resume("Overflow"));
        });
    }
}