package com.urfread.breaknews.core.test;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UserService {

    // @NotNull 表示用户名不能为空
    public void createUser(@NotNull String username) {
        System.out.println("Creating user with username: " + username);
    }

    // @Nullable 表示返回值可以为 null
    @Nullable
    public String getUserInfo(int userId) {
        if (userId == 1) {
            return "User Info: John Doe";
        }
        return null;  // 返回 null 表示用户信息未找到
    }

    // @NotNull 和 @Nullable 结合使用
    public void printUserInfo(@NotNull String username, @Nullable String middleName) {
        // 对 @NotNull 参数进行检查，避免为 null
        System.out.println("Username: " + username);
        
        // 对 @Nullable 参数进行空值检查
        if (middleName != null) {
            System.out.println("Middle name: " + middleName);
        } else {
            System.out.println("Middle name is not provided.");
        }
    }

    public static void main(String[] args) {
        UserService userService = new UserService();

        // 测试 createUser 方法，传入一个非 null 的用户名
        userService.createUser("JohnDoe");

        // 测试 getUserInfo 方法，用户 ID 为 1，应该返回用户信息
        String userInfo = userService.getUserInfo(1);
        System.out.println("Returned user info: " + userInfo);

        // 测试 getUserInfo 方法，用户 ID 为 2，应该返回 null
        userInfo = userService.getUserInfo(2);
        System.out.println("Returned user info: " + userInfo);

        // 测试 printUserInfo 方法，传入有效的用户名和可选的中间名
        userService.printUserInfo("John", null);  // 中间名为空
        userService.printUserInfo("John", "William");  // 中间名不为空
    }
}
