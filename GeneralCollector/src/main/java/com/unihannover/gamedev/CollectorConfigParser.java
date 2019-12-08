package com.unihannover.gamedev;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

public class CollectorConfigParser{
		//list that contains all the CollectorsConfig object 
		static ArrayList<CollectorConfig> configList = new ArrayList<>();
		
		
		/*
		 * Reads the config.json file for collector
		 * turns each collector to an object
		 * puts them in the list 
		*/
		@SuppressWarnings("unchecked")
		public static void configParser() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("config/collectorConfiguration/collectorConfig.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray collectorConfigList = (JSONArray) obj;
            System.out.println(collectorConfigList);  
            //Iterate over colectorConfig array
            collectorConfigList.forEach( colConfig -> parseCollectorConfigObject( (JSONObject) colConfig ) );
            //just for debugging, should be removed 
            for(CollectorConfig c : configList) {
            	System.out.println(c.getName());
                System.out.println(c.getCollectorId());
                System.out.println(c.getToken());
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}   
	
		/**
		 * helper function which turn the JsonObject to java Object 
		 * @param collectorConfig
		 */
    private static void parseCollectorConfigObject(JSONObject collectorConfig) 
    {
        //Get collectorConfig object within list
        JSONObject collectorConfigObject = (JSONObject) collectorConfig.get("collectorConfig");
        Gson gson = new Gson();
        //turns the JSONObject to the collectorConfig object and adds them in the list
        CollectorConfig config1 = gson.fromJson(collectorConfigObject.toString(), CollectorConfig.class);
        configList.add(config1); 
    }
}











