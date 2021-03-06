package com.wangjie.androidinject.annotation.core.base.process.field;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import com.wangjie.androidinject.annotation.core.base.process.AIAnnotationProcessor;
import com.wangjie.androidinject.annotation.present.AIPresent;

import java.lang.reflect.Field;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 2/4/15.
 */
public class AIScreenSizeFieldProcessor implements AIAnnotationProcessor<Field> {
    @Override
    public void process(AIPresent present, Field field) throws Exception {
        field.setAccessible(true);
        if (!Point.class.isAssignableFrom(field.getType())) {
            throw new Exception("field [" + field.getName() + "] must be a Point or its subclasses");
        }
        Display display = ((Activity) present.getContext()).getWindowManager().getDefaultDisplay();
        Point point = (Point) field.getType().newInstance();
        display.getSize(point);
        field.set(present, point);
    }
}
