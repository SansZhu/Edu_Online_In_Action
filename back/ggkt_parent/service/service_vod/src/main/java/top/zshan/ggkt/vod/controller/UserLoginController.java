package top.zshan.ggkt.vod.controller;

import org.springframework.web.bind.annotation.*;
import top.zshan.ggkt.result.Result;

import java.util.HashMap;

/**
 * @author SansZhu
 * @create 2022/8/27 18:35
 */
@RestController
@RequestMapping("/admin/vod/user")
@CrossOrigin//跨域
public class UserLoginController {
/*{
  "code": 20000,
  "data": {
    "token": "admin-token"
  }
}
* */
    @PostMapping("login")
    public Result login(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("token","admin-token");
        return  Result.ok(map);
    }
    /*
    * {
  "code": 20000,
  "data": {
    "roles": [
      "admin"
    ],
    "introduction": "I am a super administrator",
    "avatar": "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
    "name": "Super Admin"
  }
}
    * */
    @GetMapping("info")
    public Result info(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles","admin");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return Result.ok(map);
    }
}
