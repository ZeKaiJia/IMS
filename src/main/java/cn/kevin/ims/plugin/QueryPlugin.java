package cn.kevin.ims.plugin;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;

/**
 * The type Query plugin.
 * QueryPlugin
 * 加密插件
 * @Author: Kevin
 * @Date: 2020 /6/2 5:19 下午
 */
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResourceBundle.class}
        )
})
public class QueryPlugin implements Interceptor {
    /**
     * Intercept object.
     * @param invocation the invocation
     * @return the object
     * @throws Throwable the throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    /**
     * Plugin object.
     *
     * @param target the target
     * @return the object
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * Sets properties.
     *
     * @param properties the properties
     */
    @Override
    public void setProperties(Properties properties) {
    }
}
