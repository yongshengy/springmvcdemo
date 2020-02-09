package local.test.springmvc.typeconverter;

import org.springframework.core.convert.converter.Converter;

public class UserInfoConverter implements Converter<String, User> {

    @Override
    public User convert(String source) {
        String[] split = source.split("-");
        if (split.length == 3) {
            return new User(Integer.parseInt(split[0]), split[1], Integer.parseInt(split[2]));
        }
        return null;
    }
}
