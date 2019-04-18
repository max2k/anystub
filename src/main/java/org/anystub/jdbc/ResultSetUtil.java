package org.anystub.jdbc;

import org.h2.jdbc.JdbcBlob;
import org.h2.tools.SimpleResultSet;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class ResultSetUtil {

    public static List<String> encodeHeader(ResultSetMetaData metaData) throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        res.add(Integer.toString(metaData.getColumnCount()));
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            String columnName = metaData.getColumnName(i);
            String columnLabel = metaData.getColumnLabel(i);
            if (columnLabel == null || columnLabel.equalsIgnoreCase(columnName)) {
                res.add(columnName);
            } else {
                res.add(String.format("%s/%s", columnName, columnLabel));
            }
            res.add(metaData.getColumnTypeName(i));
            res.add(Integer.toString(metaData.getColumnType(i)));
            res.add(Integer.toString(metaData.getPrecision(i)));
            res.add(Integer.toString(metaData.getScale(i)));
        }

        return res;
    }

    public static List<String> encodeData(ResultSet resultSet, int columnsCount) throws SQLException {
        List<String> res = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsCount; i++) {
                res.add(resultSet.getString(i));
            }
        }

        return res;
    }

    public static List<String> encode(ResultSet resultSet, ResultSetMetaData resultSetMetaData) {
        List<String> res;
        try {
            res = encodeHeader(resultSetMetaData);
            res.addAll(encodeData(resultSet, resultSetMetaData.getColumnCount()));
        } catch (SQLException e) {
            throw new NoSuchElementException(e.getMessage());
        }
        return res;
    }

    public static List<String> encode(ResultSet resultSet) {
        try {
            return encode(resultSet, resultSet.getMetaData());
        } catch (SQLException e) {
            throw new UnsupportedOperationException("failed extract ResultSetMetaData");
        }
    }

    public static List<String> encode(PreparedStatement preparedStatement, ResultSet resultSet) {
        List<String> encode;
        ResultSetMetaData metaData;
        try {
            metaData = preparedStatement.getMetaData();
            encode = ResultSetUtil.encode(resultSet, metaData);
        } catch (SQLException e) {
            encode = encode(resultSet);
        }
        return encode;
    }

    public static ResultSet decode(Iterable<String> values) {
        // * support aliases in SimpleResultSet it creates 2nd column, and put copy of the data
        SimpleResultSet simpleResultSet = new SimpleResultSet();
        // * keeps column numbers, to double values
        Set<Integer> doubleColumnIndexes = new HashSet<>();

        Iterator<String> it = values.iterator();
        if (it.hasNext()) {
            int columnCount = Integer.parseInt(it.next());
            for (int i = 0; i < columnCount; i++) {
                String cNameLabel = it.next();
                String cTypeName = it.next();
                int cType = Integer.parseInt(it.next());
                int cPrecision = Integer.parseInt(it.next());
                int cScale = Integer.parseInt(it.next());


                String[] split = cNameLabel.split("/");

                if (split.length == 2 && split[0] != null && !split[0].equalsIgnoreCase(split[1])) {
                    simpleResultSet.addColumn(split[0], cType, cTypeName, cPrecision, cScale);
                    simpleResultSet.addColumn(split[1], cType, cTypeName, cPrecision, cScale);
                    doubleColumnIndexes.add(i);
                } else {
                    simpleResultSet.addColumn(cNameLabel, cType, cTypeName, cPrecision, cScale);
                }
            }
            while (it.hasNext() && columnCount > 0) {
                ArrayList<Object> row = new ArrayList<>();
                for (int i = 0; i < columnCount; i++) {
                    String next = it.next();
                    Object item;
                    try {
                        item = recoverType(next, simpleResultSet.getColumnType(i+1));
                    } catch (SQLException e) {
                        item = next;
                    }
                    row.add(item);
                    if (doubleColumnIndexes.contains(i)) {
                        row.add(item);
                    }
                }
                simpleResultSet.addRow((Object []) row.toArray(new Object[0]));
            }
        }
        return simpleResultSet;
    }


    private static Object recoverType(String next, int columnType){
        if(columnType == Types.BLOB) {
            return SqlTypeEncoder.decodeBlob(singletonList(next));
        }
        return next;
    }

}
