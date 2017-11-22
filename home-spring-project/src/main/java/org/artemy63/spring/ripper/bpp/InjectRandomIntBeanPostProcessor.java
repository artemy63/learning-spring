package org.artemy63.spring.ripper.bpp;

import org.artemy63.spring.ripper.annotations.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

public class InjectRandomIntBeanPostProcessor implements BeanPostProcessor {

    //вызывается до init метода бина
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(InjectRandomInt.class)) {
                InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
                int min = annotation.min();
                int max = annotation.max();
                field.setAccessible(true);
                Random random = new Random();
                int randomInt = min + random.nextInt(max - min);
                ReflectionUtils.setField(field, bean, randomInt);
            }
        }

        return bean;
    }

    //вызывается после метода postConstruct()
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
