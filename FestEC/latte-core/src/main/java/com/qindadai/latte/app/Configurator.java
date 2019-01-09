package com.qindadai.latte.app;

import java.util.WeakHashMap;

/**
 *
 * Created by mymac on 2019/1/9.
 */
public class Configurator {

    private static final WeakHashMap<String , Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name() , false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final  WeakHashMap<String , Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    //单例模式 -懒汉模式（有问题）
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void congigure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name() , host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new  RuntimeException("Configurations is not ready , call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }




}
