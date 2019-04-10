package com.zhou.datasource.dbconfig;

import com.zhou.datasource.annotation.DBChange;
import com.zhou.datasource.model.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.naming.Name;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created with IDEA
 *
 * @author : zhoupan
 * @date : 2019/4/9 10:53
 * @info :
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.zhou.datasource.annotation.DBChange)")
    private void pointcut() {

    }
    @Autowired
    private DynamicDataSource dynamicDataSource;


    @Before("pointcut()")
    public void beforeSwitchDS(JoinPoint point){

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        Object[] objects=point.getArgs();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = null;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            Parameter[] parameters = method.getParameters();
            // 判断是否存在注解
            for (int i = 0; i <parameters.length; i++) {
                Parameter parameter = parameters[i];
                if (parameter.isAnnotationPresent(DBChange.class)) {

                    DataSource dataSource1=(DataSource) objects[i];
                    dynamicDataSource.createDataSourceWithCheck(dataSource1);
                    dataSource =dataSource1.getDatasourceId();
                    break;

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 切换数据源
            DBContextHolder.setDataSource(dataSource);
        }
    }

    @After("pointcut()")
    public void afterSwitchDS(JoinPoint point){

        DBContextHolder.clearDataSource();

    }

}
