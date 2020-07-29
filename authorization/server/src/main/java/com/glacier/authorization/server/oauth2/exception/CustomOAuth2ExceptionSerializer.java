package com.glacier.authorization.server.oauth2.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 异常序列化
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-29 17:05
 */
public class CustomOAuth2ExceptionSerializer extends StdSerializer<CustomOAuth2Exception> {
    private static final long serialVersionUID = -4546804004244728921L;

    protected CustomOAuth2ExceptionSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception e, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        String message = e.getMessage();
        if (message != null) {
            message = HtmlUtils.htmlEscape(message);
        }
        gen.writeStringField("code", "-1");
        gen.writeStringField("msg", message);
        gen.writeStringField("time", String.valueOf(System.currentTimeMillis()));
        if (e.getAdditionalInformation() != null) {
            for (Map.Entry<String, String> entry : e.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}
