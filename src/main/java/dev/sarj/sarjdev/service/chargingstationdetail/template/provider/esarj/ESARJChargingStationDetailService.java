package dev.sarj.sarjdev.service.chargingstationdetail.template.provider.esarj;

import dev.sarj.sarjdev.core.utils.JSONUtils;
import dev.sarj.sarjdev.entity.enums.ChargingProvider;
import dev.sarj.sarjdev.service.chargingstationdetail.template.ChargingStationDetailTemplate;
import dev.sarj.sarjdev.service.chargingstationdetail.template.provider.esarj.response.ChargingStationResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Component
public class ESARJChargingStationDetailService extends ChargingStationDetailTemplate<ChargingStationResponse> {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    @SneakyThrows
    protected ChargingStationResponse getChargingStationStatus(String chargingStationId) {

        String url = "https://esarj.com/api/station-state";
        Map<String, String> requestData = Map.of(
                "stationState", "esarj.public.web.stationState",
                "stationCode", chargingStationId);

        String csrfToken = "GjUZbL4o-F7gsmWglyEkdxTPmWthFFv0k5FQ";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("authority", "esarj.com")
                .header("accept", "application/json, text/plain, */*")
                .header("content-type", "application/json;charset=UTF-8")
                .header("cookie", "_ga=GA1.2.1740853114.1692043730; _gid=GA1.2.522927132.1692540256; _csrf=yd1uEp4W-o3gadXGYnz_iyZZ; NG_TRANSLATE_LANG_KEY=tr; _gat=1; _ga_E8BEX745N1=GS1.2.1692563673.7.1.1692563673.0.0.0; XSRF-TOKEN=3gVYpzBA-SiJB7h8QU7PEjZMcx0PM3EFvmHk")
                .header("origin", "https://esarj.com")
                .header("referer", "https://esarj.com/")
                .header("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"")
                .header("x-xsrf-token", csrfToken)
                .POST(HttpRequest.BodyPublishers.ofString(JSONUtils.serialize(requestData)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body().replace(")]}',", "");

        return JSONUtils.deserialize(responseBody, ChargingStationResponse.class);
    }

    @Override
    protected ChargingProvider getChargingProvider() {
        return ChargingProvider.ESARJ;
    }
}
