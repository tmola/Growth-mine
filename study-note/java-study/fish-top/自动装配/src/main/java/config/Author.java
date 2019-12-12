package config;

import lombok.Data;

/**
 * <p>
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/12
 */
@Data
public class Author {
    private String name;
    private String email;
    private String author;
    public Author(){}
    public Author(ConfigProperties configProperties){
        name = configProperties.getName();
        email = configProperties.getEmail();
        author = configProperties.getPhone();
    }
}
