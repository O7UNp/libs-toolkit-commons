package dev.xethh.libs.toolkits.commons.sql;

import java.sql.ResultSet;
import java.util.Iterator;

public class ResultSetIterable implements Iterable<ResultSet> {
    private ResultSet resultSet;
    public ResultSetIterable(ResultSet resultset){
        this.resultSet = resultset;
    }

    @Override
    public Iterator<ResultSet> iterator() {
        return new ResultSetIterator(resultSet);
    }

}
