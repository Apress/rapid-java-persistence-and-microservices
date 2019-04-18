package com.example.blog_app.constants;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;

public class PgArrayType<T extends Serializable> implements UserType {

    protected static final int[] SQL_TYPES = {Types.ARRAY};
    private Class<T> typeParameterClass;

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return this.deepCopy(cached);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (T) this.deepCopy(value);
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {

        if (x == null) {
            return y == null;
        }
        return x.equals(y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        if (resultSet.wasNull()) {
            return null;
        }
        if (resultSet.getArray(names[0]) == null) {
            return new String[0];
        }

        Array array = resultSet.getArray(names[0]);
        @SuppressWarnings("unchecked")
        T javaArray = (T) array.getArray();
        return javaArray;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        Connection connection = preparedStatement.getConnection();
        if (value == null) {
            preparedStatement.setNull(index, SQL_TYPES[0]);
        } else {
            @SuppressWarnings("unchecked")
            T castObject = (T) value;
            Array array = connection.createArrayOf(JDBCType.VARCHAR.getName(), (Object[]) castObject);
            preparedStatement.setArray(index, array);
        }
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Class<T> returnedClass() {
        return typeParameterClass;
    }

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.ARRAY};
    }


}
