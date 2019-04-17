package org.anystub.mgmt;

import org.anystub.Base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseManagerImpl implements BaseManager {
    private static BaseManagerImpl baseManager = new BaseManagerImpl();
    private final List<Base> list = new ArrayList<>();

    public static BaseManagerImpl instance() {
        return baseManager;
    }

    private BaseManagerImpl() {

    }


    public Base getBase() {
        return getBase(getDefaultFilePath());
    }
    public Base getBase(String filename) {

        String fullPath = filename==null||filename.isEmpty()?
                getDefaultFilePath():
                getFilePath(filename);

        return get(fullPath).orElseGet(() -> new Base(fullPath));
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

    public static String getDefaultFilePath() {
        return "src/test/resources/anystub/stub.yml";
    }

    public static String getFilePath(String filename) {
        File file = new File(filename);
        if (file.getParentFile()==null || file.getParent().isEmpty()) {
            return "src/test/resources/anystub/" + file.getName();
        }
        return file.getPath();
    }

    public static String getFilePath(String path, String filename) {
        return new File(path).getPath() + new File(filename).getPath();
    }

}
