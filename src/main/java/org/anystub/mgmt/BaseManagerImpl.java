package org.anystub.mgmt;

import org.anystub.Base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class BaseManagerImpl implements BaseManager {
    private static BaseManagerImpl baseManager = new BaseManagerImpl();
    private final List<Base> list = new ArrayList<>();

    public static BaseManagerImpl instance() {
        return baseManager;
    }

    private BaseManagerImpl() {

    }


    public Base getBase() {
        return getBase(getFilePath());
    }

    public Base getBase(String filename) {

        String fullPath = filename == null || filename.isEmpty() ?
                getFilePath() :
                getFilePath(filename);

        return get(fullPath).orElseGet(new Supplier<Base>() {
            @Override
            public Base get() {
                return new Base(fullPath);
            }
        });
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
                .filter(base -> base.getFilePath().equals(fullFileName))
                .findFirst();
    }

    public static String getFilePath() {
        return new File("src/test/resources/anystub/stub.yml").getPath();
    }

    public static String getFilePath(String filename) {
        File file = new File(filename);
        if (file.getParentFile() == null || file.getParent().isEmpty()) {
            return new File("src/test/resources/anystub").getPath() + File.separator + file.getPath();
        }
        return file.getPath();
    }

    public static String getFilePath(String path, String filename) {
        return new File(path).getPath() + File.separator + new File(filename).getPath();
    }

}
