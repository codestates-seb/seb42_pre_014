package pre14.stackoverflow.utils;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriCreator { //0217 jinsu
    public static URI createUri(String defaultUrl, long resourceId) {
        return UriComponentsBuilder
                .newInstance()
                .path(defaultUrl + "/{resource-id}")
                .buildAndExpand(resourceId)
                .toUri();
    }
}
