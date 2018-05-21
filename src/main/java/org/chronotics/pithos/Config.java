package org.chronotics.pithos;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Config class to load applicationContext.xml
 * 
 * @author sglee
 * @description
 * Beans can be registerd in applicationContext.xml
 */
@Configuration
@ImportResource("classpath:applicationContext.xml")
public class Config {

}
