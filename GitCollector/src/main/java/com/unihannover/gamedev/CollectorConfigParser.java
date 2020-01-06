package com.unihannover.gamedev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CollectorConfigParser{
		
	/**
	 * reads the collectorconfig.json file and parse it into object  
	 * @return CollectorConfig object
	 */
	@SuppressWarnings("unchecked")
	public static CollectorConfig configJsonToObject() {
		
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser(); 
        try (FileReader reader = new FileReader("config/collectorConfiguration/collectorConfig.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray collectorConfigList = (JSONArray) obj;
       
            //just to get into array token of json file 
            //maybe requires polishing, but works for now 
            CollectorConfig[] config = new ObjectMapper().readValue(collectorConfigList.toString(), CollectorConfig[].class);
            //get the first object, will always be one 
            CollectorConfig collectorConfig = config[0];
		    return collectorConfig;
		    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * converts the CollectorConfig objects to Json String 
	 * @param collectorConfig
	 * @return json string of collectorConfig object 
	 */
	public static String configCollectorToJson(CollectorConfig collectorConfig) {		
		// Creating Object of ObjectMapper define in Jakson-Api 
	    ObjectMapper Obj = new ObjectMapper();
			try { 
				// get collectorConfig object as a json string 
				String jsonStr = Obj.writeValueAsString(collectorConfig); 
		        return jsonStr;
		     } catch (IOException e) { 
		        e.printStackTrace(); 
		     }
			 return null;
	}
	
	
}











