package mindbeacon.codetest.photoalbum.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mindbeacon.codetest.photoalbum.repository.Image;
import mindbeacon.codetest.photoalbum.service.ImageService;

@RestController
@RequestMapping("/api/v1")
public class ImageContorller {
	private static final Logger logger = LogManager.getLogger(ImageContorller.class);
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("/hello/{userId}")
    @ResponseBody
    public String hello(@PathVariable String userId, @RequestParam(value="name", required = false) String name) {
		logger.debug("userId: " + userId);
		if(name != null) {
			userId = name;
		}
		String response = "hello " + userId;
        return response;
    }

	@RequestMapping("/imagelist")
    @ResponseBody
	public List<Image> getImageList(@RequestParam(value="start", required = true) int start, @RequestParam(value="size", required = true) int size){
		logger.debug("start:" + start + " size:"+size);
		List<Image> imageList = imageService.getImageList(start, size);
		return imageList;
	}
}
