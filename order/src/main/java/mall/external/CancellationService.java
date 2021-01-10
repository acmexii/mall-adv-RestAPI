package mall.external;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@FeignClient(name="delivery", url="${api.delivery.url}")
public interface CancellationService {

    /**** CREATE ****/
    @RequestMapping(method= RequestMethod.POST, path="/cancellations")
    public void cancelShip(@RequestBody Cancellation cancellation);

    /**** READ ****/
    @RequestMapping(method= RequestMethod.GET, path="/array?values={values}")
    public List<String> array(@PathVariable("values") List<String> values);

    // When Feign default CONTRACT is used, use @RequestLine instead of @RequestMapping.
    // e.g.
    // @RequestLine("GET /array?values={values}");

    // public List<String> array(@Param("values") List<String> values);

    /**** UPDATE ****/
    @RequestMapping(method=RequestMethod.PUT, path="/deliveries/{deliveryId}")
    public String put_by_rest_api(@PathVariable("deliveryId") Long deliveryId, @RequestBody Delivery delivery);

    /**** UPDATE ****/
    @RequestMapping(method=RequestMethod.POST, path="/deliveries")
    public String patch_by_rest_api(@RequestBody Delivery delivery);

    /**** UPDATE ****/
    @RequestMapping(method=RequestMethod.POST, path="/patchDelivery")
    public String patch_by_Controller(@RequestBody Delivery delivery);

    /**** DELETE ****/
    @RequestMapping(method=RequestMethod.DELETE, path="/deliveries/{deliveryId}")
    public String delete(@PathVariable("deliveryId") Long deliveryId);
}