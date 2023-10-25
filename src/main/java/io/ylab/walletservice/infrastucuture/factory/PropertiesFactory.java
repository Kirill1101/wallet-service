package io.ylab.walletservice.infrastucuture.factory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesFactory {
    private static Properties properties;

    /**
     * Метод для получения конфигурации приложения.
     * @return Возвращает экземпляр класса Properties.
     */
    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try(InputStream in = Files.newInputStream(Paths.get("src/main/resources/configuration.properties"))){
                properties.load(in);
            } catch (IOException e) {
                System.out.println(e.getMessage());;
            }
        }
        return properties;
    }
}
