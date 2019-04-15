package com.ekaly.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.ekaly.tools.Tools;
import com.fasterxml.jackson.core.type.TypeReference;

public class Main5 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Path projectPath = Paths.get("/opt/wks/ekaly");
		
		Path analyze = Paths.get(projectPath + "/WebContent/transcriptions/watson/fauchon6.json");
		
    	if(Files.exists(analyze)) {
    		
    		Map<Long, Map<String, Object>> whoSaidWhatWhen = (Map<Long, Map<String, Object>>) Tools.whoSaidWhatWhen(analyze.toFile());
    		
    		Files.write(Paths.get(projectPath + "/WebContent/analysis/watson/fauchon6.json"),Tools.toJSON(whoSaidWhatWhen).getBytes());
        		
    	}
		
		
		
	}

}
