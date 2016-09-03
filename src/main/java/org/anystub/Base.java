package org.anystub;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * provide basic access to stub-file
 * Created by Kirill on 9/2/2016.
 */
public class Base {

    private static Logger log = Logger.getLogger(Base.class.getName());
    private List<Document> documentList = new ArrayList<>();
    //    private File file;
    private String filepath;

    /**
     *
     */
    private boolean isNew = true;

    public Base() {
        filepath = "src/test/resources/anystub/stub.yml";
    }

    public Base(String filename) {
        filepath = "src/test/resources/anystub/stub.yml";
        this.filepath = filename;
    }

    public Base(String path, String filename) {
        filepath = "src/test/resources/anystub/stub.yml";
        this.filepath = new File(path).getPath() + new File(filename).getPath();
    }

    public void put(Document document) {
        documentList.add(document);
    }

    /**
     * keeps [0..count-1] as keys, the last element as value
     *
     * @param keysAndValue requested
     */
    public void put(String... keysAndValue) {
        put(new Document(Arrays.copyOf(keysAndValue, keysAndValue.length - 1))
                .setValues(keysAndValue[keysAndValue.length - 1]));
    }

    public void put(Throwable ex, String... keys) {
        documentList.add(new Document(ex, keys));
    }

    public Optional<String> getOpt(String... keys) {
        return documentList.stream()
                .filter(x -> x.keyEqual_to(keys))
                .map(Document::get)
                .findFirst();
    }

    public String get(String... keys) {
        return documentList.stream()
                .filter(x -> x.keyEqual_to(keys))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("not found"))
                .get();
    }


    public <T, E extends Exception> T request(SupplierX<T, E> supplier, String... keys) throws E {
//        Optional<String> opt = getOpt(keys);
//        if (opt.isPresent()) {
//            return opt.get();
//        }
//
//        // execute
//        String res;
//        try {
//            res = supplier.get();
//        } catch (Exception ex) {
//            put(ex, keys);
//            throw ex;
//        }
//
//        // extract values
//        put(new Document(keys).setValues(res));
//        try {
//            save();
//        } catch (IOException ex) {
//            log.warning("keep data failed: " + ex.getMessage());
//        }
//        return res;
        T res = null;
        return res;
    }


    /**
     * load file - exceptions are suppressed
     */
    public void init() {
        try {
            load();
        } catch (IOException e) {
            log.info("init: loading failed");
        }
    }

    /**
     * reload file
     */
    void load() throws IOException {

        isNew = true;
        documentList.clear();
        try (InputStream input = new FileInputStream(new File(filepath))) {
            Constructor constructor = new Constructor(Document.class);
            TypeDescription docDescription = new TypeDescription(Document.class);
            docDescription.putListPropertyType("keys", String.class);
            docDescription.putListPropertyType("values", String.class);
            docDescription.putListPropertyType("exception", String.class);
            constructor.addTypeDescription(docDescription);
            Yaml yaml = new Yaml(constructor);
            yaml.loadAll(input)
                    .forEach(x -> documentList.add((Document) x));

            isNew = false;
        } catch (FileNotFoundException e) {
            log.info("stub file not found: " + filepath);
        }
    }


    /**
     * rewrite stub file
     *
     * @throws IOException
     */
    void save() throws IOException {
        File file = new File(filepath);
        File path = file.getParentFile();

        if (path != null && !path.exists()) {
            if (path.mkdirs())
                log.info("dirs created");
            else
                throw new RuntimeException("dirs for stub isn't created");
        }
        if (!file.exists()) {
            if (file.createNewFile())
                log.info("stub file is created");
            else
                throw new RuntimeException("stub file isn't created");
        }

        try (FileWriter output = new FileWriter(file)) {
            Yaml yaml = new Yaml(new Constructor(Document.class));
            yaml.dumpAll(documentList.iterator(), output);
        }
    }

    public boolean isNew() {
        return isNew;
    }


}
