package mc.bape.api.miliblue;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface EventHandler {
    byte priority() default 1;
}
