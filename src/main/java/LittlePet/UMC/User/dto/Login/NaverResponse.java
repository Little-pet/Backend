package LittlePet.UMC.User.dto.Login;

import java.util.Map;

public class NaverResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {

        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return "Not Provided";
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }

    @Override
    public String getPhone() {
        return "Not Provided";
    }


}
