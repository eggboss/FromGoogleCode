package kk.leech.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import kk.leech.db.DBEntities;

import org.apache.commons.beanutils.MethodUtils;

public class EntitiesUtil {

	public static List convert(ResultSet rst, Class entityClass) {

		List methods = getPublicSetterMethods(entityClass);
		List entities = new ArrayList();
		try {
			while (rst.next()) {
				Object entity = Class.forName(entityClass.getName()).newInstance();
				for (Iterator i = methods.iterator(); i.hasNext();) {
					Method method = (Method) i.next();
					String propertyName = method.getName().substring(3).toLowerCase();
					
					if (((DBEntities)entity).getExclusiveFields().contains(propertyName))
						continue;
					
					Class parameterType = method.getParameterTypes()[0];
					Object columnValue = null;
					if (parameterType.equals(Date.class))
						columnValue = rst.getDate(propertyName);
					else if (parameterType.equals(int.class))
						columnValue = new Integer(rst.getInt(propertyName));
					else if (parameterType.equals(Integer.class))
						columnValue = new Integer(rst.getInt(propertyName));
					else if (parameterType.equals(double.class))
						columnValue = rst.getBigDecimal(propertyName);
					else
						columnValue = rst.getString(propertyName);
					
					if(columnValue!=null){
						MethodUtils.invokeMethod(entity, method.getName(), columnValue);
					}
				}
				entities.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return entities;
	}

	private static List getPublicSetterMethods(Class clazz) {

		return getPublicMethods(clazz, "set");
	}

	private static List getPublicMethods(Class clazz, String getterSetter) {

		List publicMethods = new ArrayList();
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (Modifier.isPublic(method.getModifiers())
					&& method.getName().startsWith(getterSetter))
				publicMethods.add(method);
		}

		return publicMethods;
	}

}
