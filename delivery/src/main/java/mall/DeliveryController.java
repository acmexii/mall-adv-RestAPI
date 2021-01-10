package mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class DeliveryController {

  private final DeliveryService deliveryService;

  public DeliveryController(DeliveryService deliveryService) {
   this.deliveryService = deliveryService;
  }

  @GetMapping("/array")
  public ResponseEntity<List<String>> array(@RequestParam("values") List<String> values) {
    return new ResponseEntity<>(values, HttpStatus.OK);
  }

  @PostMapping("/patchDelivery")
  public ResponseEntity<String> patch(@RequestBody DeliveryDTO deliveryDTO) {
    ResponseEntity<String> entity = null;
      try {
        deliveryService.patchDelivery(deliveryDTO);
        entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
      } catch (Exception e) {
        e.printStackTrace();
        entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
     return entity;
   }

 }

