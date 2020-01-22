package com.unihannover.gamedev;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CollectorConfigParser{
		
	/**
	 * reads the collectorconfig.json file and parse it into object  
	 * @return CollectorConfig object
	 */
	private static final String CONFIG_FILEPATH ="../../config/collectorConfiguration/collectorConfig.json";
	@Autowired
	static CollectorConfig config;

	@SuppressWarnings("unchecked")

	public static CollectorConfig configJsonToObject() {
		try
        {
            CollectorConfig collectorConfig = new ObjectMapper().readValue(new File(CONFIG_FILEPATH), CollectorConfig.class);;
            if (config == null)
			{
				config = new CollectorConfig();
				config.setCollectorId(collectorConfig.getCollectorId());
				config.setToken(collectorConfig.getToken());
				config.setName(collectorConfig.getName());

			}
		    else if (!(config.getToken().equals(collectorConfig.getToken()) && config.getName().equals(collectorConfig.getName()) && config.getCollectorId().equals(collectorConfig.getCollectorId())))
		    {
				config.setCollectorId(collectorConfig.getCollectorId());
				config.setToken(collectorConfig.getToken());
				config.setCollectorId(collectorConfig.getName());
			}
		    return config;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * converts the CollectorConfig objects to Json String 
	 * @param collectorConfig
	 * @return json string of collectorConfig object
	 */
	public static CollectorConfig configCollectorToJson(CollectorConfig collectorConfig) {
		// Creating Object of ObjectMapper define in Jakson-Api 
	    /*
		ObjectMapper Obj = new ObjectMapper();
			try { 
				// get collectorConfig object as a json string 
				String jsonStr = Obj.writeValueAsString(collectorConfig); 
		        return jsonStr;
		     } catch (IOException e) { 
		        e.printStackTrace(); 
		     }
			 return null;*/
		try {
			(new ObjectMapper()).writeValue(new File(CONFIG_FILEPATH), collectorConfig);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
}











