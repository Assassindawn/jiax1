package com.bms.util;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.*;

public class SqlUtils {

    /**
     * 解析BoundSql，生成不含占位符的SQL语句
     * @param configuration
     * @param boundSql
     * @return
     */
    private  static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    String[] s =  metaObject.getObjectWrapper().getGetterNames();
                    s.toString();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    /**
     * 若为字符串或者日期类型，则在参数两边添加''
     * @param obj
     * @return
     */
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(new Date()) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unused")
	private static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }


    public static String getMapperSql(SqlSessionFactory sqlSessionFactory,String namespace,String methodName,Object map){
        //根据namespace+methdoName获取相对应的MappedStatement
        Configuration configuration = sqlSessionFactory.getConfiguration();
        MappedStatement mappedStatement = configuration.getMappedStatement(namespace+"."+methodName);
        //获取boundSql
        BoundSql boundSql = mappedStatement.getBoundSql(map);
        return showSql(configuration,boundSql);
    }


    /**
     * @author   yuuent
     * @description   传入行政区划编号,返回 行政区划级别
     * @create_time   2021-12-24 15:27
     */
    public static String getApa134(String aab301){
        String apa134 = "";
        //0000结尾,省级
        if (aab301.endsWith("0000")){
            apa134 = "2";
        }else if (aab301.endsWith("00")){//00结尾,市级
            apa134 = "3";
        }else {//其他结尾,区县级
            apa134 = "4";
        }
        return apa134;
    }

    /**
     * @author   yuuent
     * @description   传入行政区划编号,返回 模糊查询 行政区划编号%
     * @create_time   2021-12-24 15:33
     */
    public static String getLikeAab301(String aab301){
        //0000结尾,省级
        if (aab301.endsWith("0000")){
            aab301 = aab301.substring(0,2)+"%";
        }else if (aab301.endsWith("00")){//00结尾,市级
            aab301 = aab301.substring(0,6)+"%";
        }else {//其他结尾,区县级
            aab301 = aab301+"%";
        }
        return aab301;
    }

    /**
     * @author   yuuent
     * @description   传入要查询的字段名、行政区划编号,返回sql语句
     * @create_time   2021-12-24 15:37
     */
    public static String getInSql(String field,String aab301){
        String sql = "";
        //0000结尾,省级
        if (aab301.endsWith("0000")){
            sql = field+" in (select aab301 from pa50 where aab301 like '"+aab301.substring(0,2)+"%')";
        }else if (aab301.endsWith("00")){//00结尾,市级
            sql = field+" in (select aab301 from pa50 where aab301 like '"+aab301.substring(0,4)+"%')";
        }else {//其他结尾,区县级
            sql = field+" in ('"+aab301+"')";
        }
        return sql;
    }



    public static boolean isTableExist(String tableName, DataSource ds) {
        String tableNameU = tableName.toUpperCase();
        Connection conn = null;
        ResultSet rs = null;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
            conn = jdbcTemplate.getDataSource().getConnection();
            rs = null;
            DatabaseMetaData data = conn.getMetaData();
            String[] types = {"TABLE"};
            rs = data.getTables(null, null, tableName, types);
            if(rs.next()){
                return true;
            }
            rs = data.getTables(null, null, tableNameU, types);
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


}


