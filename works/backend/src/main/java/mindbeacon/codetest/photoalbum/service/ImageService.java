package mindbeacon.codetest.photoalbum.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import mindbeacon.codetest.photoalbum.repository.Image;

@Service
public class ImageService {
	private static final Logger logger = LogManager.getLogger(ImageService.class);
	private static final int MAX_NOTFOUND_COUNT = 10;
	
	private String imageURL;
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	
	private Map<Integer, Image> imageCache = new ConcurrentHashMap<Integer, Image>();
	
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public List<Image> getImageList(int start, int size){
		List<Image> imageList = new ArrayList<Image>();
		Gson gson = new Gson();
		HttpGet request = new HttpGet();  
        request.addHeader("content-type", "application/json");  
        request.addHeader("Accept", "application/json");  
        
        int count = 0;
        int notFoundCount = 0;
    	int id = start;
        
        try {
        	//if notFoundCount less than MAX_NOTFOUND_COUNT, means no more images
        	while(count < size && notFoundCount < MAX_NOTFOUND_COUNT) {
        		
        		if(imageCache.containsKey(id)) {
        			imageList.add(imageCache.get(id));
        		}else {
        			String url = imageURL+id;
            		logger.debug("imageURL: "+url);
            		request.setURI(URI.create(url));
            		HttpResponse httpResponse = httpClient.execute(request);
        			String response = EntityUtils.toString(httpResponse.getEntity());
        			
        			//image not found, skip and notFoundCount add 1
        			if(response == null || response.isEmpty() || response.contains("Not found")) {
        				id++;
        				notFoundCount++;
        				continue;
        			}
        			
        			//if found any image, reset notFoundCount
        			notFoundCount = 0;
        			Image image = gson.fromJson(response, Image.class);
        			imageList.add(image);
        			imageCache.put(id, image);
        		}
        		id++;
    			count++;
        	}
		} catch (Exception e) {
			logger.error("Get image error:", e);
		}
        
		return imageList;
	}
}
