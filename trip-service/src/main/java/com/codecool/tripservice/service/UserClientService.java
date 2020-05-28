package com.codecool.tripservice.service;

import com.codecool.tripservice.model.TripUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userservice.url}")
    private String baseUrl;

    public TripUser getTripUserByUserName(String currentUserName) {
        return null;
    }

    public String getCurrentUsername() {
        return restTemplate.getForEntity(baseUrl + "current-user", String.class).getBody();
    }

    public TripUser findById(String username) {
        ObjectMapper mapper = new ObjectMapper();
        TripUser tripUser = restTemplate.getForEntity(baseUrl + "current-user-object" + username, TripUser.class).getBody();
        return tripUser;
    }

    public List<String> getAllUserNames() {
        return restTemplate.getForEntity(baseUrl + "all-user-names", List.class).getBody();
    }

//    public List<Video> getAllVideos() {
//        ObjectMapper mapper = new ObjectMapper();
//        List videosFromService = restTemplate.getForEntity(baseUrl + "/list-all", List.class).getBody();
//        List<Video> videos = new ArrayList<>();
//        assert videosFromService != null;
//        for (Object each : videosFromService) {
//            videos.add(mapper.convertValue(each, Video.class));
//        }
//        return videos;
//    }
}
