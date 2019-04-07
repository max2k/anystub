package org.anystub.jdbc;

import org.h2.tools.SimpleResultSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class ResultSetUtil {

    public static List<String> encodeHeader(ResultSetMetaData metaData) throws SQLException {
        ArrayList<String> res = new ArrayList<>();
        res.add(Integer.toString(metaData.getColumnCount()));
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            res.add(metaData.getColumnName(i));
            res.add(metaData.getColumnTypeName(i));
            res.add(Integer.toString(metaData.getColumnType(i)));
            res.add(Integer.toString(metaData.getPrecision(i)));
            res.add(Integer.toString(metaData.getScale(i)));

        }

        return res;
    }

    public static List<String> encodeData(ResultSet resultSet, int columnCount) throws SQLException {
        List<String> res = new ArrayList<>();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                res.add(resultSet.getString(i));
            }
        }

        return res;
    }

    public static List<String> encode(ResultSet resultSet) throws SQLException {
        LinkedList<String> res = new LinkedList<>();
        if (!resultSet.next()) {
            return res;
        }

        int columnCount = 0;
        try {
            while (true) {
                resultSet.getString(columnCount);
                columnCount++;
            }
        } catch (SQLException e) {
            res.addFirst(Integer.toString(-columnCount));
        }

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                res.add(resultSet.getString(i));
            }
        }

        return res;
    }


    public static List<String> encode(ResultSet resultSet, ResultSetMetaData resultSetMetaData) throws SQLException {
        List<String> res = encodeHeader(resultSetMetaData);
        res.addAll(encodeData(resultSet, resultSetMetaData.getColumnCount()));
        return res;
    }

    public static List<String> encode(PreparedStatement preparedStatement, ResultSet resultSet) {
        List<String> encode;
        try {
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            encode = ResultSetUtil.encode(resultSet, metaData);
        } catch (SQLException e) {
            try {
                encode = ResultSetUtil.encode(resultSet);
            } catch (SQLException e1) {
                throw new NoSuchElementException("Unable to stringify the result set");
            }
        }
        return encode;
    }

    public static ResultSet decode(Iterable<String> values) {
        SimpleResultSet simpleResultSet = new SimpleResultSet();
        Iterator<String> it = values.iterator();
        if (it.hasNext()) {
            int columnCount = Integer.parseInt(it.next());
            for (int i = 0; i < columnCount; i++) {
                String cName = it.next();
                String cTypeName = it.next(); // skip typeName
                int cType = Integer.parseInt(it.next());
                int cPrecision = Integer.parseInt(it.next());
                int cScale = Integer.parseInt(it.next());
                simpleResultSet.addColumn(cName, cType, cTypeName, cPrecision, cScale);
            }
            if (columnCount < 0) {
                columnCount = -columnCount;
            }
            while (it.hasNext()) {
                Object[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = it.next();
                }
                simpleResultSet.addRow(row);
            }
        }
        return simpleResultSet;
    }
}
