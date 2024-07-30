package me.pgthinker.provider.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pgthinker.common.BaseResponse;
import me.pgthinker.common.ResultUtils;
import me.pgthinker.provider.constant.RemoteAPI;
import me.pgthinker.provider.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author bodyzxy
 * @github https://github.com/bodyzxy
 * @date 2024/7/29 11:46
 */
@RestController
@RequestMapping("/dailyLife")
@RequiredArgsConstructor
@Slf4j
public class DailyLifeController {

    private final WeatherService weatherService;
    private final RestTemplate restTemplate;

    @GetMapping("/weather/getWeather/{city}")
    public BaseResponse getWeather(@PathVariable String city){
        return weatherService.getWeather(city);
    }

    /**
     * 随机回去外国城市地址
     * 假数据生成
     * @param id 数量
     * @return
     */
    @GetMapping("/city/getCityAddress/{id}")
    public BaseResponse getCityAddress(@PathVariable String id){
        try{
            ResponseEntity<String> response = restTemplate.getForEntity(RemoteAPI.CITY_ADDRESS + id, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody();
            JsonNode node = objectMapper.readTree(responseBody);
            JsonNode address = node.get("data");
            return ResultUtils.success(address);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取一本国外书籍
     * 假数据生成
     * @param id
     * @return
     */
    @GetMapping("/book/getBook/{id}")
    public BaseResponse getBook(@PathVariable String id){
        try{
            ResponseEntity<String> response = restTemplate.getForEntity(RemoteAPI.BOOK + id,String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = response.getBody();
            JsonNode node = objectMapper.readTree(responseBody);
            JsonNode book = node.get("data");

            return ResultUtils.success(book);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
