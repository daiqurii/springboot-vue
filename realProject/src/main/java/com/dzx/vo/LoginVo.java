package com.dzx.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @author Daiquiri
 * @version 1.0
 * @date 2022/12/8 16:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo implements Serializable {
    private String username;
    private String password;
}
