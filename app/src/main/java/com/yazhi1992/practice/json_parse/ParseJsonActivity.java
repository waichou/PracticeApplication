package com.yazhi1992.practice.json_parse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yazhi1992.practice.R;
import com.yazhi1992.practice.json_parse.bean.User;
import com.yazhi1992.practice.json_parse.utils.FastJsonUtils;
import com.yazhi1992.practice.json_parse.utils.GsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseJsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
    }

    public void getValueForGsonClick(View view) {
        String json = "{\"username\":\"zhangsan\",\"age\":28}";
        String username = GsonUtils.getNoteJsonString(json, "username");

        //注意：在从Json中获取不到字段值时报错（要么没有这个字段，要么字段取错了）
        //String json2 = "{}";
        //String username2 = GsonUtils.getNoteJsonString(json2, "username");

        String jsonObj = "{\"code\":0,\"result\":{\"username\":\"aaa\",\"age\":12}}";
        String code = GsonUtils.getNoteJsonString(jsonObj,"code");
        if ("0".equals(code)){
            User user = GsonUtils.parserJsonToArrayBean(jsonObj, "result",User.class);
            System.out.println("user=" + user.toString());//User{username='aaa', age=12}
        }


        //--------将JSON转换成集合对象-----------start
        String jsonArr = "{\"code\":0,\"result\":[{\"username\":\"aaa\",\"age\":12},{\"username\":\"bbb\",\"age\":13}]}";
        //第一种 - List：
        String codeArr = GsonUtils.getNoteJsonString(jsonArr,"code");
        if ("0".equals(codeArr)){
            List<User> userList = GsonUtils.parserJsonToArrayBeans(GsonUtils.getNoteJsonString(jsonArr, "result"), User.class);
            System.out.println("user1=" + userList.toString());//[User{username='aaa', age=12}, User{username='bbb', age=13}]
        }

        //第二种 - List：
        List<User> userList2 = GsonUtils.parserJsonToArrayBeans(jsonArr, "result", User.class);
        System.out.println("user2=" + userList2.toString());//[User{username='aaa', age=12}, User{username='bbb', age=13}]

        //第二种 - Map：
        String jsonMap = "{\"111\":{\"username\":\"xxx\",\"age\":11},\"222\":{\"username\":\"www\",\"age\":12}}";
        Map<String, User> objectObjectMap = GsonUtils.parseJsonToMap(jsonMap);
        System.out.println("map=" + objectObjectMap);//{111={username=xxx, age=11.0}, 222={username=www, age=12.0}}


        //--------将对象（List，Map，Object）转换成Json-----------start

        //1.转换List<User>
        List<User> toUserList = new ArrayList<>();
        toUserList.add(new User("aaa",11));
        toUserList.add(new User("bbb",12));
        String toListJsonStr = GsonUtils.toJsonString(toUserList);

        //2.转换Map<String,User>
        Map<String,User> map = new HashMap<>();
        map.put("111",new User("xxx",11));
        map.put("222",new User("www",12));

        String toMapJsonStr = GsonUtils.toJsonString(map);

        //3.转换Object
        User toUser = new User("zhouwei",27);
        String toUserJsonStr = GsonUtils.toJsonString(toUser);

        System.out.println("toListJsonStr=" + toListJsonStr);//[{"username":"aaa","age":11},{"username":"bbb","age":12}]
        System.out.println("toMapJsonStr=" + toMapJsonStr);//{"111":{"username":"xxx","age":11},"222":{"username":"www","age":12}}
        System.out.println("toUserJsonStr=" + toUserJsonStr);//{"username":"zhouwei","age":27}

    }

    public void getValueForFastJsonClick(View view) {
        String json = "{\"username\":\"zhangsan\",\"age\":28}";
        String username = FastJsonUtils.getNoteJson(json, "username");

        String jsonObj = "{\"code\":0,\"result\":{\"username\":\"aaa\",\"age\":12}}";
        String code = FastJsonUtils.getNoteJson(jsonObj,"code");
        if ("0".equals(code)){
            User user = FastJsonUtils.parserObject(jsonObj, "result",User.class);
            System.out.println("user=" + user.toString());//User{username='aaa', age=12}
        }


        //--------将JSON转换成集合对象-----------start
        String jsonArr = "{\"code\":0,\"result\":[{\"username\":\"aaa\",\"age\":12},{\"username\":\"bbb\",\"age\":13}]}";
        String codeArr = FastJsonUtils.getNoteJson(jsonArr,"code");
        if ("0".equals(codeArr)){
            List<User> userList = FastJsonUtils.parserArray(jsonArr,"result", User.class);
            System.out.println("user1=" + userList.toString());//[User{username='aaa', age=12}, User{username='bbb', age=13}]
        }

        //第二种 - Map：
        String jsonMap = "{\"111\":{\"username\":\"xxx\",\"age\":11},\"222\":{\"username\":\"www\",\"age\":12}}";
        Map<String, User> objectObjectMap = FastJsonUtils.parserJsonForMap(jsonMap,User.class);
        System.out.println("map=" + objectObjectMap);//{111={username=xxx, age=11.0}, 222={username=www, age=12.0}}


        //--------将对象（List，Map，Object）转换成Json-----------start

        //1.转换List<User>
        List<User> toUserList = new ArrayList<>();
        toUserList.add(new User("aaa",11));
        toUserList.add(new User("bbb",12));
        String toListJsonStr = FastJsonUtils.toJsonString(toUserList);

        //2.转换Map<String,User>
        Map<String,User> map = new HashMap<>();
        map.put("111",new User("xxx",11));
        map.put("222",new User("www",12));

        String toMapJsonStr = GsonUtils.toJsonString(map);

        //3.转换Object
        User toUser = new User("zhouwei",27);
        String toUserJsonStr = GsonUtils.toJsonString(toUser);

        System.out.println("toListJsonStr=" + toListJsonStr);//[{"username":"aaa","age":11},{"username":"bbb","age":12}]
        System.out.println("toMapJsonStr=" + toMapJsonStr);//{"111":{"username":"xxx","age":11},"222":{"username":"www","age":12}}
        System.out.println("toUserJsonStr=" + toUserJsonStr);//{"username":"zhouwei","age":27}


    }

    /**
     * 解析成list or map
     * @param view
     *
     * 小结：List ---对应 []
     *      Map | User ---对应 {}
     * 根据Json字符串的[] 或 {} 类型对应左边类型来确定最终得到的数据类型
     */
    public void getListOrMapJsonClick(View view) {
        List<String> listStr = new ArrayList<>();
        listStr.add("aaa");
        listStr.add("bbb");
        listStr.add("ccc");

        String result = GsonUtils.toJsonString(listStr);
        System.out.println("list str=" + result);

        List<User> userList = new ArrayList<>();
        userList.add(new User("aaa",11));
        System.out.println("list user for gson=" + GsonUtils.toJsonString("list<user>="+userList));
        System.out.println("list user for fast =" + FastJsonUtils.toJsonString(userList));


        List<Map<String,String>> listMapForStr = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("aaa","bbb");
        listMapForStr.add(map);

        System.out.println("list map<String,String>=" + GsonUtils.toJsonString(listMapForStr));

        List<Map<String,User>> listMapForUser = new ArrayList<>();
        Map<String,User> mapUser = new HashMap<>();
        mapUser.put("aaa",new User("zhouwei",12));

        listMapForUser.add(mapUser);
        System.out.println("list map<String,User>=" + GsonUtils.toJsonString(listMapForUser));
    }

}
