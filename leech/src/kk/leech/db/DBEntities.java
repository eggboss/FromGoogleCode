package kk.leech.db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kk.leech.util.DateUtil;
import kk.leech.util.ReflactionUtil;

import org.apache.commons.beanutils.MethodUtils;

public abstract class DBEntities {

	abstract public String[] getKeys();

	public String getTablename() {
		String className = this.getClass().getName();
		if(className.indexOf(".")==-1){
			return className;
		}else{
			return className.substring(className.lastIndexOf(".")+1);
		}
	}

	public List getExclusiveFields() {
		List sampleFields = new ArrayList();
		sampleFields.add("tablename");
		sampleFields.add("keys");
		return sampleFields;
	}

	public Map convert() {

		Map value = new HashMap();

		List fields = new ArrayList();
		ReflactionUtil.retrieveFields(fields, getClass());

		for (int i = 0; i < fields.size(); i++) {
			Field field = (Field) fields.get(i);

			String fieldName = field.getName();
			if (getExclusiveFields().contains(fieldName))
				continue;

			String prefix = "get";
			if (field.getType() == boolean.class)
				prefix = "is";

			String methodName = prefix
					+ fieldName.substring(0, 1).toUpperCase()
					+ fieldName.substring(1);
			try {
				Object fieldValue = MethodUtils.invokeMethod(this, methodName, null);
				if (fieldValue instanceof String) {
					fieldValue = ((String) fieldValue).replaceAll("'", "''");
//					fieldValue = "'" + fieldValue + "'";
				} else if (fieldValue instanceof Calendar) {
					fieldValue = DateUtil.calendarFormater(
							(Calendar) fieldValue, DateUtil.PATTERN_ORACLE);
				} else if (fieldValue instanceof Date) {
					fieldValue = DateUtil.dateFormater((Date) fieldValue,
							DateUtil.PATTERN_ORACLE);
				} else if (fieldValue instanceof Boolean) {
					if (((Boolean) fieldValue).booleanValue())
						fieldValue = "1"; //true
					else
						fieldValue = "0"; //false
				}
				if(fieldValue != null){
					value.put(fieldName, fieldValue);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return value;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		try {
			Method[] f = this.getClass().getMethods();
			for (int i = 0; i < f.length; i++) {
				if (f[i].getName().indexOf("get") != -1 && (!f[i].getName().equalsIgnoreCase("getClass"))) {
					sb.append(f[i].getName().substring(3, f[i].getName().length()).toLowerCase());
					sb.append("=");
					sb.append(f[i].invoke(this, null));
					sb.append(",");
				}
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
