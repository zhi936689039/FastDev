package com.mvp.mvpmodule.util.statusBar;

import com.mvp.mvpmodule.BuildConfig;
import com.mvp.mvpmodule.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @user oyzb
 * @date 2021/7/29 16:01
 */
public class RexflectionHelper {
    public static <T> T getConstantValue(final Class aClass, final String constantName) {
        try {
            return (T)aClass.getDeclaredField(constantName).get(null);
        } catch (NoSuchFieldException e) {
            LogUtil.e("error", "can not find " + constantName + " in " + aClass.getName(), BuildConfig.DEBUG);
        }
        catch (IllegalAccessException e) {
            LogUtil.e("error", constantName + " is not accessable", BuildConfig.DEBUG);
        }
        catch (IllegalArgumentException e) {
            LogUtil.e("error", "arguments error when get " + constantName, BuildConfig.DEBUG);
        }
        catch (Exception e) {
            LogUtil.e("error", "can not get constant" + constantName, BuildConfig.DEBUG);
        }

        return null;
    }

    public static <T> T invokeInstanceMethod(final Object instance, final String methodName,
                                             final Class[] parameterTypes, final Object[] parameters) {

        final Class aClass = instance.getClass();
        try {
            final Method method = aClass.getMethod(methodName, parameterTypes);
            return (T)method.invoke(instance, parameters);
        } catch (NoSuchMethodException e) {
            LogUtil.e("error", "can not find " + methodName + " in " + aClass.getName(),BuildConfig.DEBUG);
        }
        catch (IllegalAccessException e) {
            LogUtil.e("error", methodName + " is not accessible", BuildConfig.DEBUG);
        }
        catch (IllegalArgumentException e) {
            LogUtil.e("error", "arguments are error when invoking " + methodName, BuildConfig.DEBUG);
        }
        catch (InvocationTargetException e) {
            LogUtil.e("error", "an exception was thrown by the invoked method when invoking " + methodName, BuildConfig.DEBUG);
        }

        return null;
    }
}
