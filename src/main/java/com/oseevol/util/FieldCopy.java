package com.oseevol.util;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

public class FieldCopy {

	public <S extends Object, T extends Object> void copyProperties(S source, T target) {
		if(source == null || target ==  null)
			throw new NullPointerException();
		
		
		Class<? extends Object> sourceClass = source.getClass();
		Field[] sourceFields = sourceClass.getDeclaredFields();
		
		Class<? extends Object> targetClass = target.getClass();
		
		if(sourceFields.length > 0) {
			
			Stream<Field> stream = Arrays.stream(sourceFields);
			
			stream.filter(field -> field.getName() != "id").forEach(field -> {
				try {
					Field targetField = targetClass.getDeclaredField(field.getName());
					targetField.setAccessible(true);
					targetField.set(target, field.get(source));
					
				} catch (NoSuchFieldException | InaccessibleObjectException
						| IllegalAccessException | SecurityException | NullPointerException ex) {
					ex.printStackTrace();
				}
			});
		}
	}
	
	public <S extends Object, T extends Object> void copyMethods (S source, T target) {
		if(source == null || target ==  null)
			throw new NullPointerException();
		
		
		Class<? extends Object> sourceClass = source.getClass();
		Method[] sourceMethods = sourceClass.getDeclaredMethods();
		
		Class<? extends Object> targetClass = target.getClass();
		
		if(sourceMethods.length > 0) {
			
			Stream<Method> stream = Arrays.stream(sourceMethods);
			
			stream
				.filter(method -> !method.getName().equalsIgnoreCase("getId"))
				.filter(method -> method.getName().startsWith("get"))
				.forEach(method -> {
				try {
					Object value = method.invoke(source);
					if(value != null) {
						String targetMethodName = method.getName().replace("get", "set");
						Method targetMethod = targetClass.getDeclaredMethod(targetMethodName, method.getReturnType());
						targetMethod.invoke(target, value);
					}
					
				} catch (InaccessibleObjectException | IllegalAccessException | SecurityException 
						| NullPointerException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
					ex.printStackTrace();
				} 
			});
		}
	}
}
