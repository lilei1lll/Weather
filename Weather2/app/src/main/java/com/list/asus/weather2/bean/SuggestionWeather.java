package com.list.asus.weather2.bean;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 14512 on 2018/6/8
 */
public class SuggestionWeather {

    /**
     * basic : {"cid":"CN101040100","location":"重庆","parent_city":"重庆","admin_area":"重庆","cnty":"中国","lat":"29.56376076","lon":"106.55046082","tz":"+8.00"}
     * update : {"loc":"2018-06-08 19:57","utc":"2018-06-08 11:57"}
     * status : ok
     * lifestyle : [{"type":"comf","brf":"较舒适","txt":"今天夜间天气晴好，您在这种天气条件下，会感觉凉爽、舒适，偏热。"},{"type":"drsg","brf":"热","txt":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"},{"type":"flu","brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},{"type":"sport","brf":"较适宜","txt":"天气较好，较适宜进行各种运动，但因天气热，请适当减少运动时间，降低运动强度。"},{"type":"trav","brf":"适宜","txt":"天气较好，但丝毫不会影响您的心情。微风，虽天气稍热，却仍适宜旅游，不要错过机会呦！"},{"type":"uv","brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"},{"type":"cw","brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},{"type":"air","brf":"较差","txt":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"}]
     */

    private Basic basic;
    private Update update;
    private String status;
    private List<Suggestion> lifestyle = new ArrayList<>();

    public static SuggestionWeather objectFromData(String str) {

        return new Gson().fromJson(str, SuggestionWeather.class);
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Update getUpdate() {
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Suggestion> getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(List<Suggestion> lifestyle) {
        this.lifestyle = lifestyle;
    }

}
