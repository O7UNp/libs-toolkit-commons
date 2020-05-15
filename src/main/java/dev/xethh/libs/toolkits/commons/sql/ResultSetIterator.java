package dev.xethh.libs.toolkits.commons.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultSetIterator implements Iterator<ResultSet> {
    private ResultSet resultSet;
    public ResultSetIterator(ResultSet resultSet){
        this.resultSet = resultSet;
    }
    private boolean clearBit = true;
    private boolean hasNext = false;
    @Override
    public boolean hasNext() {
        try {
            if(clearBit){
                hasNext = resultSet.next();
                clearBit = false;
            }
            return hasNext;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while extract next: "+e.getMessage(),e);
        }
    }

    @Override
    public ResultSet next() {
        if(clearBit)
            hasNext();
        clearBit = true;
        return resultSet;
    }

}
