package me.jpomykala.starters.springhoc.wrapper;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({ResponseWrapper.class})
public @interface EnableResponseWrapping {

}
