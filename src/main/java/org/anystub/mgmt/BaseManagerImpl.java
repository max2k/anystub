package org.anystub.mgmt;

import org.anystub.Base;

import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BaseManagerImpl implements BaseManager {
    private static BaseManagerImpl baseManager = new BaseManagerImpl();
    private final List<Base> list = new ArrayList<>();

    public static BaseManagerImpl instance() {
        return baseManager;
    }

    private BaseManagerImpl() {

    }


    public Base getBase(String filename) {
        Base base;
        try {
            base = new Base(filename);

        } catch (StubFileAlreadyCreatedException e) {
            Optional<Base> base1 = get(e.filePath);
            if(base1.isPresent()) {
                base=base1.get();
            }else{
                throw new RuntimeException("Unknown  error");
            }
        }

        return base;
    }

    public void register(Base base) {
        if (get(base.getFilePath()).isPresent()) {
            throw new StubFileAlreadyCreatedException("Stub is already used. Consider to use BaseManager.getBase(name)", base.getFilePath());
        }
        list.add(base);

    }

    private Optional<Base> get(String fullFileName) {
        return list
                .stream()
                .filter(new Predicate<Base>() {
                    @Override
                    public boolean test(Base base) {
                        return base.getFilePath().equals(fullFileName);
                    }
                })
                .findFirst();
    }

}
