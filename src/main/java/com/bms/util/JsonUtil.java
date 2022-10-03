package com.bms.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cglib.beans.BeanMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.*;

public class JsonUtil {

	/**
	 * 获取泛型的Collection Type
	 * @param jsonStr json字符串
	 * @param collectionClass 泛型的Collection
	 * @param elementClasses 元素类型
	 */
	public static <T> T readJson(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {
	       ObjectMapper mapper = new ObjectMapper();

	       JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);

	       try {
				return mapper.readValue(jsonStr, javaType);
		   } catch (Exception e) {
			   	System.out.print(e.getMessage());
				return null;
		   }
	}


	/**
	 *  所有对象转JSON
	 */
	public static String ToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(obj);
			json = json.replace("null","\"\"");
			return json;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	* 将json装换为list map
	* @param jsonString
	* @return
	*/
	public static List<Map<String,Object>> JsonToListMap(String jsonString){
		List<Map<String,Object>> list = readJson(jsonString, ArrayList.class, Map.class);
		return list;
	}

	/**
	* 将json装换为list bean
	* @param jsonString
	* @param
	* @return
	*/
	public static <T> List<T> JsonToListBean(String jsonString,Class<T> clazz){
		return readJson(jsonString, ArrayList.class, clazz);
	}


	/**
	* 将json装换为map
	* @param jsonString
	* @return
	*/
	@SuppressWarnings("unchecked")
	public static Map<String,Object> JsonToMap(String jsonString){
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String,Object> map = mapper.readValue(jsonString, Map.class);
			return map;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	* 将json装换为bean
	* @param jsonString
	* @param
	* @return
	*/
	public static  <T> T JsonToBean(String jsonString,Class<T> clazz){
		return readJson(jsonString, clazz);
	}

    /**
	* 将javabean对象 装换为map
	* @param bean
	* @return
	*/
	public static <T> Map<String, Object> BeanToMap(T bean) {
		 Map<String, Object> map = new HashMap<String, Object>();
		 if (bean != null) {
		     BeanMap beanMap = BeanMap.create(bean);
		     for (Object key : beanMap.keySet()) {
		         map.put(key+"", beanMap.get(key));
		     }
		 }
		 return map;
	}

	/**
	* 将map装换为javabean对象
	* @param map
	* @param bean
	* @return
	*/
	public static <T> T MapToBean(Map<String, Object> map,T bean) {

		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}

	/**
	* 将List Bean 转换为List Map
	* @param objList
	* @return
	*/
	public static <T> List<Map<String, Object>> ListBeanToListMap(List<T> objList) {
		 List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 if (objList != null && objList.size() > 0) {
		     Map<String, Object> map = null;
		     T bean = null;
		     for (int i = 0,size = objList.size(); i < size; i++) {
		         bean = objList.get(i);
		         map = BeanToMap(bean);
		         list.add(map);
		     }
		 }
		 return list;
	}

	/**
	* 将List Map 转换为List Bean
	* @param maps
	* @param
	*/
	public static <T> List<T> ListMapToListBean(List<Map<String, Object>> maps,Class<T> clazz){
		 List<T> list = new ArrayList<T>();
		 if (maps != null && maps.size() > 0) {
		     Map<String, Object> map = null;
		     T bean = null;
		     for (int i = 0,size = maps.size(); i < size; i++) {
		         map = maps.get(i);
		         try {
					bean = clazz.newInstance();
				 } catch (Exception e) {
				 	return null;
				 }
		         MapToBean(map, bean);
		         list.add(bean);
		     }
		 }
		 return list;
	}

	/**
	 * 码表转换 用在数据导出时进行码表转换
	 * @param list
	 * @param session
	 * @param codeId
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> TransListDist(List<Map<String, Object>> list,HttpSession session,String codeId,String key) {
		 for (Map<String, Object> map : list){
			String val = (String)map.get(key);

			ServletContext context = session.getServletContext();
		    List<Map<String, Object>> vallist = (List<Map<String, Object>>)context.getAttribute(codeId);
		    for (Map<String, Object> valmap : vallist){
		    	if( valmap.get("dictid").equals(val)){
		    		map.put(key, valmap.get("dictname"));
		    	}
		    }
		 }
		 return list;
	 }



	public static Map<String, Object> keyToLowerCase(Map<String, Object> orgMap) {
		Map<String, Object> resultMap = new HashMap<>();

		if (orgMap == null || orgMap.isEmpty()) {
			return resultMap;
		}

		Set<Map.Entry<String,Object>> entrySet = orgMap.entrySet();
		for (Map.Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			resultMap.put(key.toLowerCase(), value);
		}

		return resultMap;
	}

	public static Map<String, Object> keyToUpperCase(Map<String, Object> orgMap) {
		Map<String, Object> resultMap = new HashMap<>();

		if (orgMap == null || orgMap.isEmpty()) {
			return resultMap;
		}

		Set<Map.Entry<String,Object>> entrySet = orgMap.entrySet();
		for (Map.Entry<String, Object> entry : entrySet) {
			String key = entry.getKey();
			Object value = entry.getValue();
			resultMap.put(key.toUpperCase(), value);
		}

		return resultMap;
	}




}
